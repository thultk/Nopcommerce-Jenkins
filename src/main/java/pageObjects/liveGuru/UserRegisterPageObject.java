package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, UserRegisterPageUI.firstNameTextbox);
		sendKeyToElement(driver, UserRegisterPageUI.firstNameTextbox, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, UserRegisterPageUI.lastNameTextbox);
		sendKeyToElement(driver, UserRegisterPageUI.lastNameTextbox, lastName);

	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, UserRegisterPageUI.emailAddressTextbox);
		sendKeyToElement(driver, UserRegisterPageUI.emailAddressTextbox, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.passwordTextbox);
		sendKeyToElement(driver, UserRegisterPageUI.passwordTextbox, password);
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, UserRegisterPageUI.confirmPasswordTextbox);
		sendKeyToElement(driver, UserRegisterPageUI.confirmPasswordTextbox, confirmPassword);
	}

	public UserMyDashboardPageObject clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.registerButton);
		clickToElement(driver, UserRegisterPageUI.registerButton);
		return new UserMyDashboardPageObject(driver);
	}
}
