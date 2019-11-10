package testngpackage3;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailChatTests {

	
	@Test
	public void doChat() {
		System.out.println("I am do chat method");
		System.out.println("Thread ID :" + Thread.currentThread().getId()); 


	}

	@Test
	public void sendChatInvitation() {
		System.out.println("I am sendChatInvitation  method");
		System.out.println("Thread ID :" + Thread.currentThread().getId()); 


	}

	@Test
	public void doGroupChat() {
		System.out.println("I am do group chat test  method");
		System.out.println("Thread ID :" + Thread.currentThread().getId()); 


	}
}
