package seleniumexceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Siddu
 */
public class SeleniumElementNotVisibleException {
	public static void main(String[] args) throws InterruptedException {
		// incomplete
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://www.seleniumframework.com/Practiceform/");
		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) webDriver;

		String pageTitle = js.executeScript("return document.title").toString();
		System.out.println(pageTitle);

		//js.executeScript("document.getElementById(\"button1\").style.display = \"none\"");
		js.executeScript("document.getElementById(\"button1\").disabled = true");

		WebElement newBrowserButtonElement = webDriver.findElement(By.id("button1"));

		newBrowserButtonElement.click();
	}
}
