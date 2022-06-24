package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.nopcommerce.common.Common_01_Login;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class Level_15_Share_State extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;

	private UserLoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-Condition: Open browser" + browserName);
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("Pre-Condition - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();

		log.info("Pre-Condition - Step 02: Set all cookies");
		loginPage.setCookies(driver, Common_01_Login.loginPageCookie);
		loginPage.refreshCurrentPage(driver);

		log.info("Pre-Condition - Step 03: Click on NopCommerce image");
		homePage = loginPage.openHomePage();
		homePage.sleepInSecond(3);

		log.info("Pre-Condition - Step 04: Verify My Account link displayed");
		verifyTrue(homePage.isMyAccountDisplayed());
	}

	// @Test
	public void User_01_Login() {

	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-Condition: Close browser" + browserName);
		closeBrowserAndDriver();
	}
}
