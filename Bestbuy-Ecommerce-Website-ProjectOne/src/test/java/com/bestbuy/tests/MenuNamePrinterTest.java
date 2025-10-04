package com.bestbuy.tests;

import com.bestbuy.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class MenuNamePrinterTest extends BaseTest {

    @Test
    public void printMenuNames() {
        // Open the base URL
        driver.get(baseUrl);
        System.out.println("✅ Opened Base URL: " + baseUrl);

        // Locate all top menu links
        List<WebElement> menuItems = driver.findElements(By.xpath("//nav[@class='bottom-nav' or @class='bottom-nav-left lv']//a"));
        
        ////nav[@class='bottom-nav']//a"--This also work

        // Check if menu items are found
        if (menuItems.isEmpty()) {
            System.out.println("❌ No menu items found.");
        } else {
            System.out.println("Found " + menuItems.size() + " menu items:");
            for (WebElement menu : menuItems) {
                String menuName = menu.getText().trim();
                if (!menuName.isEmpty()) {
                    System.out.println("➡ " + menuName);
                }
            }
        }
    }
}
