<%@page contentType="text/html;charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML>
 <HEAD>
  <TITLE> ������ȭ </TITLE>
  <link href="style.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="../css/style.css" type="text/css"/>
<script type="text/javascript" src="../board1/script.js"/></script>
 </HEAD>
 <BODY>
<center><b>�۳��� ����</b>
<br>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0"  bgcolor="#FFFFFF" align="center">  
  <tr height="30">
    <td align="center" width="125" bgcolor="#99CCFF">�۹�ȣ</td>
    <td align="center" width="125" align="center">
	     ${article.num}</td>
    <td align="center" width="125" colspan="2"></td>
  </tr>

  <tr height="30">
    <td align="center" width="125" bgcolor="#99CCFF">�ۼ���</td>
    <td align="center" width="125" align="center">
	     ${article.writer}</td>
    <td align="center" width="125" bgcolor="#99CCFF" >�ۼ���</td>
    <td align="center" width="125" align="center">
	     ${article.reg_date}</td>
  </tr>
  <tr height="30">
    <td align="center" width="125" bgcolor="#99CCFF">������</td>
    <td align="center" width="375" align="center" colspan="3">
	     ${article.subject}</td>
  </tr>
  <tr>
    <td align="center" width="125" bgcolor="#99CCFF">�۳���</td>
    <td align="left" width="375" colspan="3">
    <pre>${article.content}</pre></td>
  </tr>
  <tr height="30">      
    <td colspan="4" bgcolor="#99CCFF" align="right" > 
	  <input type="button" value="�ۼ���" 
       onclick="document.location.href='../board1/updateForm.do?num=${article.num}&pageNum=${pageNum}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
	  <input type="button" value="�ۻ���" 
       onclick="document.location.href='../board1/deleteForm.do?num=${article.num}&pageNum=${pageNum}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="��۾���" 
       onclick="document.location.href='../board1/writeForm.do?num=${article.num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="�۸��" 
       onclick="document.location.href='../board1/list.do?pageNum=${pageNum}'">
    </td>
  </tr>
</table>
</form>   

<center>
	<pre>
	<table width="600" cellspacing="0" cellpadding="2" align="center" background=F8F8F8 border=0>  
	<c:forEach var="commentList" items="${commentList}">
	<TR>
		
		<td width="15%">${commentList.writer_id}</td>
		<TD width=350>${commentList.content} </TD>
		<TD><fmt:formatDate value="${commentList.reg_date}" timeStyle="medium"  pattern="yy.MM.dd  (hh:mm)"/></TD>
		<TD align=right>
		<!--<c:if test="${mem_id==commentList.writer_id}" >
		<A HREF="../board1/commentDel.do?num=${article.num}&reboard1_num=${commentList.reboard1_num}&pageNum=${pageNum}">�ۻ���</A>
		</c:if> -->
		<A HREF="../board1/CommentDel.do?num=${article.num}&reboard1_num=${commentList.reboard1_num}&pageNum=${pageNum}">�ۻ���</A>
		
		</TD>
		
	</TR>
	<tr>
		<td colspan=4 height="1" background="../board1/images/line_h01.gif"></td>
	</tr>
	<tr>
		<td colspan=4 height="1" background="../board1/images/line_h01.gif"></td>
	</tr>
	</c:forEach>
	</TABLE></pre>
	

	<form method="post" name="add_form" action="../board1/CommentPro.do">

	<table width="600" cellspacing="0" cellpadding="0" align="center"  bgcolor=F8F8F8 >  
	<TR>
		
		<TD style=padding:10;><img src="../board1/images/icon_arrow2.gif" align=absmiddle><input type="hidden" name="writer_id" size="10" value="${mem_id}">&nbsp;${mem_id}
		</td>
		<TD style=padding:10;>
		<textarea name="content" rows="4" cols="70"></TEXTAREA></TD>
		<INPUT TYPE="hidden" name="num" value="${article.num}">
		<INPUT TYPE="hidden" name="pageNum" value="${pageNum}">
		<input type="hidden" name="writer_id" value="${mem_id}">
		<input type="hidden" name="content" value="content">
		<TD  style=padding:5;>
		<img src="../board1/images/button_save.gif" border=0 onclick="comment()">  
		</TD>
		
	</TR>
	</TABLE>
	</form>    
	</center>
       <!-- end -->
     </TABLE></td>
     <td>&nbsp;</td>
   </tr>
   <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
   </tr>
   <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
   </tr>
 </table>
 </form>
 </BODY>
</HTML>