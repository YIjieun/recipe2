<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포함을 하는 페이지1</title>
</head>
<body>
<h1>포함을 하는 페이지1</h1>
<%
	request.setCharacterEncoding("utf-8");//한글처리
%>
<jsp:include page="sub.jsp" flush="false"/><br>
<%-- 눈에 보이지 않는 주석(스크립트릿,표현식,액션태그를 주석달때 사용)
<%
	String name=request.getParameter("name");	
%>
<b><%=name %>님! 오셨군요. 환영합니다.!!</b> 
--%>
나머지는 sub.jsp가 알아서 해줄거에요~!
</body>
</html>