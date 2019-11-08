package com.advertise.tests;

/**
 * @author siddu
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
import org.openqa.selenium.firefox.FirefoxProfile;
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
import com.advertise.datahandler.PopDataHandler;
import com.advertise.model.RowObject;
import com.advertise.utils.TestCaseUtils;
import com.advertise.utils.WebpageUtils;
import com.advertise.utils.WindowUtils;
import com.advertise.webdriver.BrowserInject;
import com.advertise.webdriver.WebDriverFactory;

public class TestCombinedPop {

	Logger logger = Logger.getLogger(TestCombinedPop.class.getName());

	WebDriver webDriver;

	String publisherURL;
	String interYieldServerURL;
	String iytAffiliate;
	String iytSubAffiliate;
	String nonIytAffiliate;
	String nonIytSubAffiliate;

	PopDataHandler combinedPopDataHandler;

	List<RowObject> outputData;

	Date startTime;

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {

		logger.info("beforeTest of MasterPop");
		WebDriverFactory.getInstance();

		combinedPopDataHandler = new PopDataHandler();

		publisherURL = System.getProperty("publisher_url");
		interYieldServerURL = System.getProperty("interyield_server_url");
		iytAffiliate = System.getProperty("iyt_affiliate");
		iytSubAffiliate = System.getProperty("iyt_subaffiliate");
		nonIytAffiliate = System.getProperty("noniyt_affiliate");
		nonIytSubAffiliate = System.getProperty("noniyt_subaffiliate");

		startTime = Calendar.getInstance().getTime();

		outputData = new ArrayList<RowObject>();
	}

	@DataProvider(name = "getDesktopDataFromExcel")
	public Object[][] getDesktopDataFromExcel() throws IOException {
		List<RowObject> inputData = combinedPopDataHandler.prepareTestCases("desktop", System.getProperty("template_file_combined_pop"), System.getProperty("scenario_sheet_combined_pop"), System.getProperty("html_sheet_combined_pop"),
				System.getProperty("browser_sheet_combined_pop"),System.getProperty("desktop_sheet_master_pop"),System.getProperty("pop_functionalities_sheet_combined_pop"));

		Collections.sort(inputData, new RowObjectComparator(new OSComparator(), new BrowserNameComparator()));

		combinedPopDataHandler.writeTestCases(inputData, "desktop", "InputData", System.getProperty("desktop_input_file_combined_pop"), startTime);

		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			RowObject rowObject = inputData.get(i);
			rowObject.setPlatformType("desktop");
			rowObjects[i] = new Object[] { rowObject };

		}
		return rowObjects;
	}

	@DataProvider(name = "getMobileDataFromExcel")
	public Object[][] getMobileDataFromExcel() throws IOException {
		List<RowObject> inputData = combinedPopDataHandler.prepareTestCases("mobile", System.getProperty("template_file_combined_pop"), System.getProperty("template_sheet_combined_pop"), System.getProperty("html_sheet_combined_pop"),
				System.getProperty("browser_sheet_combined_pop"), System.getProperty("mobile_file_combined_pop"), System.getProperty("mobile_sheet_combined_pop"));

		Collections.sort(inputData, new RowObjectComparator(new PlatformComparator(), new BrowserNameComparator()));

		combinedPopDataHandler.writeTestCases(inputData, "mobile", "InputData", System.getProperty("mobile_input_file_combined_pop"), startTime);

		Object[][] rowObjects = new Object[inputData.size()][];
		for (int i = 0; i < inputData.size(); i++) {
			RowObject rowObject = inputData.get(i);
			rowObject.setPlatformType("mobile");
			rowObjects[i] = new Object[] { rowObject };
		}
		return rowObjects;
	}

	@Test(dataProvider = "getDesktopDataFromExcel", groups = { "AllTests", "IYTests", "DesktopTests", "DesktopIYTests", "CombinedPopTests", "IYCombinedPopTests", "DesktopCombinedPopTests",
			"DesktopPopTests" }, description = "Testing IY Master Pop behavior for Desktop")
	public void testCombinedPopForDesktop(RowObject rowObject) {

		logger.info("======================================================================================");
		logger.info("Running testCombinedPopForDesktop for row ->" + rowObject.getRowNumber());
		logger.info("rowObject => OS : " + rowObject.getOs() + ", OsVersion : " + rowObject.getOsVersion() + ", Browser :" + rowObject.getBrowser() + ", BrowserVersion : " + rowObject.getBrowserVersion() + ", HTMLELement : "
				+ rowObject.getHtmlElement() + ", POP Type : " + rowObject.getPopType());

		testPopBehaviour(rowObject, false, true);
	}

	/*
	 * @Test(dataProvider = "getDesktopDataFromExcel",groups =
	 * {"AllTests","IYDTests"
	 * ,"DesktopTests","DesktopIYDTests","CombinedPopTests",
	 * "IYDCombinedPopTests","DesktopCombinedPopTests","DesktopPopTests"},
	 * description = "Testing IYD Master Pop behavior for Desktop") public void
	 * testCombinedPopForDesktopIYD(RowObject rowObject){
	 * 
	 * logger.info(
	 * "======================================================================================"
	 * ); logger.info("Running testCombinedPopForDesktopIYD for row ->"
	 * +rowObject .getRowNumber()); logger.info("rowObject => OS : " +
	 * rowObject.getOs() + ", OsVersion : " + rowObject.getOsVersion() +
	 * ", Browser :" +rowObject.getBrowser() + ", BrowserVersion : "
	 * +rowObject.getBrowserVersion() +", HTMLELement : "
	 * +rowObject.getHtmlElement() +", POP Type : "+rowObject.getPopType());
	 * 
	 * if(rowObject.getPopType() != null &&
	 * (rowObject.getPopType().equalsIgnoreCase("over") ||
	 * rowObject.getPopType().equalsIgnoreCase("UNSPECIFIED"))){ logger.warn(
	 * "This test case has run for IYD and for IYD pop should be always under i.e. pop=under. Hence could not run test case for pop=over for IYD, rowCount ->"
	 * +rowObject.getRowNumber()); return; }
	 * 
	 * testPopBehaviour(rowObject, true, true); }
	 */
	/*
	 * @Test(dataProvider = "getMobileDataFromExcel",groups =
	 * {"AllTests","IYTests","MobileTests","MobileIYTests","CombinedPopTests",
	 * "IYCombinedPopTests","MobileCombinedPopTests","MobilePopTests"},
	 * description = "Testing IY Master Pop behavior for Mobile") public void
	 * testCombinedPopForMobileIY(RowObject rowObject){
	 * 
	 * logger.info(
	 * "======================================================================================"
	 * ); logger.info("Running testCombinedPopForMobileIY for row ->"+rowObject.
	 * getRowNumber()); logger.info("rowObject => Device : " +
	 * rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() +
	 * " , BrowserName :" +rowObject.getBrowser());
	 * 
	 * testPopBehaviour(rowObject, false, false); }
	 * 
	 * @Test(dataProvider = "getMobileDataFromExcel",groups =
	 * {"AllTests","IYDTests","MobileTests","MobileIYDTests","CombinedPopTests",
	 * "IYDCombinedPopTests","MobileCombinedPopTests","MobilePopTests"},
	 * description = "Testing IYD Master Pop behavior for Mobile") public void
	 * testCombinedPopForMobileIYD(RowObject rowObject){
	 * 
	 * logger.info(
	 * "======================================================================================"
	 * ); logger.info("Running testCombinedPopForMobileIYD for row ->"
	 * +rowObject. getRowNumber()); logger.info("rowObject => Device : " +
	 * rowObject.getDevice() + " , Platform : " + rowObject.getPlatform() +
	 * " , BrowserName :" +rowObject.getBrowser());
	 * 
	 * testPopBehaviour(rowObject, true, false); }
	 */

	public void testPopBehaviour(RowObject rowObject, boolean isIYD, boolean isDesktop) {
		try {
			// rowObject.setIYD(isIYD);

			/* get instance of webDriver */
			webDriver = WebDriverFactory.getInstance().getWebDriver(rowObject, true);

			if (TestCaseUtils.verifyResult(webDriver, "WebDriver not instantiated", rowObject, outputData))
				return;

			/* delete the cache from the webDriver */
			webDriver.manage().deleteAllCookies();

			if (TestCaseUtils.verifyResult(publisherURL, "testURL cannot be empty", rowObject, outputData))
				return;

			/* set sample url to webDriver */
			webDriver.get(publisherURL);

			// waitForLoad(webDriver);
			Thread.sleep(2500);

			if (TestCaseUtils.verifyResult(interYieldServerURL, "No script url provided to execute the script!", rowObject, outputData))
				return;

			Map<String, String> paramMap = new HashMap<String, String>();

			paramMap.put("attributionDisabled", rowObject.getAttributionDisabled().toLowerCase());

			if (rowObject.getPopType().equalsIgnoreCase("UNSPECIFIED")) {
				paramMap.put("pop", "");

			} else {
				paramMap.put("pop", rowObject.getPopType().toLowerCase());
			}

			if (rowObject.getPopOption().equalsIgnoreCase("UNSPECIFIED")) {
				paramMap.put("popoption", "");
			} else {
				paramMap.put("popoption", rowObject.getPopOption().toLowerCase());

			}

			if (rowObject.getChromeOption().equalsIgnoreCase("UNSPECIFIED")) {
				paramMap.put("chromeoption", "");
			} else {
				paramMap.put("chromeoption", rowObject.getChromeOption().toLowerCase());

			}

			if (rowObject.getMacOption().equalsIgnoreCase("UNSPECIFIED")) {
				paramMap.put("macoption", "");
			} else {
				paramMap.put("macoption", rowObject.getMacOption().toLowerCase());

			}

			if (rowObject.getIsIYT().equalsIgnoreCase("YES")) {
				paramMap.put("affiliate", iytAffiliate);
				paramMap.put("subid", iytSubAffiliate);
			} else {
				paramMap.put("affiliate", nonIytAffiliate);
				paramMap.put("subid", nonIytSubAffiliate);
			}

			if (rowObject.getAttributionDisabled().equalsIgnoreCase("true") && rowObject.getPopType().equalsIgnoreCase("under")) {
				rowObject.setIYD(true);
			} else {
				rowObject.setIYD(false);
			}

			/*
			 * Construct and get Interyield server url appending parameters as
			 * per input data
			 */
			String url = WebpageUtils.getURL(interYieldServerURL, paramMap);

			System.out.println("URL :" + url);
			if (TestCaseUtils.verifyResult(url, "constructed script url is Invalid!", rowObject, outputData))
				return;

			/* get title of the user web page(sample web page) */
			String originalTitle = webDriver.getTitle();
			String tempTitle = null;

			if (!rowObject.getHtmlElement().equalsIgnoreCase("WHITESPACE")) {
				try {
					/*
					 * get window title after performing event on web page
					 * before injecting script to page
					 */
					tempTitle = WebpageUtils.getUserWindowTitle(rowObject, publisherURL, outputData);
				} catch (Exception e) {
					if (webDriver != null) {
						webDriver.quit();
					}
					rowObject.setTestCaseResult("FAIL");
					rowObject.setComments(e.getMessage());
					outputData.add(rowObject);
					logger.info("RowCount->" + rowObject.getRowNumber() + ", TestCaseResult->" + rowObject.getTestCaseResult());
					return;
				}
			} else {
				tempTitle = originalTitle;
			}

			webDriver.manage().deleteAllCookies();

			BrowserInject.injectOverrideConsoleLogScript(rowObject, webDriver);

			Thread.sleep(2500);

			logger.info("Interyield script injected is :: " + url);

			/* injecting script to web page */
			BrowserInject.injectJS(url, webDriver);

			Thread.sleep(5000);

			if (TestCaseUtils.verifyResult(TestCaseUtils.checkForAdCoverage(rowObject, webDriver), "No Ad Coverage!", rowObject, outputData)) {
				return;
			}

			WebElement webElement = WebpageUtils.getWebElement(webDriver, rowObject);

			/* perform event on the web page */
			webElement.click();

			Thread.sleep(2500);

			Set<String> handleSet = webDriver.getWindowHandles();
			if (TestCaseUtils.verifyResult(handleSet.size() == 2, "No Pop Action Triggered!", rowObject, outputData))
				return;

			webDriver.manage().deleteAllCookies();

			// waitForLoad(webDriver);
			Thread.sleep(2500);

			/* get active window title of the system using native api calls */
			String currentActiveWindowTitle = WindowUtils.getActiveWindowTitle();

			if (TestCaseUtils.verifyResult(!currentActiveWindowTitle.isEmpty(), "Unable to capture current active window title!", rowObject, outputData))
				return;

			logger.info("currently Active Window Title Is::" + currentActiveWindowTitle);

			currentActiveWindowTitle = currentActiveWindowTitle.substring(0, currentActiveWindowTitle.lastIndexOf(" - "));

			/* verify Pop Over or Under */
			if (originalTitle != null && tempTitle != null) {
				if (originalTitle.equalsIgnoreCase(tempTitle)) {
					if (currentActiveWindowTitle.equalsIgnoreCase(originalTitle) || currentActiveWindowTitle.contains(originalTitle)) {
						// POP UNDER
						if (rowObject.getExpectedPop().equalsIgnoreCase("UNDER")) {
							// PASS
						} else {
							if (TestCaseUtils.verifyResult(false, "Incorrect pop action triggered! Expected pop is UNDER", rowObject, outputData))
								return;
						}
					} else {
						// POP OVER
						if (rowObject.getExpectedPop().equalsIgnoreCase("OVER")) {
							// PASS
						} else {
							if (TestCaseUtils.verifyResult(false, "Incorrect pop action triggered! Expected pop is OVER", rowObject, outputData))
								return;
						}
					}
				} else if (currentActiveWindowTitle.equalsIgnoreCase(tempTitle) || currentActiveWindowTitle.contains(tempTitle)) {
					// POP UNDER
					if (rowObject.getExpectedPop().equalsIgnoreCase("UNDER")) {
						// PASS
					} else {
						if (TestCaseUtils.verifyResult(false, "Incorrect pop action triggered! Expected pop is UNDER", rowObject, outputData))
							return;
					}
				} else {
					// POP OVER
					if (rowObject.getExpectedPop().equalsIgnoreCase("OVER")) {
						// PASS
					} else {
						if (TestCaseUtils.verifyResult(false, "Incorrect pop action triggered! Expected pop is OVER", rowObject, outputData))
							return;
					}
				}
			} else {
				if (TestCaseUtils.verifyResult(false, "Unable to capture window title!", rowObject, outputData))
					return;
			}
			
			boolean isPopHappenedInExpectedWindowOrTab=rowObject.getExpectedWindowOrTab().equalsIgnoreCase(WindowUtils.popHappenedWindowOrTab(rowObject.getBrowser()));
			
			TestCaseUtils.verifyResult(isPopHappenedInExpectedWindowOrTab, "Actual and expected popHappenedINWindowOrTab are not matching", rowObject, outputData);
			
			System.out.println("========================================================================================PASS========================================================================================");
		
			rowObject.setTestCaseResult("PASS");

		} catch (Exception e) {
			logger.error(e);
			if (webDriver != null)
				webDriver.quit();
			rowObject.setTestCaseResult("FAIL");
			rowObject.setComments(e.getMessage());
		}
		logger.info("RowCount->" + rowObject.getRowNumber() + ", TestCaseResult->" + rowObject.getTestCaseResult());
		outputData.add(rowObject);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void closeAndQuitDriver() {
		if (webDriver != null) {
			webDriver.quit();
		}
		/*
		 * try{ RowObject rowObject = outputData.get(outputData.size()-1);
		 * logger.info("rowCount : " + rowObject.getRowNumber() + ", OS : " +
		 * rowObject.getOs() + ", OsVersion : " + rowObject.getOsVersion() +
		 * ", Browser :" +rowObject.getBrowser() + ", BrowserVersion : "
		 * +rowObject.getBrowserVersion() +", HTMLELement : "
		 * +rowObject.getHtmlElement() +", POP Type : "+rowObject .getPopType()+
		 * ", TestCase Result ->"+rowObject.getTestCaseResult());
		 * }catch(Exception e){
		 * 
		 * }
		 */
	}

	@AfterTest(alwaysRun = true)
	public void writeTestCaseResults() throws IOException {

		logger.info("afterTest of Combined Pop");
		/* Writing test case results to the excel sheet */
		if (!outputData.isEmpty()) {
			combinedPopDataHandler.writeTestCaseResults(outputData, "OutputData", System.getProperty("desktop_output_file_combined_pop"), System.getProperty("mobile_output_file_combined_pop"), startTime);
		}
		if (outputData != null) {
			outputData.clear();
			outputData = null;
		}
		if (webDriver != null) {
			webDriver.quit();
		}
		logger.info(TestCaseUtils.getTestCaseRunTime(startTime));
	}
}