package test;


import org.testng.annotations.Test;

import page.TopOffersElements;


public class TopOffersTest extends BaseClass {

	@Test
	public void topOffers() throws InterruptedException {
		
		extentTest = extent.createTest("topOffers").assignAuthor("Prasad").assignDevice("HP");
		login();
		extentTest.info("Login Successful");
		Thread.sleep(2000);
		click(TopOffersElements.topoffers_btn);
		extentTest.info("Clicked on top offers button");
		click(TopOffersElements.rakhi_btn);
		extentTest.info("Clicked on rakhi button");
		Thread.sleep(2000);
		click(TopOffersElements.aurum_rakhi_set);
		extentTest.info("Clicked on aurum rakhi set");
		String parent = driver.getWindowHandle();
		driver.switchTo().window(parent);
	}

}
