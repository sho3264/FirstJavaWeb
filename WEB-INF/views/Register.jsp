<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
</head>
<body>
<h1>Register User</h1>
	<form action="/HomeInsurance_Stephen_Ho/RegisterUser" method="post">
		Username:<input type="text" name="userName" /><br /> Password:<input
			type="password" name="password" /><br /> Confirm Password:<input
			type="password" name="confirmPassword" /><br /> <input
			type="submit" value="Register" />
	</form>
	<br/>
	<a href="/HomeInsurance_Stephen_Ho/Login">Cancel</a>

	<br />
	<br />
	<%@include file="error.jsp"%>

</body>
</html>