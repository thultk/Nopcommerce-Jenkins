package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject clickToCreateAnAccountLink() {
		waitForElementClickable(driver, UserLoginPageUI.CREATE_ACCOUNT_LINK);
		clickToElement(driver, UserLoginPageUI.CREATE_ACCOUNT_LINK);
		return new UserRegisterPageObject(driver);
	}

	public void inputToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendKeyToElement(driver, UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public UserMyDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return new UserMyDashboardPageObject(driver);
	}
}
