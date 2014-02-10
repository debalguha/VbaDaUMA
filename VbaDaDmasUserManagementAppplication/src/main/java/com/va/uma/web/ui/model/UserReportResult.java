package com.va.uma.web.ui.model;

public class UserReportResult {
	private final String firstName;
	private final String lastName;
	private final String userName;
	private final String status;
	private final String phone;
	private final String teamName;
	public UserReportResult(String firstName, String lastName, String userName,
			String status, String phone, String teamName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.status = status;
		this.phone = phone;
		this.teamName = teamName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getUserName() {
		return userName;
	}
	public String getStatus() {
		return status;
	}
	public String getPhone() {
		return phone;
	}
	public String getTeamName() {
		return teamName;
	}
	
}
