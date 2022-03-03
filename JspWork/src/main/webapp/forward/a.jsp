<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>a.jsp</title>
</head>
<body bgcolor="yellow">
<h1>a.jsp로 오신것을 환영합니다.!!</h1>
<%
	//request.setAttribute("total", su);
	//request.getArribute("키명");
	int su=(Integer)request.getAttribute("total");//Objct->Integer->int/* 변환  */
%>
total의 값은?<%=su %>입니다.
<hr><!-- 표현식으로 출력(자동형변환이 된다.) -->
move2.jsp에서 공유한 su의 값은?<%=request.getAttribute("total") %><!-- 변환X -->
</body>
</html>