package waits;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ExplicitWaitExample {
	@Test
	public void explicitWaitTest() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.w3schools.com/ajax/tryit.asp?filename=tryajax_database");
		WebDriverWait wait = new WebDriverWait(driver, 5);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.id("iframeResult"))));
		
		//WebElement selectElement = driver.findElement(By.xpath("html/body/form/select"));
		WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[name='customers']")));
		
		Select selectFromDropdown = new Select(selectElement);
		selectFromDropdown.selectByVisibleText("North/South");
		
		
		//WebElement tableElement = driver.findElement(By.xpath(".//*[@id='txtHint']/table"));
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement tableElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='txtHint']/table")));
		System.out.println("done");

		
		
		
	}
}
