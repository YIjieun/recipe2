<%@ page contentType="text/html;charset=UTF-8"	
		 pageEncoding="UTF-8"
	     import="hewon.MemberDAO"%>

<%
//추가 mem.setMem_id(request.getParameter("mem_id"));
	String mem_id=request.getParameter("mem_id");//입력 X
	String passwd=request.getParameter("passwd");//입력 O
	
	System.out.println("deleteProc.jsp의 mem_id=>"+mem_id
								+" , passwd=>"+passwd);
//---------------------------------------------------------
	MemberDAO memMgr=new MemberDAO();
	int check=memMgr.memberDelete(mem_id,passwd);//회원탈퇴 메서드 호출
	System.out.println("deleteProc.jsp의 회원탈퇴유무(check)=>"+check);//1 or 0
%>
	<%
		if(check==1){//회원탈퇴에 성공했다면
			session.invalidate();//세션종료(메모리 해제)
	%>
	<script>
		alert("<%=mem_id%>님 성공적으로 탈퇴처리 되었습니다.");
		location.href="Login.jsp";//로그인창
	</script>
	<%}else{%>
	<script>
		alert("비밀번호가 틀립니다.\n 다시한번 확인해 주세요.");
		history.back();//history.go(-1);//전의 페이지로 이동
	</script>
	<%} %>
