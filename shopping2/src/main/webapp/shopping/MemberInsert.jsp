<%@ page contentType="text/html;charset=euc-kr"%>
<%
    request.setCharacterEncoding("euc-kr");
%>
<jsp:useBean id="memMgr" class="hewon.MemberMgr"/>
<jsp:useBean id="regBean" class="hewon.RegisterBean" />
<jsp:setProperty name="regBean" property="*" />
<% 
	boolean flag = memMgr.memberInsert(regBean);	
%>
<html>
<head>
<title>ȸ������ Ȯ��</title>
<link href="style.css" rel="stylesheet" type="text/css">
<html>
<body bgcolor="#FFFFCC">
<br><br>
<center>
<%
if(flag){
	  out.println("<b>ȸ�������� ���� �帳�ϴ�.</b><p>");
	  out.println("<a href=Login.jsp>�α���</a>");
	}else{
	  out.println("<b>�ٽ� �Է��Ͽ� �ֽʽÿ�.</b><p>");
	  out.println("<a href=Register.jsp>�ٽ� ����</a>");
	  }
%>
</center>
</body>
</html>