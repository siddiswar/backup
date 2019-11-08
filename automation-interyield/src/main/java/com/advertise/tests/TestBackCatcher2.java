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

public class TestBackCatcher2 {

	String interYieldServerURL;
	String nonIYDPublisherPageURL;
	String iydPublisherPageURL;
	String publisherDomain;
	String interYieldDomain;
	String publisherURL;
	String htmlElement;
	
	WebDriver webDriver;
	WebpageUtils webpageUtils = new WebpageUtils();
	LogEntries logEntries;
	DesiredCapabilities caps;
	
	CommonDataHandler backCatcher2DataHandler;
	List<RowObject> outputData;
	
	Date startTime;
	
	Logger logger = Logger.getLogger(TestBackCatcher2.class.getName()); 

	@BeforeTest(alwaysRun=true)
	public void beforeTest() {
		
		logger.info("beforeTest of BackCatcher2");
		
		WebDriverFactory.getInstance();
		
		interYieldServerURL = System.getProperty("interyield_server_url");
		publisherURL = System.getProperty("publisher_url");
		publisherDomain = System.getProperty("publisher_domain");
		interYieldDomain = System.getProperty("interyield_domain");
		htmlElement = System.getProperty("html_element");
		
		backCatcher2DataHandler = new CommonDataHandler();
		outputData = new ArrayList<RowObject>();
		
		startTime = Calendar.getInstance().getTime();
	}
	
