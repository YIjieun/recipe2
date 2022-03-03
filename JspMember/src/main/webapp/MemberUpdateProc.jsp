<%@ page contentType="text/html;charset=UTF-8"	
		 pageEncoding="UTF-8"
	     import="hewon.MemberDAO"%>
<%
	// MemberUpdate.jsp->MemberUpdateProc.jsp(수정메서드 호출)
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="mem" class="hewon.MemberDTO" />
<jsp:setProperty name="mem" property="*" />
<%
//추가 mem.setMem_id(request.getParameter("mem_id"));
	String mem_id=request.getParameter("mem_id");
System.out.println("MemberUpdataProc.jsp의 mem_id=>"+mem_id);
//---------------------------------------------------------
	MemberDAO memMgr=new MemberDAO();
	boolean check=memMgr.memberUpdate(mem);//회원수정 메서드 호출
	System.out.println("MemberUpdateProc.jsp의 회원수정유무(check)=>"+check);
%>
<html>
<body bgcolor="#FFFFCC">
<br>
<center>
	<%
		if(check==true){//회원수정에 성공했다면
	%>
	<script>
		alert("성공적으로 수정되었습니다.");
		location.href="Login.jsp";//아직 로그아웃X
	</script>
	<%}else{%>
	<script>
		alert("수정도중 에러가 발생했습니다.");
		history.back();//history.go(-1);//전의 페이지에서 다시 수정
	</script>
	<%} %>
</center>
</body>
</html>