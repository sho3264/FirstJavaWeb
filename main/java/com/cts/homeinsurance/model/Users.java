package com.cts.homeinsurance.model;

public class Users {
	// first column
	private int userId=0;
	// second column
	private String userName="",
			// third column
			password="",
			// 
			adminRole="user";
	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	public Users(String username,String password)
	{
		this.userName=username;
		this.password=password;
	}
	
	public Users(int id,String username, String password, String role)
	{
		this.userId=id;
		this.userName=username;
		this.password=password;
		this.adminRole=role;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(String adminRole) {
			this.adminRole = adminRole;

	}

}
