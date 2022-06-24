package com.saucelab.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.sauceLab.InventoryPageObject;
import pageObjects.sauceLab.LoginPageObject;
import pageObjects.sauceLab.PageGenerator;

public class Level_19_Sort extends BaseTest {
	private WebDriver driver;
	LoginPageObject loginPage;
	InventoryPageObject inventoryPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) throws Exception {
		driver = getBrowserName(browserName, url);
		loginPage = PageGenerator.getLoginPage(driver);
		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		inventoryPage = loginPage.clickToLoginButton();
	}

	@Test
	public void Sort_01_Name_Asc() {
		inventoryPage.selectIteminSortDropdown("Name (A to Z)");
		verifyTrue(inventoryPage.isProductNameSortByAscending());
	}

	@Test
	public void Sort_02_Name_Dsc() {
		inventoryPage.selectIteminSortDropdown("Name (Z to A)");
		verifyTrue(inventoryPage.isProductNameSortByDescending());
	}

	@Test
	public void Sort_02_Price_Asc() {
		inventoryPage.selectIteminSortDropdown("Price (low to high)");
		verifyTrue(inventoryPage.isProductPriceSortByAscending());
	}

	@Test
	public void Sort_02_Price_Dsc() {
		inventoryPage.selectIteminSortDropdown("Price (high to low)");
		verifyTrue(inventoryPage.isProductPriceSortByDescending());
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-Condition: Close browser" + browserName);
		closeBrowserAndDriver();
	}
}
