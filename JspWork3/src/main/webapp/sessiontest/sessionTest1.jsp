<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파라미터값 처리</title>
</head>
<body>
 <%
  	//전달확인
  	String id=request.getParameter("id");
 	String passwd=request.getParameter("passwd");
 	System.out.println("id=>"+id+" ,passwd=>"+passwd);
 	//오라클, MYSQL->member->sql->select->true of false
 	//세션처리=>서버의 메모리에 저장->페이지를 이동해도 정보는 계속 존재
 	session.setAttribute("idKey", id);//키명(부여하는 회사의 규칙키명),저장할값(계정 id or email)
 	session.setMaxInactiveInterval(60);//초단위->1분(세션유지시간)
 %>
 <form method="post" action="sessionTest2.jsp">
   <h2>가장 좋아하는 스포츠를 선택하세요</h2><br>
   <input type="radio" name="sports" value="농구">농구
   <input type="radio" name="sports" value="유도">유도
   <input type="radio" name="sports" value="야구">야구
   <input type="radio" name="sports" value="이종격투기">이종격투기<p>
   
   <h2>가장 좋아하는 계절를 선택하세요</h2><br>
   <select name="season">
     <option value="봄">봄</option>
     <option value="여름">여름</option>
     <option value="가을">가을</option>
     <option value="겨울">겨울</option>
   </select>
   <input type="submit" value="전송">
  </form>
</body>
</html>