package com.cts.homeinsurance.BO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import com.cts.homeinsurance.dao.PolicyDAO;
import com.cts.homeinsurance.model.Policy;

public class PolicyBO {

	public Integer addPolicy(Policy pol) throws ClassNotFoundException, IOException, SQLException
	{
		PolicyDAO polDAO = new PolicyDAO();
		return polDAO.registerPolicy(pol);

	}

	public List<Policy> getPolicyByUserName(String name) throws ClassNotFoundException, IOException, SQLException
	{
		PolicyDAO polDAO = new PolicyDAO();
		return polDAO.getPolicyByUserName(name);
	}

	public boolean updatePolicy(Policy pol) throws ClassNotFoundException, SQLException, IOException
	{
		PolicyDAO polDAO = new PolicyDAO();
		return polDAO.updatePolicy(pol);

	}

	public Policy getPolicyByPolicyId(int id) throws ClassNotFoundException, IOException, SQLException {
		PolicyDAO polDAO= new PolicyDAO();
		Policy pol = polDAO.getPolicyByPolicyId(id);
		return pol;
	}

}
