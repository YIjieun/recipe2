<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    java.util.HashMap mapData=new java.util.HashMap();//사물함구조
    mapData.put("name","홍길동");//map.get(키명(name))
    mapData.put("today",new java.util.Date());//날짜객체
//    int []intArray={1,2,3,4,5};
%>
<!--변수선언 및 초기값 액션테그 set-->
<!--  			속성명							자료형 및 저장할값	-->			
<c:set var="intArray" value="<%=new int[]{1,2,3,4,5}%>" />
<!-- map도 같은 주소를 가진 동일한 객체 -->
<c:set var="map" value="<%=mapData%>" />
<html>
<head>
<meta charset="UTF-8">
<title>froEach(8).jsp</title>
</head>
<body>
<%
	//1~100까지의 합중에서 홀수값을 출력
/* int sum=0;
	for(int i=1;i<=100;i+=2){//c:forEach var="초기변수명" begin="시작값" end="종료값"
		sum+=i;//step="증가치" (default step=1인 경우 생략) 1이 아닌경우는 생략X
		//sum=sum+i;
	}
	out.println(sum); */
%>
<h4>1~100까지의 홀수의 값</h4>
<c:set var="sum" value="0" />
<c:forEach var="i" begin="1" end="100" step="2">
	<c:set var="sum" value="${sum+i}" /><!--수정의 의미 -->
</c:forEach>
결과: ${sum}
<hr>
<h4>배열의 값, 컬렉션객체의 값을 출력</h4>
<%-- 
<c:set var="intArray" value="<%=new int[]{1,2,3,4,5}%>" />
			items="${출력할 내용이 들어가 있는 객체명}"
 --%>
	<c:forEach var="i"  items="${intArray}"  begin="0" end="4">
		<h1>[${i}]</h1>
	</c:forEach>
	<!-- 
		var="꺼낸객체명" items(배열 또는 컬렉션객체(객체명.key,객체명.value)
	 -->
	<c:forEach var="a" items="${map}">
		<h1>${a.key}=${a.value}</h1>
	</c:forEach>
<hr>
<%
	//문자열에서 구분되어서 분래되는 단어를 토콘(단어),(토콘구분자)
	String str2="JAVA,JSP,Spring,jQuery";//구분자 ,
	//c:forTokens var="출력변수명(=객체명)" items="${출력객체명}" delims="구분기호"
%>
	<c:set var="s" value="<%=str2%>"/>
	<c:forTokens var="st" items="${s}" delims="," >
	<%-- <c:forTokens var="st" items="고양이|강아지|호랑이" delims="|"> --%>
	<%-- <b>${st}&nbsp;</b> --%>
	<b><c:out value="${st}"/></b>
	</c:forTokens>
</body>
</html>