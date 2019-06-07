<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Buy Policy</title>
</head>
<body>
<%@ include file="header1.html" %>
<h2>Buy Policy</h2><div>
<p>Quote Id: <i>${currentQuote.quoteId}</i>
<br/> Note: Policy start date cannot be more than 60 days from today's date
</p>
</div>
<div>
<form action="/HomeInsurance_Stephen_Ho/InsertPolicy" method="post">
<label>Enter Policy Start Date: </label>
<!-- Start Date -->
<input type="date" name="PolicyDate" required/><br/>
<a href="/HomeInsurance_Stephen_Ho/TermsAndConditions" target="_blank" onclick="myfcn()">
Please click and read terms and conditions before buying policy
</a><br/>
<input type="checkbox" name="checkCon" id="chkBox" disabled required/>This is to acknowledge that I have read this terms and conditions of the policy.
<br/><br/>
<input type="submit" value="Submit" id="sub" disabled/>
</form>
</div>
<%@ include file="error.jsp" %>

<script>
function myfcn()
{
	document.getElementById("chkBox").disabled=false;
	document.getElementById("sub").disabled=false;
	}
</script>
</body>
</html>