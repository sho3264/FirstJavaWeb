package com.cts.homeinsurance.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cts.homeinsurance.BO.PolicyBO;
import com.cts.homeinsurance.BO.UsersBO;
import com.cts.homeinsurance.model.Policy;
import com.cts.homeinsurance.model.Users;



@Controller
@SessionAttributes
public class HomeController {
	List<String> errorLog;
	ModelAndView mod= null;

	@RequestMapping(value="/AdminLoginPage", method=RequestMethod.GET)
	public ModelAndView showAdminLoginPage() 
	{
		mod = new ModelAndView("AdminLogin");
		return mod;
	}
	@RequestMapping(value="/AdminPage", method=RequestMethod.GET)
	public ModelAndView showAdminPage() 
	{
		mod = new ModelAndView("AdminPage");
		return mod;
	}


	// somehow create a session attribute
	@RequestMapping(value ="/AdminLogin", method=RequestMethod.POST)
	public ModelAndView loginAdmin(@RequestParam("userName") String name,
			@RequestParam("password") String pass, HttpSession session,HttpServletRequest req) throws ClassNotFoundException, IOException, SQLException
	{	
		UsersBO uBO= new UsersBO();
		Users u = uBO.getUserByUserName(name);
		// user does not show up
		if (u==null)
		{
			mod= new ModelAndView("AdminLogin");
			errorLog= new ArrayList<String>();
			errorLog.add("Not a valid User");
			req.setAttribute("errorLog", errorLog);
			return mod;
		}
		else 
		{
			// check if user is an admin
			// user is an admin and password matches
			if (u.getPassword().equals(pass)&&u.getAdminRole().equals("admin"))
			{	
				mod = new ModelAndView("AdminPage");
				session.setAttribute("currentAdmin", u);
				return mod;
			}
			// either password does not match or not an admin role
			else 
			{
				errorLog= new ArrayList<String>();
				mod= new ModelAndView("AdminLogin");
				// user is not an admin
				if (!u.getAdminRole().equals("admin"))
				{
					
					errorLog.add("User is not an admin");
					req.setAttribute("errorLog", errorLog);
					// do not want to check if the password matches
					return mod;
				}

				// password is the only other error
				errorLog.add("Not a valid password");
				req.setAttribute("errorLog", errorLog);
				return mod;

			}

		}
	}

	@RequestMapping(value="/AdminGetPolicys", method = RequestMethod.POST)
	public ModelAndView searchPolicies(@RequestParam("UserNameSearch") String name,
			HttpServletRequest req) throws ClassNotFoundException, IOException, SQLException {
		PolicyBO pBO = new PolicyBO();
		List<Policy> pList = pBO.getPolicyByUserName(name);
		mod = new ModelAndView("ShowPolicies");
		req.setAttribute("policyList", pList);
		req.setAttribute("selectedUser",name);
		return mod;
	}
	
	@RequestMapping(value="/RenewPolicy", method=RequestMethod.GET)
	public ModelAndView renewPolicy(HttpServletRequest req, @RequestParam("id")String id) throws NumberFormatException, ClassNotFoundException, IOException, SQLException
	{
		// place checks for renew policy date
		PolicyBO pBO= new PolicyBO();
		Policy pol=pBO.getPolicyByPolicyId(Integer.valueOf(id));
		pol.setTerm(pol.getTerm()+1);
		Date today= new Date(LocalDate.now().getYear()-1900,LocalDate.now().getMonthValue()-1,LocalDate.now().getDayOfMonth());
		Date endDate= new Date(LocalDate.now().getYear()-1899,LocalDate.now().getMonthValue()-1,LocalDate.now().getDayOfMonth());
		pol.setEffectiveDate(today);
		pol.setEndDate(endDate);
		req.setAttribute("status", "Renewed");
		pBO.updatePolicy(pol);
		req.setAttribute("currentPolicy", pol);
		mod= new ModelAndView("AdminPolicyUpdatePage");
		return mod;
	}
	
	@RequestMapping(value="/CancelPolicy", method=RequestMethod.GET)
	public ModelAndView cancelPolicy(HttpServletRequest req, @RequestParam("id")String id) throws NumberFormatException, ClassNotFoundException, IOException, SQLException
	{
		PolicyBO pBO= new PolicyBO();
		Policy pol=pBO.getPolicyByPolicyId(Integer.valueOf(id));
		Date today= new Date(LocalDate.now().getYear()-1900,LocalDate.now().getMonthValue()-1,LocalDate.now().getDayOfMonth());
		pol.setEndDate(today);
		pol.setPolicyStatus("CANCELLED");
		req.setAttribute("status", "Cancelled");
		pBO.updatePolicy(pol);
		req.setAttribute("currentPolicy", pol);
		mod= new ModelAndView("AdminPolicyUpdatePage");
		return mod;
	}

	@RequestMapping(value="/AdminLogout", method=RequestMethod.GET)
	public ModelAndView adminLogout(HttpSession session) {
		mod = new ModelAndView("AdminLogin");
		session.invalidate();
		return mod;
	}



}
