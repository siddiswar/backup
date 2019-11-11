package seleniumexceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Siddu
 */
public class SeleniumNoSuchElementException {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {

		
		//when element is not found in page
		//i used a wrong element locator to reproduce this
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://www.seleniumframework.com/Practiceform/");
		WebElement newBrowserButtonElement = webDriver.findElement(By.cssSelector("wrongbutton[onclick='newBrwTab()']"));

		newBrowserButtonElement.click();
	}

}
