package elementinteractions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ElementIntrExample {

	@Test(enabled=false)
	public void textInteractionTest() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get("http://www.facebook.com");
		
		WebElement emailElement = webDriver.findElement(By.id("email"));
		
		emailElement.sendKeys("xyz@gmail.com");
		
		Thread.sleep(5000);
		
		emailElement.clear();
		
		webDriver.quit();
		
	}

	
	@Test(enabled=false)
	public void buttonInteractionTest() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get("http://www.facebook.com");
		
		WebElement loginButtonElement = webDriver.findElement(By.id("u_0_l"));
		
		loginButtonElement.click();
		
		Thread.sleep(5000);
				
		webDriver.quit();
		
	}

	
	@Test(enabled=false)
	public void dropdownInteractionTest() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get("http://www.facebook.com");
		
		WebElement monthDropdownElement = webDriver.findElement(By.id("month"));
		
		Select monthSelection = new Select(monthDropdownElement);
		
		monthSelection.selectByIndex(2);
		
		Thread.sleep(5000);
		
		monthSelection.selectByValue("6");
		Thread.sleep(5000);

		monthSelection.selectByVisibleText("Dec");

	}
	
	@Test(enabled=false)
	public void elementStateTest() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get("http://www.facebook.com");
		
		WebElement femaleRafdioButtonElement = webDriver.findElement(By.id("u_0_d"));
		//visible in the page
		System.out.println(femaleRafdioButtonElement.isDisplayed());
		
		//whether element is editable
		System.out.println(femaleRafdioButtonElement.isEnabled());
		
		//Whether element is selected . radio buttons,check boxes
		System.out.println(femaleRafdioButtonElement.isSelected());


		
		femaleRafdioButtonElement.click();
		
		System.out.println("=======================After click================");
		
		System.out.println(femaleRafdioButtonElement.isDisplayed());
		System.out.println(femaleRafdioButtonElement.isEnabled());
		System.out.println(femaleRafdioButtonElement.isSelected());
		
		Thread.sleep(5000);
				
		webDriver.quit();

	}
	@Test
	public void elementStateTest2() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get("http://www.facebook.com");
		
		Thread.sleep(5000);
		
		WebElement hiddenElement = webDriver.findElement(By.name("lsd"));
		//visibility in the page. if hidden then isDisplayed() returns false
		System.out.println(hiddenElement.isDisplayed());
		
/*		//whether element is editable
		System.out.println(hiddenElement.isEnabled());
		
		//Whether element is selected . radio buttons,check boxes
		System.out.println(hiddenElement.isSelected());
		*/
		Thread.sleep(5000);
				
		webDriver.quit();

	}
	
	

	
}
