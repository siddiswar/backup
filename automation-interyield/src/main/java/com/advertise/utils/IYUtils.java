package com.advertise.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.advertise.model.RowObject;

public class IYUtils {
	
	static final Logger logger = Logger.getLogger(IYUtils.class.getName());
	
	public static boolean hasPopTriggered(WebDriver webDriver, RowObject rowObject, String publisherURL){
    	
    	boolean popTriggered = false;
    	
    	String oldHtmlElement = rowObject.getHtmlElement();
    	
    	int beforeSize = webDriver.getWindowHandles().size();
    	
    	String webElementArr[] = {"WHITESPACE","HASHLINK","LINK"};
		
		for(String element : webElementArr){
			if(webDriver.getCurrentUrl().equalsIgnoreCase(publisherURL)){
				rowObject.setHtmlElement(element);
				try{    			
		    		WebElement webElement = WebpageUtils.getWebElement(webDriver, rowObject);	    		
		    		if(webElement != null){    			
		    			logger.info("===================================================================================");
		    			logger.info("Before Event on WebElement ============== "+element);
		    			logger.info("WindowHandle :: "+webDriver.getWindowHandle());
		    			logger.info("CurrentURL in Window :: "+webDriver.getCurrentUrl());
		    			logger.info("Window Title :: "+webDriver.getTitle());	    			
		    			logger.info("Window Handles Size :: "+webDriver.getWindowHandles().size());
		    			
	    				webElement.click();
		    			
	    				logger.info("After Event on WebElement =============== "+element);
		    			logger.info("WindowHandle :: "+webDriver.getWindowHandle());
		    			logger.info("CurrentURL in Window :: "+webDriver.getCurrentUrl());
		    			logger.info("Window Title :: "+webDriver.getTitle());	    			
		    			logger.info("Window Handles Size :: "+webDriver.getWindowHandles().size());
	    				
	    				Thread.sleep(2000);
	    				
			    		if(webDriver.getWindowHandles().size() > beforeSize){
			    			/*Set<String> windowHandles = webDriver.getWindowHandles();
			    			int count = 0;
			    			for(String winHand : windowHandles){
			    				System.out.println("Count :: "+(++count)+"=====================");
			    				webDriver.switchTo().window(winHand);
				    			System.out.println("WindowHandle :: "+webDriver.getWindowHandle());
				    			System.out.println("CurrentURL in Window :: "+webDriver.getCurrentUrl());
				    			System.out.println("Window Title :: "+webDriver.getTitle());
				    		}*/
			    			popTriggered = true;
			    			break;
			    		}
		    		}else{
		    			logger.warn("No WebElement identified with htmlElement as :: "+element+" for rowObject :: "+rowObject.getRowNumber());
		    		}
	    		}catch(Exception e){
	    			logger.error("Cannot click on WebElement :: "+ element);
	    			logger.error(e);
	    		}
			}else{
				logger.error("Ambigous Error, either the click has happened on the link that redirects the user to target page and AD not triggered may be because Interyield script does not exist here [OR] AD might be triggered but Web Driver could not capture it !!");				
				rowObject.setComments("Ambigous Error, either the click has happened on the link that redirects the user to target page and AD not triggered may be because Interyield script does not exist here [OR] AD might be triggered but Web Driver could not capture it !!");				
			}
    	}
    	rowObject.setHtmlElement(oldHtmlElement);
    	return popTriggered;
    }
    
    
    public static void generatePageHistory(WebDriver webDriver, RowObject rowObject) throws InterruptedException{
    	
    	int i=0;
    	String oldHtmlElement = rowObject.getHtmlElement();
    	Object historyLengthObj = null;
    	webDriver.get("http://www.seleniumhq.com");  //Some times landing page is getting loaded in current window where generating history is difficult.
    	Thread.sleep(3000);
    	do{
    		String webElementArr[] = {"LINK","HASHLINK"};
    		for(String element : webElementArr){
        		rowObject.setHtmlElement(element);
        		try{
    	    		WebElement webElement = WebpageUtils.getWebElement(webDriver, rowObject);	    		
    	    		if(webElement != null){
        				webElement.click();
        				Thread.sleep(2000);
        				historyLengthObj = ((JavascriptExecutor) webDriver).executeScript("return history.length");
        				logger.info(historyLengthObj.toString());
        				if(Integer.parseInt(historyLengthObj.toString())>5){
        					break;
        				};
    	    		}else{
    	    			logger.warn("No WebElement identified with htmlElement as "+element+" for rowObject ->"+rowObject.getRowNumber());
    	    		}
        		}catch(Exception e){
        			logger.error("Cannot click on element : "+ element);
        			logger.error(e);
        		}
        	}
    		i++;//limiting number of attempts to 6
    	}while(i<6&&Integer.parseInt(historyLengthObj.toString())<6);
    	
    	rowObject.setHtmlElement(oldHtmlElement);
    }
}
