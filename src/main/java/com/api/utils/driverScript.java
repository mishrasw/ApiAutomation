package com.api.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


/**
 * This class contains all common methods which can be used across all the test classes
 */

public class driverScript 
{
	
	//initiate extent reporter object
	protected static ExtentReports reports = new ExtentReports(readFrameworkProps("reportPath")+"\\DetailedReport.html");
	protected final static Logger logger = Logger.getLogger(driverScript.class);	
	static final String logpath = "log4j.properties";
	
	
	public driverScript(){
		PropertyConfigurator.configure("log4j.properties");
	}
	
	/**
     * To fetch the response from the endpoint by doing a getRequest
     *
     * @param url is the Endpoint URL to which the request is sent
     * @return response of the request
     */
	 public static Response getRequestToEndpointUrlToFetchResponse(String url) {
		 logger.info("Parsing URL - "+url);   
		 RestAssured.defaultParser = Parser.JSON;
	        return  given().headers("Content-Type", ContentType.JSON).
	                when().get(url).
	                then().contentType(ContentType.JSON).extract().response();
	    }
	 
	 /**
	  * To read value from framewor.properties file
	  * 
	  * @param propName is the property from framework.properties file
	  * @return value of the property name
	  */
	 public static String readFrameworkProps(String propName)
	 {
		 String propValue=null;
		 try{	
			 	InputStream input = new FileInputStream("framework.properties");
	            Properties prop = new Properties();
	            prop.load(input);
	            propValue= prop.get(propName).toString().trim();
	            //logger.info("Prop Name - "+propName+";Prop Value - "+propValue);
		 }catch(IOException e){			
			 e.printStackTrace();
		 }
		 return propValue;
	 }
	 /**
	  * <Summary> This method will compare expected vs actual value and report in HTML report
	  * 
	  * @param test - Extent report Object
	  * @param Expected - expected value to print<String>
	  * @param Actual - actual value <String>
	  * @param CustomMsg - Msg to display in Report
	  * @throws Exception
	  */
	 
	 public static void customReportingExactMatch(ExtentTest test, String Expected, String Actual, String CustomMsg) throws Exception{

		if(Expected.equals(Actual)){
		 test.log(LogStatus.PASS, CustomMsg + " - Expected : "+Expected+", Actual : "+Actual);
		 logger.info(CustomMsg+" :: Expected - "+Expected+"; Actual "+Actual); 
		} else{		 
		 test.log(LogStatus.FAIL, CustomMsg + " - Expected : "+Expected+", Actual : "+Actual); 
		 logger.error(CustomMsg+" :: Expected - "+Expected+"; Actual "+Actual);
		 reports.endTest(test);
		 reports.flush();
		 System.exit(1);
		}
		 
	 }
	 
	 /**
	  * <Summary> This method will compare expected vs actual value(expected contains actual value) and report in HTML report
	  * 
	  * @param test - Extent report Object
	  * @param Expected - expected value to print<String>
	  * @param Actual - actual value <String>
	  * @param CustomMsg - Msg to display in Report
	  * @throws Exception
	  */
	 public static void customReportingContains(ExtentTest test, String Expected, String Actual, String CustomMsg) throws Exception{
			if(Expected.contains(Actual)){
			 test.log(LogStatus.PASS, CustomMsg + " - Expected : "+Expected+", Actual : "+Actual);
			 logger.info(CustomMsg+" :: Expected - "+Expected+"; Actual "+Actual); 
			} 
			else{		 
			 test.log(LogStatus.FAIL, CustomMsg + " - Expected : "+Expected+", Actual : "+Actual); 
			 logger.error(CustomMsg+" :: Expected - "+Expected+"; Actual "+Actual);
			 reports.endTest(test);
			 reports.flush();
			 System.exit(1);
			}
			 
		 }
	 
	 /**
	  * Save and close HTML report
	  */
	 @AfterClass
	 public static void closeReport(){
		 reports.flush();
	 }


}
