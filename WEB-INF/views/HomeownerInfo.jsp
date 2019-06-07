<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homeowner Information</title>
</head>
<body>
<%@ include file="header1.html" %>
<h2>Homeowner Information</h2>

<form action="/HomeInsurance_Stephen_Ho/InsertHomeowner" method="post">
First Name<input type="text" name="FirstName" required/><br/>
Last Name<input type="text" name="LastName" required/><br/>
Date of Birth<input type="date" name="dob" required/><br/>
Are you retired<input type="radio" name="RetiredStatus" value="1" required/>Yes
<input type="radio" name="RetiredStatus" value="0">No<br/>
Social Security Number<input type="number" name="ssn" required/><br/>
Email Address<input type="email" name="email" required/><br/>
<input type="submit" value="Continue"/>
</form>

<%@ include file="error.jsp" %>

</body>
</html>