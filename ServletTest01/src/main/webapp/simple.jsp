<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터를 공유해서 출력</title>
</head>
<body>
<%
   //request.setAttribute("result", resultObject);
   //request.getAttribute("result(키명)")
   //=${키명}와 기능이 같다.
%>
처리결과:<%=request.getAttribute("result") %><br>
처리결과2:${result}
</body>
</html>