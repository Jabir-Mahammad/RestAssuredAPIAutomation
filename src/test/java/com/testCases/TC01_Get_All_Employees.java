package com.testCases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.ExcelUtility;

import io.restassured.RestAssured;
import io.restassured.http.Method;


public class TC01_Get_All_Employees extends TestBase{
	ITestResult res;
	
	@BeforeTest
	void getAllEmployees() throws InterruptedException, IOException {
	 
      Object endPoint = ExcelUtility.getCell(dataSheet, sheetName, 1, 2);
	  System.out.println(endPoint); 
	  Object baseUri =ExcelUtility.getCell(dataSheet, sheetName, 1, 3);
	  System.out.println(baseUri); 
	  RestAssured.baseURI = endPoint.toString();
	  httpReq=RestAssured.given(); 
	  response = httpReq.request(Method.GET,baseUri.toString());
	  Thread.sleep(3000);
	 
	 
	}
	
	@BeforeMethod
	public void beforeMethod() {
		
	//	extentTest= extent.startTest("");
	}
	
	@Test
	void checkResponseBody() {
		extentTest= extent.startTest("checkResponseBody");
		logger.info("-------Checing the Response Body------");
		String resBody = response.getBody().asString();
		logger.info("Response Body=> "+resBody);
		Assert.assertTrue(resBody!=null);
		extentTest.log(LogStatus.PASS, "Test Case is passed");
	}
	
	@Test
	void checkStatusCode() {
		extentTest= extent.startTest("checkStatusCode");
		int statusCode = response.getStatusCode();
		logger.info("Status code=>"+statusCode);
		Assert.assertEquals(statusCode, 200);
		extentTest.log(LogStatus.PASS, "Test Case is passed");
	}
	
	@Test
	void checkResponseTime() {
		extentTest= extent.startTest("checkResponseTime");
		long time = response.getTime();
		logger.info("Response Time is=>"+time);
		
		if(time > 2000)
			logger.warn("Response time is greater than 2000");
		Assert.assertTrue(time<=2000);
		extentTest.log(LogStatus.PASS, "Test Case is passed");
	}
	
	@Test
	void checkStatusLine() {
		extentTest= extent.startTest("checkStatusLine");
		String statusLine = response.getStatusLine();
		logger.info("Status code=>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		extentTest.log(LogStatus.PASS, "Test Case is passed");
	}
	
	@Test
	void checkContentType() {
		extentTest= extent.startTest("checkContentType");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is=>"+contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		extentTest.log(LogStatus.PASS, "Test Case is passed");
	}
	
	@Test
	void checkServerType() {
		extentTest= extent.startTest("checkServerType");
		String serverType = response.header("Server");
		logger.info("Content Type is=>"+serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1");
		extentTest.log(LogStatus.PASS, "Test Case is passed");
	}
	
	@AfterMethod
	void afterMethod(ITestResult res) {
		
		if(res.getStatus()==res.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test Case is filed"+res.getName());
			extentTest.log(LogStatus.FAIL, "Test Case is filed"+res.getThrowable());
		}
		
		else if(res.getStatus()==ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Tes case is skipped"+res.getName());
		}
		
		extent.endTest(extentTest); 
	}
	
	@AfterTest
	void tearDown() {
		
		extent.flush();
		extent.close();
		logger.info("---------------------------End TC01_Get_All_Employees-----------------");
	}
}
