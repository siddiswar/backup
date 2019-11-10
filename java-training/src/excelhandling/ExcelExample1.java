package excelhandling;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExample1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		//This example shows how to create a new XLSX file and writing some values to it
		//XLSX files the class that we should use is XSSFWorkbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		workbook.createSheet();
		workbook.createSheet();
		workbook.createSheet();
		workbook.createSheet();
		workbook.createSheet();
		
		workbook.getSheetAt(0).createRow(0);
		
		workbook.getSheetAt(0).getRow(0).createCell(0);
		
		workbook.getSheetAt(0).getRow(0).getCell(0).setCellValue("sachin");
		
		workbook.getSheetAt(0).getRow(0).createCell(1);
		
		workbook.getSheetAt(0).getRow(0).getCell(1).setCellValue("sehwag");
		
		workbook.getSheetAt(0).getRow(0).createCell(2);
		
		workbook.getSheetAt(0).getRow(0).getCell(2).setCellValue("yuvraj");
		
		workbook.getSheetAt(0).getRow(0).createCell(3);
		
		workbook.getSheetAt(0).getRow(0).getCell(3).setCellValue("laxman");
		
		workbook.write(new FileOutputStream("file1.xlsx"));
		
		workbook.close();
		
		System.out.println("Done..");
		
	}

}
