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
	 //1.메서드를 불러오기위해서 객채를 생성
	 BeanDTO bd = new BeanDTO();
	 //2. 입력받은 갯수만큼 Setter Method 호출
	 bd.setName(request.getParameter("name"));
	 bd.setAddr(request.getParameter("addr"));
	 //Getter Method를 호출해서 테이블의 각 필드에 저장
	 System.out.println(" 입력받은 이름은 " + bd.getName() + "<br>");
	 System.out.println(" 입력받은  주소는 " + bd.getAddr() + "<br>");
	 out.println(" 입력받은 이름은 " + bd.getName() + "<br>");
	 out.println(" 입력받은  주소는 " + bd.getAddr() + "<br>");
 %>
 
<p>
<hr>
<b><%=bd.getName()%></b>님 잘오셨습니다.<br>
<b><%=bd.getAddr()%></b>이군요!
 
</body>
</html>