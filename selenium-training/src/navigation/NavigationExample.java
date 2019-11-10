package navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NavigationExample {

	@Test
	public void navigationTest(){
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//driver.get("https://www.facebook.com/");
		driver.navigate().to("https://www.facebook.com/");
		
		WebElement linkElement = driver.findElement(By.xpath(".//*[@id='login_form']/table/tbody/tr[3]/td[2]/a"));
		linkElement.click();
		
		driver.navigate().back();
		
		driver.navigate().forward();
		
		driver.navigate().refresh();
	}

}
