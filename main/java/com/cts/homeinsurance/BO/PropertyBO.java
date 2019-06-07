package com.cts.homeinsurance.BO;

import java.io.IOException;
import java.sql.SQLException;
import com.cts.homeinsurance.dao.PropertyDAO;
import com.cts.homeinsurance.model.Property;

public class PropertyBO {

	public Integer addProperty(Property prop) throws ClassNotFoundException, IOException, SQLException
	{
		PropertyDAO propDAO=new PropertyDAO();
		return propDAO.registerProperty(prop);
	}
	
	public Property getPropertyByLocationId(int locId) throws ClassNotFoundException, IOException, SQLException
	{
		PropertyDAO propDAO=new PropertyDAO();
		Property prop = new Property();
		prop = propDAO.getPropertyByLocationID(locId);
		return prop;
	}
	
}
