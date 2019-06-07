package com.cts.homeinsurance.model;

import java.sql.Date;

public class Policy {
	private int policyId=0,
				quoteId=0,
				userId=0,
				term=1;
	private String policyStatus="Active";
	private Date effectiveDate=null,
				endDate=null;
	public Policy() {
		// TODO Auto-generated constructor stub
	}
	public Policy(int quoteId,int userId,Date effDate)
	{
		this.quoteId=quoteId;
		this.userId=userId;
		this.effectiveDate=effDate;
		this.endDate=new Date(effDate.getYear()+1,effDate.getMonth(),effDate.getDay());
	}
	public Policy(int policyId,int qId,int userId,int term, String policyStatus,Date effDate,Date endDate)
	{
		this.policyId=policyId;
		this.quoteId=qId;
		this.userId=userId;
		this.term=term;
		this.policyStatus=policyStatus;
		this.effectiveDate=effDate;
		this.endDate=endDate;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
			this.policyStatus = policyStatus;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}	
}
