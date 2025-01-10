package cares.cwds.salesforce.pom.person;

import static java.lang.String.format;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.web.CustomException;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class PersonHealthInformation {
	private static final Logger logger =LoggerFactory.getLogger(PersonHealthInformation.class.getName());
	private static final String FAILURE_MSG= "Failed == {} ";
	private static final String ERROR_OCCURED= "Error Occured";

	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	
	
	String moduleName = ModuleConstants.PERSON;
	String screenName = ScreenConstants.PERSONHEALTHINFORMATION;
	
	public PersonHealthInformation(){ }
	
	public PersonHealthInformation(WebDriver wDriver,TestCaseParam testCaseParam)
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
	
	
	private String healthInfoTypeXpath = "//legend[text()='New Health Information']//parent::fieldset[@role='radiogroup']//span[normalize-space()='%s']";
	private String healthInsuranceTypeXpath = "//legend[text()='New Health Insurance']//parent::fieldset[@role='radiogroup']//span[normalize-space()='%s']";
	private String newButtonXpath = "//*[@apiname='%s'][@title='New']//button[text()='New']";
	
	public void navigateToPersonHealthInformation(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Navigate to Person Health Information Tab");
		action.setPageActionDescription("Navigate to Person Health Information Tab");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().navigate(driver, "https://cacwds--v1sit.sandbox.lightning.force.com/lightning/r/Contact/0033S000009FiGXQA0/view", testCaseParam, action);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Health Information", testCaseDataSd.get("HEALTH_INFO_TAB").get(0)), testCaseDataSd.get("HEALTH_INFO_TAB").get(0), testCaseParam,action);
        }
		catch (Exception e)
		{
			logger.error(FAILURE_MSG,action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			throw new CustomException(ERROR_OCCURED, e);
		}
	}
	
	public void navigateToPersonHealthRecords(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Navigate to Person Health Records Tab");
		action.setPageActionDescription("Navigate to Person Health Records Tab");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Health Records", testCaseDataSd.get("HEALTH_RECORDS_TAB").get(0)), testCaseDataSd.get("HEALTH_RECORDS_TAB").get(0), testCaseParam,action);
        }
		catch (Exception e)
		{
			logger.error(FAILURE_MSG,action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			throw new CustomException(ERROR_OCCURED, e);
		}
	}
	
	public void navigateToPersonVitalRecords(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Navigate to Person Vital Records Tab");
		action.setPageActionDescription("Navigate to Person Vital Records Tab");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Vital Records", testCaseDataSd.get("VITAL_RECORDS_TAB").get(0)), testCaseDataSd.get("VITAL_RECORDS_TAB").get(0), testCaseParam,action);
        }
		catch (Exception e)
		{
			logger.error(FAILURE_MSG,action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			throw new CustomException(ERROR_OCCURED, e);
		}
	}
	
	public void clickNewBehavioralEmotionalRecordType(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Click New Behavioral Emotional Record Type");
		action.setPageActionDescription("Click New Behavioral Emotional Record Type");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("BE_NEW_BTN").get(0),newButtonXpath,"New_Behavior_Emotional_Health"), testCaseDataSd.get("BE_NEW_BTN").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("ALCOHOL_DEPENDENCY_TREATMENT_RB").get(0),healthInfoTypeXpath,"Alcohol Dependency Treatment"),testCaseDataSd.get("ALCOHOL_DEPENDENCY_TREATMENT_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("ALCOHOL_SUSBTANCE_USE_RB").get(0),healthInfoTypeXpath,"Alcohol or Substance Use"),testCaseDataSd.get("ALCOHOL_SUSBTANCE_USE_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("BEHAVIORAL_HEALTH_DIAGNOSIS_RB").get(0),healthInfoTypeXpath,"Behavioral Health Diagnosis"),testCaseDataSd.get("BEHAVIORAL_HEALTH_DIAGNOSIS_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("BEHAVIORAL_HEALTH_SERVICES_RB").get(0),healthInfoTypeXpath,"Behavioral Health Services"),testCaseDataSd.get("BEHAVIORAL_HEALTH_SERVICES_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("BEHAVIORAL_EMOTIONAL_HEALTHNEEDS_RB").get(0),healthInfoTypeXpath,"Behavioral and Emotional Health Needs"),testCaseDataSd.get("BEHAVIORAL_EMOTIONAL_HEALTHNEEDS_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("LEGAL_ISSUES_RB").get(0),healthInfoTypeXpath,"Legal Issues"),testCaseDataSd.get("LEGAL_ISSUES_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("PSYCHIATRIC_HOSPITALIZATION_RB").get(0),healthInfoTypeXpath,"Psychiatric Hospitalization"),testCaseDataSd.get("PSYCHIATRIC_HOSPITALIZATION_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("SUBSTANCE_DEPENDENCY_TREATMENT_RB").get(0),healthInfoTypeXpath,"Substance Dependency Treatment"),testCaseDataSd.get("SUBSTANCE_DEPENDENCY_TREATMENT_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("PERSONAL_STRENGTHS_COPING_SKILLS_RB").get(0),healthInfoTypeXpath,"Personal Strengths and Coping Skills"),testCaseDataSd.get("PERSONAL_STRENGTHS_COPING_SKILLS_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("COMMERCIAL_SEXUAL_EXPLOITATION_RB").get(0),healthInfoTypeXpath,"Commercial Sexual Exploitation Incident"),testCaseDataSd.get("COMMERCIAL_SEXUAL_EXPLOITATION_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("BEHAVIORAL_EMOTIONAL_SOCIAL_DISABILITY_RB").get(0),healthInfoTypeXpath,"Behavioral/Emotional/Social Health Disability"),testCaseDataSd.get("BEHAVIORAL_EMOTIONAL_SOCIAL_DISABILITY_RB").get(0),testCaseParam,action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Next", testCaseDataSd.get("NEXT_BTN").get(0)), testCaseDataSd.get("NEXT_BTN").get(0), testCaseParam,action);
        }
		catch (Exception e)
		{
			logger.error(FAILURE_MSG,action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			throw new CustomException(ERROR_OCCURED, e);
		}
	}

	public void clickNewPhyscialHealthRecordType(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Click New PhysicalHealth Record Type");
		action.setPageActionDescription("Click New PhysicalHealth Record Type");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("PH_NEW_BTN").get(0),newButtonXpath,"New_PhysicalHealth"), testCaseDataSd.get("PH_NEW_BTN").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("ALLERGIES_RB").get(0),healthInfoTypeXpath,"Allergies"),testCaseDataSd.get("ALLERGIES_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("DENTAL_EXAMS_RB").get(0),healthInfoTypeXpath,"Dental Exams"),testCaseDataSd.get("DENTAL_EXAMS_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("HEARING_IMPAIRMENT_DEAFNESS_RB").get(0),healthInfoTypeXpath,"Hearing Impairment and Deafness"),testCaseDataSd.get("HEARING_IMPAIRMENT_DEAFNESS_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("HOSPITALIZATIONS_RB").get(0),healthInfoTypeXpath,"Hospitalizations"),testCaseDataSd.get("HOSPITALIZATIONS_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("IMMUNIZATIONS_RB").get(0),healthInfoTypeXpath,"Immunizations"),testCaseDataSd.get("IMMUNIZATIONS_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("MEDICAL_EXAMS_RB").get(0),healthInfoTypeXpath,"Medical Exams"),testCaseDataSd.get("MEDICAL_EXAMS_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("MEDICATIONS_RB").get(0),healthInfoTypeXpath,"Medications"),testCaseDataSd.get("MEDICATIONS_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("VISUAL_IMPAIRMENT_BLINDNESS_RB").get(0),healthInfoTypeXpath,"Visual Impairment and Blindness"),testCaseDataSd.get("VISUAL_IMPAIRMENT_BLINDNESS_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("REGIONAL_CENTER_INFORMATION_RB").get(0),healthInfoTypeXpath,"Regional Center Information"),testCaseDataSd.get("REGIONAL_CENTER_INFORMATION_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("PREGNANCY_RB").get(0),healthInfoTypeXpath,"Pregnancy"),testCaseDataSd.get("PREGNANCY_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("MEDICAL_EQUIPMENT_REQUIRED_RB").get(0),healthInfoTypeXpath,"Medical Equipment Required"),testCaseDataSd.get("MEDICAL_EQUIPMENT_REQUIRED_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("SPECIAL_DIET_REQUIRED_RB").get(0),healthInfoTypeXpath,"Special Diet Required"),testCaseDataSd.get("SPECIAL_DIET_REQUIRED_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("DUAL_AGENCY_SERVICES_RECEIVED_RB").get(0),healthInfoTypeXpath,"Dual-Agency Services Received"),testCaseDataSd.get("DUAL_AGENCY_SERVICES_RECEIVED_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("PHYSICAL_DISABILITY_RB").get(0),healthInfoTypeXpath,"Physical Disability"),testCaseDataSd.get("PHYSICAL_DISABILITY_RB").get(0), testCaseParam,action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Next", testCaseDataSd.get("NEXT_BTN").get(0)), testCaseDataSd.get("NEXT_BTN").get(0), testCaseParam,action);
        }
		catch (Exception e)
		{
			logger.error(FAILURE_MSG,action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			throw new CustomException(ERROR_OCCURED, e);
		}
	}
	
	public void clickNewHealthInsuranceRecordType(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Click New HealthInsurance Record Type");
		action.setPageActionDescription("Click New HealthInsurance Record Type");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollIntoViewElement(driver,genericLocators.link(driver, "History", ""));
			Webkeywords.waitTillPageLoad(new WebDriverWait(driver, 20));

			
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("HI_NEW_BTN").get(0),newButtonXpath,"CARES_New_Health_Insurance"), testCaseDataSd.get("HI_NEW_BTN").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("MEDICAL_RB").get(0),healthInsuranceTypeXpath,"Medi-Cal"),testCaseDataSd.get("MEDICAL_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("MEDICAID_RB").get(0),healthInsuranceTypeXpath,"Medicaid"),testCaseDataSd.get("MEDICAID_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("OTHER_HEALTH_INSURANCE_COVERAGE_RB").get(0),healthInsuranceTypeXpath,"Other Health Insurance Coverage"),testCaseDataSd.get("OTHER_HEALTH_INSURANCE_COVERAGE_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("MENTAL_HEALTH_RB").get(0),healthInsuranceTypeXpath,"Mental Health"),testCaseDataSd.get("MENTAL_HEALTH_RB").get(0), testCaseParam,action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("PRESCRIPTION_DRUGS_RB").get(0),healthInsuranceTypeXpath,"Prescription Drugs"),testCaseDataSd.get("PRESCRIPTION_DRUGS_RB").get(0), testCaseParam,action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Next", testCaseDataSd.get("NEXT_BTN").get(0)), testCaseDataSd.get("NEXT_BTN").get(0), testCaseParam,action);
        }
		catch (Exception e)
		{
			logger.error(FAILURE_MSG,action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			throw new CustomException(ERROR_OCCURED, e);
		}
	}
	
	/////////////////////////////////////////Private Methods///////////////////////////////////////////////
	
	private WebElement getElementBasedOnFlag(String flag, String healthInfoTypeXpath, String name) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(healthInfoTypeXpath,name)));
		}
		else
			return null;
	}
}
