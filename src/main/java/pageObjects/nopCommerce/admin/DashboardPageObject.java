package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.DashboardPageUI;

public class DashboardPageObject extends BasePage {
	WebDriver driver;

	public DashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardHeaderDisplayed() {
		waitForElementVisible(driver, DashboardPageUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver, DashboardPageUI.DASHBOARD_HEADER);
	}
}
