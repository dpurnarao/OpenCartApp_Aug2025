package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("username") , prop.getProperty("password"));
		
	}
	
	//expected data --- i.e test data
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object [][] {
			{"macbook", "MacBook Pro"},
			{"macbook", "MacBook Air"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"}
		};	
		
	}
	
	//test parameterisation
	@Test(dataProvider ="getProductTestData" )
	public void productHeaderTest(String searchKey, String productName) { // searchKey & productName are holding parameters for 2 columns
		
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actualHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualHeader, productName);
	} 
	
	
	/* without data parameterisation
	@Test
	public void productHeaderTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actualHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualHeader, "MacBook Pro");
	}  */
	
	/*
	//expected test data for the productImagecountTest() 
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object [][] {
			{"macbook", "MacBook Pro", 4},
			{"macbook", "MacBook Air", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"samsung", "Samsung Galaxy Tab 10.1", 7}
		};	
		
	} */
	
	
	
	/*
	//parameterised expected test data 
	@Test(dataProvider="getProductImagesTestData")
	public void productImageCountTest(String searchKey, String productName, int expectedImageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actualImageCount = productInfoPage.getProductImageCount();
		
		Assert.assertEquals(actualImageCount, expectedImageCount);
		
	} */
	
	/*
	@Test
	public void productImageCountTest() {
		searchResultsPage = accPage.doSearch("imac");
		productInfoPage = searchResultsPage.selectProduct("iMac");
		int actualImageCount = productInfoPage.getProductImageCount();
		
		Assert.assertEquals(actualImageCount, 3);
		
	} */
	
	/*
	//parameterised from Excel spread sheet using ExcelUtility
		@DataProvider
		public Object[][] getProductSheetData() {
			return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
			
		}
		
		@Test(dataProvider = "getProductSheetData")
		public void productImageCountTest(String searchKey, String productName, String expectedImageCount) {
			searchResultsPage = accPage.doSearch(searchKey);
			productInfoPage = searchResultsPage.selectProduct(productName);
			int actualImageCount = productInfoPage.getProductImageCount();
			
			Assert.assertEquals(String.valueOf(actualImageCount), expectedImageCount);
			
		} */
		
		//data reading from CSV file using CSVUtility
		@DataProvider
		public Object[][] getProductCSVData() {
			return CSVUtil.csvData("product");
			
		}
		
		@Test(dataProvider = "getProductCSVData")
		public void productImageCountTest(String searchKey, String productName, String expectedImageCount) {
			searchResultsPage = accPage.doSearch(searchKey);
			productInfoPage = searchResultsPage.selectProduct(productName);
			int actualImageCount = productInfoPage.getProductImageCount();
			
			Assert.assertEquals(String.valueOf(actualImageCount), expectedImageCount);
			
		}
		
	
	// product details:
//	Brand: Apple
//	Product Code: Product 16
//	Reward Points: 600
//	Availability: In Stock
//	$602.00
//	Ex Tax: $500.00
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductDetailsMap =   productInfoPage.getProductDetailsMap();
		
		//hard assertion
	/*	Assert.assertEquals(actualProductDetailsMap.get("productheader"), "MacBook Pro");
		Assert.assertEquals(actualProductDetailsMap.get("productimages"), "4");
		
		Assert.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");
		Assert.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");
		Assert.assertEquals(actualProductDetailsMap.get("productprice"), "$2,000.00");
		Assert.assertEquals(actualProductDetailsMap.get("extaxprice"), "$2,000.00");   */
		
		//Soft Assertions
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actualProductDetailsMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(actualProductDetailsMap.get("extaxprice"), "$2,000.00");
		
		softAssert.assertAll();
		
		
	}
	

}
