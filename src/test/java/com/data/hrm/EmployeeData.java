package com.data.hrm;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class EmployeeData {
	public static EmployeeData getEmployee() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + "\\testdata\\com\\hrm\\data\\Employee.json"), EmployeeData.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("fullname")
	private String fullname;

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	@JsonProperty("statusValue")
	private String statusValue;

	@JsonProperty("PersonalDetail")
	private PersonalDetail personalDetail;

	public class PersonalDetail {
		@JsonProperty("editFirstName")
		private String editFirstName;

		@JsonProperty("editLastName")
		private String editLastName;

		@JsonProperty("editGender")
		private String editGender;

		@JsonProperty("editMartialStatus")
		private String editMartialStatus;

		@JsonProperty("editNationality")
		private String editNationality;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the status value
	 */
	public String getStatusValue() {
		return statusValue;
	}

	public String getEditFirstName() {
		return personalDetail.editFirstName;
	}

	public String getEditLastName() {
		return personalDetail.editLastName;
	}

	public String getEditGender() {
		return personalDetail.editGender;
	}

	public String getEditMartialStatus() {
		return personalDetail.editMartialStatus;
	}

	public String getEditNationality() {
		return personalDetail.editNationality;
	}
}
