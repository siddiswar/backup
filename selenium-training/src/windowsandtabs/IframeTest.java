package windowsandtabs;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class IframeTest {

	@Test
	public void iframeTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://toolsqa.com/iframe-practice-page/");

		//Switch to iframe
		WebElement iframeElement = webDriver.findElement(By.id("IF2"));
		webDriver.switchTo().frame(iframeElement);
		
		//Do steps in iframe
		
		WebElement imageElement = webDriver.findElement(By.xpath(".//*[@id='post-9']/div/div[1]/div/p[1]/a/img"));
		
		imageElement.click();
		
		//switch back to parent window
		
		webDriver.switchTo().defaultContent();
		
		
		
	}

	
}
