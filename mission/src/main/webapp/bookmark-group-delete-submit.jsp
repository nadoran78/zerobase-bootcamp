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
	String name = request.getParameter("name");
	String num = request.getParameter("num");

	DataBase db = new DataBase();
	db.dbDeleteBookMark2(name);
	db.dbDeleteBookMarkGroup(id);
%>
<script type="text/javascript">
	function goNext(){
		alert("북마크 그룹 정보를 삭제하였습니다.");
				
		location = "bookmark-group.jsp";
		
	}
	goNext();
</script>
</body>
</html>