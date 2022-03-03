<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이동할 페이지를 요청하는 페이지(요청)</title>
</head>
<body>
<h1>forward 액션태그 연습</h1>
<form method="post" action="move2.jsp">
	이동할 페이지명:<input type="text" name="move"><p>
	<input type="submit" value="보내기">
</form>
</body>
</html>