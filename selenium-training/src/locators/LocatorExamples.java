package locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocatorExamples {

	@Test(enabled=false)
	public void locateByIdTest() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://www.facebook.com");
		System.out.println(webDriver.getTitle());

		WebElement emailElement = null;

		try {
			emailElement = webDriver.findElement(By.id("email"));
			emailElement.sendKeys("myemailid@gmail.com");
		} catch (Exception e) {
			System.out.println("Can not locate email field");
			Assert.assertEquals(true, false);
		}

		webDriver.quit();
	}

	@Test(enabled=false)
	public void locateByNameTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();

		webDriver.get("http://www.facebook.com");

		System.out.println(webDriver.getTitle());

		WebElement firstNameElement = null;
		
		try {
			firstNameElement = webDriver.findElement(By.name("firsaaaatname"));
			firstNameElement.sendKeys("my first name");
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("unable to locate first name field");
			webDriver.quit();
			Assert.assertEquals(true, false);
		}
		
		webDriver.quit();
		
	}
	
	@Test(enabled=false)
	public void locateByClassNameTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();

		webDriver.get("https://www.google.co.in/");

		System.out.println(webDriver.getTitle());

		WebElement surNameElement = null;
		
		try {
			surNameElement = webDriver.findElement(By.className("gb_P"));
			surNameElement.click();
			//surNameElement.sendKeys("my surname");
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("unable to locate surname field");
			webDriver.quit();
			Assert.assertEquals(true, false);
		}
		
		webDriver.quit();
		
	}
	
	@Test(enabled=false)
	public void locateByTagTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();

		webDriver.get("https://www.facebook.com");

		System.out.println(webDriver.getTitle());

		WebElement tagElement = null;
		
		try {
			tagElement = webDriver.findElement(By.tagName("a"));
			tagElement.click();
			//surNameElement.sendKeys("my surname");
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("unable to locate element with tag name a");
			webDriver.quit();
			Assert.assertEquals(true, false);
		}
		
		webDriver.quit();
		
	}
	
	@Test(enabled=false)
	public void locateByLinkTextTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();

		webDriver.get("https://www.facebook.com");

		System.out.println(webDriver.getTitle());

		WebElement linkElement = null;
		
		try {
			linkElement = webDriver.findElement(By.linkText("Log In"));
			linkElement.click();
			//surNameElement.sendKeys("my surname");
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("unable to locate element with tag name a");
			webDriver.quit();
			Assert.assertEquals(true, false);
		}
		
		webDriver.quit();
		
	}
	
	
	@Test(enabled=false)
	public void locateByPartialLinkTextTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();

		webDriver.get("https://www.facebook.com");

		System.out.println(webDriver.getTitle());

		WebElement linkElement = null;
		Thread.sleep(5000);
		try {
			linkElement = webDriver.findElement(By.partialLinkText("Log"));
			linkElement.click();
			//surNameElement.sendKeys("my surname");
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("unable to locate element with tag name a");
			webDriver.quit();
			Assert.assertEquals(true, false);
		}
		
		webDriver.quit();
		
	}
	
	@Test(enabled=false)
	public void locateByXpathTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();

		webDriver.get("https://www.facebook.com");

		System.out.println(webDriver.getTitle());

		WebElement linkElement = null;
		
		try {
			//linkElement = webDriver.findElement(By.xpath(".//*[@id='pageFooter']/div[2]/table/tbody/tr[1]/td[2]/a"));
			linkElement = webDriver.findElement(By.xpath("html/body/div[3]/div[1]/div/div/div/div/div[2]/form/table/tbody/tr[3]/td[2]/a"));
			linkElement.click();
			//surNameElement.sendKeys("my surname");
			Thread.sleep(5000);
		} catch (Exception e) {
			
			System.out.println("unable to locate element with tag name a");
			System.out.println(e.getMessage());

			webDriver.quit();
			Assert.assertEquals(true, false);
		}
		
		webDriver.quit();
		
	}
	
	@Test(enabled=false)
	public void locateByCSSTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();

		webDriver.get("https://www.facebook.com");

		System.out.println(webDriver.getTitle());

		WebElement linkElement = null;
		
		try {
			linkElement = webDriver.findElement(By.cssSelector("#email"));
			linkElement.sendKeys("MyEmailID");;
			//surNameElement.sendKeys("my surname");
			Thread.sleep(5000);
		} catch (Exception e) {
			
			System.out.println("unable to locate element with tag name a");
			System.out.println(e.getMessage());

			webDriver.quit();
			Assert.assertEquals(true, false);
		}
		
		webDriver.quit();
		
	}
	
	
	@Test(enabled=false)
	public void locateMultipleElements1() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();

		webDriver.get("https://www.facebook.com");

		System.out.println(webDriver.getTitle());

		List<WebElement> linkElements ;
		try {
			linkElements = webDriver.findElements(By.tagName("a"));
			int numberOfLinks = linkElements.size();
			
			for(WebElement linkElement:linkElements){
				System.out.println(linkElement.getAttribute("href"));
			}
			
			System.out.println("Link Count :" + numberOfLinks);
			Thread.sleep(5000);
		} catch (Exception e) {
			
			System.out.println("unable to locate element with tag name a");
			System.out.println(e.getMessage());

			webDriver.quit();
			Assert.assertEquals(true, false);
		}
		
		webDriver.quit();
		
	}
	
	
	@Test
	public void locateMultipleElements2() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();

		webDriver.get("https://www.facebook.com");

		System.out.println(webDriver.getTitle());

		List<WebElement> someElements ;
		try {
			someElements = webDriver.findElements(By.cssSelector("input[class='inputtext']"));
			int numberOfLinks = someElements.size();
			for(WebElement element: someElements){
				System.out.println(element.getAttribute("id"));
			}
			
			System.out.println("element Count :" + numberOfLinks);
			Thread.sleep(5000);
		} catch (Exception e) {
			
			System.out.println("unable to locate element with tag name a");
			System.out.println(e.getMessage());

			webDriver.quit();
			Assert.assertEquals(true, false);
		}
		
		webDriver.quit();
		
	}

}
