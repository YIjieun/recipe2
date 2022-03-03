<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h1>값 출력</h1>
<%
	request.setCharacterEncoding("utf-8");//한글처리
%>
<%
	String name=request.getParameter("name");	
%>
<b><%=name %> 입니다.</b><p>
<b>이름은(el)${param.name} 입니다.</b> 
</body>
</html>