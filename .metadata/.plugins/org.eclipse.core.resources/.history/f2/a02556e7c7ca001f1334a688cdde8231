package SeleniumGoal.SeleniumPractiseAndExrecise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;


import io.github.bonigarcia.wdm.WebDriverManager;
public class TwentySixthTaskOne {

	public static void main(String[] args)
	{
		
		        // Set the path to your WebDriver executable (e.g., ChromeDriver)
                  WebDriverManager.chromedriver().setup();

		        // Initialize the WebDriver (Chrome in this case)
		        WebDriver driver = new ChromeDriver();

		            // Navigate to the URL
		            driver.get("https://the-internet.herokuapp.com/iframe");

		            // Switch to the iframe using its CSS selector or XPath
		            WebElement iframe = driver.findElement(By.cssSelector("#mce_0_ifr"));
		            driver.switchTo().frame(iframe);

		            // Set the content of the TinyMCE editor using JavaScript
		            JavascriptExecutor js = (JavascriptExecutor) driver;
		            js.executeScript("tinymce.activeEditor.setContent('<p>Hello People</p>');");
		        
		        }
		    }
