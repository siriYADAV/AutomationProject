package com.excelutilities;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtilities {
	
public static Object[][] get2DArray(String filePath, String fileName, String sheetName, Integer numColumns) throws IOException {
		
		Object[][] tabArray = null;
		
		// Create a object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook excelWorkbook = null;
		excelWorkbook = new XSSFWorkbook(inputStream);
		// Read sheet inside the workbook by its name
		Sheet excelSheet = excelWorkbook.getSheet(sheetName);
		// Find number of rows in excel file
		int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
		int columnCount = numColumns;
		
		tabArray = new Object[rowCount][columnCount];
		
		// Create a loop over all the rows of excel file to read it
		for (int i = 1; i <= rowCount ; i++) {
			Row row = excelSheet.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 0; j < columnCount; j++) {
				
				int cellType = row.getCell(j).getCellType();
				if(cellType == 1) {
					// Print excel data in console
					tabArray[i-1][j] = row.getCell(j).getStringCellValue() ;
				} else if(cellType == 0) {
					// Print excel data in console
					tabArray[i-1][j] = row.getCell(j).getNumericCellValue() ;
				}
				
			}
		}
		return tabArray;
	}

	// Main function is calling readExcel function to read data from excel file
	public static void main(String[] args) throws IOException {
		
		// Create a object of ExcelFile class
		ExcelUtilities objExcelFile = new ExcelUtilities();
		// Prepare the path of excel file
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel";
		
		// Call read file method of the class to read data
		objExcelFile.get2DArray(filePath, "TestExcel.xlsx", "Sheet1",2);
	}
}

