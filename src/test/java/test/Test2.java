package test;

import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Test2 extends BaseClass {


	@Test
	public void test1() {
//		ExtentTestManager.getTest().log(Status.INFO, "Login NotSuccessful");
//		ExtentTestManager.getTest().info("Testing2");
		extentTest = extent.createTest("Test1").assignAuthor("dha").assignDevice("Laptop");
		extentTest.info("info message");
		
		extentTest = extent.createTest("Test 2");
		extentTest.info("pass message");
		
	}

}
