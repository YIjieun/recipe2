<%@page contentType="text/html;charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML>
 <HEAD>
  <TITLE> ������ȭ </TITLE>
  <link href="style.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="../css/style.css" type="text/css"/>
<script type="text/javascript" src="../js/dolphin.js"></script>
<script language="JavaScript">      
<!--      
  function deleteSave(){	
	if(document.delForm.passwd.value==''){
	alert("��й�ȣ�� �Է��Ͻʽÿ�.");
	document.delForm.passwd.focus();
	return false;
 }
}    
// -->      
</script>
 </HEAD>
 <BODY>
 <form method="POST">
 <table width="1026" border="0">
   <tr>
     <td width="6">&nbsp;</td>
     <td width="1000">&nbsp;</td>
     <td width="10">&nbsp;</td>
   </tr>
   <tr>
     <td height="464">&nbsp;</td>
     <td rowspan="2"><TABLE width="1000" height="507" border="1" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
       <!-- ��ܸ޴� -->
       <TR>
         <TD height="50">�ΰ� �غ� ��. </TD>
         <TD height="50">

<form method="POST">
	<div id="dolphincontainer">
	<div id="dolphinnav">
	<ul>
	<li><a HREF="../view/intro_01.jsp" rel="main"><span>Home</span></a></li>
	<li><A HREF="../board1/list.do" rel="board"> <span>�Խ���</span></a></li>
	<li><A HREF="../view/faq_01.jsp" rel="faq"><span>FAQ</span></a></li>
	<li><A HREF="../view/support_01.jsp" rel="cen"><span >������</span></a></li>
	<li><A HREF="../adboard/list.do" rel="qna"><span>������ϱ�</span></a></li>
	</ul>
	</div>
	<div id="dolphin_inner">
	<div id="main" class="innercontent"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;����&nbsp;|&nbsp;��������</font></div>
	<div id="board" class="innercontent"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;�Խ���&nbsp;</font></div>
	<div id="faq" class="innercontent"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;faq&nbsp;</font></div>
	<div id="cen" class="innercontent"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;������&nbsp;</font></div>
	<div id="qna" class="innercontent"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;Q&A&nbsp;</font></div>
	</div>
	</div>
<script type="text/javascript">
dolphintabs.init("dolphinnav", 1)
</script>
</form>	
		</TD>
       </TR>
       <!--��������  -->
       <TR>
         <!-- �����޴� -->
         <TD width="300" height="39" valign="top">
				<c:if test="${mem_id==null}">
				<jsp:include page="/LoginForm/Login.jsp" flush="false"/>
				</c:if>
				<c:if test="${mem_id!=null}">
				<jsp:include page="/LoginForm/Mem_list.jsp" flush="false"/>
				</c:if>    </TD>
         <!--�Ѿ���� �Ķ���Ͱ��� ���� �������뺯�� -->
         <TD rowspan="2" valign="top" name="show">
<center><b>�ۻ���</b>
<br>
<form method="POST" name="delForm"  
       action="/board1/deletePro.do?pageNum=${pageNum}" 
   onsubmit="return deleteSave()"> 
 <table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
  <tr height="30">
     <td align=center  bgcolor="#99CCFF">
       <b>��й�ȣ�� �Է��� �ּ���.</b></td>
  </tr>
  <tr height="30">
     <td align=center >��й�ȣ :   
       <input type="password" name="passwd" size="8" maxlength="12">
	   <input type="hidden" name="num" value="${num}"></td>
 </tr>
 <tr height="30">
    <td align=center bgcolor="#99CCFF">
      <input type="submit" value="�ۻ���" >
      <input type="button" value="�۸��" 
       onclick="document.location.href='/board1/list.do?pageNum=${pageNum}'">     
   </td>
 </tr>  
</table> 
</form>
		 </TD>
       </TR>
       <TR>
         <TD width="300" valign="top"><jsp:include page="/module/introLeft.jsp" flush="false"/></TD>
       </TR>
       <TR>
         <TD colspan="2" height="50">
           <jsp:include page="/module/bottom.jsp" flush="false"/>     
    </TD>
       </TR>
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