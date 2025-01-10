package cares.cwds.salesforce.utilities.reports.extentmodel;



import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.utilities.reports.constants.ReportContants;
import cares.cwds.salesforce.utilities.reports.extentreport.ExtentReport;
import cares.cwds.salesforce.utilities.reports.htmlmodel.TestManager;
import cares.cwds.salesforce.utilities.reports.htmlmodel.TestStep;
import cares.cwds.salesforce.utilities.reports.htmlreport.HTMLReports;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.reports.model.TestStepModel;

public class ExtentUtilities
{
	private static final Logger logger =LoggerFactory.getLogger(ExtentUtilities.class.getName());
	public TestCaseDetails initializeNewTestCase(String testcasename, String testcasedescription,
            String module, String testCaseCategory, String browser) throws InterruptedException {

LocalDateTime starttime = LocalDateTime.now();
TestCaseDetails testCaseDetails = new TestCaseDetails();

Map<UUID, TestCaseDetails> tcDetails = testCaseDetails.addNewTestCase(testcasename, 
                                        testcasename + "_" + module + "_" + browser, 
                                        testcasedescription, module,  
                                        testCaseCategory, starttime);

ExtentReport extentReport = new ExtentReport();
extentReport.startTest(testcasename, module, browser, testcasedescription);

HTMLReports htmlReports = new HTMLReports();
String tcLiveFilePath = htmlReports.createTCHTML(testcasename, module, browser);

htmlReports.createTCHTMLLive(testcasename, module, browser, ReportContants.getStatusInProgress(), tcLiveFilePath);
htmlReports.createDashboardHTMLLiveSummary();

TestManager.addTestCase(testcasename, module, browser);

TestRunDetails.testCaseRepository.add(tcDetails);
HashMap<String, UUID> tcMapping = new HashMap<>();

Optional<Map.Entry<UUID, TestCaseDetails>> firstEntryOptional = tcDetails.entrySet().stream().findFirst();

if (firstEntryOptional.isPresent()) {
Map.Entry<UUID, TestCaseDetails> firstEntry = firstEntryOptional.get();
UUID key = firstEntry.getKey();
String testCaseFullName = tcDetails.get(key).testCaseFullName;
tcMapping.put(testCaseFullName, key);
} 
TestRunDetails.testCaseMapping.add(tcMapping);
return testCaseDetails;
}



	public UUID getTestCaseID(String testCaseName, String module, String browser)
	{

		for (HashMap<String, UUID> DictKey : TestRunDetails.testCaseMapping)
		{
			if (DictKey.containsKey(testCaseName + "_" + module + "_" + browser))
			{
				return DictKey.get(testCaseName + "_" + module + "_" + browser);
			}
		}

		return null;
	}

	public void endTestCase(String testCaseName, String module, String browser)
	{
		HTMLReports htmlReports = new HTMLReports();
		htmlReports.updateTCHTMLLive(testCaseName, module, browser, ReportContants.getStatusCompleted());
	}


	public void log(TestCaseParam testCaseParam, TestStepModel testStepModel) {
	    String browser = TestCaseParam.getBrowser();
	    UUID tcId = getTestCaseID(TestCaseParam.getTestCaseName(), TestCaseParam.getModuleName(), browser);

	    TestStepDetails tsDetail = new TestStepDetails();
	    tsDetail.setStepName(testStepModel.getTestStepName());
	    tsDetail.setStepDescription(testStepModel.getTestStepDescription());
	    tsDetail.setStartTime(testStepModel.getStartTime());
	    tsDetail.setEndTime(testStepModel.getEndTime());
	    tsDetail.setExtentStatus(tsDetail.getExtentStatus(testStepModel.getTestStepStatus()));
	    tsDetail.setScreenShotData(testStepModel.getScreenShotData());
	    tsDetail.setErrorMessage(testStepModel.getErrorMessage());
	    tsDetail.setErrorDetails(testStepModel.getErrorDetails());
	    tsDetail.setExpectedResponse(testStepModel.getExpectedResponse());
	    tsDetail.setActualResponse(testStepModel.getActualResponse());
	    tsDetail.setModuleName(testStepModel.getModuleName());
	    tsDetail.setScreenName(testStepModel.getScreenName());
	    tsDetail.setTestStepType(testStepModel.getTestStepType());
	    tsDetail.setTestStepFormat(testStepModel.getTestStepFormat());
	    
	    Optional<Map<UUID, TestCaseDetails>> testCaseOptional = TestRunDetails.testCaseRepository.stream()
	            .filter(x -> x.containsKey(tcId))
	            .findFirst();

	    if (testCaseOptional.isPresent()) {
	        Map<UUID, TestCaseDetails> testCaseMap = testCaseOptional.get();
	        TestCaseDetails testCaseDetails = testCaseMap.get(tcId);
	        
	        if (testCaseDetails != null) {
	            testCaseDetails.getStepDetails().add(tsDetail);
	            testCaseDetails.browser = TestCaseParam.getBrowser();
	        } 
	    } 

	    ExtentReport extentReport = new ExtentReport();
	    extentReport.addTestStepsLogs(testCaseParam, tsDetail);

	    HTMLReports htmlReports = new HTMLReports();
	    htmlReports.addTestStepsLogs(testCaseParam, tsDetail);

	    try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        String startTime = tsDetail.startTime.format(formatter);
	        String endTime = tsDetail.endTime.format(formatter);

	        String tsStatus = tsDetail.extentStatus.toString().toUpperCase();
	        TestStep testStep = new TestStep();
	        testStep = testStep.addTestStep(tsDetail.stepName, tsStatus, startTime, endTime, tsDetail.duration);

	        TestManager.addTestStep(TestCaseParam.getTestCaseName(), TestCaseParam.getModuleName(), TestCaseParam.getBrowser(), testStep);
	    } catch (IOException e) {
	    	logger.info(Arrays.toString(e.getStackTrace()));
	    }

	}

	public List<Map<UUID, TestCaseDetails>> getTestRunDetails()
	{

		return TestRunDetails.testCaseRepository;
	}
	
	public void logPdf(TestStepModel testStepModel) {
	    String browser = TestCaseParam.getBrowser();
	    UUID tcId = getTestCaseID(TestCaseParam.getTestCaseName(), TestCaseParam.getModuleName(), browser);

	    TestStepDetails tsDetail = new TestStepDetails()
	            .addTestStepDetailsPdf(testStepModel.getTestStepName(),
	                    testStepModel.getTestStepDescription(), testStepModel.getStartTime(),
	                    testStepModel.getEndTime(), testStepModel.getTestStepStatus(),
	                    testStepModel.getErrorMessage());

	    Optional<Map<UUID, TestCaseDetails>> testCaseOptional = TestRunDetails.testCaseRepository.stream()
	            .filter(x -> x.containsKey(tcId))
	            .findFirst();

	    if (testCaseOptional.isPresent()) {
	        Map<UUID, TestCaseDetails> testCaseMap = testCaseOptional.get();
	        TestCaseDetails testCaseDetails = testCaseMap.get(tcId);

	        if (testCaseDetails != null) {
	            testCaseDetails.getStepDetails().add(tsDetail);
	            testCaseDetails.browser = TestCaseParam.getBrowser();
	        } 
	    } 
	}
	

}
