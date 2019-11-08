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

public class TestSnooze {
	
	Logger logger = Logger.getLogger(TestSnooze.class.getName());
	
	CommonDataHandler snoozeDataHandler;
	List<RowObject> outputData;
	
	WebDriver webDriver;
	int snoozeMinutes;
	String htmlElement;
	String publisherURL;
	String interYieldServerURL;
	
	String publisherDomain;
	String interYieldDomain;
	
	Date startTime;	
	
	@BeforeTest(alwaysRun=true)
	public void beforeTest() {
		logger.info("beforeTest of Snooze");
		WebDriverFactory.getInstance();	
		publisherURL = System.getProperty("publisher_url");
		publisherDomain = System.getProperty("publisher_domain");
		interYieldServerURL = System.getProperty("interyield_server_url");
		interYieldDomain = System.getProperty("interyield_domain");
		htmlElement = System.getProperty("html_element");		
		snoozeMinutes = 2;
		snoozeDataHandler = new CommonDataHandler();
		outputData = new ArrayList<RowObject>();
		
		startTime = Calendar.getInstance().getTime();
	}	
	
	@DataProvider(name="getDesktopDataFromExcel")
	public Object[][] getDesktopDataFromExcel() throws IOException {

		List<RowObject> inputData = snoozeDataHandler.prepareTestCases("desktop", System.getProperty("template_file_snooze"), System.getProperty("template_sheet_snooze"),
				System.getProperty("desktop_file_snooze"), System.getProperty("desktop_sheet_snooze"));
		
		Collections.sort(inputData, new RowObjectComparator(new OSComparator(), new BrowserNameComparator()));
		
		snoozeDataHandler.writeTestCases(inputData, "desktop", "InputData", System.getProperty("desktop_input_file_snooze"), startTime);
		
		Object[][] rowObjects = new Object[inputData.size()][];		
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}		
		return rowObjects;	
	}
	
	@DataProvider(name="getMobileDataFromExcel")
	public Object[][] getMobileDataFromExcel() throws IOException{
		
		List<RowObject> inputData = snoozeDataHandler.prepareTestCases("mobile", System.getProperty("template_file_snooze"), System.getProperty("template_sheet_snooze"),
				System.getProperty("mobile_file_snooze"), System.getProperty("mobile_sheet_snooze"));
		
		Collections.sort(inputData, new RowObjectComparator(new PlatformComparator(), new BrowserNameComparator()));
		
		snoozeDataHandler.writeTestCases(inputData, "mobile", "InputData", System.getProperty("mobile_input_file_snooze"), startTime);

		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}		
		return rowObjects;	
	}	
	
	@Test(dataProvider = "getDesktopDataFromExcel",groups = {"AllTests","IYTests","DesktopTests","DesktopIYTests","SnoozeTests",
			"IYSnoozeTests","DesktopSnoozeTests"},timeOut=180000)
	public void testSnoozeIYForDesktop(RowObject rowObject){
		logger.info("======================================================================================");
		logger.info("Running testSnoozeIYForDesktop for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );
		testSnooze(rowObject, false, true);		
	}
	
	@Test(dataProvider = "getDesktopDataFromExcel",groups = {"AllTests","IYDTests","DesktopTests","DesktopIYDTests","SnoozeTests",
			"IYDSnoozeTests","DesktopSnoozeTests"},timeOut=180000)
	public void testSnoozeIYDForDesktop(RowObject rowObject){
		logger.info("======================================================================================");
		logger.info("Running testSnoozeIYDForDesktop for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );
		testSnooze(rowObject, true, true);		
	}
	
	@Test(dataProvider = "getMobileDataFromExcel",groups = {"AllTests","IYTests","MobileTests","MobileIYTests","SnoozeTests",
			"IYSnoozeTests","MobileSnoozeTests"},timeOut=210000)
	public void testSnoozeIYForMobile(RowObject rowObject){
		logger.info("======================================================================================");
		logger.info("Running testSnoozeIYForMobile for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		testSnooze(rowObject, false, false);
	}
	
	@Test(dataProvider = "getMobileDataFromExcel",groups = {"AllTests","IYDTests","MobileTests","MobileIYDTests","SnoozeTests",
			"IYDSnoozeTests","MobileSnoozeTests"},timeOut=210000)
	public void testSnoozeIYDForMobile(RowObject rowObject){
		logger.info("======================================================================================");
		logger.info("Running testSnoozeIYDForMobile for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		testSnooze(rowObject, true, false);		
	}	

	public void testSnooze(RowObject rowObject, boolean isIYD, boolean isDesktop) {
		try {
			rowObject.setIYD(isIYD);
			
			rowObject.setHtmlElement(htmlElement);
			/* get instance of webDriver */
			webDriver = WebDriverFactory.getInstance().getWebDriver(rowObject, false);
			
			/* delete the cache from the webDriver */
			webDriver.manage().deleteAllCookies();
			
			TestCaseUtils.verifyResult(webDriver, "WebDriver not instantiated", rowObject, outputData);
			
			TestCaseUtils.verifyResult(publisherURL, "publisherURL is empty", rowObject, outputData);
			
			TestCaseUtils.verifyResult(interYieldServerURL, "No script url provided to execute the script!", rowObject, outputData);

			Map<String,String> paramMap = new HashMap<String,String>();
			
			if(isIYD){
				paramMap.put("attributionDisabled", "true");
				paramMap.put("pop", "under");
			}else{
				paramMap.put("attributionDisabled", "false");
			}
			
			paramMap.put("snoozeMinutes", String.valueOf(snoozeMinutes));
			
			/* Construct and get Interyield server url appending parameters as per input data */
			String url = WebpageUtils.getURL(interYieldServerURL, paramMap);
			
			TestCaseUtils.verifyResult(url, "constructed script url is Invalid!", rowObject, outputData);

			/* set sample url to webDriver */
			webDriver.get(publisherURL);
			
			//waitForLoad(webDriver);
			Thread.sleep(2500);
			
			BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);
			
			Thread.sleep(2500);

			/* injecting script to web page */
			BrowserInject.injectJS(url, webDriver);			
			
			//waitForLoad(webDriver);
			Thread.sleep(5000);
			
			TestCaseUtils.verifyResult(TestCaseUtils.checkForAdCoverage(rowObject, webDriver), "No Ad Coverage !!", rowObject, outputData);
			
			//Do click event			
			TestCaseUtils.verifyResult(IYUtils.hasPopTriggered(webDriver, rowObject, publisherURL), "No POP action triggered !!", rowObject, outputData);
			
			
			Thread.sleep(2000);
			
			Calendar snoozeCookieActualCreationTime = Calendar.getInstance();			
			
			if(isIYD){
				((JavascriptExecutor)webDriver).executeScript("window.location.href = '"+publisherURL+"'");		
			}else{
				((JavascriptExecutor)webDriver).executeScript("window.location.href = '"+interYieldServerURL+"'");
			}
			
			webDriver.navigate().refresh();
			
			Thread.sleep(5000);
			
			String snoozeCookieName = "snooze";
			
			Cookie snoozeCookie = null;
			
			snoozeCookie = webDriver.manage().getCookieNamed(snoozeCookieName);
			
			TestCaseUtils.verifyResult(snoozeCookie, "Snooze cookie does not exist", rowObject, outputData);


			if(isIYD){
				TestCaseUtils.verifyResult(snoozeCookie.getDomain(), publisherDomain, "Incorrect Snooze cookie Domain !!", rowObject, outputData);
			}else{
				TestCaseUtils.verifyResult(snoozeCookie.getDomain(), interYieldDomain, "Incorrect Snooze cookie Domain !!", rowObject, outputData);
			}
						
			Calendar snoozeCookieActualExpiryTime;
			Date date = snoozeCookie.getExpiry();
			snoozeCookieActualExpiryTime = Calendar.getInstance();
			snoozeCookieActualExpiryTime.setTime(date);
			
			long snoozeCookieExpectedExpiryInSeconds = snoozeMinutes * 60;
			
			TestCaseUtils.verifyResult(snoozeCookieActualExpiryTime, snoozeCookieActualCreationTime,snoozeCookieExpectedExpiryInSeconds,"Incorrect Snooze Expiry !!",rowObject, outputData);

			// To verify IY behavior While snooze cookie is set
			webDriver.navigate().to(publisherURL);
			
			webDriver.navigate().refresh();
			
			Thread.sleep(3000);
			
			BrowserInject.injectJS(url, webDriver);	
			
			Thread.sleep(5000);
			
			//Do click event			
			TestCaseUtils.verifyResult(!IYUtils.hasPopTriggered(webDriver, rowObject, publisherURL), "Pop happened while Snooze cookie is set !!", rowObject, outputData);
			
			rowObject.setTestCaseResult("PASS");
			
		} catch (Exception e) {
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
		
		logger.info("afterTest of Snooze");
		if(!outputData.isEmpty()){
			snoozeDataHandler.writeTestCaseResults(outputData, "OutputData", System.getProperty("desktop_iyd_output_file_snooze"), System.getProperty("desktop_iy_output_file_snooze"), 
					System.getProperty("mobile_iyd_output_file_snooze"), System.getProperty("mobile_iy_output_file_snooze"), startTime);
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
	
	/* wait until expected webElement is returned */
	private void waitForLoad(WebDriver driver) {
	    ExpectedCondition<Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	            	return driver.getTitle() != null;
	            }
	        };
	    WebDriverWait wait = new WebDriverWait(driver, 120);
	    wait.until(pageLoadCondition);
	}	
	
}