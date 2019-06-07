<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home Page</title>
</head>
<body>
<%@include file="header2.html" %>
<h2>Admin Screen</h2>
<form action="AdminGetPolicys" method="post">
Search User<br/>
<input type="text" name="UserNameSearch"/>
<input type="submit" value="Search"/>
</form>
<%@include file="footer1.html" %>
</body>
</html>