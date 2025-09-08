package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.factory.DriverFactory;

//import Se01_SeleniumSessions.Se09_FrameworkException;
import io.qameta.allure.Step;

public class ElementUtil {
	
	private WebDriver driver; // non-primitive | 
	
	private Actions act; // Actions class reference
	
	private JavaScriptUtil jsUtil; // reference for the JavaScriptUtil
	
	//constructor	
	public ElementUtil(WebDriver driver) { //ElementUtil constructor . when created the object for ElementUtil class this construct will be called
		
		this.driver = driver;
		act = new Actions(driver);
		jsUtil = new JavaScriptUtil(driver);
		
	}
	@Step("Clicking on element using : {0}")
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	private void nullCheck(CharSequence... value) {
		if (value == null) {
			throw new RuntimeException("===Value can not be null===");
		}
	}
	
	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	@Step("Waiting for element and clicking on it using: {0} and timeout: {1}")
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}


	
	//doClick method with wait applied
	public void doClick(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}
	
	@Step("Entering value : {1} into element : {0}")
	public void doSendKeys(By locator, String value) {
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
		
	}	
		//doSendKeys with wait applied
		 public void doSendKeys(By locator, String value, int timeOut){
			 waitForElementVisible(locator, timeOut).clear();
			 waitForElementVisible(locator, timeOut).sendKeys(value);
			 
		 }
	
