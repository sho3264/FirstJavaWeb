package com.cts.homeinsurance.dao;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.cts.homeinsurance.model.Property;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;

@RunWith(Parameterized.class)
public class PropertyDAOTest {

	private static PropertyDAO pDAO = null;
	private Property p = null;

	public PropertyDAOTest(Property prop)
	{
		this.p=new Property();
		this.p.setLocationId(prop.getLocationId());
		this.p.setMarketValue(prop.getMarketValue());
		this.p.setYearBuilt(prop.getYearBuilt());
		this.p.setSquareFootage(prop.getSquareFootage());
		this.p.setFullBaths(prop.getFullBaths());
		this.p.setHalfBaths(prop.getHalfBaths());
		this.p.setDwellingType(prop.getDwellingType());
		this.p.setRoofMaterial(prop.getRoofMaterial());
		this.p.setGarageType(prop.getGarageType());
		this.p.setPool(prop.getPool());
	}

	@Parameters
	public static Collection<Property> data()
	{
		return Arrays.asList(
				new Property[]
						{
								// Property(locationId, marketValue,yearBuilt, squareFootage, fullBaths,
								// halfBaths, dwellingType, roofMaterial,garageType, pool)
								new Property(1,10,20,30,1,5,"1 Story","Concrete","Attached",1),	
								new Property(2,11,22,33,2,1,"1-1/2 Stories","Clay","Detached",1),
								new Property(3,12,23,34,3,2,"2 Stories","Steel","Basement",1),
								new Property(4,13,21,32,4,3,"2 Stories","Rubber","Built-In",0),
								new Property(5,14,24,34,5,4,"2-1/2 Stories","Tin","None",0)

						}
				);
	}

	@BeforeClass
	public static void setUp()
	{
		pDAO = new PropertyDAO();
	}

	@Test
	public void registerPropertyTest() throws ClassNotFoundException, IOException, SQLException
	{
		assertNotNull(pDAO.registerProperty(p));
	}

	@Test
	public void getPropertyByLocationIdTest() throws ClassNotFoundException, IOException, SQLException
	{
		pDAO.getPropertyByLocationID(p.getLocationId());
	}
}
