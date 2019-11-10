package screenshot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScreenshotExample {
	@Test
	public void screenshotTest() throws IOException {

		String expectedTitle = "Forgotten Password | Can't Log In | Facebook";
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");

		try {
			WebElement forgotAccountElement = driver
					.findElement(By.xpath(".//*[@id='losgin_form']/table/tbody/tr[3]/td[2]/a"));
			forgotAccountElement.click();
			String actualtitle = driver.getTitle();

			Assert.assertEquals(expectedTitle, actualtitle);
		} catch (Exception e) {
			TakesScreenshot takeScreenshotObj = (TakesScreenshot) driver;
			File screenshotFile = takeScreenshotObj.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File("screenshots/forgotAccountLink.jpg"));
		}

	}
}
