package seleniumexceptions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Siddu
 */
public class SeleniumIllegalStateException {
	//When driver path is not set
	public static void main(String[] args) {
		// System.setProperty("webdriver.chrome.driver",
		// "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();
	}
}
