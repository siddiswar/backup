package com.advertise.utils;
/**
 * @author rahul
 *
 */
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.advertise.model.ElementType;
import com.advertise.model.RowObject;
import com.advertise.webdriver.WebDriverFactory;

public class WebpageUtils {
	
	static final Logger logger = Logger.getLogger(WebpageUtils.class.getName()); 
	
    public static String getURL(String scriptUrl, Map<String,String> paramMap) {
    	LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
    	if(scriptUrl.contains("?")){
	        String[] params = scriptUrl.split("\\?")[1].split("&");
	        for (String param : params) {  
	            String name = param.split("=")[0];  
	            String value = param.split("=")[1];
	            if(paramMap.containsKey(name)){
	            	value = paramMap.get(name).toLowerCase();
	            }	            	
	            map.put(name, value);  
	        }	        
	        Set<String> inputParamKeySet = paramMap.keySet(); 
	        for(String inputParam : inputParamKeySet){
	        	if(!map.containsKey(inputParam)){
	        		map.put(inputParam, paramMap.get(inputParam));
	        	}
	        }
    	}
    	return addParameters(scriptUrl, map);
    }
    
    private static String addParameters(String url, LinkedHashMap<String, String> map){        
        if(url.contains("?")){
        	url = url.split("\\?")[0];
        	url += "?";
        }        
        Set<String> paramNameSet  = map.keySet();
        StringBuffer stringBuffer = new StringBuffer(url);        
        for(String paramName : paramNameSet){
        	stringBuffer.append(paramName+"="+map.get(paramName)).append("&");
        }        
        String tempUrl = stringBuffer.toString();        
        String newUrl = tempUrl.substring(0, tempUrl.length()-1);    
        return newUrl;
    }
    
    public static WebElement getWebElement(WebDriver webDriver, RowObject rowObject){
		
		if (rowObject.getHtmlElement().trim().equalsIgnoreCase(ElementType.WHITESPACE.name())) {					
			return (new WebDriverWait(webDriver, 1000))
					.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		} else if (rowObject.getHtmlElement().trim().equalsIgnoreCase(ElementType.LINK.name())) {
			List<WebElement> webElements = (new WebDriverWait(webDriver, 1000))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a")));
			for (WebElement webElement : webElements) {
				if (!webElement.getAttribute("href").contains("#")
						&& !webElement.getAttribute("href").contains("javascript")) {
					return webElement;					
				}
			}
		} else if (rowObject.getHtmlElement().trim().equalsIgnoreCase(ElementType.HASHLINK.name())) {
			List<WebElement> webElements = (new WebDriverWait(webDriver, 1000))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a")));
			for (WebElement webElement : webElements) {
				if (webElement.getAttribute("href").contains("#")) {
					return webElement;
				}
			}
		} else if (rowObject.getHtmlElement().trim().equalsIgnoreCase(ElementType.SCRIPTLINK.name())) {
			List<WebElement> webElements = (new WebDriverWait(webDriver, 1000))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("//*[@href]")));
			for (WebElement webElement : webElements) {
				if (webElement.getAttribute("href").contains("javascript")) {
					return webElement;
				}
			}
		}
		return null;
	}
    
    public static String getUserWindowTitle(RowObject rowObject, String testUrl, List<RowObject> outputData) throws Exception{
		String windowTitle = null;
		WebDriver tempDriver = null;
		try{			
			tempDriver = WebDriverFactory.getInstance().getWebDriver(rowObject, true);
			
			tempDriver.manage().deleteAllCookies();
			
			tempDriver.get(testUrl);
			
			Thread.sleep(2500);
			
			WebElement tempWebElement = WebpageUtils.getWebElement(tempDriver, rowObject);
			
			TestCaseUtils.verifyResult(tempWebElement, "No Object identified in the url with the given HTML Element!", rowObject, outputData);								
			
			Thread.sleep(1500);
			
			tempWebElement.click();				
			
			//waitForLoad(webDriver);
			Thread.sleep(5000);
			
			windowTitle = tempDriver.getTitle();
			
			tempDriver.close();
			
			tempDriver.quit();
			
			return windowTitle;
		}catch(Exception exception){
			if(tempDriver != null){
				tempDriver.quit();
			}
			throw new Exception("Exception in getting window title of a temporary web driver!");
		}
	}	
    
    public static String getParameterValue(String url, String paramName){
    	Map<String, String> params = new HashMap<>();

        List<NameValuePair> result = null;
		try {
			result = URLEncodedUtils.parse(new URI(url), "UTF-8");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        for (NameValuePair nvp : result) {
            params.put(nvp.getName(), nvp.getValue());
        }
        return params.get(paramName);
    }    
}