package webdriverintro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstSeleniumExampleUsingTestNG {

	@Test
	public void myTest() {
		
		String expectedTitle ="Google";
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://www.google.com");
		
		
		String actualTitle=webDriver.getTitle();
		
		Assert.assertEquals(actualTitle, expectedTitle);
		
		webDriver.quit();

	}

}
