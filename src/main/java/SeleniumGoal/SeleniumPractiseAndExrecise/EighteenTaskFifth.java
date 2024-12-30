package SeleniumGoal.SeleniumPractiseAndExrecise;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class EighteenTaskFifth {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium Browser Driver");
        searchBox.submit(); // Submit the search

        System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
    }
}

