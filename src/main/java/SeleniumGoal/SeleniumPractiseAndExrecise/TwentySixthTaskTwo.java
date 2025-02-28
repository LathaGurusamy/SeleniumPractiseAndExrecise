package SeleniumGoal.SeleniumPractiseAndExrecise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TwentySixthTaskTwo {
    public static void main(String[] args) {
        // Set the path to your WebDriver executable (e.g., ChromeDriver)
        WebDriverManager.chromedriver().setup();

        // Initialize the WebDriver (Chrome in this case)
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to the URL
        driver.get("https://the-internet.herokuapp.com/windows");

        // Click the "Click Here" button to open a new window
        WebElement clickHereButton = driver.findElement(By.linkText("Click Here"));
        clickHereButton.click();

        // Get the handle of the current window
        String mainWindowHandle = driver.getWindowHandle();

        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();

        // Switch to the newly opened window
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Verify that the text "New Window" is present on the page
        WebElement newWindow = driver.findElement(By.tagName("h3"));
        String newWindowTitle = driver.getTitle();
        System.out.println("New Window Title: " + newWindowTitle);
        if ("New Window".equals(newWindow.getText())) {
            System.out.println("Verification successful: 'New Window' text is present.");
        } else {
            System.out.println("Verification failed: 'New Window' text is not present.");
        }

        // Close the new window
        driver.close();

        // Switch back to the original window
        driver.switchTo().window(mainWindowHandle);

        // Verify that the original window is active
        String oldWindowTitle = driver.getTitle();
        System.out.println("Old Window Title: " + oldWindowTitle);

        if ("The Internet".equals(oldWindowTitle)) {
            System.out.println("Verification successful: Original window is active.");
        } else {
            System.out.println("Verification failed: Original window is not active.");
        }

        // Close the browser instance
        driver.quit();
    }
}
