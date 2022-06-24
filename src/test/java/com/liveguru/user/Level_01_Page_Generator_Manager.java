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
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.UserRegisterPageObject;

public class Level_01_Page_Generator_Manager extends BaseTest {
	WebDriver driver;
	private String firstName, lastName, password;
	private String emailAddress = "test" + generateRandomNumber() + "@hotmail.com";
	LiveGuruUserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	UserRegisterPageObject userRegisterPage;
	UserMyDashboardPageObject userMyDashboardPage;

	@Parameters({ "browser", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String userUrl) {
		driver = getBrowserName(browserName);
		driver.get(userUrl);
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void User_01_Register_To_System() {
		userLoginPage = userHomePage.clickToMyAccountLink();
		userRegisterPage = userLoginPage.clickToCreateAnAccountLink();

		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(emailAddress);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		userMyDashboardPage = userRegisterPage.clickToRegisterButton();

		Assert.assertEquals(userMyDashboardPage.getSuccessfulRegisterMessage(), "Thank you for registering with Main Website Store.");

		userMyDashboardPage.clickToAccountLink(driver);
		userHomePage = userMyDashboardPage.clickToLogoutLink(driver);
	}

	@Test
	public void User_02_Login_To_System() {
		userLoginPage = userHomePage.clickToMyAccountLink();

		userLoginPage.inputToEmailAddressTextbox(emailAddress);
		userLoginPage.inputToPasswordTextbox(password);
		userMyDashboardPage = userLoginPage.clickToLoginButton();

		Assert.assertEquals(userMyDashboardPage.getSuccessLoggedInFirstName(), "Hello, " + firstName + " " + lastName + "!");
		Assert.assertEquals(userMyDashboardPage.getCorrectAccountInformation(), firstName + " " + lastName + "\n" + emailAddress + "\n" + "Change Password");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
