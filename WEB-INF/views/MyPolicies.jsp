<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Policies</title>
</head>
<%@include file="header1.html"%>
<body>

	<table>
		<tr>
			<th>Policy Id</th>
			<th>Quote Id</th>
			<th>Policy Effective Date</th>
			<th>Policy End Date</th>
			<th>Policy Term</th>
			<th>Policy Status</th>
		</tr>
		<c:forEach items="${policyList}" var="Policy">
			<tr>
				<td>${Policy.policyId}</td>
				<td>${Policy.quoteId}</td>
				<td>${Policy.effectiveDate}</td>
				<td>${Policy.endDate}</td>
				<td>${Policy.term}</td>
				<td>${Policy.policyStatus}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>