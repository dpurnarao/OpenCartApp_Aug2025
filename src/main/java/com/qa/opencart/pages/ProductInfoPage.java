package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By productHeader = By.tagName("h1");
	private final By productImages = By.cssSelector("ul.thumbnails img");
	private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> productMap;
	
	//ul[@class='thumbnails']//img ....xpath
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		
		String header = eleUtil.waitForElementVisible(productHeader, AppConstants.MEDIUM_DEFAULT_TIMEOUT).getText();
		
		System.out.println(" product header is : " + header);
		
		return header;
	}
	
	public int getProductImageCount() {
		int imageCount = eleUtil.waitForAllElementsVisible(productImages, AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("Total number of images is :  " + imageCount);
		return imageCount;
	}
	
	public Map<String, String> getProductDetailsMap() {
		//productMap = new HashMap<String, String>(); not an orderbased collection
		
		// productMap = new LinkedHashMap<String, String>();
		productMap = new TreeMap<String, String>(); // maintained sorted order for the key
		
		productMap.put("productheader", getProductHeader());
		
		//getProductImageCount is an integer. hence the method is converted to String
		productMap.put("productimages", String.valueOf(getProductImageCount()));
		
		getProductMetaData();
		getProductPriceData();
		System.out.println("Full product details:   " + productMap);
		return productMap;
		
	}

	// product details:
//	Brand: Apple
//	Product Code: Product 16
//	Reward Points: 600
//	Availability: In Stock
	private void getProductMetaData() {
		// productMap = new HashMap<String, String>();
		 
		 List<WebElement> MetaList = eleUtil.waitForAllElementsVisible(productMetaData, AppConstants.DEFAULT_TIMEOUT);
		 
		 for(WebElement e : MetaList) {
			 
			 String metaData = e.getText();
			 
			 String meta[] = metaData.split(":");
			 
			 String metaKey = meta[0].trim();
			 
			 String metaValue = meta[1].trim();
			 
			 productMap.put(metaKey, metaValue);
		 
		 }
	}
	
//	$602.00
//	Ex Tax: $500.00
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.waitForAllElementsVisible(productPriceData, AppConstants.DEFAULT_TIMEOUT);
		String productPrice = priceList.get(0).getText();
		
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		
		productMap.put("productprice", productPrice);
		productMap.put("extaxprice", exTaxPrice);
		
	}

}
