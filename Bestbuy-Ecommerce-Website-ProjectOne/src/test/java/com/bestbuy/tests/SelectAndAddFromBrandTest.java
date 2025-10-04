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

public class SelectAndAddFromBrandTest {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.bestbuy.com/?intl=nosplash";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void selectAndAddFromBrand() throws InterruptedException {
        // 1️⃣ Open Base URL
        driver.get(baseUrl);
        System.out.println("✅ Opened Base URL: " + baseUrl);

        // 2️⃣ Open Menu
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Menu']")));
        menuButton.click();
        System.out.println("📂 Clicked Menu button");

        // 3️⃣ Select "Brands"
        WebElement brandsMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Brands')]")));
        brandsMenu.click();
        System.out.println("➡ Selected Menu: Brands");

        // 4️⃣ Select a Brand (example: Apple)
        WebElement brand = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Apple')]")));
        brand.click();
        System.out.println("➡ Selected Brand: Apple");

        // 5️⃣ Scroll down to load sub-brands
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,800);");
        Thread.sleep(1000);
    
        
        // 6.Scroll down until "Shop all Apple" is visible
        WebElement subBrandContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h2[contains(text(),'Shop all Apple')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subBrandContainer);
        Thread.sleep(1000);
     // ✅ Get the text
        String shopAllText = subBrandContainer.getText();
        System.out.println("📌 Found heading text: " + shopAllText);
        
        
     // 7. Get all sub-brand tiles (like iPhone, iPad, Mac...)
        List<WebElement> subBrands = driver.findElements(
            By.xpath("//a[@class='flex-image-inner-wrapper flex-fluid-image']"));

        // Check if list has items:iPhone
        if (!subBrands.isEmpty()) {
            WebElement firstBrand = subBrands.get(0); // 0 → first element in the list
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstBrand);
            Thread.sleep(500);
            firstBrand.click();
            System.out.println("➡ Clicked first sub-brand (expected: iPhone)");
        } else {
            System.out.println("⚠️ No sub-brands found!");
        }
        
        
        //8. Scroll down to load sub-brands:iPhone
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,800);");
        Thread.sleep(1000);
        
     //  9.Wait for the headline to be visible
        WebElement headline = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h2[@id='flex-header-c49b675f-f9aa-4d6b-9e6d-cca0fb66be41']")));
      //Get the text
        String headlineText = headline.getText();
        System.out.println("📌 Headline on iPhone page: " + headlineText);

        //10.Wait for the product name inside the tile
        WebElement iphone17ProMax = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[text()='iPhone 17 Pro Max']")));

        String phoneName = iphone17ProMax.getText();
        System.out.println("📌 Selected iPhone model: " + phoneName);
        
     // 11️⃣ Locate all "Shop now" links
        List<WebElement> shopNowLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//a[text()='Shop now']")));

        // 12️⃣ Check and click the 4th one (index 3)
        if (!shopNowLinks.isEmpty() && shopNowLinks.size() > 3) {
            WebElement targetLink = shopNowLinks.get(3);

            // Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetLink);
            Thread.sleep(500); // Optional smooth scroll

            // Use JS click for reliability
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetLink);
            System.out.println("🛒 Clicked 4th 'Shop now' link (index 3)");
        } else {
            System.out.println("⚠️ 'Shop now' link at index 3 not found! Total found: " + shopNowLinks.size());
        }
        
        
        
     // 🔍 12.Locate the product tile by full title
        WebElement iphoneTile = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//h1[contains(text(),'Apple - iPhone 17 Pro 256GB - Deep Blue (AT&T)')]")));
        String productTitle = iphoneTile.getText();
        System.out.println("📌 Product title found: " + productTitle);
        
     // 13.🔁 Keep scrolling until "Add to cart" button is visible or max attempts reached
        int maxScrolls = 10;
        boolean buttonVisible = false;

        for (int i = 0; i < maxScrolls; i++) {
            try {
                WebElement addToCartBtn = driver.findElement(
                    By.xpath("//span[text()='Add to cart']/ancestor::button"));

                if (addToCartBtn.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
                    Thread.sleep(500);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
                    System.out.println("🛒 Clicked 'Add to cart' button");
                    buttonVisible = true;
                    break;
                }
            } catch (Exception e) {
                // Button not found yet, scroll more
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,800);");
                Thread.sleep(1000);
            }
        }

        if (!buttonVisible) {
            System.out.println("⚠️ 'Add to cart' button not found after scrolling.");
        }

     
     // 14.🔍 Wait for AT&T setup prompt
        WebElement attPrompt = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//p[contains(text(),'Letʼs set up your device with AT&T.')]")));
        System.out.println("📌 Landed on AT&T setup page");



    }
} 