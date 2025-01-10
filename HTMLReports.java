package cares.cwds.salesforce.utilities.reports.htmlreport;

import com.aventstack.extentreports.ExtentTest;

import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.constants.HTMLReportContants;
import cares.cwds.salesforce.utilities.reports.constants.ReportContants;
import cares.cwds.salesforce.utilities.reports.extentmodel.TestStepDetails;
import cares.cwds.salesforce.utilities.reports.htmlmodel.HTMLTCLiveModel;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.reports.testresultmodel.BrowserResult;
import cares.cwds.salesforce.utilities.reports.testresultmodel.ModuleResult;
import cares.cwds.salesforce.utilities.reports.testresultmodel.TestCaseResult;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTMLReports {

	private static final Logger logger =LoggerFactory.getLogger(HTMLReports.class.getName());
	
	protected static final Map<String, String> TCHTMLMapping = new HashMap<>();	


	public void generateReport(String reportPath)
	{
		try {
			
			logger.info("Initial ReportPath: {}", reportPath);

			reportPath = reportPath + HTMLReportContants.FILE_HTML_SUMMARY;
			String reportPath2 = reportPath + File.separator + ".." + File.separator + ".." + File.separator + ".." + HTMLReportContants.FILE_HTML_SUMMARY;

			logger.info("Final ReportPath: {}", reportPath);
			logger.info("Alternate ReportPath2: {}", reportPath2);
			
			StringBuilder sb = new StringBuilder();
			String moduleResults = "";
			int i = 1;
			for (ModuleResult moduleResult : ReportContants.getModuleResults()) {
				String modResult = HTMLReportContants.getPlaceHolderHTMLSummaryModuleRow();

				modResult = modResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_SNO, String.valueOf(i));
				modResult = modResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_MODULE, String.valueOf(moduleResult.getModule()));
				modResult = modResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_TOTAL_TC, String.valueOf(moduleResult.getTcTotalCount()));
				modResult = modResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_PASSED_TC, String.valueOf(moduleResult.getTcPassCount()));
				modResult = modResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_FAILED_TC, String.valueOf(moduleResult.getTcFailCount()));
				modResult = modResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_SKIPPED_TC, String.valueOf(moduleResult.getTcSkippedCount()));
				sb.append(moduleResults);
				sb.append(modResult);
				moduleResults = sb.toString();
				Thread.sleep(0);
				i++;
			}
			StringBuilder sbbr = new StringBuilder();
			String browserResults = "";
			i = 1;
			for (BrowserResult browserResult : ReportContants.getBrowserResults()) {
				String brResult = HTMLReportContants.getPlaceHolderHTMLSummaryBrowserRow();

				brResult = brResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_SNO, String.valueOf(i));
				brResult = brResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_MODULE, String.valueOf(browserResult.getBrowser()));
				brResult = brResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_TOTAL_TC, String.valueOf(browserResult.getTcTotalCount()));
				brResult = brResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_PASSED_TC, String.valueOf(browserResult.getTcPassCount()));
				brResult = brResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_FAILED_TC, String.valueOf(browserResult.getTcFailCount()));
				brResult = brResult.replaceAll(HTMLReportContants.SUMMARY_MODULE_BROWSER_SKIPPED_TC, String.valueOf(browserResult.getTcSkippedCount()));
				sbbr.append(browserResults);
				sbbr.append(brResult);
				browserResults = sbbr.toString();
				i++;
			}

			ReportCommon reportCommon = new ReportCommon();

			StringBuilder sbtcr = new StringBuilder();
			String testCaseResults = "";
			i = 1;
			for (TestCaseResult testCaseResult : ReportContants.getTestcaseResults()) {
				String tcResult = HTMLReportContants.getPlaceHolderHTMLSummaryTCRow();

				tcResult = tcResult.replaceAll(HTMLReportContants.SUMMARY_TC_SNO, String.valueOf(i));
				tcResult = tcResult.replaceAll(HTMLReportContants.SUMMARY_TC_TC_NAME, testCaseResult.getTestcaseName());
				tcResult = tcResult.replaceAll(HTMLReportContants.SUMMARY_TC_TC_DESC, testCaseResult.getTestcaseDescription());
				tcResult = tcResult.replaceAll(HTMLReportContants.SUMMARY_TC_MODULE, testCaseResult.getModule());
				tcResult = tcResult.replaceAll(HTMLReportContants.SUMMARY_TC_BROWSER, testCaseResult.getBrowser());
				tcResult = tcResult.replaceAll(HTMLReportContants.SUMMARY_TC_STATUS, testCaseResult.getTestcaseStatus());
				tcResult = tcResult.replaceAll(HTMLReportContants.SUMMARY_TC_START_TIME, reportCommon.convertLocalDateTimetoSQLDateTime(testCaseResult.getStartTime()));
				tcResult = tcResult.replaceAll(HTMLReportContants.SUMMARY_TC_END_TIME, reportCommon.convertLocalDateTimetoSQLDateTime(testCaseResult.getEndTime()));
				tcResult = tcResult.replaceAll(HTMLReportContants.SUMMARY_TC_DURATION, reportCommon.getTimeDifference(testCaseResult.getStartTime(), testCaseResult.getEndTime()));
				sbtcr.append(testCaseResults);
				sbtcr.append(tcResult);
				testCaseResults = sbtcr.toString();
				i++;
			}

			String hTMLResultsSummary = HTMLReportContants.getPlaceHolderHTMLSummary();
			hTMLResultsSummary = hTMLResultsSummary.replaceAll(HTMLReportContants.SUMMARY_DATA_ROW_MODULE, moduleResults);
			hTMLResultsSummary = hTMLResultsSummary.replaceAll(HTMLReportContants.SUMMARY_DATA_ROW_BROWSER, browserResults);
			hTMLResultsSummary = hTMLResultsSummary.replaceAll(HTMLReportContants.SUMMARY_DATA_ROW_TC, testCaseResults);

			writeDataToTextFile(reportPath, hTMLResultsSummary);
			writeDataToTextFile(reportPath2, hTMLResultsSummary);

			writeDataToTextFile(HTMLReportContants.getHtmlreportsdir() + File.separator + HTMLReportContants.SUMMARY_DASHBOARD, HTMLReportContants.getSummaryDataHTML());
			writeFileLineByLine(HTMLReportContants.getHtmlreportsdir() + File.separator + HTMLReportContants.SUMMARY_CSS, HTMLReportContants.getSummaryDataCSS());
			writeFileLineByLine(HTMLReportContants.getHtmlreportsdir() + File.separator + HTMLReportContants.SUMMARY_JS, HTMLReportContants.getHtmlSummaryDataJS());

		} catch (InterruptedException e) {
		    Thread.currentThread().interrupt(); // Restore the interrupted status
		    logger.error("Unable to create the HTML report due to interruption");
		} catch (Exception e) {
		    logger.error("Unable to create the HTML report: {}", e.getMessage());
		}
	}


	public String createTCHTML(String testCase,String moduleName, String browser)
	{
		String tCHTMLPath="";
		String tCHTMLIndex="";
		try
		{
		

		String tcDir=HTMLReportContants.getHtmlreportsdir() +testCase+"_"+ moduleName+ "_" + browser;
		tCHTMLIndex=tcDir+HTMLReportContants.FILE_HTML_INDEX;
		tCHTMLPath=tcDir+ HTMLReportContants.FILE_HTML_TEST_CASE;
		String tCHTMLData=HTMLReportContants.getPlaceHolderHTMLTestCase();
		
		String tSHTMLPath=tcDir+ HTMLReportContants.FILE_TEST_STEP;
		String tSHTMLData=HTMLReportContants.getPlaceHolderTestStepPre();

		String tcIndexPath=tcDir+ HTMLReportContants.FILE_HTML_INDEX;
		
		File dir = new File(tcDir);
        if (!dir.exists()) dir.mkdirs();

        tCHTMLData=tCHTMLData.replaceAll(HTMLReportContants.TC_NAME,testCase);
        tCHTMLData=tCHTMLData.replaceAll(HTMLReportContants.TC_MODULE,moduleName);
        tCHTMLData=tCHTMLData.replaceAll(HTMLReportContants.TC_BROWSER,browser);
        tCHTMLData=tCHTMLData.replaceAll(HTMLReportContants.TC_STATUS,"InProgress");
        tCHTMLData=tCHTMLData.replaceAll(HTMLReportContants.PAGE_REFRESH_TIME_IN_SECONDS_VAR,HTMLReportContants.getPageRefreshTimeInSeconds());
        tSHTMLData=tSHTMLData.replaceAll(HTMLReportContants.PAGE_REFRESH_TIME_IN_SECONDS_VAR,HTMLReportContants.getPageRefreshTimeInSeconds());
        
        
        writeDataToTextFile(tCHTMLPath, tCHTMLData);
        
        writeDataToTextFile(tSHTMLPath, tSHTMLData);
        
        writeDataToTextFile(tcIndexPath, HTMLReportContants.getPlaceHolderHTMLIndex());
                
        logger.info("<a href='file:///{}'>{}</a>", tSHTMLPath, testCase);

  		}
		catch(Exception e)
		{
		logger.error("{} Unable to initialize the HTML Test Case Reports",e.getMessage());
		}

		return tCHTMLIndex;
	
	}

	
	
	public String createTCHTMLLiveSummary()
	{


		String hTMLTCLiveReportFile=HTMLReportContants.getHtmlreportsdir() +HTMLReportContants.HTML_TC_LIVE_REPORT;

		try
		{
			String tCHTMLLiveReport=HTMLReportContants.getPlaceHolderHTMLTCLiveTemplate();
			writeDataToTextFile(hTMLTCLiveReportFile, tCHTMLLiveReport);

			logger.info("<a href='file:///{}'> Test Cases Details</a>", hTMLTCLiveReportFile);

		}
		catch(Exception e)
		{
			logger.error("{} Unable to initialize the HTML Live Report",e.getMessage());
		}

		return hTMLTCLiveReportFile;

	}
	public String createDashboardHTMLLiveSummary()
	{

		String hTMLTCLiveReportFile=HTMLReportContants.getHtmlreportsdir() +"/" + HTMLReportContants.SUMMARY_DASHBOARD;
		try
		{
			writeDataToTextFile(HTMLReportContants.getHtmlreportsdir() +"/" + HTMLReportContants.SUMMARY_DASHBOARD, HTMLReportContants.getSummaryDataHTML());
			writeFileLineByLine(HTMLReportContants.getHtmlreportsdir() +"/" + HTMLReportContants.SUMMARY_CSS, HTMLReportContants.getSummaryDataCSS());
			writeFileLineByLine(HTMLReportContants.getHtmlreportsdir() +"/" + HTMLReportContants.SUMMARY_JS, HTMLReportContants.getHtmlSummaryDataJS());

			logger.info("<a href='file:///{}'> Live Dashboard</a>", hTMLTCLiveReportFile);
		}
		catch(Exception e)
		{
			logger.error("{} Unable to initialize the HTML Live Report",e.getMessage());
		}

		return hTMLTCLiveReportFile;
	}

	public void createTCHTMLLive(String testCase,String moduleName, String browser, String status,String tcFilePath)
	{
		try
		{
		
	    StringBuilder sb = new StringBuilder();
		String hTMLTCLiveReportFile=HTMLReportContants.getHtmlreportsdir() +HTMLReportContants.HTML_TC_LIVE_REPORT;
		
		String tCHTMLLiveReport=HTMLReportContants.getPlaceHolderHTMLTCLiveTemplate();

		tCHTMLLiveReport=tCHTMLLiveReport.replaceAll(HTMLReportContants.PAGE_REFRESH_TIME_IN_SECONDS_VAR,HTMLReportContants.getPageRefreshTimeInSeconds());
		tCHTMLLiveReport=tCHTMLLiveReport.replaceAll(HTMLReportContants.PAGE_REFRESH_TIME_IN_SECONDS_VAR,HTMLReportContants.getPageRefreshTimeInSeconds());

		
		ReportContants.getHtmlTCLiveModels().add(new HTMLTCLiveModel().addDataHtmlTCLiveModel(testCase, moduleName, browser, status, tcFilePath));


		
		for(HTMLTCLiveModel htmltcLiveModel: ReportContants.getHtmlTCLiveModels())
		{
			String hTMLTCLiveRow=HTMLReportContants.getPlaceHolderHTMLTCLiveRow();
			hTMLTCLiveRow=hTMLTCLiveRow.replace(HTMLReportContants.TC_LIVE_TC_NAME, htmltcLiveModel.testCase);
			hTMLTCLiveRow=hTMLTCLiveRow.replace(HTMLReportContants.TC_LIVE_MODULE, htmltcLiveModel.module);
			hTMLTCLiveRow=hTMLTCLiveRow.replace(HTMLReportContants.TC_LIVE_BROWSER, htmltcLiveModel.browser);
			hTMLTCLiveRow=hTMLTCLiveRow.replace(HTMLReportContants.TC_LIVE_STATUS, htmltcLiveModel.status);
			htmltcLiveModel.filePath = (htmltcLiveModel.filePath.split("HTMLReports")[1]).substring(1);
			hTMLTCLiveRow=hTMLTCLiveRow.replace(HTMLReportContants.TC_LIVE_FILE_PATH, htmltcLiveModel.filePath);
			sb.append(tCHTMLLiveReport);
			sb.append(hTMLTCLiveRow);
			tCHTMLLiveReport = sb.toString();	
		}
		
        writeDataToTextFile(hTMLTCLiveReportFile, tCHTMLLiveReport);
        
		}
		catch(Exception e)
		{
		logger.error("{} Unable to initialize the HTML Test Case Report",e.getMessage());
		}
	
		
	
	}


	public void updateTCHTMLLive(String testCase,String moduleName, String browser, String status)
	{
		try
		{
		
	    StringBuilder sb = new StringBuilder();
		String hTMLTCLiveReportFile=HTMLReportContants.getHtmlreportsdir() +HTMLReportContants.HTML_TC_LIVE_REPORT;
		
		String tCHTMLLiveReport=HTMLReportContants.getPlaceHolderHTMLTCLiveTemplate();
		tCHTMLLiveReport=tCHTMLLiveReport.replaceAll(HTMLReportContants.PAGE_REFRESH_TIME_IN_SECONDS_VAR,HTMLReportContants.getPageRefreshTimeInSeconds());
		tCHTMLLiveReport=tCHTMLLiveReport.replaceAll(HTMLReportContants.PAGE_REFRESH_TIME_IN_SECONDS_VAR,HTMLReportContants.getPageRefreshTimeInSeconds());


		
		ReportContants.setHtmlTCLiveModels(ReportContants.getHtmlTCLiveModels().stream()
                .map(row -> {
                    if (row.getTestCase().equals(testCase) && row.getModule().equals(moduleName) && row.getBrowser().equals(browser)) {
                    	row.setStatus(status);
                    }
                    return row;
                })
                .collect(Collectors.toList()));

		
		for(HTMLTCLiveModel htmltcLiveModel: ReportContants.getHtmlTCLiveModels())
		{
			String hTMLTCLiveRow=HTMLReportContants.getPlaceHolderHTMLTCLiveRow();
			hTMLTCLiveRow=hTMLTCLiveRow.replace(HTMLReportContants.TC_LIVE_TC_NAME, htmltcLiveModel.testCase);
			hTMLTCLiveRow=hTMLTCLiveRow.replace(HTMLReportContants.TC_LIVE_MODULE, htmltcLiveModel.module);
			hTMLTCLiveRow=hTMLTCLiveRow.replace(HTMLReportContants.TC_LIVE_BROWSER, htmltcLiveModel.browser);
			hTMLTCLiveRow=hTMLTCLiveRow.replace(HTMLReportContants.TC_LIVE_STATUS, htmltcLiveModel.status);
			hTMLTCLiveRow=hTMLTCLiveRow.replace(HTMLReportContants.TC_LIVE_FILE_PATH, htmltcLiveModel.filePath);
			sb.append(tCHTMLLiveReport);
			sb.append(hTMLTCLiveRow);
			tCHTMLLiveReport = sb.toString();
		}
		
		
        writeDataToTextFile(hTMLTCLiveReportFile, tCHTMLLiveReport);
        
		}
		catch(Exception e)
		{
		logger.error("{} Unable to initialize the HTML Test Case Report",e.getMessage());
		}
	
	}

	

	public void addTestStepsLogs(TestStepDetails tsDetails)
	{

		try
		{
			String tCDir=HTMLReportContants.getHtmlreportsdir() +TestCaseParam.getTestCaseName() +"_"+ TestCaseParam.getModuleName()+ "_" + TestCaseParam.getBrowser();
			String sCHTMLPath=tCDir+ HTMLReportContants.FILE_SCREEN_SHOT;
			String sCHTMLData=HTMLReportContants.getPlaceHolderScreenShot();
			
			String tSHTMLPath=tCDir+ HTMLReportContants.FILE_TEST_STEP;
			String tSHTMLData=HTMLReportContants.getPlaceHolderCurrentTestStep();
		

			sCHTMLData=sCHTMLData.replaceAll(HTMLReportContants.SCR_STEP_NAME,tsDetails.stepName);
			sCHTMLData=sCHTMLData.replaceAll(HTMLReportContants.PAGE_REFRESH_TIME_IN_SECONDS_VAR,HTMLReportContants.getPageRefreshTimeInSeconds());

			
	        writeDataToTextFile(sCHTMLPath, sCHTMLData);
			
	        tSHTMLData=tSHTMLData.replaceAll(HTMLReportContants.TS_STEP_NAME,tsDetails.stepName);
	        tSHTMLData=tSHTMLData.replaceAll(HTMLReportContants.TS_STEP_DESC,tsDetails.stepDescription);
	        tSHTMLData=tSHTMLData.replaceAll(HTMLReportContants.TS_STATUS,tsDetails.testCaseStatus);
	        if(tsDetails.screenShotData.equals(""))
	        {
	        tSHTMLData=tSHTMLData.replaceAll(HTMLReportContants.getTsScreenshot(),"N/A");
	        }
	        else
	        {
	        	
	        	String scrLink=writeImageToReport(tsDetails.screenShotData,"Screenshot");
	        	
		        tSHTMLData=tSHTMLData.replaceAll(HTMLReportContants.getTsScreenshot(),scrLink);
		        String dele = "/";
		        String screenshotPath=tCDir+ dele + HTMLReportContants.FILE_SCREEN_SHOT_IMAGE;
		        copyFileUsingStream(tsDetails.screenShotData, screenshotPath);
		        
	        }
	        
	        ReportCommon reportCommon =new ReportCommon();
	        
	        tSHTMLData=tSHTMLData.replace(HTMLReportContants.TS_START_TIME,reportCommon.convertLocalDateTimetoSQLDateTime(tsDetails.startTime));
	        tSHTMLData=tSHTMLData.replace(HTMLReportContants.TS_END_TIME,reportCommon.convertLocalDateTimetoSQLDateTime(tsDetails.endTime));
	        
	        tSHTMLData=tSHTMLData.replaceAll(HTMLReportContants.TS_DURATION,reportCommon.getTimeDifference(tsDetails.startTime,tsDetails.endTime));
			
	        appendDataToTextFile(tSHTMLPath, tSHTMLData);
		}
		catch(Exception e)
		{		
		   logger.error("{} Unable to update the Extent Test Step",e.getMessage());
		}
	}

	public void addScreenShotDetails(TestStepDetails ts,ExtentTest node )
	{
		if (ts.screenShotData.equals(""))
		{
			node.log(ts.extentStatus, ts.stepName);
		}
		else
		{
			node.log(ts.extentStatus,writeImageToReport(ts.screenShotData, ts.stepName));
		}		
	}



	public String readDataFromTextFile(String filePath) {
	    StringBuilder data = new StringBuilder();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String strCurrentLine;
	        while ((strCurrentLine = reader.readLine()) != null) {
	            data.append(strCurrentLine);
	        }
	        return data.toString();
	    } catch (IOException e) {
	        logger.error("Error reading file: {}", e.getMessage());
	        return data.toString();
	    }
	}



	public List<String> readFileLineByLine(String filePath) {
	    ArrayList<String> data = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String strCurrentLine;
	        while ((strCurrentLine = reader.readLine()) != null) {
	            data.add(strCurrentLine);
	        }
	        return data;
	    } catch (IOException e) {
	        logger.error("Error reading file: {}", e.getMessage());
	        return data;
	    }
	}
		
	public boolean writeDataToTextFile(String filePath, String fileContent) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	        writer.write(fileContent);
	        return true; // File written successfully
	    } catch (IOException e) {
	        logger.error("Error writing data to file: {}", e.getMessage());
	        return false; // File write failed
	    }
	}



	
	public void writeFileLineByLine(String filePath,List<String> summaryDataCSS)
	{

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	        for (String line : summaryDataCSS) {
	            writer.write(line);
	            writer.newLine(); // To add a newline after each line
	        }
	    } catch (IOException e) {
	    	logger.info(Arrays.toString(e.getStackTrace()));
	    }
	}

	public boolean appendDataToTextFile(String filePath, String fileContent) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
	        writer.write(fileContent);
	        return true; // File appended successfully
	    } catch (IOException e) {
	        logger.error("Error appending data to file: {}", e.getMessage());
	        return false; // File append failed
	    }
	}



	public String writeImageToReport(String filePath, String fileName)
	{
	
		try
		{
			filePath = filePath.replace("\\", "/");
			filePath = "<a href = 'file:///" + filePath + "'>" + fileName + "</a>";
			return filePath;
		}

		catch (Exception e)
		{
			return filePath;
		}
	}


	
	public void copyFileUsingStream(String sourcePath, String destPath) throws IOException {
	    File source = new File(sourcePath);
	    File dest = new File(destPath);

	    try (InputStream is = new FileInputStream(source);
	         OutputStream os = new FileOutputStream(dest)) {
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } catch (Exception e) {
	        logger.error("Unable to copy the files: {}", e.getMessage());
	    }
	}
}