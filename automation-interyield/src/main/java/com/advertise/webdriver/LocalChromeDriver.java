/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advertise.webdriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.advertise.webdriver.Credentials;


public class LocalChromeDriver {

	private static String MAC_DRIVER = "/chromedriver/mac/chromedriver";
	private static String WINDOWS_DRIVER = "/chromedriver/windows/chromedriver.exe";
	private static String LINUX_DRIVER = "/chromedriver/linux/chromedriver";

	static void setupChromeDriver() {
		if(StringUtils.isNotBlank(System.getProperty("webdriver.chrome.driver"))) {
			return;
		}
		// OS type
		if (System.getProperty("os.name").contains("Mac")) {
			setChromeDriverPath(MAC_DRIVER,"mac");
		} 
		else if (System.getProperty("os.name").contains("nix") || System.getProperty("os.name").contains("nux") || System.getProperty("os.name").contains("aix")) {
			setChromeDriverPath(LINUX_DRIVER,"linux");
		}
		else {
			setChromeDriverPath(WINDOWS_DRIVER, "windows");
		}
	}

	private static void setChromeDriverPath(String driver, String suffix) {
		InputStream in = LocalChromeDriver.class.getResourceAsStream(driver);
		File tempDriverFile;
		try {
			tempDriverFile = Files.createTempFile("chromedriver", suffix).toFile();
			if(!tempDriverFile.canExecute()) {
				tempDriverFile.setExecutable(true);
				
			}
			Files.copy(in, tempDriverFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
			if( System.getProperty("os.name").contains("nix") || System.getProperty("os.name").contains("nux") || System.getProperty("os.name").contains("aix") ) {
				final String path = tempDriverFile.getAbsolutePath();
				Runtime.getRuntime().exec("chmod u+x "+path);
			}
			System.setProperty("webdriver.chrome.driver", tempDriverFile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static WebDriver getLocalChromeDriver() {
		setupChromeDriver();
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public static WebDriver getLocalChromeDriver( DesiredCapabilities caps ) {
		setupChromeDriver();
		WebDriver driver = new ChromeDriver( caps );
		return driver;
	}
	
	public static WebDriver getLocalChromeDriverWithLogging() {
		
		return getLocalChromeDriver(getFullLoggingCaps(null));
	}
	
	
	public static WebDriver getLocalChromeDriverWithLogging(DesiredCapabilities caps) {
		
		return getLocalChromeDriver(getFullLoggingCaps(caps));
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
	
	public static WebDriver getRemoteChromeDriver () throws MalformedURLException {
		setupChromeDriver();
        DesiredCapabilities caps = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        caps.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver driver = new RemoteWebDriver(new URL(Credentials.getBrowserStackURL()), caps);
        return driver;
    }
}
