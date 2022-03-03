<%@ page contentType="text/html;charset=EUC-KR" %>
<%@ page import="java.util.*, product.*,order.*"%>
<jsp:useBean id="orderMgr" class="order.OrderMgr" />
<jsp:useBean id="proMgr" class="product.ProductMgr" />

<%
//�󼼺��⸦ �����ϰ� �Ǹ� �ֹ���ȣ�� �Ѱܹް� �ȴ�.����
//�ش� �ֹ���ȣ�� ���� ���� ������ ������ �� �ִ�.
OrderBean order = orderMgr.getOrderDetail(request.getParameter("no"));

//�ش� �ֹ��� ���� ��ǰ������ �����´�.�ֹ��������� �ֹ��� ��ǰ��ȣ��
//����� �����Ƿ� � ��ǰ�� �ֹ��ߴ��� �� �� �ִ�.
ProductBean product = proMgr.getProduct(order.getProduct_no());
%>

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

		<form method=post name="order" action="OrderProc.jsp">
		<table width="95%" align="center" bgcolor="#FFFF99" border="1">
		<tr bgcolor="#996600"> 
		<td colspan="2" align="center"><font color="#FFFFFF">�ֹ� �󼼳���</font></td>
		</tr>
		<tr> 
		<td align="center">�����̵�</td>
		<td align="center"><%=order.getId()%></td>
		</tr>
		<tr> 
		<td align="center">�ֹ���ȣ</td>
		<td align="center"><%=order.getNo()%></td>
		</tr>
		<tr> 
		<td align="center">��ǰ��ȣ</td>
		<td align="center"><%=product.getNo()%></td>
		</tr>				
		<tr> 
		<td align="center">��ǰ�̸�</td>
		<td align="center"><%=product.getName()%></td>
		</tr>
		<tr> 
		<td align="center">��ǰ����</td>
		<td align="center"><%=product.getPrice()%>��</td>
		</tr>
		<tr> 
		<td align="center">�ֹ�����</td>
		<td align="center"><%=order.getQuantity()+""%>��</td>
		</tr>
		<tr> 
		<td align="center">������</td>
		<td align="center"><%=product.getStock()%>��</td>
		</tr>		
		<tr> 
		<td align="center">�ֹ���¥</td>
		<td align="center"><%=order.getDate()%></td>
		</tr>
		<tr> 
		<td align="center">�ݾ�</td>
		<% //�ֹ��� ��ǰ������ ��ǰ�� ������ ���Ͽ� �� �ݾ��� ���ϰ� �ȴ�.  %>
		<td align="center"><%=order.getQuantity()*Integer.parseInt(product.getPrice())%>��</td>
		</tr>
		<tr> 
		<td align="center">�ֹ�����</td>
		<td align="center">
		<select name="state">
		<option value="1">������</option>
		<option value="2">����</option>
		<option value="3">�Ա�Ȯ��</option>
		<option value="4">����غ�</option>
		<option value="5">�����</option>
		<option value="6">�Ϸ�</option>
		</select>
		<% //��ũ��Ʈ�� �̿��� ���� �ֹ��� ��ǰ�� ���� ó�����¸� �����ְ� �ȴ�. %>
		<script>document.order.state.value=<%=order.getState()%></script>
		</td>
		</tr>
		<tr> 
		<td colspan="2" align="center">
		<% //�ֹ�ó�� ���¸� �����ϰų� �ֹ��� �����Ѵ�.%>
		<input type="botton" value="����" size="3" onclick="javascript:orderUpdate(this.form)"> / 
		<input type="botton" value="����" size="3" onclick="javascript:orderDelete(this.form)">
		</td>
		</tr>
		</table>
		<% //����/������ ���ؼ��� �ֹ���ȣ�� �ʿ��ϴ�.%>
		<input type="hidden" name="no" value="<%=order.getNo()%>">

		<% //flag���� ���� �ֹ��� ���� ����/����ó���� �����ϰ� �ȴ�.
		   //flag���� ���� Ȥ�� ���� ��ư�� ������ ���ÿ� ��ũ��Ʈ�� ����
		   //�����Ǿ� ����.
		%>
		<input type="hidden" name="flag">
		</form>		
			
	</td>
	</tr>
	</table>
	
	<%@ include file="Bottom.jsp" %>	
	
</body>
</html>

