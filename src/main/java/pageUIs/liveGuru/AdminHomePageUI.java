package pageUIs.liveGuru;

public class AdminHomePageUI {
	public static final String TEXTBOX_BY_HEADER_NAME = "//tr[@class='headings']//span[text()='%s']//ancestor::tr//following-sibling::tr//*[@name='%s']";
	public static final String COLUMN_INDEX_BY_HEADER = "//tr[@class='headings']//span[@class='sort-title' and text()='%s']//ancestor::th//preceding-sibling::th";
	public static final String DATA_VALUE_BY_ROW = "//tbody//tr[@class='even'][%s]//td[%s]";
}
