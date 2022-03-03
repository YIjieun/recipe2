<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!--방법2  -->
<jsp:useBean id="ca" class="calc.CalcBean" scope="page" />
<jsp:setProperty name="ca" property="*"/> 
<%ca.calculate(); %><!-- 계산이 종료->result->getResult()호출 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기 입력(요청,처리 한번에)</title>
</head>
<body>
<!--정 가운데배치  <center>-->
<center>
   <h3>계산기</h3> 
    <hr>
    <!-- 전송은 form태그이용 action="요청처리할페이지"  -->
 <!-- 1.   <form action="cal2.jsp" method="post" name="calculate"> -->
 <!-- 2. action 부분을 생략하면 디폴트로 본인에게 요청,응답 -->
    <form  method="post" name="calculate">
        <input type="text" name="num1" size="3">
        <select name="operator">
            <option selected>+</option><!-- selected 처음시작시 고정 -->
            <option>-</option>
            <option>*</option>
            <option>/</option>
        </select>
        <input type="text" name="num2" size="3">
        <p>
        <input type="submit" value="계산" onclick="cal()">
        <input type="reset" value="다시입력" onClick = "history.back()">
    </form>
     <hr>
계산결과 : <%=ca.getResult()%><br>
계산결과2 : <jsp:getProperty name="ca" property="result"/>

</center>
<script>
/* 0으로 나눌 수 없게 코딩 */
    function cal(){
        if ( document.calculate.operator.value=="/" &&  document.calculate.num2.value==0){
            alert("다시 입력하세요.");
            return false;
        }
        document.calculate.submit();
    }
</script>
</body>
</html>