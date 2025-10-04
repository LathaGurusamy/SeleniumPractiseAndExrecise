package com.bestbuy.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchAndAddToCartTest {

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
    public void searchAndAddToCart() throws InterruptedException {
        // 1️⃣ Open Base URL
        driver.get(baseUrl);
        System.out.println("✅ Opened Base URL: " + baseUrl);

        // 2️⃣ Search for a product
        String searchItem = "Apple AirPods";
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@aria-label='Search']")));
        searchBox.sendKeys(searchItem);
        searchBox.sendKeys(Keys.ENTER);
        System.out.println("🔍 Searching for: " + searchItem);

        // 3️⃣ Wait for search results and click first product
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div//a[@class='product-list-item-link']")));
        List<WebElement> products = driver.findElements(By.xpath("//div//a[@class='product-list-item-link']"));

        if (products.size() > 0) {
            WebElement firstProduct = products.get(0);
            String firstProductName = firstProduct.getText();
            firstProduct.click();
            System.out.println("➡ Clicked on first product: " + firstProductName);
        } else {
            System.out.println("⚠️ No products found for search: " + searchItem);
            return;
        }

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
