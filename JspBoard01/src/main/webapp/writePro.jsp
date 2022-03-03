<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.Timestamp,lys.board.*" %>
    
  <%
     request.setCharacterEncoding("utf-8");//한글처리
     //BoardDTO=>Setter Method(5)+hidden(4)=9개
     //BoardDAO=>insertArticle(BoardDTO객체)호출
  %>
 <jsp:useBean id="article"  class="lys.board.BoardDTO" />
 <jsp:setProperty name="article" property="*" />
 <%
     //readcount(0)(생략),오늘날짜,원격주소ip
     Timestamp temp=new Timestamp(System.currentTimeMillis());//컴퓨터계산(날짜,시간)
     article.setReg_date(temp);//오늘 날짜 수동저장=>~.getReg_date() ->now()
     article.setIp(request.getRemoteAddr());//원격 ip주소 저장
     
     BoardDAO dbPro=new BoardDAO();
     dbPro.insertArticle(article);
     response.sendRedirect("list.jsp");//입력한후 다시 DB접속->새로 고침해서 화면에 새로운글 반영
 %>

 
 
 
 
 
 