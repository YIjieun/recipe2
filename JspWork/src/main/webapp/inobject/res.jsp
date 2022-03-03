<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>response(응답객체)</title>
</head>
<body>
<%
//	response.setContentType("text/html; charset=UTF-8");
//	response.sendRedirect("http://www.daum.net");//외부의 사이트주소(반드시 http:// 써야한다.)로 이동
//	response.sendRedirect("./req.jsp");//내부 프로젝트의 특정위치의 페이지로 이동
%>
<script>
//	location.href="http://www.naver.com";//객체명.속성=값
	location.replace("http://www.empas.com");//객체명.함수명
</script>
</body>
</html>