/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advertise.webdriver;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class LocalFirefoxDriver {

	public static WebDriver getFirefoxDriver() {
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	public static WebDriver getFirefoxDriver( DesiredCapabilities caps ) {
		WebDriver driver = new FirefoxDriver( caps );
		return driver;
	}
	
	public static WebDriver getLocalChromeDriverWithLogging() {
		
		return getFirefoxDriver(getFullLoggingCaps(null));
	}
	
	
	public static WebDriver getLocalChromeDriverWithLogging(DesiredCapabilities caps) {
		
		return getFirefoxDriver(getFullLoggingCaps(caps));
	}

	static DesiredCapabilities getFullLoggingCaps(DesiredCapabilities caps) {
		if( caps == null ) {
			caps = new DesiredCapabilities();
		}
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        return caps;
    }
	
}
