package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	//declare constants ..only application constants  ... not for test data
	
	// enum can be difined
	
	public static String LOGIN_PAGE_TITLE = "Account Login";
	public static String HOME_PAGE_TITLE = "My Account";
	public static String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static String HOME_PAGE_FRACTION_URL = "route=account/account";
	
	public static final int DEFAULT_TIMEOUT = 5;
	public static final int MEDIUM_DEFAULT_TIMEOUT = 10;
	public static final int LONG_DEFAULT_TIMEOUT = 15;
	
	//list of expected headers in Accounts page. ArrayList
	public static List<String> expectedAccPageHEadersList = List.of("My Account",
			                                                         "My Orders",
			                                                         "My Affiliate Account",
			                                                         "Newsletter");
	
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created";
	
	// file path to read excel test data
	//@SuppressWarnings("unused")
	//private static final String TEST_DATA_SHEET_PATH = "Test123";
	
	//****************** excel sheet names for ExcelUtility*********
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String PRODUCT_SHEET_NAME = "product";
	
	
}
