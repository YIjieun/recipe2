<%
	String mem_id = (String)session.getAttribute("idKey");
	
	String log="";
	if(mem_id == null) log ="<a href=Login.jsp>�α���</a>";
	else log = "<a href=Logout.jsp>�α׾ƿ�</a>";

	String mem="";
	if(mem_id == null) mem ="<a href=Register.jsp>ȸ������</a>";
	else mem = "<a href=MemberUpdate.jsp>ȸ������</a>";
%>

<table width="75%" align="center" bgcolor="#FFFF99">
  <tr bgcolor="#FFCC00"> 
    <td align="center"><b><%=log%></b></td>
    <td align="center"><b><%=mem%></b></td>
    <td align="center"><b><a href="ProductList.jsp"%>��ǰ���</a></b></td>
    <td align="center"><b><a href="CartList.jsp">��ٱ���</a></b></td>
    <td align="center"><b><a href="OrderList.jsp">���Ÿ��</a></b></td>
  </tr>
</table>
