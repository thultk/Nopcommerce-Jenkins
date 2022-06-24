package pageObjects.facebook;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.facebook.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

//	public boolean isEmailTextboxDisplayed() {
//		waitForElementClickable(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
//		return isElementDisplayed(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
//	}

	public boolean isEmailTextboxDisplayed() {
		waitForElementClickable(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
		return isElementUndisplayed(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
	}

//	public boolean isConfirmEmailTextboxDisplayed() {
//		return isElementDisplayed(driver, RegisterPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
//	}

	public boolean isConfirmEmailTextboxDisplayed() {
		return isElementUndisplayed(driver, RegisterPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
		sleepInSecond(5);
	}

//	public boolean isLoginButtonDisplayed() {
//		return isElementDisplayed(driver, RegisterPageUI.LOGIN_BUTTON);
//	}

	public boolean isLoginButtonDisplayed() {
		return isElementUndisplayed(driver, RegisterPageUI.LOGIN_BUTTON);
	}
}
