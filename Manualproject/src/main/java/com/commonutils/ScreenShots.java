package com.commonutils;

import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShots {
	public static void captureScreenShot(WebDriver ldriver, String value) throws IOException {
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {
			String path =  "C:\\Users\\Guest\\Documents\\Seleniumeasy\\Manualproject\\src\\test\\resources\\excel\\screenshots\\";
			// now copy the screenshot to desired location using copyFile method
			FileUtils.copyFile(src,
					new File(path + value + ".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}


}
