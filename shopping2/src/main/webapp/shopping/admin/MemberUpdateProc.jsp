<%@ page contentType="text/html;charset=EUC-KR"%>
<%request.setCharacterEncoding("euc-kr");%>
<jsp:useBean id="memMgr" class="hewon.MemberMgr" />
<jsp:useBean id="regBean" class="hewon.RegisterBean" />
<jsp:setProperty name="regBean" property="*" />
<%
    boolean flag = memMgr.memberUpdate(regBean);
%>

<%
if(flag){
%>
		<script>
		alert("���������� �����Ͽ����ϴ�");
		location.href="Index.jsp";
		</script>
<%
	}else{
%>
		<script>
		alert("�������� ������ �߻��Ͽ����ϴ�.");
		history.back();
		</script>

<%
	  }
%>


