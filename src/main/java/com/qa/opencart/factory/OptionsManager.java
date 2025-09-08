package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	//optionsManager to select the brower options to run in headless mode and in incognito mode
	// if it is static ... will be a problem to run in parallel mode
	
	private Properties prop;
	//Proerties is java util class: The Properties can be saved to a streamor loaded from a stream.
	//Each key and its corresponding value inthe property list is a string. 
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	//chrome options
	public ChromeOptions getChromeOptions() {
		ChromeOptions co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("--- Chrome browser running in headless mode----------");
			co.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("--- Chrome browser running in INCOGNITO mode----------");
			co.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
		}
		return co;
	}
	//Firefox browser options
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("--- Firefox browser running in headless mode----------");
			fo.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("--- Firefox browser running in INCOGNITO mode----------");
			fo.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
		}
		return fo;
	}
	
	// Edge browser options	
		public EdgeOptions getEdgeOptions() {
			EdgeOptions eo = new EdgeOptions();
			if(Boolean.parseBoolean(prop.getProperty("headless"))) {
				System.out.println("--- Edge browser running in headless mode----------");
				eo.addArguments("--headless");
			}
			
			if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
				System.out.println("--- Edge browser running in INCOGNITO mode----------");
				eo.addArguments("--inprivate");
			}
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				eo.setCapability("browserName", "edge");
			}
			return eo;
		}
	
	

}
