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
import cares.cwds.salesforce.pom.screening.Allegations;
import cares.cwds.salesforce.pom.screening.CallBackAttempts;
import cares.cwds.salesforce.pom.screening.Documents;
import cares.cwds.salesforce.pom.screening.ERRDoc;
import cares.cwds.salesforce.pom.screening.ScreeningAddress;
import cares.cwds.salesforce.pom.screening.ScreeningContactLog;
import cares.cwds.salesforce.pom.screening.ScreeningDetails;
import cares.cwds.salesforce.pom.screening.ScreeningPerson;
import cares.cwds.salesforce.pom.screening.ScreeningSafetyAlert;
import cares.cwds.salesforce.pom.screening.ScreeningTribalInquiry;
import cares.cwds.salesforce.pom.screening.ScreeningValidatePerson;
import cares.cwds.salesforce.pom.screening.ScreeningsPage;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.testng.TestNGCommon;
import cares.cwds.salesforce.utilities.web.CustomException;
import cares.cwds.salesforce.utilities.web.SalesforceCommon;

public class T2901_1 extends TestNGCommon{

	String testCaseName = "To validate Title IV Placement Oversight Staff user is able to create and edit a new Service Category, Service Type and Service Name (EBP) records and validate notifications.";
	String moduleName = "SD";
	String fileName = "ScriptMasterSheet";


	public T2901_1(){

		/*This method is initially left blank for now*/
	}
	
	@BeforeMethod
	public void setUpReport() throws InterruptedException
	{
		driver = TestRunSettings.getDriver();
		browser = TestRunSettings.getBrowser();
		testCaseParam.setTestCaseName(testCaseName);
		testCaseParam.setModuleName(moduleName);
		testCaseParam.setBrowser(browser);
		testCaseParam.setTestCaseDescription(testCaseParam.getTestCaseName());
		initializeTestCase(testCaseParam);
	}

