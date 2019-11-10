package javascriptexecutor;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class JavascriptExample {
	@Test
	public void javascriptTest() throws IOException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");

		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String jsString1 = "window.scrollBy(300,300)";
		js.executeScript(jsString1);
		

		String pageTitle = js.executeScript("return document.title").toString();
		System.out.println(pageTitle);
		
	}
}
