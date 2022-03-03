<%@page contentType="text/html;charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML>
 <HEAD>
  <TITLE> 사진인화 </TITLE>
  <link href="style.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="../css/style.css" type="text/css"/>
<script type="text/javascript" src="../js/dolphin.js"></script>
 </HEAD>
 <BODY>
 <form method="POST">
 <table width="1026" border="0">
   <tr>
     <td width="6">&nbsp;</td>
     <td width="1000">&nbsp;</td>
     <td width="10">&nbsp;</td>
   </tr>
   <tr>
     <td height="464">&nbsp;</td>
     <td rowspan="2"><TABLE width="1000" height="507" border="1" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
       <!-- 상단메뉴 -->
       <TR>
         <TD height="50">로고 준비 중. </TD>
         <TD height="50">

<form method="POST">
	<div id="dolphincontainer">
	<div id="dolphinnav">
	<ul>
	<li><a HREF="../view/intro_01.jsp" rel="main"><span>Home</span></a></li>
	<li><A HREF="../board1/list.do" rel="board"> <span>게시판</span></a></li>
	<li><A HREF="../index.jsp?STR=view&CONTROL=faq&PAGENUM=01&EXE=jsp" rel="faq"><span>FAQ</span></a></li>
	<li><A HREF="../index.jsp?STR=view&CONTROL=support&PAGENUM=01&EXE=jsp" rel="cen"><span >고객센터</span></a></li>
	<li><A HREF="../adboard/list.do" rel="qna"><span>묻고답하기</span></a></li>
	</ul>
	</div>
	<div id="dolphin_inner">
	<div id="main" class="innercontent"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;메인&nbsp;|&nbsp;공지사항</font></div>
	<div id="board" class="innercontent"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;게시판&nbsp;</font></div>
	<div id="faq" class="innercontent"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;faq&nbsp;</font></div>
	<div id="cen" class="innercontent"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;고객지원&nbsp;</font></div>
	<div id="qna" class="innercontent"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;Q&A&nbsp;</font></div>
	</div>
	</div>
<script type="text/javascript">
dolphintabs.init("dolphinnav", 1)
</script>
</form>	
		</TD>
       </TR>
       <!--본문내용  -->
       <TR>
         <!-- 좌측메뉴 -->
         <TD width="300" height="39" valign="top">
				<jsp:include page="/LoginForm/Login.jsp" flush="false"/>
    </TD>
         <!--넘어오는 파라미터값에 따라 본문내용변경 -->
         <TD rowspan="2" valign="top" name="show">

<!-- 내용삽입부분 -->


<!-- 내용삽입끝-->

		 </TD>
       </TR>
       <TR>
         <TD width="300" valign="top"><jsp:include page="/module/introLeft.jsp" flush="false"/></TD>
       </TR>
       <TR>
         <TD colspan="2" height="50">
           <jsp:include page="/module/bottom.jsp" flush="false"/>     
    </TD>
       </TR>
       <!-- end -->
     </TABLE></td>
     <td>&nbsp;</td>
   </tr>
   <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
   </tr>
   <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
   </tr>
 </table>
 </form>
 </BODY>
</HTML>