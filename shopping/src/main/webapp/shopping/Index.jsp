<%@ page contentType="text/html;charset=EUC-KR"%>

<html>
<head>
<title>Simple Shopping Mall </title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>

<body bgcolor="#996600" topmargin="100">

	<%@ include file="Top.jsp" %>

	<table width="75%" align="center" bgcolor="#FFFF99" height="100%">
	<%
	if(mem_id != null){
	%>
	<tr> 
	<td align="center"><%=mem_id%>�� �湮�� �ּż� �����մϴ�.</a></td>
	</tr>
	<%}else{%>
	<tr>
	<td align="center">�α��� �Ͻ� �� �̿��� �ּ���</a></td>
	</tr>
	<%}%>
	</table>

	<%@ include file="Bottom.jsp" %>

</body>
</html>  
  