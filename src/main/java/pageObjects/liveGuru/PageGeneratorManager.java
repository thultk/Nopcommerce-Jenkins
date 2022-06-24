package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static LiveGuruUserHomePageObject getUserHomePage(WebDriver driver) {
		return new LiveGuruUserHomePageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static UserMyDashboardPageObject getUserMyDashboardPage(WebDriver driver) {
		return new UserMyDashboardPageObject(driver);
	}

	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminHomePageObject getAdminHomePage(WebDriver driver) {
		return new AdminHomePageObject(driver);
	}
}
