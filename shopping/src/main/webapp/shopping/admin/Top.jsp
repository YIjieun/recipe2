<%
    String admin_id = (String)session.getAttribute("adminKey");

	if(admin_id == null) {
		response.sendRedirect("AdminLogin.jsp");
	}
%>
<table width="75%" align="center" bgcolor="#FFFF99">
  <tr bgcolor="#FFCC00"> 
    <td align="center"><b><a href="../Index.jsp">Ȩ</a></b></td>
    <td align="center"><b><a href="AdminLogout.jsp">�α׾ƿ�</a></b></td>
    <td align="center"><b><a href="MemberMgr.jsp">ȸ������</a></b></td>
    <td align="center"><b><a href="ProductMgr.jsp">��ǰ����</a></b></td>
    <td align="center"><b><a href="OrderMgr.jsp">�ֹ�����</a></b></td>
  </tr>
</table> 