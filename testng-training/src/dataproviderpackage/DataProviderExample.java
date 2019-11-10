package dataproviderpackage;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderExample {

	@DataProvider(name="dp1")
	public Object[][] dataProMethod1(){
		//This DataProvider has hard coded values
		Object[][] objArray = new Object[4][2];
		objArray[0][0] = "validusername"; objArray[0][1] = "validpassword"; 
		objArray[1][0] = "validusername"; objArray[1][1] = "Invalidpassword"; 
		objArray[2][0] = "invalidusername"; objArray[2][1] = "validpassword"; 
		objArray[3][0] = "invalidusername"; objArray[3][1] = "Invalidpassword"; 
		return objArray;
	}
	
	@DataProvider(name="dp2" ,parallel=true)
	public Object[][] dataProMethod2() throws InvalidFormatException, IOException{
		//This DataProvider reads values from excel

		Object[][] objArray = new Object[4][2];
		
		File file = new File("userdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet userDataSheet = workbook.getSheet("usersheet");

		objArray[0][0] = userDataSheet.getRow(0).getCell(0).getStringCellValue();
		objArray[0][1] = userDataSheet.getRow(0).getCell(1).getStringCellValue();
		objArray[1][0] = userDataSheet.getRow(1).getCell(0).getStringCellValue();
		objArray[1][1] = userDataSheet.getRow(1).getCell(1).getStringCellValue();
		objArray[2][0] = userDataSheet.getRow(2).getCell(0).getStringCellValue();
		objArray[2][1] = userDataSheet.getRow(2).getCell(1).getStringCellValue();
		objArray[3][0] = userDataSheet.getRow(3).getCell(0).getStringCellValue();
		objArray[3][1] = userDataSheet.getRow(3).getCell(1).getStringCellValue();

		workbook.close();
		
		return objArray;
	}
	
	@Test(dataProvider="dp2")
	public void firstParamTest(String uname, String pwd) {

		System.out.println(uname + " : " + pwd);
		System.out.println("----------------------------------");
		System.out.println("Thread ID :" + Thread.currentThread().getId()); 
		//open a browser
		//open url
		//
		//
		//
		//Assert
		//close browser

	}
	
	
	
/*	@Test(dataProvider="dp1")
	public void firstParamTest2(String uname, String pwd) {

		System.out.println(uname + " : " + pwd);
		System.out.println("----------------------------------");
	}
*/
	
	
	
}
