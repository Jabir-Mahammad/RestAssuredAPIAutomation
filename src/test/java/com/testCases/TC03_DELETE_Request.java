package com.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.utilities.ExcelUtility;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC03_DELETE_Request extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException, IOException {
		
	//	logger.info("---------------TC01_Get_All_Employees-----------------");
		
	
	
	/*
	 * RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1"; httpReq
	 * =RestAssured.given(); response =
	 * httpReq.request(Method.GET,"/employee/51838"); Thread.sleep(3000);
	 */
	 
      Object endPoint = ExcelUtility.getCell(dataSheet, sheetName, 1, 2);
	  System.out.println(endPoint); 
	  Object baseUri =ExcelUtility.getCell(dataSheet, sheetName, 1, 3);
	  System.out.println(baseUri); 
	  RestAssured.baseURI = endPoint.toString();
	  httpReq=RestAssured.given(); 
	  response = httpReq.request(Method.GET,baseUri.toString());
	  
	  //Deleting single  record
	  JsonPath js = response.jsonPath();
	  String emplid = js.get("[0].id");
	  Object uri = ExcelUtility.getCell(dataSheet, sheetName, 4, 3);
	  response = httpReq.request(Method.DELETE,uri.toString()+emplid);
	  
	  Thread.sleep(3000);
	 
	 
	}
	
	
	@Test
	void checkResponseBody() {
		
		logger.info("-------Checing the Response Body------");
		String resBody = response.getBody().asString();
		logger.info("Response Body=> "+resBody);
		Assert.assertTrue(resBody!=null);
		
		
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
		
		logger.info("---------------------------End TC01_Get_All_Employees-----------------");
	}

	
}
