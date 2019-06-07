<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Retrieve Quote</title>
</head>
<body>
	<%@include file="header1.html"%>
	<table>
		<tr>
			<th>Quote Id</th>
			<th>Residence Type</th>
			<th>Address Line 1</th>
			<th>Address Line 2</th>
			<th>City</th>
			<th>State</th>
			<th>Zip</th>
			<th>Residence Use</th>
		</tr>
		<c:forEach items="${quotesList}" var="entry">
			<tr>
				<td><a href ="/HomeInsurance_Stephen_Ho/showCoverage?id=${entry.key}">${entry.key}</a></td>
				<td>${entry.value.residenceType}</td>
				<td>${entry.value.addressLine1}</td>
				<td>${entry.value.addressLine2}</td>
				<td>${entry.value.city}</td>
				<td>${entry.value.locationState}</td>
				<td>${entry.value.zipCode}</td>
				<td>${entry.value.residenceUse}</td>
			</tr>
		</c:forEach>
	</table>


	<%@include file="footer1.html"%>

</body>
</html>