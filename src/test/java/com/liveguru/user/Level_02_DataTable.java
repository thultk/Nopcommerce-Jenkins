package com.liveguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.liveGuru.LiveGuruUserHomePageObject;
import pageObjects.liveGuru.UserLoginPageObject;
import pageObjects.liveGuru.UserMyDashboardPageObject;
import pageObjects.liveGuru.AdminHomePageObject;
import pageObjects.liveGuru.AdminLoginPageObject;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.UserRegisterPageObject;

public class Level_02_DataTable extends BaseTest {
	WebDriver driver;
	private String firstName, lastName, password, adminUsername, adminPassword;
	private String emailAddress = "test" + generateRandomNumber() + "@hotmail.com";
	LiveGuruUserHomePageObject liveGuruUserHomePage;
	UserLoginPageObject userLoginPage;
	UserRegisterPageObject userRegisterPage;
	UserMyDashboardPageObject userMyDashboardPage;
	AdminLoginPageObject adminLoginPage;
	AdminHomePageObject adminHomePage;

	@Parameters({ "browser", "userUrl", "adminUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String userUrl, String adminUrl) {
		driver = getBrowserName(browserName, userUrl);
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		adminUsername = "user01";
		adminPassword = "guru99com";
		liveGuruUserHomePage = PageGeneratorManager.getUserHomePage(driver);
		showBrowserConsoleLogs(driver);
		userLoginPage = liveGuruUserHomePage.clickToMyAccountLink();
		showBrowserConsoleLogs(driver);
		userRegisterPage = userLoginPage.clickToCreateAnAccountLink();
		showBrowserConsoleLogs(driver);

		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(emailAddress);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		userMyDashboardPage = userRegisterPage.clickToRegisterButton();
		showBrowserConsoleLogs(driver);

		Assert.assertEquals(userMyDashboardPage.getSuccessfulRegisterMessage(), "Thank you for registering with Main Website Store.");
		showBrowserConsoleLogs(driver);

		userMyDashboardPage.clickToAccountLink(driver);
		liveGuruUserHomePage = userMyDashboardPage.clickToLogoutLink(driver);
		showBrowserConsoleLogs(driver);

		liveGuruUserHomePage.openPageUrl(driver, adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		showBrowserConsoleLogs(driver);
	}

	@Test
	public void Table_01_Search_Registered_User() {
		adminHomePage = adminLoginPage.loginAsAdmin(adminUsername, adminPassword);
		showBrowserConsoleLogs(driver);
		adminHomePage.inputToHeaderTextboxByName("Email", "email", emailAddress);
		adminHomePage.sleepInSecond(3);
		Assert.assertEquals(adminHomePage.getUserRegisteredInfo("Name", "1"), firstName + " " + lastName);
		showBrowserConsoleLogs(driver);
		Assert.assertEquals(adminHomePage.getUserRegisteredInfo("Email", "1"), emailAddress);
		showBrowserConsoleLogs(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
