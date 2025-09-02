package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("//input[@name='newsletter' and @value='1']");
	//private By subscribeYes = By.xpath("//label[@class='radio-inline'][position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline'][position()=2]/input[@type='radio']");
	
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By successMessage = By.cssSelector("div#content h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");	
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	/*
	public Boolean userRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {
		
		eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_TIMEOUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
			
		}else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		if(eleUtil.doElementGetText(successMessage).contains(AppConstants.REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;

	}  */
	
	// email data supplying from getRandomEmailId() from stringUtils()
	public Boolean userRegistration(String firstName, String lastName, String telephone,
			String password, String subscribe) {
		
		eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_TIMEOUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, StringUtils.getRandomEmailId()); // get randomemailId from stringUtils
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
			
		}else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
				
		// experienced : org.openqa.selenium.StaleElementReferenceException: stale element reference: stale element not found
		// then applied the wait for few seconds to wait for the page response
		//eleUtil.waitForElementsVisible(successMessage, AppConstants.MEDIUM_DEFAULT_TIMEOUT);	
		// if(eleUtil.doElementGetText(successMessage).contains(AppConstants.REGISTER_SUCCESS_MESSAGE)) {
		//the above statements are merged like below
		
		if(eleUtil.waitForElementVisible(successMessage, AppConstants.MEDIUM_DEFAULT_TIMEOUT).getText()
				.contains(AppConstants.REGISTER_SUCCESS_MESSAGE)) {
		
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;

	} 

}
