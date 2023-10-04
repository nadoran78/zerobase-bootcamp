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
	String id = request.getParameter("id");
	
	DataBase db = new DataBase();
	db.dbDeleteBookMark(id);
%>
<script type="text/javascript">
	function goNext(){
		alert("북마크 정보를 삭제하였습니다.");
				
		location = "bookmark.jsp";
		
	}
	goNext();
</script>
</body>
</html>