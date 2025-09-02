package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

// import com.qa.opencart.constants.AppConstants;
import static com.qa.opencart.constants.AppConstants.*; // this packge give access to AppConstants.java
@Feature("F50: Open cart Login Feature")
@Epic("Epic 100: design pages for open cart application-- login pages")
@Story("US 100: implement login page for open cart applicaiton")
public class LoginPageTest extends BaseTest {	
	// test classes parent is BaseTest.
	//preconditions and post conditions for the tests impleted in the BaseTest. LoginPageTest inherits the properties of 
	//its parent class is BaseTest.
	//to execute the @Test browser will be opened only once and once instance
	
	@Description("Verify opencard login page title")
	@Severity(SeverityLevel.MINOR)
	@Owner("Purna")
	@Test(description = " loging page tile test verifes the title of the page")
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();   // getLoginPageTitle() is implemented in LoginPage. and the loginPage object is inherited from BaseTest
		//Assert.assertEquals(actualTitle, "Account Login"); hardcoded value
		
		//Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
		//the above line of code can be tweaked as the AppConstants are imported
		
		//ChainTestreport listener to log the element details
		ChainTestListener.log("Login page title verification in opencart application... " + actualTitle);
		Assert.assertEquals(actualTitle, LOGIN_PAGE_TITLE);
	}
	
	@Description("Verify opencard login pageURL title")
	@Severity(SeverityLevel.MINOR)
	@Owner("Purna")
	@Test
	public void LoginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		//Assert.assertTrue(actualURL.contains("?route=account/login")); hard coded value
		
		
		//Assert.assertTrue(actualURL.contains(AppConstants.HOME_PAGE_FRACTION_URL)); //AppConstants
		//the above line of code can be tweaked like below as the AppConstants are imported
		Assert.assertTrue(actualURL.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Description("Verify opencard login page PEW link")
	@Severity(SeverityLevel.MINOR)
	@Owner("Purna")
	@Test
	public void forgotPwdLinkExistTest() {
		
		// Assert.assertTrue(loginPage.isForgotPasswordLinkExist());		
		Boolean forgotPwdLink = loginPage.isForgotPasswordLinkExist();
		
		Assert.assertTrue(forgotPwdLink);
	}
	
	@Description("Verify opencard login page test")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Purna")
	@Test(priority = Short.MAX_VALUE)
	public void doLoginTest() throws InterruptedException {
		//String actualAccountPageTitle = loginPage.doLogin("march2024@open.com", "Selenium@12345"); hard coded values
		
		//String actualAccountPageTitle = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		//Assert.assertEquals(actualAccountPageTitle, "My Account"); hardcoded value		
		
		//Assert.assertEquals(actualAccountPageTitle, AppConstants.HOME_PAGE_TITLE);
		//the above line of code can be tweaked like below as the AppConstants are imported
		//Assert.assertEquals(actualAccountPageTitle, HOME_PAGE_TITLE);
		
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), HOME_PAGE_TITLE);
	}
	
	// test case disabled... it will not be executed
	@Test(enabled = false, description = "test case disabled... it will not be executed")
	public void forgotPwdTest() {
		System.out.println("Forgot password test....");
	}
	

}
