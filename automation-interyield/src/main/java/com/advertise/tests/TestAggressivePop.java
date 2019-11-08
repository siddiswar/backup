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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.advertise.datahandler.AggressivePopDataHandler;
import com.advertise.model.RowObject;
import com.advertise.utils.TestCaseUtils;
import com.advertise.utils.WebpageUtils;
import com.advertise.utils.WindowUtils;
import com.advertise.webdriver.BrowserInject;
import com.advertise.webdriver.WebDriverFactory;

public class TestAggressivePop {
	
	Logger logger = Logger.getLogger(TestAggressivePop.class.getName());
	
	AggressivePopDataHandler aggressivePopDataHandler;
	
	List<RowObject> outputData;
	
	WebDriver webDriver;
	
	String publisherURL;
	String interYieldServerURL;
	
	Date startTime; 
	
	@BeforeTest(alwaysRun=true)
	public void beforeTest() {
		logger.info("beforeTest of AggressivePop");
		WebDriverFactory.getInstance();
		aggressivePopDataHandler = new AggressivePopDataHandler();
		publisherURL = System.getProperty("publisher_url");
		interYieldServerURL = System.getProperty("interyield_server_url");		
		outputData = new ArrayList<RowObject>();		
		startTime = Calendar.getInstance().getTime();
	}	
	
	@DataProvider(name = "getDesktopDataFromExcel")
	public Object[][] getDesktopDataFromExcel() throws IOException {		
		List<RowObject> inputData = aggressivePopDataHandler.getTestCases("desktop", System.getProperty("template_file_aggressive_pop"), System.getProperty("template_sheet_aggressive_pop"),
				System.getProperty("html_sheet_aggressive_pop"), System.getProperty("desktop_file_aggressive_pop"), System.getProperty("desktop_sheet_aggressive_pop"));
		
		Collections.sort(inputData, new RowObjectComparator(new OSComparator(), new BrowserNameComparator()));
		
		aggressivePopDataHandler.writeTestCases(inputData, "desktop", "InputData", System.getProperty("desktop_input_file_aggressive_pop"), startTime);
		
		if(inputData != null){
			Object[][] rowObjects = new Object[inputData.size()][];
			for (int i = 0; i < inputData.size(); i++) {
				rowObjects[i] = new Object[] { inputData.get(i) };
			}		
			return rowObjects;
		}
		return null;
	}
	
	@DataProvider(name = "getMobileDataFromExcel")
	public Object[][] getMobileDataFromExcel() throws IOException {		
		List<RowObject> inputData = aggressivePopDataHandler.getTestCases("mobile", System.getProperty("template_file_aggressive_pop"), System.getProperty("template_sheet_aggressive_pop"),
				System.getProperty("html_sheet_aggressive_pop"), System.getProperty("mobile_file_aggressive_pop"), System.getProperty("mobile_sheet_aggressive_pop"));
		
		Collections.sort(inputData, new RowObjectComparator(new PlatformComparator(), new BrowserNameComparator()));
		
		aggressivePopDataHandler.writeTestCases(inputData, "mobile", "InputData", System.getProperty("mobile_input_file_aggressive_pop"), startTime);
		
		if(inputData != null){
			Object[][] rowObjects = new Object[inputData.size()][];
			for (int i = 0; i < inputData.size(); i++) {
				rowObjects[i] = new Object[] { inputData.get(i) };
			}		
			return rowObjects;
		}
		return null;
	}	
	
	@Test(dataProvider = "getDesktopDataFromExcel",groups = {"AllTests","IYTests","DesktopTests","DesktopIYTests","AggressivePopTests",
			"IYAggressivePopTests","DesktopAggressivePopTests","DesktopPopTests"},description = "Testing Aggressive IY Pop behavior for Desktop using SmartSwitch")
	public void testAggressivePopForDesktopIY(RowObject rowObject){
	
		logger.info("======================================================================================");
		logger.info("Running testAggressivePopForDesktopIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + ", OsVersion : " + rowObject.getOsVersion() + ", Browser :" +rowObject.getBrowser() + ", BrowserVersion : " +rowObject.getBrowserVersion() +", HTMLELement : "+rowObject.getHtmlElement() +", POP Type : "+rowObject.getPopType()+", SmartSwitch Type : "+rowObject.getSmartSwitchType()+", Expected Pop Type : "+rowObject.getExpectedPop());
	
		testAggressivePopBehaviour(rowObject, false, true);		
	}
	
	@Test(dataProvider = "getDesktopDataFromExcel",groups = {"AllTests","IYDTests","DesktopTests","DesktopIYDTests","AggressivePopTests",
			"IYDAggressivePopTests","DesktopAggressivePopTests","DesktopPopTests"}, description = "Testing Aggressive IYD Pop behavior for Desktop using SmartSwitch")
	public void testAggressivePopForDesktopIYD(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testAggressivePopForDesktopIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + ", OsVersion : " + rowObject.getOsVersion() + ", Browser :" +rowObject.getBrowser() + ", BrowserVersion : " +rowObject.getBrowserVersion() +", HTMLELement : "+rowObject.getHtmlElement() +", POP Type : "+rowObject.getPopType()+", SmartSwitch Type : "+rowObject.getSmartSwitchType()+", Expected Pop Type : "+rowObject.getExpectedPop());
		
		if(rowObject.getPopType() != null && (rowObject.getPopType().equalsIgnoreCase("over") || rowObject.getPopType().equalsIgnoreCase("UNSPECIFIED"))){
			logger.warn("This test case has run for IYD and for IYD pop should be always under i.e. pop=under. Hence could not run test case for pop=over for IYD, rowCount ->"+rowObject.getRowNumber());
			return;
		}
		
		testAggressivePopBehaviour(rowObject, true, true);		
	}

