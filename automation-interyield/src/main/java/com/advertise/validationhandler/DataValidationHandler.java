package com.advertise.validationhandler;

import org.testng.Assert;

import com.advertise.model.BrowserType;
import com.advertise.model.OSType;
import com.advertise.model.PlatformType;
import com.advertise.model.PopType;
import com.advertise.model.RowObject;

public class DataValidationHandler {
	
	@org.testng.annotations.Parameters(value={"rowObject"})
	public void testValidInput(RowObject rowObject){
		Assert.assertTrue(OSType.values().equals(rowObject.getOs()), "Invalid os at rowIndex : "+rowObject.getRowNumber());
		Assert.assertTrue(BrowserType.values().equals(rowObject.getBrowser()), "Invalid browser at rowIndex : "+rowObject.getRowNumber());
		Assert.assertTrue(rowObject.getPopType().isEmpty() || PopType.values().equals(rowObject.getPopType()),"Invalid Pop value at row->"+rowObject.getRowNumber());
	}

	@org.testng.annotations.Parameters(value={"platform", "rowIndex"})
	public void testValidPlatform(String platform, int rowIndex){
		Assert.assertTrue(PlatformType.values().equals(platform), "Invalid platform at rowIndex : "+rowIndex);
	}
	
	@org.testng.annotations.Parameters(value={"os", "rowIndex"})
	public void testValidOS(String os, int rowIndex){
		Assert.assertTrue(OSType.values().equals(os), "Invalid os at rowIndex : "+rowIndex);
	}
	
	@org.testng.annotations.Parameters(value={"osVersion", "rowIndex"})
	public void testValidOSVersion(String osVersion, int rowIndex){

	}
	
	@org.testng.annotations.Parameters(value={"browser", "rowIndex"})
	public void testValidBrowser(String browser, int rowIndex){
		Assert.assertTrue(BrowserType.values().equals(browser), "Invalid browser at rowIndex : "+rowIndex);
	}
	
	@org.testng.annotations.Parameters(value={"browserVersion", "rowIndex"})
	public void testValidBrowserVersion(String browserVersion, int rowIndex){
		
	}
	
	@org.testng.annotations.Parameters(value={"popType", "rowIndex"})
	public void testValidPopType(String popType, int rowIndex){
		Assert.assertTrue(popType.isEmpty() || PopType.values().equals(popType),"Invalid Pop value at row->"+rowIndex);
	}
	
	@org.testng.annotations.Parameters(value={"popLinkType", "rowIndex"})
	public void testValidPopLinkType(String popLinkType, int rowIndex){
		Assert.assertTrue(popLinkType.isEmpty() || PopType.values().equals(popLinkType));
	}	
}