<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Property Details</title>
</head>
<body>
<%@ include file="header1.html" %>
<h2>Property Details</h2>

<form action="/HomeInsurance_Stephen_Ho/InsertProperty" method="post">
What is the market value of your home? $
<input type="number" name="MarketValue" min ="0" step="0.01" required/><br/>
What year was your home originally built?<input type="number" name="YearBuilt" value="${currentYear}" min="0" required/><br/>
Square footage<input type="number" name="SqFootage" min="0" required/>sq ft<br/>
Dwelling Style<select name="DwellingStyle">
<option value="1 Story">1 Story</option>
<option value="1 1/2 Stories">1 1/2 Stories</option>
<option value="2 Stories">2 Stories</option>
<option value="2 1/2 Stories">2 1/2 Stories</option>
<option value="3 Stories">3 Stories</option>
</select><br/>

Roofing Material <select name="RoofingMaterial">
<option value="Concrete">Concrete</option>
<option value="Clay">Clay</option>
<option value="Rubber">Rubber</option>
<option value="Steel">Steel</option>
<option value="Tin">Tin</option>
<option value="Wood">Wood</option>
</select><br/>

Type of Garage <select name="GarageType">
<option value="Attached">Attached</option>
<option value="Detached">Detached</option>
<option value="Basement">Basement</option>
<option value="Built-In">Built-In</option>
<option value="None">None</option>
</select><br/>

Number of Full-Baths<select name="FullBaths">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4 or more</option>
</select><br/>

Number of Half-Baths<select name="HalfBaths">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4 or more</option>
</select><br/>

Swimming Pool <input type="radio" name="SwimmingPool" value="1" required>Yes
<input type="radio" name="SwimmingPool" value="0" required>No
<br/>
<input type="submit" value="Continue"/>

</form>

<%@include file="error.jsp" %>
</body>
</html> 