<%@page import="mission.DataBase"%>
<%@page import="okhttp3.ConnectionPool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "mission.ApiWifi" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	
	ApiWifi apiWifi = new ApiWifi();
	DataBase db = new DataBase();
	db.dbInsert();
	%>
</body>
</html>