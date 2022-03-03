<%@page contentType="text/html;charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML>
 <HEAD>
  <TITLE> ������ȭ </TITLE>
  <link href="style.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="../css/style.css" type="text/css"/>
 </HEAD>
 <BODY>
         <!--�Ѿ���� �Ķ���Ͱ��� ���� �������뺯�� -->
<center><b>�۸��(��ü ��:${pgList.count})</b>
<table width="700">
<tr>
    <td align="right" bgcolor="#99CCFF">
    <a href="../board1/writeForm.do">�۾���</a>
    </td>
</table>
<!-- ȭ�鿡 ����ϴ� �ڵ� -->
<c:if test="${pgList.count == 0}">
<table border="1" width="700" cellpadding="0" 
                  cellspacing="0" align="center"> 
   <tr>
    <td align="center">
      �Խ��ǿ� ����� ���� �����ϴ�.
    </td>
   </tr>
</table>
</c:if>
<c:if test="${pgList.count > 0}">

<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="#99CCFF"> 
      <td align="center"  width="50"  >�� ȣ</td> 
      <td align="center"  width="250" >��   ��</td> 
      <td align="center"  width="100" >�ۼ���</td>
      <td align="center"  width="150" >�ۼ���</td> 
    </tr>
    
    <!-- ���� ������ ��ºκ�  -->
    <c:set var="number" value="${pgList.number}"/>
    <c:forEach var="article" items="${articleList}">
   <!--��Ÿ�� ���� -->
 
   <tr height="30" onmouseOver="this.style.backgroundColor='white'"
                   onmouseOut="this.style.backgroundColor='#e0ffff'">
    <td align="center"  width="50" >
     <c:out value="${number}" />
     <c:set var="number" value="${number-1}" /></td>
    <td  width="250" >
    
	 <!--�亯���̸� �տ� �亯�̹��� �ٿ��ش�.  -->
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
      [����]</a>
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
      [����]</a>
   </c:if><br></br>
 
 <!-- �˻��� �߰� -->
  <form action="../board1/list.do" name="bus">
    <select name="search">
       <option value="subject">����</option>
       <option value="subject_content">����+����</option>
       <option value="writer">�ۼ���</option>
    </select>
    <input name="searchtext" type="text" size="15" />
    &nbsp;<input type="submit" value="�˻�"/>
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