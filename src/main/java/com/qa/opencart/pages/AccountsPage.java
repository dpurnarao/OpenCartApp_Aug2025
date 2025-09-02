package com.qa.opencart.pages;

// import AppConstants
import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;

import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By header = By.cssSelector("div#content > h2"); // > is for direct child elements
	private final By search = By.name("search");
	//private final By search = By.cssSelector("div#search");
	private final By searchIcon = By.cssSelector("div#search button");
	
	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(AccountsPage.class);
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	public String getAccPageTitle() {
		
		String title = eleUtil.waitForTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		//System.out.println("Home page title is :   " + title);
		log.info("Home page title:  " + title);
		return title;
		
	}
	
	public String getAccPageURL() {
		String url = eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		//System.out.println("Home page url is :  " + url);
		log.info("Home page url is :  " + url);
		
		return url;
	}
	
	public List<String> getAccountPageHeaders() {
		List<WebElement> headersList = eleUtil.getElements(header);
		List<String> headerValList = new ArrayList<String>();
		
		for(WebElement e: headersList) {
			String text = e.getText();
			headerValList.add(text);
			
		}
		//System.out.println("Account page headers list : " + headerValList);
		log.info("Account page headers list : " + headerValList);
		return headerValList;
		
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		//System.out.println("Search key :  " + searchKey);
		log.info("Search key..." + searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		
		return new SearchResultsPage(driver); // next landing page
		
	}
	

}
