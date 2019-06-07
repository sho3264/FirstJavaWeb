package com.cts.homeinsurance.model;

import java.sql.Date;

public class Homeowner {
	
	private int userId=0;
	
	private String firstName="",
			lastName="",
			ssn="",
			email="";
	
	private int retiredStatus=0;
	
	private Date dob=null;

	public Homeowner() {
		// TODO Auto-generated constructor stub
	}
	
	public Homeowner(String firstname,String lastname,String ssn, 
			String email, int retstat)
	{

		this.firstName=firstname;
		this.lastName=lastname;
		this.ssn=ssn;
		this.email=email;
		this.retiredStatus=retstat;
	}
	
	public Homeowner(int id, String firstname,String lastname,String ssn, 
			String email, int retstat, Date dob)
	{
		this.userId=id;
		this.firstName=firstname;
		this.lastName=lastname;
		this.ssn=ssn;
		this.email=email;
		this.retiredStatus=retstat;
		this.dob=dob;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRetiredStatus() {
		return retiredStatus;
	}

	public void setRetiredStatus(int i) {
		this.retiredStatus=i;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
