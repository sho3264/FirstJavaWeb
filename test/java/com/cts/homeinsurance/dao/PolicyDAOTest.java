package com.cts.homeinsurance.dao;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.cts.homeinsurance.model.Policy;
import java.sql.Date;

import java.io.IOException;
import java.sql.SQLException;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class PolicyDAOTest {

	
	private static PolicyDAO pDAO = null;
	private Policy p = null;
	private String username="";
	
	public PolicyDAOTest(Policy p, String username)
	{
		this.p=new Policy();
		this.p.setPolicyId(p.getPolicyId());
		this.p.setQuoteId(p.getQuoteId());
		this.p.setUserId(p.getUserId());
		this.p.setTerm(p.getTerm());
		this.p.setPolicyStatus(p.getPolicyStatus());
		this.p.setEffectiveDate(p.getEffectiveDate());
		this.p.setEndDate(p.getEndDate());
		this.username = username;
	}
	
	@Parameters
	public static Collection<Object[]> data()
	{
		return Arrays.asList(
				new Object[][]
						{	// {Policy(policyId, quoteId,userId,term,policyStatus, effectiveDate, endDate),username}
							{new Policy(1,1,1,10,"active",new Date(06,0,11),new Date(07,0,11)),"aa"},	
							{new Policy(2,2,2,11,"active",new Date(07,3,9),new Date(8,3,9)),"va"},
							{new Policy(3,3,3,12,"active",new Date(8,5,18),new Date(9,5,18)),"ab"},
							{new Policy(4,4,4,13,"cancelled",new Date(9,2,8),new Date(10,2,8)),"as"},
							{new Policy(5,5,5,14,"active",new Date(10,1,17),new Date(11,1,17)),"as"}
								
						}
				);
	}
	
	@BeforeClass
	public static void setUp()
	{
		pDAO = new PolicyDAO();
	}
	
	@Test
	public void registerPolicyTest() throws ClassNotFoundException, IOException, SQLException
	{
		assertNotNull(pDAO.registerPolicy(p));
		
	}
	
	@Test
	public void getPolicyByUserNameTest() throws ClassNotFoundException, IOException, SQLException
	{
		pDAO.getPolicyByUserName(username);
	}
	
}
