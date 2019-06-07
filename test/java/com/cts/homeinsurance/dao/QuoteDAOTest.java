package com.cts.homeinsurance.dao;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.cts.homeinsurance.model.Quote;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;

@RunWith(Parameterized.class)
public class QuoteDAOTest {

	private static QuoteDAO qDAO = null;
	private Quote q= null;


	public QuoteDAOTest(Quote quote)
	{
		this.q=new Quote();
		this.q.setQuoteId(quote.getQuoteId());
		this.q.setLocationId(quote.getLocationId());
		this.q.setMonthlyPremium(quote.getMonthlyPremium());
		this.q.setDwellingCoverage(quote.getDwellingCoverage());
		this.q.setDetachedStructures(quote.getDetachedStructures());
		this.q.setPersonalProperty(quote.getPersonalProperty());
		this.q.setAddLivingExp(quote.getAddLivingExp());
		this.q.setMedicalExpenses(quote.getMedicalExpenses());
		this.q.setDeductible(quote.getDeductible());
	}

	@Parameters
	public static Collection<Quote> data()
	{
		return Arrays.asList(
				new Quote[]{
						// Quote(quoteId, locationId,monthlyPremium,dwellingCoverage,datachedStructures,personalProperty, 
						// addLivingExp, medicalExpenses,deductible)
						new Quote(1,1,10f,10f,10f,10f,10f,10f),
						new Quote(2,2,11f,11f,11f,11f,11f,11f),
						new Quote(3,3,12f,12f,12f,12f,12f,12f),
						new Quote(4,4,13f,13f,13f,13f,13f,13f),
						new Quote(5,5,14f,14f,14f,14f,14f,14f)
				});
	}

	@BeforeClass
	public static void setUp()
	{
		qDAO=new QuoteDAO();
	}

	@Test
	public void registerQuoteTest() throws ClassNotFoundException, IOException, SQLException
	{
		assertNotNull(qDAO.registerQuote(q));
	}

	@Test
	public void getQuoteByLocationIdTest() throws ClassNotFoundException, IOException, SQLException
	{
		qDAO.getQuoteByLocationId(q.getLocationId());
	}

}
