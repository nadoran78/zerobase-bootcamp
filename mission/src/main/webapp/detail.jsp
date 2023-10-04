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
  display: flex;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers td:nth-child(even){background-color: #f2f2f2;}

#customers td:hover {background-color: #ddd;}

#customers th {
  height:20px;
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #04AA6D;
  color: white;
}

#customers td {
  height:20px;
  padding-top: 12px;
  padding-bottom: 12px;
}

#customers th, #customers td { display:block; }

#customers thead {width: 20%; display: flex}

#customers tbody { flex:1; display: flex}

#customers tr {width: 100% }

</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<p> 
		<a href = "index.jsp"> 홈 </a> | <a href = "history.jsp"> 위치 히스토리 목록 </a> | <a href = "request.jsp"> Open API 와이파이 정보 가져오기 </a> | 
		<a href = "bookmark.jsp">즐겨 찾기 보기</a> | <a href="bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
	</p>
	<form action="bookmark-add-submit.jsp" method="post">
		<select name="group" id="group">
			<option value="none" selected disabled>북마크 그룹 이름 선택</option>
			<%
				String num = request.getParameter("num");
				DataBase db = new DataBase();
				out.write(db.dbBookMarkGroupName());
			%>
		</select>
		<input type="hidden" name="num" value=<%= num %>></input>
		<input type="submit" value="즐겨찾기 추가하기" />
	</form>
	
	<table id="customers">
		<thead>
		<tr>
			<th>거리(km)</th>
			<th>관리번호</th>
			<th>자치구</th>
			<th>와이파이명</th>
			<th>도로명주소</th>
			<th>상세주소</th>
			<th>설치위치(층)</th>
			<th>설치유형</th>
			<th>설치기관</th>
			<th>서비스구분</th>
			<th>망종류</th>
			<th>설치년도</th>
			<th>실내외구분</th>
			<th>WIFI접속환경</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>작업일자</th>
		</tr>
		</thead>
		<tbody>
		<%
		
		out.write(db.dbSelectDetail(num));
		%>
		</tbody>
	</table>

</body>
</html>