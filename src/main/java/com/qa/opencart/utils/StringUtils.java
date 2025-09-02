package com.qa.opencart.utils;

public class StringUtils {
	//generates a random email id
	
	public static String getRandomEmailId() {
		
		return "testautomation" + System.currentTimeMillis() + "@opencart.com";
	}

}
