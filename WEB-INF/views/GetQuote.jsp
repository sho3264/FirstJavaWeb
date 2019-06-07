<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get Quote</title>
</head>
<body>
	<%@ include file="header1.html" %>
	<h2>Get Quote</h2>
	
	<form action="/HomeInsurance_Stephen_Ho/InsertLocation" method="post">
	Resident Type <select name="ResidentType">
	<option value="Single-Family Home">Single-Family Home</option>
	<option value="Condo">Condo</option>
	<option value="Townhouse">Townhouse</option>
	<option value="Rowhouse">Rowhouse</option>
	<option value="Duplex">Duplex</option>
	<option value="Apartment">Apartment</option>
	</select><br/>
	Address Line 1<input type="text" name="AddressLine1"/><br/>
	Address Line 2<input type="text" name="AddressLine2"/><br/>
	State<input type="text" name="State"/><br/>
	City<input type="text" name="City"/><br/>
	Zip<input type="number" name="Zip" /><br/>
	Residence Use<select name="ResidenceUse">
	<option value="Primary">Primary</option>
	<option value="Secondary">Secondary</option>
	<option value="Rental Property">Rental Property</option>
	</select><br/>
	<input type="submit" value="Continue"/>
	</form>
	
	<%@include file="error.jsp" %>

	
</body>
</html>