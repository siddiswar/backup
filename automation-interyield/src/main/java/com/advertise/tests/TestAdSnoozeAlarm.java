package com.advertise.tests;
/**
 * @author rahul
 *
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.advertise.comparator.BrowserNameComparator;
import com.advertise.comparator.OSComparator;
import com.advertise.comparator.PlatformComparator;
import com.advertise.comparator.RowObjectComparator;
import com.advertise.datahandler.CommonDataHandler;
import com.advertise.model.RowObject;
import com.advertise.utils.IYUtils;
import com.advertise.utils.TestCaseUtils;
import com.advertise.utils.WebpageUtils;
import com.advertise.webdriver.BrowserInject;
import com.advertise.webdriver.WebDriverFactory;

public class TestAdSnoozeAlarm {
	 
	WebDriver webDriver;

	String publisherURL;	
	String interYieldServerURL;
	String htmlElement;
	String snoozeMinutes;
	
	CommonDataHandler adSnoozeAlarmDataHandler;
	List<RowObject> outputData;
	
	Date startTime;
	
	Logger logger = Logger.getLogger(TestAdSnoozeAlarm.class.getName());
	
	@BeforeTest(alwaysRun=true)
	public void beforeTest() {
		
		logger.info("beforeTest of AdSnoozeAlarm");
		
		WebDriverFactory.getInstance();		

		publisherURL = System.getProperty("publisher_url");
		interYieldServerURL = System.getProperty("interyield_server_url");
		
		htmlElement = System.getProperty("html_element");
		snoozeMinutes = "1";
		
		adSnoozeAlarmDataHandler = new CommonDataHandler();
		outputData = new ArrayList<RowObject>();
		
		startTime = Calendar.getInstance().getTime();		
	}
	
	@DataProvider(name = "getDesktopDataFromExcel")
	public Object[][] getDesktopDataFromExcel() throws IOException {
		List<RowObject> inputData = adSnoozeAlarmDataHandler.prepareTestCases("desktop", System.getProperty("template_file_adsnoozealarm"), System.getProperty("template_sheet_adsnoozealarm"),
				System.getProperty("desktop_file_adsnoozealarm"), System.getProperty("desktop_sheet_adsnoozealarm"));
		
		Collections.sort(inputData, new RowObjectComparator(new OSComparator(), new BrowserNameComparator()));
		
		adSnoozeAlarmDataHandler.writeTestCases(inputData, "desktop", "InputData", System.getProperty("desktop_input_file_adsnoozealarm"), startTime);
		
		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}		
		return rowObjects;		
	}
	
	@DataProvider(name = "getMobileDataFromExcel")
	public Object[][] getMobileDataFromExcel() throws IOException {
		List<RowObject> inputData = adSnoozeAlarmDataHandler.prepareTestCases("mobile", System.getProperty("template_file_adsnoozealarm"), System.getProperty("template_sheet_adsnoozealarm"), 
				System.getProperty("mobile_file_adsnoozealarm"), System.getProperty("mobile_sheet_adsnoozealarm"));
		
		Collections.sort(inputData, new RowObjectComparator(new PlatformComparator(), new BrowserNameComparator()));
		
		adSnoozeAlarmDataHandler.writeTestCases(inputData, "mobile", "InputData", System.getProperty("mobile_input_file_adsnoozealarm"), startTime);
		
		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}		
		return rowObjects;		
	}	
	
	/* wait until expected webElement is returned */
	void waitForCookieExpiryTime(WebDriver driver, long waitTimeInSeconds) {
	    ExpectedCondition<Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {	    	
	            public Boolean apply(WebDriver driver) {	            	
	            	return (driver.manage().getCookieNamed("snooze") == null || Calendar.getInstance().getTime().getTime() > driver.manage().getCookieNamed("snooze").getExpiry().getTime());
	            }
	        };
	    WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
	    wait.until(pageLoadCondition);		    
	}
	
	// Without Wait
	boolean hasConsoleHasAlarmpressedMessage(WebDriver driver) {
		boolean hasMessage = false;
		String consoleString = (((JavascriptExecutor)driver).executeScript("return JSON.stringify(logStore)")).toString();		
 		if(consoleString.contains("Alarm pressed")){
 			hasMessage = true;        				
 		}
		return hasMessage;
	}
	
	// With Wait
	void waitTillYouSeeAlarmpressedMessageInConsole(WebDriver driver, long waitTimeInSeconds) {
	    ExpectedCondition<Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	            	boolean alarmPressed = false;	            	
	        	    String consoleString = (((JavascriptExecutor)driver).executeScript("return JSON.stringify(logStore)")).toString();		
	        		if(consoleString.contains("Alarm pressed")){
        				alarmPressed = true;        				
	        		}
	        		return alarmPressed == true;            	
	            }
	        };
	    WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
	    wait.until(pageLoadCondition);
	    
	}	
	
	@Test(dataProvider = "getDesktopDataFromExcel", groups = {"AllTests","IYTests","DesktopTests","DesktopIYTests","SnoozeAlarmTests",
			"IYSnoozeAlarmTests","DesktopSnoozeAlarmTests"})
	public void testAdSnoozeAlarmForDesktopIY(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testAdSnoozeAlarmForDesktopIY");
		logger.info("rowCount =>" + rowObject.getRowNumber() + ", OS : " + rowObject.getOs() + ", OsVersion : " + rowObject.getOsVersion() + ", Browser :" +rowObject.getBrowser() + ", BrowserVersion : " +rowObject.getBrowserVersion() );
			
		testAdSnoozeAlarm(rowObject, false, true);		
	}
	
	@Test(dataProvider = "getDesktopDataFromExcel", groups = {"AllTests","IYDTests","DesktopTests","DesktopIYDTests","SnoozeAlarmTests",
			"IYDSnoozeAlarmTests","DesktopSnoozeAlarmTests"})
	public void testAdSnoozeAlarmForDesktopIYD(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testAdSnoozeAlarmForDesktopIYD");
		logger.info("rowCount =>" + rowObject.getRowNumber() + ", OS : " + rowObject.getOs() + ", OsVersion : " + rowObject.getOsVersion() + ", Browser :" +rowObject.getBrowser() + ", BrowserVersion : " +rowObject.getBrowserVersion() );

		testAdSnoozeAlarm(rowObject, true, true);
	}
	
	@Test(dataProvider = "getMobileDataFromExcel", groups = {"AllTests","IYTests","MobileTests","MobileIYTests","SnoozeAlarmTests",
			"IYSnoozeAlarmTests","MobileSnoozeAlarmTests"})
	public void testAdSnoozeAlarmForMobileIY(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testAdSnoozeAlarmForMobileIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());		
		
		testAdSnoozeAlarm(rowObject, false, false);
	}
	
	@Test(dataProvider = "getMobileDataFromExcel", groups = {"AllTests","IYDTests","MobileTests","MobileIYDTests","SnoozeAlarmTests",
			"IYDSnoozeAlarmTests","MobileSnoozeAlarmTests"})
	public void testAdSnoozeAlarmForMobileIYD(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testAdSnoozeAlarmForMobileIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());		
		
		testAdSnoozeAlarm(rowObject, true, false);		
	}	
	
	public void testAdSnoozeAlarm(RowObject rowObject, boolean isIYD, boolean isDesktop) {		
		try{
			rowObject.setIYD(isIYD);
			
			rowObject.setHtmlElement(htmlElement);
			
			/* get instance of webDriver */
			webDriver = WebDriverFactory.getInstance().getWebDriver(rowObject, false);
			
			TestCaseUtils.verifyResult(webDriver, "WebDriver not instantiated", rowObject, outputData);
			
			/* delete the cache from the webDriver */
			webDriver.manage().deleteAllCookies();
			
			TestCaseUtils.verifyResult(publisherURL, "testURL cannot be empty", rowObject, outputData);
			
			TestCaseUtils.verifyResult(interYieldServerURL, "No script url provided to execute the script!", rowObject, outputData);
			/* Non-IYD */
			Map<String,String> paramMap = new HashMap<String,String>();
			
			if(isIYD){
				paramMap.put("attributionDisabled", "true");
				paramMap.put("pop", "under");
			}else{
				paramMap.put("attributionDisabled", "false");
			}
	
			paramMap.put("snoozeMinutes", snoozeMinutes);
			
			/* Construct and get Interyield server url appending parameters as per input data */
			String url = WebpageUtils.getURL(interYieldServerURL, paramMap);
			
			TestCaseUtils.verifyResult(url, "constructed script url is Invalid!", rowObject, outputData);
	
			/* set sample url to webDriver */
			webDriver.get(publisherURL);
			
			Thread.sleep(2500);
			
			BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);
			
			Thread.sleep(2500);
			
			BrowserInject.injectJS(url, webDriver);
			
			Thread.sleep(5000);
			
			TestCaseUtils.verifyResult(TestCaseUtils.checkForAdCoverage(rowObject, webDriver), "No Ad Coverage!", rowObject, outputData);
			
			
			//Do click event			
			TestCaseUtils.verifyResult(IYUtils.hasPopTriggered(webDriver, rowObject, publisherURL), "No POP action triggered since could not identify specific webElements!", rowObject, outputData);
			
			int previousSize = webDriver.getWindowHandles().size();
			
			if(previousSize > 1){
				logger.info("Pop Action Triggered!");
			}else{
				if(TestCaseUtils.verifyResult(previousSize > 1, "No Pop Action Triggered!", rowObject, outputData))
					return;
			}	
			
			/*String newWindowHandle = JavaScriptUtils.popNewTabORWindowAndGetItsWindowHandle(webDriver);
			
			TestCaseUtils.verifyResult(newWindowHandle, "Unable to open a window which contains cookie information!", rowObject, outputData);*/
			
			if(isIYD){
				((JavascriptExecutor)webDriver).executeScript("window.location.href = '"+publisherURL+"'");		
			}else{
				((JavascriptExecutor)webDriver).executeScript("window.location.href = '"+interYieldServerURL+"'");
			}
			
			webDriver.navigate().refresh();
			
			Set<Cookie> cookieSet = webDriver.manage().getCookies();
			
			logger.info("Getting cookie information after first Click from URL :: "+webDriver.getCurrentUrl());
			for(Cookie cookie : cookieSet){
				logger.info("Cookie Name :: "+cookie.getName()+", Cookie Value :: "+cookie.getValue()+", Expiry Time :: "+cookie.getExpiry());
			}
			
			TestCaseUtils.verifyResult(webDriver.manage().getCookieNamed("snooze"), "Snooze cookie does not exist !!", rowObject, outputData);
			
			long expiryTime = (Long.parseLong(snoozeMinutes) * 60);
			expiryTime = expiryTime + 10;
			
			logger.info("Wait for cookie expiryTime in Seconds :: "+expiryTime);
			try{
				waitForCookieExpiryTime(webDriver, expiryTime);
			}catch(NullPointerException e){
				logger.error(e.getMessage());
			}
			
			webDriver.navigate().to(publisherURL);
			
			webDriver.navigate().refresh();
			
			logger.info("CurrentURL in WebDriver :: "+webDriver.getCurrentUrl());
			
			BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);
			
			Thread.sleep(2000);				
			
			BrowserInject.injectJS(url, webDriver);		
			
			TestCaseUtils.verifyResult(TestCaseUtils.checkForAdCoverage(rowObject, webDriver), "No Ad Coverage!", rowObject, outputData);
			
			Set<Cookie> cookieSet2 = webDriver.manage().getCookies();
			
			logger.info("Getting cookie information before Second Click from URL :: "+webDriver.getCurrentUrl()+", this is to cross check for snooze cookie not to exist!");
			for(Cookie cookie : cookieSet2){
				logger.info("Cookie Name :: "+cookie.getName()+", Cookie Value :: "+cookie.getValue()+", Expiry Time :: "+cookie.getExpiry());
			}
			
			//Do click event after cookie gets expired			
			TestCaseUtils.verifyResult(IYUtils.hasPopTriggered(webDriver, rowObject, publisherURL), "Pop not triggered even after snooze cookie got expired!", rowObject, outputData);
			rowObject.setTestCaseResult("PASS");
			
		}catch(Exception e){
			logger.error(e);
			rowObject.setTestCaseResult("FAIL");
			rowObject.setComments(e.getMessage());
			if(webDriver != null){
				webDriver.quit();
			}
		}
		logger.info("RowCount->"+rowObject.getRowNumber()+", TestCaseResult->"+rowObject.getTestCaseResult());
		outputData.add(rowObject);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeAndQuitDriver(){
		if(webDriver != null){
			webDriver.quit();
		}
	}
	
	@AfterTest(alwaysRun=true)
	public void writeTestCaseResults() throws IOException{	
		
		logger.info("afterTest of AdSnoozeAlarm");
		if(!outputData.isEmpty()){			
			adSnoozeAlarmDataHandler.writeTestCaseResults(outputData, "OutputData", System.getProperty("desktop_iyd_output_file_adsnoozealarm"), System.getProperty("desktop_iy_output_file_adsnoozealarm"), 
					System.getProperty("mobile_iyd_output_file_adsnoozealarm"), System.getProperty("mobile_iy_output_file_adsnoozealarm"), startTime);			
		}	
		if(outputData != null){
			outputData.clear();
			outputData = null;
		}
		if(webDriver != null){
			webDriver.quit();
		}		
		logger.info(TestCaseUtils.getTestCaseRunTime(startTime));		
	}
}
