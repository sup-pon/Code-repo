package cares.cwds.salesforce.pom.folio;

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
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.web.CommonOperations;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class FolioDisposition {
	private static final Logger logger = LoggerFactory.getLogger(FolioDisposition.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;

	String moduleName = ModuleConstants.FOLIO;
	String screenName = ScreenConstants.FOLIODISPOSITION;
	LocalDateTime startTime = LocalDateTime.now();
	private static final String DISPOSITIONS = "Dispositions";

	public FolioDisposition() {
	}

	public FolioDisposition(WebDriver wDriver, TestCaseParam testCaseParam) {
		initializePage(wDriver, testCaseParam);
	}

	public void initializePage(WebDriver wDriver, TestCaseParam testCaseParam) {
		logger.info(this.getClass().getName());
		driver = wDriver;
		PageFactory.initElements(driver, this);
		ReportCommon testStepLogDetails = new ReportCommon();
		testStepLogDetails.logModuleAndScreenDetails(testCaseParam, moduleName, screenName);
		genericLocators = new GenericLocators(wDriver);
	}

	@FindBy(how = How.XPATH, using = "//span[text()='View All']//parent::a")
	public WebElement viewAllButton;

	@FindBy(how = How.XPATH, using = "//h1[text()='Disposition']")
	public WebElement dispositionHeader;

	String dispositioLink = "(//table[@aria-label='Disposition']//tbody/tr[%s]//records-hoverable-link)[2]";

	@FindBy(how = How.XPATH, using = "(//label[text()='Closure Date']/../../following-sibling::lightning-input//input)[1]")
	public WebElement closureDate;

	@FindBy(how = How.XPATH, using = "//p[text()='Alleged Victim']")
	public WebElement allegedVictiunHeader;



	public void navigateToFolioDispositions(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate To Folio Dispositions");
		action.setPageActionDescription("Navigate To Folio Dispositions");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				TestRunSettings.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		String allegationsTabFolio = testCaseDataSd.get("FOLIO_DISPOSITION_TAB").get(0);
		Webkeywords.instance().click(driver, genericLocators.link(driver, DISPOSITIONS, allegationsTabFolio),
				allegationsTabFolio, testCaseParam, action);
	}

	public void editDispositionInfoFolio(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {

		PageDetails action = new PageDetails();
		action.setPageActionName("Process Disposition Info Folio ");
		action.setPageActionDescription("Process Disposition Info Folio");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				TestRunSettings.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);

		Webkeywords.instance().pause();
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		Webkeywords.instance().jsClick(driver, viewAllButton, testCaseDataSd.get("VIEWALL").get(0), testCaseParam,
				action);
		Webkeywords.instance().waitElementToBeVisible(driver, dispositionHeader);
		navigateToDispositionRecord(testCaseParam, scriptIteration, pomIteration);
		Webkeywords.instance().pause();
		Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("DISPOSTION").get(0), "Disposition",
				testCaseParam, action);
		Webkeywords.instance().setDateText(driver, closureDate,
				CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("CLOSURE_DATE").get(0)), testCaseParam, action);
		Webkeywords.instance().selectValueInputDropdown(driver,
				testCaseDataSd.get("DISPOSTION_CLOSURE_REASON_TYPE").get(0), "Disposition Closure Reason Type",
				testCaseParam, action);
		Webkeywords.instance().setText(driver,
				genericLocators.textarea(driver, "Rationale", testCaseDataSd.get("RATIONALE").get(0)),
				util.getRandom(testCaseDataSd.get("RATIONALE").get(0)), testCaseParam, action);
		Webkeywords.instance().click(driver,
				genericLocators.button(driver, "Save", testCaseDataSd.get("SAVE_BTN").get(0)),
				testCaseDataSd.get("SAVE_BTN").get(0), testCaseParam, action);
		Webkeywords.instance().pause();
		Webkeywords.instance().waitElementToBeVisible(driver, allegedVictiunHeader);

	}

	public void verifyingDispositionFieldsFolio(TestCaseParam testCaseParam, String scriptIteration,
			String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("verifying Alligations Fields");
		action.setPageActionDescription("verifying Alligations Fields");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				TestRunSettings.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);

		Webkeywords.instance().verifyTextDisplayed(driver,
				genericLocators.textOnPage(driver, "Child", testCaseDataSd.get("Child_VERIFY").get(0)),
				testCaseDataSd.get("Child_VERIFY").get(0), testCaseParam, action);
		Webkeywords.instance().verifyElementDisplayed(driver,
				genericLocators.dropdown(driver, "Disposition", testCaseDataSd.get("DISPOSITION_VERIFY").get(0)),
				testCaseDataSd.get("DISPOSITION_VERIFY").get(0), testCaseParam, action);
		Webkeywords.instance().verifyElementDisplayed(driver,
				genericLocators.textbox(driver, "Closure Date", testCaseDataSd.get("CLOSUREDATE_VERIFY").get(0)),
				testCaseDataSd.get("CLOSUREDATE_VERIFY").get(0), testCaseParam, action);

		Webkeywords.instance().verifyElementDisplayed(driver,
				genericLocators.textarea(driver, "Rationale", testCaseDataSd.get("RATIONALE_VERIFY").get(0)),
				testCaseDataSd.get("RATIONALE_VERIFY").get(0), testCaseParam, action);
		Webkeywords.instance().verifyElementDisplayed(driver,
				genericLocators.button(driver, "Cancel", testCaseDataSd.get("CANCEL_VERIFY").get(0)),
				testCaseDataSd.get("CANCEL_VERIFY").get(0), testCaseParam, action);
		Webkeywords.instance().verifyElementDisplayed(driver,
				genericLocators.button(driver, "Save", testCaseDataSd.get("SAVE_VERIFY").get(0)),
				testCaseDataSd.get("SAVE_VERIFY").get(0), testCaseParam, action);
	}

	public void navigateToDispositionRecord(TestCaseParam testCaseParam, String scriptIteration, String pomIteration) {

		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Disposition Record");
		action.setPageActionDescription("Navigate to Disposition Record");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				TestRunSettings.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);

		String formatedXpath = format(dispositioLink, pomIteration);
		WebElement scpIdXpath = driver.findElement(By.xpath(formatedXpath));
		Webkeywords.instance().waitElementToBeVisible(driver, scpIdXpath);
		Webkeywords.instance().waitElementClickable(driver, scpIdXpath);
		Webkeywords.instance().jsClick(driver, scpIdXpath, testCaseDataSd.get("DISPOSITION_RECORD").get(0),
				testCaseParam, action);
		Webkeywords.instance().waitElementToBeVisible(driver, scpIdXpath);
	}
}