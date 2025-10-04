package com.bestbuy.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }

    // ✅ Step 1: Click initial Sign In
    public void clickInitialSignIn() {
        WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='v-p-right-xxs line-clamp']")));
        actions.moveToElement(signInBtn).pause(Duration.ofMillis(500)).click().perform();
    }

    // ✅ Step 2: Click popup Sign In
    public void clickPopupSignIn() {
        WebElement popupSignIn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class,'c-button') and contains(text(),'Sign In')]")));
        actions.moveToElement(popupSignIn).pause(Duration.ofMillis(500)).click().perform();
    }

    // ✅ Step 3: Enter email
    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fld-e")));
        emailField.clear();
        emailField.sendKeys(email);
    }

    // ✅ Step 4: Click Continue after email
    public void clickContinueAfterEmail() {
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Continue')]")));
        actions.moveToElement(continueBtn).pause(Duration.ofMillis(500)).click().perform();
    }

    // ✅ Step 5: Select password radio option
    public void selectPasswordRadio() {
        List<WebElement> radioOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//input[@type='radio']")));
        if (radioOptions.size() >= 5) {
            actions.moveToElement(radioOptions.get(4)).pause(Duration.ofMillis(500)).click().perform();
        } else {
            throw new NoSuchElementException("Password radio option not found.");
        }
    }

    // ✅ Step 6: Enter password and click Continue
    public void submitPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fld-p1")));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement toggleBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("show-hide-password-toggle")));
        actions.moveToElement(toggleBtn).pause(Duration.ofMillis(300)).click().perform();
        actions.moveToElement(toggleBtn).pause(Duration.ofMillis(300)).click().perform();

        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@class='c-button c-button-secondary c-button-lg c-button-block c-button-icon c-button-icon-leading cia-form__controls__submit ']")));
        actions.moveToElement(continueBtn).pause(Duration.ofMillis(500)).click().perform();
    }

    // ✅ Step 7: Check for login error
    public void checkLoginErrorAfterContinue() {
        List<WebElement> errorMessages = driver.findElements(
                By.xpath("//*[contains(text(),'went wrong') or contains(text(),'try again')]"));

        if (!errorMessages.isEmpty()) {
            String errorText = errorMessages.get(0).getText().trim();
            if (!errorText.isEmpty()) {
                throw new RuntimeException("Login failed due to error: " + errorText);
            }
        }
    }

    // ✅ Step 8: Verify home page greeting
    public void verifyHomePageGreeting() {
        try {
            // ⏳ Wait for potential redirect or greeting
            WebDriverWait postLoginWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            postLoginWait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("account"),
                    ExpectedConditions.urlMatches(".*bestbuy.com.*")
            ));

            // 📍 Log current URL
            String currentUrl = driver.getCurrentUrl();
            System.out.println("📍 Current URL after login: " + currentUrl);

            // ❗ Check for hidden login errors
            List<WebElement> errors = driver.findElements(By.xpath("//*[contains(text(),'try again') or contains(text(),'incorrect')]"));
            if (!errors.isEmpty()) {
                System.out.println("❌ Login error: " + errors.get(0).getText());
                throw new RuntimeException("Login failed due to error message.");
            }

            // 🧭 Scroll to top in case greeting is off-screen
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

            // 🔍 Wait for greeting element
            WebElement greeting = postLoginWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//span[contains(text(),'Hi,')] | //span[contains(text(),'Account')]")
            ));

            if (greeting.isDisplayed()) {
                System.out.println("🎉 Login successful! Greeting: " + greeting.getText());
            } else {
                throw new RuntimeException("Greeting element found but not visible.");
            }

        } catch (TimeoutException te) {
            System.out.println("⚠️ Greeting not found within timeout.");
            System.out.println("📄 Page title: " + driver.getTitle());
            System.out.println(driver.getPageSource().substring(0, 1000)); // Preview only
            throw new RuntimeException("Home page greeting verification failed.");
        } catch (Exception e) {
            System.out.println("⚠️ Exception during greeting verification: " + e.getMessage());
            throw new RuntimeException("Greeting check failed.");
        }
    }

  
    }

