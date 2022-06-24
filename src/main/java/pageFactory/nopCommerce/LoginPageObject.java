package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory {
	WebDriver driver;

	@FindBy(xpath = "//button[contains(@class,'login-button')]")
	WebElement loginButton;

	@FindBy(id = "Email-error")
	WebElement emailErrorMessage;

	@FindBy(xpath = "//div[contains(@class,'message-error')]")
	WebElement unsuccessfulErrorMessage;

	@FindBy(id = "Email")
	WebElement emailTextbox;

	@FindBy(id = "Password")
	WebElement passwordTextbox;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public void inputToEmailTextbox(String invalidEmailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(driver, emailTextbox, invalidEmailAddress);
	}

	public String getErrorMessageUnsuccessful() {
		waitForElementVisible(driver, unsuccessfulErrorMessage);
		return getElementText(driver, unsuccessfulErrorMessage);
	}

	public void inputToPasswordTextbox(String validPassword) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, validPassword);
	}
}
