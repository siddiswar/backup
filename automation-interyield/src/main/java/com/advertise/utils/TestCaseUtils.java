package com.advertise.utils;

/**
 * @author rahul
 *
 */
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.advertise.model.BrowserType;
import com.advertise.model.PopType;
import com.advertise.model.RowObject;

public class TestCaseUtils {
	
	static Logger logger = Logger.getLogger(TestCaseUtils.class.getName());
	
	public static boolean verifyResult(String expectedResult, String actualResult, String message, RowObject rowObject, List<RowObject> outputData){
		if(!actualResult.equals(expectedResult)){
			rowObject.setTestCaseResult("FAIL");
			if(rowObject.getComments() != null){
				rowObject.setComments(rowObject.getComments() +", " +message);
			}else{
				rowObject.setComments(message);	
			}			
			outputData.add(rowObject);
			logger.info("RowCount->"+rowObject.getRowNumber()+", TestCaseResult->"+rowObject.getTestCaseResult() + ",Reason-> " + message + "->Actaul: " + actualResult + "->Expected: "+ expectedResult);

			Assert.assertEquals(false, true,message);
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean verifyResult(Object notNullObject, String message, RowObject rowObject, List<RowObject> outputData){
		if(notNullObject == null){
			rowObject.setTestCaseResult("FAIL");
			if(rowObject.getComments() != null){
				rowObject.setComments(rowObject.getComments() +", " +message);
			}else{
				rowObject.setComments(message);	
			}
			outputData.add(rowObject);
			logger.info("RowCount->"+rowObject.getRowNumber()+", TestCaseResult->"+rowObject.getTestCaseResult() + ",Reason-> " + message);
			Assert.assertEquals(false, true,message);
			return true;
		}
		return false;
	}
	
	public static boolean verifyResult(boolean notFalseObject, String message, RowObject rowObject, List<RowObject> outputData){
		if(!notFalseObject){
			rowObject.setTestCaseResult("FAIL");
			if(rowObject.getComments() != null){
				rowObject.setComments(rowObject.getComments() +", " +message);
			}else{
				rowObject.setComments(message);	
			}
			outputData.add(rowObject);
			logger.info("RowCount->"+rowObject.getRowNumber()+", TestCaseResult->"+rowObject.getTestCaseResult() + ",Reason-> " + message);
			Assert.assertEquals(false, true,message);
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean verifyResult(Calendar cookieActualExpiryTime,Calendar cookieActualCreationTime, long expectedExpiryInSeconds, String message,RowObject rowObject, List<RowObject> outputData ) {

		long actualTimeDifferenceInMilliSeconds =  cookieActualExpiryTime.getTimeInMillis() - cookieActualCreationTime.getTimeInMillis();
		long actualTimeDifferenceInSeconds = actualTimeDifferenceInMilliSeconds / 1000;
		logger.info("actualTimeDifferenceInSeconds :" + actualTimeDifferenceInSeconds);
		
		if (actualTimeDifferenceInSeconds <= (expectedExpiryInSeconds - 40) || actualTimeDifferenceInSeconds <= (expectedExpiryInSeconds + 40)) {
			return false;
		} else {
			rowObject.setTestCaseResult("FAIL");
			if(rowObject.getComments() != null){
				rowObject.setComments(rowObject.getComments() +", " +message);
			}else{
				rowObject.setComments(message);	
			}	
			outputData.add(rowObject);
			logger.info("RowCount->"+rowObject.getRowNumber()+", TestCaseResult->"+rowObject.getTestCaseResult() + ",Reason-> " + message + " ,actualTimeDifferenceInSeconds-> "+actualTimeDifferenceInSeconds +",expectedTimeDifferenceInSeconds-> "+expectedExpiryInSeconds);
			Assert.assertEquals(false, true,message);
			return true;
		}
	}
	
	public static void validateInputData(RowObject rowObject){
		Assert.assertTrue(BrowserType.values().equals(rowObject.getBrowser()), "Invalid browser at rowIndex : "+rowObject.getRowNumber());
		Assert.assertTrue(rowObject.getPopType().isEmpty() || PopType.values().equals(rowObject.getPopType()),"Invalid pop value at row->"+rowObject.getRowNumber());
		Assert.assertTrue(rowObject.getHtmlElement().isEmpty() || PopType.values().equals(rowObject.getHtmlElement()), "Invalid HTML Element value at row->"+rowObject.getRowNumber());
	}
	
	public static String getTestCaseRunTime(Date startTime){
		long diff = Calendar.getInstance().getTime().getTime() - startTime.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		return diffDays + " days, "+diffHours + " hours, "+diffMinutes + " minutes, "+diffSeconds + " seconds.";
	}
	
	public static boolean checkForAdCoverage(RowObject rowObject, WebDriver driver) throws Exception {
		String consoleString = (((JavascriptExecutor)driver).executeScript("return JSON.stringify(logStore)")).toString();		
		if(consoleString.equals("[]")){
			logger.info("Empty Console String ..!");
		}
		if(consoleString.contains("InterYield") && (!consoleString.contains("InterYield click bind handler had no ad coverage"))&&(!consoleString.contains("no interstitial because cookie support not detect"))){
			return true;
		}else {
			return false;
		}		
	}	
	
	
}