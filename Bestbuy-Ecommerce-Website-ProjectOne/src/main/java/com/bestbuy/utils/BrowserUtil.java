package com.bestbuy.utils;

import java.util.Arrays;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserUtil {

	public static WebDriver launchBrowser(String browserName) {
	    WebDriver driver;

	    switch (browserName.toLowerCase()) {
	        case "chrome":
	            ChromeOptions options = new ChromeOptions();

	            // ✅ Remove Selenium automation flags
	            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
	            options.setExperimentalOption("useAutomationExtension", false);

	            // ✅ Language
	            options.addArguments("--lang=en-US");

	            // ✅ Disable WebDriver detection
	            options.addArguments("--disable-blink-features=AutomationControlled");

	            // ✅ Browser settings
	            options.addArguments("--start-maximized");
	            options.addArguments("--disable-notifications");
	            options.addArguments("--disable-popup-blocking");

	            // ✅ Spoof user-agent
	            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
	                    + "AppleWebKit/537.36 (KHTML, like Gecko) "
	                    + "Chrome/141.0.0.0 Safari/537.36");

	            // ✅ Optional: Disable CDP if causing issues
	            options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
	                put("profile.default_content_setting_values.notifications", 2);
	            }});

	            try {
	                driver = new ChromeDriver(options);
	            } catch (Exception e) {
	                System.out.println("❌ ChromeDriver launch failed: " + e.getMessage());
	                throw new RuntimeException("Failed to launch Chrome browser", e);
	            }

	            // ✅ Override navigator.webdriver = false
	            try {
	                ((JavascriptExecutor) driver).executeScript(
	                    "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
	                );
	            } catch (Exception jsEx) {
	                System.out.println("⚠️ JavaScript override failed: " + jsEx.getMessage());
	            }

	            break;

	        case "firefox":
	            driver = new FirefoxDriver();
	            break;

	        case "edge":
	            driver = new EdgeDriver();
	            break;

	        default:
	            throw new IllegalArgumentException("Unsupported browser: " + browserName);
	    }

	    System.out.println("✅ Browser launched successfully: " + browserName);
	    return driver;
	}
	
	public static void maximizeWindow(WebDriver driver) {
	    if (driver != null) {
	        driver.manage().window().maximize();
	        System.out.println("🖥️ Browser window maximized.");
	    } else {
	        System.out.println("⚠️ WebDriver is null. Cannot maximize window.");
	    }
	}

}