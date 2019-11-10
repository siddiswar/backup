package testngpackage2;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailReplyEmailTests {
	
	//@Test is represents a test method and not test
	//test is represented by <test> tag in testng.xml
	
	@BeforeClass
	public void befClass(){
		System.out.println("I am before class of GmailReplyEmailTests");
		System.out.println("======================================");

	}
	
	@AfterClass
	public void aftClass(){
		System.out.println("I am after class of GmailReplyEmailTests");
		System.out.println("======================================");
	}
	
	@Test
	public void replyEmail(){
		System.out.println("I am reply email test");
	}
	
	
	@Test
	public void replyAllEmail(){
		System.out.println("I am reply all email test");
	}
}
