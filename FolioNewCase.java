package cares.cwds.salesforce.pom.folio;

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
import cares.cwds.salesforce.utilities.web.Webkeywords;

import static java.lang.String.format;

public class FolioNewCase {
private static final Logger logger =LoggerFactory.getLogger(FolioNewCase.class.getName());

private WebDriver driver;
ReportCommon exceptionDetails = new ReportCommon();
ReportCommon testStepDetails = new ReportCommon();
Util util = new Util();

String moduleName = ModuleConstants.FOLIO;
String screenName = ScreenConstants.FOLIONEWCASE;
private static final String FAILED_FORMAT = "Failed ==> {} {} ";

public FolioNewCase(){ }
public FolioNewCase(WebDriver wdriver,TestCaseParam testCaseParam)  
{
initializePage(wdriver,testCaseParam);
                    		}
public void initializePage(WebDriver wdriver,TestCaseParam testCaseParam)
{
    driver = wdriver;
    PageFactory.initElements(driver, this);
    ReportCommon testStepLogDetails = new ReportCommon();
    testStepLogDetails.logModuleAndScreenDetails(testCaseParam, moduleName, screenName);
}


@FindBy(how = How.XPATH, using = "//button[@value='New Case']")
public WebElement newCaseBtn;

@FindBy(how = How.XPATH, using = "//input[@id=string(//label[text()='Last Name']/@for)]")
public WebElement lastName;

@FindBy(how = How.XPATH, using = "//input[@id=string(//label[text()='Last Name']/@for)]")
public WebElement enterLastName;

@FindBy(how = How.XPATH, using = "//input[@id=string(//label[text()='First Name']/@for)]")
public WebElement firstName;

@FindBy(how = How.XPATH, using = "//input[@id=string(//label[text()='First Name']/@for)]")
public WebElement enterFirstName;

@FindBy(how = How.XPATH, using = "//button[@name='SearchBtn']")
public WebElement searchBtn;

@FindBy(how = How.XPATH, using = "//input[@id=string(//label[text()='Middle Name']/@for)]")
public WebElement middleName;

@FindBy(how = How.XPATH, using = "//input[@id=string(//label[text()='Middle Name']/@for)]")
public WebElement enterMiddleName;

@FindBy(how = How.XPATH, using = "//label[text()='Birthdate']/../../following-sibling::lightning-input//input")
public WebElement dob;

@FindBy(how = How.XPATH, using = "//input[@id=string(//label[text()='Social Security Number']/@for)]")
public WebElement socialSecurityNumber;

@FindBy(how = How.XPATH, using = "//input[@id=string(//label[text()='Social Security Number']/@for)]")
public WebElement enterSocialSecurityNumber;

@FindBy(how = How.XPATH, using = "//label[text()='County']/../../following-sibling::lightning-combobox//button")
public WebElement countyDD;

String selectCounty = "//span[@title='%s']";

@FindBy(how = How.XPATH, using = "//label[text()='Sex at Birth']/../../following-sibling::lightning-combobox//button")
public WebElement sexAtBirthDD;


String selectSexAtBirth ="//span[@title='%s']";

@FindBy(how = How.XPATH, using = "//label[text()='Race']/../../following-sibling::lightning-combobox//button")
public WebElement raceDD;

String selectRace = "//span[@title='%s']";

@FindBy(how = How.XPATH, using = "//label[text()='Hispanic or Latino Ethnicity Details']/../../following-sibling::lightning-combobox//button")
public WebElement ethinicityDetailsDD;

String selectEthinicity = "//span[@title='%s']";

@FindBy(how = How.XPATH, using = "//div[text()='New Person']")
public WebElement newPerson;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////Method Start ===>  createNewCase6Jan2025///////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void createNewCase(TestCaseParam testCaseParam,String scriptIteration, String pomIteration) throws Exception  {
PageDetails action = new PageDetails();
LocalDateTime startTime= LocalDateTime.now();
action.setPageActionName("Create New Case");
action.setPageActionDescription("Create New Case");

try {
	Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);





Webkeywords.instance().click(driver,  newCaseBtn,testCaseDataSd.get("NEW_CASE_BTN").get(0), testCaseParam,action);
Webkeywords.instance().click(driver,  lastName,testCaseDataSd.get("LASTNAME").get(0), testCaseParam,action);
Webkeywords.instance().setText(driver,  enterLastName,util.getRandom(testCaseDataSd.get("ENTER_LAST_NAME").get(0)), testCaseParam,action);
Webkeywords.instance().click(driver,  firstName,testCaseDataSd.get("FIRSTNAME").get(0), testCaseParam,action);
Webkeywords.instance().setText(driver,  enterFirstName,util.getRandom(testCaseDataSd.get("ENTER_FIRST_NAME").get(0)), testCaseParam,action);
Webkeywords.instance().click(driver,  searchBtn,testCaseDataSd.get("SEARCH_BTN").get(0), testCaseParam,action);
Webkeywords.instance().click(driver,  middleName,testCaseDataSd.get("MIDDLENAME").get(0), testCaseParam,action);
Webkeywords.instance().setText(driver,  enterMiddleName,util.getRandom(testCaseDataSd.get("ENTER_MIDDLE_NAME").get(0)), testCaseParam,action);
Webkeywords.instance().setDateText(driver, dob, CommonOperations.getDate("M/d/yyyy",testCaseDataSd.get("DATE_OF_BIRTH").get(0)), testCaseParam, action);

Webkeywords.instance().click(driver,  socialSecurityNumber,testCaseDataSd.get("SOCIAL_SECURITY_NUMBER").get(0), testCaseParam,action);
Webkeywords.instance().setText(driver,  enterSocialSecurityNumber,testCaseDataSd.get("ENTER_SOCIAL_SECURITY_NUMBER").get(0), testCaseParam,action);

Webkeywords.instance().click(driver,  countyDD,testCaseDataSd.get("CountyDD").get(0), testCaseParam,action);
WebElement countySelect= driver.findElement(By.xpath(format(selectCounty,testCaseDataSd.get("SELECT_COUNTY").get(0))));
Webkeywords.instance().click(driver,  countySelect,testCaseDataSd.get("SELECT_COUNTY").get(0), testCaseParam,action);

Webkeywords.instance().click(driver,  sexAtBirthDD,testCaseDataSd.get("SEX_AT_BIRTH_DD").get(0), testCaseParam,action);
WebElement sexAtBirthSelect= driver.findElement(By.xpath(format(selectCounty,testCaseDataSd.get("SELECT_SEX_AT_BIRTH").get(0))));
Webkeywords.instance().click(driver,  sexAtBirthSelect,testCaseDataSd.get("SELECT_SEX_AT_BIRTH").get(0), testCaseParam,action);

Webkeywords.instance().click(driver,  raceDD,testCaseDataSd.get("RACE_DD").get(0), testCaseParam,action);
WebElement raceSelect= driver.findElement(By.xpath(format(selectRace,testCaseDataSd.get("SELECT_RACE").get(0))));
Webkeywords.instance().click(driver,  raceSelect,testCaseDataSd.get("SELECT_RACE").get(0), testCaseParam,action);

Webkeywords.instance().click(driver,  ethinicityDetailsDD, testCaseDataSd.get("ETHINICITY_DETAILS_DD").get(0), testCaseParam,action);
WebElement ethicinitySelect= driver.findElement(By.xpath(format(selectEthinicity,testCaseDataSd.get("SELECT_ETHINICITY").get(0))));
Webkeywords.instance().click(driver,  ethicinitySelect,testCaseDataSd.get("SELECT_ETHINICITY").get(0), testCaseParam,action);

Webkeywords.instance().click(driver,  newPerson,testCaseDataSd.get("NEW_PERSON").get(0), testCaseParam,action);

}
            catch (Exception e)
            {
                logger.error(FAILED_FORMAT  ,action.getPageActionDescription());
                exceptionDetails.logExceptionDetails(driver, testCaseParam, action.getPageActionName(), action.getPageActionDescription(), startTime,e);
                throw e;
            }
 }
}

