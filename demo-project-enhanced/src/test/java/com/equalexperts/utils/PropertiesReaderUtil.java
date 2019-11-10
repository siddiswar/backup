package com.equalexperts.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesReaderUtil {

	Logger logger = Logger.getLogger(PropertiesReaderUtil.class.getName());

	private static final PropertiesReaderUtil propertiesReaderUtil = new PropertiesReaderUtil();

	private PropertiesReaderUtil() {
		logger.info("Reading properties from properties file into system properties");
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File("conf/config.properties")));
			System.getProperties().putAll(props);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PropertiesReaderUtil getInstance() {
		return propertiesReaderUtil;
	}
}
