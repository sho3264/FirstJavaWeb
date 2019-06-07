<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get Started</title>
<%@ include file="style1.html" %>
</head>
<body>
	<%@include file="header1.html" %>
	<h2>Welcome ${currentUser.userName}</h2>
	<h4>Get Started</h4>
	<section>
	<article><p></p></article>
	<article>
		<a href="/HomeInsurance_Stephen_Ho/GetQuote">Get Quote</a><br/> <a
			href="/HomeInsurance_Stephen_Ho/RetrieveQuote">Retrieve Quote</a>
	</article>
	</section>
</body>
</html>