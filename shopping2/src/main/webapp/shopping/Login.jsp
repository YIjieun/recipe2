<%@ page contentType="text/html;charset=EUC-KR"%>
<%
    String mem_id = (String)session.getAttribute("idKey");
%>
<html>
<head>
<title>�α���</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js">
</script>
</head>
<body bgcolor="#FFFFCC">
<center>
<br><br><br>
<%
	if(mem_id != null){
%>
<b><%=mem_id%></b>�� ȯ�� �մϴ�.<p>
���ѵ� ����� ��� �� ���� �ֽ��ϴ�.<p>
<a href="Logout.jsp">�α׾ƿ�</a>
<%}else{%>
  <table>
    <form name="login" method="post" action="LoginProc.jsp">
      <tr> 
        <td align="center" colspan="2"><h4>�α���</h4></td>
      </tr>
      <tr> 
        <td>�� �� ��</td>
        <td><input type="text" name="mem_id"></td>
      </tr>
      <tr> 
        <td>��й�ȣ </td>
        <td><input type="password" name="mem_passwd"> </td>
      </tr>
      <tr> 
        <td colspan="2"> <div align="right"> 
            <input type="button" value="�α���" onclick="loginCheck()">
            &nbsp;<input type="button" value="ȸ������" onclick="memberReg()"></div></td>			
      </tr>
    </form>
  </table>
<%}%>
</center>
</body>
</html>