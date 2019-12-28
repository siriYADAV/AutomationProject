package com.testsrcripts;



import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.commonutils.ScreenShots;
import com.excelutilities.ExcelUtilities;
import com.weblocators.FirstformWebLocators;




public class SeleniumeasyFirstform {

	
	WebDriver driver;
    private final int NUM_OF_EXCEL_COLUMNS = 3;

	@BeforeTest
	public void beforeTest() throws IOException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Browser initialized");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ScreenShots.captureScreenShot(driver, "TC_Screenshot1");

	}

	@Test(dataProvider="excelData")
	// Number of arguments must match with number of columns in excel sheet
	public void loginPage( String textValue, double numberA, double numberB) {
		try {
			driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html" );
			System.out.println("Seleniumeasy page  is displayed");
					
			ScreenShots.captureScreenShot(driver, "TC_Screenshot2");
			driver.findElement(By.xpath(FirstformWebLocators.Entermessage_XPATH)).sendKeys(textValue);
			driver.findElement(By.cssSelector(FirstformWebLocators.ShowMessage_CSSSELECTOR)).click();
			System.out.println("Message is displayed");
			driver.findElement(By.xpath(FirstformWebLocators.Entera_XPATH)).sendKeys(String.valueOf(numberA));
			driver.findElement(By.xpath(FirstformWebLocators.Enterb_XPATH)).sendKeys(String.valueOf(numberB));
			driver.findElement(By.xpath(FirstformWebLocators.GET_TOTAL_XPATH)).click();
			System.out.println("GetTotal value is displayed");
			ScreenShots.captureScreenShot(driver, "TC_Screenshot3");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception undergone");
		}
	}
	
	@DataProvider(name="excelData")
    public Object[][] getAuthentication() throws Exception{
		 String filePath = "C:\\Users\\Guest\\Documents\\Seleniumeasy\\Manualproject\\src\\test\\resources\\excel\\";
         Object[][] testObjArray = ExcelUtilities.get2DArray(filePath, "TestExcel.xlsx", "Sheet1",NUM_OF_EXCEL_COLUMNS);
         return (testObjArray);
	}

	@AfterTest()
	public void afterTest() throws IOException {
		//driver.close();
	}

}
