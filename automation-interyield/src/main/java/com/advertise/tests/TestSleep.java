package com.advertise.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.remote.DesiredCapabilities;
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
import com.advertise.utils.JavaScriptUtils;
import com.advertise.utils.TestCaseUtils;
import com.advertise.utils.WebpageUtils;
import com.advertise.webdriver.BrowserInject;
import com.advertise.webdriver.WebDriverFactory;

public class TestSleep {
	
	String publisherURL;
	String interYieldServerURL;
	String publisherDomain;
	String interYieldDomain;
	String htmlElement;
	int adCountIntervalHours;
	int maxAdCountsPerInterval;
	int snoozeMinutes;
	
	WebDriver webDriver;
	WebpageUtils webpageUtils = new WebpageUtils();	
	LogEntries logEntries;
	DesiredCapabilities caps;
	
	CommonDataHandler sleepCookieDataHandler;
	List<RowObject> outputData;
	
	Date startTime;
	
	Logger logger = Logger.getLogger(TestSleep.class.getName()); 

	@BeforeTest(alwaysRun=true)
	public void beforeTest() {
		logger.info("beforeTest of Sleep");
		WebDriverFactory.getInstance();
		
		publisherURL = System.getProperty("publisher_url");
		publisherDomain = System.getProperty("publisher_domain");
		interYieldServerURL = System.getProperty("interyield_server_url");
		interYieldDomain = System.getProperty("interyield_domain");
		htmlElement = System.getProperty("html_element");	
		adCountIntervalHours = 1;
		maxAdCountsPerInterval = 1;
		snoozeMinutes = 0;
		
		
		sleepCookieDataHandler = new CommonDataHandler();
		outputData = new ArrayList<RowObject>();
		
		startTime = Calendar.getInstance().getTime();
	}

	@DataProvider(name = "SleepDataProviderForDesktop")
	public Object[][] getDataForMobilePlatform() throws IOException {
		List<RowObject> inputData = sleepCookieDataHandler.prepareTestCases("desktop", System.getProperty("template_file_sleepcookie"), 
				System.getProperty("template_sheet_sleepcookie"), System.getProperty("desktop_file_sleepcookie"), System.getProperty("desktop_sheet_sleepcookie"));
		
		Collections.sort(inputData, new RowObjectComparator(new OSComparator(), new BrowserNameComparator()));
		
		sleepCookieDataHandler.writeTestCases(inputData, "desktop", "InputData", System.getProperty("desktop_input_file_sleepcookie"), startTime);
		
		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {			
			rowObjects[i] = new Object[] { inputData.get(i) };		
		}		
		return rowObjects;
	}
	
	@DataProvider(name = "SleepDataProviderForMobile")
	public Object[][] getDataForDesktopPlatform() throws IOException {
		List<RowObject> inputData = sleepCookieDataHandler.prepareTestCases("mobile", System.getProperty("template_file_sleepcookie"), 
				System.getProperty("template_sheet_sleepcookie"), System.getProperty("mobile_file_sleepcookie"), System.getProperty("mobile_sheet_sleepcookie"));
		
		Collections.sort(inputData, new RowObjectComparator(new PlatformComparator(), new BrowserNameComparator()));
		
		sleepCookieDataHandler.writeTestCases(inputData, "mobile", "InputData", System.getProperty("mobile_input_file_sleepcookie"), startTime);
		
		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {			
			rowObjects[i] = new Object[] { inputData.get(i) };		
		}		
		return rowObjects;
	}
	
	@Test(dataProvider = "SleepDataProviderForDesktop",groups = {"AllTests","IYTests","DesktopTests","DesktopIYTests","SleepTests",
			"IYSleepTests","DesktopSleepTests"},timeOut=180000)
	public void testSleepForDesktopIY(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testSleepForDesktopIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );

