package com.cts.homeinsurance.dao;

import com.cts.homeinsurance.model.Location;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class LocationDAO {
	
	
	public List<Location> getLocationByUserID(int UserId) throws ClassNotFoundException, IOException, SQLException 
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Location l = null;
		ArrayList<Location> locationList=null;
		OracleConnection orcl = new OracleConnection();
		String qString = "SELECT * FROM LOCATIONS WHERE USER_ID = ?";
		
		locationList=new ArrayList<Location>();
		try {
			con=orcl.getConnection();
			stmt = con.prepareStatement(qString);
			stmt.setInt(1, UserId);
			rs=stmt.executeQuery();
			while (rs.next())
			{
				l=new Location();
				l.setLocationId(rs.getInt(1));
				l.setUserId(rs.getInt(2));
				l.setResidenceType(rs.getString(3));
				l.setAddressLine1(rs.getString(4));
				l.setAddressLine2(rs.getString(5));
				l.setCity(rs.getString(6));
				l.setLocationState(rs.getString(7));
				l.setZipCode(rs.getString(8));
				l.setResidenceUse(rs.getString(9));
				locationList.add(l);
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

		return locationList;
	}
	
	public Location getLocationByLocationID(int LocationId) throws ClassNotFoundException, IOException, SQLException 
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Location l = null;
		OracleConnection orcl = new OracleConnection();
		String qString = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";
		
		
		try {
			con=orcl.getConnection();
			stmt = con.prepareStatement(qString);
			stmt.setInt(1, LocationId);
			rs=stmt.executeQuery();
			if (rs.next())
			{
				l=new Location();
				l.setLocationId(rs.getInt(1));
				l.setUserId(rs.getInt(2));
				l.setResidenceType(rs.getString(3));
				l.setAddressLine1(rs.getString(4));
				l.setAddressLine2(rs.getString(5));
				l.setCity(rs.getString(6));
				l.setLocationState(rs.getString(7));
				l.setZipCode(rs.getString(8));
				l.setResidenceUse(rs.getString(9));
			
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

		return l;
	}
	
	public Integer registerLocation(Location Location) throws ClassNotFoundException, IOException, SQLException
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Integer id= -1;
		String[] col = {"LOCATION_ID"};
		String qString="INSERT INTO LOCATIONS (USER_ID,RESIDENCE_TYPE, ADDRESS_LINE_1, ADDRESS_LINE_2, "
				+ "CITY, LOCATION_STATE, ZIP_CODE, RESIDENCE_USE)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		OracleConnection orcl= new OracleConnection();
		
		try 
		{
			con=orcl.getConnection();
			stmt=con.prepareStatement(qString,col);
			stmt.setInt(1, Location.getUserId());
			stmt.setString(2, Location.getResidenceType());
			stmt.setString(3, Location.getAddressLine1());
			stmt.setString(4, Location.getAddressLine2());
			stmt.setString(5, Location.getCity());
			stmt.setString(6, Location.getLocationState());
			stmt.setString(7, Location.getZipCode());
			stmt.setString(8, Location.getResidenceUse());

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
