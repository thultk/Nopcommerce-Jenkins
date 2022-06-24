package com.bankguru.payment;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import environmentConfig.Environment;

public class Level_22_Multiple_Environment extends BaseTest {
	Environment environment;

	@Parameters({ "serverName", "browser" })
	@BeforeClass
	public void beforeClass(String serverName, String browserName) {
		// String prefixPropertiesName = System.getProperty("envMaven");
		ConfigFactory.setProperty("env", serverName);
		environment = ConfigFactory.create(Environment.class);
		log.info("Pre-Condition - Step 01: Open browser" + browserName);
		driver = getBrowserName(browserName, environment.appUrl());
		// driver = getBrowserName(browserName, url);
	}

	// @Test
	public void Employee_01_Add_New_Employee() {
	}

	// @Test
	public void Employee_02_Upload_Avatar() {
	}

	// @Test
	public void Employee_03_Personal_Details() {
	}

	// @Test
	public void Employee_04_Contact_Details() {
	}

	@Test
	public void Employee_05_Emergency_Details() {
	}

	@Test
	public void Employee_06_Assigned_Dependents() {

	}

	@Test
	public void Employee_07_Edit_View_Job() {

	}

	@Test
	public void Employee_08_Edit_View_Salary() {

	}

	@Test
	public void Employee_09_Edit_View_Tax() {

	}

	@Test
	public void Employee_10_Qualifications() {

	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-Condition: Close browser" + browserName);
		closeBrowserAndDriver();
	}

	WebDriver driver;
}
