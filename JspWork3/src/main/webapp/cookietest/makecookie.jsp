<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	//쿠키이름, 저장할값=쿠키객체생성->접속자에게 전송(탐색기 cookie->브라우저에서 확인)
	String cookiename="mycookie";
	//1.쿠키객체 생성
	Cookie c=new Cookie(cookiename,"이이");//1.쿠키이름 2.저장할값
	//2. 쿠키값을 접속자에게 전송하기전에 설정
	c.setMaxAge(60*2);//2분   쿠키저장시간=>60*60(한시간저장) 60*60*24(하루종일저장) 60*60*24*365->1년 내내저장
	//c.setValue("Melone"); 중간에 쿠키값을 변경->setValue(수정할값)
	response.addCookie(c);//addCookie(전송할 쿠키객체명)
	
%>
<body>
<h1>쿠키를 생성하는 예제</h1>
<p>
쿠키가 생성되었습니다.<br>
쿠키의 내용<a href="useCookie.jsp">여기에서 확인</a>
</body>
</html>