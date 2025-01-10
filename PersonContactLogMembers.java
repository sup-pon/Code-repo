package cares.cwds.salesforce.pom.person;

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
import org.testng.Assert;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.web.CustomException;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class PersonContactLogMembers {
	private static final Logger logger =LoggerFactory.getLogger(PersonContactLogMembers.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;

	String moduleName = ModuleConstants.PERSON;
	String screenName = ScreenConstants.PERSONCONTACTLOGMEMBERS;
	
	public PersonContactLogMembers(){ }
	
	public PersonContactLogMembers(WebDriver wDriver,TestCaseParam testCaseParam)
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
	
	@FindBy(how = How.XPATH, using = "(//table[@aria-label='Contact Log Members (Participant)']//tbody//tr[1]//td[2]//lightning-primitive-cell-factory//span//div)[2]//a")
	public WebElement contactID;
	
	public void navigateToContactLogMembers(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Navigate to Contact Log Members");
		action.setPageActionDescription("Navigate to Contact Log Members");
		try {
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Contact Log Members",testCaseDataSd.get("CONTACT_LOG_MEMBERS_TAB").get(0)),testCaseDataSd.get("CONTACT_LOG_MEMBERS_TAB").get(0), testCaseParam,action);

        }
		catch (Exception e)
		{
			logger.error("Failed== {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			Thread.currentThread().interrupt();
		}
	}
	
	public void verifyContactLogMembersID(TestCaseParam testCaseParam) throws CustomException  {

		PageDetails action = new PageDetails();
		LocalDateTime startTime= LocalDateTime.now();
		action.setPageActionName("Verify Contact Log Members ID");
		action.setPageActionDescription("Verify Contact Log Members ID");
		try {
			Webkeywords.instance().waitElementToBeVisible(driver,contactID );
			String actualContactID= contactID.getAttribute("title");
			String expectedContractID = SalesforceConstants.getConstantValue("SCR TRIBAL CONTACTID");
			Assert.assertEquals(actualContactID, expectedContractID);
        }
		catch (Exception e)
		{
			logger.error("Failed== {}",action.getPageActionDescription());
			exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
			Thread.currentThread().interrupt();
		}
	}
}