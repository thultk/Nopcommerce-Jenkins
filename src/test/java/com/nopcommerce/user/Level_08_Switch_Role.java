package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.admin.DashboardPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class Level_08_Switch_Role extends BaseTest {
	private WebDriver driver;
	private String userEmailAddress, adminEmailAddress;
	private String userPassword, adminPassword;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private AdminLoginPageObject adminLoginPage;
	private DashboardPageObject dashboardPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {
		driver = getBrowserName(browserName, environment);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userHomePage.openPageUrl(driver, GlobalConstants.USER_DEV_URL);

		userEmailAddress = "test1@hotmail.com";
		adminEmailAddress = "admin@yourstore.com";
		userPassword = "123456";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_User_To_Admin() {
		userLoginPage = userHomePage.clickToLoginLink();

		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountDisplayed());

		userCustomerInfoPage = userHomePage.clickToMyAccountLink();

		userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);

		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_DEV_URL);

		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		dashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed());

		adminLoginPage = dashboardPage.clickToLogoutLinkAtAdminPage(driver);
	}

	@Test
	public void Role_02_Admin_To_User() {
		adminLoginPage.openPageUrl(driver, GlobalConstants.USER_DEV_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userLoginPage = userHomePage.clickToLoginLink();

		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
