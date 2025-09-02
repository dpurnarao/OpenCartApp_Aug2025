package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	@BeforeClass
	public void registerSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	/* Method 1
	@Test
	public void userRegisterTest() {
		Assert.assertTrue(registerPage.userRegistration("Krishna", "dunmurry", "krishnadm@opencart.com", "987623989",
				                                         "krishna@99", "yes"));
	} */
	
	//Method 2: parameterisation for the above test method
	/*
	@DataProvider
	public Object[][] getUserRegistrationTestData() {
		return new Object[][] {
			{"Krishnaa", "dunmurry", "krishareab@opencart.com", "987623989","krishna@99", "yes"},
			{"Nanda", "dunmurry", "nandaab@opencart.com", "987623989","krishna@99", "yes"},
			{"Rama", "dunmurry", "ramaab@opencart.com", "987623989","krishna@99", "yes"},
			
		};
	} 
	

	@Test(dataProvider = "getUserRegistrationTestData")
	public void userRegisterTest(String firstname, String lastname, String email, String phonenumber, String password, String subscribe) {
		Assert.assertTrue(registerPage.userRegistration(firstname, lastname, email, phonenumber, password, subscribe));
	} */
	
	//Method 3: supplying emailId randomly from getRandomEmailId() utility
	/*	@DataProvider
		public Object[][] getUserRegistrationTestData() {
			return new Object[][] {
				{"Krishnaa", "dunmurry", "987623989","krishna@99", "yes"},
				{"Nanda", "dunmurry", "987623989","krishna@99", "yes"},
				{"Rama", "dunmurry", "987623989","krishna@99", "yes"},
				
			};
		} 
		//email input data will supplied from the randomEmail utility
		@Test(dataProvider = "getUserRegistrationTestData")
		public void userRegisterTest(String firstname, String lastname, String phonenumber, String password, String subscribe) {
			Assert.assertTrue(registerPage.userRegistration(firstname, lastname, phonenumber, password, subscribe));
		}  */
	
	//Method 4: registration data driving with excel ....from ExcelUtility	
		@DataProvider
	public Object [][] getUserRegData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
		
	}
		@Test(dataProvider = "getUserRegData")
		public void userRegisterTest(String firstname, String lastname, String phonenumber, String password, String subscribe) {
			Assert.assertTrue(registerPage.userRegistration(firstname, lastname, phonenumber, password, subscribe));
		} 
	
	

}
