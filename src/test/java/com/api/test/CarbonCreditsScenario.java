package com.api.test;

import com.api.utils.driverScript;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class CarbonCreditsScenario extends driverScript
{
	 private static String SERVICE_ENDPOINT_URL = readFrameworkProps("baseURL") + "v1/Categories/6327/Details.json?catalogue=false";
	 
	
	 	@Test
	    public void validateElementsOfCarbonCreditsApi() throws Exception {
		 	
		 	//For extent report
		 	ExtentTest test = reports.startTest("GET Carbon Credit Test");

	        // Logs the response in case of the failures
	        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

	        // Get response by sending request to endpoint
	        Response response = getRequestToEndpointUrlToFetchResponse(SERVICE_ENDPOINT_URL);
	        logger.info("response : "+response.print());
	        
	        // Asserting to verify the successful response
	        response.then().assertThat().statusCode(200);
	        customReportingExactMatch(test,"200", Integer.toString(response.getStatusCode()),"Verify Status Code");

	        // Get the JsonPath to do further validations
	        JsonPath jsonPathEval = response.jsonPath();

	        // Validation - Name as Carbon Credits
	        customReportingExactMatch(test,"Carbon credits", jsonPathEval.get("Name").toString(),"Verify Name");

	        // Validation - CanRelist as true
	        customReportingExactMatch(test,"true", Boolean.toString(jsonPathEval.getBoolean("CanRelist")),"Verify Can Relist");

	        // Validation - Gallery promotion description us validated of Gallery Promotion
	        List<String> names = jsonPathEval.getList("Promotions.Name");

	        String galleryDescription = null;
	        for (int i = 0; i < names.size(); i++) {
	            if ("Gallery".equals(names.get(i))) {
	                galleryDescription = jsonPathEval.getList("Promotions.Description").get(i).toString();
	                break;
	            }
	        }
	        customReportingContains(test,galleryDescription, "2x larger image","Gallery description should contain the expected description");
	        
	        //Finish report for this test
	        reports.endTest(test);
	        

	    }


}
