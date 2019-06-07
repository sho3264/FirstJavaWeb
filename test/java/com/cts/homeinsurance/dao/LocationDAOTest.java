package com.cts.homeinsurance.dao;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.cts.homeinsurance.model.Location;


import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;

@RunWith(Parameterized.class)
public class LocationDAOTest {

	private static LocationDAO locDAO = null;
	private Location loc = null;

	public LocationDAOTest(Location loc)
	{
		this.loc=new Location();
		this.loc.setLocationId(loc.getLocationId());
		this.loc.setUserId(loc.getUserId());
		this.loc.setResidenceType(loc.getResidenceType());
		this.loc.setAddressLine1(loc.getAddressLine1());
		this.loc.setAddressLine2(loc.getAddressLine2());
		this.loc.setCity(loc.getCity());
		this.loc.setLocationState(loc.getLocationState());
		this.loc.setZipCode(loc.getZipCode());
		this.loc.setResidenceUse(loc.getResidenceUse());
	}

	@Parameters
	public static Collection<Location> data()
	{
		return Arrays.asList(
				new Location[]
						{
								// Location(locationId, userId, residenceType, addressLine1, addressLine2, city, ,
								// zipCode, residenceUse)
								new Location(1,1,"Condo","line1","line2","Dallas","TX","123456789","Rental Property"),	
								new Location(2,2,"Apartment","line21","line22","Dallas","TX","123456789","Primary"),
								new Location(3,3,"Apartment","line31","line32","Dallas","TX","123456789","Primary"),
								new Location(4,4,"Duplex","line41","line42","Dallas","TX","123456789","Secondary"),
								new Location(5,5,"Townhouse","line51","line52","Dallas","TX","123456789","Rental Property")			
						}
				);
	}

	@BeforeClass
	public static void setUp()
	{
		locDAO = new LocationDAO();
	}

	@Test
	public void registerLocationTest() throws ClassNotFoundException, IOException, SQLException
	{
		assertNotNull(locDAO.registerLocation(loc));
	}

	@Test
	public void getLocationByLocationIdTest() throws ClassNotFoundException, IOException, SQLException
	{
		locDAO.getLocationByUserID(loc.getUserId());
	}

}
