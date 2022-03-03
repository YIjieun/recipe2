<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1}">
 <meta http-equiv="Refresh" 
        content="0;url=/board1/list.do?pageNum=${pageNum}">
</c:if>
<c:if test="${check==0}">
  <script>
         alert("비밀번호가 틀립니다.\n 다시한번확인 요망!");
         history.go(-1);//history.back();
  </script>
</c:if>



