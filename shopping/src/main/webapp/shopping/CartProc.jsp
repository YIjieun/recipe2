<%@ page contentType="text/html;charset=euc-kr"%>
<jsp:useBean id="cartMgr" class="product.CartMgr" scope="session"/>
<jsp:useBean id="order" class="order.OrderBean" />
<jsp:setProperty name="order" property="*" />
<%
	String flag=request.getParameter("flag");
	String id= (String)session.getAttribute("idKey");
	
	if(id == null) {
		response.sendRedirect("Login.jsp");
	}else{

		if(flag == null){
		order.setId(id);		
		cartMgr.addCart(order);	
%>
		<script>
		alert("��ٱ��Ͽ� ��ҽ��ϴ�.");
		location.href="CartList.jsp";
		</script>
<%		}else if(flag.equals("update")){
	    order.setId(id);
		cartMgr.updateCart(order);
%>
		<script>
		alert("���� �Ͽ����ϴ�.");
		location.href="CartList.jsp";
		</script>
<%		}else{
		cartMgr.deleteCart(order);
%>
		<script>
		alert("���� �Ͽ����ϴ�.");
		location.href="CartList.jsp";
		</script>
<%
		}
	}
%>