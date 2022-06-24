package pageObjects.liveGuru;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.AdminHomePageUI;

public class AdminHomePageObject extends BasePage {
	private WebDriver driver;

	public AdminHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToHeaderTextboxByName(String headerName, String dataInputItem, String emailAddress) {
		waitForElementVisible(driver, AdminHomePageUI.TEXTBOX_BY_HEADER_NAME, headerName, dataInputItem);
		sendKeyToElement(driver, AdminHomePageUI.TEXTBOX_BY_HEADER_NAME, emailAddress, headerName, dataInputItem);
		pressKey(driver, AdminHomePageUI.TEXTBOX_BY_HEADER_NAME, Keys.ENTER, headerName, dataInputItem);
	}

	public String getUserRegisteredInfo(String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, AdminHomePageUI.COLUMN_INDEX_BY_HEADER, headerName) + 1;
		return getElementText(driver, AdminHomePageUI.DATA_VALUE_BY_ROW, rowIndex, String.valueOf(columnIndex));
	}
}
