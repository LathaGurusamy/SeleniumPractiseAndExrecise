package SeleniumGoal.SeleniumPractiseAndExrecise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class TwentySixthTaskThree {
    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Open the URL
        driver.get("http://the-internet.herokuapp.com/nested_frames");
        Thread.sleep(2000); // Pause for 2 seconds

        // Switch to the top frame
        driver.switchTo().frame("frame-top");
        Thread.sleep(2000); // Pause for 2 seconds

        // Verify there are three frames in the top frame
        int frameCount = driver.findElements(By.tagName("frame")).size();
        if (frameCount == 3) {
            System.out.println("Verification successful: There are three frames in the top frame.");
        } else {
            System.out.println("Verification failed: Expected three frames but found " + frameCount + ".");
        }
        Thread.sleep(2000); // Pause for 2 seconds

        // Switch to the left frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-left']")));
        Thread.sleep(2000); // Pause for 2 seconds

        // Verify that the left frame has the text "LEFT"
        WebElement leftFrameText = driver.findElement(By.tagName("body"));
        if ("LEFT".equals(leftFrameText.getText())) {
            System.out.println("Verification successful: Left frame has the text 'LEFT'.");
        } else {
            System.out.println("Verification failed: Left frame text is not 'LEFT'.");
        }
        Thread.sleep(2000); // Pause for 2 seconds

        // Switch back to the top frame
        driver.switchTo().parentFrame();
        Thread.sleep(2000); // Pause for 2 seconds

        // Switch to the middle frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-middle']")));
        Thread.sleep(2000); // Pause for 2 seconds

        // Verify that the middle frame has the text "MIDDLE"
        WebElement middleFrameText = driver.findElement(By.id("content"));
        if ("MIDDLE".equals(middleFrameText.getText())) {
            System.out.println("Verification successful: Middle frame has the text 'MIDDLE'.");
        } else {
            System.out.println("Verification failed: Middle frame text is not 'MIDDLE'.");
        }
        Thread.sleep(2000); // Pause for 2 seconds

        // Switch back to the top frame
        driver.switchTo().parentFrame();
        Thread.sleep(2000); // Pause for 2 seconds

        // Switch to the right frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-right']")));
        Thread.sleep(2000); // Pause for 2 seconds

        // Verify that the right frame has the text "RIGHT"
        WebElement rightFrameText = driver.findElement(By.tagName("body"));
        if ("RIGHT".equals(rightFrameText.getText())) {
            System.out.println("Verification successful: Right frame has the text 'RIGHT'.");
        } else {
            System.out.println("Verification failed: Right frame text is not 'RIGHT'.");
        }
        Thread.sleep(2000); // Pause for 2 seconds

        // Switch back to the top frame
        driver.switchTo().parentFrame();
        Thread.sleep(2000); // Pause for 2 seconds

        // Switch to the bottom frame
        driver.switchTo().defaultContent(); // Switch back to the main document before switching to the bottom frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-bottom']")));
        Thread.sleep(2000); // Pause for 2 seconds

        // Verify that the bottom frame has the text "BOTTOM"
        WebElement bottomFrameText = driver.findElement(By.tagName("body"));
        if ("BOTTOM".equals(bottomFrameText.getText())) {
            System.out.println("Verification successful: Bottom frame has the text 'BOTTOM'.");
        } else {
            System.out.println("Verification failed: Bottom frame text is not 'BOTTOM'.");
        }
        Thread.sleep(2000); // Pause for 2 seconds

        // Verify that the page title is "The Internet"
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
        if ("The Internet".equals(pageTitle)) {
            System.out.println("Verification successful: Page title is 'The Internet'.");
        } else {
            System.out.println("Verification failed: Page title is not 'The Internet'.");
        }

        // Close the browser instance
        driver.quit();
    }
}
