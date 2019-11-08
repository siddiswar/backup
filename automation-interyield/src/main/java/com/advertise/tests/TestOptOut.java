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
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import com.advertise.utils.JavaScriptUtils;
import com.advertise.utils.TestCaseUtils;
import com.advertise.utils.WebpageUtils;
import com.advertise.webdriver.BrowserInject;
import com.advertise.webdriver.WebDriverFactory;

public class TestOptOut {
	
	WebDriver webDriver;
	String publisherURL;
	String interYieldServerURL;
	String htmlElement;
	String publisherDomain;
	String interYieldDomain;
	CommonDataHandler optoutDataHandler;	
	List<RowObject> outputData;
	
	Date startTime;
	
	Logger logger = Logger.getLogger(TestOptOut.class.getName());
	
	@BeforeTest(alwaysRun=true)
	public void beforeTest(){
		logger.info("beforeTest of OptOut");
		WebDriverFactory.getInstance();		
		publisherURL = System.getProperty("publisher_url");
		publisherDomain = System.getProperty("publisher_domain");

		interYieldServerURL = System.getProperty("interyield_server_url");	
		interYieldDomain = System.getProperty("interyield_domain");

		htmlElement = System.getProperty("html_element");
		
		optoutDataHandler = new CommonDataHandler();
		outputData = new ArrayList<RowObject>();
		
		startTime = Calendar.getInstance().getTime();		
	}	
	
	@DataProvider(name="getDesktopDataFromExcel")
	public Object[][] getDesktopDataFromExcel() throws IOException {
		List<RowObject> inputData = optoutDataHandler.prepareTestCases("desktop", System.getProperty("template_file_optout"), System.getProperty("template_sheet_optout"),
				System.getProperty("desktop_file_optout"), System.getProperty("desktop_sheet_optout"));
		
		Collections.sort(inputData, new RowObjectComparator(new OSComparator(), new BrowserNameComparator()));
		
		optoutDataHandler.writeTestCases(inputData, "desktop", "InputData", System.getProperty("desktop_input_file_optout"), startTime);
		
		Object[][] rowObjects = new Object[inputData.size()][];		
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}		
		return rowObjects;	
	}
	
	@DataProvider(name="getMobileDataFromExcel")
	public Object[][] getMobileDataFromExcel() throws IOException{
		List<RowObject> inputData = optoutDataHandler.prepareTestCases("mobile", System.getProperty("template_file_optout"), System.getProperty("template_sheet_optout"),
				System.getProperty("mobile_file_optout"), System.getProperty("mobile_sheet_optout"));
		
		Collections.sort(inputData, new RowObjectComparator(new PlatformComparator(), new BrowserNameComparator()));
		
		optoutDataHandler.writeTestCases(inputData, "mobile", "InputData", System.getProperty("mobile_input_file_optout"), startTime);

		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			rowObjects[i] = new Object[] { inputData.get(i) };
		}		
		return rowObjects;	
	}	
	
	@Test(dataProvider = "getDesktopDataFromExcel",groups = {"AllTests","IYTests","DesktopTests","DesktopIYTests","OptOutTests",
			"IYOptOutTests","DesktopOptOutTests"},timeOut=180000)
	public void testOptoutForDesktopIY(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testOptoutForDesktopIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );

		testOptOutBehaviour(rowObject, false, true);		
	}
//No IYD cases for opt out functionality	
/*	@Test(dataProvider = "getDesktopDataFromExcel",groups = {"AllTests","IYDTests","DesktopTests","DesktopIYDTests","OptOutTests",
			"IYDOptOutTests","DesktopOptOutTests"}, description="Test IYD Optout functionality for Desktop")
	public void testOptoutForDesktopIYD(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testOptoutForDesktopIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + " , OsVersion : " + rowObject.getOsVersion() + " , Browser :" +rowObject.getBrowser() + " , BrowserVersion : " +rowObject.getBrowserVersion() );

		testOptOutBehaviour(rowObject, true, true);		
	}*/
	
	@Test(dataProvider = "getMobileDataFromExcel", groups = {"AllTests","IYTests","MobileTests","MobileIYTests","OptOutTests",
			"IYOptOutTests","MobileOptOutTests"},timeOut=300000)
	public void testOptoutForMobileIY(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testOptoutForMobileIY for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		testOptOutBehaviour(rowObject, false, false);
	}

	//No IYD cases for opt out functionality		
