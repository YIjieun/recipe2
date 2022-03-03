<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.util.*, product.*,order.*" %>
<jsp:useBean id="cartMgr" class="product.CartMgr" scope="session"/>
<jsp:useBean id="orderMgr" class="order.OrderMgr" />
<jsp:useBean id="proMgr" class="product.ProductMgr" />

<%  
    //��ٱ��ϸ� �����´�.
	Hashtable hCart = cartMgr.getCartList();
	Enumeration hCartKey=hCart.keys();//��ٱ��ϸ� �о��

	//��ٱ��ϰ� ��������� ����
	if(hCart.size() !=0){

		//��ٱ����� ����� �ִٸ� while������ ���鼭 �ֹ�ó���� �Ѵ�.
		while(hCartKey.hasMoreElements()){

		//��ٱ����� �ֹ������� �о�´�.
		OrderBean order = (OrderBean)hCart.get(hCartKey.nextElement());
		orderMgr.insertOrder(order);//�ֹ��� �Ѵ�.
		proMgr.reduceProduct(order);//��ǰ��� ���δ�.		
		cartMgr.deleteCart(order);//�ֹ�ó���� ��ٱ��� ������ �����Ѵ�.
		}
%>
	<script>
	   alert("�ֹ�ó�� �Ͽ����ϴ�");
	   location.href="OrderList.jsp";
	</script>
<%}else{ %>
	<script>
		alert("��ٱ��ϰ� ����ֽ��ϴ�.");
		location.href="OrderList.jsp";
	</script>
<%}%>

