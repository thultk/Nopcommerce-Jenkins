package pageUIs.nopCommerce.admin;

public class AdminBasePageUI {
	public static final String DASHBOARD_MENU_BY_NAME = "//ul[@role='menu']/li/a/p[contains(text(),'%s')]";
	public static final String DASHBOARD_SUB_MENU_BY_NAME = "//ul[@class='nav nav-treeview' and @style]//p[contains(text(),'%s')]";
	// public static final String DASHBOARD_SUB_MENU_BY_NAME = "//li[@class='nav-item has-treeview menu-open']//p[contains(text(),'%s')]";
	public static final String ADD_NEW_ITEM = "//h1[contains(string(),'%s')]//following-sibling::div/a";
	public static final String ITEM_NAME_TEXTBOX = "//input[@id='Name']";
	public static final String NEW_ITEM_SAVE_BUTTON = "//button[@name='save']";
	public static final String TOGGLE_ICON_BY_CARD_NAME = "//div[contains(@data-card-name,'%s')]//i[contains(@class,'toggle-icon')]";
	public static final String UPLOAD_FILE_BUTTON_BY_CARD_NAME = "//div[@data-card-name='%s']//input[@type='file']";
	public static final String UPLOADED_FILE_TITLE = "//span[@class='qq-upload-file-selector qq-upload-file' and text()='%s']";
}
