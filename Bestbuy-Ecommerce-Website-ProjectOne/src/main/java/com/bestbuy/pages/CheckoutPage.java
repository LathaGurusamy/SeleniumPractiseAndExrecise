package com.bestbuy.pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bestbuy.utils.ConfigReader;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }

    // ✅ 1. Navigate to Cart
    public void goToCart() {
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@title='Cart']")));
        actions.moveToElement(cartIcon).pause(Duration.ofMillis(500)).click().perform();
        System.out.println("🛒 Navigated to Cart page.");
    }

    // ✅ 2. Proceed to Checkout
    public void proceedToCheckout() {
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Checkout')]")));
        actions.moveToElement(checkoutBtn).pause(Duration.ofMillis(500)).click().perform();
        System.out.println("➡️ Clicked Checkout button.");
    }


    // ✅ 4. Fill Contact Info
    public void fillContactInformationAndContinue() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@id='user.phone']")
            ));
            String phoneNumber = ConfigReader.get("phoneNumber");

            phoneField.clear();
            phoneField.sendKeys(phoneNumber);

            js.executeScript("arguments[0].dispatchEvent(new Event('input'));", phoneField);
            js.executeScript("arguments[0].dispatchEvent(new Event('change'));", phoneField);
            System.out.println("📞 Entered phone number from config: " + phoneNumber);

        } catch (Exception e) {
            System.out.println("⚠️ Failed in Contact Information step: " + e.getMessage());
            throw new RuntimeException("Contact info step failed.");
        }
    }

    // ✅ 5. Continue to Payment
    public void clickContinueToPaymentAndWait() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            ((JavascriptExecutor) driver).executeScript("document.body.style.overflow = 'hidden';");
            System.out.println("🔒 Scroll locked.");

            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.page-spinner.page-spinner--in")
            ));
            System.out.println("⏳ Spinner disappeared.");

            WebElement continueBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Continue to Payment')]")
            ));
            continueBtn.click();
            System.out.println("➡️ Clicked Continue to Payment.");

            shortWait.until(ExpectedConditions.urlContains("/payment"));
            System.out.println("✅ URL transitioned to payment page.");

            shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Payment Information')]")
            ));
            System.out.println("🧾 Payment section is visible.");

            ((JavascriptExecutor) driver).executeScript("document.body.style.overflow = 'auto';");
            System.out.println("🔓 Scroll restored.");

        } catch (Exception e) {
            System.out.println("❌ Error during payment navigation: " + e.getMessage());
            throw new RuntimeException("Failed to reach payment page.");
        }
    }
}
