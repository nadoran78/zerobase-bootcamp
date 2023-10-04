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

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #04AA6D;
  color: white;
  width: 20%;
}
</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<p> 
		<a href = "index.jsp"> 홈 </a> | <a href = "history.jsp"> 위치 히스토리 목록 </a> | <a href = "request.jsp"> Open API 와이파이 정보 가져오기 </a> | 
		<a href = "bookmark.jsp">즐겨 찾기 보기</a> | <a href="bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
	</p>
	<p>북마크 그룹 이름을 삭제하시겠습니까?</p>
	<%
		String id = request.getParameter("id");
		DataBase db = new DataBase();
		String[] array = db.dbSelectOneBookMarkGroup(id);
		String name = array[0];
		String num = array[1];
	%>
	<form action="bookmark-group-delete-submit.jsp" method="post" accept-charset="UTF-8">
		<table id="customers">
			<tr>
				<th>북마크 이름</th>
				<td><input id="name" name="name" value=<%= name %>></input></td>
			</tr>
			<tr>
				<th>순서</th>
				<td><input type="number" id="num" name="num" value=<%= num %>></input></td>		
			</tr>
			<tr>
				<td style="text-align:center" colspan="2">
						<a href="bookmark-group.jsp">돌아가기</a> | 
						<input type="hidden" name="id" value=<%= id %> ></input>
						<input type="submit" value="삭제"/>
					
				</td>		
			</tr>
			
		</table>
	</form>

</body>
</html>