/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advertise.webdriver;
/**
 * @author rahul
 *
 */
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.advertise.model.RowObject;

public class WebDriverFactory {

	URL url;
	boolean runLocal;
	private static final WebDriverFactory webDriverFactory = new WebDriverFactory();
	
	private WebDriverFactory() {
		init();
		if(System.getProperty("run_mode").equalsIgnoreCase("local")){
			runLocal = true;
		}else{
			runLocal = false;
			try {
				url = new URL(System.getProperty("remote_url"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}				
	}	

	public static WebDriverFactory getInstance() {
		return webDriverFactory;
	}
	
	static void init() {
		Properties props = new Properties();
		try {
			props.load(ClassLoader.getSystemResourceAsStream("driver.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		System.getProperties().putAll(props);
	}
	
	public WebDriver getWebDriverForMobile(RowObject rowObject) {
		DesiredCapabilities capabilities = null;
		
		if(rowObject.getBrowser().equalsIgnoreCase("iPhone")){			
			capabilities = DesiredCapabilities.iphone();
			
			capabilities.setCapability("device", rowObject.getDevice());
			capabilities.setCapability("platform", rowObject.getPlatform()); // MAC, ANDROID, WINDOWS
			capabilities.setCapability("browserName", rowObject.getBrowser());	 // iPhone, iPad, android							
			
		}else if(rowObject.getBrowser().equalsIgnoreCase("iPad")){						
			capabilities = DesiredCapabilities.ipad();		
			
			capabilities.setCapability("device", rowObject.getDevice());
			capabilities.setCapability("platform", rowObject.getPlatform());
			capabilities.setCapability("browserName", rowObject.getBrowser());				

		}else if(rowObject.getBrowser().equalsIgnoreCase("android")){				
			capabilities = DesiredCapabilities.android();    
		    
			capabilities.setCapability("device", rowObject.getDevice());
			capabilities.setCapability("platform", rowObject.getPlatform());
			capabilities.setCapability("browserName", rowObject.getBrowser());							
		}
		
		if(capabilities != null){
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			
			capabilities.setCapability("browserstack.safari.enablePopups", true);
			capabilities.setCapability("browserstack.ie.enablePopups", true);
			capabilities.setCapability("browserstack.debug", true);
		    capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);		    
		    capabilities.setJavascriptEnabled(true);
		}		
		
		return new RemoteMobileWebDriver(url, capabilities);
	}
	
	public WebDriver getWebDriverForDesktop(RowObject rowObject, boolean focusTests) {
		DesiredCapabilities capabilities = null;
		
		if(rowObject.getBrowser().equalsIgnoreCase("IE")){			
			capabilities = DesiredCapabilities.internetExplorer();
			
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);			
			
			capabilities.setJavascriptEnabled(true);	
			
			capabilities.setCapability("os", rowObject.getOs());
			capabilities.setCapability("os_version", rowObject.getOsVersion());				
			capabilities.setCapability("browser", BrowserType.IE);				
			capabilities.setCapability("browser_version", rowObject.getBrowserVersion());				
			
			return getIEDriver(capabilities, focusTests);
			
		}else if(rowObject.getBrowser().equalsIgnoreCase("FIREFOX")){						
			capabilities = DesiredCapabilities.firefox();			
			
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);	
			
			capabilities.setJavascriptEnabled(true);
			
			capabilities.setCapability("os", rowObject.getOs());
			capabilities.setCapability("os_version", rowObject.getOsVersion());				
			capabilities.setCapability("browser", BrowserType.FIREFOX);				
			capabilities.setCapability("browser_version", rowObject.getBrowserVersion());				
			
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("dom.disable_window_flip",false);
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);

			
			return getFirefoxDriver(capabilities, focusTests);
			
		}else if(rowObject.getBrowser().equalsIgnoreCase("CHROME")){				
			capabilities = DesiredCapabilities.chrome();
		    
			ChromeOptions chromeOptions = new ChromeOptions();		    
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.addArguments("--test-type");
			chromeOptions.addArguments("--allow-running-insecure-content");
			chromeOptions.addArguments("--disable-extensions");
			
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);				
						
		    LoggingPreferences logPrefs = new LoggingPreferences();
		    logPrefs.enable(LogType.BROWSER, Level.ALL);		    
		    capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		    
		    capabilities.setJavascriptEnabled(true);
		    
			capabilities.setCapability("os", rowObject.getOs());
			capabilities.setCapability("os_version", rowObject.getOsVersion());				
			capabilities.setCapability("browser", BrowserType.CHROME);				
			capabilities.setCapability("browser_version", rowObject.getBrowserVersion());				
			
		    return getChromeDriver(capabilities, focusTests);	
		    
		}else if(rowObject.getBrowser().equalsIgnoreCase("SAFARI")){			
			capabilities = DesiredCapabilities.safari();			
			
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);			
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);			
			
			capabilities.setJavascriptEnabled(true);
			
			capabilities.setCapability("os", rowObject.getOs());
			capabilities.setCapability("os_version", rowObject.getOsVersion());				
			capabilities.setCapability("browser", BrowserType.SAFARI);				
			capabilities.setCapability("browser_version", rowObject.getBrowserVersion());	
			capabilities.setCapability("browserstack.safari.enablePopups", "true");
			capabilities.setCapability("browserstack.safari.allowAllCookies", true);
			
			return getSafariDriver(capabilities, focusTests);
			
		}else if(rowObject.getBrowser().equalsIgnoreCase("OPERA")){			
			capabilities = DesiredCapabilities.operaBlink();			
			
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);			
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			
			capabilities.setJavascriptEnabled(true);
			
