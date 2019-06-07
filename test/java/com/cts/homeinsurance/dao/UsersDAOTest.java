package com.cts.homeinsurance.dao;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.cts.homeinsurance.model.Users;

import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;

@RunWith(Parameterized.class)
public class UsersDAOTest {
	
	private static UsersDAO uDAO=null;
	private Users user = null;
	private String check="";
	
	public UsersDAOTest(Users u)
	{
		this.user= new Users();
		this.user.setUserId(u.getUserId());
		this.user.setUserName(u.getUserName());
		this.user.setPassword(u.getPassword());
		this.user.setAdminRole(u.getAdminRole());
		this.check=u.getUserName();
	}
	
	@Parameters
	public static Collection<Users> data()
	{
		return Arrays.asList(
				new Users[] {
						// Users(userId,userName,password, adminRole)
						new Users(1,"aa","bb888888","user"),
						new Users(2,"va","ba000000","user"),
						new Users(3,"ab","caopiiet","admin"),
						new Users(4,"as","dsldoadie","user"),
						new Users(5,"af","biepdutd","user")
				}
				);

	}
	
	@BeforeClass
	public static void setUp()
	{
		uDAO=new UsersDAO();
	}
	
	@Test
	public void registerUserTest() throws ClassNotFoundException, SQLException, IOException
	{
		assertNotNull(uDAO.registerUser(user));
		
	}
	
	@Test
	public void getUserByUserNameTest() throws ClassNotFoundException, SQLException, IOException
	{
		uDAO.getUserByUserName(check);
		
	}
	
}

