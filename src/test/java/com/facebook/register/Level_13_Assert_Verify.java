package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.PageGeneratorManager;
import pageObjects.facebook.RegisterPageObject;

public class Level_13_Assert_Verify extends BaseTest {
	private WebDriver driver;
	private String emailAddress = "test" + generateRandomNumber() + "@gmail.com";
	private RegisterPageObject registerPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserName(browserName, url);
		registerPage = PageGeneratorManager.getRegisterPage(driver);
	}

	@Test
	public void Register_01_Verify() {
		verifyTrue(registerPage.isEmailTextboxDisplayed());
		registerPage.inputToEmailTextbox(emailAddress);
		verifyTrue(registerPage.isConfirmEmailTextboxDisplayed());
		registerPage.inputToEmailTextbox(" ");
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
