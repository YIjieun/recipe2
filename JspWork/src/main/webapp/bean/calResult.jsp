<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="calc.CalcBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요청을 받아서 처리해주는 페이지(결과)</title>
</head>
<body>
<%
//자바빈즈=>클래스 만들어서 메서드 호출(방법1)
/*
	CalcBean ca=new CalcBean();
	ca.setNum1(Integer.parseInt(request.getParameter("num1")));//"23"->23
	ca.setOperator(request.getParameter("operator"));
	ca.setNum2(Integer.parseInt(request.getParameter("num2")));
	ca.calculate();//계산이 종료->result->getResult()호출
	*/
%>
<!--방법2  -->
<jsp:useBean id="ca" class="calc.CalcBean" scope="page" />
<jsp:setProperty name="ca" property="*"/> 
<%ca.calculate(); %><!-- 계산이 종료->result->getResult()호출 -->
 <hr>
계산결과 : <%=ca.getResult()%><br>
</body>
</html>