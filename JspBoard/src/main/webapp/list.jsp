<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="yje.board.*,java.util.*,java.text.SimpleDateFormat"%>

<!-- 글목록보기  -->
<!DOCTYPE html>
<%! //선언문-전역변수 같은 느낌
	int pageSize=10;//numPerPage=>페이지당 보여주는 게시물수(=레코드수)10
	int blockSize=10;//pagePerBlock=>블럭당 보여주는 페이지수 10
	//작성날짜->우리나라 스타일 년-월-일 시 분 초->SimpleDateFormat
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
	//게시판을 맨 처음 실행시키면 무조건 1페이지부터 출력->가장 최근의 글이 나오기 때문에
	String pageNum=request.getParameter("pageNum");
	if(pageNum==null){
		pageNum="1";//default(무조건 1페이지는 선택하지 않아도 보여줘야 되기 때문에)
	}
	int currentPage=Integer.parseInt(pageNum);//"1"->1(=nowPage)(현재페이지)
	//				1페이지->(1-1)*10+1=1, 2페이지->(2-1)*10+1=11, 3페이지->(3-1)*10+1=21
	int startRow=(currentPage-1)*pageSize+1;//시작 레코드번호
	int endRow=currentPage*pageSize;//1*10=10,2*10=20,3*10=30
	
	int count=0;//총레코드수
	int number=0;//beginPerPage->페이지별로 시작하는 맨 처음에 나오는 게시물 번호
	List<BoardDTO> articleList=null;//화면에 출력할 레코드를 저장할 변수
	
	BoardDAO dbpro=new BoardDAO();//메서드 호출 위해 객체 생성
	count=dbpro.getArticleCount();//select count(*) from board->member
	System.out.println("현재 레코드수(count)=>"+count);
	if(count>0){
		articleList=dbpro.getArticles(startRow,pageSize);//첫번째레코드번호,불러올갯수
		System.out.println("list.jsp의 articleList=>"+articleList);//null->메서드 내용확인
	}
	//		첫번째페이지		122-(1-1)*10=122,121,120,119,,,,,,,
	//		두번째페이지		122-(2-1)*10=112		
	number=count-(currentPage-1)*pageSize;
	System.out.println("페이지별 number=>"+number);
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#e0ffff">
<center><b>글목록(전체 글:<%=count%>)</b>
<table width="700">
<tr>
    <td align="right" bgcolor="#b0e0e6">
    <a href="writeForm.jsp">글쓰기</a>
    </td>
</table>
<!-- 데이터의 유무 -->
<%
	if(count==0){
%>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
	<tr>
		<td align="center">게시판에 저장된 글이 없습니다.</td>
	</tr>
</table>
<%}else{ %>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="#b0e0e6"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
      <td align="center"  width="100" >IP</td>    
    </tr>
    <!--실질적으로 레코드를 출력시켜주는 부분  -->
	 <%
	 	for(int i=0;i<articleList.size();i++){   
	    	BoardDTO article=articleList.get(i);//articleList.elementAt(i);//벡터
	  %>
   <tr height="30"><!--하나씩 감소하면서 출력하는 게시물번호  -->
    <td align="center"  width="50" ><%=number--%></td>
    <td  width="250" >
	<!--답변글인 경우 먼저 답변이미지를 부착시키는 코드  -->
	<%
		int wid=0;//공백을 계산할 변수선언
		if(article.getRe_level()>0){//최소1 ->답변글이라면
			wid=7*(article.getRe_level());//7px,14,21,,,,들여쓰기
	%>
	  <img src="images/level.gif" width="<%=wid%>" height="16"><!-- 공백이미지 -->
	  <img src="images/re.gif"><!-- 답변이미지  -->
	  <%}else {%>
	  <img src="images/level.gif" width="<%=wid%>" height="16"><!-- 공백이미지 -->    
	  <%} %>  <!--num(게시물번호),pageNum(페이지번호)  -->
      <a href="content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">
           <%=article.getSubject()%></a> 
         <% if(article.getReadcount()>=20){%><!-- 조회수가 20이상인 것만 hot이미지 부착 -->
         <img src="images/hot.gif" border="0"  height="16"> 
         <%} %>
         </td>
    <td align="center"  width="100"> 
       <a href="mailto:<%=article.getEmail()%>"><%=article.getWriter()%></a></td>
       <!-- sdf.format(article.getReg_date()) 우리나라 스타일로 변경-->
    <td align="center"  width="150"><%=sdf.format(article.getReg_date())%></td>
    <td align="center"  width="50"><%=article.getReadcount()%></td>
    <td align="center" width="100" ><%=article.getIp()%></td>
  </tr>
  	<%} //for%>
</table>
<%}//else %>
<!-- 페이징 처리 -->
<%
	if(count>0){//레코드가 한개 이상이라면
		//1. 총페이지수 구하기
		//						122/10=12.2+(122%10==0?0:1)=>1   -> 12.2+1.0=13.2=13페이지
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		//2. 시작페이지
		int startPage=0;
		if(currentPage%blockSize!=0){//1~9,11~19,21~29,,,(10의 배수가 아닌경우)
			startPage=currentPage/blockSize*blockSize+1;//경계선 때문에(playback표시)
		}else {//10%10==0(10,20,30,,,)
			//				((10/10)-1)*10+1
			startPage=((currentPage/blockSize)-1)*blockSize+1;
		}
		//종료페이지
		int endPage=startPage+blockSize-1;//1+10-1=10 ,2+10-1=11,,,
		System.out.println("startPage=>"+startPage+" ,endPage=>"+endPage);
		//블럭별로 구분해서 링크 걸어서 출력(마지막 페이지 > 총페이지수)
		//11 > 10=>endPage=10
		if(endPage > pageCount) {
			endPage=pageCount;// 마지막페이지=총페이지수
		}
		//1.이전블럭(11>10)				11-10=1 페이지로 이동
		if(startPage > blockSize){%>
		<a href="list.jsp?pageNum=<%=startPage-blockSize%>">[이전]</a>
	<% }
		//2.현재블럭(1,2,[3],4,5,,,,,10)
		for(int i=startPage;i<=endPage;i++){%>
	<a href="list.jsp?pageNum=<%=i%>">[<%=i%>]</a>
	<%}
		//3.다음블럭  1~14<15
		if(endPage < pageCount){%>
	<a href="list.jsp?pageNum=<%=startPage+blockSize%>">[다음]</a>
<%
	  }//다음블럭
 }//if(count>0)
%>
</center>
</body>
</html>