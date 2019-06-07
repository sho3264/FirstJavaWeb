package com.cts.homeinsurance.dao;

import com.cts.homeinsurance.model.Property;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class PropertyDAO {


	public Property getPropertyByLocationID(int LocationId) throws ClassNotFoundException, IOException, SQLException 
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Property p = null;
		OracleConnection orcl = new OracleConnection();
		String qString = "SELECT * FROM PROPERTIES WHERE LOCATION_ID = ?";
		try {
			con=orcl.getConnection();
			stmt = con.prepareStatement(qString);
			stmt.setInt(1, LocationId);
			rs=stmt.executeQuery();
			if (rs.next())
			{
				p=new Property();
				p.setLocationId(rs.getInt(1));
				p.setMarketValue(rs.getFloat(2));
				p.setYearBuilt(rs.getInt(3));
				p.setSquareFootage(rs.getInt(4));
				p.setDwellingType(rs.getString(5));
				p.setRoofMaterial(rs.getString(6));
				p.setGarageType(rs.getString(7));
				p.setFullBaths(rs.getInt(8));
				p.setHalfBaths(rs.getInt(9));
				p.setPool(rs.getInt(10));
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

		return p;
	}
	
	public Integer registerProperty(Property Property) throws ClassNotFoundException, IOException, SQLException
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String qString="INSERT INTO PROPERTIES VALUES (?,?,?,?,?,?,?,?,?,?)";
		OracleConnection orcl= new OracleConnection();
		
		try 
		{
			con=orcl.getConnection();
			stmt=con.prepareStatement(qString);
			stmt.setInt(1, Property.getLocationId());
			stmt.setFloat(2, Property.getMarketValue());
			stmt.setInt(3, Property.getYearBuilt());
			stmt.setInt(4, Property.getSquareFootage());
			stmt.setString(5, Property.getDwellingType());
			stmt.setString(6, Property.getRoofMaterial());
			stmt.setString(7, Property.getGarageType());
			stmt.setInt(8, Property.getFullBaths());
			stmt.setInt(9, Property.getHalfBaths());
			stmt.setInt(10, Property.getPool());
			stmt.executeUpdate();


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
		
		return Property.getLocationId();
	}
	

	
}
