package com.hrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.MyInfoPO;
import pageObjects.hrm.PageGenerator;
import utilities.DataUtil;

public class Level_21_Data_Test_II_TestNG_XML extends BaseTest {
	String employeeID;
	String avatarFilePath;
	String editAddressStreet1, emegencyContactName, emegencyRelationship, emegencyHomeTelephone;

	@Parameters({ "browser", "url", "adminUserName", "adminPassword" })
	@BeforeClass
	public void beforeClass(String browserName, String url, String adminUserName, String adminPassword) {
		log.info("Pre-Condition - Step 01: Open browser" + browserName);
		driver = getBrowserName(browserName, url);
		loginPage = PageGenerator.getLoginPage(driver);

		avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "Rose.png";
		editAddressStreet1 = "9547 Belmont Rd #22";
		emegencyContactName = "John Smith";
		emegencyRelationship = "brother";
		emegencyHomeTelephone = "012345678";
		log.info("Pre-Condition - Step 02: Login with Admin role");
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
	}

	@Parameters({ "firstname", "lastname", "fullname", "username", "password", "statusValue" })
	@Test
	public void Employee_01_Add_New_Employee(String empFirstName, String empLastName, String empFullName, String empUserName, String empPassword, String statusValue) {
		log.info("Add_New_01 - Step 01: Open 'Employee List' page");
		dashboardPage.openSubMenuPageHRM(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Add_New_01 - Step 02: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "firstName", empFirstName);

		log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "lastName", empLastName);

		log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboValueByID(driver, "employeeId");

		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_name", empUserName);

		log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_password", empPassword);

