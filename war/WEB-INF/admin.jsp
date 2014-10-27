<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>
	<%
		UserService userService = UserServiceFactory.getUserService();
		String currentUserEmail = userService.getCurrentUser().getEmail();
		String logoutUrl = userService.createLogoutURL("/");
	%>
	<div>
		<div><a href="<%=logoutUrl%>">Logout</a></div>
		<div><i>Welcome (</i><%=currentUserEmail %><i>)</i></div>
	</div>
	<div>
		<h1>Administration page</h1>
		<h6>Only Admins have the right to be here (those choosed by the Daemon)</h6>
		<hr>
	</div>
	<div>
		<button type="button">Import Image (s)</button>
		<button type="button">View All Bukcets</button>
	</div>
</body>
</html>