	@DataProvider(name = "Backcatcher2DataProviderForDesktop")
	public Object[][] getDataForDesktopPlatform() throws IOException {
		List<RowObject> inputData = backCatcher2DataHandler.prepareTestCases("desktop", System.getProperty("template_file_backcatcher2"), 
				System.getProperty("template_sheet_backcatcher2"), System.getProperty("desktop_file_backcatcher2"), System.getProperty("desktop_sheet_backcatcher2"));
		
		Collections.sort(inputData, new RowObjectComparator(new OSComparator(), new BrowserNameComparator()));
		
		backCatcher2DataHandler.writeTestCases(inputData, "desktop", "InputData", System.getProperty("desktop_input_file_backcatcher2"), startTime);		
		
		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}
		return rowObjects;
	}
	
	@DataProvider(name = "Backcatcher2DataProviderForMobile")
	public Object[][] getDataForMobilePlatform() throws IOException {
		List<RowObject> inputData = backCatcher2DataHandler.prepareTestCases("mobile", System.getProperty("template_file_backcatcher2"), 
				System.getProperty("template_sheet_backcatcher2"), System.getProperty("mobile_file_backcatcher2"), System.getProperty("mobile_sheet_backcatcher2"));
		
		Collections.sort(inputData, new RowObjectComparator(new PlatformComparator(), new BrowserNameComparator()));
		
		backCatcher2DataHandler.writeTestCases(inputData, "mobile", "InputData", System.getProperty("mobile_input_file_backcatcher2"), startTime);
		
		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {			
			rowObjects[i] = new Object[] { inputData.get(i) };		
		}		
		return rowObjects;
	}
	
	@Test(dataProvider = "Backcatcher2DataProviderForDesktop" , groups = {"AllTests","IYTests","DesktopTests","DesktopIYTests",
			"IYBackcatcher2Tests","Backcatcher2Tests","DesktopBackcatcher2Tests"},timeOut=180000)
	public void testBackcatcher2ForDesktopIY(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testBackcatcher2ForDesktopIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );

		testBackcatcher2(rowObject, false, true);
	}
	
	@Test(dataProvider = "Backcatcher2DataProviderForDesktop", groups = {"AllTests","IYDTests","DesktopTests","DesktopIYDTests",
			"IYDBackcatcher2Tests","Backcatcher2Tests","DesktopBackcatcher2Tests"},timeOut=180000)
	public void testBackcatcher2ForDesktopIYD(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testBackcatcher2ForDesktopIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );

		testBackcatcher2(rowObject, true, true);
	}
	
	@Test(dataProvider = "Backcatcher2DataProviderForMobile", groups = {"AllTests","IYTests","MobileTests","MobileIYTests",
			"IYBackcatcher2Tests","Backcatcher2Tests","MobileBackcatcher2Tests"},timeOut=180000)
	public void testBackcatcher2ForMobileIY(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testBackcatcher2ForMobileIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		testBackcatcher2(rowObject, false, false);
	}
	
	@Test(dataProvider = "Backcatcher2DataProviderForMobile", groups = {"AllTests","IYDTests","MobileTests","MobileIYDTests",
			"IYDBackcatcher2Tests","Backcatcher2Tests","MobileBackcatcher2Tests"},timeOut=180000)
	public void testBackcatcher2ForMobileIYD(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testBackcatcher2ForMobileIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		testBackcatcher2(rowObject, true, false);
	}	
	
	public void testBackcatcher2(RowObject rowObject, boolean isIYD, boolean isDesktop) {
		try{			
			rowObject.setIYD(isIYD);
			
			rowObject.setHtmlElement(htmlElement);
			
			/* get instance of webDriver */
			webDriver = WebDriverFactory.getInstance().getWebDriver(rowObject, false);
			
			/* delete the cache from the webDriver */
			webDriver.manage().deleteAllCookies();
			
			TestCaseUtils.verifyResult(webDriver, "WebDriver not instantiated !!", rowObject, outputData);
			
			TestCaseUtils.verifyResult(publisherURL, "Publisher URL cannot be empty !!", rowObject, outputData);
			
			TestCaseUtils.verifyResult(interYieldServerURL, "No script url provided to execute the script !!", rowObject, outputData);

			/* Non-IYD */
			Map<String,String> paramMap = new HashMap<String,String>();
			if(isIYD){
				paramMap.put("attributionDisabled", "true");
				paramMap.put("pop", "under");
			}else{
				paramMap.put("attributionDisabled", "false");
			}
			
			//snoozeMinutes should be greater than zero 2 test backcatcher2
			paramMap.put("snoozeMinutes", "3");
			
			/* Construct and get Interyield server url appending parameters as per input data */
			String url = WebpageUtils.getURL(interYieldServerURL, paramMap);
			
			TestCaseUtils.verifyResult(url, "Constructed script url is Invalid !!", rowObject, outputData);

			
			webDriver.get(publisherURL);
			
			Thread.sleep(2500);
			
			BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);
			
			Thread.sleep(2500);

			/* injecting script to web page */
			BrowserInject.injectJS(url, webDriver);			
			
			Thread.sleep(5000);
			
			TestCaseUtils.verifyResult(TestCaseUtils.checkForAdCoverage(rowObject, webDriver), "No Ad Coverage !!", rowObject, outputData);
			
			Thread.sleep(2000);
			
			//Do click event			
			TestCaseUtils.verifyResult(IYUtils.hasPopTriggered(webDriver, rowObject, publisherURL), "No POP action triggered !!", rowObject, outputData);
			
			Thread.sleep(2000);
			
			//To make history.length >5
			IYUtils.generatePageHistory(webDriver, rowObject);
	
			
			//To read listingback cookies
			//listingBack cookie always set on IY domain only
			
			/*webDriver.get(interYieldServerURL);
			
			String affiliateName = "qatest_siddu_pub";
			String listingBackAffCookieName = "listingBack_" + affiliateName;
			Cookie listingBackAffCookie = null;
			listingBackAffCookie = webDriver.manage().getCookieNamed(listingBackAffCookieName);
						
			String listingCPCCookieName = "listingCPC_" + affiliateName;
			Cookie listingCPCCookie = null;
			listingCPCCookie = webDriver.manage().getCookieNamed(listingCPCCookieName);
			
			String listingSICookieName = "listingSI_" + affiliateName;
			Cookie listingSICookie = null;
			listingSICookie = webDriver.manage().getCookieNamed(listingSICookieName);
			
			
			if(TestCaseUtils.verifyResult(listingBackAffCookie, "listingBackAffCookie value is null", rowObject, outputData))
				return;
			if(TestCaseUtils.verifyResult(listingCPCCookie, "listingCPCCookie value is null", rowObject, outputData))
				return;
			if(TestCaseUtils.verifyResult(listingSICookie, "listingSICookie value is null", rowObject, outputData))
				return;*/
			
			Object historyLengthObj = ((JavascriptExecutor) webDriver).executeScript("return history.length");
			
			//To read snooze cookie
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
			
			TestCaseUtils.verifyResult(snoozeCookie, "Snooze cookie does not exist !!", rowObject, outputData);

			TestCaseUtils.verifyResult(snoozeCookie.getValue(), "true", "Snooze cookie value is not true !!", rowObject, outputData);
			
			TestCaseUtils.verifyResult(Integer.parseInt(historyLengthObj.toString())>5, "History length is not greater than 5 !!", rowObject, outputData);
				
			/* injecting script to web page */
			BrowserInject.injectJS(url, webDriver);	
			
			Thread.sleep(3000);
			
			//snooze=true and history.length>5=>click browser back button to fire backcatcher2
			webDriver.navigate().back();
			
			String currentURL;
			boolean hasBackCatcher2Fired = false;
			for (int i=0;i<20;i++){
				currentURL = webDriver.getCurrentUrl();
				logger.info(currentURL);
				Thread.sleep(100);
				if(currentURL.contains("backcatcher")){
					hasBackCatcher2Fired=true;
					break;
				}
			}
			
			TestCaseUtils.verifyResult(hasBackCatcher2Fired, "Backcatcher2 is not fired !!", rowObject, outputData);
			
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
		
		logger.info("afterTest of BackCatcher2");
		/* Writing test case results to the excel sheet */
		if(!outputData.isEmpty()){
			backCatcher2DataHandler.writeTestCaseResults(outputData, "OutputData", System.getProperty("desktop_iyd_output_file_backcatcher2"), System.getProperty("desktop_iy_output_file_backcatcher2"), 
				System.getProperty("mobile_iyd_output_file_backcatcher2"), System.getProperty("mobile_iy_output_file_backcatcher2"), startTime);			
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