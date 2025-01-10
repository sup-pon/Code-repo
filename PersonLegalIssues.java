package cares.cwds.salesforce.pom.person;

import static java.lang.String.format;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.web.CommonOperations;
import cares.cwds.salesforce.utilities.web.CustomException;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.SalesforceCommon;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class PersonLegalIssues {
	private static final Logger logger =LoggerFactory.getLogger(PersonLegalIssues.class.getName());
	private static final String DATE_FORMAT= "M/d/yyyy";
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;


	String moduleName = ModuleConstants.PERSON;
	String screenName = ScreenConstants.PERSONLEGALISSUES;
	
	public PersonLegalIssues(){ }
	
	public PersonLegalIssues(WebDriver wDriver,TestCaseParam testCaseParam)
	{
		initializePage(wDriver,testCaseParam);
	}
	
	public void initializePage(WebDriver wDriver,TestCaseParam testCaseParam) 
	    {
	    	 driver = wDriver;
	         PageFactory.initElements(driver, this);
	         ReportCommon testStepLogDetails = new ReportCommon(); 
	         testStepLogDetails.logModuleAndScreenDetails(testCaseParam, moduleName, screenName);
	         genericLocators = new GenericLocators(wDriver);
	    }
	
	@FindBy(xpath = "//*[@title='Health Information ID']/..//lightning-formatted-text")
	WebElement legalIssuesID;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Date of Occurrence']/../../following-sibling::lightning-input//input)[1]")
	public WebElement dateOfOccurrence;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Date Source Observed Concern']/../../following-sibling::lightning-input//input)[1]")
	public WebElement dateSourceObservedConcern;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Date Reported to Worker']/../../following-sibling::lightning-input//input)[1]")
	public WebElement dateReportedToWorker;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Related Health Record']/..//input")
	public WebElement relatedHealthRecord;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Legal Issues Type']/..//input")
	public WebElement legalIssuesType;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Health Need']/..//input")
	public WebElement healthNeed;
	
	private String checkboxXpath = "//span[normalize-space()='%s']//label";
	
	public void enterPersonLegalIssuesDetails(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Enter PersonLegalIssues Details");
		action.setPageActionDescription("Enter PersonLegalIssues Details");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			

			String legalIssuesTypeTD = testCaseDataSd.get("LEGAL_ISSUES_TYPE").get(0);	
			String healthNeedTD = testCaseDataSd.get("HEALTH_NEED").get(0);	
			
			Webkeywords.instance().click(driver, legalIssuesType,legalIssuesTypeTD, testCaseParam,action);
			Webkeywords.instance().click(driver, genericLocators.link(driver, legalIssuesTypeTD,legalIssuesTypeTD),legalIssuesTypeTD, testCaseParam,action);
			Webkeywords.instance().setDateText(driver, dateOfOccurrence, CommonOperations.getDate(DATE_FORMAT, testCaseDataSd.get("DATE_OF_OCCURRENCE").get(0)), testCaseParam, action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Source Name",testCaseDataSd.get("SOURCE_NAME").get(0)),util.getRandom(testCaseDataSd.get("SOURCE_NAME").get(0)), testCaseParam, action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Source Relationship to Focus Child",testCaseDataSd.get("SOURCE_RELATIONSHIP_FOCUSCHILD").get(0)),util.getRandom(testCaseDataSd.get("SOURCE_RELATIONSHIP_FOCUSCHILD").get(0)), testCaseParam, action);
			Webkeywords.instance().setDateText(driver, dateSourceObservedConcern, CommonOperations.getDate(DATE_FORMAT, testCaseDataSd.get("DATE_SOURCE_OBSERVED_CONCERN").get(0)), testCaseParam, action);		
			Webkeywords.instance().setDateText(driver, dateReportedToWorker, CommonOperations.getDate(DATE_FORMAT, testCaseDataSd.get("DATE_REPORTED_TO_WORKER").get(0)), testCaseParam, action);		
			
			Webkeywords.instance().click(driver, healthNeed,healthNeedTD, testCaseParam,action);
			Webkeywords.instance().click(driver, genericLocators.link(driver, healthNeedTD,healthNeedTD),healthNeedTD, testCaseParam,action);
			
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("CONFIDENTIAL_CB").get(0),checkboxXpath, "Confidential"),testCaseDataSd.get("CONFIDENTIAL_CB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("INCLUDE_AS_HEP_ALERT_CB").get(0),checkboxXpath, "Include as HEP alert?"),testCaseDataSd.get("INCLUDE_AS_HEP_ALERT_CB").get(0), testCaseParam,action);
			
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Summary of Current Health Condition",testCaseDataSd.get("SUMMARY_CURRENT_HEALTH_CONDITION").get(0)), util.getRandom(testCaseDataSd.get("SUMMARY_CURRENT_HEALTH_CONDITION").get(0)), testCaseParam, action);

			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Additional Information",testCaseDataSd.get("ADDITIONAL_INFORMATION").get(0)), util.getRandom(testCaseDataSd.get("ADDITIONAL_INFORMATION").get(0)), testCaseParam, action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "HEP Alert Description",testCaseDataSd.get("HEP_ALERT_DESCRIPTION").get(0)), util.getRandom(testCaseDataSd.get("HEP_ALERT_DESCRIPTION").get(0)), testCaseParam, action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0), testCaseParam,action);
			SalesforceCommon.verifyToastMessage(driver,"Data saved successfully.",testCaseParam, action);
			
			Webkeywords.instance().waitElementToBeVisible(driver, legalIssuesID);
			SalesforceConstants.setConstantValue("Person LegalIssuesID", legalIssuesID.getText());	
        }
		catch (Exception e)
		{
			logger.error("Failed== {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			throw new CustomException("Error Occured", e);
		}
	}
	
	public WebElement getElementBasedOnFlag(String flag, String checkboxXpath, String name) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(checkboxXpath,name)));
		}
		else
			return null;
	}
}
