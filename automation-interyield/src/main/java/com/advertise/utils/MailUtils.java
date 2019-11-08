package com.advertise.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class MailUtils {
	
	Logger logger = Logger.getLogger(MailUtils.class.getName()); 
	
	public void sendNotification(Date startTime) {
		
		Properties properties = new Properties();
		try {
			properties.load(ClassLoader.getSystemResourceAsStream("driver.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		System.getProperties().putAll(properties);
		try{
			String host = properties.getProperty("host");			
			String port = properties.getProperty("port");
			String protocol = properties.getProperty("protocol");
			
			String fromAddress = properties.getProperty("from_address");
			String toAddress = properties.getProperty("to_address");
			String ccAddress = properties.getProperty("cc_address");
			
			final String username = properties.getProperty("username");
			final String password = properties.getProperty("password");
			
			logger.info("e-mail properties, host ::"+host+", port :: "+port+", protocol :: "+protocol);
			
			String datePath = new SimpleDateFormat("ddMMyyyy").format(startTime);
			
			String subject = "IY Automation Execution Results, Dated :: "+datePath;
			String body = "IY Automation Execution Results can be seen as attached(Zip Folder)";			
			
			String outputFolderPath = properties.getProperty("output_folder_path") + "" +datePath;
			String zippedFolderPath = properties.getProperty("zipped_folder_path") + "_" +datePath+".zip";
			
			try {
				FileUtils fileUtils = new FileUtils();
				fileUtils.zipOutputFolder(outputFolderPath, zippedFolderPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}						
			
			Properties props = new Properties();
			props.put("mail.smtps.auth", "true");
		    props.put("mail.smtps.starttls.enable", "true");
		    props.put("mail.smtps.debug", true);
		    props.put("mail.smtp.host", host);
		    props.put("mail.smtp.port", port);
		    props.put("mail.smtp.protocol", protocol);
	
			 //Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() { 
				protected PasswordAuthentication getPasswordAuthentication() { 
					return new PasswordAuthentication(username, password); 
					} 
				});
	
			 //Create a new e-mail message 
			Message message = new MimeMessage(session);
	
			message.setFrom(new InternetAddress(fromAddress));			
			
			InternetAddress[] ccAddressArray = InternetAddress.parse(ccAddress);
			
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setRecipients(Message.RecipientType.CC, ccAddressArray);
			
			message.setSubject(subject);
	
			 //Create body part for the message 
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);
	
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();	
			DataSource source = new FileDataSource(new File(zippedFolderPath));
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(zippedFolderPath);			
			multipart.addBodyPart(messageBodyPart);
			
			message.setContent(multipart);
	
			Transport.send(message);
		}catch(Exception e){
			logger.error("Unable to send notification of the job execution completion!"+e);
		}
	}
}
