package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminBasePageUI;
import pageUIs.nopCommerce.admin.ProductDetailPageUI;
import pageUIs.nopCommerce.admin.ProductSearchPageUI;

public class ProductSearchPageObject extends BasePage {
	WebDriver driver;

	public ProductSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void searchByProductName(String productName) {
		waitForElementVisible(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX);
		if (driver.toString().contains("firefox")) {
			sleepInSecond(5);
		}
		getWebElement(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX).sendKeys(productName);
		waitForElementClickable(driver, ProductSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, ProductSearchPageUI.SEARCH_BUTTON);
		if (driver.toString().contains("firefox")) {
			sleepInSecond(5);
		}
	}

	public ProductDetailPageObject openProductDetailPage() {
		waitForElementClickable(driver, ProductDetailPageUI.EDIT_PRODUCT_BUTTON);
		clickToElement(driver, ProductDetailPageUI.EDIT_PRODUCT_BUTTON);
		return PageGeneratorManager.getProductDetailPage(driver);
	}

	public boolean isEditProductSuccessMessageDisplayed() {
		return isElementDisplayed(driver, ProductSearchPageUI.EDIT_PRODUCT_SUCCESS_MESSAGE);
	}

	public boolean isUpdatedProductDisplayed(String productImageName, String productName) {
		waitForElementVisible(driver, ProductSearchPageUI.UPDATED_PRODUCT_INFO, productImageName.replace(" ", "-").toLowerCase(), productName);
		return isElementDisplayed(driver, ProductSearchPageUI.UPDATED_PRODUCT_INFO, productImageName.replace(" ", "-").toLowerCase(), productName);
	}
}