	@Test(dataProvider = "getMobileDataFromExcel",groups = {"AllTests","IYTests","MobileTests","MobileIYTests","AggressivePopTests",
			"IYAggressivePopTests","MobileAggressivePopTests"}, description = "Testing Aggressive IY Pop behavior for Mobile using SmartSwitch")
	public void testAggressivePopForMobileIY(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testAggressivePopForMobileIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());		
		
		testAggressivePopBehaviour(rowObject, false, false);
	}
	
	@Test(dataProvider = "getMobileDataFromExcel",groups = {"AllTests","IYDTests","MobileTests","MobileIYDTests","AggressivePopTests",
			"IYDAggressivePopTests","MobileAggressivePopTests"}, description = "Testing Aggressive IYD Pop behavior for Mobile using SmartSwitch")
	public void testAggressivePopForMobileIYD(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testAggressivePopForMobileIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());		
		
		testAggressivePopBehaviour(rowObject, true, false);		
	}	
	
	public void testAggressivePopBehaviour(RowObject rowObject, boolean isIYD, boolean isDesktop) {
		try {
			rowObject.setIYD(isIYD);
			// getting the instance of a driver(invoking call to browser)
			/* 2nd argument true means focusTests=true */
			webDriver = WebDriverFactory.getInstance().getWebDriver(rowObject, true);
			
			// assert for driver instantiation as not null
			if(TestCaseUtils.verifyResult(webDriver, "WebDriver not instantiated", rowObject, outputData))
				return;
			
			// delete the cookies
			webDriver.manage().deleteAllCookies();
			
			// assert for test(end user url) page as not empty
			if(TestCaseUtils.verifyResult(publisherURL, "testURL cannot be empty", rowObject, outputData))
				return;			
			
			// get the url in the web driver
			webDriver.get(publisherURL);
			
			// wait until the page gets loaded
			waitForLoad(webDriver);
			//Thread.sleep(2500);
			
			if(TestCaseUtils.verifyResult(interYieldServerURL, "No script url provided to execute the script!", rowObject, outputData))
				return;			
			
			Map<String, String> paramMap = new HashMap<String,String>();			
			if(rowObject.getPopType().equalsIgnoreCase("UNSPECIFIED"))
				paramMap.put("pop", "");
			else
				paramMap.put("pop", rowObject.getPopType());
			if(rowObject.getSmartSwitchType().equalsIgnoreCase("UNSPECIFIED"))
				paramMap.put("smartSwitch", "");
			else
				paramMap.put("smartSwitch", rowObject.getSmartSwitchType().equalsIgnoreCase("ON") ? "true": "false");
			
			if(isIYD){
				paramMap.put("attributionDisabled", "true");
				paramMap.put("pop", "under");
			}else{
				paramMap.put("attributionDisabled", "false");
			}			
			
			String url = WebpageUtils.getURL(interYieldServerURL, paramMap);
			
			if(TestCaseUtils.verifyResult(url, "constructed script url is Invalid!", rowObject, outputData))
				return;
			
			String originalTitle = webDriver.getTitle();			
			String tempTitle = null;
			
			if(!rowObject.getHtmlElement().equalsIgnoreCase("WHITESPACE")){
				try{
					tempTitle = WebpageUtils.getUserWindowTitle(rowObject, publisherURL, outputData);
				}catch(Exception e){
					rowObject.setTestCaseResult("FAIL");
					rowObject.setComments(e.getMessage());
					if (webDriver != null) {
						webDriver.quit();	
					}
					outputData.add(rowObject);
					logger.info("RowCount->"+rowObject.getRowNumber()+", TestCaseResult->"+rowObject.getTestCaseResult());
					return;
				}
			}else{
				tempTitle = originalTitle;
			}
			
			webDriver.manage().deleteAllCookies();		
			
			BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);
			
			Thread.sleep(2500);
			
			logger.info("Interyield script injected is :: "+url);
						
			BrowserInject.injectJS(url, webDriver);
			
			Thread.sleep(5000);
			
			if(TestCaseUtils.verifyResult(TestCaseUtils.checkForAdCoverage(rowObject, webDriver), "No Ad Coverage!", rowObject, outputData)){
				return;
			}
			
			WebElement webElement = WebpageUtils.getWebElement(webDriver, rowObject);

			webElement.click();
			
			Thread.sleep(2500);
			
			Set<String> handleSet = webDriver.getWindowHandles();
			if(TestCaseUtils.verifyResult(handleSet.size() == 2, "No Pop Action Triggered!", rowObject, outputData))
				return;
			
			String currentActiveWindowTitle = WindowUtils.getActiveWindowTitle();

			if(TestCaseUtils.verifyResult(!currentActiveWindowTitle.isEmpty(), "Unable to capture current active window title!", rowObject, outputData))
				return;
			
			logger.info("Current Active Window Title is::"+currentActiveWindowTitle);
			
			currentActiveWindowTitle = currentActiveWindowTitle.substring(0, currentActiveWindowTitle.lastIndexOf(" - "));						
			
			if(originalTitle != null && tempTitle != null){
				if(originalTitle.equalsIgnoreCase(tempTitle)){
					if(currentActiveWindowTitle.equalsIgnoreCase(originalTitle) || currentActiveWindowTitle.contains(originalTitle)){
						// POP UNDER
						if(rowObject.getExpectedPop().equalsIgnoreCase("UNDER")){
							// PASS
						}else{
							if(TestCaseUtils.verifyResult(false, "Incorrect pop action triggered!", rowObject, outputData))
								return;
						}
					}else{
						// POP OVER
						if(rowObject.getExpectedPop().equalsIgnoreCase("OVER")  || rowObject.getPopType().equalsIgnoreCase("UNSPECIFIED")){
							// PASS
						}else{
							if(TestCaseUtils.verifyResult(false, "Incorrect pop action triggered!", rowObject, outputData))
								return;
						}
					}
				}else if(currentActiveWindowTitle.equalsIgnoreCase(tempTitle) || currentActiveWindowTitle.contains(tempTitle)){
					// POP UNDER
					if(rowObject.getExpectedPop().equalsIgnoreCase("UNDER")){
						// PASS
					}else{
						if(TestCaseUtils.verifyResult(false, "Incorrect pop action triggered!", rowObject, outputData))
							return;
					}
				}else{
					// POP OVER
					if(rowObject.getExpectedPop().equalsIgnoreCase("OVER")  || rowObject.getPopType().equalsIgnoreCase("UNSPECIFIED")){
						// PASS
					}else{
						if(TestCaseUtils.verifyResult(false, "Incorrect pop action triggered!", rowObject, outputData))
							return;
					}
				}
			}else{
				if(TestCaseUtils.verifyResult(false, "Unable to capture window title!", rowObject, outputData))
					return;
			}				
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
		/*try{
			RowObject rowObject = outputData.get(outputData.size()-1);
			logger.info("rowCount : " + rowObject.getRowNumber() + ", OS : " + rowObject.getOs() + ", OsVersion : " + rowObject.getOsVersion() + ", Browser :" +rowObject.getBrowser() + ", BrowserVersion : " +rowObject.getBrowserVersion() +", HTMLELement : "+rowObject.getHtmlElement() +", POP Type : "+rowObject.getPopType()+", SmartSwitch Type : "+rowObject.getSmartSwitchType()+", Expected Pop Type : "+rowObject.getExpectedResult()+", TestCase Result ->"+rowObject.getTestCaseResult());
		}catch(Exception e){
			
		}*/
	}
	
	@AfterTest(alwaysRun=true)
	public void writeTestCaseResults() throws IOException{	
		
		logger.info("afterTest of AggressivePop");
		if(!outputData.isEmpty()){				
			aggressivePopDataHandler.writeTestCaseResults(outputData, "OutputData", System.getProperty("desktop_iyd_output_file_aggressive_pop"), System.getProperty("desktop_iy_output_file_aggressive_pop"), 
					System.getProperty("mobile_iyd_output_file_aggressive_pop"), System.getProperty("mobile_iy_output_file_aggressive_pop"), startTime);	
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
	
	private void waitForLoad(WebDriver driver) {
	    ExpectedCondition<Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                //return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            	return driver.getTitle() != null;
	            }
	        };
	    WebDriverWait wait = new WebDriverWait(driver, 45);
	    wait.until(pageLoadCondition);
	}
}