package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

public class Level_10_DataTable extends BaseTest {
	private WebDriver driver;
	HomePageObject homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserName(browserName, url);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	// @Test
	public void Table_01_Paging() {
		homePage.openPageByNumber("2");
		homePage.isPageActiveByNumber("2");
		homePage.sleepInSecond(3);

		homePage.openPageByNumber("22");
		homePage.isPageActiveByNumber("22");
		homePage.sleepInSecond(3);
	}

	// @Test
	public void Table_02_Input() {
		homePage.inputToHeaderTextboxByName("Females", "384187");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);

		homePage.inputToHeaderTextboxByName("Country", "AFRICA");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);
	}

	// @Test
	public void Table_03_Click_Icon() {
		homePage.clickToIconByCountryName("AFRICA", "qgrd-remove-row-btn");
		homePage.sleepInSecond(3);

		homePage.clickToIconByCountryName("Albania", "qgrd-edit-row-btn");
		homePage.sleepInSecond(3);
	}

	// @Test
	public void Table_04_Verify_Row_Value() {
		homePage.inputToHeaderTextboxByName("Males", "407124");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isDataValueByRowDisplayed("384187", "Afghanistan", "407124", "791312"));
		homePage.refreshCurrentPage(driver);

		homePage.inputToHeaderTextboxByName("Country", "AFRICA");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isDataValueByRowDisplayed("12253515", "AFRICA", "12599691", "24853148"));
		homePage.refreshCurrentPage(driver);
	}

	// @Test
	public void Table_05_Input_To_Cell() {
		homePage.inputToCellByColumnNameAndCellIndex("Contact Person", "2", "automation");
		homePage.sleepInSecond(3);

		homePage.inputToCellByColumnNameAndCellIndex("Member Since", "3", "12/23/2021");
		homePage.sleepInSecond(3);
	}

	@Test
	public void Table_06_Click_Icon_At_Row() {
		homePage.clickToButtonAtRow("2", "Move Up");
		homePage.sleepInSecond(3);

		homePage.clickToButtonAtRow("3", "Insert Row Above");
		homePage.sleepInSecond(3);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
