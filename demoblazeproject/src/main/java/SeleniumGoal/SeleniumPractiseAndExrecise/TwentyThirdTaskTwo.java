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
		        WebDriverManager.chromedriver().setup();

		        WebDriver driver = new ChromeDriver();

		        driver.get("https://jqueryui.com/droppable/");

		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait

		        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        WebElement sourceElement = driver.findElement(By.id("draggable"));
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        WebElement targetElement = driver.findElement(By.id("droppable"));
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        Actions action = new Actions(driver);
		        action.dragAndDrop(sourceElement, targetElement).perform();
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        String color = targetElement.getCssValue("background-color");
		        System.out.println("Color after drop: " + color);
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        String text = targetElement.getText();
		        System.out.println("Text after drop: " + text);
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


		        if (text.equals("Dropped!")) {
		            System.out.println("Drag and drop operation was successful!");
		        } else {
		            System.out.println("Drag and drop operation failed!");
		        }

		        driver.quit();
		    }
		}

