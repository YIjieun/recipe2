<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인창을 실행</title>
<script >
	function test(){//public void test(){} 자바스크립트 p113~115
		//1. 경로포함해서 불러올 문서명 , 2. 문서의 타이들 제목, 
		//3.창의 옵션(left,top(위치),width,height(크기)) 
	    //팝업창 띄우기
		//window.open('./sub/send.html','w','left=300,top=200,width=400,height=200');
		//window.open('/ServletTest/abc/tes/imsi/HelloTest','w','left=300,top=200,width=400,height=200');
		window.open('/ServletTest/Notice','w','left=300,top=200,width=400,height=200');
	}
</script>
</head>
<body onload="test()">
  <h2>Welcome To JSP Study Site!!</h2>
</body>
</html>