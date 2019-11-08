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
import com.advertise.utils.TestCaseUtils;
import com.advertise.utils.WebpageUtils;
import com.advertise.webdriver.BrowserInject;
import com.advertise.webdriver.WebDriverFactory;

public class TestAdCount {

	String interYieldServerURL;
	String interYieldDomain;
	String publisherURL;
	String publisherDomain;
	String htmlElement;
	int adCountIntervalHours;
	
	WebDriver webDriver;
	WebpageUtils webpageUtils = new WebpageUtils();
	LogEntries logEntries;
	DesiredCapabilities caps;
	
	CommonDataHandler adCountDataHandler;
	List<RowObject> outputData;
	
	Date startTime;
	
	Logger logger = Logger.getLogger(TestAdCount.class.getName()); 

	@BeforeTest(alwaysRun=true)
	public void beforeTest() {
		
		logger.info("beforeTest of AdCount");
		
		WebDriverFactory.getInstance();
		
		interYieldServerURL = System.getProperty("interyield_server_url");
		interYieldDomain = System.getProperty("interyield_domain");
		publisherURL = System.getProperty("publisher_url");
		publisherDomain = System.getProperty("publisher_domain");
		htmlElement = System.getProperty("html_element");
		
		adCountIntervalHours = 1;
		
		adCountDataHandler = new CommonDataHandler();
		outputData = new ArrayList<RowObject>();
		startTime = Calendar.getInstance().getTime();
	}
	
