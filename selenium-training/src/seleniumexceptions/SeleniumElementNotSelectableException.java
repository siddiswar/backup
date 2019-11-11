package seleniumexceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Siddu
 */
public class SeleniumElementNotSelectableException {
	public static void main(String[] args) throws InterruptedException {
		// incomplete
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://www.seleniumframework.com/Practiceform/");
		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		WebElement newBrowserButtonElement = webDriver.findElement(By.id("button1"));

		
		Select select = new Select(newBrowserButtonElement);


		newBrowserButtonElement.click();
	}
}
