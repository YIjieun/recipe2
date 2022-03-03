<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="lys.board.*, java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>매개변수를 전달</title>
</head>
<body>
<%
	   request.setCharacterEncoding("utf-8");//이름검색이라면 
	   String userid=request.getParameter("userid");
	   System.out.println("autoid.jsp의 userid=>"+userid);
	   //DB연동 확인결과
	   BoardDAO dbPro=new BoardDAO();
	   List<String> name=dbPro.getArticleId(userid);
	   //검색된 갯수만큼 li태그에 담아서 전송
	   for(int i=0;i<name.size();i++){
		   String sname=name.get(i);//0~
		   out.println("<li>"+sname+"</li>");
	   }
	 /* (1)테스트용
	   out.println("<li>testkim</li>");
	   out.println("<li>test</li>");
	   out.println("<li>test2</li>");
	   out.println("<li>test3</li>");
	  */
%>
</body>
</html>