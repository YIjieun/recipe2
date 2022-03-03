<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>catch(7).jsp</title>
</head>
<body>
<%
	//try{ 실행구문 }catch(Exception e){e.getMessage() to e.toString()}
	//형식) <c:catch var="예외처리객체명">예외처리내용</c:catch>
%>
<h1>
	<c:catch var="e">
		name 매개변수명:<%=request.getParameter("name")%>
		<%
			if(request.getParameter("name").equals("test")){%>
				${param.name}은 test입니다.
		<%}%>
	</c:catch>
	<c:if test="${not empty e}"><!--net empty 출력변수명 -->
   <%-- <c:if test="${e!=null}"> --%><!--예외처리정보가 들어가있다면  -->
   		예외가 발생하였습니다.<br>
   		${e}
   </c:if>
</h1>
</body>
</html>