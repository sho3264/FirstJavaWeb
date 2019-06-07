package com.cts.homeinsurance.BO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cts.homeinsurance.dao.QuoteDAO;
import com.cts.homeinsurance.model.Property;
import com.cts.homeinsurance.model.Quote;

public class QuoteBO {

	public Integer addQuote(Quote q) throws ClassNotFoundException, IOException, SQLException
	{
		QuoteDAO qDAO= new QuoteDAO();
		return qDAO.registerQuote(q);
	}
	
	public Quote getQuoteByLocationId(int locId) throws ClassNotFoundException, IOException, SQLException
	{
		QuoteDAO qDAO= new QuoteDAO();
		Quote q= new Quote();
		q=qDAO.getQuoteByLocationId(locId);
		return q;
	}
	
	public Quote getQuoteByQuoteId(int qId) throws ClassNotFoundException, IOException, SQLException
	{
		QuoteDAO qDAO= new QuoteDAO();
		Quote q=qDAO.getQuoteByQuoteId(qId);
		return q;
	}
	
	public List<Quote> getQuoteByUserId(int userId) throws ClassNotFoundException, IOException, SQLException
	{
		QuoteDAO qDAO= new QuoteDAO();
		List<Quote> qList = new ArrayList<Quote>();
		qList=qDAO.getQuoteByUserId(userId);
		return qList;
	}
	
	public float calcMonthlyPremium(float p,String ResType)
	{
		float premium =1.0f;
		switch (ResType)
		{
		case "Single-Family Home":
			premium=1.05f;
			break;
		case "Condo":
		case "Duplex": 
		case "Apartment": 
			premium=1.06f;
		case "Townhouse":
		case "Rowhouse":
			premium = 1.07f;
			break;
		}
		float f = premium*5*p*0.001f/12;
		return f;
	}
	
	public float calcDwellingCoverage(Property p)
	{
		float reduction = 0.0f;
		if ((2019-p.getYearBuilt())<5)
			reduction = 0.9f;
		else if ((2019-p.getYearBuilt())>=5&&(2019-p.getYearBuilt())<10)
			reduction =0.8f;
		else if ((2019-p.getYearBuilt())>=10&&(2019-p.getYearBuilt())<20)
			reduction =0.7f;
		else
			reduction = 0.5f;
		
		return p.getMarketValue()*0.5f+reduction*p.getSquareFootage()*120;
	}
	
	
}
