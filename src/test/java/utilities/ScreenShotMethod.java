package utilities;

import java.io.File;
import java.io.IOException;

import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import test.BaseClass;

public class ScreenShotMethod extends BaseClass {

	public static void getScreenshot() throws IOException {
		Date currentdate = new Date();
		String screenshotfilename = currentdate.toString().replace(" ", "-").replace(":", "-");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(".//ScreenShots//" + screenshotfilename + ".png"));
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
	
	public void screen() {
//		String timeStamp;
//		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		File destinationfilepath = new File(".//ReportScreenShots//"+timeStamp+".png");
//		String absolutepathlocation = destinationfilepath.getAbsolutePath();
//		
//		FileUtils.copyFile(srcfile, destinationfilepath);
//		return absolutepathlocation;
	}

}
