package com.cts.homeinsurance.model;

public class Location {
	private int locationId=0,
			userId=0;
	private String residenceType="",
			addressLine1="",
			addressLine2="",
			city="",
			locationState="",
			zipCode="",
			residenceUse="";
	public Location() {
		// TODO Auto-generated constructor stub
	}
	
	public Location( String resType, String add1, String add2,
			String city, String locState, String zip,String resUse)
	{
		
		this.residenceType=resType;
		this.addressLine1=add1;
		this.addressLine2=add2;
		this.city = city;
		this.locationState=locState;
		this.zipCode = zip;
		this.residenceUse = resUse;
	}
	
	public Location(int userId, String resType, String add1, String add2,
			String city, String locState, String zip,String resUse)
	{
		
		this.userId=userId;
		this.residenceType=resType;
		this.addressLine1=add1;
		this.addressLine2=add2;
		this.city = city;
		this.locationState=locState;
		this.zipCode = zip;
		this.residenceUse = resUse;
	}
	
	public Location(int locId, int userId, String resType, String add1, String add2,
			String city, String locState, String zip,String resUse)
	{
		this.locationId=locId;
		this.userId=userId;
		this.residenceType=resType;
		this.addressLine1=add1;
		this.addressLine2=add2;
		this.city = city;
		this.locationState=locState;
		this.zipCode = zip;
		this.residenceUse = resUse;
	}
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getResidenceType() {
		return residenceType;
	}
	public void setResidenceType(String residenceType) {	
			this.residenceType = residenceType;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocationState() {
		return locationState;
	}
	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getResidenceUse() {
		return residenceUse;
	}
	public void setResidenceUse(String residenceUse) {
			this.residenceUse = residenceUse;
	}

}
