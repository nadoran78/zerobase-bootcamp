<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mission.DataBase" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String group = request.getParameter("group");
	String num = request.getParameter("num");
	DataBase db = new DataBase();
	db.dbInsertBookMark(group, num);
	String href = "bookmark.jsp?num=" + num;
%>
<input type="hidden" name="num" id="num" value=<%= href %>></input>
<script type="text/javascript">
	function goNext(){
		var url = document.getElementById("num").value;
		alert("북마크 정보를 추가하였습니다.");
				
		location = url;
		
	}
	goNext();
</script>
</body>
</html>