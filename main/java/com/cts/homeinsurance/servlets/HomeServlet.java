package com.cts.homeinsurance.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import com.cts.homeinsurance.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cts.homeinsurance.BO.*;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<String> errorLog;
	//"^*[a-zA-Z0-9]*$" alphanumeric regex with no spaces
	//"[0-9]" numbers regex
	//"^.*[a-zA-Z0-9.-]*" alphanumeric with .- and spaces
	//^.*[a-zA-Z0-9]*" alphanumeric with spaces
	private String[] regex = {"^*[a-zA-Z0-9]*$","^*[0-9]*$","^*[\\sa-zA-Z0-9.-]*$","^*[\\sa-zA-Z0-9]*$"}; 

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/Home":
				login(request, response);
				break;
			case "/Login":
				loginPage(request, response);
				break;
			case "/RegisterUser":
				addUser(request, response);
				break;
			case "/showRegistrationPage":
				registrationPage(request, response);
				break;
			case "/GetStarted":
				getStarted(request, response);
				break;
			case "/GetQuote":
				getQuote(request, response);
				break;
			case "/InsertLocation":
				insertLocation(request,response);
				break;
			case "/HomeownerInfo":
				showHomeownerInfo(request,response);
				break;
			case "/InsertHomeowner":
				insertHomeowner(request,response);
				break;
			case "/PropertyDetails":
				showPropertyDetails(request,response);
				break;
			case "/showCoverageDetails":
				showCoverageDetails(request,response);
				break;
			case "/showCoverage":
				showCoverage(request,response);
				break;
			case "/QuoteSummary":
				quoteSummary(request,response);
				break;
			case "/InsertProperty":
				insertProperty(request,response);
				break;
			case "/InsertPolicy":
				insertPolicy(request,response);
				break;
			case "/BuyQuote":
				buyQuotePage(request,response);
				break;
			case "/PolicyConfirmation":
				policyConfirmation(request,response);
				break;
			case "/RetrieveQuote":
				retrieveQuotePage(request, response);
				break;
			case"/MyPolicies":
				myPoliciesPage(request,response);
				break;
			case"/AdditionalInfo":
				additionalInfoPage(request,response);
				break;
			case"/TermsAndConditions":
				disclaimerPage(request,response);
				break;
			case "/Logout":
				logout(request, response);
				break;
			default:
				login(request, response);
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request, response);
	}

	private void loginPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		List<String> errorLog;
		UsersBO uBO = new UsersBO();
		Users u = uBO.getUserByUserName(userName);
		if (u == null) {
			errorLog = new ArrayList<String>();
			errorLog.add("Invalid User");
			request.setAttribute("errorLog", errorLog);
			login(request,response);
		} else {
			if (password.equals(u.getPassword())) {
				session.setAttribute("currentUser", u);
				response.sendRedirect("GetStarted");
			} else {
				errorLog = new ArrayList<String>();
				errorLog.add("Incorrect Password");
				request.setAttribute("errorLog", errorLog);
				login(request,response);
			}
		}
	}

	private void registrationPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String p =(String) session.getAttribute("Pass");
		System.out.println(p);
		request.getRequestDispatcher("/WEB-INF/views/Register.jsp").forward(request, response);
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String username = request.getParameter("userName");
		String pass = request.getParameter("password");
		String confirm = request.getParameter("confirmPassword");
		UsersBO uBO = new UsersBO();
		if (pass.equals(confirm)&&uBO.getUserByUserName(username)==null)
		{
			Users u = new Users(username, pass);
			uBO.addUsers(u);
			response.sendRedirect("Home");
		}
		else { 
			errorLog = new ArrayList<>();
			if (uBO.getUserByUserName(username)!=null) {
				errorLog.add("User already exist");	
			}
			if (!pass.equals(confirm)) {
				errorLog.add("Passwords do not match");
			} 
			if (!checkString(username,2,50,regex[0]))
			{
				errorLog.add("Username must be between 2 and 50 Alphanumeric Characters");
			}
			if (!checkString(pass,8,20,regex[0]))
			{
				errorLog.add("Password must be between 8 and 20 Alphanumeric Characters");
			}
			request.setAttribute("errorLog", errorLog);
			registrationPage(request,response);
		}
	}

	private void insertLocation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String ResType=request.getParameter("ResidentType");
		String AddLine1=request.getParameter("AddressLine1");
		String AddLine2=request.getParameter("AddressLine2");
		String state=request.getParameter("State");
		String city=request.getParameter("City");
		String zip= request.getParameter("Zip");
		String ResUse=request.getParameter("ResidenceUse");
		HttpSession session = request.getSession();
		Location l=null;
		// add conditions for addressline 1&2, city, and state
		// pass case
		if (checkString(zip,9,9,regex[1])
				&&checkString(city,2,25,regex[3])
				&&checkString(state,2,25,regex[3])
				&&checkString(AddLine1,3,50,regex[2]))
		{	
			// Address Line 2 is optional
			// check if it is not empty and fits conditions
			if (!AddLine2.equals("")&&checkString(AddLine2,3,50,regex[2])) 
			{

				Users u = (Users) session.getAttribute("currentUser");
				LocationBO lBO= new LocationBO();
				l = new Location(1,u.getUserId(),ResType,AddLine1,AddLine2,city,state,zip,ResUse);
				int locId=lBO.addLocation(l);
				l.setLocationId(locId);
				session.setAttribute("currentLocation",l);
				response.sendRedirect("HomeownerInfo");
			}
			// if AddLine2 is null 
			else if(AddLine2.equals(""))
			{
				Users u = (Users) session.getAttribute("currentUser");
				LocationBO lBO= new LocationBO();
				l = new Location(1,u.getUserId(),ResType,AddLine1,AddLine2,city,state,zip,ResUse);
				int locId =lBO.addLocation(l);
				l.setLocationId(locId);
				session.setAttribute("currentLocation",l);
				response.sendRedirect("HomeownerInfo");
			}
			// Address Line 2 is the only thing wrong
			else
			{
				errorLog= new ArrayList<>();
				errorLog.add("Not a valid input for Address Line 2 \n"
						+ "Must be between 3 and 50 characters");
				request.setAttribute("errorLog", errorLog);
				getQuote(request,response);	
			}
		}
		// count errors
		else
		{
			errorLog= new ArrayList<>();
			// Address Line 1 is wrong
			if (!checkString(AddLine1,3,50,regex[2]))
				errorLog.add("Not a valid input for Address Line 1 \n"
						+ "Must be between 3 and 50 characters");
			// Address Line 2 is wrong
			if(!AddLine2.equals("")&&!checkString(AddLine2,3,50,regex[2]))
				errorLog.add("Not a valid input for Address Line 2 \n"
						+ "Must be between 3 and 50 characters");
			// state is wrong
			if (!checkString(state,2,25,regex[3]))
				errorLog.add("Not a valid input for State \n"
						+ "Must be between 2 and 25 characters");
			// city is wrong
			if (!checkString(city,2,25,regex[3]))
				errorLog.add("Not a valid input for City \n"
						+ "Must be between 2 and 25 characters");
			// zip is wrong
			if (!checkString(zip,9,9))
				errorLog.add("Not a valid input for Zip \n"
						+ "Must be 9 digits");
			// send errors
			request.setAttribute("errorLog", errorLog);
			getQuote(request,response);
		}
	}

	private void getStarted(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/GetStarted.jsp").forward(request, response);
	}

	private void showHomeownerInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/HomeownerInfo.jsp").forward(request, response);
	}

	private void insertHomeowner(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String firstName=request.getParameter("FirstName");
		String lastName=request.getParameter("LastName");
		String dob=request.getParameter("dob");
		String retStat=request.getParameter("RetiredStatus");
		String ssn = request.getParameter("ssn");
		String email = request.getParameter("email");
		HttpSession session = request.getSession();
		Homeowner hm = null;
		// Everything is valid
		if(checkString(firstName,2,50,regex[0])
				&&checkString(lastName,2,50,regex[0])
				&&checkString(email,1,50)
				&&checkString(ssn,9,9)&&LocalDate.parse(dob).isBefore(LocalDate.now()))
		{

			//store data into a homeowner object
			Users u = (Users) session.getAttribute("currentUser");
			hm = new Homeowner(u.getUserId(),firstName,lastName,ssn,email,Integer.valueOf(retStat),sqlDate(dob));
			HomeownerBO hmBO= new HomeownerBO();
			hmBO.addHomeowner(hm);
			session.setAttribute("currentHomeowner",hm);
			response.sendRedirect("PropertyDetails");
		}
		else
		{
			errorLog=new ArrayList<>();
			// first name is not valid
			if (!checkString(firstName,2,50,regex[0]))
			{
				errorLog.add("Invalid First Name. Must be between 2 and 50 alphanumeric characters.");
			}
			// last name is wrong
			if(!checkString(lastName,2,50,regex[0]))
			{
				errorLog.add("Invalid Last Name. Must be between 2 and 50 alphanumeric characters.");
			}
			// dob is not valid
			if(!LocalDate.parse(dob).isBefore(LocalDate.now()))
			{
				errorLog.add("Invalid DOB. Must be before "+LocalDate.now().toString());
			}
			// email is wrong
			if(!checkString(email,1,50))
			{
				errorLog.add("Invalid Email. Must be valid email format");
			}
			// ssn is wrong
			if(!checkString(ssn,9,9))
			{
				errorLog.add("Social Security Number must be 9 digits long");
			}


			// print errors 
			request.setAttribute("errorLog", errorLog);
			showHomeownerInfo(request,response);
		}
	}

	private void showPropertyDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/PropertyDetails.jsp").forward(request, response);
	}

	private void insertProperty(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();

		float mValue= Float.valueOf(request.getParameter("MarketValue"));
		Integer yr = Integer.valueOf(request.getParameter("YearBuilt"));
		Integer sqft = Integer.valueOf(request.getParameter("SqFootage"));
		String dStyle = request.getParameter("DwellingStyle");
		String rMaterial = request.getParameter("RoofingMaterial");
		String garage = request.getParameter("GarageType");
		Integer full= Integer.valueOf(request.getParameter("FullBaths"));
		Integer half = Integer.valueOf(request.getParameter("HalfBaths"));
		Integer pool = Integer.valueOf(request.getParameter("SwimmingPool"));
		// year entered is 2019 or smaller
		if (yr<=LocalDate.now().getYear())
		{
			Location l =(Location)session.getAttribute("currentLocation");
			Property prop = new Property(l.getLocationId(),mValue,yr,sqft,full,half,dStyle,rMaterial,garage,pool);
			PropertyBO propBO= new PropertyBO();
			propBO.addProperty(prop);
			session.setAttribute("currentProperty", prop);
			// Create quote info
			QuoteBO qBO= new QuoteBO();
			float dwellingCoverage=qBO.calcDwellingCoverage(prop);
			Float monthlyPremium = qBO.calcMonthlyPremium(prop.getMarketValue(),l.getResidenceType());
			// insert quote
			Quote q =new Quote(l.getLocationId(),monthlyPremium,dwellingCoverage,0.1f*mValue);

			int qId=qBO.addQuote(q);	
			q.setQuoteId(qId);
			session.setAttribute("currentQuote", q); 
			// redirect to next page
			response.sendRedirect("showCoverageDetails");
		}
		else
		{
			errorLog.add("Must enter a year "
					+ "before or at " + LocalDate.now().getYear());
			request.setAttribute("errorLog", errorLog);
			showPropertyDetails(request,response);
		}

	}

	// used for path to go from inserting property to coverage details
	private void showCoverageDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		request.getRequestDispatcher("/WEB-INF/views/CoverageDetails.jsp").forward(request, response);
	}

	// used for path to go from retrieve quote to the coverage details 
	private void showCoverage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		Integer id=-1;
		id=Integer.valueOf(request.getParameter("id"));
		if (id!=-1)
		{

			HttpSession session = request.getSession();
			Users u = (Users) session.getAttribute("currentUser");
			// Quote Id
			// need a  quote ,property, location, and homeowner stored in the session
			QuoteBO qBO= new QuoteBO();
			HomeownerBO hBO= new HomeownerBO();
			LocationBO lBO = new LocationBO();
			PropertyBO pBO = new PropertyBO();
			Quote q = qBO.getQuoteByLocationId(id);
			Location l = lBO.getLocationByLocationId(q.getLocationId());
			Property p = pBO.getPropertyByLocationId(q.getLocationId());
			Homeowner hm = hBO.getHomeownerByUserId(u.getUserId());


			session.setAttribute("currentQuote", q);
			session.setAttribute("currentHomeowner", hm);
			session.setAttribute("currentLocation", l);
			session.setAttribute("currentProperty", p);
			//			

		}
		request.getRequestDispatcher("/WEB-INF/views/CoverageDetails.jsp").forward(request, response);
	}


	private void quoteSummary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/QuoteSummary.jsp").forward(request, response);
	}


	private void getQuote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/GetQuote.jsp").forward(request, response);
	}

	private void buyQuotePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/BuyPolicy.jsp").forward(request, response);
	}

	private void insertPolicy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {

		HttpSession session = request.getSession();
		Quote q= (Quote)session.getAttribute("currentQuote");
		Users u = (Users)session.getAttribute("currentUser");
		// Start Date
		String date = request.getParameter("PolicyDate");

		LocalDate today= LocalDate.now();
		// Check if Date falls within 60 days of current Date
		// isBefore and isAfter is exclusive when comparing
		if(LocalDate.parse(date).isBefore(today.plusDays(61))&&
				LocalDate.parse(date).isAfter(today.minusDays(1)))
		{
			Policy pol = new Policy(q.getQuoteId(),u.getUserId(),sqlDate(date));
			PolicyBO polBO= new PolicyBO();
			int polId= polBO.addPolicy(pol);
			pol.setPolicyId(polId);
			session.setAttribute("currentPolicy", pol);
			response.sendRedirect("PolicyConfirmation");
		}
		else
		{
			errorLog.add("Policy Start Date must fall within 60 days of "+today);
			request.setAttribute("errorLog", errorLog);
			buyQuotePage(request,response);
		}
	}

	private void policyConfirmation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/PolicyConfirmation.jsp").forward(request, response);
	}
	
	private void retrieveQuotePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();	
		Users u=(Users)session.getAttribute("currentUser");
		QuoteBO qBO = new QuoteBO();
		LocationBO lBO = new LocationBO();
		List<Location> lList=lBO.getLocationByUserId(u.getUserId());
		HashMap<Integer,Location> hm = new HashMap<Integer,Location>();
		if (lList!=null)
		{
			lList.forEach((n) -> {
				try {
					hm.put(qBO.getQuoteByLocationId(n.getLocationId()).getQuoteId(), n);
				} catch (ClassNotFoundException | IOException | SQLException e) {
					e.printStackTrace();
				}
			});
		}
		session.setAttribute("quotesList",hm);
		request.getRequestDispatcher("/WEB-INF/views/RetrieveQuote.jsp").forward(request, response);

	}
	private void myPoliciesPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {

		HttpSession session = request.getSession();
		Users u = (Users) session.getAttribute("currentUser");
		PolicyBO pBO = new PolicyBO();
		System.out.println(u.getUserName());
		System.out.println(u.getUserName());
		List<Policy> pList = pBO.getPolicyByUserName(u.getUserName());
		request.setAttribute("policyList", pList);

		request.getRequestDispatcher("/WEB-INF/views/MyPolicies.jsp").forward(request, response);
		//		response.getWriter().append("Madeit");

	}

	private void disclaimerPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/DisclaimerPage.jsp").forward(request, response);
	}
	private void additionalInfoPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/AdditionalInfo.jsp").forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("Home");

	}
	// for alphanumeric only
	private boolean checkString(String str, int min, int max,String regex)
	{
		return (str.matches(regex)&&str.length()>=min&&str.length()<=max);
	}
	private boolean checkString(String str, int min, int max)
	{
		return (str.length()>=min&&str.length()<=max);
	}
	// split date based on "/" and 
	private Date sqlDate(String date)
	{
		// mm/dd/yyyy
		String[] arr = date.split("-");
		// year, month, day
		return new Date((Integer.valueOf(arr[0])-1900),Integer.valueOf(arr[1])-1,Integer.valueOf(arr[2]));
	}
}
