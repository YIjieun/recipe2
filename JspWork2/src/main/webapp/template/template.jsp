<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//index.jsp->/view/indexControl.jsp->template.jsp
	String CONTROL=request.getParameter("CONTROL");
 	String PAGENUM=request.getParameter("PAGENUM");
 	System.out.println("template.jsp의 CONTROL=>"+CONTROL+
 				" ,PAGENUM=>"+PAGENUM);
 	//왼쪽 메뉴=>jsp조합
 	String left="/module/"+CONTROL+"Left.jsp";//module/introLeft.jsp
 	//본문내용             /view/intro_01.jsp
 	String content="/view/"+CONTROL+"_"+PAGENUM+".jsp";
 	System.out.println("left=>"+left+" ,content=>"+content);
%>
<html>
<head>
<meta charset="UTF-8">
<title>실질적인 메인 페이지</title>
</head>
<body>
<center>
<table width="800"  height="600"  border="1"  cellpadding="2"  cellspacing="0">
<!--상단메뉴(top.jsp)  -->
	<tr height="50">
		<td colspan="2">
			<jsp:include page="/module/top.jsp"  flush="false " />
		</td>
	</tr>

<!--왼쪽,가운데(본문)  -->
<tr height="500">
	<td width="150" valign="top">
		<jsp:include page="<%=left %>"  flush="false" />
	</td>
	<td width="650" valign="top">
		<jsp:include page="<%=content %>" flush="false" />
	</td>
</tr>
<!--하단부분(bottom.jsp)  -->
	<tr height="50">
		<td colspan="2">
			<jsp:include page="/module/bottom.jsp"  flush="false " />
		</td>
	</tr>
</table>
</center>
</body>
</html>