package com.cts.homeinsurance.dao;

import com.cts.homeinsurance.model.Users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class UsersDAO {
	
	// get User by username to verify if password matches
	public Users getUserByUserName(String name) throws ClassNotFoundException, IOException, SQLException 
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Users u = null;
		OracleConnection orcl = new OracleConnection();
		String qString = "SELECT * FROM USERS WHERE USER_NAME = ?";
		try {
			con=orcl.getConnection();
			stmt = con.prepareStatement(qString);
			stmt.setString(1, name);
			rs=stmt.executeQuery();
			if (rs.next())
			{
				u= new Users();
				u.setUserId(rs.getInt(1));
				u.setUserName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setAdminRole(rs.getString(4));
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

		return u;
	}
	
	// create a new user
	public Integer registerUser(Users user) throws ClassNotFoundException, IOException, SQLException
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int id= -1;
		String[] col = {"USER_ID"};
		String qString="INSERT INTO USERS (USER_NAME ,PASSWORD,ADMIN_ROLE) VALUES (?,?,?)";
		OracleConnection orcl= new OracleConnection();
		
		try 
		{
			con=orcl.getConnection();
			stmt=con.prepareStatement(qString,col);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getAdminRole());
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
	
	// maybe use to update password
	public Boolean updateUser(Users user)throws SQLException, IOException, ClassNotFoundException
	{
		Connection con = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		OracleConnection orcl=new OracleConnection();
		String updateString= "UPDATE USERS "
							+"SET USER_NAME = ?, PASSWORD = ?, ADMIN_ROLE = ? "
							+ "WHERE USER_ID = ?";
		try 
		{
			con=orcl.getConnection();
			stmt=con.prepareStatement(updateString);
			stmt.setString(1,user.getUserName());
			stmt.setString(2,user.getPassword());
			stmt.setString(3,user.getAdminRole());
			stmt.setInt(4,user.getUserId());
			updateResult=stmt.executeUpdate();
		}
		catch(SQLException|IOException|ClassNotFoundException e)
		{
			System.out.println("Error: "+e.getMessage());
		}
		
		finally
		{
			if (stmt!=null) 
				stmt.close();

			if (con!=null) 
				con.close();
		}
		if (updateResult>0)
			return true;
		
		return false;
	}
	

	
}
