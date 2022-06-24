package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_14_Log_Allure extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password, confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-Condition: Open browser" + browserName);
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "test" + generateRandomNumber() + "@gmail.com";
		password = "123456";
		confirmPassword = "123456";
	}

	@Test
	public void User_01_Register() {
		log.info("User_01_Register - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("User_01_Register - Step 02: Enter to First Name textbox");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("User_01_Register - Step 03: Enter to Last Name textbox");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("User_01_Register - Step 04: Enter to Email Address textbox with value" + emailAddress);
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("User_01_Register - Step 05: Enter to Password textbox with value" + password);
		registerPage.inputToPasswordTextbox(password);

		log.info("User_01_Register - Step 06: Enter to Confirm Password textbox with value" + confirmPassword);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		log.info("User_01_Register - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("User_01_Register - Step 08: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("User_01_Register - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void User_02_Login() {
		log.info("User_02_Login - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();

		log.info("User_02_Login - Step 02: Input to Email Address textbox with value" + emailAddress);
		loginPage.inputToEmailTextbox(emailAddress);

		log.info("User_02_Login - Step 03: Input to Password textbox with value" + password);
		loginPage.inputToPasswordTextbox(password);

		log.info("User_02_Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		log.info("User_02_Login - Step 05: Verify My Account link displayed");
		verifyFalse(homePage.isMyAccountDisplayed());
	}

	@AfterClass
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		driver.quit();
	}
}
