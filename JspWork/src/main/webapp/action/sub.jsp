<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답2</title>
</head>
<body>
<!-- sub.jsp -->
<%
	String name=request.getParameter("name");	
%>
<b><%=name %>님! 오셨군요. 환영합니다.!!</b>
</body>
</html>