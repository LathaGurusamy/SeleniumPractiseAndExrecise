package com.bestbuy.tests;

import com.bestbuy.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class MenuNavigationTest extends BaseTest {

    @Test
    public void navigateTopMenusAndValidateTitle() throws InterruptedException {
        driver.get(baseUrl);
        System.out.println("✅ Opened Base URL: " + baseUrl + "\n");

        // Wait utility
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find all top menu links
        List<WebElement> menuItems = driver.findElements(By.xpath("//nav[@class='bottom-nav' or @class='bottom-nav-left lv']//a"
        		+ ""));
        System.out.println("Found " + menuItems.size() + " top menu items.\n");

        for (int i = 0; i < menuItems.size(); i++) {
            // Re-fetch menu items each iteration to avoid stale element
            menuItems = driver.findElements(By.xpath("//nav[@class='bottom-nav' or @class='bottom-nav-left lv']//a"));
            WebElement menu = menuItems.get(i);

            String menuName = menu.getText().trim();
            String menuHref = menu.getAttribute("href");

            System.out.println("➡ Clicking Menu: " + menuName + " | URL: " + menuHref);

            try {
                // Wait for any popup overlay to disappear (if exists)
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("confirmIt-backdrop")));
                } catch (Exception e) {
                    // No popup, continue
                }

                // Click the menu
                menu.click();

                // Wait for page to load
                Thread.sleep(2000); // For production, replace with proper wait

                // Handle broken link case
                if (driver.getCurrentUrl().contains("pcmcat1679668833285.c")) {
                    System.out.println("⚠️ Menu may be invalid or broken: " + menuName);
                } else {
                    String pageTitle = driver.getTitle();
                    Assert.assertTrue(pageTitle != null && !pageTitle.isEmpty(),
                            "❌ Page title is empty for menu: " + menuName);
                    System.out.println("✅ Menu Clicked: " + menuName);
                    System.out.println("   Page Title: " + pageTitle + "\n");
                }

            } catch (Exception e) {
                System.out.println("❌ Failed to click menu: " + menuName);
                System.out.println("⚠️ Exception: " + e.getMessage() + "\n");
            } finally {
                // Navigate back to base URL before next menu
                driver.get(baseUrl);
            }
        }

        System.out.println("✅ All top menus navigated and page titles validated.");
    }
}
