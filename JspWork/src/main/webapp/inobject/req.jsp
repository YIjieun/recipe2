<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체의 개요 및 사용법(reauest)</title>
</head>
<body>
<h2>
1. 서버의 도메인 이름:<%=request.getServerName() %><br>
2. 서버의 포트번호:<%=request.getServerPort() %><br>
****3. 요청된 URL에서의 경로:<%=request.getRequestURL() %><br>
****4. 요청된 URI에서의 경로:<%=request.getRequestURI() %><br>
**5. 원격주소 ip:<%=request.getRemoteAddr() %><br>
6. 클라이언트가 요청한 프로토콜:<%=request.getProtocol() %><br>
***7. 요청방식(Get or Post):<%=request.getMethod() %><br>
</h2>
</body>
</html>