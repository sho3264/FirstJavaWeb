package com.cts.homeinsurance.dao;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.cts.homeinsurance.model.Homeowner;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

@RunWith(Parameterized.class)
public class HomeownerDAOTest {

	private static HomeownerDAO hmDAO=null;
	private Homeowner homeowner =null;
	
	public HomeownerDAOTest(Homeowner hm)
	{
		this.homeowner=new Homeowner();
		this.homeowner.setUserId(hm.getUserId());
		this.homeowner.setFirstName(hm.getFirstName());
		this.homeowner.setLastName(hm.getLastName());
		this.homeowner.setSsn(hm.getSsn());
		this.homeowner.setRetiredStatus(hm.getRetiredStatus());
		this.homeowner.setEmail(hm.getEmail());
		this.homeowner.setDob(hm.getDob());
	}
	
	@Parameters
	public static Collection<Homeowner> data()
	{
		return Arrays.asList(
				new Homeowner[] {
						// Homeowner(userId,firstName,LastName,ssn, email, retiredStaus, dob) 
						new Homeowner(1,"Bob","Max","111111111","ma",0,new Date(05,0,10)),
						new Homeowner(2,"Joe","Min","122323456","email",1,new Date(04,2,12)),
						new Homeowner(3,"Bat","Mobiel","123456789","school",0,new Date(01,6,15)),
						new Homeowner(4,"Low","Surf","123456781","try",0,new Date(15,4,17)),
						new Homeowner(5,"Doe","John","123812345","day",1,new Date(25,1,9))
				}
				);

	}
	
	@BeforeClass
	public static void setUp()
	{
		hmDAO=new HomeownerDAO();
	}
	
	@Test
	public void registerUserTest() throws ClassNotFoundException, SQLException, IOException
	{
		assertNotNull(hmDAO.registerHomeowner(homeowner));
	}
	
	@Test
	public void getUserByUserNameTest() throws ClassNotFoundException, SQLException, IOException
	{
		hmDAO.getHomeownerByUserID(homeowner.getUserId());
	}
	
}
