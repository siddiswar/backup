/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.advertise.webdriver;

/**
 *
 * @author nsutter
 */
public class Credentials {
    private static final String USERNAME = "chucklicata1";
    
    private static final String AUTOMATE_KEY = "smqp2Nxs5Fx2qVz5bWif";    
    
    //  http://rameshnadella1:LBWqdhBAA1ESjNxzFB7Z@hub.browserstack.com/wd/hub
    private static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
    
    public static String getBrowserStackURL(){
        return URL;
    }
}
