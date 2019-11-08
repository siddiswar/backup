package com.advertise.datahandler;

/**
 * @author rahul
 *
 */
import java.io.FileOutputStream;
import java.io.IOException;
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

public class PopDataHandler {

	Logger logger = Logger.getLogger(PopDataHandler.class.getName());
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

	/* apply format for excel sheet cells */
	private void setCellStyle(Cell cell, Workbook workbook) {
		CellStyle style = workbook.createCellStyle();

		style.setBorderBottom((short) 1);
		style.setBorderTop((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderRight((short) 1);

		cell.setCellStyle(style);
	}

	/* Create static Headers in excel sheet */
	private void createHeader(Row row, Workbook workbook, String platformType) {
		int cellNo = 0;

		CellStyle style = workbook.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
		style.setFillPattern(CellStyle.NO_FILL);

		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);

		style.setBorderBottom((short) 1);
		style.setBorderTop((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderRight((short) 1);

		Cell cell;

		cell = row.createCell(cellNo++);
		cell.setCellValue("COMBO NUMBER");
		cell.setCellStyle(style);
		
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

		cell = row.createCell(cellNo++);
		cell.setCellValue("HTML ELEMENT");
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("ATTRIBUTION DISABLED");
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("POP");
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("POP OPTION");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellNo++);
		cell.setCellValue("IYT");
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("CHROME OPTION");
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("MAC OPTION");
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("");
		row.getSheet().setColumnWidth(cellNo, 100);
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("EXPECTED POP");
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("EXPECTED WINDOW/TAB");
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("");
		row.getSheet().setColumnWidth(cellNo, 100);
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("TESTCASE RESULT");
		cell.setCellStyle(style);

		cell = row.createCell(cellNo++);
		cell.setCellValue("COMMENTS");
		cell.setCellStyle(style);
	}

	public List<RowObject> readBrowserData(String templateFilePath, String templateSheetName) throws IOException {
		List<RowObject> browserRowObjects = null;
		try {
			Workbook workbook = new XSSFWorkbook(this.getClass().getResourceAsStream(templateFilePath));
			Sheet sheet = workbook.getSheet(templateSheetName);
			browserRowObjects = new ArrayList<RowObject>();

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				if (nextRow.getRowNum() > 0) {
					Iterator<Cell> cellIterator = nextRow.cellIterator();

					String browserName = getCellValue(cellIterator.next());
					String browserVersions = getCellValue(cellIterator.next());
					String runMode = getCellValue(cellIterator.next());

					if (runMode != null && runMode.equalsIgnoreCase("Y")) {
						boolean hasMultipleBrowserVersions = browserVersions.contains(",");
						String[] browserArr = {};
						if (hasMultipleBrowserVersions) {
							browserArr = browserVersions.split(",");
						} else {
							browserArr = new String[1];
							browserArr[0] = browserVersions;
						}
						for (String browserVersion : browserArr) {
							RowObject rowObject = new RowObject();
							rowObject.setBrowser(browserName);
							rowObject.setBrowserVersion(browserVersion);
							browserRowObjects.add(rowObject);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return browserRowObjects;
	}

	/* get HTML(Web) Elements from Excel */
	private ArrayList<RowObject> readHTMLElementsData(String templateFilePath, String htmlElementsSheetName) throws IOException {
		Workbook workbook = new XSSFWorkbook(this.getClass().getResourceAsStream(templateFilePath));
		Sheet sheet = workbook.getSheet(htmlElementsSheetName);
		ArrayList<RowObject> htmlElementsObjects = new ArrayList<RowObject>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			if (nextRow.getRowNum() > 0) {

				Iterator<Cell> cellIterator = nextRow.cellIterator();
				String htmlElement = getCellValue(cellIterator.next());
				String runMode = getCellValue(cellIterator.next());

				if (runMode != null && runMode.equalsIgnoreCase("Y")) {

					RowObject htmlElementsObject = new RowObject();

					htmlElementsObject.setHtmlElement(htmlElement);

					htmlElementsObjects.add(htmlElementsObject);
				}
			}
		}
		return htmlElementsObjects;
	}

	private ArrayList<RowObject> readDesktopPlatformsData(String desktopFilePath, String desktopSheetName) throws IOException {
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
				if (runMode != null && runMode.equalsIgnoreCase("Y")) {
					RowObject platformObject = new RowObject();
					platformObject.setOs(os);
					platformObject.setOsVersion(osVersion);
					platformObjects.add(platformObject);
				}
			}
		}
		return platformObjects;
	}

	private ArrayList<RowObject> readPopFunctionalitiesData(String desktopFilePath, String desktopSheetName) throws IOException {
		Workbook workbook = new XSSFWorkbook(this.getClass().getResourceAsStream(desktopFilePath));
		Sheet sheet = workbook.getSheet(desktopSheetName);
		ArrayList<RowObject> popFunctionalityObjects = new ArrayList<RowObject>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			if (nextRow.getRowNum() > 0) {

				Iterator<Cell> cellIterator = nextRow.cellIterator();
				String popFunctionality = getCellValue(cellIterator.next());
				String runMode = getCellValue(cellIterator.next());

				if (runMode != null && runMode.equalsIgnoreCase("Y")) {

					RowObject popFunctionalityObject = new RowObject();

					popFunctionalityObject.setPopFunctionality(popFunctionality);

					popFunctionalityObjects.add(popFunctionalityObject);
				}
			}
		}
		return popFunctionalityObjects;
	}

	private List<RowObject> readScenariosData(String templateFilePath, String templateSheetName) throws IOException {
		List<RowObject> scenarioObjects = null;
		Workbook workbook = new XSSFWorkbook(this.getClass().getResourceAsStream(templateFilePath));
		Sheet sheet = workbook.getSheet(templateSheetName);
		scenarioObjects = new ArrayList<RowObject>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			if (nextRow.getRowNum() > 1) {
				int cellNo = 0;
				String runMode = getCellValue(nextRow.getCell(cellNo++));
				String testComboNumber = getCellValue(nextRow.getCell(cellNo++));
				String attributionDisabled = getCellValue(nextRow.getCell(cellNo++));
				String popType = getCellValue(nextRow.getCell(cellNo++));
				String popOption = getCellValue(nextRow.getCell(cellNo++));
				String isIYT = getCellValue(nextRow.getCell(cellNo++));
				String chromeOption = getCellValue(nextRow.getCell(cellNo++));
				String macOption = getCellValue(nextRow.getCell(cellNo++));
				nextRow.getCell(cellNo++);

				String windowsChromeExpectedPop = getCellValue(nextRow.getCell(cellNo++));
				String windowsChromeExpectedWindowOrTab = getCellValue(nextRow.getCell(cellNo++));
				String windowsFirefoxExpectedPop = getCellValue(nextRow.getCell(cellNo++));
				String windowsFirefoxEpectedWindowOrTab = getCellValue(nextRow.getCell(cellNo++));
				String windowsIEExpectedPop = getCellValue(nextRow.getCell(cellNo++));
				String windowsIEExpectedWindowOrTab = getCellValue(nextRow.getCell(cellNo++));
				String macSafariExpectedPop = getCellValue(nextRow.getCell(cellNo++));
				String macSafariExpectedWindowOrTab = getCellValue(nextRow.getCell(cellNo++));
				String macChromeExpectedPop = getCellValue(nextRow.getCell(cellNo++));
				String macChromeExpectedWindowOrTab = getCellValue(nextRow.getCell(cellNo++));

				if (runMode != null && runMode.equalsIgnoreCase("Y")) {

					RowObject scenarioObject = new RowObject();

					scenarioObject.setTestComboNumber(testComboNumber);
					scenarioObject.setAttributionDisabled(attributionDisabled);
					scenarioObject.setPopType(popType);
					scenarioObject.setIsIYT(isIYT);
					scenarioObject.setPopOption(popOption);
					scenarioObject.setChromeOption(chromeOption);
					scenarioObject.setMacOption(macOption);
					scenarioObject.setWindowsChromeExpectedPop(windowsChromeExpectedPop);
					scenarioObject.setWindowsChromeExpectedWindowOrTab(windowsChromeExpectedWindowOrTab);
					scenarioObject.setWindowsFirefoxExpectedPop(windowsFirefoxExpectedPop);
					scenarioObject.setWindowsFirefoxEpectedWindowOrTab(windowsFirefoxEpectedWindowOrTab);
					scenarioObject.setWindowsIEExpectedPop(windowsIEExpectedPop);
					scenarioObject.setWindowsIEExpectedWindowOrTab(windowsIEExpectedWindowOrTab);
					scenarioObject.setMacSafariExpectedPop(macSafariExpectedPop);
					scenarioObject.setMacSafariExpectedWindowOrTab(macSafariExpectedWindowOrTab);
					scenarioObject.setMacChromeExpectedPop(macChromeExpectedPop);
					scenarioObject.setMacChromeExpectedWindowOrTab(macChromeExpectedWindowOrTab);

					scenarioObjects.add(scenarioObject);
				}
			}
		}
		return scenarioObjects;
	}

	private List<RowObject> expandFunctionalitiesWithScenarios(List<RowObject> popFunctionalityObjects, List<RowObject> scenarioObjects) throws IOException {
		List<RowObject> scenariosOfSelectedFunctionalitiesRowObjects = new ArrayList<RowObject>();

		List<String> selectedFunctionalities = new ArrayList<String>();

		for (RowObject popFunctionalityObject : popFunctionalityObjects) {
			selectedFunctionalities.add(popFunctionalityObject.getPopFunctionality());
		}

		if (!selectedFunctionalities.isEmpty()) {
			if (selectedFunctionalities.contains("MasterPOP")) {

				for (RowObject scenarioObject : scenarioObjects) {
					if (scenarioObject.getChromeOption().contains("UNSPECIFIED") && scenarioObject.getMacOption().contains("UNSPECIFIED")) {
						scenariosOfSelectedFunctionalitiesRowObjects.add(scenarioObject);
					}
				}
			}

			if (selectedFunctionalities.contains("CHROMEOPTION")) {

				for (RowObject scenarioObject : scenarioObjects) {
					if (!scenarioObject.getChromeOption().contains("UNSPECIFIED")) {
						scenariosOfSelectedFunctionalitiesRowObjects.add(scenarioObject);
					}
				}
			}

			if (selectedFunctionalities.contains("MACOPTION")) {

				for (RowObject scenarioObject : scenarioObjects) {
					if (!scenarioObject.getMacOption().contains("UNSPECIFIED")) {
						scenariosOfSelectedFunctionalitiesRowObjects.add(scenarioObject);
					}
				}

			}

			if (!selectedFunctionalities.contains("IY")) {

				for (RowObject scenariosOfSelectedFunctionalitiesRowObject : scenariosOfSelectedFunctionalitiesRowObjects) {
					if (!(scenariosOfSelectedFunctionalitiesRowObject.getAttributionDisabled().contains("TRUE") && scenariosOfSelectedFunctionalitiesRowObject.getPopType()
							.contains("UNDER"))) {
						scenariosOfSelectedFunctionalitiesRowObjects.remove(scenariosOfSelectedFunctionalitiesRowObject);
					}
				}

			}

			if (!selectedFunctionalities.contains("IYD")) {

				for (RowObject scenariosOfSelectedFunctionalitiesRowObject : scenariosOfSelectedFunctionalitiesRowObjects) {
					if (scenariosOfSelectedFunctionalitiesRowObject.getAttributionDisabled().contains("TRUE")
							&& scenariosOfSelectedFunctionalitiesRowObject.getPopType().contains("UNDER")) {
						scenariosOfSelectedFunctionalitiesRowObjects.remove(scenariosOfSelectedFunctionalitiesRowObject);
					}
				}

			}

			if (!selectedFunctionalities.contains("IYT")) {

				for (RowObject scenariosOfSelectedFunctionalitiesRowObject : scenariosOfSelectedFunctionalitiesRowObjects) {
					if (scenariosOfSelectedFunctionalitiesRowObject.getIsIYT().contains("YES")) {
						scenariosOfSelectedFunctionalitiesRowObjects.remove(scenariosOfSelectedFunctionalitiesRowObject);
					}
				}

			}
		}

		return scenariosOfSelectedFunctionalitiesRowObjects;
	}

	private List<RowObject> expandBrowserDataWithHtmlElements(List<RowObject> rowObjects, List<RowObject> htmlElementsObjects) throws IOException {
		List<RowObject> bowserWithHtmlElementRowObjects = new ArrayList<RowObject>();
		for (RowObject browserRowObject : rowObjects) {
			for (RowObject htmlElementObject : htmlElementsObjects) {
				RowObject bowserWithHtmlElementRowObject = new RowObject();

				bowserWithHtmlElementRowObject.setBrowser(browserRowObject.getBrowser());
				bowserWithHtmlElementRowObject.setBrowserVersion(browserRowObject.getBrowserVersion());
				bowserWithHtmlElementRowObject.setHtmlElement(htmlElementObject.getHtmlElement());

				bowserWithHtmlElementRowObjects.add(bowserWithHtmlElementRowObject);
			}
		}
		return bowserWithHtmlElementRowObjects;
	}

	private List<RowObject> expandBrowserAndHtmlElementsWithOs(List<RowObject> rowObjects, List<RowObject> osObjects) throws IOException {
		List<RowObject> browserAndHtmlElementsWithOsRowObjects = new ArrayList<RowObject>();

		for (RowObject bowserWithHtmlElementRowObject : rowObjects) {
			for (RowObject platformObject : osObjects) {
				RowObject browserAndHtmlElementsWithOsRowObject = new RowObject();

				browserAndHtmlElementsWithOsRowObject.setOs(platformObject.getOs());
				browserAndHtmlElementsWithOsRowObject.setOsVersion(platformObject.getOsVersion());
				browserAndHtmlElementsWithOsRowObject.setBrowser(bowserWithHtmlElementRowObject.getBrowser());
				browserAndHtmlElementsWithOsRowObject.setBrowserVersion(bowserWithHtmlElementRowObject.getBrowserVersion());
				browserAndHtmlElementsWithOsRowObject.setHtmlElement(bowserWithHtmlElementRowObject.getHtmlElement());
				browserAndHtmlElementsWithOsRowObject.setPlatformType("desktop");

				browserAndHtmlElementsWithOsRowObjects.add(browserAndHtmlElementsWithOsRowObject);
			}
		}
		return browserAndHtmlElementsWithOsRowObjects;
	}

	private List<RowObject> expandBrowserAndHtmlElementsAndOsWithScnarios(List<RowObject> bowserAndHtmlElementsWithOsRowObjects, List<RowObject> scenariosOfSelectedFunctionalitiesRowObjects)
			throws IOException {
		List<RowObject> browserAndHtmlElementsAndOsWithScenariosRowObjects = new ArrayList<RowObject>();

		for (RowObject bowserAndHtmlElementsWithOsRowObject : bowserAndHtmlElementsWithOsRowObjects) {
			for (RowObject scenariosOfSelectedFunctionalitiesRowObject : scenariosOfSelectedFunctionalitiesRowObjects) {
				RowObject browserAndHtmlElementsAndOsWithScenariosRowObject = new RowObject();

				browserAndHtmlElementsAndOsWithScenariosRowObject.setOs(bowserAndHtmlElementsWithOsRowObject.getOs());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setOsVersion(bowserAndHtmlElementsWithOsRowObject.getOsVersion());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setBrowser(bowserAndHtmlElementsWithOsRowObject.getBrowser());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setBrowserVersion(bowserAndHtmlElementsWithOsRowObject.getBrowserVersion());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setHtmlElement(bowserAndHtmlElementsWithOsRowObject.getHtmlElement());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setPlatformType("desktop");

				browserAndHtmlElementsAndOsWithScenariosRowObject.setTestComboNumber(scenariosOfSelectedFunctionalitiesRowObject.getTestComboNumber());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setAttributionDisabled(scenariosOfSelectedFunctionalitiesRowObject.getAttributionDisabled());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setPopType(scenariosOfSelectedFunctionalitiesRowObject.getPopType());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setIsIYT(scenariosOfSelectedFunctionalitiesRowObject.getIsIYT());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setPopOption(scenariosOfSelectedFunctionalitiesRowObject.getPopOption());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setChromeOption(scenariosOfSelectedFunctionalitiesRowObject.getChromeOption());
				browserAndHtmlElementsAndOsWithScenariosRowObject.setMacOption(scenariosOfSelectedFunctionalitiesRowObject.getMacOption());

				if (bowserAndHtmlElementsWithOsRowObject.getOs().equalsIgnoreCase("WINDOWS")) {
					if (bowserAndHtmlElementsWithOsRowObject.getBrowser().equalsIgnoreCase("CHROME")) {
						browserAndHtmlElementsAndOsWithScenariosRowObject.setExpectedPop(scenariosOfSelectedFunctionalitiesRowObject.getWindowsChromeExpectedPop());
						browserAndHtmlElementsAndOsWithScenariosRowObject.setExpectedWindowOrTab(scenariosOfSelectedFunctionalitiesRowObject.getWindowsChromeExpectedWindowOrTab());
					}
					if (bowserAndHtmlElementsWithOsRowObject.getBrowser().equalsIgnoreCase("FIREFOX")) {
						browserAndHtmlElementsAndOsWithScenariosRowObject.setExpectedPop(scenariosOfSelectedFunctionalitiesRowObject.getWindowsFirefoxExpectedPop());
						browserAndHtmlElementsAndOsWithScenariosRowObject.setExpectedWindowOrTab(scenariosOfSelectedFunctionalitiesRowObject.getWindowsFirefoxEpectedWindowOrTab());
					}

					if (bowserAndHtmlElementsWithOsRowObject.getBrowser().equalsIgnoreCase("IE")) {
						browserAndHtmlElementsAndOsWithScenariosRowObject.setExpectedPop(scenariosOfSelectedFunctionalitiesRowObject.getWindowsIEExpectedPop());
						browserAndHtmlElementsAndOsWithScenariosRowObject.setExpectedWindowOrTab(scenariosOfSelectedFunctionalitiesRowObject.getWindowsIEExpectedWindowOrTab());
					}
				}

				if (bowserAndHtmlElementsWithOsRowObject.getOs().equalsIgnoreCase("OS X")) {
					if (bowserAndHtmlElementsWithOsRowObject.getBrowser().equalsIgnoreCase("SAFARI")) {
						browserAndHtmlElementsAndOsWithScenariosRowObject.setExpectedPop(scenariosOfSelectedFunctionalitiesRowObject.getMacSafariExpectedPop());
						browserAndHtmlElementsAndOsWithScenariosRowObject.setExpectedWindowOrTab(scenariosOfSelectedFunctionalitiesRowObject.getMacSafariExpectedWindowOrTab());
					}
					if (bowserAndHtmlElementsWithOsRowObject.getBrowser().equalsIgnoreCase("CHROME")) {
						browserAndHtmlElementsAndOsWithScenariosRowObject.setExpectedPop(scenariosOfSelectedFunctionalitiesRowObject.getMacChromeExpectedPop());
						browserAndHtmlElementsAndOsWithScenariosRowObject.setExpectedWindowOrTab(scenariosOfSelectedFunctionalitiesRowObject.getMacChromeExpectedWindowOrTab());
					}

				}

				browserAndHtmlElementsAndOsWithScenariosRowObjects.add(browserAndHtmlElementsAndOsWithScenariosRowObject);
			}
		}
		return browserAndHtmlElementsAndOsWithScenariosRowObjects;
	}

	/* getting test cases to give input to automation script */
	public List<RowObject> prepareTestCases(String desktopOrMobile, String templateFilePath, String scenariosSheetName, String htmlElementsSheetName, String browserSheetName,
			String desktopOrMobileSheetName, String popFunctionalitiesSheetName) throws IOException {

		List<RowObject> browserAndHtmlElementsWithOsRowObjects = new ArrayList<RowObject>();
		List<RowObject> browserAndHtmlElementsAndOsWithScenariosRowObjects = new ArrayList<RowObject>();
		List<RowObject> browserRowObjects = new ArrayList<RowObject>();
		List<RowObject> htmlElementsObjects = new ArrayList<RowObject>();
		List<RowObject> browserWithHtmlElementRowObjects = new ArrayList<RowObject>();
		List<RowObject> osObjects = new ArrayList<RowObject>();
		List<RowObject> scenarioObjects = new ArrayList<RowObject>();
		ArrayList<RowObject> popFunctionalityObjects = new ArrayList<RowObject>();
		List<RowObject> scenariosOfSelectedFunctionalitiesRowObjects = new ArrayList<RowObject>();

		browserRowObjects = readBrowserData(templateFilePath, browserSheetName);

		htmlElementsObjects = readHTMLElementsData(templateFilePath, htmlElementsSheetName);

		osObjects = readDesktopPlatformsData(templateFilePath, desktopOrMobileSheetName);

		scenarioObjects = readScenariosData(templateFilePath, scenariosSheetName);

		popFunctionalityObjects = readPopFunctionalitiesData(templateFilePath, popFunctionalitiesSheetName);

		scenariosOfSelectedFunctionalitiesRowObjects = expandFunctionalitiesWithScenarios(popFunctionalityObjects, scenarioObjects);

		browserWithHtmlElementRowObjects = expandBrowserDataWithHtmlElements(browserRowObjects, htmlElementsObjects);

		if (desktopOrMobile.equalsIgnoreCase("desktop")) {
			browserAndHtmlElementsWithOsRowObjects = expandBrowserAndHtmlElementsWithOs(browserWithHtmlElementRowObjects, osObjects);
		}

		browserAndHtmlElementsAndOsWithScenariosRowObjects = expandBrowserAndHtmlElementsAndOsWithScnarios(browserAndHtmlElementsWithOsRowObjects,
				scenariosOfSelectedFunctionalitiesRowObjects);

		return browserAndHtmlElementsAndOsWithScenariosRowObjects;
	}

	/* Prepare Test Cases and write to excel sheet */
	public void writeTestCases(List<RowObject> rowObjects, String platformType, String sheetName, String filePath, Date startTime) throws IOException {
		XSSFWorkbook excelWorkBook = new XSSFWorkbook();
		Sheet sheet = excelWorkBook.createSheet(sheetName);
		int rowNum = 0;
		int noOfCells = 0;

		createHeader(sheet.createRow(rowNum++), excelWorkBook, platformType);

		for (RowObject rowObject : rowObjects) {
			int cellNo = 0;
			Row row = sheet.createRow(rowNum++);
			Cell cell;

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getTestComboNumber());
		
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

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getHtmlElement());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getAttributionDisabled());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getPopType());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getPopOption());
			
			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getIsIYT());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getChromeOption());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getMacOption());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue("");

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getExpectedPop());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, excelWorkBook);
			cell.setCellValue(rowObject.getExpectedWindowOrTab());

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
		FileOutputStream outFile = new FileOutputStream(fileUtils.getFilePath(filePath, startTime));
		excelWorkBook.write(outFile);
		outFile.close();
	}

	private void writeTestCaseResultsintoIndividualFiles(List<RowObject> outputData, String sheetName, String filePath, Date startTime) throws IOException {
		Workbook outputExcelWorkBook = new XSSFWorkbook();
		Sheet sheet = outputExcelWorkBook.createSheet(sheetName);
		int rowNum = 0;
		int noOfCells = 0;
		String platformType = outputData.get(0).getPlatformType();
		createHeader(sheet.createRow(rowNum++), outputExcelWorkBook, platformType);
		sheet.createFreezePane(0, 1);

		for (RowObject rowObject : outputData) {
			int cellNo = 0;
			Row row = sheet.createRow(rowNum++);
			Cell cell;

			cell = row.createCell(cellNo++);
			
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getTestComboNumber());
			
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

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getHtmlElement());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getAttributionDisabled());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getPopType());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getPopOption());
			
			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getIsIYT());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getChromeOption());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getMacOption());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue("");

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getExpectedPop());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getExpectedWindowOrTab());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue("");

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getTestCaseResult());

			cell = row.createCell(cellNo++);
			setCellStyle(cell, outputExcelWorkBook);
			cell.setCellValue(rowObject.getComments());

			rowObject.setRowNumber(rowNum);

			noOfCells = cellNo;
		}
		for (int position = 0; position <= noOfCells; position++) {
			sheet.autoSizeColumn(position);
		}
		FileOutputStream outFile = new FileOutputStream(fileUtils.getFilePath(filePath, startTime));
		outputExcelWorkBook.write(outFile);
		outFile.close();
	}

	/* Writing test case results to excel sheet */
	public void writeTestCaseResults(List<RowObject> outputData, String sheetName, String desktopOutputFilePath, String mobileOutputFilePath, Date startTime) throws IOException {

		List<RowObject> desktopRowObjects = new ArrayList<RowObject>();
		List<RowObject> mobileRowObjects = new ArrayList<RowObject>();

		/*
		 * for (RowObject rowObject : outputData) { if
		 * (rowObject.getPlatformType().equalsIgnoreCase("desktop") &&
		 * rowObject.isIYD() == true) { desktopIYDRowObjects.add(rowObject); }
		 * else if (rowObject.getPlatformType().equalsIgnoreCase("desktop") &&
		 * rowObject.isIYD() == false) { desktopIYRowObjects.add(rowObject); }
		 * else if (rowObject.getPlatformType().equalsIgnoreCase("mobile") &&
		 * rowObject.isIYD() == true) { mobileIYDRowObjects.add(rowObject); }
		 * else if (rowObject.getPlatformType().equalsIgnoreCase("mobile") &&
		 * rowObject.isIYD() == false) { mobileIYRowObjects.add(rowObject); } }
		 */

		for (RowObject rowObject : outputData) {
			if (rowObject.getPlatformType().equalsIgnoreCase("desktop")) {
				desktopRowObjects.add(rowObject);
			} else if (rowObject.getPlatformType().equalsIgnoreCase("mobile")) {
				mobileRowObjects.add(rowObject);
			}
		}

		if (!desktopRowObjects.isEmpty())
			writeTestCaseResultsintoIndividualFiles(desktopRowObjects, sheetName, desktopOutputFilePath, startTime);
		if (!mobileRowObjects.isEmpty())
			writeTestCaseResultsintoIndividualFiles(mobileRowObjects, sheetName, mobileOutputFilePath, startTime);
	}
}