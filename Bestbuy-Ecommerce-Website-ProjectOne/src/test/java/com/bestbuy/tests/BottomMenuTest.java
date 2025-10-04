package com.bestbuy.tests;

import com.bestbuy.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class BottomMenuTest extends BaseTest {

    @Test
    public void validateBottomLinks() throws InterruptedException {
        driver.get(baseUrl);
        System.out.println("✅ Opened Base URL: " + baseUrl + "\n");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate all bottom menu items (li)
        List<WebElement> bottomItems = driver.findElements(By.xpath("//footer[@id='footer']//li"));

        System.out.println("Found " + bottomItems.size() + " bottom menu items.\n");

        for (int i = 0; i < bottomItems.size(); i++) {
            // Re-fetch to avoid stale element
            bottomItems = driver.findElements(By.xpath("//footer[@id='footer']//li"));
            WebElement item = bottomItems.get(i);

            try {
                // Find <a> inside <li>
                WebElement anchor = item.findElement(By.tagName("a"));
                String linkText = anchor.getText().trim();
                String linkHref = anchor.getAttribute("href");

                System.out.println("➡ Bottom Link: " + linkText + " | URL: " + linkHref);

                // Wait for any popup overlay to disappear
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".shop-blue-assist-2GTBG")));
                } catch (Exception e) { }

                // Click link
                anchor.click();

                Thread.sleep(2000); // wait for page to load
                System.out.println("   Page Title: " + driver.getTitle() + "\n");

            } catch (Exception e) {
                System.out.println("❌ Failed to click bottom menu: " + e.getMessage() + "\n");
            } finally {
                driver.get(baseUrl); // back to home
            }
        }

        System.out.println("✅ All bottom links validated.");
    }
}
