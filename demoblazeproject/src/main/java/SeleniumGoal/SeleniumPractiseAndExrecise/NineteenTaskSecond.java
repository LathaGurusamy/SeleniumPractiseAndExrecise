package SeleniumGoal.SeleniumPractiseAndExrecise;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

		public class NineteenTaskSecond {
		    public static void main(String[] args) {
		    	WebDriverManager.chromedriver().setup();


		        WebDriver driver = new ChromeDriver();


		        driver.get("https://www.demoblaze.com/");

		        driver.manage().window().maximize();
		        
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait


		        String pageTitle = driver.getTitle();

		        if (pageTitle.equals("STORE")) {
		            System.out.println("Page landed on correct website");
		        } else {
		            System.out.println("Page not landed on correct website");
		        }

		        driver.quit();
		    }
		}