package testngpackage2;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailSendEmailTests {

	
	
	@BeforeTest
	public void befTest(){
		System.out.println("I am before test of email tests");
		System.out.println("==================================================");
	}
	
	@AfterTest
	public void aftTest(){
		System.out.println("I am after test of email tests");
		System.out.println("==================================================");
	}
	
	
	@BeforeClass
	public void befClass(){
		System.out.println("I am before class of GmailSendEmailTests");
		System.out.println("======================================");

	}
	
	@AfterClass
	public void aftClass(){
		System.out.println("I am after class of GmailSendEmailTests");
		System.out.println("======================================");

	}
	@Test
	public void sendEmail() {
		System.out.println("I am send email test");
	}
	
	@Test
	public void sendAllEmail() {
		System.out.println("I am send All email test");
	}
	

}