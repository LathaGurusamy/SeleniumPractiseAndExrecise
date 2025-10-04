package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting 
{	    private static ExtentReports extent;
	    private static ExtentTest test;

	    public static void setUpReport() {
	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./test-output/reports/extent.html");
	        sparkReporter.config().setTheme(Theme.STANDARD);
	        sparkReporter.config().setDocumentTitle("SpiceJet Automation Test Report");
	        sparkReporter.config().setReportName("SpiceJet Test Results");

	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	    }

	    public static ExtentTest createTest(String testName) {
	        test = extent.createTest(testName);
	        return test;
	    }

	    public static void flushReport() {
	        extent.flush();
	    }
	

}
