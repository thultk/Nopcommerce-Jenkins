package pageUIs.nopCommerce.admin;

public class ProductSearchPageUI {
	public static final String PRODUCT_NAME_TEXTBOX = "//input[@id='SearchProductName']";
	public static final String ADD_NEW_PRODUCT_SUCCESS_MESSAGE = "//div[@class='alert alert-success alert-dismissable']";
	public static final String SEARCH_BUTTON = "//button[@id='search-products']";
	public static final String EDIT_PRODUCT_SUCCESS_MESSAGE = "//div[contains(@class,'alert alert-success') and contains(string(),'The product has been updated successfully.')]";
	public static final String UPDATED_PRODUCT_INFO = "//img[contains(@src,'%s')]//parent::td//following-sibling::td[text()='%s']";
}
