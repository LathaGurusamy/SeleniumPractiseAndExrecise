package com.demoblaze.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass

{
	
	
	    public WebDriver driver;
	    
	    public void initializeDriver() {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.demoblaze.com/");
	    }
	    
	    public void quitDriver() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}

