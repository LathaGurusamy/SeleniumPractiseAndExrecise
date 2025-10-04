package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverUtils {

    public static WebDriver getDriver(String browser) {
        // Debug log to inspect raw input
        System.out.println("Raw browser input: [" + browser + "]");

        if (browser == null || browser.trim().isEmpty()) {
            throw new IllegalArgumentException("Browser parameter is missing.");
        }

        // Clean up input: remove quotes, trim spaces, normalize case
        browser = browser.replace("\"", "").trim().toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
