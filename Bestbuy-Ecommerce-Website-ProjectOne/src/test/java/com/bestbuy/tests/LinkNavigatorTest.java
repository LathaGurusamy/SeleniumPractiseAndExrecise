package com.bestbuy.tests;

import com.bestbuy.base.BaseTest;
import com.bestbuy.utils.LinkValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkNavigatorTest extends BaseTest {


    
    @Test
    public void validateBaseUrlLink() {
        // Check if base URL is reachable
        boolean isBroken = LinkValidator.isLinkBroken(baseUrl);
        Assert.assertFalse(isBroken, "❌ Base URL is broken: " + baseUrl);
        System.out.println("✅ Base URL is valid: " + baseUrl);

        try {
            // Find the Best Buy logo
            WebElement logo = driver.findElement(By.xpath("//div[@class='top-nav top-nav-with-full-spanning-search']"));
            Assert.assertTrue(logo.isDisplayed(), "❌ Best Buy logo not visible—page may be broken.");
            System.out.println("✅ Page loaded successfully and Best Buy logo is visible.");
        } catch (Exception e) {
            System.out.println("❌ Page failed to load or Best Buy logo not found.");
            System.out.println("⚠️ Exception: " + e.getMessage());
            Assert.fail("Page is broken or Best Buy logo is missing.");
        }
    }

}
