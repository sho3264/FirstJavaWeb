package com.cts.homeinsurance.BO;

import java.io.IOException;
import java.sql.SQLException;

import com.cts.homeinsurance.dao.HomeownerDAO;
import com.cts.homeinsurance.model.Homeowner;

public class HomeownerBO {

	public Integer addHomeowner(Homeowner hm) throws ClassNotFoundException, IOException, SQLException
	{
		HomeownerDAO hmDAO=new HomeownerDAO();
		return hmDAO.registerHomeowner(hm);
		
	}
	
	public Homeowner getHomeownerByUserId(int userId) throws ClassNotFoundException, IOException, SQLException
	{
		HomeownerDAO hmDAO=new HomeownerDAO();
		Homeowner hm= new Homeowner();
		hm=hmDAO.getHomeownerByUserID(userId);
		return hm;
	}
	
}
