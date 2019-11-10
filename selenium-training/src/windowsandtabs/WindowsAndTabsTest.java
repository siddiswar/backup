package windowsandtabs;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WindowsAndTabsTest {
	@Test(enabled=false)
	public void windowAndTabTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://www.seleniumframework.com/Practiceform/");

		System.out.println("Before Click :" + webDriver.getWindowHandles().size());

		String parentWindowHandle = webDriver.getWindowHandle();
		System.out.println(webDriver.getCurrentUrl());

		// WebElement newBrowserButtonElement =
		// webDriver.findElement(By.id("button1"));
		WebElement newBrowserButtonElement = webDriver.findElement(By.cssSelector("button[onclick='newBrwTab()']"));

		newBrowserButtonElement.click();

		Thread.sleep(3000);
		System.out.println("===============After click");
		// After click
		Set<String> allWindowHandles = webDriver.getWindowHandles();

		System.out.println("After Click :" + allWindowHandles.size());

		// switch to new window

		for (String singleWindowHandle : allWindowHandles) {
			if (singleWindowHandle.equals(parentWindowHandle)) {
				// do nothing
			} else {
				webDriver.switchTo().window(singleWindowHandle);
			}
		}

		System.out.println(webDriver.getCurrentUrl());
		// do all actions you want to do in child window

		// Switch back to parent window.
		webDriver.close();
		// webDriver.quit();
		System.out.println("=====Switching back to parent window");
		webDriver.switchTo().window(parentWindowHandle);
		System.out.println(webDriver.getCurrentUrl());

		webDriver.quit();

	}
	@Test
	public void alertTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://www.seleniumframework.com/Practiceform/");

		WebElement alertElement = webDriver.findElement(By.id("alert"));
		
		alertElement.click();
		Thread.sleep(2000);
		
		
		Alert alert1 = webDriver.switchTo().alert();
		System.out.println(alert1.getText());
		alert1.accept();
		//alert1.dismiss();
		
		WebElement newBrowserButtonElement = webDriver.findElement(By.cssSelector("button[onclick='newBrwTab()']"));

		newBrowserButtonElement.click();
	}

}
