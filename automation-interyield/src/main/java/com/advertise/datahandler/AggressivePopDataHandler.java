package com.advertise.datahandler;
/**
 * @author rahul
 *
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.advertise.model.RowObject;
import com.advertise.utils.FileUtils;

public class AggressivePopDataHandler {
	
	Logger logger = Logger.getLogger(AggressivePopDataHandler.class.getName());
	FileUtils fileUtils = new FileUtils();
	
	/* change any type of cell value to string and return */
	private String getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());

		case Cell.CELL_TYPE_NUMERIC:
			DataFormatter fmt = new DataFormatter();
			return fmt.formatCellValue(cell);
		}
		return null;
	}	
	
	/* Apply format for excel sheet cells */
	private void setCellStyle(Cell cell, Workbook workbook){
		CellStyle style = workbook.createCellStyle();	    	    
	    
	    style.setBorderBottom((short)1);
	    style.setBorderTop((short)1);
	    style.setBorderLeft((short)1);
	    style.setBorderRight((short)1);		
	    
	    cell.setCellStyle(style);
	}
	
	/* Create static Headers in excel sheet*/
	private void createHeader(Row row, Workbook workbook, String platformType){
		int cellNo = 0;		
		
		CellStyle style = workbook.createCellStyle();
	    style.setFillBackgroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
	    style.setFillPattern(CellStyle.NO_FILL);
	    
	    Font font = workbook.createFont();
	    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    style.setFont(font);
	    
	    style.setBorderBottom((short)1);
	    style.setBorderTop((short)1);
	    style.setBorderLeft((short)1);
	    style.setBorderRight((short)1);
	    
	    Cell cell;
	    
	    if(platformType.equalsIgnoreCase("desktop")){
		    cell = row.createCell(cellNo++); 
			cell.setCellValue("OS");
			cell.setCellStyle(style);
		    
		    cell = row.createCell(cellNo++); 
			cell.setCellValue("OS VERSION");
			cell.setCellStyle(style);
			
		    cell = row.createCell(cellNo++); 
			cell.setCellValue("BROWSER");
			cell.setCellStyle(style);		
			
			cell = row.createCell(cellNo++);
			cell.setCellValue("BROWSER VERSION");
			cell.setCellStyle(style);
		}else{
			cell = row.createCell(cellNo++); 
			cell.setCellValue("PLATFORM");
			cell.setCellStyle(style);
		    
		    cell = row.createCell(cellNo++); 
			cell.setCellValue("BROWSER");
			cell.setCellStyle(style);		
			
			cell = row.createCell(cellNo++);
			cell.setCellValue("DEVICE");
			cell.setCellStyle(style);
		}		
		cell = row.createCell(cellNo++);
		cell.setCellValue("POP");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellNo++);
		cell.setCellValue("WEB ELEMENT");
		cell.setCellStyle(style);
		
	    cell = row.createCell(cellNo++); 
		cell.setCellValue("SMARTSWITCH");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellNo++);
		cell.setCellValue("  ");
		row.getSheet().setColumnWidth(cellNo, 100);
		cell.setCellStyle(style);
		
		cell = row.createCell(cellNo++);
		cell.setCellValue("EXPECTED POP");
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("  ");
		row.getSheet().setColumnWidth(cellNo, 100);
		cell.setCellStyle(style);
		
		cell = row.createCell(cellNo++);
		cell.setCellValue("TESTCASE RESULT");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellNo++);
		cell.setCellValue("COMMENTS");
		cell.setCellStyle(style);
	}	
	
	private ArrayList<RowObject> getDataForDesktopPlatformOnly(String desktopFilePath, String desktopSheetName) throws IOException {				
		Workbook workbook = new XSSFWorkbook(this.getClass().getResourceAsStream(desktopFilePath));			
		Sheet sheet = workbook.getSheet(desktopSheetName);		
		ArrayList<RowObject> platformObjects = new ArrayList<RowObject>();		
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			if (nextRow.getRowNum() > 0) {
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				String os = getCellValue(cellIterator.next());
				String osVersion = getCellValue(cellIterator.next());
				String runMode = getCellValue(cellIterator.next());				
				if(runMode != null && runMode.equalsIgnoreCase("Y")){
					RowObject platformObject = new RowObject();
					platformObject.setOs(os);
					platformObject.setOsVersion(osVersion);					
					platformObjects.add(platformObject);
				}				
			}			
		}
		return platformObjects;
	}	
	
	private ArrayList<RowObject> getDataForMobilePlatformOnly(String mobileFilePath, String mobileSheetName) throws IOException {	
		Workbook workbook = new XSSFWorkbook(this.getClass().getResourceAsStream(mobileFilePath));			
		Sheet sheet = workbook.getSheet(mobileSheetName);		
		ArrayList<RowObject> platformObjects = new ArrayList<RowObject>();		
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			if (nextRow.getRowNum() > 0) {
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				String platform = getCellValue(cellIterator.next());
				String browserName = getCellValue(cellIterator.next());
				String device = getCellValue(cellIterator.next());
				String runMode = getCellValue(cellIterator.next());				
				if(runMode != null && runMode.equalsIgnoreCase("Y")){
					RowObject platformObject = new RowObject();
					platformObject.setPlatform(platform);
					platformObject.setBrowser(browserName);
					platformObject.setDevice(device);					
					platformObjects.add(platformObject);
				}
			}
		}
		return platformObjects;
	}	
	
	private List<RowObject> expandTestDataForDesktopPlatform(List<RowObject> rowObjects, String desktopFilePath, String desktopSheetName) throws IOException {
		List<RowObject> rowObjectWithDesktopPlatforms = new ArrayList<RowObject>();
		ArrayList<RowObject> platformObjects = getDataForDesktopPlatformOnly(desktopFilePath, desktopSheetName);
		
		for (RowObject rowObject : rowObjects) {
			for(RowObject platformObject : platformObjects){	
				
				RowObject rowObjectWithDesktopPlatform = new RowObject();

				rowObjectWithDesktopPlatform.setBrowser(rowObject.getBrowser());
				rowObjectWithDesktopPlatform.setBrowserVersion(rowObject.getBrowserVersion());				
				rowObjectWithDesktopPlatform.setSmartSwitchType(rowObject.getSmartSwitchType());
				rowObjectWithDesktopPlatform.setPopType(rowObject.getPopType());
				rowObjectWithDesktopPlatform.setHtmlElement(rowObject.getHtmlElement());
				rowObjectWithDesktopPlatform.setFocus(rowObject.getFocus());			
				rowObjectWithDesktopPlatform.setExpectedPop(rowObject.getExpectedPop());

				rowObjectWithDesktopPlatform.setPlatformType("desktop");
				rowObjectWithDesktopPlatform.setOs(platformObject.getOs());
				rowObjectWithDesktopPlatform.setOsVersion(platformObject.getOsVersion());
				rowObjectWithDesktopPlatforms.add(rowObjectWithDesktopPlatform);					
			}				
		}
		return rowObjectWithDesktopPlatforms;		
	}
	
	private List<RowObject> expandTestDataForMobilePlatform(List<RowObject> rowObjects, String mobileFilePath, String mobileSheetName) throws MalformedURLException {
		List<RowObject> rowObjectWithMobilePlatforms = new ArrayList<RowObject>();
		try {
			ArrayList<RowObject> platformObjects = getDataForMobilePlatformOnly(mobileFilePath, mobileSheetName);			
			for (RowObject rowObject : rowObjects) {
				for(RowObject platformObject : platformObjects){
					
					RowObject rowObjectWithMobilePlatform = new RowObject();
					
					rowObjectWithMobilePlatform.setExpectedPop(rowObject.getExpectedPop());
					rowObjectWithMobilePlatform.setSmartSwitchType(rowObject.getSmartSwitchType());
					rowObjectWithMobilePlatform.setPopType(rowObject.getPopType());
					rowObjectWithMobilePlatform.setHtmlElement(rowObject.getHtmlElement());
					rowObjectWithMobilePlatform.setFocus(rowObject.getFocus());			
					
					rowObjectWithMobilePlatform.setPlatformType("mobile");
					rowObjectWithMobilePlatform.setPlatform(platformObject.getPlatform());
					rowObjectWithMobilePlatform.setBrowser(platformObject.getBrowser());
					rowObjectWithMobilePlatform.setDevice(platformObject.getDevice());					
					rowObjectWithMobilePlatforms.add(rowObjectWithMobilePlatform);					
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowObjectWithMobilePlatforms;		
	}
	
	private List<RowObject> readMasterData(String templateFilePath, String templateSheetName) throws IOException{
		List<RowObject> inputData = null;
		Workbook workbook = new XSSFWorkbook(this.getClass().getResourceAsStream(templateFilePath));
		Sheet sheet = workbook.getSheet(templateSheetName);				
		inputData = new ArrayList<RowObject>();			
		Iterator<Row> rowIterator = sheet.iterator();				
		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			if (nextRow.getRowNum() > 0) {						
				int cellNo = 0;
				String smartSwitch = getCellValue(nextRow.getCell(cellNo++));					
				String popType = getCellValue(nextRow.getCell(cellNo++));					
				String htmlElement = getCellValue(nextRow.getCell(cellNo++));
				String browser = getCellValue(nextRow.getCell(cellNo++));
				String version = getCellValue(nextRow.getCell(cellNo++));					
				
				nextRow.getCell(cellNo++);				
				
				String expectedPopType = getCellValue(nextRow.getCell(cellNo++));
				
				String runMode = getCellValue(nextRow.getCell(cellNo++));
				
				if(runMode != null && runMode.equalsIgnoreCase("Y")){
					RowObject rowObject = new RowObject();
					
					rowObject.setSmartSwitchType(smartSwitch);
					rowObject.setPopType(popType);
					rowObject.setHtmlElement(htmlElement);
					rowObject.setBrowser(browser);
					rowObject.setBrowserVersion(version);
					
					rowObject.setExpectedPop(expectedPopType);					

					inputData.add(rowObject);	
				}				
			}
		}
		return inputData;
	}	
	
	public List<RowObject> getTestCases(String desktopOrMobile, String templateFilePath, String templateSheetName, String htmlElementsSheetName, 
			String desktopOrMobileFilePath, String desktopOrMobileSheetName) throws IOException {
		
		List<RowObject> masterOrTemplateObjects = readMasterData(templateFilePath, templateSheetName);
		List<RowObject> rowobjects;
		
		if(desktopOrMobile.equalsIgnoreCase("desktop")){
			rowobjects = expandTestDataForDesktopPlatform(masterOrTemplateObjects, desktopOrMobileFilePath, desktopOrMobileSheetName);
		}else{
			rowobjects = expandTestDataForMobilePlatform(masterOrTemplateObjects, desktopOrMobileFilePath, desktopOrMobileSheetName);
		}
		return rowobjects;
	}
	
	/* Prepare Test Cases and write to excel sheet*/
	public void writeTestCases(List<RowObject> rowObjects, String platformType, String outputFileSheetName, String outputFilePath, Date startTime) throws IOException {
		XSSFWorkbook excelWorkBook = new XSSFWorkbook();
		Sheet sheet = excelWorkBook.createSheet(outputFileSheetName);
		int rowNum = 0;
		int noOfCells = 0;	

		createHeader(sheet.createRow(rowNum++), excelWorkBook, platformType);	
		
		for (RowObject rowObject : rowObjects) {				
			int cellNo = 0;
			Row row = sheet.createRow(rowNum++);
			Cell cell;

			if(platformType.equalsIgnoreCase("desktop")){
				cell = row.createCell(cellNo++);
				setCellStyle(cell, excelWorkBook);
				cell.setCellValue(rowObject.getOs());
				
				cell = row.createCell(cellNo++);
				setCellStyle(cell, excelWorkBook);
				cell.setCellValue(rowObject.getOsVersion());
				
				cell = row.createCell(cellNo++);
				setCellStyle(cell, excelWorkBook);
				cell.setCellValue(rowObject.getBrowser());

				cell = row.createCell(cellNo++);
				setCellStyle(cell, excelWorkBook);
				cell.setCellValue(rowObject.getBrowserVersion());
			}else{
				cell = row.createCell(cellNo++);
				setCellStyle(cell, excelWorkBook);
				cell.setCellValue(rowObject.getPlatform());
				
				cell = row.createCell(cellNo++);
				setCellStyle(cell, excelWorkBook);
				cell.setCellValue(rowObject.getBrowser());
				
				cell = row.createCell(cellNo++);
				setCellStyle(cell, excelWorkBook);
				cell.setCellValue(rowObject.getDevice());	
			}
			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getPopType());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getHtmlElement());
			
			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getSmartSwitchType());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);							
			cell.setCellValue("");						
			
			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);							
			cell.setCellValue(rowObject.getExpectedPop());
			
			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);							
			cell.setCellValue("");
			
			cell = row.createCell(cellNo++);							
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getTestCaseResult());							

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);							
			cell.setCellValue(rowObject.getComments());
			
			rowObject.setRowNumber(rowNum);

			noOfCells = cellNo;
		}
		for (int position = 0; position <= noOfCells; position++) {
			sheet.autoSizeColumn(position);
		}
		sheet.createFreezePane(0, 1);
		FileOutputStream outFile = new FileOutputStream(fileUtils.getFilePath(outputFilePath, startTime));
		excelWorkBook.write(outFile);
		outFile.close();
	}	
	
	/* Writing test case results into individual excel files */
	public void writeTestCaseResultsintoIndividualFiles(List<RowObject> outputData, String sheetName, String filePath, Date startTime) throws IOException {
		Workbook outputExcelWorkBook = new XSSFWorkbook();
		Sheet sheet = outputExcelWorkBook.createSheet(sheetName);
		int rowNum = 0;
		int noOfCells = 0;
		String platformType = outputData.get(0).getPlatformType();
		createHeader(sheet.createRow(rowNum++), outputExcelWorkBook, platformType);
		sheet.createFreezePane(0, 1);
		for(RowObject rowObject : outputData){
			int cellNo = 0;
			Row row = sheet.createRow(rowNum++);
			
			Cell cell;
			
			if(platformType.equalsIgnoreCase("desktop")){
				cell = row.createCell(cellNo++);
				setCellStyle(cell, outputExcelWorkBook);
				cell.setCellValue(rowObject.getOs());
				
				cell = row.createCell(cellNo++);
				setCellStyle(cell, outputExcelWorkBook);
				cell.setCellValue(rowObject.getOsVersion());
				
				cell = row.createCell(cellNo++);
				setCellStyle(cell, outputExcelWorkBook);
				cell.setCellValue(rowObject.getBrowser());

				cell = row.createCell(cellNo++);
				setCellStyle(cell, outputExcelWorkBook);
				cell.setCellValue(rowObject.getBrowserVersion());
			}else{						
				cell = row.createCell(cellNo++);
				setCellStyle(cell, outputExcelWorkBook);
				cell.setCellValue(rowObject.getPlatform());
				
				cell = row.createCell(cellNo++);
				setCellStyle(cell, outputExcelWorkBook);
				cell.setCellValue(rowObject.getBrowser());
				
				cell = row.createCell(cellNo++);
				setCellStyle(cell, outputExcelWorkBook);
				cell.setCellValue(rowObject.getDevice());
			}	
			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getPopType());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getHtmlElement());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getSmartSwitchType());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);							
			cell.setCellValue("");
			
			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);							
			cell.setCellValue(rowObject.getExpectedPop());
			
			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);							
			cell.setCellValue("");
			
			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);							
			cell.setCellValue(rowObject.getTestCaseResult());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);							
			cell.setCellValue(rowObject.getComments());			
			noOfCells = cellNo;
		}
		for (int position = 0; position <= noOfCells; position++) {
			sheet.autoSizeColumn(position);
		}				
		FileOutputStream outFile = new FileOutputStream(fileUtils.getFilePath(filePath, startTime));
		outputExcelWorkBook.write(outFile);
		outFile.close();
	}
	
	public void writeTestCaseResults(List<RowObject> outputData, String sheetName, String desktopIYDOutputFilePath, String desktopIYOutputFilePath,
			String mobileIYDOutputFilePath, String mobileIYOutputFilePath, Date startTime) throws IOException {
		
		List<RowObject> desktopIYRowObjects = new ArrayList<RowObject>();
		List<RowObject> desktopIYDRowObjects = new ArrayList<RowObject>();
		List<RowObject> mobileIYRowObjects = new ArrayList<RowObject>();
		List<RowObject> mobileIYDRowObjects = new ArrayList<RowObject>();
		
		for(RowObject rowObject : outputData){
			if(rowObject.getPlatformType().equalsIgnoreCase("desktop") && rowObject.isIYD() == true){
				desktopIYDRowObjects.add(rowObject);
			}else if(rowObject.getPlatformType().equalsIgnoreCase("desktop") && rowObject.isIYD() == false){
				desktopIYRowObjects.add(rowObject);
			}else if(rowObject.getPlatformType().equalsIgnoreCase("mobile") && rowObject.isIYD() == true){
				mobileIYDRowObjects.add(rowObject);
			}else if(rowObject.getPlatformType().equalsIgnoreCase("mobile") && rowObject.isIYD() == false){
				mobileIYRowObjects.add(rowObject);
			}
		}
		if(!desktopIYRowObjects.isEmpty())
			writeTestCaseResultsintoIndividualFiles(desktopIYRowObjects, sheetName, desktopIYOutputFilePath, startTime);
		if(!desktopIYDRowObjects.isEmpty())
			writeTestCaseResultsintoIndividualFiles(desktopIYDRowObjects, sheetName, desktopIYDOutputFilePath, startTime);
		if(!mobileIYRowObjects.isEmpty())
			writeTestCaseResultsintoIndividualFiles(mobileIYRowObjects, sheetName, mobileIYOutputFilePath, startTime);
		if(!mobileIYDRowObjects.isEmpty())
			writeTestCaseResultsintoIndividualFiles(mobileIYDRowObjects, sheetName, mobileIYDOutputFilePath, startTime);		
	}	
}