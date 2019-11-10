package actionspackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class MouseActions {

	
	@Test(enabled=false)
	public void moveToExample(){
		
		
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe");

		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();

		
		driver.get("http://www.seleniumframework.com/demo-sites/");
		
		WebElement titorialElement = driver.findElement(By.xpath(".//*[@id='main-nav']/li[2]/a/span"));
		
		Actions actions = new Actions(driver);
		
		actions.moveToElement(titorialElement).build().perform();
		
		WebElement rubyElement = driver.findElement(By.xpath(".//*[@id='main-nav']/li[2]/ul/li[2]/a/span"));
		
		actions.moveToElement(rubyElement).build().perform();
		
		System.out.println("Done");
		
		
		
	}
	
	@Test(enabled=false)
	public void dragAndDropExample(){
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe");
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		driver.get("http://demoqa.com/droppable/");
		Actions actions = new Actions(driver);

		WebElement sourceElement = driver.findElement(By.xpath(".//*[@id='draggableview']"));
		WebElement targetElement = driver.findElement(By.xpath(".//*[@id='droppableview']"));
		
		actions.dragAndDrop(sourceElement, targetElement).build().perform();

		System.out.println("Done");
			
	}
	
	@Test(enabled=false)
	public void dragAndDropExample2(){
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demoqa.com/droppable/");
		Actions actions = new Actions(driver);

		WebElement sourceElement = driver.findElement(By.xpath(".//*[@id='draggableview']"));
		WebElement targetElement = driver.findElement(By.xpath(".//*[@id='droppableview']"));
		
		//actions.dragAndDrop(sourceElement, targetElement).build().perform();
		
		actions.clickAndHold(sourceElement).moveToElement(targetElement).release().build().perform();

		System.out.println("Done");
			
	}
	
	@Test
	public void contextClickExample2() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demoqa.com/tooltip/");
		Actions actions = new Actions(driver);

		WebElement contextElement = driver.findElement(By.xpath(".//*[@id='ui-id-1']"));
		
		actions.contextClick(contextElement).build().perform();
		Thread.sleep(4000);
		
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();


		System.out.println("Done");
	
	}
	
	
	
}
