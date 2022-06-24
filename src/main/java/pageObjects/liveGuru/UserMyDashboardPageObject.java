package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.UserMyDashboardPageUI;

public class UserMyDashboardPageObject extends BasePage {
	private WebDriver driver;

	public UserMyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getSuccessfulRegisterMessage() {
		waitForElementVisible(driver, UserMyDashboardPageUI.SUCCESS_REGISTER_MESSAGE);
		return getElementText(driver, UserMyDashboardPageUI.SUCCESS_REGISTER_MESSAGE);
	}

	public String getCorrectAccountInformation() {
		waitForElementVisible(driver, UserMyDashboardPageUI.CONTACT_LABEL);
		return getElementText(driver, UserMyDashboardPageUI.CONTACT_LABEL);
	}

	public String getSuccessLoggedInFirstName() {
		waitForElementVisible(driver, UserMyDashboardPageUI.FIRST_NAME_LABEL);
		return getElementText(driver, UserMyDashboardPageUI.FIRST_NAME_LABEL);
	}
}
