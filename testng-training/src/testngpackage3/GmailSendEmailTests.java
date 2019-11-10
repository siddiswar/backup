package testngpackage3;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailSendEmailTests {

	
	

	@Test
	public void sendEmail() {
		System.out.println("I am send email test");
		System.out.println("Thread ID :" + Thread.currentThread().getId()); 
		
	}
	
	@Test
	public void sendAllEmail() {
		System.out.println("I am send All email test");
		System.out.println("Thread ID :" + Thread.currentThread().getId()); 

	}
	

}