//	public WebElement getElement(By locator) {
//		return driver.findElement(locator);
//		
//	}
	
		 //implementation for the WebElement highlighting
		 @Step("Finding the element using: {0} ")
		 public WebElement getElement(By locator) {
			 //Chaintest report log
			 ChainTestListener.log("Locator :  " + locator.toString());
			 WebElement element = driver.findElement(locator);
			 highlightElement(element);
			 
			return element;
			 }
		 
		private void highlightElement(WebElement element) {
			if(Boolean.parseBoolean(DriverFactory.highlight)) {
				jsUtil.flash(element);
			}
		}
		
		public WebElement getElementWithWait(By locator, int timeOut) {
			return waitForElementVisible(locator, timeOut);
		}

	
       public  boolean isElementDisplayed(By locator) {
		
		try {
			
			return getElement(locator).isDisplayed();			
			    
			
		}catch (NoSuchElementException e) {
			//e.printStackTrace();
			
			System.out.println("Element is not displayed : " + locator);
			
			return false;
		}
		
	}
	
	public String doGetElementText(By locator) {
		// return getElement(locator).getText();
		String eleText = getElement(locator).getText();
		if(eleText !=null) {
			return eleText;
		}
		else {
			System.out.println("Element text is null : " + eleText);
			return null;
		}
	}
	
	@Step("Fetching the element text using: {0}")
	public String doElementGetText(By locator) {
		String eleText = getElement(locator).getText();
		System.out.println("element text =>" + eleText);
		return eleText;
	}

	
	public  String doElementGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
		
	}
	
	//this method returns the count of the elements list
		public  int getElementsCount(By locator) {
			return getElements(locator).size();
		}
		
		//return the webElements list
		public  List<WebElement> getElements(By locator) {
			return driver.findElements(locator);
		}
		
		//this method is responsible to print the list data
		public  void printElementTextList(By locator) {
			List<String> eleTextList = getElementsTextList(locator);
			
			for ( String e : eleTextList) {
				System.out.println(e);
			}
		}
		
		public  boolean isElementPresentMultipleTimes(By locator) {
			
			if(getElementsCount(locator) >=1) {
				return true;
			}
			return false;
			
		}
		
		//check if the elementIsNotPresent
		public  boolean isElementNotPresent(By locator) {
			if(getElementsCount(locator) == 0) {
				
				return true;
			}
			return false;
		}
		
		//checks if the logo or icon(s) is present as expected
		public  boolean isElementPresent(By locator, int expectedElementCount) {
			if(getElementsCount(locator) == expectedElementCount) {
				return true;
			}
			return false;
		}
		
		//checks if the logo or icon is present
		public  boolean isElementPresent(By locator) {
			if(getElementsCount(locator) == 1) {
				return true;
			}
			return false;
		}
		
		// this method utility is responsible to get list text based the tagName. fetch data from list of webElements. 
		//selenium does not provide list of strings. it provides list of webElements
		public  List<String> getElementsTextList(By locator){
		    List<WebElement> eleList =	getElements(locator);
		    
		    List<String> eleTextList = new ArrayList<String>();
		    
		    for(WebElement e : eleList) {
		    	String eleText = e.getText();
		    	
		    	if(eleText.length()!=0) {
		    		eleTextList.add(eleText); // collecting the webElements strings data
		    	}
		    	
		    }
		    return eleTextList;
		}
		
		//Google search scenario...
		public boolean doSearch(By searchField, By suggestions, String searchKey, String matchValue) throws Exception {
			
			boolean flag = false;
			doSendKeys(searchField, searchKey);
			Thread.sleep(3000);
			
			List<WebElement> suggList = getElements(suggestions);
			int totalSuggestions = suggList.size();
			System.out.println("Total number f suggestiongs : " + totalSuggestions);
			
			if(totalSuggestions == 0) {
				System.out.println("No suggestions found ...");
				//throw new Se09_FrameworkException("No suggestios FOUND...");
				throw new FrameworkException("No suggestios FOUND...");
			}
			
			for(WebElement e: suggList) {
				String text = e.getText();
				System.out.println(text);
				
				if(text.contains(matchValue)) {
					e.click();
					flag = true;
					break;
				}
			}
			
			if(flag) {
				System.out.println(matchValue + "  is found  ");
				return true;
			}
			else {
				System.out.println(matchValue + "  is not found  ");
				return false;
			}
		
		}
		
		// ******************select drop down utils****************
		//this method finds the number of options in the drop down list
		
		private Select getSelect(By locator) {
			
			return new Select(getElement(locator));
		}
		
		
		public  int getDropDownOptionsCount(By locator) {
			return getSelect(locator).getOptions().size();
			
			//Select select = new Select(getElement(locator));
			//return select.getOptions().size();
		}
		
		
		public  void selectDropDownValueByValue(By locator, String value) {
			
			getSelect(locator).selectByValue(value);
			
			//Select select = new Select(getElement(locator));
			//select.selectByValue(value);
		}
		
		//select dropdown value by index
		public  void selectDropDownValueByIndex(By locator, int index) {
			
			getSelect(locator).deselectByIndex(index);
			//Select select = new Select(getElement(locator));
			//select.selectByIndex(index);
			
		}
		
		//select dropdown value by visible text
		public  void selectDropDownValueByVisibleText(By locator, String visibleText) {
			
			Select select = new Select(getElement(locator));
			select.selectByVisibleText(visibleText);
			
		}
		
		//Se11_SelectDropDownAllOptions
		//select dropdown value from the list --- using Select class & options attribute
		public  List<String> getDropDownOptionsTextList(By locator) {
			
			//Select select = new Select(getElement(locator));
			//List<WebElement> optionsList = select.getOptions();
			
			List<WebElement> optionsList = getSelect(locator).getOptions();
			
			System.out.println("Dropdown list count :  " + optionsList.size());
			
			List<String> optionsTextList = new ArrayList<String>();
			int i=0;
			for(WebElement e : optionsList) {
				String optionsText = e.getText();
				//System.out.println(i + " :: " + optionsText); // prints the 
				optionsTextList.add(optionsText);
				
				i++;
			}
			
			return optionsTextList;
		
		}
		
		/*
		 * utility to select a value from the drop down list using Select class
		 */
		
       public void   selectDropDownValueUsingSelect(By locator, String value) {
			
			Select select = new Select(getElement(locator));
			List<WebElement> optionsList = select.getOptions();
			
			selectDropDown(optionsList, value);
		
		}
       
       //Se11_SelectDropDownAllOptions
		
		//utility to select a value from the dropdown options list without using Select class
		public  void selectDropDownValue(By locator, String value) {
			
			List<WebElement> countryOptionsList= getElements(locator);
			
			selectDropDown(countryOptionsList, value);
		
		}
		//Se11_DropDownHandleWithoutSelectClass
		public void selectDropDown(List<WebElement> optionsList, String value) {
			int count = optionsList.size();
			System.out.println("total number of options / cout : " + count);
			
			for(WebElement e : optionsList) {
				String text = e.getText();
				
				System.out.println(text);
				
				if(text.equals(value)) {
					e.click();
					break;
				}
			}
			
		}
		
		// ********************* Actions utilities / generic functions***************
		// actions class with sendkeys
		
		//sendKeyswithPause
		public  void doActionsSendKeysWithPause(By locator, String value, long pauseTime) {
			//Actions act = new Actions(driver);
			char ch[] = value.toCharArray();
			
			for(char c : ch) {
				act.sendKeys(getElement(locator), String.valueOf(c)).pause(pauseTime).perform();
				
			}
		}
		
		public void doActionsClick(By locator) {
			act.click(getElement(locator)).perform();
		}
		
		public  void  doActionsSendKeys(By locator, String value) {
			//Actions act = new Actions(driver); a reference is created at class level
			act.sendKeys(getElement(locator), value).perform();
		}
		
		// click with actions class
		public  void doClickCheckBox(By locator) {
			Actions act = new Actions(driver);
			act.click(getElement(locator)).perform();
		}
		/**
		 * The below parentChildMenu() method is overloaded generic method that performs click action on a child menu by traversing through parent menu
		 * @param parentMenu
		 * @param childMenu
		 */
		
		public  void parentChildMenu(By parentMenu, By childMenu) {
			
			//Actions act = new Actions(driver); no need to initialize here ... Action reference is being accessed from the constructor
			
			//act.moveToElement(getElement(By.xpath("//div[text()='"+parentMenu+"']"))).perform();
			
			act.moveToElement(getElement(parentMenu)).perform();
			getElement(childMenu).click();
			//doClick(childMenu); generic method for clicking action
			
		}
		
		public  void parentChildMenu(String parentMenu, String childMenu) {
			
			//Actions act = new Actions(driver); no need to initialize here ... Action reference is being accessed from the constructor
			act.moveToElement(getElement(By.xpath("//*[text()='"+parentMenu+"']"))).perform();
			
			getElement(By.xpath("//*[text()='"+childMenu+"']")).click();
			//doClick(By.xpath("//*[text()='"+childMenu+"']")); // generic for clicking action
			
			
		}
		
		//this method handles the 4 level parent and child menu traverse on the basis of By locator
		public  void parentChildMenu(By level1Menu, By level2Menu, By level3Menu, By level4Menu ) throws InterruptedException {
			
			//getElement(level1Menu).click();
			doClick(level1Menu);
			
			Thread.sleep(1000);
			
			// Actions act = new Actions(driver); no need to initialize here ... Action reference is being accessed from the constructor
			
			act.moveToElement(getElement(level2Menu)).perform();
			
			Thread.sleep(1000);
			
			act.moveToElement(getElement(level3Menu)).perform();
			
			Thread.sleep(1000);
			
			//getElement(level4Menu).click();
			doClick(level4Menu);
			
			
		}
		
