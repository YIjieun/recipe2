<%@page contentType="text/html;charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML>
 <HEAD>
  <TITLE> 사진인화 </TITLE>
  <link href="style.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="../css/style.css" type="text/css"/>
 </HEAD>
 <BODY>
         <!--넘어오는 파라미터값에 따라 본문내용변경 -->
<center><b>글목록(전체 글:${pgList.count})</b>
<table width="700">
<tr>
    <td align="right" bgcolor="#99CCFF">
    <a href="../board1/writeForm.do">글쓰기</a>
    </td>
</table>
<!-- 화면에 출력하는 코딩 -->
<c:if test="${pgList.count == 0}">
<table border="1" width="700" cellpadding="0" 
                  cellspacing="0" align="center"> 
   <tr>
    <td align="center">
      게시판에 저장된 글이 없습니다.
    </td>
   </tr>
</table>
</c:if>
<c:if test="${pgList.count > 0}">

<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="#99CCFF"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
    </tr>
    
    <!-- 실제 데이터 출력부분  -->
    <c:set var="number" value="${pgList.number}"/>
    <c:forEach var="article" items="${articleList}">
   <!--스타일 적용 -->
 
   <tr height="30" onmouseOver="this.style.backgroundColor='white'"
                   onmouseOut="this.style.backgroundColor='#e0ffff'">
    <td align="center"  width="50" >
     <c:out value="${number}" />
     <c:set var="number" value="${number-1}" /></td>
    <td  width="250" >
    
	 <!--답변글이면 앞에 답변이미지 붙여준다.  -->
	 <c:if test="${article.re_level > 0}">
	  <img src="images/level.gif" 
	       width="${5*article.re_level}"  height="16">
	  <img src="images/re.gif">
	  </c:if>
	  <c:if test="${article.re_level == 0}">
	  <img src="images/level.gif" width="${5*article.re_level}" 
	                             height="16"> 
	 </c:if>
	          
      <a href="../board1/content.do?num=${article.num}&pageNum=${pgList.currentPage}">
           ${article.subject}</a>

	<c:if test="${article.comment_count!=0}">
		[${article.comment_count}]
	</c:if>

      </td>
    <td align="center"  width="100"> 
    
    <a href="mailto:${article.email}">
                ${article.writer}</a></td>
    <td align="center"  width="150">
    <fmt:formatDate value="${article.reg_date}"
     timeStyle="medium" pattern="yy.MM.dd (hh:mm)"/>
     </td>
  </tr>
   </c:forEach>
</table>
</c:if>


<c:if test="${pgList.startPage > pgList.blockSize}">
   <a href="../board1/list.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">
      [이전]</a>
</c:if>

<c:forEach var="i" begin="${pgList.startPage}" 
                   end="${pgList.endPage}">
   <a href="../board1/list.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
      <c:if test="${pgList.currentPage == i}">
                   <B>[${i}]</B></c:if>
      <c:if test="${pgList.currentPage != i}">
                   [${i}]</c:if>            
      </a>
</c:forEach>

<c:if test="${pgList.endPage < pgList.pageCount}">
   <a href="../board1/list.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">
      [다음]</a>
   </c:if><br></br>
 
 <!-- 검색어 추가 -->
  <form action="../board1/list.do" name="bus">
    <select name="search">
       <option value="subject">제목</option>
       <option value="subject_content">제목+본문</option>
       <option value="writer">작성자</option>
    </select>
    <input name="searchtext" type="text" size="15" />
    &nbsp;<input type="submit" value="검색"/>
  </form>
 <!--  -->
</center>

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