	@DataProvider(name = "AdCountDataProviderForDesktop")
	public Object[][] getDataForDesktopPlatform() throws IOException {
		List<RowObject> inputData = adCountDataHandler.prepareTestCases("desktop", System.getProperty("template_file_adcount"), 
				System.getProperty("template_sheet_adcount"), System.getProperty("desktop_file_adcount"), System.getProperty("desktop_sheet_adcount"));
		
		Collections.sort(inputData, new RowObjectComparator(new OSComparator(), new BrowserNameComparator()));
		
		adCountDataHandler.writeTestCases(inputData, "desktop", "InputData", System.getProperty("desktop_input_file_adcount"), startTime);		
		
		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}
		return rowObjects;
	}
	
	@DataProvider(name = "AdCountDataProviderForMobile")
	public Object[][] getDataForMobilePlatform() throws IOException {
		List<RowObject> inputData = adCountDataHandler.prepareTestCases("mobile", System.getProperty("template_file_adcount"), 
				System.getProperty("template_sheet_adcount"), System.getProperty("mobile_file_adcount"), System.getProperty("mobile_sheet_adcount"));
		
		Collections.sort(inputData, new RowObjectComparator(new PlatformComparator(), new BrowserNameComparator()));
		
		adCountDataHandler.writeTestCases(inputData, "mobile", "InputData", System.getProperty("mobile_input_file_adcount"), startTime);
		
		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {			
			rowObjects[i] = new Object[] { inputData.get(i) };		
		}		
		return rowObjects;
	}
	
	@Test(dataProvider = "AdCountDataProviderForDesktop" , groups = {"AllTests","IYTests","DesktopTests","DesktopIYTests","AdCountTests",
			"IYAdCountTests","DesktopAdCountTests"},timeOut=180000)
	public void testAdCountForDesktopIY(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testAdCountForDesktopIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );
		
		testAdcount(rowObject, false, true);
	}
	
	@Test(dataProvider = "AdCountDataProviderForDesktop", groups = {"AllTests","IYDTests","DesktopTests","DesktopIYDTests","AdCountTests",
			"IYDAdCountTests","DesktopAdCountTests"},timeOut=180000)
	public void testAdCountForDesktopIYD(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testAdCountForDesktopIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );
		
		testAdcount(rowObject, true, true);
	}
	
	@Test(dataProvider = "AdCountDataProviderForMobile", groups = {"AllTests","IYTests","MobileTests","MobileIYTests","AdCountTests",
			"IYAdCountTests","MobileAdCountTests"},timeOut=180000)
	public void testAdCountForMobileIY(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testAdCountForMobileIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		
		testAdcount(rowObject, false, false);
	}
	
	@Test(dataProvider = "AdCountDataProviderForMobile", groups = {"AllTests","IYDTests","MobileTests","MobileIYDTests","AdCountTests",
			"IYDAdCountTests","MobileAdCountTests"},timeOut=180000)
	public void testAdCountForMobileIYD(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testAdCountForMobileIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		
		testAdcount(rowObject, true, false);
	}	
	
	public void testAdcount(RowObject rowObject, boolean isIYD, boolean isDesktop) {
		try{
			
			rowObject.setIYD(isIYD);
			
			rowObject.setHtmlElement(htmlElement);
			/* get instance of webDriver */
			webDriver = WebDriverFactory.getInstance().getWebDriver(rowObject, false);
			
			/* delete the cache from the webDriver */
			webDriver.manage().deleteAllCookies();
			
			TestCaseUtils.verifyResult(webDriver, "WebDriver not instantiated", rowObject, outputData);
			
			TestCaseUtils.verifyResult(publisherURL, "publisherURL cannot be empty", rowObject, outputData);
			
			TestCaseUtils.verifyResult(interYieldServerURL, "No script url provided to execute the script!", rowObject, outputData);

			/* Non-IYD */
			Map<String,String> paramMap = new HashMap<String,String>();
			
			if(isIYD){
				paramMap.put("attributionDisabled", "true");
				paramMap.put("pop", "under");
			}else{
				paramMap.put("attributionDisabled", "false");
			}
			
			paramMap.put("adCountIntervalHours", String.valueOf(adCountIntervalHours));
			
			/* Construct and get Interyield server url appending parameters as per input data */
			String url = WebpageUtils.getURL(interYieldServerURL, paramMap);
			
			TestCaseUtils.verifyResult(url, "constructed script url is Invalid!", rowObject, outputData);

			/* set sample url to webDriver */
			webDriver.get(publisherURL);
			
			Thread.sleep(2500);
			
			BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);
			
			Thread.sleep(2500);

			/* injecting script to web page */
			BrowserInject.injectJS(url, webDriver);			
			
			Thread.sleep(5000);
			
			TestCaseUtils.verifyResult(TestCaseUtils.checkForAdCoverage(rowObject, webDriver), "No Ad Coverage!", rowObject, outputData);

			int beforeSize = webDriver.getWindowHandles().size();
	
			//Do click event			
			TestCaseUtils.verifyResult(IYUtils.hasPopTriggered(webDriver, rowObject, publisherURL), "No POP action triggered since could not identify specific webElements!", rowObject, outputData);
			
			Thread.sleep(2000);
			
			int afterSize = webDriver.getWindowHandles().size();
			
			TestCaseUtils.verifyResult(afterSize>beforeSize, "No pop action triggered!", rowObject, outputData);
			
			Calendar adcountCookieActualCreationTime = Calendar.getInstance();
			
			Thread.sleep(2000);
			
			//To read adcount cookie			
			if(isIYD){
				((JavascriptExecutor)webDriver).executeScript("window.location.href = '"+publisherURL+"'");		
			}else{
				((JavascriptExecutor)webDriver).executeScript("window.location.href = '"+interYieldServerURL+"'");
			}
			
			webDriver.navigate().refresh();
			
			Thread.sleep(2000);
			
			String adcountCookieName = "adcount";
			
			Cookie adcountCookie = null;			
			
			adcountCookie = webDriver.manage().getCookieNamed(adcountCookieName);
			
			TestCaseUtils.verifyResult(adcountCookie, "adcount cookie is null", rowObject, outputData);		
			
			logger.info("adcountCookie Value :" + adcountCookie.getValue());

			if (TestCaseUtils.verifyResult(adcountCookie.getValue().equals("1"), "Incorrect adcount cookie value", rowObject, outputData))
				return;
			
			logger.info("adcountCookie Domain :" + adcountCookie.getDomain());

			if(isIYD){
				TestCaseUtils.verifyResult(adcountCookie.getDomain(), publisherDomain, "Incorrect adcount cookie Domain", rowObject, outputData);				
			}else{
				TestCaseUtils.verifyResult(adcountCookie.getDomain(), interYieldDomain, "Incorrect adcount cookie Domain", rowObject, outputData);
			}		
			
			Calendar adcountCookieActualExpiryTime;
			Date date = adcountCookie.getExpiry();
			adcountCookieActualExpiryTime = Calendar.getInstance();
			adcountCookieActualExpiryTime.setTime(date);
			
			long adcountCookieExpectedExpiryInSeconds = adCountIntervalHours * 60 * 60;			

			TestCaseUtils.verifyResult(adcountCookieActualExpiryTime, adcountCookieActualCreationTime,adcountCookieExpectedExpiryInSeconds,"Incorrect Snooze expiry Time", rowObject, outputData);
			
			rowObject.setTestCaseResult("PASS");
		}catch(Exception e){
			logger.error(e);
			//e.printStackTrace();
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
		
		logger.info("afterTest of AdCount");
		/* Writing test case results to the excel sheet */
		if(!outputData.isEmpty()){			
			adCountDataHandler.writeTestCaseResults(outputData, "OutputData", System.getProperty("desktop_iyd_output_file_adcount"), System.getProperty("desktop_iy_output_file_adcount"), 
					System.getProperty("mobile_iyd_output_file_adcount"), System.getProperty("mobile_iy_output_file_adcount"), startTime);	
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