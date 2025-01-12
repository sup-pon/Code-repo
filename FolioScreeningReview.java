package cares.cwds.salesforce.pom.folio;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class FolioScreeningReview {
	private static final Logger logger =LoggerFactory.getLogger(FolioScreeningReview.class.getName());
	

	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;

	String moduleName = ModuleConstants.FOLIO;
	String screenName = ScreenConstants.FOLIOSCREENINGREVIEW;
	
	private static final String SCREENINGREVIEW= "Screening Review";
	
	public FolioScreeningReview(){ }
	
	public FolioScreeningReview(WebDriver wDriver,TestCaseParam testCaseParam)
	{
		initializePage(wDriver,testCaseParam);
	}
	
	public void initializePage(WebDriver wDriver,TestCaseParam testCaseParam) 
	    {
		logger.info(this.getClass().getName());
	    	 driver = wDriver;
	         PageFactory.initElements(driver, this);
	         ReportCommon testStepLogDetails = new ReportCommon(); 
	         testStepLogDetails.logModuleAndScreenDetails(testCaseParam, moduleName, screenName);
	         genericLocators = new GenericLocators(wDriver);
	    }
	
	@FindBy(how = How.XPATH, using = "//p[contains(.,'Folio Name: ')]")
	public WebElement folioNameLabel;
	@FindBy(how = How.XPATH, using = "//p[contains(.,'Folio Status: ')]")
	public WebElement folioStatusLabel;
	@FindBy(how = How.XPATH, using = "//p[contains(.,'Folio : ')]")
	public WebElement folioLabel;
	
	@FindBy(how = How.XPATH, using = "//p[contains(.,'Screening Name: ')]")
	public WebElement screeningNameLabel;
	@FindBy(how = How.XPATH, using = "//p[contains(.,'Screening : ')]")
	public WebElement screeningsLabel;
	@FindBy(how = How.XPATH, using = "//p[contains(.,'Response Type: ')]")
	public WebElement responseTypeLabel;
	
	public void navigateToFolioScreeningReview	(TestCaseParam testCaseParam,String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		
		action.setPageActionName("Navigate To Folio SCREENINGREVIEW");
		action.setPageActionDescription("Navigate To Folio SCREENINGREVIEW");

			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			String screeninfViewTabFolio = testCaseDataSd.get("FOLIO_SCREENINGREVIEW_TAB").get(0);
			Webkeywords.instance().click(driver, genericLocators.link(driver, SCREENINGREVIEW, screeninfViewTabFolio), screeninfViewTabFolio, testCaseParam,action);
	}

	public void verifyingScreeningReviewDetailsFieldsFolio(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		
		action.setPageActionName("verifying ScreeningReviewDetails Fields");
		action.setPageActionDescription("verifying ScreeningReviewDetails Fields");

			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Screening Name",testCaseDataSd.get("SCREENINGNAME_VERIFY").get(0)), testCaseDataSd.get("SCREENINGNAME_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Response Type",testCaseDataSd.get("RESPONSETYPE_VERIFY").get(0)), testCaseDataSd.get("RESPONSETYPE_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Date/Time of Surrender",testCaseDataSd.get("DATETIME_VERIFY").get(0)), testCaseDataSd.get("DATETIME_VERIFY").get(0), testCaseParam, action);
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.checkbox(driver, "Safely Surrender Baby",testCaseDataSd.get("SAFLYSURRENDERBABU_VERIFY").get(0)), testCaseDataSd.get("SAFLYSURRENDERBABU_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Call Date/Time",testCaseDataSd.get("CALLDATETIME_VERIFY").get(0)), testCaseDataSd.get("CALLDATETIME_VERIFY").get(0), testCaseParam, action);
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Fatality/ Near Fatality",testCaseDataSd.get("NEARFATALITY_VERIFY").get(0)), testCaseDataSd.get("NEARFATALITY_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Primary Worker",testCaseDataSd.get("PRIMARYWORKER_VERIFY").get(0)), testCaseDataSd.get("PRIMARYWORKER_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Location of the Incident",testCaseDataSd.get("LOCATIONOFINCIDENT_VERIFY").get(0)), testCaseDataSd.get("LOCATIONOFINCIDENT_VERIFY").get(0), testCaseParam, action);
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.checkbox(driver, "Further investigation of Commercial Sexual Exploitation (CSE)",testCaseDataSd.get("CSECHECKBOX_VERIFY").get(0)), testCaseDataSd.get("CSECHECKBOX_VERIFY").get(0), testCaseParam, action);
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Perpetrator",testCaseDataSd.get("PERPRTRATOR_VERIFY").get(0)), testCaseDataSd.get("PERPRTRATOR_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textarea(driver, "Provisional Harm Statement",testCaseDataSd.get("PROVISIONALHARMSTATEMENT_VERIFY").get(0)), testCaseDataSd.get("PROVISIONALHARMSTATEMENT_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textarea(driver, "Provisional Danger Statement",testCaseDataSd.get("PROVISIONALDANGERSTATEMENT_VERIFY").get(0)), testCaseDataSd.get("PROVISIONALDANGERSTATEMENT_VERIFY").get(0), testCaseParam, action);
	}
	
	public void verifyingSafetyAlertsFieldsFolio(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		
		action.setPageActionName("verifying Safety Alerts Fields");
		action.setPageActionDescription("verifying Safety Alerts Fields");

			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Safety Alerts",testCaseDataSd.get("SAFTYALERTS_VERIFY").get(0)), testCaseDataSd.get("SAFTYALERTS_VERIFY").get(0), testCaseParam, action);
	}
	
	public void verifyingMandatedReporterInformationFieldsFolio(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		
		action.setPageActionName("verifying MandatedReporterInformation Fields");
		action.setPageActionDescription("verifying MandatedReporterInformation Fields");

			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Caller First Name",testCaseDataSd.get("CALLERFIRSTNAME_VERIFY").get(0)), testCaseDataSd.get("CALLERFIRSTNAME_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Caller Last Name",testCaseDataSd.get("CALLERLASTNAME_VERIFY").get(0)), testCaseDataSd.get("CALLERLASTNAME_VERIFY").get(0), testCaseParam, action);
			
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Country Code",testCaseDataSd.get("COUNTRYCODE_VERIFY").get(0)), testCaseDataSd.get("COUNTRYCODE_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Phone",testCaseDataSd.get("PHONE_VERIFY").get(0)), testCaseDataSd.get("PHONE_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Email",testCaseDataSd.get("EMAIL_VERIFY").get(0)), testCaseDataSd.get("EMAIL_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Preferred Language",testCaseDataSd.get("PREFERREDLANGUAGE_VERIFY").get(0)), testCaseDataSd.get("PREFERREDLANGUAGE_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Interpreter Name",testCaseDataSd.get("INTERPRETERNAME_VERIFY").get(0)), testCaseDataSd.get("INTERPRETERNAME_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Method of Report",testCaseDataSd.get("METHODREPORT_VERIFY").get(0)), testCaseDataSd.get("METHODREPORT_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Preferred Method to Receive ERNRD",testCaseDataSd.get("PREFERREDMETHODERNRD_VERIFY").get(0)), testCaseDataSd.get("PREFERREDMETHODERNRD_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Fax Number",testCaseDataSd.get("FAXNUMBER_VERIFY").get(0)), testCaseDataSd.get("FAXNUMBER_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "Mandated Reporter Type",testCaseDataSd.get("MANDATEDREPORTERTYPE_VERIFY").get(0)), testCaseDataSd.get("MANDATEDREPORTERTYPE_VERIFY").get(0), testCaseParam, action);
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Law Enforcement Badge ID",testCaseDataSd.get("LAWENFORCEMENTBADGE_VERIFY").get(0)), testCaseDataSd.get("LAWENFORCEMENTBADGE_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Employer/Organization Name",testCaseDataSd.get("EMPLOYERORGANIZATION_VERIFY").get(0)), testCaseDataSd.get("EMPLOYERORGANIZATION_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Report Number",testCaseDataSd.get("REPORTNUMBER_VERIFY").get(0)), testCaseDataSd.get("REPORTNUMBER_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.checkbox(driver, "Family Informed of Report",testCaseDataSd.get("FAMILYINFORMEDREPORT_VERIFY").get(0)), testCaseDataSd.get("FAMILYINFORMEDREPORT_VERIFY").get(0), testCaseParam, action);
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.checkbox(driver, "Family Confidentiality Waived",testCaseDataSd.get("FAMILYCONFIDENTIALITYWAIVED_VERIFY").get(0)), testCaseDataSd.get("FAMILYCONFIDENTIALITYWAIVED_VERIFY").get(0), testCaseParam, action);

	}
	
	
}