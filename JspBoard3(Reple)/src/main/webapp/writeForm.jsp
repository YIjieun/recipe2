<%@page contentType="text/html;charset=euc-kr"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML>
 <HEAD>
  <TITLE> ������ȭ </TITLE>
  <link href="style.css" rel="stylesheet" type="text/css">
  <script language="JavaScript" src="script.js"></script>
 </HEAD>
 <BODY>
					<c:if test="${num == 0}">
					<center><b>�۾���</b>
					</c:if>
					<c:if test="${num != 0}">
					<center><b>�亯 �۾���</b>
					</c:if>
					<br>
					<!-- hidden���� �ѱ��->writePro.jsp  -->
					  <form method="post" name="writeform" action="/board1/writePro.do"
					  onsubmit="return writeSave()">
					  
					  <input type="hidden" name="num" value="${num}">
					  <input type="hidden" name="ref" value="${ref}">
					  <input type="hidden" name="re_step" value="${re_step}">
					  <input type="hidden" name="re_level" value="${re_level}">
					<!--  -->
					<table width="400" border="1" cellspacing="0" cellpadding="0"  bgcolor="#FFFFFF" align="center">
					   <tr>
						<td align="right" colspan="2" bgcolor="#99CCFF">
							<a href="../board1/list.do"> �۸��</a> 
					   </td>
					   </tr>
					   <tr>
						<td  width="70"  bgcolor="#99CCFF" align="center">�� ��</td>
						<td  width="330">
						   <input type="text" size="10" maxlength="10" name="writer"></td>
					  </tr>
					  <tr>
						<td  width="70"  bgcolor="#99CCFF" align="center" >�� ��</td>
						<td  width="330">
						<!-- �űԱ����� �亯������ ���� ó�� -->
						<c:if test="${num == 0}">
						   <input type="text" size="40" 
									  maxlength="50" name="subject"></td>
						</c:if>
						<c:if test="${num != 0}">
							<input type="text" size="40" 
							 maxlength="50" name="subject" value="[�亯]"></td>
						</c:if>
					  </tr>
					  <tr>
						<td  width="70"  bgcolor="#99CCFF" align="center">Email</td>
						<td  width="330">
						   <input type="text" size="40" maxlength="30" name="email" ></td>
					  </tr>
					  <tr>
						<td  width="70"  bgcolor="#99CCFF" align="center" >�� ��</td>
						<td  width="330" >
						 <textarea name="content" rows="13" cols="40"></textarea> </td>
					  </tr>
					  <tr>
						<td  width="70"  bgcolor="#99CCFF" align="center" >��й�ȣ</td>
						<td  width="330" >
						 <input type="password" size="8" maxlength="12" name="passwd"> 
						 </td>
					  </tr>
					<tr>      
					 <td colspan=2 bgcolor="#99CCFF" align="center"> 
					  <input type="submit" value="�۾���" >  
					  <input type="reset" value="�ٽ��ۼ�">
					  <input type="button" value="��Ϻ���" OnClick="window.location='/board1/list.do'">
					</td></tr></table>   
					</form>    
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