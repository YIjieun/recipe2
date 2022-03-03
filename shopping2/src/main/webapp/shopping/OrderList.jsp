<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page import="java.util.*, product.*,order.*" %>
<jsp:useBean id="proMgr" class="product.ProductMgr" />
<jsp:useBean id="orderMgr" class="order.OrderMgr" />
<%
    //������� �α��ο��θ� üũ
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
		<td align="center"><font color="#FFFFFF">�ֹ���ȣ</font></td>
		<td align="center"><font color="#FFFFFF">��ǰ</font></td>
		<td align="center"><font color="#FFFFFF">�ֹ�����</font></td>
		<td align="center"><font color="#FFFFFF">�ֹ���¥</font></td>
		<td align="center"><font color="#FFFFFF">�ֹ�����</font></td>
		</tr>
		<%
		 //������� ID�� �ش� ������� �ֹ������� �о�ɴϴ�.
		Vector vResult = orderMgr.getOrder(mem_id);
		if(vResult.size() ==0){
		%>
		<tr> 
		<td align="center" colspan="5">�ֹ��Ͻ� ��ǰ�� �����ϴ�.</td>
		</tr>		
		<%}else{

			//Vector�� ����� ������� �ֹ������� ������ ������ �����鼭
			//�ϳ��� �ҷ����� ���
			for(int i=0; i< vResult.size(); i++){
			OrderBean order = (OrderBean)vResult.get(i);
			ProductBean product = proMgr.getProduct(order.getProduct_no());
		%>
		<tr> 
		<td align="center"><%=order.getNo()%></td>
		<td align="center"><a href="javascript:productDetail('<%=order.getProduct_no()%>')"><%=product.getName()%></a></td>
		<td align="center"><%=order.getQuantity()%></td>
		<td align="center"><%=order.getDate()%></td>
		<td align="center">
		<%
		switch(Integer.parseInt(order.getState())){
		case 1 : out.println("������");
		break;
		case 2 : out.println("����");
		break;
		case 3 : out.println("�Ա�Ȯ��");
		break;
		case 4 : out.println("����غ�");
		break;
		case 5 : out.println("�����");
		break;
		default : out.println("�Ϸ�");
		}
		%> 
		</td>
		</tr>
		<%}
		}%>
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


