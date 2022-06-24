package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AddNewProductPageUI;

public class AddNewProductPageObject extends BasePage {
	WebDriver driver;

	public AddNewProductPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
