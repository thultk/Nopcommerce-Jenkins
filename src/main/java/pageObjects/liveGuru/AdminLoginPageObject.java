package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminHomePageObject loginAsAdmin(String adminUserName, String adminPassword) {
		waitForElementVisible(driver, AdminLoginPageUI.ADMIN_USERNAME_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.ADMIN_USERNAME_TEXTBOX, adminUserName);
		waitForElementVisible(driver, AdminLoginPageUI.ADMIN_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.ADMIN_PASSWORD_TEXTBOX, adminPassword);
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminHomePage(driver);
	}
}
