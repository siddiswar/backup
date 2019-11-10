package com.equalexperts.utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.equalexperts.bookingobjects.BookingData;
import com.equalexperts.bookingobjects.Bookingdates;

/**
 * @author Siddu
 */

public class ExcelUtils {
	
	//This class has utilities to deal with Excel file which has test data
	Logger logger = Logger.getLogger(ExcelUtils.class.getName());

	//Reads test data of a given row and creates a BookingData object with this test data
	public BookingData returnBookingData(int rowNumber) throws InvalidFormatException, IOException {

		BookingData bookingData = new BookingData();
		Bookingdates bookingdates = new Bookingdates();
		
		XSSFWorkbook workbook = new XSSFWorkbook(new File(System.getProperty("test-data-file")));
		XSSFSheet sheet = workbook.getSheet(System.getProperty("test-data-sheet"));
		XSSFRow row = sheet.getRow(rowNumber);
		

		bookingData.setFirstname(row.getCell(1).getStringCellValue());
		bookingData.setLastname(row.getCell(2).getStringCellValue());
		bookingData.setTotalprice(row.getCell(3).getNumericCellValue());
		bookingData.setDepositpaid(row.getCell(4).getStringCellValue());
		bookingdates.setCheckin(row.getCell(5).getStringCellValue());
		bookingdates.setCheckout(row.getCell(6).getStringCellValue());
		bookingData.setBookingdates(bookingdates);
		bookingData.setBookingCreationStatus(row.getCell(7).getStringCellValue());
		
		
		logger.info(bookingData.getFirstname()+" : ");
		logger.info(bookingData.getLastname()+" : ");
		logger.info(bookingData.getTotalprice()+" : ");
		logger.info(bookingData.getDepositpaid()+" : ");
		logger.info(bookingData.getBookingdates().getCheckin()+" : ");
		logger.info(bookingData.getBookingdates().getCheckout()+" : ");
		logger.info(bookingData.getBookingCreationStatus()+" : ");
		workbook.close();
		return bookingData;

	}

}
