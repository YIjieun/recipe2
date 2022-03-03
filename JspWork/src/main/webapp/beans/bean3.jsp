<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="test.BeanDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바빈즈를 사용하는 경우</title>
</head>
<body>

 <%
	 request.setCharacterEncoding("utf-8");
 	 String name=request.getParameter("name");//value값 생성 (String name)
 	 String addr=request.getParameter("addr");//value값 생성 (String addr)
	 //1.메서드를 불러오기위해서 객채를 생성
	 //BeanDTO bd = new BeanDTO();
	 //2. 입력받은 갯수만큼 Setter Method 호출
	 //bd.setName(request.getParameter("name"));
	// bd.setAddr(request.getParameter("addr"));
	 //Getter Method를 호출해서 테이블의 각 필드에 저장
	// out.println(" 입력받은 이름은 " + bd.getName() + "<br>");
	// out.println(" 입력받은  주소는 " + bd.getAddr() + "<br>");
 %>
<!--  자바구문 : BeanDTO bd = new BeanDTO(); -->
 <!-- 액션태그(3가지)로 변경 -->
 <jsp:useBean id="bd" class="test.BeanDTO" scope="page" />
 <%-- 액션태그 주석 ctrl+shift+/ --%>
 <!-- setter Method를 호출하는 경우 -->
 <%-- <jsp:setProperty name="bd" property="name" value="<%=name %>" /><!-- useBean다음에 만들수 있음 -->
 <jsp:setProperty name="bd" property="addr" value="<%=addr %>" /> --%>
 <jsp:setProperty property="*" name="bd"/>
<p>
<hr>
<!-- getter Method를 호출하는 경우 -->
액션태그를 통한 getter Method를 호출하는 경우<P>
getName() 호출=><jsp:getProperty property="name" name="bd" /><br><%-- <%=bd.getName()%> --%>
getAddr() 호출=><jsp:getProperty property="addr" name="bd"/><%-- <%=bd.getAddr()%> --%>
<hr>
<b><%=bd.getName()%></b>님 잘오셨습니다.<br>
<b><%=bd.getAddr()%></b>이군요!
 
</body>
</html>