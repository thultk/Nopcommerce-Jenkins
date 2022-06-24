package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Login extends BaseTest {
	private WebDriver driver;
	private String emailAddress;
	private String password;
	private String firstName, lastName, confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	public static Set<Cookie> loginPageCookie;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) throws Exception {
		log.info("Pre-Condition: Open browser" + browserName);
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		emailAddress = "test" + generateRandomNumber() + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		confirmPassword = "123456";

		log.info("Common-01 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		log.info("Common-01 - Step 02: Enter to First Name textbox");
		registerPage.inputToFirstnameTextbox(firstName);
		log.info("Common-01 - Step 03: Enter to Last Name textbox");
		registerPage.inputToLastnameTextbox(lastName);
		log.info("Common-01 - Step 04: Enter to Email Address textbox with value" + emailAddress);
		registerPage.inputToEmailTextbox(emailAddress);
		log.info("Common-01 - Step 05: Enter to Password textbox with value" + password);
		registerPage.inputToPasswordTextbox(password);
		log.info("Common-01 - Step 06: Enter to Confirm Password textbox with value" + confirmPassword);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
		log.info("Common-01- Step 07: Click to Register button");
		registerPage.clickToRegisterButton();
		log.info("Common-01 - Step 08: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		log.info("Common-01 - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
		log.info("Common-01 - Step 10: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		log.info("Common-01 - Step 11: Input to Email Address textbox with value" + emailAddress);
		loginPage.inputToEmailTextbox(emailAddress);
		log.info("Common-01 - Step 12: Input to Password textbox with value" + password);
		loginPage.inputToPasswordTextbox(password);
		log.info("Common-01 - Step 13: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		log.info("Common-01 - Step 14: Get cookies");
		loginPageCookie = homePage.getCookies(driver);
		log.info("Common-01 - Step 15: Verify My Account link displayed");
		verifyTrue(homePage.isMyAccountDisplayed());
		log.info("Post-Condition: Close browser" + browserName);
		closeBrowserAndDriver();

	}
}
