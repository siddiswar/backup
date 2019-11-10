package testngpackage2;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailChatTests {

	@BeforeTest
	public void befTest() {
		System.out.println("I am before test of chat tests");
		System.out.println("==================================================");
	}

	@AfterTest
	public void aftTest() {
		System.out.println("I am after test of chat tests");
		System.out.println("==================================================");
	}

	@BeforeClass
	public void befClass() {
		System.out.println("I am before class of GmailChatTests");
		System.out.println("======================================");

	}

	@AfterClass
	public void aftClass() {
		System.out.println("I am after class of GmailChatTests");
		System.out.println("======================================");

	}

	@Test
	public void doChat() {
		System.out.println("I am do chat method");

	}

	@Test
	public void sendChatInvitation() {
		System.out.println("I am sendChatInvitation  method");

	}

	@Test
	public void doGroupChat() {
		System.out.println("I am do group chat test  method");

	}
}
