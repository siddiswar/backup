package com.advertise.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
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
import com.advertise.utils.TestCaseUtils;
import com.advertise.utils.WebpageUtils;
import com.advertise.webdriver.BrowserInject;
import com.advertise.webdriver.WebDriverFactory;

public class TestBackCatcher1 {
	
	Logger logger = Logger.getLogger(TestBackCatcher1.class.getName()); 
	
	String publisherURL;
	String interYieldServerURL;	
	String htmlElement;
	
	Date startTime;
	CommonDataHandler backCatcher1DataHandler;
	List<RowObject> outputData;
	
	WebDriver webDriver;	
	
	@BeforeTest(alwaysRun=true)
	public void beforeTest() {
		logger.info("beforeTest of BackCatcher1");
		WebDriverFactory.getInstance();
		
		backCatcher1DataHandler = new CommonDataHandler();		
		outputData = new ArrayList<RowObject>();
		
		htmlElement = System.getProperty("html_element");
		publisherURL = System.getProperty("publisher_url");
		interYieldServerURL = System.getProperty("interyield_server_url");
		
		startTime = Calendar.getInstance().getTime();		
	}
	
	@DataProvider(name="getDesktopDataFromExcel")
	public Object[][] getDesktopDataFromExcel() throws IOException {
		List<RowObject> inputData = backCatcher1DataHandler.prepareTestCases("desktop", System.getProperty("template_file_backcatcher1"), System.getProperty("template_sheet_backcatcher1"),
				System.getProperty("desktop_file_backcatcher1"), System.getProperty("desktop_sheet_backcatcher1"));
		
		Collections.sort(inputData, new RowObjectComparator(new OSComparator(), new BrowserNameComparator()));
		
		backCatcher1DataHandler.writeTestCases(inputData, "desktop", "InputData", System.getProperty("desktop_input_file_backcatcher1"), startTime);
		
		Object[][] rowObjects = new Object[inputData.size()][];		
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}		
		return rowObjects;	
	}
	
	@DataProvider(name="getMobileDataFromExcel")
	public Object[][] getMobileDataFromExcel() throws IOException{
		List<RowObject> inputData = backCatcher1DataHandler.prepareTestCases("mobile", System.getProperty("template_file_backcatcher1"), System.getProperty("template_sheet_backcatcher1"),
				System.getProperty("mobile_file_backcatcher1"), System.getProperty("mobile_sheet_backcatcher1"));
		
		Collections.sort(inputData, new RowObjectComparator(new PlatformComparator(), new BrowserNameComparator()));
		
		backCatcher1DataHandler.writeTestCases(inputData, "mobile", "InputData", System.getProperty("mobile_input_file_backcatcher1"), startTime);

		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}		
		return rowObjects;	
	}	
	
	@Test(dataProvider = "getDesktopDataFromExcel",groups = {"AllTests","IYTests","DesktopTests","DesktopIYTests","BackCatcher1Tests",
			"IYBackCatcher1Tests","DesktopBackCatcher1Tests"},timeOut=180000)
	public void testBackCatcher1ForDesktopIY(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testBackCatcher1ForDesktopIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );
		
		testBackCatcher1(rowObject, false, true);		
	}
	
	@Test(dataProvider = "getDesktopDataFromExcel",groups = {"AllTests","IYDTests","DesktopTests","DesktopIYDTests","BackCatcher1Tests",
			"IYDBackCatcher1Tests","DesktopBackCatcher1Tests"},timeOut=180000)
	public void testBackCatcher1ForDesktopIYD(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testBackCatcher1ForDesktopIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );
		
		testBackCatcher1(rowObject, true, true);		
	}
	
	@Test(dataProvider = "getMobileDataFromExcel",groups = {"AllTests","IYTests","MobileTests","MobileIYTests","BackCatcher1Tests",
			"IYBackCatcher1Tests","MobileBackCatcher1Tests"},timeOut=180000)
	public void testBackCatcher1ForMobileIY(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testBackCatcher1ForMobileIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());	
		
		testBackCatcher1(rowObject, false, false);	
	}
	
	@Test(dataProvider = "getMobileDataFromExcel",groups = {"AllTests","IYDTests","MobileTests","MobileIYDTests","BackCatcher1Tests",
			"IYDBackCatcher1Tests","MobileBackCatcher1Tests"},timeOut=180000)
	public void testBackCatcher1ForMobileIYD(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testBackCatcher1ForMobileIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());			

		testBackCatcher1(rowObject, true, false);	

	}	
	
	@Test(dataProvider = "getExcelData", description="Testing Master Pop behaviour functionality of IY")
	public void testBackCatcher1(RowObject rowObject, boolean isIYD, boolean isDesktop) {
		try {
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
			
			/* Construct and get Interyield server url appending parameters as per input data */
			String url = WebpageUtils.getURL(interYieldServerURL, paramMap);
			
			TestCaseUtils.verifyResult(url, "Constructed script url is Invalid !!", rowObject, outputData);

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
			
			webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
			/* click browser back button */
			
			webDriver.navigate().back();

			String currentURL;
			boolean hasBackCatcher1Fired = false;
			for (int i=0;i<20;i++){
				currentURL = webDriver.getCurrentUrl();
				logger.info(currentURL);
				Thread.sleep(100);
				if(currentURL.contains("backcatcher")){
					hasBackCatcher1Fired=true;
					break;
				}
			}
			
			TestCaseUtils.verifyResult(hasBackCatcher1Fired, "Backcatcher1 is not fired !!", rowObject, outputData);
			
			//waitForLoad(webDriver);
			Thread.sleep(2500);			
			/* get active window title of the system using native api calls */			
			rowObject.setTestCaseResult("PASS");

		} catch (Exception e) {
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
		
		logger.info("afterTest of BackCatcher1");
		if(!outputData.isEmpty()){			
			backCatcher1DataHandler.writeTestCaseResults(outputData, "OutputData", System.getProperty("desktop_iyd_output_file_backcatcher1"), System.getProperty("desktop_iy_output_file_backcatcher1"), 
					System.getProperty("mobile_iyd_output_file_backcatcher1"), System.getProperty("mobile_iy_output_file_backcatcher1"), startTime);
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