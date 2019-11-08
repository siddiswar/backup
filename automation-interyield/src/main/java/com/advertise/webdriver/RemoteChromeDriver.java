/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advertise.webdriver;

import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author clicata
 */
class RemoteChromeDriver {

    protected static WebDriver getRemoteChromeDriver () {
        
        DesiredCapabilities caps = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        caps.setCapability(ChromeOptions.CAPABILITY, options);

        Properties props = new Properties();
        
        try{
            props.load(RemoteChromeDriver.class.getResourceAsStream("/testing-runtime.properties"));
            String driverPath = props.getProperty("chrome_driver_path");
            System.setProperty("webdriver.chrome.driver", driverPath);
            
            System.out.println(props.toString());
            WebDriver driver = new RemoteWebDriver(new URL(Credentials.getBrowserStackURL()), caps);
            return driver;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

}