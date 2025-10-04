package SeleniumGoal.SeleniumPractiseAndExrecise;

import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TwentyTastOne {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Function to capture screenshots
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // 1. Open the browser and navigate to the Snapdeal website
        driver.get("http://www.snapdeal.com");
        System.out.println("Opened Snapdeal website.");

        // 2. Wait for the Sign In button to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Increased wait time
        WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sign In']")));
        System.out.println("Sign In button is visible.");

        // Move the cursor to the Sign In button and hold it
        Actions actions = new Actions(driver);
        actions.moveToElement(signInButton).perform();
        System.out.println("Hovered over the Sign In button.");

        // 3. Click on the Sign In button
        signInButton.click();
        System.out.println("Clicked on the Sign In button.");
        
        // 4. Click on the LOGIN button
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='accountBtn btn rippleWhite']")));
        loginButton.click();
        System.out.println("Clicked on the Login button.");

        // 5. Check for iframes and switch if necessary
        try {
            System.out.println("Checking for iframes...");
            int iframeCount = driver.findElements(By.tagName("iframe")).size();
            System.out.println("Number of iframes: " + iframeCount);

            for (int i = 0; i < iframeCount; i++) {
                driver.switchTo().frame(i);
                try {
                    WebElement emailid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='login-register-common']/form[@id='commonView']//input[@id='userName']")));
                    if (emailid.isDisplayed()) {
                        emailid.sendKeys("lathagurusamy1994@gmail.com");
                        System.out.println("Entered email ID is :lathagurusamy1994@gmail.com");
                        break;
                    }
                } catch (Exception e) {
                    driver.switchTo().defaultContent();
                }
            }
        } catch (Exception e) {
            System.out.println("Email field is not visible. Please check the XPath/ID or the element's availability.");
        }
        
        // 6. Click on the Continue button
        WebElement continueButton = driver.findElement(By.id("checkUser"));
        continueButton.click();
        System.out.println("Continue Button Clicked");
        
        
        
        
        
        
        
        
        // 9. Add any further steps as needed
        
        // Close the driver

        driver.quit();
    }
}
