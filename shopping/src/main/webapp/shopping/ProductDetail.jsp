<%@ page contentType="text/html;charset=EUC-KR" %>
<%@ page import="java.util.*, product.*"%>
<jsp:useBean id="proMgr" class="product.ProductMgr" />

<html>
<head>
<title>Simple Shopping Mall</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>

<body bgcolor="#996600" topmargin="100">

	<%@ include file="Top.jsp" %>
	
	<%ProductBean product= proMgr.getProduct(request.getParameter("no"));%>

	<table width="75%" align="center" bgcolor="#FFFF99">
	<tr> 
	<td align="center" bgcolor="#FFFFCC">

		<table width="95%" align="center" bgcolor="#FFFF99" border="1">
		<tr bgcolor="#996600"> 
		<td colspan="3" align="center"><font color="#FFFFFF"><%=product.getName()%></font></td>
		</tr>
		<tr> 
		<td width="20%">
		<img src="data/<%=product.getImage()%>" height="150" width="150">
		</td>
		<td width="30%" valign="top">
			<form name="cart" action="CartProc.jsp">
			<table border="0" width="100%" heigth="100%">
			<tr>
			<td><b>��ǰ�̸� : </b><%=product.getName()%></td>
			</tr>			
			<tr>
			<td><b>��    �� : </b><%=product.getPrice()%></td>
			</tr>
			<tr>
			<td><b>��    �� : </b><input type="text" name="quantity" size="5" value="1">��</td>
			</tr>
			<tr>
			<td align="center">
			<input type="submit" value="��ٱ��� ���">
			</td>
			</tr>
			</table>
			<input type=hidden name=product_no value=<%=product.getNo()%>>			
			</form>
		
		</td>
		<td width="50%" valign="top">
		<b>�󼼼���</b><br><pre><%=product.getDetail()%></pre>
		</td>
		</tr>
		</table>

	</td>
	</tr>
	</table>

	<%@ include file="Bottom.jsp" %>

</body>
</html>




