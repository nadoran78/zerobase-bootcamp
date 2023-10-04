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
<script type="text/javascript">
function success(position){
	document.getElementById("lat").setAttribute('value', position.coords.latitude);
	document.getElementById("lnt").setAttribute('value', position.coords.longitude);
}

function setInputValue(){
	navigator.geolocation.getCurrentPosition(success);
}
</script>
</head>
<body>
	<h1>와이파이 정보 구하기 </h1>
	<p> 
		<a href = "index.jsp"> 홈 </a> | <a href = "history.jsp"> 위치 히스토리 목록 </a> | <a href = "request.jsp"> Open API 와이파이 정보 가져오기 </a> | 
		<a href = "bookmark.jsp">즐겨 찾기 보기</a> | <a href="bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
	</p>
	 
	<form action="index.jsp" method="get">
		LAT : <input type="number" step="0.0000001" name="lat" id="lat"></input>
		, LNT : <input type="number" step="0.0000001" name="lnt" id="lnt" ></input> 
		<input type="button" value="내 위치 가져오기" onclick="setInputValue();"/> 
		<input type="submit" value="근처 WIFI 정보 보기"/>
	</form>
	
	<table id="customers">
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
	<%
	String lat = request.getParameter("lat");
	String lnt = request.getParameter("lnt");
		
	String empty = 
			"<tr>\n" +
			"<td colspan=\"17\" style=\"text-align:center;\"> 위치 정보를 입력한 후에 조회해 주세요. </td>\n"+
			"</tr>";
		
	if (lat == null || lnt == null || lat.equals("") || lnt.equals("")){
		out.write(empty);	
	} else {
		double doubleLat = Double.parseDouble(lat);
		double doubleLnt = Double.parseDouble(lnt);
		DataBase db = new DataBase();
		out.write(db.dbSelect20(doubleLat, doubleLnt));
		db.dbInserHistory(doubleLat, doubleLnt);
		
	}
	
		
	%>
	</table>


</body>

</html>