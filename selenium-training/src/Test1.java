import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class Test1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();

		webDriver.get("https://www.google.co.in/");
		Thread.sleep(3000);
		webDriver.findElement(By.xpath(".//*[@id='gs_htif0']")).sendKeys("sachin");
		
		webDriver.findElement(By.xpath(".//*[@id='sbtc']/div[2]/div[2]/div[1]"));
		System.out.println("done");
		
		
		FluentWait wait = new FluentWait<WebDriver>(webDriver);
		
		
	}

}
