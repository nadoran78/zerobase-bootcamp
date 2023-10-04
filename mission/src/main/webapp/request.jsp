<%@page import="mission.DataBase"%>
<%@page import="okhttp3.ConnectionPool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "mission.ApiWifi" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>

	<% 
	ApiWifi apiWifi = new ApiWifi();
	int n = apiWifi.getTotalCount();
	%>
	<h1 align="center">  <%=n %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	<div align="center">
		<a href = "index.jsp">홈 으로 가기 </a>	
	</div>
		
</body>
</html>