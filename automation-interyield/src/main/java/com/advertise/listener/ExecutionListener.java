package com.advertise.listener;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.IExecutionListener;

import com.advertise.utils.MailUtils;

public class ExecutionListener implements IExecutionListener {
	
	Logger logger = Logger.getLogger(ExecutionListener.class.getName()); 

	Date startTime;

	@Override
	public void onExecutionStart() {
		startTime = Calendar.getInstance().getTime();
		logger.info("Execution Start Time :: " + startTime);
		System.out.println("Execution Start Time :: " + startTime);
	}

	@Override
	public void onExecutionFinish() {
		logger.info("Execution End Time :: " + Calendar.getInstance().getTime());
		System.out.println("Execution End Time :: " + Calendar.getInstance().getTime());		
		MailUtils mailUtils = new MailUtils();
		mailUtils.sendNotification(startTime);		
	}	
}
