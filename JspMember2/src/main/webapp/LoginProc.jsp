<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="hewon.MemberDAO" %>

<jsp:useBean id="memMgr" class="hewon.MemberDAO"  scope="page" />
  <%
     //Login.jsp->LoginProc.jsp->LoginSuccess.jsp
     String mem_id=request.getParameter("mem_id");
     String mem_passwd=request.getParameter("mem_passwd");
     System.out.println("mem_id=>"+mem_id+"mem_passwd=>"+mem_passwd);
     //->member->MemberDAO객체필요->loginCheck()
    // MemberDAO memMgr=new MemberDAO();
     boolean check=memMgr.loginCheck(mem_id, mem_passwd);
     System.out.println("LoginProc.jsp의 check=>"+check);
  %>
  <%
      //check->LoginSuccess.jsp(인증화면),LogError.jsp(에러메세지)
      if(check){//if(check==true)//인증성공
    	  session.setAttribute("idKey",mem_id);
         // response.sendRedirect("LoginSuccess.jsp");//단순히 페이지 이동(공유X)
    	    response.sendRedirect("Login.jsp");
      }else{//id==null
    	  response.sendRedirect("LogError.jsp");
      }
  %>
  
  
  
  
  
  