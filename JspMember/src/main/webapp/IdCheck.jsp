<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hewon.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
//searchWord.js=>IdCheck.jsp?mem_id='kkk'&timestamp=?
//xhrObject->xml형태로 받을 수 있도록 하고 싶다.(태그+문자열)
    //추가
    response.setContentType("text/xml;charset=utf-8");
    String outString="";//xhr객체에게 전달할 내용을 담을 변수선언

    String mem_id=request.getParameter("mem_id");
    System.out.println("IdCheck.jsp의 mem_id=>"+mem_id);
    //->member->MemberDAO객체필요=>loginCheck()호출
    MemberDAO memMgr=new MemberDAO();
    boolean check=memMgr.checkId(mem_id);//select id,passwd from member where id=?
    System.out.println("IdCheck.jsp의 check="+check);
%>

<body bgcolor="#FFFFCC">
<%
   if(check){//이미 존재하는 아이디라면
	 outString="<font color='red'><b>이미 존재하는 아이디</b></font>";
   }else{//존재하는 아이디가 아니라면
	 outString="<font color='blue'><b>사용이 가능한 아이디</b></font>";  
   }
  //xhr객체가 받을 수 있도록 전송
  out.println(outString);
  System.out.println(outString);//콘솔에 출력(디버깅)
%>
</body>
</html>













