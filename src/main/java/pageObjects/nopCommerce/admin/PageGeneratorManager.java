package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	private static AdminLoginPageObject adminLoginPage;
	private static DashboardPageObject dashboardPage;
	private static ProductSearchPageObject productSearchPage;
	private static ProductDetailPageObject productDetailPage;
	private static AddNewProductPageObject addNewProductPage;

	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		if (adminLoginPage == null) {
			adminLoginPage = new AdminLoginPageObject(driver);
		}
		return adminLoginPage;
	}

	public static DashboardPageObject getAdminDashboardPage(WebDriver driver) {
		if (dashboardPage == null) {
			dashboardPage = new DashboardPageObject(driver);
		}
		return dashboardPage;
	}

	public static ProductSearchPageObject getProductSearchPage(WebDriver driver) {
		if (productSearchPage == null) {
			productSearchPage = new ProductSearchPageObject(driver);
		}
		return productSearchPage;
	}

	public static ProductDetailPageObject getProductDetailPage(WebDriver driver) {
		if (productDetailPage == null) {
			productDetailPage = new ProductDetailPageObject(driver);
		}
		return productDetailPage;
	}

	public static AddNewProductPageObject getAddNewProductPage(WebDriver driver) {
		if (addNewProductPage == null) {
			addNewProductPage = new AddNewProductPageObject(driver);
		}
		return addNewProductPage;
	}
}
