package misc;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Siddu
 */
public class Test1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "conf/drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();
		webDriver.get("https://www.google.com/");

		webDriver.findElement(By.name("q")).sendKeys("test");
		Thread.sleep(2000);
		
		List<WebElement> sList = webDriver.findElements(By.xpath("//li[@class='sbct' and @jsaction='click:.CLIENT;mouseover:.CLIENT']")) ;
		System.out.println("size = "+sList.size());
		for(WebElement ele : sList){
			String text = ele.getText();
			System.out.println(text);
			if(text.contains("javanese")){
				ele.click();
				break;
			}
			
		}
		System.out.println("done");
	}

}
