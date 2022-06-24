package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.admin.AddNewProductPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.admin.DashboardPageObject;
import pageObjects.nopCommerce.admin.PageGeneratorManager;
import pageObjects.nopCommerce.admin.ProductDetailPageObject;
import pageObjects.nopCommerce.admin.ProductSearchPageObject;

public class Level_11_Admin_Upload_File extends BaseTest {
	private WebDriver driver;
	private String adminEmailAddress;
	private String adminPassword;
	private AdminLoginPageObject adminLoginPage;
	private DashboardPageObject dashboardPage;
	private ProductSearchPageObject productSearchPage;
	private AddNewProductPageObject addNewProductPage;
	private ProductDetailPageObject productDetailPage;
	private String productName;
	private String firstFileName;
	private String pictureAltAttribute;
	private String pictureTitle;
	private String pictureDisplayOrder;
	private String defaultImage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		productName = "Samsung Galaxy J5";
		firstFileName = "Rainbow.png";
		pictureAltAttribute = "test";
		pictureTitle = "Title";
		pictureDisplayOrder = "1";
		defaultImage = "default image";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
		driver = getBrowserName(browserName);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		dashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		dashboardPage.openSubMenuPage(driver, "Catalog", "Products");
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);
		productSearchPage.openAddNewItemPage(driver, "Products");
		addNewProductPage = PageGeneratorManager.getAddNewProductPage(driver);
		addNewProductPage.addNewItem(driver, productName);
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);
		productSearchPage.searchByProductName(productName);
		productDetailPage = productSearchPage.openProductDetailPage();
		productDetailPage.expandPanelByCardName(driver, "product-picture");

	}

	@Test
	public void Admin_01_Upload_File() {
		productDetailPage.uploadMultipleFiles(driver, "product-pictures", firstFileName);
		Assert.assertTrue(productDetailPage.isUploadedFileDisplayed(driver, firstFileName));
		productDetailPage.inputToAltAttributeTextbox(pictureAltAttribute);
		productDetailPage.inputToTitleTextbox(pictureTitle);
		productDetailPage.selectDisplayOrder("Increase value");
		productDetailPage.clickToAddProductPictureButton();
		Assert.assertTrue(productDetailPage.isUploadedFileDisplayedSuccessfully(productName, pictureDisplayOrder, pictureAltAttribute, pictureTitle));
		productSearchPage = productDetailPage.clickToSaveButton();
		Assert.assertTrue(productSearchPage.isEditProductSuccessMessageDisplayed());
		productSearchPage.searchByProductName(productName);
		Assert.assertTrue(productSearchPage.isUpdatedProductDisplayed(productName, productName));
		productDetailPage = productSearchPage.openProductDetailPage();
		productDetailPage.expandPanelByCardName(driver, "product-picture");
		productDetailPage.clickToDeleteButton(pictureTitle);
		Assert.assertTrue(productDetailPage.isNoDataAvailableMessageDisplayed());
		productSearchPage = productDetailPage.clickToSaveButton();
		Assert.assertTrue(productSearchPage.isEditProductSuccessMessageDisplayed());
		productSearchPage.searchByProductName(productName);
		Assert.assertTrue(productSearchPage.isUpdatedProductDisplayed(defaultImage, productName));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
