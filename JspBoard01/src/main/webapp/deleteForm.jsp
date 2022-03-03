<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
   //deleteForm.jsp?num=2&pageNum=1->deletePro.jsp(deleteArticle()호출)
   int num=Integer.parseInt(request.getParameter("num"));//게시물번호
   String pageNum=request.getParameter("pageNum");//페이지 번호
   System.out.println("deleteForm.jsp의 num=>"+num+",pageNum=>"+pageNum);
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">

<script language="JavaScript">            
  function deleteSave(){	
	if(document.delForm.passwd.value==''){
	alert("비밀번호를 입력하십시요.");
	document.delForm.passwd.focus();
	return false;
 }
}         
</script>
</head>

<body bgcolor="#e0ffff">
<center><b>글삭제</b>
<br>
<form method="POST" name="delForm"  action="deletePro.jsp" 
   onsubmit="return deleteSave()"> 
 <table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
  <tr height="30">
     <td align=center  bgcolor="#b0e0e6">
       <b>비밀번호를 입력해 주세요.</b></td>
  </tr>
  <tr height="30">
     <td align=center >비밀번호 :   
       <input type="password" name="passwd" size="8" maxlength="12">
	   <input type="hidden" name="num" value="<%=num%>">
	   <input type="hidden" name="pageNum" value="<%=pageNum%>">
  </td>
 </tr>
 <tr height="30">
    <td align=center bgcolor="#b0e0e6">
      <input type="submit" value="글삭제" >
      <input type="button" value="글목록" 
       onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">     
   </td>
 </tr>  
</table> 
</form>
</body>
</html> 
