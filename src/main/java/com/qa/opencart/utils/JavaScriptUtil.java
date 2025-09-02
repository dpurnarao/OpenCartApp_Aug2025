package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {	
		
		private WebDriver driver;
		private JavascriptExecutor js;
		
		//constructor
		public JavaScriptUtil(WebDriver driver) {
			this.driver = driver;
			js = (JavascriptExecutor)driver;	
		}
		
		//get page title using javascriptExecutor interface
		public String getPagetitle() {
			
			return js.executeScript("return document.title").toString();
			
		}
		
		////get webpage url using javascriptExecutor interface
		public String getPageURL() {
			return js.executeScript("return document.URL").toString();
		}
		
		// alert for debugging purpose
		public void generateJsAlert(String mesg) {
			
			js.executeScript("alert('"+mesg+"')").toString();
		}
		
		//javascript that fetches the innertext in a web url. can be used for web page content verification
		public String getPageInnerText() {
			return js.executeScript("return document.documentElement.innerText").toString();
		}
		//backwards with javascript
		public void goBackWithJS() {
			js.executeScript("history.go(-1)");
		}
		//go forward with JavaScript
		public void goForwardWithJS() {
			js.executeScript("history.go(1)");
		}
		//refresh web page with java script
		public void pageRefreshWithJS() {
			js.executeScript("history.go(0)");
		}
		
		/**
		 * example: document.body.style.zoom = "100.0%";
		 * @param zoomPercentage
		 */
		public void zoomChromeEdgeSafari(String zoomPercentage) {
			
			String zoom = "document.body.style.zoom = '"+zoomPercentage+"%'";
			js.executeScript(zoom);
			
		}
		//zoomin for FireFox
		public void zoomFireFox(String zoomPercentage) {
			String zoom = "document.body.style.MozTransform = 'scale("+zoomPercentage+")'";
			js.executeScript(zoom);
		}
		
		// highlights the border of a text
		public void drawBorder(WebElement element) {
			
			js.executeScript("arguments[0].style.border='3px solid red'", element);
		}
		
		private void changeColor(String color, WebElement element) {
			js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);//GBGBGBGBGBGB

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}

		
		public void flash(WebElement element) {
			String bgcolor = element.getCssValue("backgroundColor");//blue
			for (int i = 0; i < 5; i++) {
				changeColor("rgb(0,200,0)", element);// green
				changeColor(bgcolor, element);// blue
			}
		}


	}



