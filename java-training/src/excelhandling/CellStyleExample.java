package excelhandling;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CellStyleExample {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);

		workbook.createSheet();

		workbook.getSheetAt(0).createRow(0);

		workbook.getSheetAt(0).getRow(0).createCell(0);

		workbook.getSheetAt(0).getRow(0).getCell(0).setCellValue("sachin ramesh tendulkar");
		
		workbook.getSheetAt(0).getRow(0).getCell(0).setCellStyle(cellStyle);

		workbook.write(new FileOutputStream("file2.xlsx"));

		workbook.close();

		System.out.println("Done..");

	}

}
