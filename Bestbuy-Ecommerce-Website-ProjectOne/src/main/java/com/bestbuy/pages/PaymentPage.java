package com.bestbuy.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import com.bestbuy.utils.ConfigReader;

public class PaymentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // --- Locators ---
    private By cardTypeOption = By.xpath("//img[translate(@alt, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" 
            + ConfigReader.get("cardType").toLowerCase() + "']");
    private By cardNumberField = By.id("number");
    private By expMonthandYearField = By.id("expirationDate");
    private By cvvField = By.id("cvv");

    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By streetField = By.id("address-input");
    private By cityField = By.id("city");
    private By stateField = By.id("state"); // Native <select>
    private By zipField = By.id("postalCode");

    private By continueBtn = By.xpath("//span[contains(text(),'Place Your Order')]");

    // --- Utility: Scroll + Fill + Validate ---
    private void scrollFillValidate(By locator, String value, String fieldName) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(300); // Stabilize scroll
            element.clear();
            element.sendKeys(value);

            String actual = element.getAttribute("value");
            if (!actual.equals(value)) {
                System.out.println("[WARN] " + fieldName + " mismatch. Expected: " + value + ", Found: " + actual);
            } else {
                System.out.println("[PASS] " + fieldName + " filled correctly.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to fill " + fieldName + ": " + e.getMessage());
        }
    }

    // --- Utility: Scroll + Select from <select> dropdown ---
    private void scrollSelectDropdown(By locator, String visibleText, String fieldName) {
        try {
            WebElement dropdown = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
            Thread.sleep(300); // Stabilize scroll

            Select select = new Select(dropdown);
            select.selectByVisibleText(visibleText);

            String selected = select.getFirstSelectedOption().getText();
            if (!selected.equalsIgnoreCase(visibleText)) {
                System.out.println("[WARN] " + fieldName + " selection mismatch. Expected: " + visibleText + ", Found: " + selected);
            } else {
                System.out.println("[PASS] " + fieldName + " selected: " + selected);
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to select " + fieldName + ": " + e.getMessage());
        }
    }

    // --- Main Method: Fill Payment + Billing ---
    public PaymentPage fillPaymentAndBilling() {
        try {
            // 🌐 Confirm we're on payment page
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("/payment")) {
                throw new IllegalStateException("❌ Not on payment page. URL: " + currentUrl);
            }
            System.out.println("✅ Confirmed payment page URL: " + currentUrl);

            // 🧾 Wait for payment section
            WebElement paymentHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Payment Information')]")
            ));
            System.out.println("🧾 Payment section header: " + paymentHeader.getText());

            // 💳 Select card type
            WebElement cardType = wait.until(ExpectedConditions.elementToBeClickable(cardTypeOption));
            cardType.click();
            System.out.println("💳 Selected card type: " + ConfigReader.get("cardType"));

            // 💳 Fill card details
            scrollFillValidate(cardNumberField, ConfigReader.get("cardNumber"), "Card Number");
            scrollFillValidate(expMonthandYearField, ConfigReader.get("expMonthandYear"), "Expiration Date");
            scrollFillValidate(cvvField, ConfigReader.get("cvv"), "CVV");

            // 🏠 Fill billing details
            scrollFillValidate(firstNameField, ConfigReader.get("billingFirstName"), "First Name");
            scrollFillValidate(lastNameField, ConfigReader.get("billingLastName"), "Last Name");
            scrollFillValidate(streetField, ConfigReader.get("billingAddress"), "Street");
            scrollFillValidate(cityField, ConfigReader.get("billingCity"), "City");
            scrollSelectDropdown(stateField, ConfigReader.get("billingState"), "State");
            scrollFillValidate(zipField, ConfigReader.get("billingZip"), "Zip");

            System.out.println("✅ Payment and billing info filled successfully.");

        } catch (Exception e) {
            System.out.println("❌ Error while filling payment info: " + e.getMessage());
            throw new RuntimeException("Payment form fill failed.");
        }

        return this;
    }

    /** 🛒 Click continue/place order button */
    /** 🛒 Click continue/place order button and validate card error */
    public void pay() {
        try {
            driver.findElement(continueBtn).click();
            System.out.println("🛒 Clicked 'Place Your Order' button.");

            // ⏳ Wait briefly for validation message to appear
            Thread.sleep(1000); // Adjust if needed for spinner timing

            // 🔍 Check for card number error
            By cardErrorLocator = By.xpath("//p[contains(text(),'Please enter a valid card number.')]");
            if (driver.findElements(cardErrorLocator).size() > 0) {
                WebElement errorMsg = driver.findElement(cardErrorLocator);
                System.out.println("❌ Card validation failed: " + errorMsg.getText());
            } else {
                System.out.println("✅ No card validation error detected.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error during payment submission: " + e.getMessage());
        }
    }

    }

