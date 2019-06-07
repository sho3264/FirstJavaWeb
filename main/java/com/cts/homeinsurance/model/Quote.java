package com.cts.homeinsurance.model;

public class Quote {
	// Column 1
	private int quoteId=0,
			// column 2
			locationId=0;
	// column 3
	private float monthlyPremium=0f,
			// column 4
			dwellingCoverage=0f,
			// column 5
			detachedStructures=0f,
			// column 6
			personalProperty=0f,
			// column 7
			addLivingExp=0f,
			// column 8
			medicalExpenses=5000f,
			// column 9
			deductible=0f;
	public Quote() {
		// TODO Auto-generated constructor stub
	}
	
	public Quote( int locId, float monthlyPremium, float dwellingCoverage,float deductible)
	{
		
		this.locationId=locId;
		this.monthlyPremium=monthlyPremium;
		this.dwellingCoverage=dwellingCoverage;
		this.detachedStructures=dwellingCoverage*0.1f;
		this.personalProperty=0.6f*dwellingCoverage;
		this.addLivingExp=0.2f*dwellingCoverage;
		this.deductible=deductible;
		
	}
	
	public Quote(int quoteId, int locId, float monthlyPremium, float dwellingCoverage, float detachedStructures,
			float personalProperty, float addLivingExp, float deductible)
	{
		this.quoteId=quoteId;
		this.locationId=locId;
		this.monthlyPremium=monthlyPremium;
		this.dwellingCoverage=dwellingCoverage;
		this.detachedStructures=detachedStructures;
		this.personalProperty=personalProperty;
		this.addLivingExp=addLivingExp;
		this.deductible=deductible;
		
	}
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public float getMonthlyPremium() {
		return monthlyPremium;
	}
	public void setMonthlyPremium(float monthlyPremium) {
		this.monthlyPremium = monthlyPremium;
	}
	public float getDwellingCoverage() {
		return dwellingCoverage;
	}
	public void setDwellingCoverage(float dwellingCoverage) {
		this.dwellingCoverage = dwellingCoverage;
	}
	public float getDetachedStructures() {
		return detachedStructures;
	}
	public void setDetachedStructures(float detachedStructures) {
		this.detachedStructures = detachedStructures;
	}
	public float getPersonalProperty() {
		return personalProperty;
	}
	public void setPersonalProperty(float personalProperty) {
		this.personalProperty = personalProperty;
	}
	public float getAddLivingExp() {
		return addLivingExp;
	}
	public void setAddLivingExp(float addLivingExp) {
		this.addLivingExp = addLivingExp;
	}
	public float getMedicalExpenses() {
		return medicalExpenses;
	}
	public void setMedicalExpenses(float medicalExpenses) {
		this.medicalExpenses = medicalExpenses;
	}
	public float getDeductible() {
		return deductible;
	}
	public void setDeductible(float deductible) {
		this.deductible = deductible;
	}

}
