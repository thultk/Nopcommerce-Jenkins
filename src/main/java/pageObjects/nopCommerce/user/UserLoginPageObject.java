package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.LoginPageUI;

public class UserLoginPageObject extends BasePage {
	WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Login button")
	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public UserHomePageObject loginAsUser(String emailAddress, String password) {
		inputToEmailTextbox(emailAddress);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Step("Input value to Email textbox with value {0}")
	public void inputToEmailTextbox(String invalidEmailAddress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, invalidEmailAddress);
	}

	public String getErrorMessageUnsuccessful() {
		waitForElementVisible(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
	}

	@Step("Input value to Password textbox with value {0}")
	public void inputToPasswordTextbox(String validPassword) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, validPassword);
	}

	public Object loginToSystemAfterSwitchFromOtherPages(String pageName) {
		switch (pageName) {
		case "customerInfo":
			waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
			clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
			return PageGeneratorManager.getUserCustomerInfoPage(driver);

		case "sitemap":
			waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
			clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
			return PageGeneratorManager.getUserSiteMapPage(driver);
		default:
			throw new IllegalArgumentException("Unexpected value: " + pageName);
		}
	}

	public UserHomePageObject openHomePage() {
		waitForElementClickable(driver, LoginPageUI.NOPCOMMERCE_IMAGE);
		clickToElement(driver, LoginPageUI.NOPCOMMERCE_IMAGE);
		return PageGeneratorManager.getUserHomePage(driver);
	}
}
