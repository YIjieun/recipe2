<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바빈즈가 나온 배경</title>
</head>
<body>
<%!
//입력->매개변수를 전달=>저장(필드별로 입력)=>테이블에 필드별로 저장
String str="선언문";//웹상에서 입력받은 값을 저장
String addr="";

public void setStr(String str){
	this.str=str;
	System.out.println("setStr() 호출됨!");
}
public void setAddr(String addr){
	this.addr=addr;
	System.out.println("setAddr() 호출됨!");
}

public String getStr(){return str;}
public String getAddr(){return addr;}
%>
 메서드 호출(Setter Method):<%

 request.setCharacterEncoding("utf-8");
 setStr(request.getParameter("name")); 
 setAddr(request.getParameter("addr")); 
 System.out.println("getStr()=>"+getStr());
 System.out.println("getAddr()=>"+getAddr());
%>
<p>
저장된 값 출력(Getter Method):
<h1><%=getStr()%></h1><h1><%=getAddr()%></h1>
 
</body>
</html>