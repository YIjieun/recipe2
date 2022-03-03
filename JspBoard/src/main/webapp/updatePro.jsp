<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.Timestamp,yje.board.*"%>
<%
	request.setCharacterEncoding("UTF-8");//한글처리
	//BoardDTO=>Setter Method(5개)+hidden(num)+action(pageNum)
	//BoardDAO=>updateArticle(BoardDTO객체)호출
%>
<jsp:useBean id="article" class="yje.board.BoardDTO" />
<jsp:setProperty property="*" name="article"/>
<%
	String pageNum=request.getParameter("pageNum");//멤버변수가 아니기때문에 따로 호출
	
	BoardDAO dbPro=new BoardDAO();
	int check=dbPro.updateArticle(article);
	
	if(check==1){//글 수정에 성공했다면
		//response.sendRedirect("list.jsp");	
		//http-equiv="Refresh"=>새로고침 옵션
		//content="초단위(몇초동안 멈춘 후 ); url="이동할 페이지"
%>
<meta http-equiv="Refresh" 
		   content="0;url=list.jsp?pageNum=<%=pageNum%>">
<%}else {%>
<script>
	alert("비밀번호가 맞지 않습니다.\n 다시 비밀번호를 확인요망!");
	history.go(-1);
</script>
<%}%>