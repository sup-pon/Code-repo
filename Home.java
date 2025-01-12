package cares.cwds.salesforce.pom;

import static java.lang.String.format;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.SalesforceCommon;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class Home {
	
	private static final Logger logger =LoggerFactory.getLogger(Home.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;

	String moduleName = ModuleConstants.COMMON;
	String screenName = ScreenConstants.HOME;
	public Home(){ }
	
	public Home(WebDriver wDriver,TestCaseParam testCaseParam) 
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
	
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'slds-global-actions__notifications slds-global-actions__item-action')]")
	public WebElement bellIcon;

	@FindBy(how = How.XPATH, using = "//li[@class='notification-row notification-unread unsNotificationsListRow']/a/div/div/h3[@class='notification-text-title']")
	public List<WebElement> notificationTitlesList;

	@FindBy(how = How.XPATH, using = "//li[@class='notification-row notification-unread unsNotificationsListRow']/a/div/div/span[@class='notification-text uiOutputText']")
	public List<WebElement> notificationMessagesList;

	@FindBy(how = How.XPATH, using = "//div[@data-se='app-card-container']/a")
	public WebElement selectApp;
	
	@FindBy(how = How.XPATH, using = "//div/span[contains(text(),'CWS-CARES')]")
	public WebElement txtcares;
	
	@FindBy(how = How.XPATH, using = "//div[@data-aura-class='navexAppNavMenu']")
	public WebElement appNavMenu;
	
	@FindBy(how = How.XPATH, using = "//*[@role='menu'][@aria-label='Navigation Menu']")
	public WebElement appNavMenuContainerList;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Search')]//parent::button")
	public WebElement searchRecord;
	
	String clickRecord = "//span[@title='%s']//parent::div/span";
	
	String pageName = "//span[contains(@class,'slds-listbox__option')][text()='%s']";
	
	String quickLink = "//strong[text()='Quick Links:']/../..//a[text()='%s']";
	
    @FindBy(how = How.XPATH, using = "//button[contains(@title,'Close')]")
	public WebElement closeButton;
    
    @FindBy(how = How.XPATH, using ="//input[@data-value='Search: All']//ancestor::lightning-grouped-combobox/../following::input[@type='search']")
    public WebElement searchTextField;
    
    @FindBy(how = How.XPATH, using ="//input[@placeholder='Search this list...']")
    public WebElement innerSearch;
    
    @FindBy(how = How.XPATH, using ="//a[@data-refid='recordId']")
    public WebElement recordID;
    
    private String notificationTitle = "//div[@class='unsNotificationsPanel']//div[@class='listContainer scrollable']//div[@class='notification-content']//*[text()='%s']";
    private String notificationMessage = "//div[@class='unsNotificationsPanel']//div[@class='listContainer scrollable']//div[@class='notification-content']//span[normalize-space()='%s']";
 
    
    //pageTitle should match the app menu item name on the Home page
	public void navigateToAppMenuPage(String pageTitle, TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Navigate to App Menu Page");
		action.setPageActionDescription("Navigate to App Menu Page");
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			String testDataPageTitle = null;
						
			Webkeywords.instance().waitElementToBeVisible(driver, appNavMenu);
			Webkeywords.instance().click(driver, appNavMenu,testCaseDataSd.get("APP_NAV_MENU").get(0), testCaseParam,action);
			Webkeywords.instance().waitElementToBeVisible(driver, appNavMenuContainerList);
			WebElement page = driver.findElement(By.xpath(format(pageName,pageTitle)));
			Webkeywords.instance().scrollIntoViewElement(driver,page);
			testDataPageTitle = (pageTitle.toUpperCase()).replace(" ","_");
			if(pageTitle.equalsIgnoreCase("Service Names / Evidence Based Practices"))
				testDataPageTitle = "SERVICE_NAMES";
			Webkeywords.instance().click(driver, page,testCaseDataSd.get(testDataPageTitle).get(0), testCaseParam,action);

	}
	
	//name should match the quick link on the Home page
	public void navigateToQuickLinkInHome(String name, TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Homepage QuickLink");
		action.setPageActionDescription("Navigate to Homepage QuickLink");	
			String testDataName=null;
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			testDataName = (name.toUpperCase()).replace(" ","_");
			Webkeywords.instance().click(driver, driver.findElement(By.xpath(format(quickLink,name))),testCaseDataSd.get(testDataName).get(0), testCaseParam,action);
}

	public void verifyNotificationMessage(TestCaseParam testCaseParam) {
		
		PageDetails action = new PageDetails();

		action.setPageActionName("Verify Notification");
		action.setPageActionDescription("Verify Notification");
		Webkeywords.instance().click(driver, bellIcon,"", testCaseParam,action);
	}
	
	public void verifyNotification(TestCaseParam testCaseParam, String expectedTitle, String expectedMessage){
		PageDetails action = new PageDetails();
		action.setPageActionName("Verify Bell Notification Message");
		action.setPageActionDescription("Verify Bell Notification Message");

		Webkeywords.instance().waitElementToBeVisible(driver, bellIcon);
		Webkeywords.instance().click(driver, bellIcon,"", testCaseParam,action);
		Webkeywords.instance().verifyElementDisplayed(driver, driver.findElement(By.xpath(format(notificationTitle,expectedTitle))), "", testCaseParam, action);
		Webkeywords.instance().verifyElementDisplayed(driver, driver.findElement(By.xpath(format(notificationMessage,expectedMessage))), "", testCaseParam, action);
		Webkeywords.instance().click(driver, bellIcon,"", testCaseParam,action);
	}
	
