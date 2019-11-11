package seleniumexceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Siddu
 */
public class SeleniumStaleElementReferenceException {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		//when element is first located, then page got refreshed and the some actions are done on the element
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://www.seleniumframework.com/Practiceform/");
		Thread.sleep(5000);
		WebElement newBrowserButtonElement = webDriver.findElement(By.cssSelector("button[onclick='newBrwTab()']"));
		
		webDriver.navigate().refresh();// on refresh previously located elements become stale

		newBrowserButtonElement.click();
	}

}
