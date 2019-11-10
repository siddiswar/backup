package testngpackage3;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailReplyEmailTests {
	
	//@Test is represents a test method and not test
	//test is represented by <test> tag in testng.xml
	
	
	
	@Test
	public void replyEmail(){
		System.out.println("I am reply email test");
		System.out.println("Thread ID :" + Thread.currentThread().getId());
		Reporter.log("I am reply email test using Reporter class");
		Reporter.log("I am reply email test using Reporter class both console and reporter", true);
		
	}
	
	
	@Test
	public void replyAllEmail(){
		System.out.println("I am reply all email test");
		System.out.println("Thread ID :" + Thread.currentThread().getId());
		Reporter.log("I am reply  all email test using Reporter class");


	}
}
