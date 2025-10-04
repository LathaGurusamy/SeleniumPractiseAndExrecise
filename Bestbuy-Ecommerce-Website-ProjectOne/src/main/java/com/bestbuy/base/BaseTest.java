package com.bestbuy.base;

import com.bestbuy.utils.BrowserUtil;
import com.bestbuy.utils.ConfigReader;
import com.bestbuy.utils.CookieRead;
import com.bestbuy.utils.CookieUtil;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public WebDriver driver;
    public String baseUrl;
    @BeforeMethod(alwaysRun = true)
    public void setup() {
        try {
            String browser = ConfigReader.get("browser");
            baseUrl = ConfigReader.get("baseUrl");

            System.out.println("🚀 Launching browser...");
            driver = BrowserUtil.launchBrowser(browser);

            driver.manage().deleteAllCookies();

            System.out.println("🌐 Navigating to baseUrl: " + baseUrl);
            driver.get(baseUrl);

            // ⏳ Wait until real page loads (not data URI)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(d -> !d.getCurrentUrl().startsWith("data:"));

            System.out.println("✅ Real page loaded: " + driver.getCurrentUrl());

            // 🍪 Now inject cookies safely
            CookieUtil.loadCookies(driver, "src/test/resources/cookies.txt");
            CookieRead.injectCookies(driver, "src/test/resources/config.properties");

            // 🔄 Refresh after cookie injection
            driver.navigate().refresh();

            BrowserUtil.maximizeWindow(driver);
            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("❌ Setup failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Setup failed", e);
        }
    }



   
    //@AfterMethod(alwaysRun = true)
    //public void tearDown() {
      //  if (driver != null) {
        //  driver.quit();
        //}
    //}

  
    
}
