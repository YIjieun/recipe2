<%@ page contentType="text/html;charset=euc-kr"%>
<% 
    request.setCharacterEncoding("euc-kr");
%>
<jsp:useBean id="regBean" class="hewon.RegisterBean"/>
  <jsp:setProperty name="regBean" property="*" />

<html>
<head>
<title>ȸ������ Ȯ��</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<body bgcolor="#996600">
<br>
<table align="center" border="0" cellspacing="0" cellpadding="5" >
  <tr> 
    <td align="center" valign="middle" bgcolor="#FFFFCC"> 
	<table border="1" cellspacing="0" cellpadding="2"  align="center">
        <form name="regForm" method="post" action="MemberInsert.jsp">
          <tr align="center" bgcolor="#996600"> 
            <td colspan="3"><font color="#FFFFFF"><b> 
              <jsp:getProperty name="regBean" property="mem_name" />
              ȸ������ �ۼ��Ͻ� �����Դϴ�. Ȯ���� �ּ���</b></font> </td>
          </tr>
          <tr> 
            <td>���̵�</td>
            <td><input type="text" name="mem_id" 
			           value="<jsp:getProperty name="regBean" property="mem_id" />"></td>
          </tr>
          <tr> 
            <td>�н�����</td>
            <td><input type="text" name="mem_passwd" 
			           value="<jsp:getProperty name="regBean" property="mem_passwd" />"></td>
          </tr>
          <tr> 
            <td>�̸�</td>
            <td><input type="text" name="mem_name" 
			           value="<jsp:getProperty name="regBean" property="mem_name" />"></td>
          </tr>
		  <tr> 
            <td>�ֹε�Ϲ�ȣ</td>
            <td><input type="text" name="mem_num1" 
			           value="<jsp:getProperty name="regBean" property="mem_num1" />"> -
			    <input type="text" name="mem_num2" 
				       value="<jsp:getProperty name="regBean" property="mem_num2" />"></td>
          </tr>
          <tr> 
            <td>�̸���</td>
           <td><input type="text" name="mem_email"  size="30" 
		              value="<jsp:getProperty name="regBean" property="mem_email" />"></td>
          </tr>
          <tr> 
            <td>��ȭ��ȣ</td>
            <td><input type="text" name="mem_phone" 
			           value="<jsp:getProperty name="regBean" property="mem_phone" />"></td>
          </tr>
		  <tr> 
            <td>�����ȣ</td>
            <td><input type="text" name="mem_zipcode" 
			           value="<jsp:getProperty name="regBean" property="mem_zipcode" />"></td>
          </tr>
		  <tr> 
            <td>�ּ�</td>
           <td><input type="text" name="mem_address" size="50" 
		              value="<jsp:getProperty name="regBean" property="mem_address" />"></td>
          </tr>
		  <tr> 
            <td>����</td>
            <td><input type="text" name="mem_job" 
			           value="<jsp:getProperty name="regBean" property="mem_job" />"></td>
          </tr>
          <tr> 
            <td colspan="2" align="center"><input type="submit" value="Ȯ�οϷ�"> 
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
            <input type="button" value="�ٽþ���" onClick="history.back()"> 
            </td>
          </tr>
        </form>
      </table>
 </td>
  </tr>
</table>
</body>
</html>
