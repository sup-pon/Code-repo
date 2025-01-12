package uitests.testng.milestone3;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;	
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.pom.ApprovalandAuditHistory;
import cares.cwds.salesforce.pom.Home;
import cares.cwds.salesforce.pom.Login;
import cares.cwds.salesforce.pom.Logout;
import cares.cwds.salesforce.pom.RecallAndResubmitRecord;
import cares.cwds.salesforce.pom.SubmitForApproval;
import cares.cwds.salesforce.pom.SupervisorReviewPromotion;
import cares.cwds.salesforce.pom.screening.Documents;
import cares.cwds.salesforce.pom.screening.ERRDoc;
import cares.cwds.salesforce.pom.screening.ScreeningApproval;
import cares.cwds.salesforce.pom.screening.ScreeningContactLog;
import cares.cwds.salesforce.pom.screening.ScreeningDetails;
import cares.cwds.salesforce.pom.screening.ScreeningPerson;
import cares.cwds.salesforce.pom.screening.ScreeningTribalInquiry;
import cares.cwds.salesforce.pom.screening.ScreeningValidatePerson;
import cares.cwds.salesforce.pom.screening.ScreeningsPage;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.testng.TestNGCommon;
import cares.cwds.salesforce.utilities.web.CustomException;
import cares.cwds.salesforce.utilities.web.SalesforceCommon;

public class T2922 extends TestNGCommon {
	String testCaseName = "To validate screening";
	String moduleName = "SD";
	String fileName = "ScriptMasterSheet"; 


	public T2922(){

		/*This method is initially left blank for now*/
	}

	@BeforeMethod
	public void setUpReport() throws InterruptedException
	{
		driver = TestRunSettings.getDriver();
		browser = TestRunSettings.getBrowser();
		TestCaseParam.setTestCaseName(testCaseName);
		TestCaseParam.setModuleName(moduleName);
		TestCaseParam.setBrowser(browser);
		TestCaseParam.setTestCaseDescription(TestCaseParam.getTestCaseName());
		initializeTestCase(testCaseParam);
	}


