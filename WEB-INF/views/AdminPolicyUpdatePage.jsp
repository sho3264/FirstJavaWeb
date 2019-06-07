<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Policy Details</title>
<%@include file="style1.html" %>
</head>
<body>
<%@include file="header2.html" %>
<h2>Policy Details</h2>
<section>
		<article><p></p></article>
		<article>
			<h3>Policy ${status} Successfully</h3>
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


<%@include file="footer1.html" %>
</body>
</html>