    @Test (dataProvider = "data-provider")
    public void testT2901_1(String scriptIteration) throws CustomException, InterruptedException {
    	if(driver == null) {
    		driver = initializeDriver();
    	}
    	
    	Login login = new Login(driver, testCaseParam);
    	login.processLoginNew(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1, SalesforceConstants.LOGINUSER); 

    	Home home = new Home(driver, testCaseParam);
    	home.navigateToAppMenuPage("Screenings", testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
    	    	
		ScreeningsPage screeningPage = new ScreeningsPage(driver, testCaseParam);   	 		
		screeningPage.navigateToListView("Screenings", "Approved Screenings", testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		screeningPage.checkStatusOnScreeningRecord(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		screeningPage.navigateToListView("Screenings", "Current Screenings", testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		screeningPage.navigateToLatestRecord(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);

		ScreeningDetails screeningDetails = new ScreeningDetails(driver, testCaseParam);
		screeningDetails.clickNewScreening(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		screeningDetails.enterScreeningDetails(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		screeningDetails.submitScreeningDetails(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		
		ScreeningPerson screeningPerson= new ScreeningPerson(driver, testCaseParam);
		screeningPerson.navigateToScreeningPersons(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		screeningPerson.addScreeningPerson(testCaseParam, scriptIteration , SalesforceConstants.POMITERATION1);
		screeningPerson.addScreeningPerson(testCaseParam, scriptIteration , SalesforceConstants.POMITERATION2);
		screeningPerson.addScreeningPerson(testCaseParam, scriptIteration , SalesforceConstants.POMITERATION3);
		screeningPerson.addScreeningPerson(testCaseParam, scriptIteration , SalesforceConstants.POMITERATION4);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		
		ScreeningAddress screeningAddress = new ScreeningAddress(driver, testCaseParam);
		screeningAddress.navigateToScreeningAddresses(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		screeningAddress.addScreeningAddress(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		CallBackAttempts callBackAttempts = new CallBackAttempts(driver, testCaseParam);
		callBackAttempts.navigateToScrCallBackAttempts(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		callBackAttempts.addCallBackAttemptsInfo(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		Allegations allegations = new Allegations(driver, testCaseParam);
		allegations.navigateToScreeningAllegations(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
		allegations.addAllegationsInfo(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
   	    SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		ScreeningSafetyAlert screeningSafetyAlert = new ScreeningSafetyAlert(driver, testCaseParam);
		screeningSafetyAlert.navigateToScreeningSafetyAlert(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		screeningSafetyAlert.addScreeningSafetyAlert(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		//Tab is available but new button is not available
		//ScreeningAssessments screeningAssessments = new ScreeningAssessments(driver, testCaseParam);
		//screeningAssessments.navigateToAssessments(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		//screeningAssessments.createNewAssessments(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		//SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);
	
		ScreeningTribalInquiry tribalInquiryAndCollaboration = new ScreeningTribalInquiry(driver, testCaseParam);
		tribalInquiryAndCollaboration.navigateToScreeningContactLogs(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
    	tribalInquiryAndCollaboration.addTribalInquiryAndCollaborationInfo(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
			
		Documents doucments = new Documents(driver, testCaseParam);
		doucments.documentSection(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		doucments.uploadDocument(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);
		
		tribalInquiryAndCollaboration = new ScreeningTribalInquiry(driver, testCaseParam);
		tribalInquiryAndCollaboration.navigateToScreeningContactLogs(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION2);
		tribalInquiryAndCollaboration.addTribalInquiryAndCollaborationInfo(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION2);
			
		doucments = new Documents(driver, testCaseParam);
    	doucments.documentSection(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		doucments.uploadDocument(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);

		ScreeningContactLog screeningContact = new ScreeningContactLog(driver, testCaseParam);
		screeningContact.navigateToScreeningContactLogs(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		screeningContact.addScreeningContactLog(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
			
		doucments = new Documents(driver, testCaseParam);
		doucments.documentSection(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		doucments.uploadDocument(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);
		
		ERRDoc errDoc= new ERRDoc(driver, testCaseParam);
    	errDoc.generateERRDoc(testCaseParam,scriptIteration, SalesforceConstants.POMITERATION1);

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
		

		ApprovalandAuditHistory approvalandAuditHistory = new ApprovalandAuditHistory(driver, testCaseParam);
		approvalandAuditHistory.navigateToApprovalAuditHistory(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		approvalandAuditHistory.verifyHeaderColumnsInTable("Screening History", testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);

		SupervisorReviewPromotion review = new SupervisorReviewPromotion(driver, testCaseParam);
		review.navigateToSupervisorReviewPromotion(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
		review.addSupervisorReviewPromotionInfo(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);

		SubmitForApproval approval= new SubmitForApproval (driver,testCaseParam);
		approval.clickSubmitForApprovalInfo(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1);
    
    	RecallAndResubmitRecord recall= new RecallAndResubmitRecord(driver,testCaseParam);
		recall.clickRecallAndResubmitRecordInfo(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);

    	Logout objLogout = new Logout(driver, testCaseParam);
    	objLogout.processLogout(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1); 
    	
    	login = new Login(driver, testCaseParam);
    	login.processLoginNew(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION1, SalesforceConstants.HLSUPERVISOR);
    	
    	home = new Home(driver, testCaseParam);
    	home.navigateToAppMenuPage("Screenings", testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
    	home.searchRecordMethod(testCaseParam,scriptIteration, SalesforceConstants.POMITERATION1);
    	
    	screeningDetails.navigateToScreeningDetails(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION2);
		screeningDetails.editCallerType(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION2);
		screeningDetails.submitScreeningDetails(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION2);
		SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);
	
    	home = new Home(driver, testCaseParam);
    	home.closeAllTabs();
    	home.navigateToAppMenuPage("Screenings", testCaseParam, scriptIteration, SalesforceConstants.POMITERATION1);
    	    	
       	screeningDetails= new ScreeningDetails(driver, testCaseParam);
    	screeningDetails.clickNewScreening(testCaseParam,scriptIteration,SalesforceConstants.POMITERATION3);
    	screeningDetails.enterScreeningDetails(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION3);
    	screeningDetails.submitScreeningDetails(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION3);
 
    	doucments= new Documents(driver, testCaseParam);
    	doucments.navigateToDocuments(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION3);
     	doucments.uploadDocument(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION3);  	
     	SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.SCR);
     	
     	doucments.navigateToDocuments(testCaseParam, scriptIteration,SalesforceConstants.POMITERATION3);
     	doucments.downloadDocument(testCaseParam, scriptIteration, SalesforceConstants.POMITERATION3);

		objLogout = new Logout(driver, testCaseParam);
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
