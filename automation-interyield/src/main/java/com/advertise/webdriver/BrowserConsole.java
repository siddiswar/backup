package com.advertise.webdriver;
/**
 * @author rahul
 *
 */
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class BrowserConsole {

    public static void printLogs(WebDriver driver) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }
    
    public static LogEntries getLogs(WebDriver driver){
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        return logEntries;
    }
}
