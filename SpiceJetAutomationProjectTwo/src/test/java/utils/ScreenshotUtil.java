package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtil 
{
	    public static void takeScreenshot(WebDriver driver, String filePath) {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(source, new File(filePath));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

