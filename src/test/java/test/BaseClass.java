package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadConfig;

public class BaseClass {

	public static WebDriver driver;
	public static WebDriverWait waitE;
	public static Wait<WebDriver> wait;

	ReadConfig readconfig = new ReadConfig();
	public String baseURL = readconfig.getApplicationURL();
	public String Email = readconfig.getEmail();
	public String Password = readconfig.getPassword();
	public String Search_item = readconfig.getSearch_item();

	public By email = By.xpath("(//input[@type='text'])[2]");
	public By password = By.xpath("//input[@type='password']");
	public By login_btn = By.xpath("(//button[@type='submit'])[2]");
	public By profile_dropdown = By.xpath("//div[@class='exehdJ'][contains(.,'Dhanoosh')]");
	public By logout = By.xpath("(//div[contains(.,'Logout')])[12]");
	
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest extentTest;

	@Parameters("browser")
	@BeforeClass
	public void setUp(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
	}

	@AfterClass
	public void tearDown() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(profile_dropdown)).perform();
		extentTest.info("Hovered on profile dropdown");
		click(logout);
		extentTest.info("Clicked on logout button");
		driver.quit();
	}

	public void login() {

		sendKeys(email, Email);
		extentTest.info("Entered the email: " + Email);
		sendKeys(password, Password);
		extentTest.info("Entered the password: " + Password);
		click(login_btn);
		extentTest.info("Clicked on login button");
	}

	public static void click(By element) {
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(3))
				.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
		if (wait.until(ExpectedConditions.visibilityOfElementLocated((element))).isDisplayed()) {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} else {
			Assert.assertTrue(false);
		}
	}

	// Send keys method with by element
	public static void sendKeys(By element, String text) {
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(3))
				.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
		if (wait.until(ExpectedConditions.visibilityOfElementLocated((element))).isDisplayed()) {
			wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
		} else {
			Assert.assertTrue(false);
		}
	}
}
