package com.cts.homeinsurance.dao;

import com.cts.homeinsurance.model.Homeowner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class HomeownerDAO {


	public Homeowner getHomeownerByUserID(int UserId) throws ClassNotFoundException, IOException, SQLException 
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Homeowner hm = null;
		OracleConnection orcl = new OracleConnection();
		String qString = "SELECT * FROM HOMEOWNERS WHERE USER_ID = ?";
		try {
			con=orcl.getConnection();
			stmt = con.prepareStatement(qString);
			stmt.setInt(1, UserId);
			rs=stmt.executeQuery();
			if (rs.next())
			{
				hm=new Homeowner();
				hm.setUserId(rs.getInt(1));
				hm.setFirstName(rs.getString(2));
				hm.setLastName(rs.getString(3));
				hm.setDob(rs.getDate(4));
				hm.setRetiredStatus(rs.getInt(5));
				hm.setSsn(rs.getString(6));
				hm.setEmail(rs.getString(7));
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

		return hm;
	}
	
	public Integer registerHomeowner(Homeowner Homeowner) throws ClassNotFoundException, IOException, SQLException
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;

		String qString="INSERT INTO HOMEOWNERS" 
				+ " VALUES (?,?,?,?,?,?,?)";
		OracleConnection orcl= new OracleConnection();
		
		try 
		{
			con=orcl.getConnection();
			stmt=con.prepareStatement(qString);
			stmt.setInt(1, Homeowner.getUserId());
			stmt.setString(2, Homeowner.getFirstName());
			stmt.setString(3, Homeowner.getLastName());
			stmt.setDate(4, Homeowner.getDob());
			stmt.setInt(5, Homeowner.getRetiredStatus());
			stmt.setString(6, Homeowner.getSsn());
			stmt.setString(7, Homeowner.getEmail());
			stmt.executeUpdate();
			// Get AutoIncrement value
			
			

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
		
		return Homeowner.getUserId();
	}
	
	
	
}

