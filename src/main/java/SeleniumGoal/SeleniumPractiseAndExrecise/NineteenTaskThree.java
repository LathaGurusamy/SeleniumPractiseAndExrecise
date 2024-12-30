package SeleniumGoal.SeleniumPractiseAndExrecise;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

		public class NineteenTaskThree {
		    public static void main(String[] args) {
		        WebDriverManager.chromedriver().setup();

		        WebDriver driver = new ChromeDriver();

		        driver.get("https://www.wikipedia.org/");

		        driver.manage().window().maximize();
		        
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait


		        WebElement searchBar = driver.findElement(By.id("searchInput"));
		        searchBar.sendKeys("Artificial Intelligence");
		        
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait


		        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
		        searchButton.click();
		        
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait


		        WebElement historySection = driver.findElement(By.xpath("//span[text()='History']"));
		        historySection.click();
		        
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait


		        System.out.println("Section Title: " + historySection.getText());

		        driver.quit();
		    }
		}


