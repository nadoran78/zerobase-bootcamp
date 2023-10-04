<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mission.DataBase" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(odd){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #04AA6D;
  color: white;
}
</style>
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<p> 
		<a href = "index.jsp"> 홈 </a> | <a href = "history.jsp"> 위치 히스토리 목록 </a> | <a href = "request.jsp"> Open API 와이파이 정보 가져오기 </a> | 
		<a href = "bookmark.jsp">즐겨 찾기 보기</a> | <a href="bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
	</p>
	<table id="customers">
		<tr>
			<th>ID</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>조회일자</th>
			<th>비고</th>
		</tr>
		<%
		DataBase db = new DataBase();
		String id = request.getParameter("id");
		if (id != null){
			db.dbDeleteHistory(id);
		}
		
		out.write(db.dbSelectHistory());
		%>
	</table>
</body>
</html>