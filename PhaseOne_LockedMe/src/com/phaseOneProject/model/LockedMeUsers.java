package com.phaseOneProject.model;

public class LockedMeUsers {
	
	private String userName;
	private String password;
	
	public LockedMeUsers(String userName, String password) {
		
		this.userName = userName;
		this.password = password;
	}

	public LockedMeUsers() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Users [username=" + userName + ", password=" + password + "]";
	}

}
