<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지를 이동시키는 역할</title>
</head>
<body>
<h1>페이지 이동</h1>
<% 
	String move=request.getParameter("move");//a.jsp or b.jsp
	System.out.println("move=>"+move);
	//equals("a.jsp") or equals("b.jsp") else alert("요청하신 페이지가 없습니다.")=>move.jsp
	if(move.equals("a.jsp")){
	//response.sendRedirect("a.jsp");//단순히 이동만 시킬때 사용하는 방법	
%>
<!--forward 액션태그명 page="이동할페이지명"(데이터를 공유하면서 이동)  -->
<jsp:forward page='a.jsp'/><br>
<%}else if (move.equals("b.jsp")){%>
<jsp:forward page='b.jsp'/><br>
<%}else { %>
	<script>
		alert("요청하신 페이지가 없습니다.\n 다시 확인하신 후 시도하세요.");
		location.href="forward.jsp";//history.back();
	</script>
	<%} %>
<%-- <jsp:forward page='<%=move %>' /> --%>
</body>
</html>