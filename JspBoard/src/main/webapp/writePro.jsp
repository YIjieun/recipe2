<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.Timestamp,yje.board.*"%>
<%
	request.setCharacterEncoding("UTF-8");//한글처리
	//BoardDTO=>Setter Method(5개)+hidden(4개)
	//BoardDAO=>insertArticle(BoardDTO객체)호출
%>
<jsp:useBean id="article" class="yje.board.BoardDTO" />
<jsp:setProperty property="*" name="article"/>
<%
	//readcount(0)(생략),오늘날짜,원격주수ip
	Timestamp temp=new Timestamp(System.currentTimeMillis());//컴퓨터계산(날짜,시간)
	article.setReg_date(temp);//오늘 날짜 수동으로 저장=>~.getReg_date() ->now()
	article.setIp(request.getRemoteAddr());//원격 ip주소 저장
	
	BoardDAO dbPro=new BoardDAO();
	dbPro.insertArticle(article);
	response.sendRedirect("list.jsp");//입력한후다시 DB접속->새로 고침해서 화면에 새로운글 반연
%>
