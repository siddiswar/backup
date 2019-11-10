package testngpackage3;

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

	@Test(groups = { "group1" })
	public void doLogin() {
		System.out.println("I am doLgin method..");
		String actualValue = "something";
		String expectedValue = "something";
		Assert.assertEquals(actualValue, expectedValue);
		System.out.println("Thread ID :" + Thread.currentThread().getId()); 

	}

	@Test(groups = { "group1" })
	public void doSignup() {
		System.out.println("I am dosignup method..");
		String actualValue = "something";
		String expectedValue = "something";
		Assert.assertEquals(actualValue, expectedValue);
		Assert.assertEquals(true, false);
		System.out.println("Thread ID :" + Thread.currentThread().getId()); 

	}

	@Test(groups = { "group2" })
	public void doForgotPassword() {
		System.out.println("I am doForgotPassword method..");
		String actualValue = "something";
		String expectedValue = "something";
		Assert.assertEquals(actualValue, expectedValue);
		System.out.println("Thread ID :" + Thread.currentThread().getId()); 

	}

	@Test(groups = { "group2" })
	public void doResetPassword() {
		System.out.println("I am doResetPassword method..");
		String actualValue = "something";
		String expectedValue = "something";
		Assert.assertEquals(actualValue, expectedValue);
		System.out.println("Thread ID :" + Thread.currentThread().getId()); 

	}

}
