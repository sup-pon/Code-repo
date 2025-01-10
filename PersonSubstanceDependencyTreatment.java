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

public class PersonSubstanceDependencyTreatment {
	private static final Logger logger =LoggerFactory.getLogger(PersonSubstanceDependencyTreatment.class.getName());
	private static final String DATE_FORMAT= "M/d/yyyy";
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	

	String moduleName = ModuleConstants.PERSON;
	String screenName = ScreenConstants.SUBSTANCEDEPENDENCYTREATMENT;
	
	public PersonSubstanceDependencyTreatment(){ }
	
	public PersonSubstanceDependencyTreatment(WebDriver wDriver,TestCaseParam testCaseParam)
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
	WebElement substanceDependencyTreatmentID;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Start Date of Services']/../../following-sibling::lightning-input//input)[1]")
	public WebElement startDateOfServices;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='End Date of Services']/../../following-sibling::lightning-input//input)[1]")
	public WebElement endDateOfServices;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Medical Necessity Determinate Date']/../../following-sibling::lightning-input//input)[1]")
	public WebElement medicalNecessityDeterminateDate;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Date Reported to Worker']/../../following-sibling::lightning-input//input)[1]")
	public WebElement dateReportedToWorker;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Related Health Record']/..//input")
	public WebElement relatedHealthRecord;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Health Need']/..//input")
	public WebElement healthNeed;
	
	private String checkboxXpath = "//span[normalize-space()='%s']//label";
	
	public void enterPersonSubstanceDependencyTreatmentDetails(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Enter PersonSubstanceDependencyTreatment Details");
		action.setPageActionDescription("Enter PersonSubstanceDependencyTreatment Details");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			

			String healthNeedTD = testCaseDataSd.get("HEALTH_NEED").get(0);	
			
			Webkeywords.instance().setDateText(driver, startDateOfServices, CommonOperations.getDate(DATE_FORMAT, testCaseDataSd.get("START_DATE_SERVICES").get(0)), testCaseParam, action);		
			Webkeywords.instance().setDateText(driver, endDateOfServices, CommonOperations.getDate(DATE_FORMAT, testCaseDataSd.get("END_DATE_SERVICES").get(0)), testCaseParam, action);		
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Provider Name",testCaseDataSd.get("PROVIDER_NAME").get(0)),util.getRandom(testCaseDataSd.get("PROVIDER_NAME").get(0)), testCaseParam, action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Provider Phone",testCaseDataSd.get("PROVIDER_PHONE").get(0)),util.getRandom(testCaseDataSd.get("PROVIDER_PHONE").get(0)), testCaseParam, action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Outcomes",testCaseDataSd.get("OUTCOMES").get(0)),util.getRandom(testCaseDataSd.get("OUTCOMES").get(0)), testCaseParam, action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Reason for Admission",testCaseDataSd.get("REASON_FOR_ADMISSION").get(0)),util.getRandom(testCaseDataSd.get("REASON_FOR_ADMISSION").get(0)), testCaseParam, action);
			Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("MEETS_MEDICAL_NECESSITY").get(0),"Meets Medical Necessity",testCaseParam,action);
			Webkeywords.instance().setDateText(driver, medicalNecessityDeterminateDate, CommonOperations.getDate(DATE_FORMAT, testCaseDataSd.get("MEDICAL_NECESSITY_DETERMINATE_DATE").get(0)), testCaseParam, action);		
			Webkeywords.instance().setDateText(driver, dateReportedToWorker, CommonOperations.getDate(DATE_FORMAT, testCaseDataSd.get("DATE_REPORTED_TO_WORKER").get(0)), testCaseParam, action);		
			
			Webkeywords.instance().click(driver, healthNeed,healthNeedTD, testCaseParam,action);
			Webkeywords.instance().click(driver, genericLocators.link(driver, healthNeedTD,healthNeedTD),healthNeedTD, testCaseParam,action);
			
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("CONFIDENTIAL_CB").get(0),checkboxXpath, "Confidential"),testCaseDataSd.get("CONFIDENTIAL_CB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("INCLUDE_AS_HEP_ALERT_CB").get(0),checkboxXpath, "Include as HEP alert?"),testCaseDataSd.get("INCLUDE_AS_HEP_ALERT_CB").get(0), testCaseParam,action);
			
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Summary of Current Health Condition",testCaseDataSd.get("SUMMARY_CURRENT_HEALTH_CONDITION").get(0)), util.getRandom(testCaseDataSd.get("SUMMARY_CURRENT_HEALTH_CONDITION").get(0)), testCaseParam, action);
			
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Services Provided/Treatment Goals",testCaseDataSd.get("SERVICES_PROVIDED_TREATMENT_GOALS").get(0)), util.getRandom(testCaseDataSd.get("SERVICES_PROVIDED_TREATMENT_GOALS").get(0)), testCaseParam, action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Additional Information",testCaseDataSd.get("ADDITIONAL_INFORMATION").get(0)), util.getRandom(testCaseDataSd.get("ADDITIONAL_INFORMATION").get(0)), testCaseParam, action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "HEP Alert Description",testCaseDataSd.get("HEP_ALERT_DESCRIPTION").get(0)), util.getRandom(testCaseDataSd.get("HEP_ALERT_DESCRIPTION").get(0)), testCaseParam, action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0), testCaseParam,action);
			SalesforceCommon.verifyToastMessage(driver,"Data saved successfully.",testCaseParam, action);
			
			Webkeywords.instance().waitElementToBeVisible(driver, substanceDependencyTreatmentID);
			SalesforceConstants.setConstantValue("Person SubstanceDependencyTreatmentID", substanceDependencyTreatmentID.getText());	
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
