package excelhandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DealingAlreadyExistingExcelFile {

	public static void main(String[] args) throws IOException {
		
		
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("file1.xlsx"));
		
		System.out.println(workbook.getSheet("Sheet0").getRow(0).getCell(0).getStringCellValue());
		System.out.println(workbook.getSheet("Sheet0").getRow(0).getCell(1).getStringCellValue());
		System.out.println(workbook.getSheet("Sheet0").getRow(0).getCell(2).getStringCellValue());
		System.out.println(workbook.getSheet("Sheet0").getRow(0).getCell(3).getStringCellValue());
		
		
		/*workbook.createSheet("MySheet2");	
		workbook.getSheet("MySheet2").createRow(0);
		workbook.getSheet("MySheet2").getRow(0).createCell(0);
		workbook.getSheet("MySheet2").getRow(0).getCell(0).setCellValue("Independence day");;*/

		workbook.write(new FileOutputStream("file1.xlsx"));
		
		workbook.close();
		
	}

}
