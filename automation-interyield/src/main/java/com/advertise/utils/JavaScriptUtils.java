package com.advertise.utils;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.advertise.model.RowObject;

/**
 * @author rahul
 *
 */
public class JavaScriptUtils {
	
	static Logger logger = Logger.getLogger(JavaScriptUtils.class.getName());
	
	public static boolean checkForSuspendDueToOptOut(RowObject rowObject, WebDriver driver) throws Exception {
		String consoleString = (((JavascriptExecutor)driver).executeScript("return JSON.stringify(logStore)")).toString();		
		if(consoleString.contains("InterYield")){
			if( consoleString.contains("InterYield click bind suspended")){
				return true;
			}else{
				return false;
			}
		}else{
			//Assert.assertEquals(true, false,"IY not fired while checking suspend due to Opt out");
			return false;
		}	
	}
	
	/*
	 * We are actually opening a tab/window which contains cookie information 
	 */
	public static String popNewTabORWindowAndGetItsWindowHandle(WebDriver webDriver){
		
		Set<String> oldWindowHandles = webDriver.getWindowHandles();
		
		((JavascriptExecutor)webDriver).executeScript("window.open('')");				

		Set<String> newWindowHandles = webDriver.getWindowHandles();
		for(String newWindowHandle : newWindowHandles){
			if(!oldWindowHandles.contains(newWindowHandle)){
				webDriver.switchTo().window(newWindowHandle);
				return newWindowHandle;
			}								
		}
		return null;
	}	
}
