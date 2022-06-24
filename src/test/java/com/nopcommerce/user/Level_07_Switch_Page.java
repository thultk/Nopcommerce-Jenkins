package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductPreviewsPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;
import pageObjects.nopCommerce.user.UserSearchPageObject;
import pageObjects.nopCommerce.user.UserShippingAndReturnPageObject;
import pageObjects.nopCommerce.user.UserSiteMapPageObject;
import pageObjects.nopCommerce.user.UserWishlistPageObject;

public class Level_07_Switch_Page extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password, confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressPageObject addressPage;
	private UserMyProductPreviewsPageObject myProductPreviewsPage;
	private UserRewardPointsPageObject rewardPointsPage;
	private UserSearchPageObject userSearchPage;
	private UserShippingAndReturnPageObject userShippingAndReturnPage;
	private UserSiteMapPageObject userSiteMapPage;
	private UserWishlistPageObject userWishlistPage;

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
	public void User_03_Customer_Info() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}

	@Test
	public void User_04_Switch_Page() {
		addressPage = customerInfoPage.openAddressPage(driver);
		myProductPreviewsPage = addressPage.openMyProductPreviewsPage(driver);
		rewardPointsPage = myProductPreviewsPage.openRewardPointsPage(driver);
		addressPage = rewardPointsPage.openAddressPage(driver);
		rewardPointsPage = addressPage.openRewardPointsPage(driver);
		myProductPreviewsPage = rewardPointsPage.openMyProductPreviewsPage(driver);
		customerInfoPage = addressPage.openCustomerInfoPageOnHeader(driver);
	}

	@Test
	public void User_05_Exercise() {
		homePage = customerInfoPage.clickToLogoutLinkAtUserPage(driver);
		userSearchPage = homePage.openSearchPage(driver);
		userShippingAndReturnPage = userSearchPage.openShippingAndSearchPage(driver);
		userSiteMapPage = userShippingAndReturnPage.openSiteMapPage(driver);
		loginPage = userSiteMapPage.openLoginPageObject(driver);

		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		customerInfoPage = (UserCustomerInfoPageObject) loginPage.loginToSystemAfterSwitchFromOtherPages("customerInfo");

		homePage = customerInfoPage.clickToLogoutLinkAtUserPage(driver);
		userWishlistPage = homePage.openWishlistPage(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
