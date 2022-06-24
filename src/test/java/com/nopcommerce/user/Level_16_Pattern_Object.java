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

public class Level_16_Pattern_Object extends BaseTest {
	private WebDriver driver;
	private String emailAddress, password, firstName, lastName, confirmPassword, day, month, year;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-Condition: Open browser" + browserName);
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		emailAddress = "test" + generateRandomNumber() + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		confirmPassword = "123456";
		day = "8";
		month = "11";
		year = "2011";
	}

	@Test
	public void User_01_Register() {
		log.info("User_01_Register - Step 01: Click to Register link");
		homePage.getHeaderPageByName(driver, "Register");
		registerPage = PageGeneratorManager.getUserRegisterPage(driver);

		log.info("User_01_Register - Step 02: Check to Male radio button");
		registerPage.clickToRadioButtonByID(driver, "gender-male");

		log.info("User_01_Register - Step 03: Enter to First Name textbox");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);

		log.info("User_01_Register - Step 04: Enter to Last Name textbox");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);

		log.info("User_01_Register - Step 05: Select day in dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthDay", day);

		log.info("User_01_Register - Step 06: Select month in dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);

		log.info("User_01_Register - Step 07: Select year in dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);

		log.info("User_01_Register - Step 08: Enter to Email Address textbox with value" + emailAddress);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("User_01_Register - Step 09: Enter to Password textbox with value" + password);
		registerPage.inputToTextboxByID(driver, "Password", password);

		log.info("User_01_Register - Step 10: Enter to Confirm Password textbox with value" + confirmPassword);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", confirmPassword);

		log.info("User_01_Register - Step 11: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("User_01_Register - Step 12: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("User_01_Register - Step 13: Click to Logout link");
		registerPage.getHeaderPageByName(driver, "Log out");
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void User_02_Login() {
		log.info("User_02_Login - Step 01: Click to Login link");
		homePage.getHeaderPageByName(driver, "Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		log.info("User_02_Login - Step 02: Input to Email Address textbox with value" + emailAddress);
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("User_02_Login - Step 03: Input to Password textbox with value" + password);
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("User_02_Login - Step 04: Click to Log in button");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("User_02_Login - Step 05: Click to My Account link");
		loginPage.getHeaderPageByName(driver, "My account");
		homePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("User_02_Login - Step 06: Verify My Account link displayed");
		verifyTrue(homePage.isMyAccountDisplayed());
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-Condition: Close browser" + browserName);
		closeBrowserAndDriver();
	}
}