	@Test (dataProvider = "data-provider")
	public void testT2922(String scriptIteration) throws CustomException, InterruptedException {
		if(driver == null) {
			driver = initializeDriver();
		}

		/////////////////////Login As Hotline Worker/////////////////////////////////////////////////////

		
		Login login = new Login(driver, testCaseParam);
    	login.processLoginNew(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1, SalesforceConstants.LOGINUSER); 

		Home home = new Home(driver, testCaseParam);
		
		home = new Home(driver, testCaseParam);
		home.closeAllTabs();
		home.navigateToAppMenuPage("Screenings",testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);

		ScreeningsPage screeningPage= new ScreeningsPage(driver, testCaseParam);
		screeningPage.cwsPolicyManualValidation(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		screeningPage.navigateToListView("Screenings", "Recently Viewed",testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		screeningPage.navigateToLatestRecord(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);

		ScreeningDetails screeningDetails= new ScreeningDetails(driver, testCaseParam);
		screeningDetails= new ScreeningDetails(driver, testCaseParam);
		screeningDetails.clickNewScreening(testCaseParam,scriptIteration,SalesforceConstants.POMITERATION1);
		screeningDetails.enterScreeningDetails(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		screeningDetails.submitScreeningDetails(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION2);   	

		ScreeningPerson screeningPerson = new ScreeningPerson(driver, testCaseParam);
		screeningPerson = new ScreeningPerson(driver, testCaseParam);
		screeningPerson.navigateToScreeningPersons(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		screeningPerson.addScreeningPerson(testCaseParam, scriptIteration , SalesforceConstants.POMITERATION1);
		screeningPerson.addScreeningPerson(testCaseParam, scriptIteration , SalesforceConstants.POMITERATION2);
		screeningPerson.addScreeningPerson(testCaseParam, scriptIteration , SalesforceConstants.POMITERATION3);
		screeningPerson.addScreeningPerson(testCaseParam, scriptIteration , SalesforceConstants.POMITERATION4);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		ScreeningTribalInquiry tribalInquiry=new ScreeningTribalInquiry(driver, testCaseParam);
		tribalInquiry=new ScreeningTribalInquiry(driver, testCaseParam);
		tribalInquiry.navigateToScreeningContactLogs(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		tribalInquiry.addTribalInquiryAndCollaborationInfo(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		tribalInquiry.navigateToScreeningContactLogs(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		tribalInquiry.addTribalInquiryAndCollaborationInfo(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION2);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		SupervisorReviewPromotion review= new SupervisorReviewPromotion(driver, testCaseParam);
		review= new SupervisorReviewPromotion(driver, testCaseParam);
		review.navigateToSupervisorReviewPromotion(testCaseParam, scriptIteration, scriptIteration);
		review.addSupervisorReviewPromotionInfo(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		ScreeningValidatePerson validatePerson = new ScreeningValidatePerson(driver, testCaseParam);
		screeningPerson.navigateToScreeningPersons(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		validatePerson.validatePersonDetails(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);
		screeningPerson.navigateToScreeningPersons(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		validatePerson.validatePersonDetails(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION2);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);
		screeningPerson.navigateToScreeningPersons(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		validatePerson.validatePersonDetails(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION3);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);
		screeningPerson.navigateToScreeningPersons(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		validatePerson.validatePersonDetails(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION4);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		ApprovalandAuditHistory screeningapproval = new ApprovalandAuditHistory(driver,testCaseParam);
		screeningapproval.navigateToApprovalAuditHistory(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		screeningapproval.verifyHeaderColumnsInTable("Screening History", testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);

		ScreeningContactLog screeningContact = new ScreeningContactLog(driver, testCaseParam);
		screeningContact.navigateToScreeningContactLogs(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		screeningContact.addScreeningContactLog(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

	
		ERRDoc errDoc= new ERRDoc(driver, testCaseParam);
		errDoc= new ERRDoc(driver, testCaseParam);
		errDoc.generateERRDoc(testCaseParam,scriptIteration, SalesforceConstants.POMITERATION1);

		errDoc.generateERRDoc(testCaseParam,scriptIteration, SalesforceConstants.POMITERATION2);

		Documents document= new Documents(driver, testCaseParam);
		document.navigateToDocuments(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		document.deleteDocument(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);

		errDoc= new ERRDoc(driver, testCaseParam);
		errDoc.generateERRDoc(testCaseParam,scriptIteration, SalesforceConstants.POMITERATION1);

		SubmitForApproval approval= new SubmitForApproval (driver,testCaseParam);
		approval= new SubmitForApproval (driver,testCaseParam);
		approval.clickSubmitForApprovalInfo(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION2);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);
		
		screeningDetails= new ScreeningDetails(driver, testCaseParam);
		screeningDetails.navigateToScreeningDetails(testCaseParam,scriptIteration,SalesforceConstants.POMITERATION1);
		screeningDetails.verifyScreeningDetailsNonEditable(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, "SCR");
		
		RecallAndResubmitRecord recall= new RecallAndResubmitRecord(driver,testCaseParam);
		recall.clickRecallAndResubmitRecordInfo(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, "SCR");
		screeningPage.checkStatusOnScreeningRecord(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION2);
		
		Logout objLogout = new Logout(driver, testCaseParam);
		objLogout.processLogout(testCaseParam,scriptIteration,SalesforceConstants.POMITERATION1); 

		

		////////////////login as supervisor 1 (to validate recalled  record is editable)/////////////////////////////////
		
		login = new Login(driver, testCaseParam);
		login.processLoginNew(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1, SalesforceConstants.HLSUPERVISOR);	
		SalesforceCommon.navigateToRecordURL(driver, "SCR");
		
		screeningDetails.navigateToScreeningDetails(testCaseParam,scriptIteration,SalesforceConstants.POMITERATION1);
		screeningDetails.enterScreeningDetails(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION2);
		screeningDetails.submitScreeningDetails(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		
		objLogout.processLogout(testCaseParam,scriptIteration,SalesforceConstants.POMITERATION1);  
		
		/////////////login as Hotline worker to resubmit the record//////////////
		
		login.processLoginNew(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1, SalesforceConstants.LOGINUSER);	
		
		home = new Home(driver, testCaseParam);
		home.closeAllTabs();
		home.navigateToAppMenuPage("Screenings",testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		
		SalesforceCommon.navigateToRecordURL(driver, "SCR");
		
		approval.clickSubmitForApprovalInfo(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, "SCR");		
		objLogout.processLogout(testCaseParam,scriptIteration,SalesforceConstants.POMITERATION1);  
			
		///////////login as supervisor 2(to validate recalled  record is editable)///////////////
		
		login = new Login(driver, testCaseParam);
		login.processLoginNew(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1, SalesforceConstants.HLSUPERVISOR2);
		
		SalesforceCommon.navigateToRecordURL(driver, "SCR");
		
		ApprovalandAuditHistory approvalandAuditHistory = new ApprovalandAuditHistory(driver, testCaseParam);
		approvalandAuditHistory.navigateToApprovalAuditHistory(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, "SCR");
		
		ScreeningApproval screeningApproval = new ScreeningApproval(driver,testCaseParam);
		screeningApproval.approveScreening(testCaseParam,scriptIteration,SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, "SCR");
		
		document= new Documents(driver, testCaseParam);
		document.navigateToDocuments(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		document.validateDocumentList(testCaseParam, scriptIteration, scriptIteration, SalesforceConstants.POMITERATION1);
		
		home= new Home(driver,testCaseParam);
		home.verifyNotification(testCaseParam, "Approval request for the screening is approved", SalesforceConstants.getConstantValue("SCR_ID1"));
		
		objLogout.processLogout(testCaseParam,scriptIteration,SalesforceConstants.POMITERATION1); 
			
	}

	@DataProvider (name = "data-provider")
	public String[] dpMethod(Method method){
		return setScriptIterationFlag(fileName,moduleName, method.getName());

	}


	@AfterMethod
	public void tearDownMethod()
	{
		endTestCase(testCaseParam);
		quitDriver(driver);  	
	}
}