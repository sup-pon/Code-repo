package cares.cwds.salesforce.pom.screening;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

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
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class ScreeningContactLog {
	private static final Logger logger =LoggerFactory.getLogger(ScreeningContactLog.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;

	String moduleName = ModuleConstants.SCREENING;
	String screenName = ScreenConstants.SCREENINGCONTACTLOG;
	
	private static final String CONTACT_LOGS_TAB = "CONTACT_LOGS_TAB";
	private static final String CONTACT_LOGS = "Contact Logs";
	private static final String CONTACT_PURPOSE = "Contact Purpose";
	private static final String METHOD = "Method";
	private static final String LOCATION = "Location";

	
	public ScreeningContactLog(){ }
	
	public ScreeningContactLog(WebDriver wDriver,TestCaseParam testCaseParam)
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
	

	@FindBy(how = How.XPATH, using = "(//label[text()='Contact Start Date/Time']/../../following-sibling::lightning-input//input)[1]")
	public WebElement contactStartDateTime;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Contact End Date/Time']/../../following-sibling::lightning-input//input)[1]")

	public WebElement contactEndDateTime;
	
	@FindBy(how = How.XPATH, using = "//*[@apiname='New_Contact_Note']//button[text()='New']")
	public WebElement contactLogNewButton;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Status']/../../following-sibling::lightning-combobox//button")
	public WebElement contactStatus;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Status']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> contactStatusList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Purpose']/../../following-sibling::lightning-combobox//button")
	public WebElement contactPurpose;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Purpose']/../../following::lightning-combobox//span//span")
	public List<WebElement> contactPurposeList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='On Behalf of Child(ren)']/..//input")
	public WebElement onBehalfOfChild;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Participant']/..//input")
	public WebElement participantTD;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Method']/../../following-sibling::lightning-combobox//button")
	public WebElement methoddd;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Method']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> methodList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Location']/../../following-sibling::lightning-combobox//button")
	public WebElement locationdd;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Location']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> locationList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Staff Person']/..//input")
	public WebElement staffPerson;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Other Staff Present']/..//input")
	public WebElement otherStaffPresent;
	
	@FindBy(xpath = "//*[@title='Contact ID']/..//lightning-formatted-text")
	WebElement scrContactLogID;
	
	@FindBy(how = How.XPATH, using =  "//span[text()='Location']//parent::div[text()='Complete this field.']")
	public WebElement mandatoryFieldLocation;
	
	@FindBy(how = How.XPATH, using =  "(//span[@title='Documents']//parent::a)[2]")
	public WebElement documentSubTab;
	
	@FindBy(how = How.XPATH, using = "//table[@aria-label='Contact Log']//tbody//tr/th//descendant::slot//span//slot")
	public WebElement contactRecordId;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Topic']/../child::div//input")
	public WebElement contactTopic;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Topic']/../child::div//ul//li//span//span//span[1]")
	public List<WebElement> contactTopicList;
	
	@FindBy(how = How.XPATH, using = "(//label[@part='input-radio']//span)[3]")
	public WebElement scheduledNextInPersonContactVisit;
	
	@FindBy(how = How.XPATH, using = "//*[text()='On Behalf of Child(ren) and Participants']")
	public WebElement OnBehalfChildAndParticipantHeader;
	
	public void navigateToScreeningContactLogs(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Navigate to Screening Contact Logs");
		action.setPageActionDescription("Navigate to Screening Contact Logs");
		try {

			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			String contactLogsTabTD = testCaseDataSd.get(CONTACT_LOGS_TAB).get(0);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().waitElementToBeVisible(driver,  genericLocators.button(driver, CONTACT_LOGS,contactLogsTabTD));
			Webkeywords.instance().click(driver, genericLocators.link(driver, CONTACT_LOGS,contactLogsTabTD),contactLogsTabTD, testCaseParam,action);
		}
		catch (Exception e)
		{
			logger.error("Failed == {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			
		}
	}
	

	public void addScreeningContactLog(TestCaseParam testCaseParam,String scriptIteration, String pomIteration) throws CustomException{
		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Add Screening Contact");
		action.setPageActionDescription("Add Screening Contact");
		
		try {

			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			String onBehalfOfChildTD = testCaseDataSd.get("ON_BEHALF_OF_CHILD").get(0);
			String onBehalfOfchild = SalesforceConstants.getConstantValue(onBehalfOfChildTD);
			String participant = SalesforceConstants.getConstantValue(testCaseDataSd.get("PARTICIPANT").get(0));
			Webkeywords.instance().waitElementToBeVisible(driver, contactLogNewButton);
			Webkeywords.instance().click(driver,contactLogNewButton,testCaseDataSd.get("NEW_BTN").get(0), testCaseParam, action);
			
			Webkeywords.instance().waitElementToBeVisible(driver, onBehalfOfChild);
			Webkeywords.instance().waitElementClickable(driver, onBehalfOfChild);
			Webkeywords.instance().click(driver, participantTD,"", testCaseParam,action);
			Webkeywords.instance().click(driver, genericLocators.link(driver,participant ,testCaseDataSd.get("PARTICIPANT").get(0)),"", testCaseParam,action);
			
			Webkeywords.instance().click(driver, onBehalfOfChild,"", testCaseParam,action);
			Webkeywords.instance().click(driver, genericLocators.link(driver,onBehalfOfchild ,onBehalfOfChildTD),onBehalfOfChildTD, testCaseParam,action);

			Webkeywords.instance().selectDropdownValueByElement(driver,contactStatus,contactStatusList,testCaseDataSd.get("CONTACT_STATUS").get(0),"Contact Status",testCaseParam,action);
			Webkeywords.instance().selectDropdownValueByElement(driver,contactPurpose,contactPurposeList,testCaseDataSd.get("CONTACT_PURPOSE").get(0),CONTACT_PURPOSE,testCaseParam,action);
			
			Webkeywords.instance().scrollIntoViewElement(driver,participantTD );
			Webkeywords.instance().selectDropdownValueByElement(driver,contactTopic,contactTopicList,testCaseDataSd.get("CONTACT_TOPIC").get(0),"Contact Topic",testCaseParam,action);
			
			Webkeywords.instance().setDateText(driver, contactStartDateTime, CommonOperations.getDate("M/d/yyyy",testCaseDataSd.get("CONTACT_START_DATETIME").get(0)), testCaseParam, action);
			
			
			Webkeywords.instance().selectDropdownValueByElement(driver, methoddd, methodList,testCaseDataSd.get("METHOD").get(0),METHOD,testCaseParam,action);
			Webkeywords.instance().selectDropdownValueByElement(driver, locationdd, locationList, testCaseDataSd.get("LOCATION").get(0),LOCATION,testCaseParam,action);
			
			Webkeywords.instance().click(driver, scheduledNextInPersonContactVisit, testCaseDataSd.get("SCHEDULED_NEXT_IN_PERSON_CONTACT_VISIT").get(0), testCaseParam, action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Notes",testCaseDataSd.get("NOTES").get(0)), util.getRandom(testCaseDataSd.get("NOTES").get(0)), testCaseParam, action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Narrative",testCaseDataSd.get("NARRATIVE").get(0)), util.getRandom(testCaseDataSd.get("NARRATIVE").get(0)), testCaseParam, action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)), testCaseDataSd.get("SAVE_BTN").get(0), testCaseParam, action);
			Webkeywords.instance().pause();
			Webkeywords.instance().waitElementToBeVisible(driver, scrContactLogID);

			SalesforceConstants.setConstantValue("SCR CONTACTID", scrContactLogID.getText());
			Webkeywords.instance().waitElementToBeVisible(driver,OnBehalfChildAndParticipantHeader );
			}
		catch (Exception e)
		{
			logger.error("Failed == {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
		}
    }
	
	public void validateContactLogFields(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException {
		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("verifying Screening Contact logs Fields");
		action.setPageActionDescription("verifying Screening Contact logs Fields");
		
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				
				Webkeywords.instance().verifyElementDisplayed(driver, contactStatus, testCaseDataSd.get("CONTACTSTATUS_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, contactPurpose, testCaseDataSd.get("CONTACTPURPOSE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, contactStartDateTime, testCaseDataSd.get("CONTACTSTARTDATE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, contactEndDateTime, testCaseDataSd.get("CONTACTENDDATE_VERIFY").get(0), testCaseParam, action);

				Webkeywords.instance().verifyElementDisplayed(driver, onBehalfOfChild,testCaseDataSd.get("ONBEHALFOFCHILD_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, participantTD, testCaseDataSd.get("PARTICIPANT_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, staffPerson, testCaseDataSd.get("STAFFPERSON_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, otherStaffPresent, testCaseDataSd.get("OTHERSTAFFPRESENT_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, methoddd, testCaseDataSd.get("METHOD_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, locationdd, testCaseDataSd.get("LOCATION_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textarea(driver, "Narrative",testCaseDataSd.get("NARRATIVE_VERIFY").get(0)), testCaseDataSd.get("NARRATIVE_VERIFY").get(0), testCaseParam, action);
				
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "System Information",testCaseDataSd.get("SYSTEMINFO_VERIFY").get(0)), testCaseDataSd.get("SYSTEMINFO_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Created By",testCaseDataSd.get("CREATEDBY_VERIFY").get(0)), testCaseDataSd.get("CREATEDBY_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Created Date",testCaseDataSd.get("CREATEDDATE_VERIFY").get(0)), testCaseDataSd.get("CREATEDDATE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Modified Date",testCaseDataSd.get("MODIFIEDDATE_VERIFY").get(0)), testCaseDataSd.get("MODIFIEDDATE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Last Modified By",testCaseDataSd.get("LASTMODIFIEDBY_VERIFY").get(0)), testCaseDataSd.get("LASTMODIFIEDBY_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, documentSubTab , testCaseDataSd.get("DOCUMENTS_TAB_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, mandatoryFieldLocation , testCaseDataSd.get("LOCATION_FIELD_ERROR").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.button(driver, "Cancel",testCaseDataSd.get("CANCEL_VERIFY").get(0)), testCaseDataSd.get("CANCEL_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_VERIFY").get(0)), testCaseDataSd.get("SAVE_VERIFY").get(0), testCaseParam, action);
		
		} catch (Exception e) {
				logger.error("Failed == {} ", action.getPageActionDescription());
				exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			}
	}
	
	public void verfiyContactLogRecord(TestCaseParam testCaseParam) throws CustomException  {
		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Verify Contact Log Record");
		action.setPageActionDescription("Verify Contact Log Record");
		try {

		    String actuaContactLogCreated = SalesforceConstants.getConstantValue("SCR_CONTACTID");
			String expectedContactLogRecordID = contactRecordId.getText();
			
			Assert.assertEquals(actuaContactLogCreated, expectedContactLogRecordID);				
		}
		catch (Exception e)
		{
			logger.error("Failed == {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			
		}
	}
	
}