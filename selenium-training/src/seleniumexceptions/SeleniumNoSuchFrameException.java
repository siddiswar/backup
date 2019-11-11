package seleniumexceptions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Siddu
 */
public class SeleniumNoSuchFrameException {
	public static void main(String[] args) throws InterruptedException {
		// when we try to switch to a non existent frame
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://www.seleniumframework.com/Practiceform/");
		Thread.sleep(5000);

		webDriver.switchTo().frame("nonexistantframename");
	}
}