/////////////////////////////This method is already covered in the above method navigateToAppMenuPage() and can be removed///////////////////////////////
	public void selectLinkFromNavigationMenue(TestCaseParam testCaseParam,String scriptIteration, String pomIteration, String navigatorMenue){
		PageDetails action = new PageDetails();
		action.setPageActionName("Show Navigation Menu");
		action.setPageActionDescription("Show Navigation Menu");

		

			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Show Navigation Menu",testCaseDataSd.get("SHOWNAVIGATIONMENUE").get(0)), testCaseDataSd.get("SHOWNAVIGATIONMENUE").get(0), testCaseParam, action);
			switch (navigatorMenue) {
			case "Organizations":
				Webkeywords.instance().click(driver, genericLocators.button(driver, "Organizations",testCaseDataSd.get("ORGANIZATIONS").get(0)), testCaseDataSd.get("ORGANIZATIONS").get(0), testCaseParam, action);
				break;
			case "Provider Search":
				Webkeywords.instance().click(driver, genericLocators.button(driver, "Provider Search",testCaseDataSd.get("PROVIDERSEARCH").get(0)), testCaseDataSd.get("PROVIDERSEARCH").get(0), testCaseParam, action);
				break;
			case "Screenings":
				Webkeywords.instance().click(driver, genericLocators.button(driver, "Screenings",testCaseDataSd.get("SCREENINGS").get(0)), testCaseDataSd.get("testCaseDataSd.get(\"RECALL\").get(0)").get(0), testCaseParam, action);
				break;
			case "Home":
				Webkeywords.instance().click(driver, genericLocators.button(driver, "Home",testCaseDataSd.get("HOME_TAB").get(0)), testCaseDataSd.get("HOME_TAB").get(0), testCaseParam, action);
				break;
			case "Focused Search":
				Webkeywords.instance().click(driver, genericLocators.button(driver, "Focused Search",testCaseDataSd.get("FOCUSED_SEARCH_TAB").get(0)), testCaseDataSd.get("FOCUSED_SEARCH_TAB").get(0), testCaseParam, action);
				break;

			default:
				break;
			}

	}
	
	public void clickOnNotificationForApproval(String recordType, TestCaseParam testCaseParam, String scriptIteration, String pomIteration){
		PageDetails action = new PageDetails();
		action.setPageActionName("Click Bell Notification Item");
		action.setPageActionDescription("Click Bell Notification Item");
		
			WebElement notificationBell = null;
			WebElement scrIdnotification = null;
			String id;
			notificationBell = Webkeywords.instance().waitAndReturnWebElement(driver,By.xpath("//button[contains(@class,'slds-global-actions__notifications slds-global-actions__item-action')]"));
			Webkeywords.instance().waitElementClickable(driver, notificationBell);
			notificationBell.click();
			switch (recordType.toLowerCase()) {
			case "scr_id":
				id = SalesforceConstants.getConstantValue(recordType+pomIteration);
				scrIdnotification = Webkeywords.instance().waitAndReturnWebElement(driver,By.xpath("//div/span[contains(text(),'Screening ID: " + id + "')]"));
				Webkeywords.instance().waitElementClickable(driver, scrIdnotification);
				Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				Webkeywords.instance().click(driver, scrIdnotification,testCaseDataSd.get("SCR_BELL_NOTIFICATION").get(0), testCaseParam,action);
				return;
			case "folio ref":
				id = SalesforceConstants.getConstantValue(recordType);
				WebElement folionotification = driver.findElement(By.xpath("//div/span[contains(text(),'Folio Ref.: " + id + "')]"));
				Webkeywords.instance().waitElementClickable(driver, folionotification);
				Webkeywords.instance().click(driver, folionotification,"", testCaseParam,action);
				return;
			case "family transfer id":
				WebElement ftrnotification = driver.findElement(By.xpath("//li[1]//div/span[contains(text(),'FTR-')]"));
				Webkeywords.instance().waitElementClickable(driver, ftrnotification);
				Webkeywords.instance().click(driver, ftrnotification,"", testCaseParam,action);

				return;
			case "court work item id":
				WebElement courtnotification = driver.findElement(By.xpath("//li[1]//div/span[contains(text(),'Court Work Item ID:')]"));
				Webkeywords.instance().waitElementClickable(driver, courtnotification);
				Webkeywords.instance().click(driver, courtnotification,"", testCaseParam,action);
				return;
			default:
				logger.error("Record type name not found in click notification");
			}

	}

	public void searchRecordMethod (TestCaseParam testCaseParam, String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();

		action.setPageActionName("Search Record");
		action.setPageActionDescription("Search Record");
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		
			String recordId = SalesforceConstants.getConstantValue(testCaseDataSd.get("RECORD_ID").get(0));
			Webkeywords.instance().click(driver,searchRecord ,testCaseDataSd.get("SEARCH").get(0), testCaseParam,action);
			Webkeywords.instance().setText(driver,searchTextField,recordId , testCaseParam, action);
			Webkeywords.instance().pause();
			WebElement page = driver.findElement(By.xpath(format(clickRecord,recordId))) ;
			Webkeywords.instance().waitElementToBeVisible(driver, page);
			Webkeywords.instance().waitElementClickable(driver, page);
			Webkeywords.instance().waitElementToBeVisible(driver, page);
			Webkeywords.instance().click(driver,page ,recordId, testCaseParam,action);

			
	}
	

	
	public void searchFolioRecord (TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Search Folio Record");
		action.setPageActionDescription("Search Folio Record");


			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().pause();
			String recordId = SalesforceConstants.getConstantValue(testCaseDataSd.get("RECORD_ID").get(0));

			Webkeywords.instance().click(driver,searchRecord ,testCaseDataSd.get("SEARCH").get(0), testCaseParam,action);
			Webkeywords.instance().setText(driver,searchTextField,recordId , testCaseParam, action);
			Webkeywords.instance().pause();
			WebElement page = driver.findElement(By.xpath(format(clickRecord,recordId))) ;
			Webkeywords.instance().waitElementToBeVisible(driver, page);
			Webkeywords.instance().waitElementClickable(driver, page);
			Webkeywords.instance().waitElementToBeVisible(driver, page);
			Webkeywords.instance().click(driver,page ,recordId, testCaseParam,action);
			Webkeywords.instance().pause();
			SalesforceCommon.captureRecordURL(driver,"FOLIO");

	}


		public void closeAllTabs() {

			PageDetails action = new PageDetails();
			action.setPageActionName("Close All tabs");
			action.setPageActionDescription("Close All tabs");
			
				Webkeywords.instance().closeAllOpenTabs(driver, closeButton);
			
		}
		

		public void innerSearchMethod (TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
			PageDetails action = new PageDetails();
			action.setPageActionName("Inner Search Record");
			action.setPageActionDescription("Inner Search Record");
				Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
						TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
				String recordId = SalesforceConstants.getConstantValue("SCR_ID"+pomIteration);
				Webkeywords.instance().click(driver,innerSearch ,testCaseDataSd.get("INNER SEARCH").get(0), testCaseParam,action);
				Webkeywords.instance().setText(driver,innerSearch,recordId , testCaseParam, action);
				innerSearch.sendKeys(Keys.ENTER);
				Webkeywords.instance().waitElementToBeVisible(driver, recordID);
				Webkeywords.instance().jsClick(driver,recordID ,recordId, testCaseParam,action);
			

		}
		
		
		public void validateTitleIVHomeScreen(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
			
		}
		

