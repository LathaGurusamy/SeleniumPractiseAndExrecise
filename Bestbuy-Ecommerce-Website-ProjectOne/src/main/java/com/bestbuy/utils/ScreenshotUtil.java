package com.bestbuy.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "screenshots/" + fileName + "_" + System.currentTimeMillis() + ".png";
            Files.copy(src.toPath(), Paths.get(path));
            System.out.println("📸 Screenshot saved: " + path);
        } catch (IOException e) {
            System.out.println("⚠️ Screenshot capture failed: " + e.getMessage());
        }
    }
}
