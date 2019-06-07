<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Policies</title>
</head>
<body>
	<%@include file="header2.html"%>
	<h2>Admin Page</h2>
	<h3>User: ${selectedUser}</h3>
	<table>
		<tr>
			<th>Policy Key</th>
			<th>Quote Id</th>
			<th>Policy Effective Date</th>
			<th>Policy End Date</th>
			<th>Policy Term</th>
			<th>Policy Status</th>
			<th>Renew Policy</th>
			<th>Cancel Policy</th>
		</tr>
			<c:forEach items="${policyList}" var="Policy">
			<tr>
				<td>${Policy.policyId}</td>
				<td>${Policy.quoteId}</td>
				<td>${Policy.effectiveDate}</td>
				<td>${Policy.endDate}</td>
				<td>${Policy.term}</td>
				<td>${Policy.policyStatus}</td>
				<td><a href="/HomeInsurance_Stephen_Ho/admin/RenewPolicy?id=${Policy.policyId}">Renew</a></td>
				<td><a href="/HomeInsurance_Stephen_Ho/admin/CancelPolicy?id=${Policy.policyId}">Cancel</a></td>
				
			</tr>
		</c:forEach>
	</table>
	<%@include file="footer1.html"%>
</body>
</html>