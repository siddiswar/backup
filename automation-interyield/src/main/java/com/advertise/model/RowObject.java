package com.advertise.model;

/**
 * @author rahul
 *
 */
public class RowObject {

	private String platformType;
	private boolean isIYD;

	private String os;
	private String osVersion;
	private String browser;
	private String browserVersion;

	private String platform;
	private String device;

	private String popType;
	private String htmlElement;

	private String focus;
	private String tabORWindow;
	private String switchRoo;

	private String smartSwitchType;
	private String expectedWindowOrTab;

	private String windowsChromeExpectedPop;
	private String windowsChromeExpectedWindowOrTab;

	private String windowsFirefoxExpectedPop;
	private String windowsFirefoxEpectedWindowOrTab;

	private String windowsIEExpectedPop;
	private String windowsIEExpectedWindowOrTab;

	private String macSafariExpectedPop;
	private String macSafariExpectedWindowOrTab;

	private String adultDisabled;
	private String adultOrNonAdultURL;
	private String shouldSnooze;

	private String attributionDisabled;
	private String isIYT;
	private String popOption;
	private String chromeOption;
	private String macOption;

	private String expectedResult;
	private String testCaseResult;
	
	private String macChromeExpectedPop;
	private String macChromeExpectedWindowOrTab;
	
	
	public String getMacChromeExpectedPop() {
		return macChromeExpectedPop;
	}

	public void setMacChromeExpectedPop(String macChromeExpectedPop) {
		this.macChromeExpectedPop = macChromeExpectedPop;
	}

	public String getMacChromeExpectedWindowOrTab() {
		return macChromeExpectedWindowOrTab;
	}

	public void setMacChromeExpectedWindowOrTab(String macChromeExpectedWindowOrTab) {
		this.macChromeExpectedWindowOrTab = macChromeExpectedWindowOrTab;
	}

	public String getPopFunctionality() {
		return popFunctionality;
	}

	public void setPopFunctionality(String popFunctionality) {
		this.popFunctionality = popFunctionality;
	}

	private String comments;

	
	private String popFunctionality;
	private String testComboNumber;
	
	public String getTestComboNumber() {
		return testComboNumber;
	}

	public void setTestComboNumber(String testComboNumber) {
		this.testComboNumber = testComboNumber;
	}

	private int rowNumber;

	public String getWindowsChromeExpectedPop() {
		return windowsChromeExpectedPop;
	}

	public void setWindowsChromeExpectedPop(String windowsChromeExpectedPop) {
		this.windowsChromeExpectedPop = windowsChromeExpectedPop;
	}

	public String getWindowsChromeExpectedWindowOrTab() {
		return windowsChromeExpectedWindowOrTab;
	}

	public void setWindowsChromeExpectedWindowOrTab(String windowsChromeExpectedWindowOrTab) {
		this.windowsChromeExpectedWindowOrTab = windowsChromeExpectedWindowOrTab;
	}

	public String getWindowsFirefoxExpectedPop() {
		return windowsFirefoxExpectedPop;
	}

	public void setWindowsFirefoxExpectedPop(String windowsFirefoxExpectedPop) {
		this.windowsFirefoxExpectedPop = windowsFirefoxExpectedPop;
	}

	public String getWindowsFirefoxEpectedWindowOrTab() {
		return windowsFirefoxEpectedWindowOrTab;
	}

	public void setWindowsFirefoxEpectedWindowOrTab(String windowsFirefoxEpectedWindowOrTab) {
		this.windowsFirefoxEpectedWindowOrTab = windowsFirefoxEpectedWindowOrTab;
	}

	public String getWindowsIEExpectedPop() {
		return windowsIEExpectedPop;
	}

	public void setWindowsIEExpectedPop(String windowsIEExpectedPop) {
		this.windowsIEExpectedPop = windowsIEExpectedPop;
	}

	public String getWindowsIEExpectedWindowOrTab() {
		return windowsIEExpectedWindowOrTab;
	}

	public void setWindowsIEExpectedWindowOrTab(String windowsIEExpectedWindowOrTab) {
		this.windowsIEExpectedWindowOrTab = windowsIEExpectedWindowOrTab;
	}


	public String getMacSafariExpectedPop() {
		return macSafariExpectedPop;
	}

	public void setMacSafariExpectedPop(String macSafariExpectedPop) {
		this.macSafariExpectedPop = macSafariExpectedPop;
	}

	public String getMacSafariExpectedWindowOrTab() {
		return macSafariExpectedWindowOrTab;
	}

