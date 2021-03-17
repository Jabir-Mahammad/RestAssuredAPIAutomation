package com.base;



import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TestBase {
	
	public static RequestSpecification httpReq;
	public static Response response;
	public String dataSheet = "C:\\Users\\mahammad.jabir.s.i\\eclipse-workspace\\RestAssuredAPIAutomation\\TestDataSheet.xlsx";
	public String sheetName="DataSheet";
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	public Logger logger;
	
	
	public TestBase() {
		
		//Log$j
		logger = Logger.getLogger("RestAPI_Automation_Logs");
		PropertyConfigurator.configure("log4j.properties");
		
		//Extent Report
		extent  = new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentReport.html");
		extent.addSystemInfo("Host", "Windows");
		extent.addSystemInfo("User Name", "Jabir");
		
	}
	

}