			capabilities.setCapability("os", rowObject.getOs());
			capabilities.setCapability("os_version", rowObject.getOsVersion());				
			capabilities.setCapability("browser", BrowserType.OPERA_BLINK);				
			capabilities.setCapability("browser_version", rowObject.getBrowserVersion());
			
			return getOperaDriver(capabilities, focusTests);			
		}		
		return null;
	}
	
	
	//TODO need to remove isIYD parameter
	public WebDriver getWebDriver(RowObject rowObject, boolean focusTests) {
		if(rowObject.getPlatformType().equalsIgnoreCase("desktop")){
			return getWebDriverForDesktop(rowObject, focusTests);
		}else{
			return getWebDriverForMobile(rowObject);
		}			
	}

	public WebDriver getChromeDriver(DesiredCapabilities capabilities, boolean focusTests) {
		if(runLocal || focusTests) {
			return getLocalChromeDriver(DesiredCapabilities.chrome().merge(capabilities));
		}else {
			capabilities.setCapability("browserstack.debug", true);
			return new RemoteWebDriver(url, DesiredCapabilities.chrome().merge(capabilities));
		}
	}
	
	public WebDriver getLocalChromeDriver(DesiredCapabilities capabilities) {
		if(StringUtils.isNotBlank(System.getProperty("webdriver.chrome.driver"))) { 
			return new ChromeDriver(capabilities);		
		}else {
			throw new IllegalArgumentException("Please specify webdriver.chrome.driver property");
		}
	}
	
	public WebDriver getFirefoxDriver(DesiredCapabilities capabilities, boolean focusTests) {
		if(runLocal || focusTests) {
			return getLocalFirefoxDriver(capabilities);
		}else {
			capabilities.setCapability("browserstack.debug", true);
			return new RemoteWebDriver(url, DesiredCapabilities.firefox().merge(capabilities));
		}
	}
	
	public WebDriver getLocalFirefoxDriver(DesiredCapabilities capabilities) {		
		return new FirefoxDriver(DesiredCapabilities.firefox().merge(capabilities));
	}
	
	public WebDriver getIEDriver(DesiredCapabilities capabilities, boolean focusTests) {
		if(runLocal || focusTests) {
			return getLocalIEDriver(capabilities);
		}else {
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			capabilities.setCapability("resolution", "1920x1200");
			capabilities.setCapability("browserstack.debug", true);
			return new RemoteWebDriver(url, DesiredCapabilities.internetExplorer().merge(capabilities));
		}
	}
	
	public WebDriver getLocalIEDriver(DesiredCapabilities capabilities) {
		if(StringUtils.isNotBlank(System.getProperty("webdriver.ie.driver"))) {
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			capabilities.setCapability("resolution", "1920x1200");
			return new InternetExplorerDriver(DesiredCapabilities.internetExplorer().merge(capabilities));
		}else {
			throw new IllegalArgumentException("Please specify webdriver.ie.driver property");
		}
	}
	
	public WebDriver getOperaDriver(DesiredCapabilities capabilities, boolean focusTests) {
		if(runLocal || focusTests) {
			return getLocalOperaDriver(capabilities);
		}else {
			capabilities.setCapability("browserstack.debug", true);
			return new RemoteWebDriver(url, DesiredCapabilities.operaBlink().merge(capabilities));
		}
	}
	
	public WebDriver getLocalOperaDriver(DesiredCapabilities capabilities) {
		if(StringUtils.isNotBlank(System.getProperty("webdriver.opera.driver"))) { 
			return new OperaDriver(DesiredCapabilities.chrome().merge(capabilities));
		}else {
			throw new IllegalArgumentException("Please specify webdriver.opera.driver property");
		}
	}
	
	public WebDriver getSafariDriver(DesiredCapabilities capabilities, boolean focusTests) {
		if(runLocal || focusTests) {
			return getLocalSafariDriver(capabilities);
		}else {
			capabilities.setCapability("browserstack.safari.enablePopups", "true");
			capabilities.setCapability("browserstack.debug", true);
			return new RemoteWebDriver(url, DesiredCapabilities.safari().merge(capabilities));
		}
	}
	
	public WebDriver getLocalSafariDriver(DesiredCapabilities capabilities) {
		if(StringUtils.isNotBlank(System.getProperty("webdriver.opera.driver"))) { 
			return new SafariDriver(DesiredCapabilities.chrome().merge(capabilities));
		}else {
			throw new IllegalArgumentException("Please specify webdriver.safari.driver property");
		}
	}
	
}