/**
 * ****************************Wait utils: *********************
 * ExplicitWaits:WebDriverWait AND FluentWait
 * 1.presenceOfElementLocated
 * 2.visibilityOfElementLocated
 * default polling time/interval time =500ms		
 * 
 */

		
		//utility for the WebDriverWait webElement
		//This method is for presenceOfElementLocated
		public  WebElement waitForElementPresence(By locator, int timeOut) {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		//This method is for visibilityOfElementLocated
		@Step("waiting for element using: {0} and timeout : {1}")
		public  WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			
			 WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			 highlightElement(element);

			 return element;
			
		}
		
		public  WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
			
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
		}
		
		/**
		 * wait for element visible on the page with Fluent wait features
		 * @param locator
		 * @param timeOut
		 */
		public WebElement waitForElementVisibleWithFluentFeatures(By locator, int timeOut, int pollingTime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(pollingTime))
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class)
					.ignoring(ElementNotInteractableException.class)
					.withMessage("**** Web element is not found****" + locator);
			
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		//elementToBeClickable: An expectation for checking an element is visible and enabled such that you can click it
		public void waitForElementAndClick(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		
		}
		
		/**
		 * An expectation for checking that all elements present on the web page that
		 * match the locator are visible. Visibility means that the elements are not
		 * only displayed but also have a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForAllElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			} catch (TimeoutException e) {
				return Collections.EMPTY_LIST; //returns empty list :[]-0
			}
		}

		
		//return list of webElements: List<WebElement>. All elements present on the web page
		//visibilityOfAllElementsLocatedBy
		//An expectation for checking that all elements present on the web page that match the locatorare visible.
		//Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
		public  List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		
		//presenceOfAllElementsLocatedBy
		//An expectation for checking that there is at least one element present on a web page.
		public  List<WebElement> waitForElementsPresence(By locator, int timeout) {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}
		
	//	****** pageTitle utils ************
		
		//full text string as expected
		public  String getPageTitle(String expectedTitle, int timeOut) {
			if(waitForTitle(expectedTitle, timeOut)) {
				return driver.getTitle();
			}
			else {
				return "-1";
			}
			
		}
		
	/*	public  String getPageTitleIs(String expectedTitle, int timeOut) {
			
			if(waitForTitleIs(expectedTitle, timeOut)) {
				return driver.getTitle();
				
			}
			else {
				return "-1";
			}
			
		} */
		
		//partial text string as expected
		public  String getPageTitleContains(String expectedTitle, int timeOut) {
			if(waitForTitleContains(expectedTitle, timeOut)) {
				return driver.getTitle();
			}
			else {
				return "-1";
			}
			
		}
		
		//waitforTitleContains
		public  boolean waitForTitleContains(String fractionTitle, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			boolean flag = false;
			try {
				return wait.until(ExpectedConditions.titleContains(fractionTitle));
			} catch(TimeoutException e) {
				System.out.println("Title is not matched");
				return flag;
			}
			
		}
		
	/*	public  boolean waitForTitleIs(String expectedTitle, int timeOut) {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			boolean flag =false;
			try {
				return wait.until(ExpectedConditions.titleIs(expectedTitle));//il title not matched, it will be throw timeOut exception
			}catch(TimeoutException e) {
				System.out.println("Title is not matched");
				return flag;
			}
		} */
		
		public String waitForTitleIs(String title, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				wait.until(ExpectedConditions.titleIs(title));
				return driver.getTitle();

			} catch (TimeoutException e) {
				return null;
			}

		}

			
		//titleIS
		public  boolean waitForTitle(String expectedTitle, int timeOut) {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			
			boolean flag = false;
			
			try {
				wait.until(ExpectedConditions.titleIs(expectedTitle)); // if title is not matched, it will be throwing timeOut exception
				flag = true;
				return flag;
			}catch (TimeoutException e) {
				System.out.println("Title is not matched");
				return flag;
			}
			
		}
		
		//**** util for the getCurrentURL*********
		// getcurrentURL
	/*	public  String getPageURLContains(String fractionURL, int timeOut) {
			if(waitForURLContains(fractionURL, timeOut)) {
				return driver.getCurrentUrl();
				
			}
			else {
				return "-1";
			}
		} */
		
		//urlContains
	/*	public  boolean waitForURLContains(String fractionURL, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			boolean flag = false;
			try {
				return wait.until(ExpectedConditions.urlContains(fractionURL));
			}catch(TimeoutException e) {
				System.out.println("URL is not matched");
				return flag;
				
			}
		} */
		
		// wait for url:
		public String waitForURLContains(String fractionURL, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				wait.until(ExpectedConditions.urlContains(fractionURL));
				return driver.getCurrentUrl();

			} catch (TimeoutException e) {
				return null;
			}

		}

		
		//*************** wait for Alerts and Frames *************
		public  Alert waitForAlertAndSwitchToAlert(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent()); // returns Alert
						
		}
		/**
		 * wait for alert using Fluent wait feature
		 * 
		 */
		
		public Alert waitForAlertUsingFluentWaitAndSwitch(int timeOut) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.ignoring(NoAlertPresentException.class)
					.withMessage("**** JS alert is not present" );
			 return wait.until(ExpectedConditions.alertIsPresent());
		}
		
		
		public  String getAlertText(int timeOut) {
			return waitForAlertAndSwitchToAlert(timeOut).getText();
		}
		
		public  void acceptAlert(int timeOut) {
			waitForAlertAndSwitchToAlert(timeOut).accept();
		}
		
		public  void dismissAlert(int timeOut) {
			waitForAlertAndSwitchToAlert(timeOut).dismiss();
		}
		
		public  void enterValueOnAlert(int timeOut, String value) {
			waitForAlertAndSwitchToAlert(timeOut).sendKeys(value);
		}
		
		// wait for frame
		public void waitForFrameUsingLocatorAndSwitchToIt(By frameLocator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		}
		//frame index
		public void waitForFrameUsingLocatorAndSwitchToIt(int frameIndex, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		}
		//frame ID or Name
		public void waitForFrameUsingLocatorAndSwitchToIt(String idOrName, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
		}
		//frame webElement
		public void waitForFrameUsingLocatorAndSwitchToIt(WebElement frameElement, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		}
		
		//method for wait window / tab
		public  boolean waitForNewWindowOrTab(int expectedNumberOfWindows, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				if(wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows))) {
					return true;
				}
			}catch (TimeoutException e) {
				System.out.println("Number of windows are not matched...");
				
			}
			return false;
		}

		
		

}
//The following functions are tweaked to utitlise the webelements list

