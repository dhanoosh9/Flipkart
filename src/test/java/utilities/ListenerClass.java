package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import test.BaseClass;

public class ListenerClass extends BaseClass implements ITestListener {

	public void onStart(ITestContext context) {
		System.out.println("Starting...");
		ExtentSparkReporter spark = new ExtentSparkReporter("Reports/index.html");
		spark.config().setDocumentTitle("My Report");
		spark.config().setReportName("Flipkart Report");
		spark.config().setTheme(Theme.DARK);
		extent.attachReporter(spark);
		System.out.println("Report created -----------");

	}

	public void onTestStart(ITestResult result) {
		System.out.println("Test is starting");

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test success");
		extentTest.pass(result.getName());

	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed - capture screenshot");
		extentTest.fail(result.getName());
		try {
			extentTest.addScreenCaptureFromPath(capturescreenshot());

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test is skipped");
		extentTest.skip(result.getName());
		// ExtentTestManager.getTest().skip(result.getName());
	}

//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		System.out.println("Failed but has success?");
//	}
//
//	public void onTestFailedWithTimeout(ITestResult result) {
//		System.out.println("Test failed due to timeout - retry");
//	}

	public void onFinish(ITestContext context) {
		System.out.println("Ending...");
		extent.flush();

	}
	public static String capturescreenshot() throws IOException {
		Date currentdate = new Date();
		String time = currentdate.toString().replace(" ", "-").replace(":", "-");
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destinationfilepath = new File(".//ReportScreenShots//" + time + ".png");
		String absolutepath = destinationfilepath.getAbsolutePath();
		FileUtils.copyFile(srcfile, destinationfilepath);
		return absolutepath;
	}
}
