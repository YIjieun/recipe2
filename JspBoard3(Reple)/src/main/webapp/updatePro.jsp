<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1}">
 <meta http-equiv="Refresh" 
        content="0;url=/board1/list.do?pageNum=${pageNum}">
</c:if>
<c:if test="${check==0}">
  <script>
         alert("��й�ȣ�� Ʋ���ϴ�.\n �ٽ��ѹ�Ȯ�� ���!");
         history.go(-1);//history.back();
  </script>
</c:if>



