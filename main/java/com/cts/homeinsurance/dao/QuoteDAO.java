package com.cts.homeinsurance.dao;

import com.cts.homeinsurance.model.Quote;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class QuoteDAO {
	
	
	public Quote getQuoteByLocationId(int LocationId) throws ClassNotFoundException, IOException, SQLException 
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Quote q = null;
		OracleConnection orcl = new OracleConnection();
		String qString = "SELECT * FROM QUOTES WHERE LOCATION_ID = ?";
		try {
			con=orcl.getConnection();
			stmt = con.prepareStatement(qString);
			stmt.setInt(1, LocationId);
			rs=stmt.executeQuery();
			if (rs.next())
			{
				q= new Quote();
				q.setQuoteId(rs.getInt(1));
				q.setLocationId(rs.getInt(2));
				q.setMonthlyPremium(rs.getFloat(3));
				q.setDwellingCoverage(rs.getFloat(4));
				q.setDetachedStructures(rs.getFloat(5));
				q.setPersonalProperty(rs.getFloat(6));
				q.setAddLivingExp(rs.getFloat(7));
				q.setMedicalExpenses(rs.getFloat(8));
				q.setDeductible(rs.getFloat(9));
			}

		}
		catch(ClassNotFoundException|IOException|SQLException e)
		{
			System.out.println("Error: "+ e.getMessage());
			e.getStackTrace();
		}

		finally
		{
			if (rs!=null) 
				rs.close();

			if (stmt!=null) 
				stmt.close();

			if (con!=null) 
				con.close();
		}

		return q;
	}
	
	public Quote getQuoteByQuoteId(int QuoteId) throws ClassNotFoundException, IOException, SQLException 
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Quote q = null;
		OracleConnection orcl = new OracleConnection();
		String qString = "SELECT * FROM QUOTES WHERE QUOTE_ID = ?";
		try {
			con=orcl.getConnection();
			stmt = con.prepareStatement(qString);
			stmt.setInt(1, QuoteId);
			rs=stmt.executeQuery();
			if (rs.next())
			{
				q= new Quote();
				q.setQuoteId(rs.getInt(1));
				q.setLocationId(rs.getInt(2));
				q.setMonthlyPremium(rs.getFloat(3));
				q.setDwellingCoverage(rs.getFloat(4));
				q.setDetachedStructures(rs.getFloat(5));
				q.setPersonalProperty(rs.getFloat(6));
				q.setAddLivingExp(rs.getFloat(7));
				q.setMedicalExpenses(rs.getFloat(8));
				q.setDeductible(rs.getFloat(9));
			}

		}
		catch(ClassNotFoundException|IOException|SQLException e)
		{
			System.out.println("Error: "+ e.getMessage());
			e.getStackTrace();
		}

		finally
		{
			if (rs!=null) 
				rs.close();

			if (stmt!=null) 
				stmt.close();

			if (con!=null) 
				con.close();
		}

		return q;
	}
	
	
	public List<Quote> getQuoteByUserId(int UserId) throws ClassNotFoundException, IOException, SQLException 
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Quote q = null;
		List<Quote> quoteList = null;
		OracleConnection orcl = new OracleConnection();
		String qString = "SELECT * FROM QUOTES WHERE EXIST(SELECT * FROM LOCATIONS WHERE USER_ID =?)";
		
		try {
			con=orcl.getConnection();
			stmt = con.prepareStatement(qString);
			stmt.setInt(1, UserId);
			rs=stmt.executeQuery();
			quoteList= new ArrayList<Quote>();
			while (rs.next())
			{
				q= new Quote();
				q.setQuoteId(rs.getInt(1));
				q.setLocationId(rs.getInt(2));
				q.setMonthlyPremium(rs.getFloat(3));
				q.setDwellingCoverage(rs.getFloat(4));
				q.setDetachedStructures(rs.getFloat(5));
				q.setPersonalProperty(rs.getFloat(6));
				q.setAddLivingExp(rs.getFloat(7));
				q.setMedicalExpenses(rs.getFloat(8));
				q.setDeductible(rs.getFloat(9));
				quoteList.add(q);
			}

		}
		catch(ClassNotFoundException|IOException|SQLException e)
		{
			System.out.println("Error: "+ e.getMessage());
			e.getStackTrace();
		}

		finally
		{
			if (rs!=null) 
				rs.close();

			if (stmt!=null) 
				stmt.close();

			if (con!=null) 
				con.close();
		}

		return quoteList;
	}
	
	public Integer registerQuote(Quote Quote) throws ClassNotFoundException, IOException, SQLException
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Integer id= -1;
		String[] col = {"Quote_Id"};
		String qString="INSERT INTO QUOTES (LOCATION_ID,MONTHLY_PREMIUM,DWELLING_COVERAGE,"
				+ "DETACHED_STRUCTURES, PERSONAL_PROPERTY, ADD_LIVING_EXP,"
				+ "MEDICAL_EXPENSES, DEDUCTIBLE) VALUES (?,?,?,?,?,?,?,?)";
		OracleConnection orcl= new OracleConnection();
		
		try 
		{
			con=orcl.getConnection();
			stmt=con.prepareStatement(qString,col);
			stmt.setInt(1, Quote.getLocationId());
			stmt.setFloat(2, Quote.getMonthlyPremium());
			stmt.setFloat(3, Quote.getDwellingCoverage());
			stmt.setFloat(4, Quote.getDetachedStructures());
			stmt.setFloat(5, Quote.getPersonalProperty());
			stmt.setFloat(6, Quote.getAddLivingExp());
			stmt.setFloat(7, Quote.getMedicalExpenses());
			stmt.setFloat(8, Quote.getDeductible());
			stmt.executeUpdate();
			// Get AutoIncrement value
			rs=stmt.getGeneratedKeys();
			
			if (rs!=null&&rs.next())
			{
				id=rs.getInt(1);
				
			}
		}
		catch(ClassNotFoundException|IOException|SQLException e)
		{
			System.out.println("Error: "+e.getMessage());
			e.getStackTrace();
		}
		
		finally
		{
			if (rs!=null) 
				rs.close();

			if (stmt!=null) 
				stmt.close();

			if (con!=null) 
				con.close();
		}
		
		return id;
	}
	
	

}