public void verifyingHomeCurrentTaskAssignmentsFieldsHeaders (TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("verifying Current Task Assignments Fields");
		action.setPageActionDescription("verifying Current Task Assignments Fields");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				
				Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.link(driver, "Current Task Assignments",testCaseDataSd.get("CURRENTTASKASSIGNMENT_VERIFY").get(0)), testCaseDataSd.get("CURRENTTASKASSIGNMENT_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Due Date",testCaseDataSd.get("DUEDATE_VERIFY").get(0)), testCaseDataSd.get("DUEDATE_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Subject",testCaseDataSd.get("SUBJECT_VERIFY").get(0)), testCaseDataSd.get("SUBJECT_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Related To",testCaseDataSd.get("RELATEDTO_VERIFY").get(0)), testCaseDataSd.get("RELATEDTO_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Priority",testCaseDataSd.get("PRIORITY_VERIFY").get(0)), testCaseDataSd.get("PRIORITY_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Status",testCaseDataSd.get("STATUS_VERIFY").get(0)), testCaseDataSd.get("STATUS_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Source",testCaseDataSd.get("SOURCE_VERIFY").get(0)), testCaseDataSd.get("SOURCE_VERIFY").get(0), testCaseParam, action);
				
				
		
	}
	
	public void verifyingHomeServiceTypesFieldsHeaders (TestCaseParam testCaseParam, String scriptIteration, String pomIteration){
		PageDetails action = new PageDetails();
		action.setPageActionName("verifying ServiceTypes Fields");
		action.setPageActionDescription("verifying ServiceTypes Fields");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Service Types",testCaseDataSd.get("SERVICETYPES_VERIFY").get(0)), testCaseDataSd.get("SERVICETYPES_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Service Type Name",testCaseDataSd.get("SERVICETYPENAME_VERIFY").get(0)), testCaseDataSd.get("SERVICETYPENAME_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Service Type Description",testCaseDataSd.get("SERVICETYPEDESCRIPTION_VERIFY").get(0)), testCaseDataSd.get("SERVICETYPEDESCRIPTION_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Service Category",testCaseDataSd.get("SERVICECATEGORY_VERIFY").get(0)), testCaseDataSd.get("SERVICECATEGORY_VERIFY").get(0), testCaseParam, action);
				Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Last Modified Date",testCaseDataSd.get("LASTMODIEFIEDDATE_VERIFY").get(0)), testCaseDataSd.get("LASTMODIEFIEDDATE_VERIFY").get(0), testCaseParam, action);
			
	}
	
	public void verifyingHomeMyTaskListFieldsHeaders (TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("verifying MyTaskList Fields");
		action.setPageActionDescription("verifying MyTaskList Fields");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				
			Webkeywords.instance().click(driver, genericLocators.link(driver, "My Task List",testCaseDataSd.get("MYTASKLIST").get(0)),testCaseDataSd.get("MYTASKLIST").get(0), testCaseParam,action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.link(driver, "Current Task Assignments",testCaseDataSd.get("CURRENTTASKASSIGNMENT_VERIFY").get(0)), testCaseDataSd.get("CURRENTTASKASSIGNMENT_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Due Date",testCaseDataSd.get("DUEDATE_VERIFY").get(0)), testCaseDataSd.get("DUEDATE_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Subject",testCaseDataSd.get("SUBJECT_VERIFY").get(0)), testCaseDataSd.get("SUBJECT_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Related To",testCaseDataSd.get("RELATEDTO_VERIFY").get(0)), testCaseDataSd.get("RELATEDTO_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Priority",testCaseDataSd.get("PRIORITY_VERIFY").get(0)), testCaseDataSd.get("PRIORITY_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Status",testCaseDataSd.get("STATUS_VERIFY").get(0)), testCaseDataSd.get("STATUS_VERIFY").get(0), testCaseParam, action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Source",testCaseDataSd.get("SOURCE_VERIFY").get(0)), testCaseDataSd.get("SOURCE_VERIFY").get(0), testCaseParam, action);

	}
	
	public void verifyingHomeMyOrganizationFieldsHeaders (TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("verifying MyOrganization Fields");
		action.setPageActionDescription("verifying MyOrganization Fields");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				
			Webkeywords.instance().click(driver, genericLocators.link(driver, "My Organizations",testCaseDataSd.get("MYORGANIZATION").get(0)),testCaseDataSd.get("MYORGANIZATION").get(0), testCaseParam,action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.link(driver, "New Providers created in past 120 days",testCaseDataSd.get("NEWPROVIDER120DAYS_VERIFY").get(0)), testCaseDataSd.get("NEWPROVIDER120DAYS_VERIFY").get(0), testCaseParam, action);
			
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Name",testCaseDataSd.get("NAME_VERIFY").get(0)), testCaseDataSd.get("NAME_VERIFY").get(0), testCaseParam, action);		
	}

	}
