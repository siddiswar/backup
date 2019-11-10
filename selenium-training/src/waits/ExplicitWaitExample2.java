package waits;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ExplicitWaitExample2 {
	@Test(enabled=false)
	public void explicitWaitTest() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.seleniumframework.com/Practiceform/");

		WebElement timingAlertButton = driver.findElement(By.id("timingAlert"));

		timingAlertButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 5);

/*		FluentWait wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);*/
		
	

		wait.until(ExpectedConditions.alertIsPresent());

		Alert delayedAlert = driver.switchTo().alert();

		System.out.println(delayedAlert.getText());

		delayedAlert.accept();

		System.out.println("continue with next steps..");

	}
	
	@Test
	public void autoSuggestionTest() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.abhibus.com/");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		WebElement sourceCity = driver.findElement(By.xpath(".//*[@id='source']"));
		
		sourceCity.sendKeys("an");
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/ul[1]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/ul[1]")));
		
		driver.findElement(By.xpath("html/body/ul[1]/li[2]")).click();
		
		System.out.println("done...");

		
		
		
		
	}
}
