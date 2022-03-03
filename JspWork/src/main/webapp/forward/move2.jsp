<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지를 이동시키는 역할2</title>
</head>
<body>
<h1>페이지 이동2</h1>
<% 
	//--------------------데이터를 공유할 값(서버의 메모리에 저장)-------------------------------------
		int su=100;//a.jsp이 공유->사용
		Date d=new Date();//오늘 날짜->b.jsp
		
	//서버의 메모리에 저장->객체만 저장=>컬렉션=>HashMap계열(key,value)
	//형식) request | session | application  .setAttribute("키명",저장할값(객체))
	request.setAttribute("total", su);//int->Integer->Object=>형변환
	request.setAttribute("cal", d);
	//---------------------------------------------------------
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