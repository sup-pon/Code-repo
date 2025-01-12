package cares.cwds.salesforce.pom.screening;

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

public class InitialScreening {
	private static final Logger logger =LoggerFactory.getLogger(InitialScreening.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;

	String moduleName = ModuleConstants.COMMON;
	String screenName = ScreenConstants.SCREENINGDETAILS;
	public InitialScreening(){ }
	
	public InitialScreening(WebDriver wDriver,TestCaseParam testCaseParam)
	{
		initializePage(wDriver,testCaseParam);
	}
	
	public void initializePage(WebDriver wDriver,TestCaseParam testCaseParam) 
	    {
	    	 driver = wDriver;
	         PageFactory.initElements(driver, this);
	         ReportCommon testStepLogDetails = new ReportCommon(); 
	         testStepLogDetails.logModuleAndScreenDetails(testCaseParam, moduleName, screenName);
	    }
	
	@FindBy(how = How.XPATH, using = "//label[text()='Date']//parent::div//input")
	public WebElement dateTb;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@name,'ReasonForCall')]")
	public WebElement reasonForCallDd;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Screening Name']//following-sibling::div/input")
	public WebElement screeningNameTb;
	
	@FindBy(how = How.XPATH, using = "//div[@aria-label='Screening Narrative']//div[contains(@class,'rich-text-area')]")
	public WebElement screeningNarrativeTb;
	
	@FindBy(how = How.XPATH, using = "//h1[text()='Caller Type']")
	public WebElement callerTypeRb;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Caller First Name']//following-sibling::div/input")
	public WebElement callerFnTb;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Caller Last Name']//following-sibling::div/input")
	public WebElement callerLnTb;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Preferred Method to Receive ERNRD']//parent::div/div//div//button")
	public WebElement preferredMethodDd;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Mandated Reporter Type']//parent::div/div//div//button")
	public WebElement mandatedReporterTypeDd;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Phone Type']//parent::div/div//div//button")
	public WebElement phoneTypeDd;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Phone']//following-sibling::div/input")
	public WebElement phoneTb;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Call Back Required']")
	public WebElement callBackRequired;
	
	
	@FindBy(how = How.XPATH, using = "//label[text()='Call Back Required']//parent::div/div//div//button")
	public WebElement callBackRequiredDD;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Save and Proceed']")
	public WebElement saveAndproceedBtn;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Email']//following-sibling::div/input")
	public WebElement emailTb;
	
	
	
	public void addInitialScreeningInfo(TestCaseParam testCaseParam,String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Process Initial Screening");
		action.setPageActionDescription("Process Initial Screening");
		try {
			
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestCaseParam.getTestCaseName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

			String date = testCaseDataSd.get("TodaysDate").get(0);
			String reasonForTheCallValue = testCaseDataSd.get("ReasonForTheCall").get(0);
			String screeningName = testCaseDataSd.get("ScreeningName").get(0);
			String screeningNarrrative = testCaseDataSd.get("ScreeningNarrative").get(0);
			String callerType = testCaseDataSd.get("CallerType").get(0);
			String callerFn = testCaseDataSd.get("FirstName").get(0);
			String callerLn = testCaseDataSd.get("LastName").get(0);
			String preferredMethod = testCaseDataSd.get("PreferredMethodToReceiveERNRD").get(0);
			String mandatedReporterType = testCaseDataSd.get("MandatedReporterType").get(0);
			String phoneType = testCaseDataSd.get("PhoneType").get(0);
			String phoneTextBox = testCaseDataSd.get("PhoneTextbox").get(0);
			String callBackRequiredValue = testCaseDataSd.get("CallBackRequired").get(0);
						
			Webkeywords.instance().setText(driver, dateTb, CommonOperations.getDate("MM/dd/YYYY",date), testCaseParam,action);
			
			Webkeywords.instance().click(driver, reasonForCallDd,"", testCaseParam,action);
			CommonOperations.selectDropdownvalue(driver,"Reason for the Call", reasonForTheCallValue);
			Webkeywords.instance().setText(driver, screeningNameTb, screeningName, testCaseParam,action);
			
			Webkeywords.instance().scrollTillElement(driver,dateTb);
			Webkeywords.instance().click(driver, screeningNarrativeTb,"", testCaseParam,action);
			driver.findElement(By.xpath("//div[@aria-label='Screening Narrative']//div[contains(@class,'rich-text-area')]")).sendKeys(screeningNarrrative);
			Webkeywords.instance().scrollIntoViewElement(driver, callerTypeRb);
			
			genericLocators = new GenericLocators(driver);
			
			WebElement mandateReporter = genericLocators.radiobutton(driver,callerType, "");
			Webkeywords.instance().jsClick(driver, mandateReporter, "", testCaseParam, action);
	    	
			Webkeywords.instance().setText(driver, callerFnTb, callerFn, testCaseParam,action);
			Webkeywords.instance().setText(driver, callerLnTb, callerLn, testCaseParam,action);
			
			Webkeywords.instance().click(driver, preferredMethodDd,"", testCaseParam,action);
			CommonOperations.selectDropdownvalue(driver,"Preferred Method to Receive ERNRD", preferredMethod);
			
			Webkeywords.instance().setText(driver, emailTb,"aaaaa@gmail.com", testCaseParam,action);
			
			Webkeywords.instance().click(driver, mandatedReporterTypeDd,"", testCaseParam,action);
			CommonOperations.selectDropdownvalue(driver,"Mandated Reporter Type",mandatedReporterType);
	    	
			Webkeywords.instance().click(driver, phoneTypeDd,"", testCaseParam,action);
			CommonOperations.selectDropdownvalue(driver,"Phone Type",phoneType);
			
			Webkeywords.instance().setText(driver, phoneTb, phoneTextBox, testCaseParam,action);
			Webkeywords.instance().scrollIntoViewElement(driver, callBackRequired);
			
			Webkeywords.instance().click(driver, callBackRequiredDD,"", testCaseParam,action);
			CommonOperations.selectDropdownvalue(driver,"Call Back Required",callBackRequiredValue);
			
			Webkeywords.instance().click(driver, saveAndproceedBtn,"", testCaseParam,action);
			CommonOperations.pageRefresh(driver);
		}catch (InterruptedException e){
			logger.error("Failed == {} ", action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			Thread.currentThread().interrupt();
			throw new CustomException("Error Occured",e); 
        }
		catch (Exception e)
		{
			logger.error("Failed== {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			
		}
	}
//////////////////New method for reason for call as per LWC screen//////////////////////////////////////////
public void selectReasonForCall(TestCaseParam testCaseParam,String reasonForCall) throws CustomException {
PageDetails action = new PageDetails();
LocalDateTime startTime= LocalDateTime.now();
action.setPageActionName("Select Reason for call");
action.setPageActionDescription("Select Reason for call");

try {
			
	Webkeywords.instance().clickRadioButton(driver, testCaseParam, genericLocators.radiobutton(driver,reasonForCall,""));		
	
}catch (Exception e)
{
logger.error("{} Failed to generate the Live Results",e.getMessage());
exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);

}
}
}