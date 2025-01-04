package SeleniumGoal.SeleniumPractiseAndExrecise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.Scanner;

public class TwentyTastOne {
	public static void main(String[] args) {
		// Set up the ChromeDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();

		// Initialize WebDriver
		WebDriver driver = new ChromeDriver();

		// Open Snapdeal website
		driver.get("https://www.snapdeal.com");

		 // Step 3: Move the cursor to the Sign In button
        Actions actions = new Actions(driver);
        WebElement signInButton = driver.findElement(By.xpath(("//div[@class='accountInner']//span)[1]")));
        actions.moveToElement(signInButton).perform();

        // Step 4: Click on the Sign In button
        WebElement loginButton = driver.findElement(By.linkText("login"));
        loginButton.click();

		
	}
}
