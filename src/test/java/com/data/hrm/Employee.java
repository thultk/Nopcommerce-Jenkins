package com.data.hrm;

public class Employee {
	public static Employee getEmployee() {
		return new Employee();
	}

	public class Role {
		public static final String ADMIN_USERNAME = "Admin";
		public static final String ADMIN_PASSWORD = "admin123";
	}

	public class EmployeeInfo {
		public static final String FIRSTNAME = "John";
		public static final String LASTNAME = "Lennol";
		public static final String FULLNAME = FIRSTNAME + " " + LASTNAME;
		public static final String USERNAME = "johnlennol";
		public static final String PASSWORD = "john1234";
		public static final String STATUS_VALUE = "Enabled";
	}

	public class PersonalDetail {
		public static final String FIRSTNAME = "Angelina";
		public static final String LASTNAME = "Jolie";
		public static final String GENDER = "Female";
		public static final String MARITAL_STATUS = "single";
		public static final String NATIONALITY = "Vietnamese";
	}

	public class ContactDetail {
		public static final String ADDRESS_STREET = "9547 Belmont Rd #23";
	}
}