/*	@Test(dataProvider = "getMobileDataFromExcel", groups = {"AllTests","IYDTests","MobileTests","MobileIYDTests","OptOutTests",
			"IYDOptOutTests","MobileOptOutTests"},description="Test IYD Optout functionality for Mobile")
	public void testOptoutForMobileIYD(RowObject rowObject){
		
		logger.info("======================================================================================");
		logger.info("Running testOptoutForMobileIYD for row ->"+rowObject.getRowNumber());
		logger.info("rowObject => Device : " + rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() + " , BrowserName :" +rowObject.getBrowser());
		testOptOutBehaviour(rowObject, true, false);		
	}
	*/
	public void testOptOutBehaviour(RowObject rowObject, boolean isIYD, boolean isDesktop){
		try{
			rowObject.setIYD(isIYD);
			
			rowObject.setHtmlElement(htmlElement);
			
			webDriver = WebDriverFactory.getInstance().getWebDriver(rowObject, false);
			
			TestCaseUtils.verifyResult(webDriver, "WebDriver not instantiated !!", rowObject, outputData);
			
			webDriver.manage().deleteAllCookies();
			
			TestCaseUtils.verifyResult(publisherURL, "Publisher URL cannot be empty !!", rowObject, outputData);
			
			webDriver.get(publisherURL);
			
			Thread.sleep(1500);
			
			Map<String,String> paramMap = new HashMap<String,String>();			
			
			if(isIYD){
				paramMap.put("attributionDisabled", "true");
				paramMap.put("pop", "under");
			}else{
				paramMap.put("attributionDisabled", "false");				
			}
			
			paramMap.put("pop", "over");
			
			TestCaseUtils.verifyResult(interYieldServerURL, "No script url provided to execute the script !!", rowObject, outputData);
			
			String url = WebpageUtils.getURL(interYieldServerURL, paramMap);
			
			TestCaseUtils.verifyResult(url, "Constructed script url is Invalid !!", rowObject, outputData);
			
			//waitForLoad(webDriver);
			Thread.sleep(2500);
			
			BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);
			
			Thread.sleep(2500);
			
			BrowserInject.injectJS(url, webDriver);
			
			//waitForLoad(webDriver);
			Thread.sleep(5000);
			
			TestCaseUtils.verifyResult(TestCaseUtils.checkForAdCoverage(rowObject, webDriver), "No Ad Coverage !!", rowObject, outputData);
			
			//Do click event			
			if(TestCaseUtils.verifyResult(IYUtils.hasPopTriggered(webDriver, rowObject, publisherURL), "No POP action triggered !!", rowObject, outputData)){
				return;
			}

			Thread.sleep(5000);
			
			//switch to ad window.
			
			String currentWindowHandle = webDriver.getWindowHandle();
			Set<String> outerWindowHandles = webDriver.getWindowHandles();
			
			for(String outerWindowHandle : outerWindowHandles){
				if(!outerWindowHandle.equals(currentWindowHandle)){
					webDriver.switchTo().window(outerWindowHandle);
				}	
			}
			
			//Firing a new URL in ad window. When ad landing page is invalid IY script doesn't execute and hence attribution window doesn't show up.So loading with a new valid URL
			((JavascriptExecutor)webDriver).executeScript("window.location.href = '"+publisherURL+"'");
			
			webDriver.navigate().refresh();
			
			Thread.sleep(2000);
			
			BrowserInject.injectJS(url, webDriver);
			
			Thread.sleep(4000);
			
			//Check for attribution Window
			WebElement attributionWindowElement = null;
			
			boolean attributionWindowElementFound = true;
			
			try{
				attributionWindowElement = webDriver.findElement(By.linkText(WebpageUtils.getParameterValue(url, "attributionTitle")));
			}catch(Exception e){
				attributionWindowElementFound = false;
			}
			
			TestCaseUtils.verifyResult(attributionWindowElementFound, "Attribution Window is not found !!", rowObject, outputData);

			// Click on attribution window
			attributionWindowElement.click();
			
			Thread.sleep(4000);
			
			//Check whether opt out page is opened
			boolean optOutPageIsOpened = false;
			outerWindowHandles = webDriver.getWindowHandles();
			
			for(String outerWindowHandle : outerWindowHandles){
				webDriver.switchTo().window(outerWindowHandle);
				if(webDriver.getCurrentUrl().contains("InterYield/optout.do")){
					optOutPageIsOpened = true;
					break;
				}
			}
			
			TestCaseUtils.verifyResult(optOutPageIsOpened, "OptOut page Is not found !!", rowObject, outputData);
			
			//Check whether Optout button is found on optout page
			WebElement optOutButtonElement = null;
			boolean isOptOutButtonElementFound = true ;
			try {
				optOutButtonElement = (new WebDriverWait(webDriver, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@href]")));
			} catch (Exception e) {
				isOptOutButtonElementFound = false;
			}
			
			TestCaseUtils.verifyResult(isOptOutButtonElementFound, "OptOut Button is not found in optout page !!", rowObject, outputData);

			//click optout button
			optOutButtonElement.click();
			
			//wait for alert
			Thread.sleep(1000);
			//Accept alert
			webDriver.switchTo().alert().accept(); 
			
			//read opuout cookie. optout cookie always gets created on IY domain.			
			((JavascriptExecutor)webDriver).executeScript("window.location.href = '"+interYieldServerURL+"'");
			
			webDriver.navigate().refresh();
			
			Thread.sleep(2000);
			
			String optoutCookieName = "optout";
			Cookie  optoutCookie = null;
			optoutCookie = webDriver.manage().getCookieNamed(optoutCookieName);			
			
			TestCaseUtils.verifyResult(optoutCookie, "Optout cookie does not exist", rowObject, outputData);
			
			TestCaseUtils.verifyResult(optoutCookie.getValue().equalsIgnoreCase("true"), "Optout cookie value is not TRUE !!", rowObject, outputData);
			
			if(isIYD){
				TestCaseUtils.verifyResult(optoutCookie.getDomain(), publisherDomain, "Incorrect optout cookie Domain !!", rowObject, outputData);			
			}else{
				TestCaseUtils.verifyResult(optoutCookie.getDomain(), interYieldDomain, "Incorrect optout cookie Domain !!", rowObject, outputData);
			}			
	
			//Interyield should be suspended when optout is set(Check for "InterYield click bind suspended" message in console log)
			logger.info("No further Ad pops should get trigger on performing any event as optout cookie is set to true !!");
			
			webDriver.navigate().to(publisherURL);
			
			Thread.sleep(2000);
			
			BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);
			
			Thread.sleep(2500);
			
			BrowserInject.injectJS(url, webDriver);
		
			Thread.sleep(5000);
			
			TestCaseUtils.verifyResult(JavaScriptUtils.checkForSuspendDueToOptOut(rowObject, webDriver), "Interyield is not suspended even though Optout is set !!", rowObject, outputData);
			
			//Pop should not happen
			TestCaseUtils.verifyResult(!IYUtils.hasPopTriggered(webDriver, rowObject, publisherURL), "Pop happened even though optout is set to true !!", rowObject, outputData);			
		
			rowObject.setTestCaseResult("PASS");
			
		}catch(Exception e){
			logger.error(e);
			//e.printStackTrace();
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
	public void tearDown() throws IOException{
		
		logger.info("afterTest of OptOut");
		if(webDriver != null){
			webDriver.quit();
		}
		if(!outputData.isEmpty()){			
			optoutDataHandler.writeTestCaseResults(outputData, "OutputData", System.getProperty("desktop_iyd_output_file_optout"), System.getProperty("desktop_iy_output_file_optout"), 
					System.getProperty("mobile_iyd_output_file_optout"), System.getProperty("mobile_iy_output_file_optout"), startTime);
		}	
		logger.info(TestCaseUtils.getTestCaseRunTime(startTime));	
	}
}