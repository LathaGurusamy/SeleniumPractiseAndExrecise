package SeleniumGoal.SeleniumPractiseAndExrecise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class TwentySixthTaskThree {
    public static void main(String[] args) {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Open the URL
        driver.get("https://the-internet.herokuapp.com/iframe");

        // Check for the read-only mode notice on the page before switching to the iframe
        WebElement readOnlyNotice = driver.findElement(By.tagName("body"));
        if (readOnlyNotice != null && readOnlyNotice.getText().contains("TinyMCE is in read-only mode")) {
            System.out.println("TinyMCE is in read-only mode: Cannot write text.");
        } else {
            // Switch to the iframe using its CSS selector
            WebElement iframe = driver.findElement(By.cssSelector("#mce_0_ifr"));
            driver.switchTo().frame(iframe);

            // Locate the "p" tag inside the iframe and write the text "Hello People"
            WebElement pTag = driver.findElement(By.cssSelector("#tinymce p"));
            pTag.clear();
            pTag.sendKeys("Hello People");
        }

        // Close the browser instance
        driver.quit();
    }
}
