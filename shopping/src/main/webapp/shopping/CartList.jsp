<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.util.*, product.*,order.*" %>
<jsp:useBean id="cartMgr" class="product.CartMgr" scope="session"/>
<jsp:useBean id="proMgr" class="product.ProductMgr" />
<%
	if(session.getAttribute("idKey") == null) {
		response.sendRedirect("Login.jsp");
	}else{
%>	
<html>
<head>
<title>Simple Shopping Mall</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>

<body bgcolor="#996600" topmargin="100">

	<%@ include file="Top.jsp" %>

	<table width="75%" align="center" bgcolor="#FFFF99">
	<tr>
	<td align="center" bgcolor="#FFFFCC">

		<table width="95%" align="center" bgcolor="#FFFF99" border="1">
		<tr bgcolor="#996600">
		<td align="center"><font color="#FFFFFF">��ǰ</font></td>
		<td align="center"><font color="#FFFFFF">����</font></td>
		<td align="center"><font color="#FFFFFF">����</font></td>
		<td align="center"><font color="#FFFFFF">����/����</font></td>
		<td align="center"><font color="#FFFFFF">��ȸ</font></td>
		</tr>
		<%
		int totalPrice =0 ;		
		Hashtable hCart = cartMgr.getCartList();
		if(hCart.size() == 0){
		%>
		<tr><td colspan="5" align="center">�����Ͻ� ��ǰ�� �����ϴ�.</td></tr>	

		<%
		}else{
		Enumeration hCartKey=hCart.keys();
			while(hCartKey.hasMoreElements()){
			OrderBean order = (OrderBean)hCart.get(hCartKey.nextElement());
			ProductBean product = proMgr.getProduct(order.getProduct_no());
			int price = Integer.parseInt(product.getPrice());
			int quantity = order.getQuantity();
			int subTotal = price*quantity;
			totalPrice += price*quantity;
			
		%>
		<form method="post"action="CartProc.jsp">
		<input type="hidden" name="product_no" value="<%=product.getNo()%>">
		<input type="hidden" name="flag">
		<tr> 
		<td align="center">	<%=product.getName()%></td>
		<td align="center"><input type=text name=quantity value="<%=order.getQuantity()%>" size=5 >��</td>
		<td align="center"><%=subTotal+""%></td>
		<td align="center">
		<input type="botton" value="����" size="3" onclick="javascript:cartUpdate(this.form)"> /
		<input type="botton" value="����" size="3" onclick="javascript:cartDelete(this.form)">
		</td>
		<td align="center"><a href="javascript:productDetail('<%=product.getNo()%>')">�󼼺���</a></td>
		</tr>
		</form>
		<%}%>
		<tr> 
		<td colspan="4" align="right">�� �ݾ� : <%=totalPrice%>��</td>
		<td align="center"><a href="OrderProc.jsp">�ֹ��ϱ�</a></td>
		</tr>
		<%
		}
		%>			
			
		</table>

	</td>
	</tr>
	</table>
	
	<%@ include file="Bottom.jsp" %>
	<form name="detail" method="post" action="ProductDetail.jsp" >
	<input type="hidden" name="no">
	</form>	
		
</body>
</html>
<%}%>