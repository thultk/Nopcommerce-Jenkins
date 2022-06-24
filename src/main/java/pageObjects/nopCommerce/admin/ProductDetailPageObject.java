package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;
import pageUIs.nopCommerce.admin.ProductDetailPageUI;

public class ProductDetailPageObject extends BasePage {
	WebDriver driver;

	public ProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToAltAttributeTextbox(String altAttribute) {
		waitForElementVisible(driver, ProductDetailPageUI.ALT_ATTRIBUTE_TEXTBOX);
		sendKeyToElement(driver, ProductDetailPageUI.ALT_ATTRIBUTE_TEXTBOX, altAttribute);
	}

	public void inputToTitleTextbox(String pictureTitle) {
		waitForElementVisible(driver, ProductDetailPageUI.TITLE_TEXTBOX);
		sendKeyToElement(driver, ProductDetailPageUI.TITLE_TEXTBOX, pictureTitle);
	}

	public void selectDisplayOrder(String value) {
		waitForElementClickable(driver, ProductDetailPageUI.DISPLAY_ORDER_ARROW, value);
		clickToElement(driver, ProductDetailPageUI.DISPLAY_ORDER_ARROW, value);
	}

	public void clickToAddProductPictureButton() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_PRODUCT_PICTURE_BUTTON);
		clickToElement(driver, ProductDetailPageUI.ADD_PRODUCT_PICTURE_BUTTON);
	}

	public ProductSearchPageObject clickToSaveButton() {
		waitForElementClickable(driver, ProductDetailPageUI.PRODUCT_DETAIL_SAVE_BUTTON);
		clickToElement(driver, ProductDetailPageUI.PRODUCT_DETAIL_SAVE_BUTTON);
		return PageGeneratorManager.getProductSearchPage(driver);
	}

	public void inputToDisplayOrder(String value) {
		waitForElementVisible(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE);
		clickToElement(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE);
		sendKeyToElement(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, value);
	}

	public boolean isUploadedFileDisplayedSuccessfully(String productName, String displayOrder, String alt, String title) {
		waitForElementVisible(driver, ProductDetailPageUI.UPLOADED_IMAGE_INFO, productName.replace(" ", "-").toLowerCase(), displayOrder, alt, title);
		return isElementDisplayed(driver, ProductDetailPageUI.UPLOADED_IMAGE_INFO, productName.replace(" ", "-").toLowerCase(), displayOrder, alt, title);
	}

	public void clickToDeleteButton(String pictureTitle) {
		waitForElementClickable(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, pictureTitle);
		clickToElement(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, pictureTitle);
		acceptAlert(driver);
		sleepInSecond(4);
	}

	public boolean isNoDataAvailableMessageDisplayed() {
		waitForElementVisible(driver, ProductDetailPageUI.AVAILABLE_IMAGE_MESSAGE);
		return isElementDisplayed(driver, ProductDetailPageUI.AVAILABLE_IMAGE_MESSAGE);
	}
}
