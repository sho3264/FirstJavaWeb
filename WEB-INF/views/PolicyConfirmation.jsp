<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="style1.html"%>
<meta charset="ISO-8859-1">
</head>
<body>
	<%@include file="header1.html"%>
	<h2>Policy Confirmation</h2>
	<section>
		<article><p></p></article>
		<article>
			<h3>Our customer service representative will contact you shortly
				for premium collection arrangements.</h3>
			<table>
				<tr>
					<td>Quote Id</td>
					<td>${currentPolicy.quoteId}</td>
				</tr>
				<tr>
					<td>Policy Key</td>
					<td>${currentPolicy.policyId}</td>
				</tr>
				<tr>
					<td>Policy Effective Date</td>
					<td>${currentPolicy.getEffectiveDate()}</td>
				</tr>
				<tr>
					<td>Policy End Date</td>
					<td>${currentPolicy.getEndDate()}</td>
				</tr>
				<tr>
					<td>Policy Term</td>
					<td>${currentPolicy.getTerm()}</td>
				</tr>
				<tr>
					<td>Policy Status</td>
					<td>${currentPolicy.getPolicyStatus()}</td>
				</tr>
			</table>
		</article>
	</section>
	<%@ include file="footer1.html" %>
</body>
</html>