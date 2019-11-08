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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
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
import com.advertise.datahandler.MatureContentDataHandler;
import com.advertise.model.RowObject;
import com.advertise.utils.TestCaseUtils;
import com.advertise.utils.WebpageUtils;
import com.advertise.webdriver.BrowserConsole;
import com.advertise.webdriver.BrowserInject;
import com.advertise.webdriver.WebDriverFactory;

public class TestMatureDataContent {	
	
	String htmlElement;
	
	WebDriver webDriver;
	String interYieldServerURL;	
	MatureContentDataHandler matureContentDataHandler;
	List<RowObject> outputData;
	
	Date startTime;
	
	Logger logger = Logger.getLogger(TestMatureDataContent.class.getName());
	
	@BeforeTest(alwaysRun=true)
	public void beforeTest() {
		
		logger.info("beforeTest of MatureDataContent");
		WebDriverFactory.getInstance();
		
		interYieldServerURL = System.getProperty("interyield_server_url");
		htmlElement = System.getProperty("html_element");
		
		matureContentDataHandler = new MatureContentDataHandler();
		outputData = new ArrayList<RowObject>();		
		
		startTime = Calendar.getInstance().getTime();		
	}	
	
	@DataProvider(name = "getExcelDataForDesktop")
	public Object[][] getExcelDataForDesktop() throws IOException {		
		
		List<RowObject> inputData = matureContentDataHandler.prepareTestCases("desktop", System.getProperty("template_file_maturecontent"), 
				System.getProperty("template_sheet_maturecontent"), System.getProperty("desktop_file_maturecontent"), 
				System.getProperty("desktop_sheet_maturecontent"));
		
		Collections.sort(inputData, new RowObjectComparator(new OSComparator(), new BrowserNameComparator()));
		
		matureContentDataHandler.writeTestCases(inputData, "desktop", "InputData", System.getProperty("desktop_input_file_maturecontent"), startTime);		
		
		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}		
		return rowObjects;		
	}
	
	@DataProvider(name = "getExcelDataForMobile")
	public Object[][] getExcelDataForMobile() throws IOException {		
		
		List<RowObject> inputData = matureContentDataHandler.prepareTestCases("mobile", System.getProperty("template_file_maturecontent"), 
				System.getProperty("template_sheet_maturecontent"), System.getProperty("mobile_file_maturecontent"), 
				System.getProperty("mobile_sheet_maturecontent"));
		
		Collections.sort(inputData, new RowObjectComparator(new PlatformComparator(), new BrowserNameComparator()));
		
		matureContentDataHandler.writeTestCases(inputData, "mobile", "InputData", System.getProperty("mobile_input_file_maturecontent"), startTime);		
		
		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}		
		return rowObjects;		
	}	
	
	@Test(dataProvider = "getExcelDataForDesktop", groups = {"AllTests","IYTests","DesktopTests","DesktopIYTests","MatureContentTests",
			"IYMatureContentTests","DesktopMatureContentTests"},timeOut=180000)
	public void testMatureContentHandlingForDesktopIY(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testMatureContentHandlingForDesktopIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );

		testMatureContent(rowObject, false, true);
	}
	
	@Test(dataProvider = "getExcelDataForDesktop",groups = {"AllTests","IYDTests","DesktopTests","DesktopIYDTests","MatureContentTests",
			"IYDMatureContentTests","DesktopMatureContentTests"},timeOut=180000)
	public void testMatureContentHandlingForDesktopIYD(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testMatureContentHandlingForDesktopIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );

		testMatureContent(rowObject, true, true);
	}
	
	@Test(dataProvider = "getExcelDataForMobile", groups = {"AllTests","IYTests","MobileTests","MobileIYTests","MatureContentTests",
			"IYMatureContentTests","MobileMatureContentTests"},timeOut=180000)
	public void testMatureContentHandlingForMobileIY(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testMatureContentHandlingForMobileIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		testMatureContent(rowObject, false, false);
	}
	
	@Test(dataProvider = "getExcelDataForMobile", groups = {"AllTests","IYDTests","MobileTests","MobileIYDTests","MatureContentTests",
			"IYDMatureContentTests","MobileMatureContentTests"},timeOut=180000)
	public void testMatureContentHandlingForMobileIYD(RowObject rowObject) {
		
		logger.info("======================================================================================");
		logger.info("Running testMatureContentHandlingForMobileIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		testMatureContent(rowObject, true, false);
	}
	
	public void testMatureContent(RowObject rowObject, boolean isIYD, boolean isDesktop) {
		try {
			rowObject.setIYD(isIYD);
			
			logger.info("rowObject => AdultDisabled : " + rowObject.getAdultDisabled() + " , AdultOrNonAdultURL : " + rowObject.getAdultOrNonAdultURL() );
			
			String adultDisabled = rowObject.getAdultDisabled();
			String adultOrNonAdultURL = rowObject.getAdultOrNonAdultURL();
			String shouldSnooze= rowObject.getShouldSnooze();
			
			// get instance of webDriver 
			webDriver = WebDriverFactory.getInstance().getWebDriver(rowObject, false);
			
			// delete the cache from the webDriver 
			webDriver.manage().deleteAllCookies();
			
			if(TestCaseUtils.verifyResult(webDriver, "WebDriver not instantiated", rowObject, outputData))
				return;			
			
			if(TestCaseUtils.verifyResult(adultOrNonAdultURL, "testURL cannot be empty", rowObject, outputData))
				return;		
			
			if(TestCaseUtils.verifyResult(interYieldServerURL, "No script url provided to execute the script!", rowObject, outputData))
				return;				

			Map<String,String> paramMap = new HashMap<String,String>();
			
			if(isIYD){
				paramMap.put("attributionDisabled", "true");
				paramMap.put("pop", "under");
			}else{
				paramMap.put("attributionDisabled", "false");
			}			
			
			paramMap.put("adultdisabled", adultDisabled);
			
			 //Construct and get Interyield server url appending parameters as per input data 
			String url = WebpageUtils.getURL(interYieldServerURL, paramMap);
			
			if(TestCaseUtils.verifyResult(url, "constructed script url is Invalid!", rowObject, outputData))
				return;

			// set sample url to webDriver 
			webDriver.get(adultOrNonAdultURL);
			
			//waitForLoad(webDriver);
			Thread.sleep(2500);
			
			BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);
			
			Thread.sleep(2500);
					
			// injecting script to web page 
			BrowserInject.injectJS(url, webDriver);	
			
			//waitForLoad(webDriver);
			Thread.sleep(5000);
			
			String actualSnooze = null;
			
			if(checkSnoozeOnMatureDomain(webDriver)){				
				actualSnooze = "yes";				
			}else{
				actualSnooze = "no";
			}						
			
			logger.info("actualSnooze: "+actualSnooze+", ExpectedSnooze :" + shouldSnooze);			
			
			if(TestCaseUtils.verifyResult(actualSnooze, shouldSnooze, "Incorrect snooze behaviour(Mature content handling)!", rowObject, outputData))
				return;	
			
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
		
		logger.info("afterTest of MatureDataContent");
		if(!outputData.isEmpty()){			
			matureContentDataHandler.writeTestCaseResults(outputData, "OutputData", System.getProperty("desktop_iyd_output_file_maturecontent"), System.getProperty("desktop_iy_output_file_maturecontent"), 
					System.getProperty("mobile_iyd_output_file_maturecontent"), System.getProperty("mobile_iy_output_file_maturecontent"), startTime);
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
	
	private boolean checkSnoozeOnMatureDomain(WebDriver driver) {		
		boolean snoozeOnMatureDomain = false;
		
		String consoleString = (((JavascriptExecutor)driver).executeScript("return JSON.stringify(logStore)")).toString();		
		String snoozeString = "\\\"snooze\\\":\\\"true\\\",\\\"reason\\\":\\\"identified mature domain";
		if(consoleString.contains(snoozeString)){
			snoozeOnMatureDomain = true;
			logger.info("Snoozing on mature domain !");
		}
		
		return snoozeOnMatureDomain;
	}
	
	private boolean checkSnoozeOnMatureDomain1(WebDriver driver) {		
		boolean snoozeOnMatureDomain = false;
		LogEntries logEntries = BrowserConsole.getLogs(driver);
		for (LogEntry logEntry : logEntries) {
			logger.info(logEntry.getMessage());
			if (logEntry.getMessage().contains("\"snooze\":\"true\",\"reason\":\"identified mature domain")) {
				logger.info(new Date(logEntry.getTimestamp()) + " " + logEntry.getLevel() + " " + logEntry.getMessage());
				snoozeOnMatureDomain = true;
				break;
			}
		}
		return snoozeOnMatureDomain;
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