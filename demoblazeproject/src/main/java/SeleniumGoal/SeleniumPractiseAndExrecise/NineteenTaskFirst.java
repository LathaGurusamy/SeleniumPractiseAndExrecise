package SeleniumGoal.SeleniumPractiseAndExrecise;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NineteenTaskFirst {
		    public static void main(String[] args) {
		        WebDriverManager.chromedriver().setup();

		        WebDriver driver = new FirefoxDriver();

		        driver.manage().window().maximize();

		        driver.get("http://google.com");

		        System.out.println("Current URL: " + driver.getCurrentUrl());
		        
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait

		        driver.navigate().refresh();
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait


		        driver.quit();
		    }
		}