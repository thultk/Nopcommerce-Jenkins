package pageUIs.nopCommerce.admin;

public class ProductDetailPageUI {
	public static final String EDIT_PRODUCT_BUTTON = "//a[@class='btn btn-default']";
	public static final String ALT_ATTRIBUTE_TEXTBOX = "//input[@id='AddPictureModel_OverrideAltAttribute']";
	public static final String TITLE_TEXTBOX = "//input[@id='AddPictureModel_OverrideTitleAttribute']";
	public static final String DISPLAY_ORDER_ARROW = "//input[@id='AddPictureModel_DisplayOrder']//following-sibling::span//span[@title='%s']/span";
	public static final String ADD_PRODUCT_PICTURE_BUTTON = "//button[@id='addProductPicture']";
	public static final String PRODUCT_DETAIL_SAVE_BUTTON = "//button[@name='save']";
	public static final String UPLOADED_IMAGE_INFO = "//td[@data-columnname='PictureUrl']/a/img[contains(@src,'%s')]//ancestor::td//following-sibling::td[@data-columnname='DisplayOrder' and text()='%s']//following-sibling::td[@data-columnname='OverrideAltAttribute' and text()='%s']//following-sibling::td[@data-columnname='OverrideTitleAttribute' and text()='%s']";
	public static final String DELETE_BUTTON_BY_IMAGE_TITLE = "//td[text()='%s']//following-sibling::td/a[text()='Delete']";
	public static final String AVAILABLE_IMAGE_MESSAGE = "//table[@id='productpictures-grid']//td[text()='No data available in table']";
}
