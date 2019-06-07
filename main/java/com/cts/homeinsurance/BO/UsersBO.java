package com.cts.homeinsurance.BO;

import java.io.IOException;
import java.sql.SQLException;
import com.cts.homeinsurance.dao.UsersDAO;
import com.cts.homeinsurance.model.Users;

public class UsersBO {

	public Integer addUsers(Users user) throws ClassNotFoundException, IOException, SQLException {
		UsersDAO udao=new UsersDAO();
		return udao.registerUser(user);
	}
	
	public Users getUserByUserName(String uname) throws ClassNotFoundException, IOException, SQLException
	{
		Users u=new Users();
		UsersDAO udao=new UsersDAO();
		u=udao.getUserByUserName(uname);
		return u;
	}
}
