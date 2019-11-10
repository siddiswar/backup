package webdriverintro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyFirstSeleniumExample {

	public static void main(String[] args) {

		
		//Interface has all abstract methods => no method body
		//Interface has to be implemented by another class 
		
		//WebDriver  is interface
		//WebDriver is implemented by FirefoxDriver,ChromeDriver,IEDriver
		//System.setProperty("webdriver.chrome.driver", "C:/Users/advhyd-laptop04/workspace/selenium-training/drivers/chromedriver.exe");
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get("http://www.google.com");
		
		System.out.println(webDriver.getTitle());
		
		webDriver.quit();


	}

}