	public void setMacSafariExpectedWindowOrTab(String macSafariExpectedWindowOrTab) {
		this.macSafariExpectedWindowOrTab = macSafariExpectedWindowOrTab;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public String getExpectedWindowOrTab() {
		return expectedWindowOrTab;
	}

	public void setExpectedWindowOrTab(String expectedWindowOrTab) {
		this.expectedWindowOrTab = expectedWindowOrTab;
	}

	public String getAttributionDisabled() {
		return attributionDisabled;
	}

	public void setAttributionDisabled(String attributionDisabled) {
		this.attributionDisabled = attributionDisabled;
	}

	public String getIsIYT() {
		return isIYT;
	}

	public void setIsIYT(String isIYT) {
		this.isIYT = isIYT;
	}

	public String getPopOption() {
		return popOption;
	}

	public void setPopOption(String popOption) {
		this.popOption = popOption;
	}

	public String getChromeOption() {
		return chromeOption;
	}

	public void setChromeOption(String chromeOption) {
		this.chromeOption = chromeOption;
	}

	public String getMacOption() {
		return macOption;
	}

	public void setMacOption(String macOption) {
		this.macOption = macOption;
	}

	/**
	 * @return the platformType
	 */
	public String getPlatformType() {
		return platformType;
	}

	/**
	 * @param platformType
	 *            the platformType to set
	 */
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	/**
	 * @return the isIY
	 */
	public boolean isIYD() {
		return isIYD;
	}

	/**
	 * @param isIY
	 *            the isIY to set
	 */
	public void setIYD(boolean isIYD) {
		this.isIYD = isIYD;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the osVersion
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * @param osVersion
	 *            the osVersion to set
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * @return the browser
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * @param browser
	 *            the browser to set
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/**
	 * @return the browserVersion
	 */
	public String getBrowserVersion() {
		return browserVersion;
	}

	/**
	 * @param browserVersion
	 *            the browserVersion to set
	 */
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform
	 *            the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * @return the popType
	 */
	public String getPopType() {
		return popType;
	}

	/**
	 * @param popType
	 *            the popType to set
	 */
	public void setPopType(String popType) {
		this.popType = popType;
	}

	/**
	 * @return the htmlElement
	 */
	public String getHtmlElement() {
		return htmlElement;
	}

	/**
	 * @param htmlElement
	 *            the htmlElement to set
	 */
	public void setHtmlElement(String htmlElement) {
		this.htmlElement = htmlElement;
	}

	/**
	 * @return the focus
	 */
	public String getFocus() {
		return focus;
	}

	/**
	 * @param focus
	 *            the focus to set
	 */
	public void setFocus(String focus) {
		this.focus = focus;
	}

	/**
	 * @return the tabORWindow
	 */
	public String getTabORWindow() {
		return tabORWindow;
	}

	/**
	 * @param tabORWindow
	 *            the tabORWindow to set
	 */
	public void setTabORWindow(String tabORWindow) {
		this.tabORWindow = tabORWindow;
	}

	/**
	 * @return the switchRoo
	 */
	public String getSwitchRoo() {
		return switchRoo;
	}

	/**
	 * @param switchRoo
	 *            the switchRoo to set
	 */
	public void setSwitchRoo(String switchRoo) {
		this.switchRoo = switchRoo;
	}

	/**
	 * @return the smartSwitchType
	 */
	public String getSmartSwitchType() {
		return smartSwitchType;
	}

	/**
	 * @param smartSwitchType
	 *            the smartSwitchType to set
	 */
	public void setSmartSwitchType(String smartSwitchType) {
		this.smartSwitchType = smartSwitchType;
	}

	/**
	 * @return the expectedResult
	 */
	public String getExpectedPop() {
		return expectedResult;
	}

	/**
	 * @param expectedResult
	 *            the expectedResult to set
	 */
	public void setExpectedPop(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	/**
	 * @return the testCaseResult
	 */
	public String getTestCaseResult() {
		return testCaseResult;
	}

	/**
	 * @param testCaseResult
	 *            the testCaseResult to set
	 */
	public void setTestCaseResult(String testCaseResult) {
		this.testCaseResult = testCaseResult;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the adultDisabled
	 */
	public String getAdultDisabled() {
		return adultDisabled;
	}

	/**
	 * @param adultDisabled
	 *            the adultDisabled to set
	 */
	public void setAdultDisabled(String adultDisabled) {
		this.adultDisabled = adultDisabled;
	}

	/**
	 * @return the adultOrNonAdultURL
	 */
	public String getAdultOrNonAdultURL() {
		return adultOrNonAdultURL;
	}

	/**
	 * @param adultOrNonAdultURL
	 *            the adultOrNonAdultURL to set
	 */
	public void setAdultOrNonAdultURL(String adultOrNonAdultURL) {
		this.adultOrNonAdultURL = adultOrNonAdultURL;
	}

	/**
	 * @return the shouldSnooze
	 */
	public String getShouldSnooze() {
		return shouldSnooze;
	}

	/**
	 * @param shouldSnooze
	 *            the shouldSnooze to set
	 */
	public void setShouldSnooze(String shouldSnooze) {
		this.shouldSnooze = shouldSnooze;
	}

	/**
	 * @return the rowNumber
	 */
	public int getRowNumber() {
		return rowNumber;
	}

	/**
	 * @param rowNumber
	 *            the rowNumber to set
	 */
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
}