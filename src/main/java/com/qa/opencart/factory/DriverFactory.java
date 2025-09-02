package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;
//import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;



public class DriverFactory {
	// initialising and impleting webdriver and cross browser logic
	WebDriver driver;
	
	Properties prop; // Properties class coming from java
	OptionsManager optionsManager; // reference of OptionsManager class
	
	//thread local. create local copy of every thread.deadlock situation is resolved. race condition 
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static String highlight; //to highlight the WebElements
	
	public static final org.apache.logging.log4j.Logger log = LogManager.getLogger(DriverFactory.class);
		// warning, info, error or fatal messages
	
	//using threadlocal
	public WebDriver initDriver(Properties prop) {
		log.info("properties:  " + prop);
		//getProperty() is coming from Properties class
		String browserName = prop.getProperty("browser"); // browser value is coming from config.properties
		log.info("Browser name is : " + browserName);
		//System.out.println("Browser name is :  " + browserName);
		optionsManager = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight"); // highlight=true/false is declared in config.properties
	
	switch(browserName.toLowerCase().trim()){
	case "chrome":
		tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		//driver = new ChromeDriver(optionsManager.getChromeOptions());
		break;
	case "edge":		
		tlDriver.set(new EdgeDriver(optionsManager.EdgeOptions()));
		break;
	case "firefox":
		tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		break;
	case "safari":
		tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		tlDriver.set(new SafariDriver());
		
	default:
		log.error("Please pass the valid browser..." + browserName);
		//System.out.println("please pass the correct browser...   " + browserName);
		// throw is  a custom exception. user can display own exception messages
		throw new BrowserException("===== INVALID BROWSER=======");
		
	} 

	getDriver().get(prop.getProperty("url"));
	
	getDriver().manage().window().maximize();
	//driver.manage().deleteAllCookies();
	return getDriver();
} 
	
	/**
	 * getDriver: get the local thread copy of the driver
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	
	/**
	 * This method is used to initialise the browser driver based on the chosen driver
	 * @param browserName
	 */
	
	//public WebDriver initDriver(String browserName) { hard coded
	//without headless
	/*	public WebDriver initDriver(Properties prop) {
			//getProperty() is coming from Properties class
			String browserName = prop.getProperty("browser"); // browser value is coming from config.properties
		
		switch(browserName.toLowerCase().trim()){
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			
		default:
			System.out.println("please pass the correct browser...   " + browserName);
			// throw is  a custom exception. user can display own exception messages
			throw new BrowserException("===== INVALID BROWSER=======");
			
		} 
		//driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login"); // hard coded
//		String url = prop.getProperty("url");
//		driver.get(url);
		
		driver.get(prop.getProperty("url"));
		
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		return driver;
		
		
		
	} */
	/**
	 * This method initialises the driver based on the chosen browser
	 * and also browser is chosen is invoked headless
	 * highlights WebElement based on the config.properties file
	 * 
	 */
	
/* public WebDriver initDriver(Properties prop) {
		//getProperty() is coming from Properties class
		String browserName = prop.getProperty("browser"); // browser value is coming from config.properties
		System.out.println("Browser name is :  " + browserName);
		optionsManager = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight"); // highlight=true/false is declared in config.properties
	
	switch(browserName.toLowerCase().trim()){
	case "chrome":
		driver = new ChromeDriver(optionsManager.getChromeOptions());
		break;
	case "edge":
		driver = new EdgeDriver(optionsManager.EdgeOptions());
		break;
	case "firefox":
		driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
		break;
	case "safari":
		driver = new SafariDriver();
		
	default:
		System.out.println("please pass the correct browser...   " + browserName);
		// throw is  a custom exception. user can display own exception messages
		throw new BrowserException("===== INVALID BROWSER=======");
		
	} 

	driver.get(prop.getProperty("url"));
	
	driver.manage().window().maximize();
	//driver.manage().deleteAllCookies();
	return driver;
} */
	
	/**
	 * 1.Creates a FileInputStream by opening a connection to an actual file,
	 * the following method is used to initialise and read the config properties
	 * 
	 */
	
//	public Properties initProp(){
//		prop = new Properties(); // properties is a java class from Java.util. 
//		
//		try {
//			FileInputStream ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties/");
//			prop.load(ip);
//			
//		} catch (FileNotFoundException e) {
//			
//			e.printStackTrace();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		return prop; // prop returns Properties
//		
//	}
 
  /**
   * initProp() implementation for the different environments 
   * mvn clean install -Denv (here D is an identifier)
   * @return
   */
   public Properties initProp(){
	  String envName =  System.getProperty("env");
	  
	  FileInputStream ip =null;
	  prop = new Properties();
	  		
		
		try {
			
			  if(envName == null) {
				 // System.out.println("Environment is null hence running tests on QA environment ...");
				  log.warn("env is null, hence running the tests on QA env by default...");
				  ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			  }
			  else {
				  
				  //System.out.println("Running tests on environment :  " + envName);
				  log.info("Running tests on environment :  " + envName);
				  
				  switch(envName.toLowerCase().trim()) {
				  case "qa":
					  ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
					  break;
				  case "dev":
					  ip=new FileInputStream("./src/test/resources/config/dev.config.properties");
					  break;	
				  case "stage":
					  ip=new FileInputStream(".\\src\\test\\resources\\config\\stage.config.properties/");
					  break;	
				  case "uat":
					  ip=new FileInputStream(".\\src\\test\\resources\\config\\uat.config.properties/");
					  break;	 
				  case "prod":
					  ip=new FileInputStream(".\\src\\test\\resources\\config\\prod.config.properties/");
					  break;	
					  
				  default:
					  log.error("----Invalid env name ---" + envName);
					  throw new FrameworkException("****** INVALID ENVIRONMENT NAME ***************   "  + envName);
					  
							  
				  }
			  }
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
	     }
		try {
			prop.load(ip);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return prop; // prop returns Properties
	
	}
   //**********takescreenshot***********
   
   //screenshot file format
   public static File getScreenshotFile() {
//	   TakesScreenshot ts = (TakesScreenshot) driver;
//	   File file = ts.getScreenshotAs(OutputType.FILE);
//	   return file;
	   
	   //the above code simplified like below
	   File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE); //tem directory
	   return srcFile;
   }
   
   //screenshot in byte format
   public static byte[] getScreenshotByte() {
	   return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES); //tem directory
	   
   }
   
 //screenshot in Base64 format
   public static String getScreenshotBase64() {
	   return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64); //tem directory
	   
   }

}
