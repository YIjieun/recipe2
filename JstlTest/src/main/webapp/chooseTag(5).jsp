<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>if문 액션태그</title>
</head>
<body>
<%
//	int age=20;
//	String name="bk";
//	if(age > 19)//<c:if test="${조건식}(el로 표시)"
%>
<c:set var="age" value="${20}" />
<c:set var="name" value="${'bk'}" />
<c:if test="true">
	무조건 실행이 되는 구문<br>
</c:if>
<c:if test="${age > 19}">
	당신의 나이는 19살 이상이군요.<br>
</c:if>
<c:if test="${name=='bk' }">
	name의 값은 ${name}입니다.<br>
</c:if>
<!--c:if 액션태그에서는 else구문이 없다.  -->
<c:if test="${name!='bk' }">
	name의 값은 ${name}가 아니군요.<br>
</c:if>
<!--
	다중 조건식 else if~, switch~case
	c:choose
		c:when test="조건식"
			수행할 문장
		/c:when
		c:when test="조건식2"
			수행할 문장2
		/c:when
		,,,
		c:otherwise->else역할
			위의 조건에 해당되지 않는 경우
		/c:otherwise
	/c:choose
	
	el의 내장객체->param
	chooseTag(5).jsp?name2=bk
	chooseTag(5).jsp?age2=20
  -->
  <p>
  <hr>
  <c:choose>
  	<c:when test="${param.name2=='bk'}">
  		<h1>name2 파라미터값은 ${param.name2}입니다.</h1>
  	</c:when>
 	<c:when test="${param.age2 > 18}">
  		<h1>당신의 나이는 18세 이상이군요.</h1>
  	</c:when>
  	<c:otherwise>
  		<li>당신은 'bk'도 아니고 나이도 18세이상도 아니군요!</li>
  	</c:otherwise>
  </c:choose>
</body>
</html>