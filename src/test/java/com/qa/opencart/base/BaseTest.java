package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.utils.LogUtil;

import io.qameta.allure.Description;

@Listeners(ChainTestListener.class)
public class BaseTest {
	//purpose is pre and post conditions for the test cases or test classes
	WebDriver driver;
	
	DriverFactory df; // reference of the DriverFactory class 
	
	protected Properties prop;
	
	//LoginPage loginPage;//defaulit access modifier
	//A member with no access modifier is only accessible within classes in the same package. 
	//Out side of the package not accessible	
	
	protected LoginPage loginPage; // protected variables can be accessed within the class and outside of the package
	
	protected AccountsPage accPage; // reference of the AccountsPage
	
	protected SearchResultsPage searchResultsPage;
	
	protected ProductInfoPage productInfoPage;
	
	protected RegisterPage registerPage;
	
	public static final org.apache.logging.log4j.Logger log = LogManager.getLogger(BaseTest.class);
	
/*	@BeforeTest
	public void setUp() {
		df = new DriverFactory(); // object of the DriverFactory class
		
		prop = df.initProp(); // prop is a holding parameter
		
		//driver = df.initDriver("chrome"); // session id is same as driverFactory
		driver = df.initDriver(prop); // call by reference ... login page
		
		loginPage = new LoginPage(driver); //login page seession is also same
		
	} */
	
	//browser setUp() that the browser selected based on the test in the testng.xml -- for cross browser testing
/*	@Parameters({"browser"})  //browser value is passed from testng.xml
	@BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory(); // object of the DriverFactory class
		
		prop = df.initProp(); // prop is a holding parameter
		
		if(browserName != null) { //browserName is passed from testng.xml
			prop.setProperty("browser", browserName);
		}
		
		//driver = df.initDriver("chrome"); // session id is same as driverFactory
		driver = df.initDriver(prop); // call by reference ... login page
		
		loginPage = new LoginPage(driver); //login page seession is also same
		
	} */
	
	// browser setu that supports parallel testing 
	@Description("initialise the driver and properties")
	@Parameters({"browser", "browserversion", "testname"})  //browser value is passed from testng.xml
	@BeforeTest
	public void setUp(String browserName, String browserversion, String testname) {
		df = new DriverFactory(); // object of the DriverFactory class
		
		prop = df.initProp(); // prop is a holding parameter
		
		if(browserName != null) { //browserName is passed from testng.xml
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserversion);
			prop.setProperty("testname", testname);
		}
		
		//driver = df.initDriver("chrome"); // session id is same as driverFactory
		driver = df.initDriver(prop); // call by reference ... login page
		
		loginPage = new LoginPage(driver); //login page seession is also same
		
	}
	
//	@BeforeMethod
//	public void beforeMethod(ITestContext result) {
//		LogUtil.info("------Starting test case ----" + result.getName());
//	}
	
	@AfterMethod  // this method will be running after each @Test method
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) { // only for failure test cases
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
			
		}
		
		//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png"); take screen shot for pass and fail tests
		
	}
	@Description("Closing the browser...")
	@AfterTest
    public void tearDown() {
	driver.quit();	// driver is closed
	log.info("----closing the browser-----");
	}

}
