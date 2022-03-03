<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키를 확인</title>
</head>
<body>
<h1>웹브라우저에 저장된 쿠키를 가져오는 예제</h1>
<%
	Cookie[] cookies=request.getCookies();//메서드의 반환형
	if(cookies!=null) {//검색할 쿠키의 정보가 들어있다면
		for(int i=0;i<cookies.length;i++){
			//쿠키이름 mycookie 찾았다면 
			if(cookies[i].getName().equals("mycookie")){%>
			cookieName : <%=cookies[i].getName()%><br>
			cookieValue : <%=cookies[i].getValue() %><br>
<% 				
			}
		}
	}
	
%>
</body>
</html>