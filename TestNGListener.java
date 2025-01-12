package cares.cwds.salesforce.utilities.testng;

import java.time.LocalDateTime;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentreport.ExtentReport;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;


/**
 * @author hakkanolla
 * Implementing TestNG listeners for report logging
 */

public class TestNGListener implements ITestListener {

	TestNGCommon testngCommon = new TestNGCommon();
	
	
	/**
	 * This method is invoked before each time test runs
	 */
	@Override
	public void onTestStart(ITestResult result) {
		WebDriver driver = testngCommon.initializeDriver();
		TestRunSettings.setDriver(driver);
		try {
			TestNGCommon TestngCommon = new TestNGCommon();
			TestngCommon .initializeTestCase(TestngCommon.getTestCaseParam());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Method is invoked each time test succeeds
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		teardown();
	}

	/**
	 * Method is invoked if a test fails
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		TestCaseParam testCaseParam = new TestCaseParam();
		ReportCommon reportDetails = new ReportCommon();
		LocalDateTime startTime= LocalDateTime.now();
		
		WebDriver driver = TestRunSettings.getDriver();
		reportDetails.logExceptionDetails(driver, testCaseParam, result.getName(),result.getMethod().toString(), startTime,result.getThrowable());
		teardown();
	}
	
	
	private void teardown() {
		ExtentReport extentReport = new ExtentReport();
		testngCommon.endTestCase(testngCommon.getTestCaseParam());
		testngCommon.quitDriver(TestRunSettings.getDriver());
		extentReport.endReport();
	}

}
