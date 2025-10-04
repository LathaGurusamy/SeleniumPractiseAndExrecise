package com.bestbuy.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bestbuy.base.BaseTest;
import com.bestbuy.pages.LoginPage;
import com.bestbuy.utils.ConfigReader;
import com.bestbuy.utils.ScreenshotUtil;

import java.time.Duration;

public class BestBuyOpenToCloseBrowserTest extends BaseTest {


    @Test
    public void loginFlowTest() {
        try {
          
            // 🔐 Call LoginPage methods
            LoginPage login = new LoginPage(driver);

            login.clickInitialSignIn();
            Thread.sleep(2000);

            login.clickPopupSignIn();
            Thread.sleep(2000);

            login.enterEmail(ConfigReader.get("email"));
            Thread.sleep(1000);

            login.clickContinueAfterEmail();
            Thread.sleep(2000);

            login.selectPasswordRadio();
            Thread.sleep(1000);

            login.submitPassword(ConfigReader.get("password"));
            Thread.sleep(3000);

            login.checkLoginErrorAfterContinue();
            Thread.sleep(3000);
            
            login.verifyHomePageGreeting();
            Thread.sleep(3000);
            
            System.out.println("💳 Login flow completed.");
            ScreenshotUtil.capture(driver, "login_success");
            
          
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                    // 🔽 Click the account dropdown
                    WebElement accountDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[contains(text(),'Hi, Latha')]")
                    ));
                    accountDropdown.click();
                    System.out.println("🔽 Account dropdown clicked.");

                    // ⏳ Wait for Sign Out button to appear
                    WebElement signOutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.id("logout-button-bby")
                    ));
                    signOutBtn.click();
                    System.out.println("🚪 Signed out successfully.");
                    ScreenshotUtil.capture(driver, "logout_success");



       

        } catch (Exception e) {
            System.out.println("❌ Exception during login flow: " + e.getMessage());
        } finally {
           if (driver != null) {
                driver.quit();
               System.out.println("🛑 Browser closed.");
            }
        }
    }
}