		testSleepFunctionality(rowObject, false, true);
	}
	
	@Test(dataProvider = "SleepDataProviderForDesktop",groups = {"AllTests","IYDTests","DesktopTests","DesktopIYDTests","SleepTests",
			"IYDSleepTests","DesktopSleepTests"},timeOut=180000)
	public void testSleepForDesktopIYD(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testSleepForDesktopIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );

		testSleepFunctionality(rowObject, true, false);
	}
	
	@Test(dataProvider = "SleepDataProviderForMobile",groups = {"AllTests","IYTests","MobileTests","MobileIYTests","SleepTests",
			"IYSleepTests","MobileSleepTests"},timeOut=240000)
	public void testSleepForMobileIY(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testSleepForMobileIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		
		testSleepFunctionality(rowObject, false, false);
	}
	
	@Test(dataProvider = "SleepDataProviderForMobile",groups = {"AllTests","IYDTests","MobileTests","MobileIYDTests","SleepTests",
			"IYDSleepTests","MobileSleepTests"},timeOut=240000)
	public void testSleepForMobileIYD(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testSleepForMobileIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		
		testSleepFunctionality(rowObject, true, false);
	}	
	
	public void testSleepFunctionality(RowObject rowObject, boolean isIYD, boolean isDesktop) {
		try{
			rowObject.setIYD(isIYD);
			
			rowObject.setHtmlElement(htmlElement);
			
			/* get instance of webDriver */
			webDriver = WebDriverFactory.getInstance().getWebDriver(rowObject, false);
			
			/* delete the cache from the webDriver */
			//webDriver.manage().deleteAllCookies();
			
			TestCaseUtils.verifyResult(webDriver, "WebDriver not instantiated !!", rowObject, outputData);
			
			TestCaseUtils.verifyResult(publisherURL, "Publisher URL cannot be empty !!", rowObject, outputData);
			
			TestCaseUtils.verifyResult(interYieldServerURL, "No script url provided to execute the script !!", rowObject, outputData);

			Map<String,String> paramMap = new HashMap<String,String>();
			
			if(isIYD){
				paramMap.put("attributionDisabled", "true");
				paramMap.put("pop", "under");
			}else{
				paramMap.put("attributionDisabled", "false");
			}
			
			paramMap.put("adCountIntervalHours", String.valueOf(adCountIntervalHours));
			
			paramMap.put("maxAdCountsPerInterval", String.valueOf(maxAdCountsPerInterval));
			
			paramMap.put("snoozeMinutes", String.valueOf(snoozeMinutes)); //snoozeMinutes are set to zero since sleep cookie gets created after snoozeMinutes wven though adcount is reached.So to reduce waiting time snoozeMinutes are set to zero.

			/* Construct and get Interyield server url appending parameters as per input data */
			String url = WebpageUtils.getURL(interYieldServerURL, paramMap);
			
			TestCaseUtils.verifyResult(url, "Constructed script url is Invalid !!", rowObject, outputData);
			
			int popCount = 0;

			Calendar sleepCookieActualCreationTime = Calendar.getInstance();
	
			while(popCount < maxAdCountsPerInterval) {
				// Iterating only maxAdCountsPerInterval no of times
				webDriver.get(publisherURL);
			
				//waitForLoad(webDriver);
				Thread.sleep(2500);
				
				BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);
				
				Thread.sleep(1000);
			
				/* injecting script to web page */
				BrowserInject.injectJS(url, webDriver);			
				
				//waitForLoad(webDriver);
				Thread.sleep(5000);
				
				TestCaseUtils.verifyResult(TestCaseUtils.checkForAdCoverage(rowObject, webDriver), "No Ad Coverage !!", rowObject, outputData);

				//Do click event			
				TestCaseUtils.verifyResult(IYUtils.hasPopTriggered(webDriver, rowObject, publisherURL), "No POP action triggered !!", rowObject, outputData);
				
				Thread.sleep(2000);
				
				popCount = popCount + 1;
				
				if (popCount == maxAdCountsPerInterval) {
					
					// This is when sleep cookie gets created
					sleepCookieActualCreationTime = Calendar.getInstance();
				}				
	
			}	
			
			TestCaseUtils.verifyResult(popCount == maxAdCountsPerInterval, "IY Ad did not pop maxAdCountsPerInterval times !!", rowObject, outputData);
			//The following step is needed since in case of IY next 'getSnoozing.do' call creates sleep cookie
			if(!isIYD){
				webDriver.get(publisherURL);
				
				Thread.sleep(3000);
				
				BrowserInject.injectJS(url, webDriver);	
				
				Thread.sleep(3000);
			}
			
			// To read cookie
			if(isIYD){
				((JavascriptExecutor)webDriver).executeScript("window.location.href = '"+publisherURL+"'");		
			}else{
				((JavascriptExecutor)webDriver).executeScript("window.location.href = '"+interYieldServerURL+"'");
			}
			
			webDriver.navigate().refresh();
			 
			String sleepCookieName = "sleep";
			Cookie sleepCookie = null;
			
			sleepCookie = webDriver.manage().getCookieNamed(sleepCookieName);

			TestCaseUtils.verifyResult(sleepCookie, "Sleep cookie does not exist !!", rowObject, outputData);
			
			TestCaseUtils.verifyResult(sleepCookie.getValue().equals("true"), "Sleep cookie value is not true !!", rowObject, outputData);
			
			if(isIYD){
					TestCaseUtils.verifyResult(sleepCookie.getDomain(),publisherDomain, "Incorrect sleep cookie domain !!", rowObject, outputData);
			}else{
					TestCaseUtils.verifyResult(sleepCookie.getDomain(),interYieldDomain, "Incorrect sleep cookie domain !!", rowObject, outputData);
			}
			
			Calendar sleepCookieActualExpiryTime;
			sleepCookieActualExpiryTime = Calendar.getInstance();
			Date date = sleepCookie.getExpiry();
			sleepCookieActualExpiryTime.setTime(date);
			long sleepCookieExpectedExpiryInSeconds = adCountIntervalHours * 60 * 60;

			TestCaseUtils.verifyResult(sleepCookieActualExpiryTime, sleepCookieActualCreationTime,sleepCookieExpectedExpiryInSeconds, "Incorrect sleep cookie expiry Time !!", rowObject, outputData);
				
			
			// To verify IY behavior While sleep cookie is set			
			webDriver.navigate().to(publisherURL);
			
			webDriver.navigate().refresh();
			
			Thread.sleep(3000);
			
			BrowserInject.injectJS(url, webDriver);	
			
			Thread.sleep(5000);
			
			//Do click event			
			TestCaseUtils.verifyResult(!IYUtils.hasPopTriggered(webDriver, rowObject, publisherURL), "pop happened while sleep cookie is set", rowObject, outputData);
			
			rowObject.setTestCaseResult("PASS");
		}catch(Exception e){
			logger.error(e);
			if (webDriver != null)
				webDriver.quit();			
			rowObject.setTestCaseResult("FAIL");
			rowObject.setComments(e.getMessage());
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
		
		logger.info("afterTest of Sleep");
		/* Writing test case results to the excel sheet */
		if(!outputData.isEmpty()){
			sleepCookieDataHandler.writeTestCaseResults(outputData, "OutputData", System.getProperty("desktop_iyd_output_file_sleepcookie"), System.getProperty("desktop_iy_output_file_sleepcookie"), 
					System.getProperty("mobile_iyd_output_file_sleepcookie"), System.getProperty("mobile_iy_output_file_sleepcookie"), startTime);
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
	
	private boolean popHappened(WebDriver driver, int winHandlesBeforeClickEvent) {		
		int winHandlesAfterClickEvent = driver.getWindowHandles().size();
		logger.info("winHandlesBeforeClickEvent :" + winHandlesBeforeClickEvent );
		logger.info("winHandlesAfterClickEvent :" + winHandlesAfterClickEvent );		
		if (winHandlesAfterClickEvent == winHandlesBeforeClickEvent + 1) {
			return true;
		} else {
			return false;
		}
	}
}
