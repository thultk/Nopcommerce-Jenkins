package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserWishlistPageObject extends BasePage {
	WebDriver driver;

	public UserWishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
