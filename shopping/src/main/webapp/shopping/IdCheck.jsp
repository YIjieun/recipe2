<%@ page contentType="text/html;charset=EUC-KR"%>
<jsp:useBean id="memMgr" class="hewon.MemberMgr" />

<%
   String mem_id = request.getParameter("mem_id");
   boolean check = memMgr.checkId(mem_id);
%>
<html>
<head>
<title>ID�ߺ�üũ</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<body bgcolor="#FFFFCC">
<br>
<center>
<b><%=mem_id%></b>
<%
  if(check){
     out.println("�� �̹� �����Ѵ� ID�Դϴ�.<p>");
}else{    
     out.println("�� ��� ���� �մϴ�.<p>");
}
%>
<a href="javascript:this.close();" onClick="win_close()">�ݱ�</a>
</center>
</body>
</html>