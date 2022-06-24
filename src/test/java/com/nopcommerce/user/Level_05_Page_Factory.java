package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.nopCommerce.HomePageObject;
import pageFactory.nopCommerce.LoginPageObject;
import pageFactory.nopCommerce.RegisterPageObject;

public class Level_05_Page_Factory extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, validEmailAddress, notFoundEmailAddress, invalidEmailAddress, validPassword, confirmPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserName(browserName);
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObject(driver);
		firstName = "Automation";
		lastName = "FC";
		validEmailAddress = "test" + generateRandomNumber() + "@gmail.com";
		notFoundEmailAddress = "fc" + generateRandomNumber() + "@hotmail.vn";
		invalidEmailAddress = "123@";
		validPassword = "123456";
		confirmPassword = "123456";
		System.out.println("Pre-condition - Register a success account");
		System.out.println("Pre-condition - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);

		System.out.println("Pre-condition - Step 02: Input values to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmailAddress);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		System.out.println("Pre-condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-condition - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
		homePage = new HomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login 01 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		System.out.println("Login 01 - Step 02: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login 01 - Step 03: Verify error message for empty data displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login 02 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		System.out.println("Login 02 - Step 02: Input invalid email to Email textbox");
		loginPage.inputToEmailTextbox(invalidEmailAddress);

		System.out.println("Login 02 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login 02 - Step 04: Verify error message for invalid email displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Not_Found_Email() {
		System.out.println("Login 03 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		System.out.println("Login 03 - Step 02: Input not found email to Email textbox");
		loginPage.inputToEmailTextbox(notFoundEmailAddress);

		System.out.println("Login 03 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login 03 - Step 04: Verify error message for not found email displayed");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		System.out.println("Login 04 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		System.out.println("Login 04 - Step 02: Input existing email to Email textbox");
		loginPage.inputToEmailTextbox(validEmailAddress);

		System.out.println("Login 04 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login 04 - Step 04: Verify error message for empty password displayed");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Existing_Email_Invalid_Password() {
		System.out.println("Login 05 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		System.out.println("Login 05 - Step 02: Input existing email to Email textbox");
		loginPage.inputToEmailTextbox(validEmailAddress);

		System.out.println("Login 05 - Step 03: Input invalid password to Password textbox");
		loginPage.inputToPasswordTextbox("111111");

		System.out.println("Login 05 - Step 04: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login 05 - Step 05: Verify error message for invalid password displayed");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Existing_Email_Valid_Password() {
		System.out.println("Login 06 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		System.out.println("Login 06 - Step 02: Input existing email to Email textbox");
		loginPage.inputToEmailTextbox(validEmailAddress);

		System.out.println("Login 06 - Step 03: Input valid password to Password textbox");
		loginPage.inputToPasswordTextbox(validPassword);

		System.out.println("Login 05 - Step 04: Click to Login button");
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);

		System.out.println("Login 05 - Step 05: Verify Login page switch to Home page successfully");
		Assert.assertTrue(homePage.isMyAccountDisplayed());
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
