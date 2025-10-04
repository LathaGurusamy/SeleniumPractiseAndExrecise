package com.bestbuy.tests;

import com.bestbuy.base.BaseTest;
import com.bestbuy.pages.CheckoutPage;
import com.bestbuy.pages.LoginPage;
import com.bestbuy.pages.PaymentPage;
import com.bestbuy.utils.BrowserUtil;
import com.bestbuy.utils.ConfigReader;
import com.bestbuy.utils.ScreenshotUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginToBestBuy() {
        try {
            LoginPage login = new LoginPage(driver);


            login.clickInitialSignIn();
            System.out.println("✅ Clicked initial Sign In");
            Thread.sleep(2000);

            login.clickPopupSignIn();
            System.out.println("✅ Clicked Sign In button inside gateway.");
            Thread.sleep(2000);

            login.enterEmail(ConfigReader.get("email"));
            System.out.println("📧 Entered email: " + ConfigReader.get("email"));
            Thread.sleep(2000);

            login.clickContinueAfterEmail();
            System.out.println("➡️ Clicked Continue after email.");
            Thread.sleep(2000);

            login.selectPasswordRadio();
            System.out.println("✅ Selected password radio button (5th index)");
            Thread.sleep(500);

            login.submitPassword(ConfigReader.get("password"));
            System.out.println("📧 Entered password: " + ConfigReader.get("password"));
            Thread.sleep(500);

            login.checkLoginErrorAfterContinue();

            // ✅ Verify successful login
            Thread.sleep(3000);
            login.verifyHomePageGreeting();
            Thread.sleep(3000);
            System.out.println("💳 Login flow completed.");
            ScreenshotUtil.capture(driver, "login_success");
       
            
         // After successful login verification-Card
            CheckoutPage checkout = new CheckoutPage(driver);
            checkout.goToCart();
            checkout.proceedToCheckout();
            checkout.fillContactInformationAndContinue();
            checkout.clickContinueToPaymentAndWait();
            System.out.println("💳 Card  flow completed.");
            ScreenshotUtil.capture(driver, "card_flow_completed");
            
             //Payment
            PaymentPage paymentdetails= new PaymentPage(driver);
            paymentdetails.fillPaymentAndBilling();
            paymentdetails.pay();
            System.out.println("💳 Dummy Payment Information details given.");
            ScreenshotUtil.capture(driver, "dummy_payment_failure");

            

        } catch (Exception e) {
            System.out.println("⚠️ Exception during login test: " + e.getMessage());
            Assert.fail("Test failed due to unexpected error.");
        }
        
    }
    
    
}
