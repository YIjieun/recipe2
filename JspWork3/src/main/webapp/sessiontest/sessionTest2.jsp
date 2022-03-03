<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>실제로 처리</title>
</head>
<body>
<%
      request.setCharacterEncoding("utf-8");//한글처리
      //입력을 받거나 링크로 연결(get방식)=>request.getParameter("매개변수명")
      String sports=request.getParameter("sports");
      String season=request.getParameter("season"); 
      //앞에서 session.setAttribute("idKey",id)<->request.getAttribute("idKey") 받는다.
      String id=(String)session.getAttribute("idKey");//Object->String 자료형 변경
      //접속한 브라우저도 구분(세션id(번호표)), 매번바뀐다. 은행 번호표같은것.
      String sessionid=session.getId();
      int interval=session.getMaxInactiveInterval();//60초
      
      if(id!=null) {//현재 계정으로 로그인했다면
%> 
		<b><%=id %></b>님이 좋아하시는 스포츠와 게절은<p>
		<b><%=sports %></b>와<b><%=season %></b>이군요!!<p>
		세션id : <%=sessionid %><p>
		세션유지시간: <%=interval %><p>
		<%
			session.invalidate();//연결해제(세션객체 정보를 자동으로 삭제)->로그아웃을 할때
         }else{//id==null(즉 로그인한적이 없거나 연결시간이 초과한 경우)
        	 out.println("세션의 연결시간이 초과되었습니다. 다시 로그인해주세요!");
         }
		%>   	      
</body>
</html>







