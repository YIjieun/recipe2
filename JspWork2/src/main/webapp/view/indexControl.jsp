<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	String CONTROL=null;//전달할 페이지(=불러올 파일명)
 	String PAGENUM=null;//페이지 번호
 	
 	try{
 		CONTROL=request.getParameter("CONTROL");
 		PAGENUM=request.getParameter("PAGENUM");
 		System.out.println("indexControl.jsp의 CONTROL=>"+CONTROL+
 				" ,PAGENUM=>"+PAGENUM);
 		//만약에 중간에 갑을 전달 못하는 상황이 발생->기본적인 파일을 불러 올 수 있도록 설정
 		if(CONTROL.equals(null)){
 			CONTROL="intro";
 		}
 		if(PAGENUM.equals(null)){
 			PAGENUM="01";
 		}
 	}catch(Exception e){
 		e.printStackTrace();
 	}
 %>
        
<jsp:forward page="/template/template.jsp" >
	<jsp:param value="<%=CONTROL %>" name="CONTROL"/>
	<jsp:param value="<%=PAGENUM %>" name="PAGENUM"/>
</jsp:forward>