package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.UserHomePageUI;

public class LiveGuruUserHomePageObject extends BasePage {
	private WebDriver driver;

	public LiveGuruUserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserLoginPageObject clickToMyAccountLink() {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, UserHomePageUI.MY_ACCOUNT_LINK);
			sleepInSecond(3);
		} else {
			clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		}
		return new UserLoginPageObject(driver);
	}
}
