package com.cts.homeinsurance.BO;

import java.io.IOException;
import java.sql.SQLException;
import com.cts.homeinsurance.dao.LocationDAO;
import com.cts.homeinsurance.model.Location;
import java.util.List;
import java.util.ArrayList;

public class LocationBO {

	public Integer addLocation(Location loc) throws ClassNotFoundException, IOException, SQLException
	{
		LocationDAO locDAO=new LocationDAO();
		return locDAO.registerLocation(loc);
	}
	
	public List<Location> getLocationByUserId(int userId) throws ClassNotFoundException, IOException, SQLException
	{
		LocationDAO locDAO=new LocationDAO();
		List<Location> loc = new ArrayList<Location>();
		loc=locDAO.getLocationByUserID(userId);
		return loc;
	}
	public Location getLocationByLocationId(int locId) throws ClassNotFoundException, IOException, SQLException
	{
		LocationDAO locDAO=new LocationDAO();
		Location loc = locDAO.getLocationByLocationID(locId);
		return loc;
	}
	
}

