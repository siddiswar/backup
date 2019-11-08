package com.advertise.webdriver;
/**
 * @author rahul
 *
 */
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.advertise.model.BrowserType;
import com.advertise.model.RowObject;

public class BrowserInject {
	
	public static void injectOverrideConsoleLogScript(RowObject rowObject, WebDriver driver){
		//if(rowObject.getBrowser().equalsIgnoreCase(BrowserType.IE.name()) || rowObject.getBrowser().equalsIgnoreCase(BrowserType.FIREFOX.name())){
			if(rowObject.getBrowser().equalsIgnoreCase(BrowserType.IE.name())){
				/* IE console object gets activated when we open developer tools, 
				 once gets opened even after closing developer tools console object remains in active state */
				Actions action = new Actions(driver);
				action.sendKeys(Keys.F12).perform();
			}
			String script = "var a = document.createElement('script');"
					+ "a.type = 'text/javascript';" 
					+ "a.innerHTML = 'logStore = []; console.log = function() { logStore.push(arguments); }';"
					+ "document.head.appendChild(a);";
			
			((JavascriptExecutor)driver).executeScript(script);
		//}
	}
	
	public static void injectJS(String jsUrl, WebDriver driver) {
		String script = "var a = document.createElement('script');"
				+ "a.type = 'text/javascript';" + "a.src = '" + jsUrl + "';"
				+ "document.head.appendChild(a);";
		
		((JavascriptExecutor) driver).executeScript(script);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}