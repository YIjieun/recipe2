<%
    String admin_id = (String)session.getAttribute("adminKey");

	if(admin_id == null) {
		response.sendRedirect("AdminLogin.jsp");
	}
%>
<table width="75%" align="center" bgcolor="#FFFF99">
  <tr bgcolor="#FFCC00"> 
    <td align="center"><b><a href="../Index.jsp">权</a></b></td>
    <td align="center"><b><a href="AdminLogout.jsp">肺弊酒眶</a></b></td>
    <td align="center"><b><a href="MemberMgr.jsp">雀盔包府</a></b></td>
    <td align="center"><b><a href="ProductMgr.jsp">惑前包府</a></b></td>
    <td align="center"><b><a href="OrderMgr.jsp">林巩包府</a></b></td>
  </tr>
</table> 