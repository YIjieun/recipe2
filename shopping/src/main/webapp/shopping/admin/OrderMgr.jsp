<%@ page contentType="text/html;charset=EUC-KR" %>
<%@ page import="java.util.*, product.*,order.*"%>
<jsp:useBean id="orderMgr" class="order.OrderMgr" />
<jsp:useBean id="proMgr" class="product.ProductMgr" />

<html>
<head>
<title>Simple Shopping Mall Admin</title>
<link href="../style.css" rel="stylesheet" type="text/css">
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
		<td align="center"><font color="#FFFFFF">�ֹ���</font></td>
		<td align="center"><font color="#FFFFFF">��ǰ</font></td>
		<td align="center"><font color="#FFFFFF">�ֹ�����</font></td>
		<td align="center"><font color="#FFFFFF">�ֹ���¥</font></td>
		<td align="center"><font color="#FFFFFF">�ֹ�����</font></td>
		<td>&nbsp;</td>
		</tr>

		<%
		//�ֹ������� Vector�� ��� �����´�.
		Vector vResult = orderMgr.getOrderList();
		if(vResult.size() ==0){ //�ֹ������� ������ �ֹ������� ���ٰ� ���
		%>
		<tr> 
		<td align="center" colspan="7">�ֹ� ������ �����ϴ�</td>
		</tr>
		<%}else{

			//�ֹ������� �ִٸ� Vector�� ����� �ֹ������� for������ ���鼭 
			//�ϳ��� �����´�.

			for(int i=0; i< vResult.size(); i++){
			OrderBean order = (OrderBean)vResult.get(i);

			//�ֹ������� �ִ� ��ǰ������ �����´�.
			ProductBean product = proMgr.getProduct(order.getProduct_no());
		%>
		<tr> 
		<td align="center"><%=order.getNo()%></td>
		<td align="center"><%=order.getId()%></td>
		<td align="center"><%=product.getName()%></td>
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
		}%>
		</td>
		<%
            //�ش��ֹ��� ���� �󼼳����� ��ȸ�Ѵ�.�󼼺��⸦ ���� �ֹ���ȣ�� �Բ� ����
			%>
		<td align="center"><a href="javascript:orderDetail('<%=order.getNo()%>')">�󼼺���</a></td>
		</tr>
		<%}
		}%>
		</table>
	
	</td>
	</tr>
	</table>
	
	<%@ include file="Bottom.jsp" %>
    <% //�ֹ��󼼳����� ���� ���� �ʿ��� ��  %>
	<form name="detail" method="post" action="OrderDetail.jsp" >
	<input type="hidden" name="no">
	</form>

</body>
</html>
