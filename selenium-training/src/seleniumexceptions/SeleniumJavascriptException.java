package seleniumexceptions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Siddu
 */
public class SeleniumJavascriptException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//When incorrect javascript is executed
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");

		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String jsString1 = "dasdadsawindow.scrollBy(300,300)";
		js.executeScript(jsString1);
		

		String pageTitle = js.executeScript("return document.title").toString();
		System.out.println(pageTitle);

	}

}
