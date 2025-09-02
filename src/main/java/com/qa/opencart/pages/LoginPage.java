package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*; // this packge give access to AppConstants.java

public class LoginPage {
	
	private WebDriver driver;
	
	private ElementUtil eleUtil; // reference of the ElementUtil.... value is null
	//where is the driver initialised. webDriver is not initialised. chromedriver, 
	//cross browser logic is implemented in Driver Factory class
	//Page objects are a classic example of encapsulation - they hide the details of the UI structure 
	//and widgetry from other components 
	
	//1.Private By locators OR Object repository
	
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");
	
	
	//2.public page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
		eleUtil = new ElementUtil(driver);
	}
	
	
	//3.public page actions / methods
	//Assertions will be checked in the test class. conditions will be checked in the testNg class or in the test class
	@Step("getting login page title")
	public String getLoginPageTitle() {
		/*String title = driver.getTitle();
		System.out.println("Login page title :   " + title);
		return title; */
		
		//using generic method
		//String title =  eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
		//after importing AppConstants the above line of code can be tweaked like below:
		String title =  eleUtil.waitForTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Login page title :   " + title);
		return title;
	
	}
	
	public String getLoginPageURL() {
		
	/*	String url = driver.getCurrentUrl();
		System.out.println("Login page URL...    " + url);
		return url; */
		
		//re-usable method from ElementUtil
		//String url = eleUtil.waitForURLContains(AppConstants.HOME_PAGE_FRACTION_URL, AppConstants.DEFAULT_TIMEOUT);
		//after importing AppConstants the above line of code can be tweaked like below:
		
		
		String url = eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Login page URL...    " + url);
		return url; 
		
	}
	
	//private variables(forgotPwdLink) used in public methods: encapsulation is achieved
	//verifying forgot password link is present. not clicking on it
	public boolean isForgotPasswordLinkExist() {
		// return driver.findElement(forgotPwdLink).isDisplayed();
		
		return eleUtil.isElementDisplayed(forgotPwdLink);
		
	}
	
	// every page method should return a value... so that these actions can be validated in the test class(testng assertions)
	@Step("login with valid username: {0} and pwd: {1}")
	public AccountsPage doLogin(String username, String pwd) throws InterruptedException {
	/*	System.out.println(" User credentials :   " + username + " : " + pwd);
		//driver.findElement(email).clear();
		driver.findElement(email).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		
		Thread.sleep(5000);
		
		String title =  driver.getTitle();
		System.out.println(" Title of the account page is :  " + title );
		
		return title; */
		
		System.out.println(" User credentials :   " + username + " : " + pwd);		
		
		//eleUtil.waitForElementVisible(email, AppConstants.DEFAULT_TIMEOUT).sendKeys(username);
		//after importing AppConstants the above line of code can be tweaked like below:
		eleUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(username);
		
		
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
//		//String title = eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
//		String title = eleUtil.waitForTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
//		
//		System.out.println("Accounts page title :  " + title);
//		return title;
		
		//this method returning the next landing page object i.e AccountsPage
		return new AccountsPage(driver); // return type is AccountsPage object
		//inside heap memory
		
		
	}
	
	//verify other elements in the login page window. New Customer, logo, links, menu etc...
	@Step("Navigating to the registration page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	

}
