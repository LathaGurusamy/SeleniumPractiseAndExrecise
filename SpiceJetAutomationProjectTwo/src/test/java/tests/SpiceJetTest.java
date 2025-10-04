package tests;

import report.Reporting;
import utils.DriverUtils;
import utils.ScreenshotUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class SpiceJetTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void setUpSuite() {
        Reporting.setUpReport();
        System.out.println("===== Test Suite Started =====");
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverUtils.getDriver(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.spicejet.com/");
        Reporting.createTest("SpiceJet Automation Test");
        System.out.println("Opened SpiceJet homepage");
    }

    @Test(priority = 1)
    public void searchOneWayFlight() {
        System.out.println(">>> Test Case: searchOneWayFlight");

        try {
            Thread.sleep(3000); // Let page load

            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());

            // Attempt to locate and click One Way selector (if present)
            List<WebElement> oneWayOption = driver.findElements(By.xpath("//div[contains(text(),'One Way')]"));
            if (oneWayOption.size() > 0) {
                WebElement oneWay = oneWayOption.get(0);
                highlight(oneWay);
                oneWay.click();
                System.out.println("Clicked One Way trip selector.");
            } else {
                System.out.println("One Way selector not found. Proceeding with default trip type.");
            }

            // Fill flight search form
            WebElement origin = driver.findElement(By.id("originCity"));
            highlight(origin);
            origin.sendKeys("Delhi");

            WebElement destination = driver.findElement(By.id("destinationCity"));
            highlight(destination);
            destination.sendKeys("Goa");

            WebElement departureDate = driver.findElement(By.id("departureDate"));
            highlight(departureDate);
            departureDate.sendKeys("06/10/2025");

            WebElement searchButton = driver.findElement(By.id("searchFlights"));
            highlight(searchButton);
            searchButton.click();
            Thread.sleep(2000);

            // Validate results
            List<WebElement> results = driver.findElements(By.id("flightResults"));
            if (results.size() > 0 && results.get(0).isDisplayed()) {
                ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/oneway_search_success.png");
                System.out.println("One-way flight search completed successfully.");
            } else {
                ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/oneway_search_no_results.png");
                System.out.println("Flight results not found. Search may have failed.");
                Assert.fail("Flight results not found.");
            }

        } catch (NoSuchElementException e) {
            System.out.println("Element not found during one-way search: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/oneway_search_error.png");
            Assert.fail("One-way search failed due to missing element.");
        } catch (Exception e) {
            System.out.println("Unexpected error in one-way search: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/oneway_search_exception.png");
            Assert.fail("Unexpected error: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void searchRoundTripFlight() throws InterruptedException {
        System.out.println(">>> Test Case: searchRoundTripFlight");

        try {
            // Check if round-trip option is present
            List<WebElement> roundTripOptions = driver.findElements(By.id("roundTrip"));
            if (roundTripOptions.size() == 0) {
                System.out.println("Round-trip option not found. Skipping test.");
                ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/roundtrip_not_found.png");
                return;
            }

            WebElement roundTripRadio = roundTripOptions.get(0);
            highlight(roundTripRadio);
            roundTripRadio.click();
            Thread.sleep(1500);

            WebElement origin = driver.findElement(By.id("originCity"));
            highlight(origin);
            origin.sendKeys("Delhi");

            WebElement destination = driver.findElement(By.id("destinationCity"));
            highlight(destination);
            destination.sendKeys("Goa");

            WebElement departureDate = driver.findElement(By.id("departureDate"));
            highlight(departureDate);
            departureDate.sendKeys("06/10/2025");

            WebElement returnDate = driver.findElement(By.id("returnDate"));
            highlight(returnDate);
            returnDate.sendKeys("10/10/2025");

            WebElement searchButton = driver.findElement(By.id("searchFlights"));
            highlight(searchButton);
            searchButton.click();
            Thread.sleep(2000);

            WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flightResults")));
            Assert.assertTrue(results.isDisplayed(), "Round-trip flight search failed.");
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/roundtrip_search.png");
            System.out.println("Round-trip flight search completed successfully.");

        } catch (NoSuchElementException e) {
            System.out.println("Element not found during round-trip search: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/roundtrip_search_error.png");
            Assert.fail("Round-trip search failed due to missing element.");
        } catch (Exception e) {
            System.out.println("Unexpected error in round-trip search: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/roundtrip_search_exception.png");
            Assert.fail("Unexpected error: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void selectFlightAndProceed() {
        System.out.println(">>> Test Case: selectFlightAndProceed");

        try {
            // Optional: wait for search results to load
            Thread.sleep(3000);

            // Log current page info
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());

            // Check if flight selection element is present
            List<WebElement> flightOptions = driver.findElements(By.id("selectFlight"));
            if (flightOptions.size() == 0) {
                System.out.println("No flight options found. Skipping test.");
                ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/selectFlight_not_found.png");
                return;
            }

            WebElement flightOption = flightOptions.get(0);
            highlight(flightOption);
            flightOption.click();
            Thread.sleep(1500);

            WebElement bookingPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bookingPage")));
            Assert.assertTrue(bookingPage.isDisplayed(), "Booking page not loaded.");
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/flight_selected.png");
            System.out.println("Flight selected and booking page opened.");

        } catch (TimeoutException e) {
            System.out.println("Booking page did not load in time.");
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/selectFlight_timeout.png");
            Assert.fail("Booking page timeout: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during flight selection: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/selectFlight_error.png");
            Assert.fail("Unexpected error: " + e.getMessage());
        }
    }


    @Test(priority = 4)
    public void fillBookingForm() {
        System.out.println(">>> Test Case: fillBookingForm");

        try {
            Thread.sleep(3000); // Let page settle

            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());

            // Check if booking form is present
            List<WebElement> passengerFields = driver.findElements(By.id("passengerName"));
            if (passengerFields.size() == 0) {
                System.out.println("Passenger name field not found. Booking form may not be loaded.");
                ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/booking_form_missing.png");
                return;
            }

            WebElement passengerName = passengerFields.get(0);
            highlight(passengerName);
            passengerName.sendKeys("John Doe");

            WebElement paymentInfo = driver.findElement(By.id("paymentInfo"));
            highlight(paymentInfo);
            paymentInfo.sendKeys("1234567890123456");

            WebElement bookButton = driver.findElement(By.id("bookFlight"));
            highlight(bookButton);
            bookButton.click();
            Thread.sleep(2000);

            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/form_filled.png");
            System.out.println("Booking form filled and submitted.");

        } catch (NoSuchElementException e) {
            System.out.println("Element not found during booking form fill: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/booking_form_error.png");
            Assert.fail("Booking form fill failed due to missing element.");
        } catch (Exception e) {
            System.out.println("Unexpected error during booking form fill: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/booking_form_exception.png");
            Assert.fail("Unexpected error: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void verifyBookingConfirmation() {
        System.out.println(">>> Test Case: verifyBookingConfirmation");

        try {
            // Optional: wait a few seconds for confirmation to render
            Thread.sleep(3000);

            // Log current page info
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());

            // Wait for confirmation message
            WebElement confirmationMessage = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("confirmationMessage"))
            );

            highlight(confirmationMessage);
            String messageText = confirmationMessage.getText();
            System.out.println("Confirmation Message: " + messageText);

            Assert.assertTrue(messageText.contains("Booking Confirmed"), "Confirmation text mismatch.");
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/booking_confirmed.png");
            System.out.println("Booking confirmation verified successfully.");

        } catch (TimeoutException e) {
            System.out.println("Confirmation message not found within timeout.");
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/booking_confirmation_missing.png");
            Assert.fail("Booking confirmation failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during confirmation check: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/booking_confirmation_error.png");
            Assert.fail("Unexpected error: " + e.getMessage());
        }
    }


    @Test(priority = 6)
    public void validateBookingPageFields() {
        System.out.println(">>> Test Case: validateBookingPageFields");

        try {
            Thread.sleep(3000); // Give time for page to load

            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());

            // Check-in field
            List<WebElement> checkInList = driver.findElements(By.id("checkIn"));
            if (checkInList.size() == 0) {
                System.out.println("Check-in field not found.");
                ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/checkin_missing.png");
            } else {
                WebElement checkIn = checkInList.get(0);
                highlight(checkIn);
                Assert.assertTrue(checkIn.isDisplayed(), "Check-in field not visible.");
                System.out.println("Check-in field validated.");
            }

            // Flight status field
            List<WebElement> flightStatusList = driver.findElements(By.id("flightStatus"));
            if (flightStatusList.size() == 0) {
                System.out.println("Flight status field not found.");
                ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/flightstatus_missing.png");
            } else {
                WebElement flightStatus = flightStatusList.get(0);
                highlight(flightStatus);
                Assert.assertTrue(flightStatus.isDisplayed(), "Flight status field not visible.");
                System.out.println("Flight status field validated.");
            }

            // Manage booking field
            List<WebElement> manageBookingList = driver.findElements(By.id("manageBooking"));
            if (manageBookingList.size() == 0) {
                System.out.println("Manage Booking field not found.");
                ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/managebooking_missing.png");
            } else {
                WebElement manageBooking = manageBookingList.get(0);
                highlight(manageBooking);
                Assert.assertTrue(manageBooking.isDisplayed(), "Manage Booking field not visible.");
                System.out.println("Manage Booking field validated.");
            }

            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/booking_fields_checked.png");
            System.out.println("Booking page field validation completed.");

        } catch (Exception e) {
            System.out.println("Unexpected error during booking page field validation: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, "./test-output/Screenshots/booking_fields_error.png");
            Assert.fail("Unexpected error: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Closing browser");
        driver.quit();
    }

    @AfterSuite
    public void tearDownSuite() {
        Reporting.flushReport();
        System.out.println("===== Test Suite Completed =====");
    }

    // 🔧 Highlight utility
    public void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void highlight(WebElement element) {
        highlight(driver, element);
    }
}
