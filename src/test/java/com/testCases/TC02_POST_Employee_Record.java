package com.testCases;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.utilities.ExcelUtility;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC02_POST_Employee_Record extends TestBase{
	
	String dataSheet = "C:\\Users\\mahammad.jabir.s.i\\eclipse-workspace\\RestAssuredAPIAutomation\\TestDataSheet.xlsx";
	String sheetName="DataSheet";

	@BeforeClass
	public void createEmployee() throws IOException, InterruptedException {
		
		  Object endPoint =  ExcelUtility.getCell(dataSheet, sheetName, 2, 2);
		  System.out.println(endPoint); 
		  Object baseUri = ExcelUtility.getCell(dataSheet, sheetName, 2, 3);
		  System.out.println(baseUri); 
		  Object name =  ExcelUtility.getCell(dataSheet, sheetName, 2, 4);
		  System.out.println(name); 
		  Object sal = ExcelUtility.getCell(dataSheet, sheetName, 2, 5);
		  System.out.println(sal); 
		  Object age = ExcelUtility.getCell(dataSheet, sheetName, 2, 6);
		  System.out.println(age); 
		  
		  RestAssured.baseURI = endPoint.toString();
		  httpReq=RestAssured.given(); 
		  
		  JSONObject jsOb = new JSONObject();
		  jsOb.put("name", name);
		  jsOb.put("salary", sal);
		  jsOb.put("age", age);
		  
		  httpReq.header("Content-Type","application/json");
		  httpReq.body(jsOb.toJSONString());
		  
		  response = httpReq.request(Method.POST,baseUri.toString());
		  Thread.sleep(3000);
	}
	
	@Test
	void testResponseBody() {
		
		logger.info("-------Checing the Response Body------");
		String resBody = response.getBody().asString();
		logger.info("Response Body=> "+resBody);
		Assert.assertTrue(resBody!=null);
		Assert.assertEquals(response.jsonPath().get(""), "Successfully! Record has been added.");
		
		
	}
	
	@Test
	void checkStatusCode() {
		
		int statusCode = response.getStatusCode();
		logger.info("Status code=>"+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime() {
		
		long time = response.getTime();
		logger.info("Response Time is=>"+time);
		
		if(time > 2000)
			logger.warn("Response time is greater than 2000");
		Assert.assertTrue(time<=2000);
	}
	
	@Test
	void checkStatusLine() {
		
		String statusLine = response.getStatusLine();
		logger.info("Status code=>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
		
		String contentType = response.header("Content-Type");
		logger.info("Content Type is=>"+contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test
	void checkServerType() {
		
		String serverType = response.header("Server");
		logger.info("Content Type is=>"+serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1");
	}
	
	@AfterClass
	void tearDown() {
		
		logger.info("---------------------------End TC02_create_employee-----------------");
	}

	
}
