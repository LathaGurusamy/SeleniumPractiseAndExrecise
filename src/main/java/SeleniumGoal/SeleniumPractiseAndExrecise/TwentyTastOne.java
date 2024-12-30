package SeleniumGoal.SeleniumPractiseAndExrecise;
		

		import io.github.bonigarcia.wdm.WebDriverManager;
		import org.openqa.selenium.By;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.interactions.Actions;
		import java.time.Duration;

		public class TwentyTastOne {
		    public static void main(String[] args) {
		        // Set up the ChromeDriver using WebDriverManager
		        WebDriverManager.chromedriver().setup();

		        // Initialize WebDriver
		        WebDriver driver = new ChromeDriver();

		        // Open Snapdeal website
		        driver.get("https://www.snapdeal.com");

		        // Maximize the window
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait

		        // Move cursor to the Sign In button and hold it
		        WebElement signInBtn = driver.findElement(By.className("accountUser"));
		        Actions action = new Actions(driver);
		        action.moveToElement(signInBtn).perform();

		        // Click on the Sign In button
		        WebElement loginBtn = driver.findElement(By.xpath("//span[text()='Sign In']"));
		        loginBtn.click();

		        // Switch to the login iframe
		        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='iframeLogin']")));

		        // Enter a valid Email Id
		        WebElement emailField = driver.findElement(By.id("userName"));
		        emailField.sendKeys("your-email@example.com");

		        // Click on the Continue button
		        WebElement continueBtn = driver.findElement(By.id("checkUser"));
		        continueBtn.click();

		        // Wait for the password field to be visible and enter the valid password
		        WebElement passwordField = driver.findElement(By.id("j_password_login_uc"));
		        passwordField.sendKeys("your-password");

		        // Click on the Login button
		        WebElement loginSubmitBtn = driver.findElement(By.id("submitLoginUC"));
		        loginSubmitBtn.click();

		        // Verify the user is logged in successfully and print the verification message
		        WebElement accountName = driver.findElement(By.className("accountUserName"));
		        if (accountName.isDisplayed()) {
		            System.out.println("Login successful. User logged in as: " + accountName.getText());
		        } else {
		            System.out.println("Login failed.");
		        }

		        // Close the browser
		        driver.quit();
		    }
		}
