package com.cts.homeinsurance.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */

@WebFilter({"/GetStarted","/GetQuote","/HomeownerInfo","/PropertyDetails","/showCoverageDetails"
	,"/QuoteSummary","/BuyQuote","/PolicyConfirmation"
	,"/RetrieveQuote","/MyPolicies"})
public class AuthenticationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		if (session.getAttribute("currentUser") != null) {
			chain.doFilter(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/default");
    		HttpServletResponse resp = (HttpServletResponse)response;
			rd.forward(req, resp);
		}
	}
   
}
