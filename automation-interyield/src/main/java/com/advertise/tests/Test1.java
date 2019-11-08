package com.advertise.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.advertise.utils.WindowUtils;

public class Test1 {
public static void main(String[] args) throws IOException, InterruptedException {
	
	System.out.println("Starting.....");
	
	WindowUtils.getActiveWindowTitleUsingAutoit();	
	
	System.out.println("==========================");
	
	System.out.println(WindowUtils.getBrowserWindowCountUsingAutoIT("CHROME"));
}
}
