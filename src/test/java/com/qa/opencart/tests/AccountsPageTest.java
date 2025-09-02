package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.qa.opencart.constants.AppConstants.*; // import AppConstants

import java.util.List;

import com.qa.opencart.base.BaseTest;

public class AccountsPageTest extends BaseTest {
	/* when the AccountsPageTest is called, BaseTests @BeforTest method is executed first.
	 * @BeforeTest then the @BeforeClass will be executed
	 *  BeforeTest ---> @BeforeClass
	 */
	
	@BeforeClass
	public void accPageSetUp() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(), HOME_PAGE_TITLE);
	}
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccPageURL().contains(HOME_PAGE_FRACTION_URL));
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String>actualHeadersList = accPage.getAccountPageHeaders();
		
		Assert.assertEquals(actualHeadersList, expectedAccPageHEadersList);
	}
	
	

}
