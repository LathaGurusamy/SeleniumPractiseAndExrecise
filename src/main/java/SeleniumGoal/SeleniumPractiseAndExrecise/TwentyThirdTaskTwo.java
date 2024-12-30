package SeleniumGoal.SeleniumPractiseAndExrecise;




		import io.github.bonigarcia.wdm.WebDriverManager;
		import org.openqa.selenium.By;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.interactions.Actions;
		import java.time.Duration;

		public class TwentyThirdTaskTwo {
		    public static void main(String[] args) {
		        // Set up the ChromeDriver using WebDriverManager
		        WebDriverManager.chromedriver().setup();

		        // Initialize WebDriver
		        WebDriver driver = new ChromeDriver();

		        // Open jQueryUI droppable demo site
		        driver.get("https://jqueryui.com/droppable/");

		        // Maximize the window
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait

		        // Switch to the iframe that contains the droppable elements
		        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        // Find the source element
		        WebElement sourceElement = driver.findElement(By.id("draggable"));
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        // Find the target element
		        WebElement targetElement = driver.findElement(By.id("droppable"));
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        // Perform drag and drop operation
		        Actions action = new Actions(driver);
		        action.dragAndDrop(sourceElement, targetElement).perform();
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        // Verify the color change after drop
		        String color = targetElement.getCssValue("background-color");
		        System.out.println("Color after drop: " + color);
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        // Verify the text change after drop
		        String text = targetElement.getText();
		        System.out.println("Text after drop: " + text);
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        if (text.equals("Dropped!")) {
		            System.out.println("Drag and drop operation was successful!");
		        } else {
		            System.out.println("Drag and drop operation failed!");
		        }

		        // Close the browser
		        driver.quit();
		    }
		}

