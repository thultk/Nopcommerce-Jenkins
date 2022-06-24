package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserOrdersPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserSearchPageObject;

public class Level_09_Dynamic_Locator extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password, confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserSearchPageObject userSearchPage;
	private UserOrdersPageObject userOrdersPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
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
		registerPage = homePage.clickToRegisterLink();

		System.out.println("Pre-condition - Step 02: Input values to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTextbox(emailAddress);

		loginPage.inputToPasswordTextbox(password);

		homePage = loginPage.clickToLoginButton();

		Assert.assertTrue(homePage.isMyAccountDisplayed());
	}

	@Test
	public void User_02_Open_Page_At_Footer() {
		userSearchPage = (UserSearchPageObject) homePage.openFooterPageByName(driver, "Search");
		customerInfoPage = (UserCustomerInfoPageObject) userSearchPage.openFooterPageByName(driver, "My account");
		customerInfoPage.getFooterPageByName(driver, "Orders");
		userOrdersPage = PageGeneratorManager.getUserOrdersPage(driver);
		userSearchPage = (UserSearchPageObject) userOrdersPage.openFooterPageByName(driver, "Search");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
