package cares.cwds.salesforce.pom.screening;

import static java.lang.String.format;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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
import cares.cwds.salesforce.utilities.web.SalesforceCommon;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class ScreeningTribalInquiry {
	private static final Logger logger =LoggerFactory.getLogger(ScreeningTribalInquiry.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;

	String moduleName = ModuleConstants.SCREENING;
	String screenName = ScreenConstants.SCRTRIBALINQUIRYANDCOLLABORATION;
	
	private static final String CONTACT_PURPOSE= "Contact Purpose";
	private static final String CONTACT_METHOD= "Contact Method";
	private static final String CONTACT_STATUS= "Contact Status";
	private static final String CONTACT_LOGS= "Contact Logs";
	private static final String ON_BEHALF_OF_CHILD= "On Behalf of Child";
	private static final String PARTICIPANT_TYPE= "Participant Type";
	private static final String STAFF_PERSON= "Staff Person";
	private static final String OTHER_STAFF_PRESENT= "Other Staff Present";
	private static final String LOCATION= "Location";
	private static final String INITIAL_ICWA_INQUIRY= "Initial ICWA Inquiry";
	private static final String NARRATIVE= "Narrative";
	private static final String SAVE= "Save";
	
	
	
	public ScreeningTribalInquiry(){ }
	
	public ScreeningTribalInquiry(WebDriver wDriver,TestCaseParam testCaseParam)
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
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Contact End Date/Time']/../../following-sibling::lightning-input//input)[2]")
	public WebElement contactEndDateTime;
	
	@FindBy(how = How.XPATH, using = "(//label[.='If yes,then which Tribe(s)?'])[1]/..//input")
	public WebElement whichTribeDD1;

	@FindBy(how = How.XPATH, using = "(//label[.='If yes,then which Tribe(s)?'])[2]/..//input")
	public WebElement whichTribeDD2;
	
	@FindBy(how = How.XPATH, using = "(//label[.='If yes, which Tribe(s)?'])[1]/..//input")
	public WebElement whichTribeDD3;
	
	@FindBy(how = How.XPATH, using = "//li[@data-target-selection-name='sfdc:CustomButton.Contact_Log__c.New_Tribal_Inquiry']//button")
	public WebElement tribalInquiryNew;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Status']/../../following-sibling::lightning-combobox//button")
	public WebElement contactStatus;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Status']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> contactStatusList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Purpose']/../../following-sibling::lightning-combobox//button")
	public WebElement contactPurpose;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Purpose']/../../following::lightning-combobox//span//span")
	public List<WebElement> contactPurposeList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='On Behalf of Child']/..//input")
	public WebElement onBehalfOfChild;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Participant']/..//input")
	public WebElement participant;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Method']/../../following-sibling::lightning-combobox//button")
	public WebElement contactMethod;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Method']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> contactMethodList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Location']/../../following-sibling::lightning-combobox//button")
	public WebElement locationdd;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Location']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> locationList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Participant Type']/../../following-sibling::lightning-combobox//button")
	public WebElement participantType;
	
	@FindBy(how = How.XPATH, using = " //label[text()='Participant Type']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> participantTypeList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Initial ICWA Inquiry']/../../following-sibling::lightning-combobox//button")
	public WebElement icwaInquiry;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Initial ICWA Inquiry']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> icwaInquiryList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Staff Person(s)']/..//input")
	public WebElement staffPerson;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Staff Person(s)']/..//child::div//following-sibling::div)[1]//ul//li//span[2]//child::span[1]")
	public List<WebElement> staffPersonList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Other Staff Present']/..//input")
	public WebElement otherStaffPresent;
	
	@FindBy(how = How.XPATH, using = "//label[text()='On Behalf of Child']/..//input")
	public WebElement onBehalfofChildClick;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Method']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> methodList;
	
	@FindBy(xpath = "//*[@title='Contact ID']/..//lightning-formatted-text")
	WebElement scrTribalContactLogID;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Initial ICWA Inquiry']/../lightning-helptext//span[text()='Help']")
	public WebElement initialICWAInquiry;
	
	@FindBy(how = How.XPATH, using = "(//lightning-primitive-bubble[@role='tooltip']//div//div)[1]")
    public WebElement initialICWAInquiryText;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Contact ID']//ancestor::th//parent::tr/..//following::tbody//tr//th//descendant::slot//span//slot")
	public WebElement expectedTribalRecord;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Narrative']/../../following::lightning-input-rich-text//div//div//div)[1]//div[3]")
	public WebElement narrative_path;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Tribal Inquiry & Collaboration']")
	public WebElement TribalInquiryCollaboration;
	
	String columnHeader = "//table[@aria-label='%s']/thead/tr/th[@*='%s']";
	
	@FindBy(how = How.XPATH , using = "(//button[@title='Save'])[2]")
	public WebElement saveBtn;

	String participantSelect= "(//span[normalize-space()='%s'])[3]";
	
	public void navigateToScreeningContactLogs(TestCaseParam testCaseParam,String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Navigate to Screening Contact Logs");
		action.setPageActionDescription("Navigate to Screening Contact Logs");
		try {

			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			String contactLogsTabTD = testCaseDataSd.get("CONTACT_LOGS_TAB").get(0);
			Webkeywords.instance().pause();
			Webkeywords.instance().click(driver, genericLocators.button(driver, CONTACT_LOGS,contactLogsTabTD),contactLogsTabTD, testCaseParam,action);
			Webkeywords.instance().pause();
		}
		catch (Exception e)
		{
			logger.error("Failed == {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			
		}
	}
	
	public void addTribalInquiryAndCollaborationInfo(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Process Screening Tribal Inquiry And Collaboration");
		action.setPageActionDescription("Process Screening Tribal Inquiry And Collaboration");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName,TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			String onBehalfOfChildTD = testCaseDataSd.get("ON_BEHALF_OF_CHILD").get(0);
			String staffPersonTD = testCaseDataSd.get("STAFF_PERSON").get(0);
			String otherStaffPresentTD = testCaseDataSd.get("OTHER_STAFF_PRESENT").get(0);	


			String onBehalfChild = SalesforceConstants.getConstantValue( testCaseDataSd.get("ON_BEHALF_OF_CHILD").get(0));

			String participantTD = testCaseDataSd.get("PARTICIPANT").get(0);
			String Participant = SalesforceConstants.getConstantValue( testCaseDataSd.get("PARTICIPANT").get(0));
			
			Webkeywords.instance().scrollUpPageToTheTop(driver);

			Webkeywords.instance().waitElementToBeVisible(driver, tribalInquiryNew);
			Webkeywords.instance().click(driver, tribalInquiryNew, testCaseDataSd.get("TRIBAL_NEW_BTN").get(0), testCaseParam,action);
			Webkeywords.instance().pause();
			Webkeywords.instance().waitElementToBeVisible(driver, contactStartDateTime);
			Webkeywords.instance().waitElementClickable(driver, contactStartDateTime);
			Webkeywords.instance().setDateText(driver, contactStartDateTime, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("CONTACT_START_DATE_TIME").get(0)), testCaseParam, action);
			Webkeywords.instance().selectDropdownValueByElement(driver,contactPurpose,contactPurposeList,testCaseDataSd.get("CONTACT_PURPOSE").get(0),CONTACT_PURPOSE,testCaseParam,action);
			Webkeywords.instance().selectDropdownValueByElement(driver, contactMethod, contactMethodList,testCaseDataSd.get("CONTACT_METHOD").get(0),CONTACT_METHOD,testCaseParam,action);
			Webkeywords.instance().selectDropdownValueByElement(driver,contactStatus,contactStatusList,testCaseDataSd.get("CONTACT_STATUS").get(0),CONTACT_STATUS,testCaseParam,action);
			
			Webkeywords.instance().click(driver, onBehalfOfChild,onBehalfOfChildTD, testCaseParam,action);
			Webkeywords.instance().jsClick(driver, genericLocators.link(driver, onBehalfChild ,onBehalfOfChildTD),onBehalfOfChildTD, testCaseParam,action);
			
			Webkeywords.instance().selectDropdownValueByElement(driver,participantType,participantTypeList,testCaseDataSd.get("PARTICIPANT_TYPE").get(0),PARTICIPANT_TYPE,testCaseParam,action);	
			Webkeywords.instance().scrollTillElement(driver, participantType);
			
			if(Participant!=null)
			{
			Webkeywords.instance().click(driver, participant,participantTD, testCaseParam,action);			
			Webkeywords.instance().click(driver,driver.findElement(By.xpath(format(participantSelect,Participant))),participantTD, testCaseParam,action);
			}
			
			Webkeywords.instance().click(driver, staffPerson,staffPersonTD, testCaseParam,action);
			Webkeywords.instance().click(driver, genericLocators.link(driver, staffPersonTD,staffPersonTD),staffPersonTD, testCaseParam,action);
			
			Webkeywords.instance().click(driver, otherStaffPresent,otherStaffPresentTD, testCaseParam,action);
			Webkeywords.instance().click(driver, genericLocators.link(driver, otherStaffPresentTD,otherStaffPresentTD),otherStaffPresentTD, testCaseParam,action);
			
			Webkeywords.instance().selectDropdownValueByElement(driver, locationdd, locationList, testCaseDataSd.get("LOCATION").get(0),LOCATION,testCaseParam,action);
				
			Webkeywords.instance().scrollIntoViewElement(driver, contactStatus);
			Webkeywords.instance().mouseHover(driver, initialICWAInquiry, testCaseParam, action);

			
			Webkeywords.instance().selectDropdownValueByElement(driver, icwaInquiry, icwaInquiryList, testCaseDataSd.get("INITIAL_ICWA_INQUIRY").get(0),INITIAL_ICWA_INQUIRY,testCaseParam,action);		
			Webkeywords.instance().scrollIntoViewElement(driver, narrative_path);
					
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, NARRATIVE ,testCaseDataSd.get("NARRATIVE").get(0)), testCaseDataSd.get("NARRATIVE").get(0), testCaseParam,action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, SAVE,testCaseDataSd.get("SAVE_BTN").get(0)),  testCaseDataSd.get("SAVE_BTN").get(0), testCaseParam, action);
			Webkeywords.instance().pause();

			Webkeywords.instance().waitElementToBeVisible(driver, scrTribalContactLogID);
			SalesforceConstants.setConstantValue("SCR TRIBAL CONTACTID", scrTribalContactLogID.getText());	
			
			Webkeywords.instance().waitElementToBeVisible(driver, TribalInquiryCollaboration);
		
		}
		catch (Exception e)
		{
			logger.error("Failed== {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			
		}
	}
	
	public void addIndianChildInquiriesInfo(TestCaseParam testCaseParam,String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Process Screening Indian Child Inquiries");
		action.setPageActionDescription("Process Screening Indian Child Inquiries");
		try {
			
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			 
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("MEMBEROFTRIBEALASKANATIVEVILLAGE").get(0),"Member of Tribe/Alaska Native Village?",testCaseParam,action);
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("IFYESTHENWHICHTRIBE1").get(0),"If yes,then which Tribe(s)?",testCaseParam,action);
			
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("ISPARENTMEMBEROFTRIBE").get(0),"Is parent member of Tribe(s)?",testCaseParam,action);
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("IFYESTHENWHICHTRIBE2").get(0),"If yes,then which Tribe(s)?",testCaseParam,action);
			
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("RECEIVESSERVICEBENEFITFROMTRIBE").get(0),"Receives service/benefit from Tribe(s)?",testCaseParam,action);
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("IFYESTHENWHICHTRIBE3").get(0),"If yes, which Tribe(s)?",testCaseParam,action);
			
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("RESIDESONRESERVATIONTRIBALLAND").get(0),"Resides on Reservation/Tribal land?",testCaseParam,action);
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("ANYFAMILYIDENTIFIEDANCESTRYHERITAGE").get(0),"Any family identified ancestry/heritage?",testCaseParam,action);
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("RECEIVESFEDERALSERVICETRIBALBENEFIT").get(0),"Receives Federal service/Tribal benefit?",testCaseParam,action);
			
		}
		catch (Exception e)
		{
			logger.error("Failed== {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			
		}
	}
	
	public void verifyingTribalInquiryFields(TestCaseParam testCaseParam,String scriptIteration, String pomIteration) throws CustomException {
		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("verifying Screening TribalInquiry Fields");
		action.setPageActionDescription("verifying Screening TribalInquiry Fields");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Tribal Inquiry & Collaboration",testCaseDataSd.get("TRIBALINQUIRY_COLLABORATION_VERIFY").get(0)), testCaseDataSd.get("TRIBALINQUIRY_COLLABORATION_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Contact Start Date/ Time",testCaseDataSd.get("CONTACTSTARTDATE_VERIFY").get(0)), testCaseDataSd.get("CONTACTSTARTDATE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Contact End Date/ Time",testCaseDataSd.get("CONTACTENDDATE_VERIFY").get(0)), testCaseDataSd.get("CONTACTENDDATE_VERIFY").get(0), testCaseParam, action);
				
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, CONTACT_PURPOSE,testCaseDataSd.get("CONTACTPURPOSE_VERIFY").get(0)), testCaseDataSd.get("CONTACTPURPOSE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, CONTACT_METHOD,testCaseDataSd.get("CONTACTMETHOD_VERIFY").get(0)), testCaseDataSd.get("CONTACTMETHOD_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, ON_BEHALF_OF_CHILD,testCaseDataSd.get("ONBEHALFOFCHILD_VERIFY").get(0)), testCaseDataSd.get("ONBEHALFOFCHILD_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, CONTACT_STATUS,testCaseDataSd.get("CONTACTSTATUS_VERIFY").get(0)), testCaseDataSd.get("CONTACTSTATUS_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Status Narrative", testCaseDataSd.get("STATUSNARRATIVE_VERIFY").get(0)), testCaseDataSd.get("STATUSNARRATIVE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, PARTICIPANT_TYPE,testCaseDataSd.get("PARTICIPANT_TYPE_VERIFY").get(0)), testCaseDataSd.get("PARTICIPANT_TYPE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Participant",testCaseDataSd.get("PARTICIPANT_VERIFY").get(0)), testCaseDataSd.get("PARTICIPANT_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, OTHER_STAFF_PRESENT,testCaseDataSd.get("OTHERSTAFFPERSON_VERIFY").get(0)), testCaseDataSd.get("OTHERSTAFFPERSON_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, STAFF_PERSON,testCaseDataSd.get("STAFFPERSON_VERIFY").get(0)), testCaseDataSd.get("STAFFPERSON_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Other Location",testCaseDataSd.get("OTHERLOCATION_VERIFY").get(0)), testCaseDataSd.get("OTHERLOCATION_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, LOCATION,testCaseDataSd.get("LOCATION_VERIFY").get(0)), testCaseDataSd.get("LOCATION_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Reason to Know Rationale", testCaseDataSd.get("REASONTOKNOWRATIONALE_VERIFY").get(0)), testCaseDataSd.get("REASONTOKNOWRATIONALE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, INITIAL_ICWA_INQUIRY,testCaseDataSd.get("INITIALICWAINQ_VERIFY").get(0)), testCaseDataSd.get("INITIALICWAINQ_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textarea(driver, NARRATIVE,testCaseDataSd.get("NARRATIVE_VERIFY").get(0)), testCaseDataSd.get("NARRATIVE_VERIFY").get(0), testCaseParam, action);

				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Indian Child Inquiries", testCaseDataSd.get("INDIANCHILDINQ_VERIFY").get(0)), testCaseDataSd.get("INDIANCHILDINQ_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Member of Tribe/Alaska Native Village?", testCaseDataSd.get("MEMBEROFTRIBAL_VERIFY").get(0)), testCaseDataSd.get("MEMBEROFTRIBAL_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "If yes,then which Tribe(s)?",testCaseDataSd.get("IFTESREASON1_VERIFY").get(0)), testCaseDataSd.get("IFTESREASON1_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Is parent member of Tribe(s)?",testCaseDataSd.get("ISPARENTMEMBER_VERIFY").get(0)), testCaseDataSd.get("ISPARENTMEMBER_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Receives service/benefit from Tribe(s)?",testCaseDataSd.get("RECEIVESERVICE_VERIFY").get(0)), testCaseDataSd.get("RECEIVESERVICE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "If yes, which Tribe(s)?",testCaseDataSd.get("IFTESREASON2_VERIFY").get(0)), testCaseDataSd.get("IFTESREASON2_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Resides on Reservation/Tribal land?",testCaseDataSd.get("RESIDEONRESERVATION_VERIFY").get(0)), testCaseDataSd.get("RESIDEONRESERVATION_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Any family identified ancestry/heritage?", testCaseDataSd.get("ANYFAMILYIDENTIFIED_VERIFY").get(0)), testCaseDataSd.get("ANYFAMILYIDENTIFIED_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Receives Federal service/Tribal benefit?",testCaseDataSd.get("RECEIVESFEDERALSERVICE_VERIFY").get(0)), testCaseDataSd.get("RECEIVESFEDERALSERVICE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.button(driver, "Cancel",testCaseDataSd.get("CANCEL_VERIFY").get(0)), testCaseDataSd.get("CANCEL_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_VERIFY").get(0)), testCaseDataSd.get("SAVE_VERIFY").get(0), testCaseParam, action);
			 
		} catch (Exception e) {
				logger.error("Failed == {} ", action.getPageActionDescription());
				exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			}
	}
	
	public void verifyTribalHeaderColumnsInTable(String tableName,TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException{
		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Verify tribal header columns in Table");
		action.setPageActionDescription("Verify tribal header columns in Table");
		try {	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERCONTACTID").get(0),columnHeader,tableName,"Contact ID"), testCaseDataSd.get("HEADERCONTACTID").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERCONTACTSTARTDATETIME").get(0),columnHeader,tableName,"Contact Start Date Time"), testCaseDataSd.get("HEADERCONTACTSTARTDATETIME").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERCONTACTPURPOSE").get(0),columnHeader,tableName,"Contact Purpose"), testCaseDataSd.get("HEADERCONTACTPURPOSE").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERCONTACTSTATUS").get(0),columnHeader,tableName,"Contact Status"), testCaseDataSd.get("HEADERCONTACTSTATUS").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERINITIALICWAINQUIRY").get(0),columnHeader,tableName,"Initial ICWA Inquiry"), testCaseDataSd.get("HEADERINITIALICWAINQUIRY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERONBEHALFOFCHILD").get(0),columnHeader,tableName,"On Behalf of Child (Screening Person)"), testCaseDataSd.get("HEADERONBEHALFOFCHILD").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERPARTICIPANT").get(0),columnHeader,tableName,"Participant (Screening Person)"), testCaseDataSd.get("HEADERPARTICIPANT").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERSTAFFPERSON").get(0),columnHeader,tableName,"Staff Person(s)"), testCaseDataSd.get("HEADERSTAFFPERSON").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADEROTHERSTAFFPRESENT").get(0),columnHeader,tableName,"Other Staff Present"), testCaseDataSd.get("HEADEROTHERSTAFFPRESENT").get(0), testCaseParam, action);
			
		}
		catch (Exception e){
			logger.error("Failed == {} ", action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
		}
	}
	
	public WebElement getElementBasedOnFlag(String flag, String columnHeaderXpath, String tableName, String columnName) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(columnHeaderXpath,tableName,columnName)));
		}
		else
			return null;
	}
	
	public void verfiyTribalInquiryRecord(TestCaseParam testCaseParam) throws CustomException  {
		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Verify Tribal Inquiry Record");
		action.setPageActionDescription("Verify Tribal Inquiry Record");
		try {

			String actualtribalRecordcreated = SalesforceConstants.getConstantValue("SCR TRIBAL CONTACTID");
			String expectedTribalRecordID = expectedTribalRecord.getText();

			Assert.assertEquals(actualtribalRecordcreated, expectedTribalRecordID);				
		}
		catch (Exception e)
		{
			logger.error("Failed == {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			
		}
	}
}