/* 
 //select dropdown value from the list --- using Select class & options attribute
		public  List<String> getDropDownOptionsTextList(By locator) {
			
			Select select = new Select(getElement(locator));
			List<WebElement> optionsList = select.getOptions();
			
			System.out.println("Dropdown list count :  " + optionsList.size());
			
			List<String> optionsTextList = new ArrayList<String>();
			int i=0;
			for(WebElement e : optionsList) {
				String optionsText = e.getText();
				//System.out.println(i + " :: " + optionsText); // prints the 
				optionsTextList.add(optionsText);
				
				i++;
			}
			
			return optionsTextList;
		
		}
		
		/*
		 * utility to select a value from the drop down list using Select class
		 */
		
    /*   public void   selectDropDownValueUsingSelect(By locator, String value) {
			
			Select select = new Select(getElement(locator));
			List<WebElement> optionsList = select.getOptions();
			
			System.out.println("Dropdown list count :  " + optionsList.size());
			
			// List<String> optionsTextList = new ArrayList<String>();
			int i=0;
			for(WebElement e : optionsList) {
				String optionsText = e.getText();
				System.out.println(i + " :: " + optionsText);
				
				if(optionsText.equals(value)) {
					System.out.println("Selected country is : " + optionsText);
					e.click();
					break;
				}
			
			}
		
		}
		
		//utility to select a value from the dropdown options list without using Select class
		public  void selectDropDownValue(By locator, String value) {
			
			List<WebElement> countryOptionsList= getElements(locator);
			
			int count = countryOptionsList.size();
			
			System.out.println("Total count of the country list  =  " + count);
			
			for(WebElement e : countryOptionsList ) {
				
				String text = e.getText();
				
				System.out.println(text);
				
				if(text.equals(value)) {
					System.out.println("Country =  " + text);
					e.click();
					
					break;
				}
			}
		}	

}
 
 */
 

