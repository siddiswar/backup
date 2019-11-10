package testngpackage2;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailLoginTests {

	@BeforeSuite
	public void befSuite(){
		System.out.println("I am before suite"); 
		System.out.println("=================================================================");

	}
	
	@AfterSuite
	public void aftSuite(){
		System.out.println("I am after suite"); 
		System.out.println("=================================================================");

	}
	
	
	@BeforeTest
	public void befTest(){
		System.out.println("I am before test of login tests");
		System.out.println("==================================================");
	}
	
	@AfterTest
	public void aftTest(){
		System.out.println("I am after test of login tests");
		System.out.println("==================================================");
	}
	
	
	@BeforeClass
	public void befClass(){
		System.out.println("I am before class of GmailLoginTests");
		System.out.println("======================================");

	}
	
	@AfterClass
	public void aftClass(){
		System.out.println("I am after class of GmailLoginTests");
		System.out.println("======================================");

	}
	
/*	@BeforeMethod
	public void befMethod(){
		System.out.println("I am before method");
		//open browser
		//open www.google.com/login
		//database connections
	}
	
	
	@AfterMethod
	public void aftMethod(){
		System.out.println("I am after method");
		System.out.println("-----------------------");
		//close the browser
		//close connections
	}
	*/
	
	@Test
	public void doLogin(){
		System.out.println("I am doLgin method..");
		String actualValue="something";
		String expectedValue = "something";
		Assert.assertEquals(actualValue, expectedValue);
	}
	
	@Test
	public void doSignup(){
		System.out.println("I am dosignup method..");
		String actualValue="something";
		String expectedValue = "something";
		Assert.assertEquals(actualValue, expectedValue);
	}
	
	@Test
	public void doForgotPassword(){
		System.out.println("I am doForgotPassword method..");
		String actualValue="something";
		String expectedValue = "something";
		Assert.assertEquals(actualValue, expectedValue);
	}
	
	@Test
	public void doResetPassword(){
		System.out.println("I am doResetPassword method..");
		String actualValue="something";
		String expectedValue = "something";
		Assert.assertEquals(actualValue, expectedValue);
	}
	
	
}
