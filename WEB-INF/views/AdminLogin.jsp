<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<title>Admin Login</title>
<body>
<h1>Admin Login</h1>
<form action="/HomeInsurance_Stephen_Ho/admin/AdminLogin" method="post">
Username:<input type="text" name="userName"/><br/>
Password:<input type="password" name="password"/><br/>
<input type="submit" value="Login Admin"/>
</form>
<%@include file="error.jsp" %>

</body>
</html>