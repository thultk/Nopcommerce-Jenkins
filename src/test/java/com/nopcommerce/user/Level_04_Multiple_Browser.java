package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, validEmailAddress, invalidEmailAddress, password, confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserName(browserName);
		driver.get("https://demo.nopcommerce.com/");
		homePage = new UserHomePageObject(driver);
		firstName = "Automation";
		lastName = "FC";
		validEmailAddress = "test" + generateRandomNumber() + "@gmail.com";
		invalidEmailAddress = "123@";
		password = "123456";
		confirmPassword = "123456";
		System.out.println("Pre-condition - Register a success account");
		System.out.println("Pre-condition - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Pre-condition - Step 02: Input values to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		System.out.println("Pre-condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-condition - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
		homePage = new UserHomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login 01 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);

		System.out.println("Login 01 - Step 02: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login 01 - Step 03: Verify error message for empty data displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login 02 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);

		System.out.println("Login 02 - Step 02: Input invalid email to Email textbox");
		loginPage.inputToEmailTextbox(invalidEmailAddress);

		System.out.println("Login 02 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login 02 - Step 04: Verify error message for invalid email displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateRandomNumber() {
		Random rnd = new Random();
		return rnd.nextInt();
	}
}
