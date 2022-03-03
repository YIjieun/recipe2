<%@page contentType="Text/html; charset=UTF-8"
import="java.util.*,dog.DogBean,java.text.DecimalFormat"
%>
<%
//먼저 get인지 post인지 판별
Vector dogVector=null;
if(request.getMethod().equalsIgnoreCase("GET")){
	dogVector = (Vector)session.getAttribute("dogList");
}else{
	//폼데이터로 넘어오는 세가지 정보를 받는다.
	//장바구니에 담은 강아지이름, 수량, 가격....
	String dogName = request.getParameter("dogName");
	int dogCount = Integer.parseInt(request.getParameter("dogCount"));
	double dogPrice = Double.parseDouble(request.getParameter("dogPrice"));
	//check하자
	//System.out.println(dogName + "|" + dogCount + "|" + dogPrice);

	//지금 부터가 세션에 관련되 코드
	//Object타입으로 형변환해서 받는다. 헤쉬 테이블에서 받기 때문에
	dogVector = (Vector)session.getAttribute("dogList");
	//만일 처음으로 강아지를 담는 경우라면
	//벡터가 널로 떨어지겠지 ㅎㅎㅎㅎ
	if(dogVector == null){
		dogVector=new Vector(1,1);//벡터생성
	}else{
		for(int check=0;check<dogVector.size();check++){
			if((((DogBean)dogVector.elementAt(check)).getName()).equals(dogName)){
				dogCount+=((DogBean)dogVector.elementAt(check)).getCount();
				dogVector.removeElementAt(check);
			//DogBean temp=(DogBean)dogVector.elementAt(check);
			//if((temp.getName()).equals(dogName)){
			//	dogCount+=temp.getCount();
			//	dogVector.removeElementAt(check);
			}
		}
	}


	//벡터에 강아지 담기

	dogVector.addElement(new DogBean(dogName,dogCount,dogPrice));


	//세션에 벡터 담기
	session.setAttribute("dogList",dogVector);
}
%>

<HTML>
<HEAD>
<TITLE>장바구니 보기...</TITLE>
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
</HEAD>
<BODY>
<table width=80% border=0 cellspacing=0 cellpadding=0 align=center>
<tr valign=middle bgcolor=#556b2f>
<td height=25 align=center class=menu>::::: 나의 장바구니 :::::</td>
</td>
</tr>
</table>
<br>
<table align=center border=1 width=80% cellspacing=0 bordercolordark=#FFFFFF bordercolorlight=#4682b4>
<tr>
<td width=290 height=25 bgcolor=#4682b4 align=center class=t1><font color=#FFFFFF>강아지 이름</font></td>
<td width=112 height=25 bgcolor=#4682b4 align=center  class=t1><font color=#FFFFFF>수 량</font></td>
<td width=166 height=25 bgcolor=#4682b4 align=center class=t1><font color=#FFFFFF>가 격</font></td>
<td width=50 height=25 bgcolor=#4682b4 align=center class=t1><font color=#FFFFFF>비 고</font></td>
</tr>

<%
DecimalFormat d=new DecimalFormat("##,###.##");
double total=0;
//벡터에 담긴 수량만큼 레코드를 반복한다.
for(int i=0;i<dogVector.size();i++){
	DogBean dog=(DogBean)dogVector.elementAt(i);
	total += (dog.getCount()*dog.getPrice());
	%>

<tr>
<td width=290 height=26 align=center class=t1><%=dog.getName()%></td>
<td width=112 height=26 align=center  class=t1><%=dog.getCount()%></td>
<td width=166 height=26 align=center class=t1><%=(dog.getCount()*dog.getPrice())%></td>
<td width=50 height=26 align=center class=t1><a href=delete_dog.jsp?dogName=<%=dog.getName()%>  class=m1>삭제</a></td>
</tr>
	<%
}
%>
<tr>
<td width=640 colspan=4 height=26 class=t1>
<p align=right><font color=#FF0000>총 금액 : <%=d.format(total)%>원</font></p></td>
</tr>
</table>
<p align=center><a href=BuyServlet class=m1>집에 데려가기</a>&nbsp;&nbsp;<a href=delete_dog.jsp class=m1>장바구니 비우기</a>&nbsp;&nbsp;<a href=kim_shop.html class=m1>계속 구경하기</a></p>
</BODY>
</HTML>
