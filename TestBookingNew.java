package api_tests.testng.booking;

import api_tests.testng.common.APITestNGCommon;
import APIUtilities.APIHelpers.TestCaseHelper;
import APIUtilities.TestSettings.APITestSettings;
import org.testng.annotations.Test;

public class TestBookingNew extends APITestNGCommon {


	 @Test(groups = { "Booking" })
	    public void createToken() throws Exception
	    {
		 
		 TestCaseHelper testCaseHelper= new TestCaseHelper();
		 
		 testCaseHelper.getTestCaseDetails(APITestSettings.apiTestSettings.APITestSuiteFileName, APITestSettings.apiTestSettings.APITestSuiteSheetName);
		 
		 testCaseHelper.ExecuteTestCases();
		 
	    }


}
