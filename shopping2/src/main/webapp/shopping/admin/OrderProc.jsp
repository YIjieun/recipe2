<%@ page contentType="text/html;charset=EUC-KR" %>
<%@ page import="java.util.*, product.*,order.*"%>
<jsp:useBean id="orderMgr" class="order.OrderMgr" />

<%
    //����/������ �����ϱ� ���� flag���� �о�´�.
	String flag = request.getParameter("flag");
    String no = request.getParameter("no");//�ֹ���ȣ�� �о�´�.
	String state = request.getParameter("state");
	boolean result = false;
	
	if(flag.equals("update")){
    	result=orderMgr.updateOrder(no, state);//�ֹ� ó�����¸� ����
    }else if(flag.equals("delete")){
		result=orderMgr.deleteOrder(no);//�ֹ�����
	}else{
		response.sendRedirect("OrderMgr.jsp");
	}

    if(result){//ó������� ���� ������ �޽����� ���
%>
	<script>
		alert("���������� ó���Ͽ����ϴ�.");
		location.href="OrderMgr.jsp";
	</script>
<%
	}else{
%>
	<script>
		alert("������ �߻��Ͽ����ϴ�.");
		location.href="OrderMgr.jsp";
	</script>
<%
	}
%>

