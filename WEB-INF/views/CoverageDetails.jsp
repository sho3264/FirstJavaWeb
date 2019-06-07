<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
table {
	witdh: 50%;
}

td {
	text-align: left;
	width: 50%;
}
</style>
<meta charset="ISO-8859-1">
<title>Coverage Details</title>
</head>
<body>

	<%@include file="header1.html"%>

	<h2>Coverage Details</h2>

	<a href="/HomeInsurance_Stephen_Ho/AdditionalInfo" target="_blank">Additional Info</a>
	<br />
	<table>
		<tr>
			<td>Quote Id</td>
			<td>${currentQuote.quoteId}</td>
		</tr>
		<tr>
			<td>Monthly Premium</td>
			<td>$(${currentQuote.monthlyPremium})</td>
		</tr>
		<tr>
			<td>Dwelling Coverage</td>
			<td>$(${currentQuote.dwellingCoverage})</td>
		</tr>
		<tr>
			<td>Detached Structures</td>
			<td>$(${currentQuote.detachedStructures})</td>
		</tr>
		<tr>
			<td>Personal Property</td>
			<td>$(${currentQuote.personalProperty})</td>
		</tr>
		<tr>
			<td>Additional Living Expenses</td>
			<td>$(${currentQuote.addLivingExp})</td>
		</tr>
		<tr>
			<td>Medical Expenses</td>
			<td>$(${currentQuote.medicalExpenses})</td>
		</tr>
		<tr>
			<td>Deductible</td>
			<td>$(${currentQuote.deductible})</td>
		</tr>
	</table>
	<a href="/HomeInsurance_Stephen_Ho/QuoteSummary"> <input type="submit" value="Proceed to buy" />
	</a>
</body>
</html>