		log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "re_password", empPassword);

		log.info("Add_New_01 - Step 10: Select '" + statusValue + "' value in 'Status' dropdown");
		addEmployeePage.selectDropdownByID(driver, "status", statusValue);
		;

		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Add_New_01 - Step 12: Open 'Employee List' page");
		myInfoPage.openSubMenuPageHRM(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		verifyTrue(employeeListPage.isJQueryLoadedSuccess(driver));

		log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
		employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", empFullName);
		verifyTrue(employeeListPage.isJQueryLoadedSuccess(driver));

		log.info("Add_New_01 - Step 14: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryLoadedSuccess(driver));

		log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result table'");
		verifyEquals(employeeListPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), empLastName);
	}

	@Parameters({ "firstname", "password" })
	public void Employee_02_Upload_Avatar(String empFirstName, String empPassword) {
		log.info("Upload_Avatar_02 - Step 01: Login with Employee role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, empFirstName, empPassword);

		log.info("Upload_Avatar_02 - Step 02: Open Personal Detail page");
		dashboardPage.openMenuPageHRM(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Upload_Avatar_02 - Step 03: Click to Change Photo image");
		myInfoPage.clickToChangePhotoImage();

		log.info("Upload_Avatar_02 - Step 04: Upload new avatar image");
		myInfoPage.uploadImage(driver, avatarFilePath);

		log.info("Upload_Avatar_02 - Step 05: Click to Upload button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Upload_Avatar_02 - Step 06: Verify success message displayed ");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));

		log.info("Upload_Avatar_02 - Step 07: Verify new avatar image displayed ");
		verifyTrue(myInfoPage.isAvatarImageDisplayed());
	}

	@Parameters({ "editFirstname", "editLastname", "editGender", "editMaritalStatus", "editNationality" })
	public void Employee_03_Personal_Details(String editEmpFirstName, String editEmpLastName, String editEmpGender, String editEmpMaritalStatus, String editEmpNationality) {
		log.info("Personal_Details_03 - Step 01: Click to Personal Details tab at side bar");
		myInfoPage.openTabAtSidebarByName("Personal Details");

		log.info("Personal_Details_03 - Step 02: Verify all fields at 'Personal Detals' tab are disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtLicenNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtSINNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_optGender_2"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_cmbNation"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_DOB"));

		log.info("Personal_Details_03 - Step 03: Click to 'Edit' button at 'Personal Detals' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		myInfoPage.sleepInSecond(10);

		log.info("Personal_Details_03 - Step 04: Verify 'Employee ID' field is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmployeeId"));

		log.info("Personal_Details_03 - Step 05: Verify 'Driver's License Number' field is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtLicenNo"));

		log.info("Personal_Details_03 - Step 06: Verify 'SSN Number' field is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtNICNo"));

		log.info("Personal_Details_03 - Step 07: Verify 'SIN Number' field is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtSINNo"));

		log.info("Personal_Details_03 - Step 08: Verify 'Date of Birth' field is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_DOB"));

		log.info("Personal_Details_03 - Step 09: Enter new value to 'First Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpFirstName", editEmpFirstName);

		log.info("Personal_Details_03 - Step 10: Enter new value to 'Last Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpLastName", editEmpLastName);

		log.info("Personal_Details_03 - Step 11: Select new value to 'Gender' radio button");
		myInfoPage.clickToRadioByLabel(driver, editEmpGender);

		log.info("Personal_Details_03 - Step 12: Select new value to 'Marital Status' dropdown");
		myInfoPage.selectDropdownByName(driver, "personal[cmbMarital]", editEmpMaritalStatus);

		log.info("Personal_Details_03 - Step 13: Select new value to 'Nationality' dropdown");
		myInfoPage.selectDropdownByName(driver, "personal[cmbNation]", editEmpNationality);

		log.info("Personal_Details_03 - Step 14: Click to 'Save' button in 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Personal_Details_03 - Step 15: Verify success message displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Personal_Details_03 - Step 16: Verify 'First Name' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboValueByID(driver, "personal_txtEmpFirstName"), editEmpFirstName);

		log.info("Personal_Details_03 - Step 17: Verify 'Last Name' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboValueByID(driver, "personal_txtEmpLastName"), editEmpLastName);

		log.info("Personal_Details_03 - Step 18: Verify 'Gender' radio button is updated successfully");
		verifyTrue(myInfoPage.isRadioButtonSelected(driver, editEmpGender));

		log.info("Personal_Details_03 - Step 19: Verify 'Marital Status' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbMarital"), editEmpMaritalStatus);

		log.info("Personal_Details_03 - Step 20: Verify 'Nationality' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbNation"), editEmpNationality);

		log.info("Personal_Details_03 - Step 21: Verify 'Employee Id' value is correct");
		verifyEquals(myInfoPage.getTextboValueByID(driver, "personal_txtEmployeeId"), employeeID);
	}

	public void Employee_04_Contact_Details() {
		log.info("Contact_Details_04 - Step 01: Click to 'Contact Details' tab at side bar");
		myInfoPage.openTabAtSidebarByName("Contact Details");

		log.info("Contact_Details_04 - Step 02: Verify all fields in 'Contact Details' form are disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_street1"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_street2"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_city"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_province"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_country"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_hm_telephone"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_mobile"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_work_telephone"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_work_email"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_oth_email"));

		log.info("Contact_Details_04 - Step 03: Click to 'Edit' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact_Details_04 - Step 04: Enter new value to 'Address Street 1' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_street1", editAddressStreet1);

		log.info("Contact_Details_04 - Step 05: Click to 'Save' button in 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact_Details_04 - Step 06: Verify success message displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Contact_Details_04 - Step 07: Verify 'Address Street 1' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboValueByID(driver, "contact_street1"), editAddressStreet1);

	}

	public void Employee_05_Emergency_Details() {
		log.info("Emergency_Details_05 - Step 01: Click to 'Emergency Details' tab at side bar");
		myInfoPage.openTabAtSidebarByName("Emergency Contacts");

		log.info("Emergency_Details_05 - Step 02: Verify 'No Records Found' displayed in 'Assigned Emergency Contacts' form");
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"), "No Records Found");

		log.info("Emergency_Details_05 - Step 03: Click to 'Add' button in 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");

		log.info("Emergency_Details_05 - Step 04: Enter emegency contact name to 'Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_name", emegencyContactName);

		log.info("Emergency_Details_05 - Step 05: Enter emegency relationship to 'Relationship' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_relationship", emegencyRelationship);

		log.info("Emergency_Details_05 - Step 06: Enter emegency home telephone to 'Home Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_homePhone", emegencyHomeTelephone);

		log.info("Emergency_Details_05 - Step 07: Cick To 'Save' button in 'Add Emergency Contact' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");

		log.info("Emergency_Details_05 - Step 08: Verify emegency information displayed corectly");
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"), emegencyContactName);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"), emegencyRelationship);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "1"), emegencyHomeTelephone);
	}

	public void Employee_06_Assigned_Dependents() {

	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-Condition: Close browser" + browserName);
		closeBrowserAndDriver();
	}

	WebDriver driver;
	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	EmployeeListPO employeeListPage;
	DashboardPO dashboardPage;
	MyInfoPO myInfoPage;
}
