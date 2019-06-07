package com.cts.homeinsurance.dao;

import com.cts.homeinsurance.model.Policy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PolicyDAO {


	public List<Policy> getPolicyByUserName(String username) throws ClassNotFoundException, IOException, SQLException 
	{
		ArrayList<Policy> policyList=null;
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Policy p = null;
		OracleConnection orcl = new OracleConnection();
		String qString = "SELECT * FROM POLICIES WHERE EXISTS (SELECT USER_ID FROM USERS WHERE USER_NAME =?)";
		try {
			con=orcl.getConnection();
			stmt = con.prepareStatement(qString);
			stmt.setString(1, username);
			rs=stmt.executeQuery();
			policyList = new ArrayList<Policy>();
			while (rs.next())
			{
				p=new Policy();
				p.setPolicyId(rs.getInt(1));
				p.setQuoteId(rs.getInt(2));
				p.setUserId(rs.getInt(3));
				p.setEffectiveDate(rs.getDate(4));
				p.setEndDate(rs.getDate(5));
				p.setTerm(rs.getInt(6));
				p.setPolicyStatus(rs.getString(7));;
				policyList.add(p);
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

		return policyList;
	}
	
	public Policy getPolicyByPolicyId(Integer polId) throws ClassNotFoundException, IOException, SQLException 
	{
		
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Policy p = null;
		OracleConnection orcl = new OracleConnection();
		String qString = "SELECT * FROM POLICIES WHERE POLICY_ID = ?";
		try {
			con=orcl.getConnection();
			stmt = con.prepareStatement(qString);
			stmt.setInt(1, polId);
			rs=stmt.executeQuery();
			if (rs.next())
			{
				p=new Policy();
				p.setPolicyId(rs.getInt(1));
				p.setQuoteId(rs.getInt(2));
				p.setUserId(rs.getInt(3));
				p.setEffectiveDate(rs.getDate(4));
				p.setEndDate(rs.getDate(5));
				p.setTerm(rs.getInt(6));
				p.setPolicyStatus(rs.getString(7));;
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
	
	
	public Integer registerPolicy(Policy Policy) throws ClassNotFoundException, IOException, SQLException
	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Integer id= -1;
		String[] col = {"POLICY_ID"};
		String qString="INSERT INTO POLICIES (QUOTE_ID, USER_ID, EFFECTIVE_DATE, END_DATE, TERM, "
				+ "POLICY_STATUS)"
				+ " VALUES (?,?,?,?,?,?)";
		OracleConnection orcl= new OracleConnection();
		
		try 
		{
			con=orcl.getConnection();
			stmt=con.prepareStatement(qString,col);
			stmt.setInt(1, Policy.getQuoteId());
			stmt.setInt(2, Policy.getUserId());
			stmt.setDate(3, Policy.getEffectiveDate());
			stmt.setDate(4,Policy.getEndDate());
			stmt.setInt(5, Policy.getTerm());
			stmt.setString(6, Policy.getPolicyStatus());
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
	
	public Boolean updatePolicy(Policy Policy)throws SQLException, IOException, ClassNotFoundException
	{
		Connection con = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		OracleConnection orcl=new OracleConnection();
		String updateString= "UPDATE POLICIES "
							+"SET EFFECTIVE_DATE = ?, END_DATE = ?, TERM = ?, "
							+ "POLICY_STATUS = ?"
							+ "WHERE POLICY_ID = ?";
		try 
		{
			con=orcl.getConnection();
			stmt=con.prepareStatement(updateString);
			stmt.setDate(1, Policy.getEffectiveDate());
			stmt.setDate(2, Policy.getEndDate());
			stmt.setInt(3, Policy.getTerm());
			stmt.setString(4, Policy.getPolicyStatus());
			stmt.setInt(5, Policy.getPolicyId());

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
