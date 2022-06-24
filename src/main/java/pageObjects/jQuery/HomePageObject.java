package pageObjects.jQuery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPageByNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUI.PAGING_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGING_BY_NUMBER, pageNumber);
	}

	public boolean isPageActiveByNumber(String pageNumber) {
		return isElementDisplayed(driver, HomePageUI.PAGING_BY_NUMBER_ACTIVE, pageNumber);
	}

	public void inputToHeaderTextboxByName(String headerName, String value) {
		waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_NAME, headerName);
		sendKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_NAME, value, headerName);
		pressKey(driver, HomePageUI.HEADER_TEXTBOX_BY_NAME, Keys.ENTER, headerName);
	}

	public void clickToIconByCountryName(String country, String iconName) {
		waitForElementClickable(driver, HomePageUI.ICON_BY_COUNTRY_NAME, country, iconName);
		clickToElement(driver, HomePageUI.ICON_BY_COUNTRY_NAME, country, iconName);
	}

	public boolean isDataValueByRowDisplayed(String females, String country, String males, String total) {
		return isElementDisplayed(driver, HomePageUI.DATA_VALUE_BY_ROW, females, country, males, total);
	}

	public void inputToCellByColumnNameAndCellIndex(String columnName, String rowIndex, String value) {
		int columnIndex = getElementSize(driver, HomePageUI.HEADER_NAME_INDEX, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.TEXTBOX_BY_ROW_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
		if (columnName.equals("Member Since")) {
			removeAttributeInDOM(driver, "type", HomePageUI.TEXTBOX_BY_ROW_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
			sendKeyToElement(driver, HomePageUI.TEXTBOX_BY_ROW_COLUMN_INDEX, value, rowIndex, String.valueOf(columnIndex));
		} else {
			sendKeyToElement(driver, HomePageUI.TEXTBOX_BY_ROW_COLUMN_INDEX, value, rowIndex, String.valueOf(columnIndex));
		}
	}

	public void clickToButtonAtRow(String rowIndex, String buttonName) {
		waitForElementClickable(driver, HomePageUI.BUTTON_BY_ROW_INDEX, rowIndex, buttonName);
		clickToElement(driver, HomePageUI.BUTTON_BY_ROW_INDEX, rowIndex, buttonName);
	}
}
