<%@ page language="java"
          contentType="text/html; charset=UTF-8"
%>

<%
//쿼리스트링으로 넘어오는 파라메터를 먼저 받는다.
String dogName=request.getParameter("dogName");
String dogPrice=request.getParameter("dogPrice");
%>

<HTML>
<BODY>
<head>
<style type=text/css>
a:link {color:#FFFFFF}
a:visited {color:#FFFFFF}
a:hover {color:#00FF00}
a.m1 {font-size : 9pt; text-decoration : underline; COLOR: #0000FF}
a.m1:visited {font-size : 9pt; text-decoration : underline; COLOR: #0000FF}
a.m1:active {font-size : 9pt; text-decoration : underline; COLOR: #0000FF}
a.m1:hover {font-size : 9pt; text-decoration : underline; COLOR: #FF3300}
a.m2 {font-size : 10pt; text-decoration : none}
a.m2:visited {font-size : 10pt; text-decoration : none}
a.m2:active {font-size : 10pt; text-decoration : none}
a.m2:hover {font-size : 10pt; text-decoration : none}
.menu {color: #ffffff; font-size: 11pt; font-weight: bold; }
.t1 {font-size : 9pt; line-HEIGHT:140%;}
.t2 {font-size : 10pt;}
.TXTFLD {FONT-SIZE:9pt; COLOR:#000000; BORDER:1x SOLID #000000}
.TXTFLD1 {FONT-SIZE:9pt; COLOR:#000000; BORDER:0 SOLID #000000}
</style>
</head>
<table border=0 width=80% height=376 align=center>
<tr width=100% valign=bottom>
<td width=30% height=33 align=center class=t1><font size=2 color=#0000FF><b>주문량</b></font></td>
<td width=40% height=33 align=center  class=t1><font size=2 color=#0000FF><b>멍멍이 이름</b></font></td>
<td width=30% height=33 align=center  class=t1><font size=2 color=#0000FF><b>멍멍이 소개</b></font></td>
</tr>
<tr width=100%>
<td colspan=3 height=5><hr color=#556b2f></td>
</tr>
<tr width=100%>
<td width=30% height=270 align=center class=t1>
<form method=post action=dog_cart.jsp>
수량 : <input type=text name=dogCount value=1 size=4 class=TXTFLD> 마리
<input type=hidden name=dogName value=<%=dogName%>>
<input type=hidden name=dogPrice value=<%=dogPrice%>>
<br><br>
<input type=submit value=장바구니에담기 class=TXTFLD>
</td>
<td width=40% height=270 align=center>
<img border=0 src=images/<%=dogName%>.gif width=120 height=200>
</td>
<td width=30% height=270 class=t1>
&nbsp;&nbsp;&nbsp;<b><li>견종 : <%=dogName%></b>
&nbsp;&nbsp;&nbsp;<font color=#FF0000><li>가격 : <%=dogPrice%></font>
&nbsp;&nbsp;&nbsp;<font color=#0000FF><li>기타 상세 정보 등...</font>
</td>
</tr>
<tr>
<td colspan=3 height=18 align=center><a href=Buy>집에 데려가기</a>&nbsp;&nbsp;<a href=kim_shop.html>계속 구경하기</a></td>
</tr>
<tr>
<td colSpan=3 height=21><hr color=#556b2f></td>
</tr></table>
</BODY>
</HTML>
