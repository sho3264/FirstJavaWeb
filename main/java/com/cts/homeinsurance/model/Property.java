package com.cts.homeinsurance.model;

public class Property {
	// first column
	private int locationId=0,
			// third column
			yearBuilt=0,
			// fourth column
			squareFootage=0,
			// 8th column
			fullBaths=0,
			// 9th column
			halfBaths=0;
			// 5th column
	private String dwellingType="",
			// 6th column
			roofMaterial="",
			// 7th column
			garageType="";
			// 10th column
	private int pool=0;
	// second column
	private float marketValue=0f;
	
	
	public Property() {
		// TODO Auto-generated constructor stub
	}
	
	public Property(float marketValue, int yearBuilt, int squareFootage, int fullBaths,
			int halfBaths,String dwellingType, String roofMaterial, String garageType, int pool)
	{
		this.marketValue=marketValue;
		this.yearBuilt=yearBuilt;
		this.squareFootage=squareFootage;
		this.fullBaths=fullBaths;
		this.halfBaths=halfBaths;
		this.dwellingType=dwellingType;
		this.roofMaterial=roofMaterial;
		this.garageType=garageType;
		this.pool=pool;
	}
	
	public Property(int locId, float marketValue, int yearBuilt, int squareFootage, int fullBaths,
			int halfBaths,String dwellingType, String roofMaterial, String garageType, int pool)
	{
		this.locationId=locId;
		this.marketValue=marketValue;
		this.yearBuilt=yearBuilt;
		this.squareFootage=squareFootage;
		this.fullBaths=fullBaths;
		this.halfBaths=halfBaths;
		this.dwellingType=dwellingType;
		this.roofMaterial=roofMaterial;
		this.garageType=garageType;
		this.pool=pool;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(int yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public int getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}
	
	public int getFullBaths() {
		return fullBaths;
	}

	public void setFullBaths(int fullBaths) {
		if (fullBaths>0)
			this.fullBaths = fullBaths;
		else
			return;
	}

	public int getHalfBaths() {
		return halfBaths;
	}

	public void setHalfBaths(int halfBaths) {
		if (halfBaths>0)
			this.halfBaths = halfBaths;
		else 
			return;
	}

	public String getDwellingType() {
		return dwellingType;
	}

	public void setDwellingType(String dwellingType) {
		this.dwellingType = dwellingType;
	}

	public String getRoofMaterial() {
		return roofMaterial;
	}

	public void setRoofMaterial(String roofMaterial) {	
			this.roofMaterial = roofMaterial;
	}

	public String getGarageType() {
		return garageType;
	}

	public void setGarageType(String garageType) {
			this.garageType = garageType;
	}

	public int getPool() {
		return pool;
	}

	public void setPool(int i) {
		
		this.pool=i;
		
	}

	public float getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(float marketValue) {
		this.marketValue = marketValue;
	}


}
