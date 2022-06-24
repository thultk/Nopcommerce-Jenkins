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

public class Level_12_Element_Undisplayed extends BaseTest {
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
	public void Register_01_Element_Displayed() {
		Assert.assertFalse(registerPage.isEmailTextboxDisplayed());
		registerPage.inputToEmailTextbox(emailAddress);
		Assert.assertFalse(registerPage.isConfirmEmailTextboxDisplayed());
	}

	@Test
	public void Register_02_Element_Undisplayed_In_DOM() {
		registerPage.inputToEmailTextbox(" ");
		Assert.assertTrue(registerPage.isConfirmEmailTextboxDisplayed());
	}

	@Test
	public void Register_03_Element_Undisplayed_Not_In_DOM() {
		Assert.assertTrue(registerPage.isLoginButtonDisplayed());
	}

//	@Test
//	public void Register_01_Element_Displayed() {
//		Assert.assertTrue(registerPage.isEmailTextboxDisplayed());
//		registerPage.inputToEmailTextbox(emailAddress);
//		Assert.assertTrue(registerPage.isConfirmEmailTextboxDisplayed());
//	}
//
//	@Test
//	public void Register_02_Element_Undisplayed_In_DOM() {
//		registerPage.inputToEmailTextbox(" ");
//		Assert.assertFalse(registerPage.isConfirmEmailTextboxDisplayed());
//	}
//
//	@Test
//	public void Register_03_Element_Undisplayed_Not_In_DOM() {
//		Assert.assertFalse(registerPage.isLoginButtonDisplayed());
//	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
