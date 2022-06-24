package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.hrm.MyInfoPageUI;

public class MyInfoPO extends BasePage {
	WebDriver driver;

	public MyInfoPO(WebDriver driver) {
		this.driver = driver;
	}

	public EmployeeListPO openEmployeeListPage() {
		return null;
	}

	public void clickToChangePhotoImage() {
		waitForElementVisible(driver, MyInfoPageUI.AVATAR_IMAGE);
		clickToElement(driver, MyInfoPageUI.AVATAR_IMAGE);
	}

	public boolean isAvatarImageDisplayed() {
		waitForElementVisible(driver, MyInfoPageUI.AVATAR_IMAGE);
		int imageWidth = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.AVATAR_IMAGE, "width"));
		int imageHeight = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.AVATAR_IMAGE, "height"));
		return (imageWidth != 200 || imageHeight != 200);
	}

	public void openTabAtSidebarByName(String tabName) {
		waitForElementClickable(driver, MyInfoPageUI.TAB_LINK_BY_NAME, tabName);
		clickToElement(driver, MyInfoPageUI.TAB_LINK_BY_NAME, tabName);
	}
}
