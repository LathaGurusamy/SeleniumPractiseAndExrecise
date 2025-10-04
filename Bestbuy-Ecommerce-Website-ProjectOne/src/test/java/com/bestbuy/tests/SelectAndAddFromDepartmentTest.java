package com.bestbuy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelectAndAddFromDepartmentTest 
{
	WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.bestbuy.com/?intl=nosplash";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void selectAndAddFromDepartmentTest() throws InterruptedException {
    	
    	// 1️⃣ Open Base URL
        driver.get(baseUrl);
        System.out.println("✅ Opened Base URL: " + baseUrl);

        // 2 Open Menu (hamburger icon top-left)
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Menu']")));
        menuButton.click();
        System.out.println("📂 Clicked Menu button");

  
        // 3 Select Department directly (example: "TVs & Home Theater")
        String departmentName = "TV & Home Theater";

        WebElement department = wait.until(ExpectedConditions.elementToBeClickable(
        	    By.xpath("//button[contains(text(),'" + departmentName + "')]")));
        	department.click();
            System.out.println("📂 Clicked TV & Home Theater");


        // 4 Select Subcategory (example: "TVs")--97-Inch or Larger TVs
        	String subCategoryName = "97-Inch or Larger TVs";

        	WebElement subCategory = wait.until(ExpectedConditions.elementToBeClickable(
        		    By.xpath("//a[contains(text(),'" + subCategoryName + "')]")));
        		subCategory.click();
                System.out.println("📂 Clicked 97-Inch or Larger TVs");

        // 4️⃣ Scroll to Add to Cart button and click
        Thread.sleep(2000); // wait for page to load
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,800);");
        Thread.sleep(1000);

        List<WebElement> addToCartButtons = driver.findElements(
                By.xpath("//button[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'add to cart')]")
        );

        if (addToCartButtons.size() > 0) {
            WebElement addToCartBtn = addToCartButtons.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
            System.out.println("🛒 Clicked Add to Cart button");

            // 5️⃣ Wait for confirmation
            try {
                WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'added-to-cart')]")
                ));
                System.out.println("✅ Item successfully added to cart: " + confirmation.getText());
            } catch (Exception e) {
                System.out.println("⚠️ Add to Cart confirmation not found.");
            }

        } else {
            System.out.println("⚠️ Add to Cart button not found. Product may be out of stock.");
        }
    }

           

    }
