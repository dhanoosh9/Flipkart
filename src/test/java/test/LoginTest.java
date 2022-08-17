package test;


import org.testng.annotations.Test;

import page.LoginElements;

public class LoginTest extends BaseClass {
	@Test
	public void loginTest() throws Exception {
		extentTest = extent.createTest("Login Test").assignAuthor("dhanoosh").assignDevice("Dell Laptop");
		login();
		extentTest.info("Login Successful");
		Thread.sleep(1000);
		sendKeys(LoginElements.search_box, Search_item);
		extentTest.info("Entered the search item: " + Search_item);
		click(LoginElements.search_btn);
		extentTest.info("Clicked on search button");

	}

}
