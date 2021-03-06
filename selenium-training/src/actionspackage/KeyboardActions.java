package actionspackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class KeyboardActions {
	@Test
	public void dragAndDropExample2() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		Actions actions = new Actions(driver);

		WebElement sourceElement = driver.findElement(By.xpath("//*[@id='u_0_o']"));
		WebElement targetElement = driver.findElement(By.xpath("//*[@id='u_0_q']"));

		actions.sendKeys(sourceElement, "sachin").build().perform();

		actions.moveToElement(sourceElement).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();

		actions.moveToElement(sourceElement).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();

		actions.moveToElement(targetElement).click().keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build()
				.perform();

		System.out.println("Done");
		
		
		actions.sendKeys(sourceElement, "sehwag").build().perform();

		actions.moveToElement(sourceElement).click().sendKeys(Keys.CONTROL+"a").build().perform();

		actions.moveToElement(sourceElement).click().sendKeys(Keys.CONTROL+"c").build().perform();

		actions.moveToElement(targetElement).click().sendKeys(Keys.CONTROL+"V").build().perform();

		System.out.println("Done");
		
	}
	
	
	
}
