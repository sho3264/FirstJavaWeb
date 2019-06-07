<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<h1>Login Page</h1>
	<form action="/HomeInsurance_Stephen_Ho/Login" method="post">
		Username:<input type="text" name="userName" /> <br /> Password:<input
			type="password" name="password" /><br /> <input type="submit"
			value="Login" />
	</form><p>
	<a href="/HomeInsurance_Stephen_Ho/admin/AdminLoginPage">Admin Login</a>
	New User?<a href="/HomeInsurance_Stephen_Ho/showRegistrationPage">Register User</a>
	</p>
	<br/>
	<br/>

	<%@ include file="error.jsp" %>

</body>
</html>