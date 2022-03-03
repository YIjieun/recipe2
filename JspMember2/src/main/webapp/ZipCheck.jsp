<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,hewon.*"%>
<!DOCTYPE html>
<jsp:useBean id="memMgr" class="hewon.MemberDAO" />
<%
  //script.js=>ZipCheck()=>ZipCheck.jsp?check=y
   request.setCharacterEncoding("utf-8");//한글처리
  
  String check=request.getParameter("check");//y->n
  String area3=request.getParameter("area3");//null->"미아2동"
  System.out.println("ZipCheck.jsp의 check="+check+",area3=>"+area3);
  
  ArrayList<ZipcodeDTO> zipcodeList=memMgr.zipcodeRead(area3);
  int totalList=zipcodeList.size();
  System.out.println("검색된 총레코드수(totalList=>"+totalList);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>우편번호 검색</title>
<link href="style.css?ver=1" rel="stylesheet"  type="text/css">
<script>
//동이름을 체크할 함수선언
  function dongCheck(){
	  if(document.zipForm.area3.value==""){
		  alert("동이름을 먼저 입력하세요!");
		  document.zipForm.area3.focus();
		  return;
	  }
	  document.zipForm.submit();//ZipCheck.jsp
  }
  //검색된 레코드중에서 내주소와 가장 가까운 주소를 링크클릭
  //형식) Regiser.jsp(부모창)의 특정항목에 값을 넣어주는 함수
  //opener=>부모창을 가리키는 예약어<->자식창(self or this)
  //형식2) opener.document.폼객체명.입력양식.속성=값
  function sendAddress(zipcode,area1,area2,area3,area4){
	  var address=area1+" "+area2+" "+area3+" "+area4;
	  opener.document.regForm.mem_zipcode.value=zipcode;//우편번호
	  opener.document.regForm.mem_address.value=address;//주소
	  self.close();//self=>ZipCheck.jsp창(자식창)
  }
</script>
</head>
<body bgcolor="#FFFFCC">
  <center>
    <b>우편번호 찾기</b>
    <table>
  
   <form name="zipForm" method="post"  action="ZipCheck.jsp">
        <tr>
        <td><br>
        동이름 입력:<input type="text"  name="area3">
        <input type="button" value="검색" onclick="dongCheck()">
                  
        </td>
        </tr>    
        <!--post방식중에서 값을 입력하지 않고도 전달시키는 방법  -->
        <!-- <input type="hidden" name="전달할매개변수명" value="전달할값"> -->
        <input type="hidden" name="check" value="n">                               
   </form>
   <%
      //검색어를 입력하고 검색버튼을 누른경우
      if(check.equals("n")){
    	  if(zipcodeList.isEmpty()){//검색된 레코드가 없다면
    		 //if(zipcodeList.size()==0){  
   %>
      <tr><td align="center">
            <br>검색된 레코드가 없습니다.
          </td>
      </tr>
    <%}else { %>
   <tr><td align="center"><br>
   *검색후 ,아래 우편번호를 클릭하면 자동으로
     입력됩니다</td></tr>
    <%
       for(int i=0;i<totalList;i++){//for
          ZipcodeDTO zipBean=zipcodeList.get(i);//zipcodeList.elementAt(i);
    		String tempZipcode=zipBean.getZipcode();//우편번호
    		String tempArea1=zipBean.getArea1().trim();//시->"서울      "=>"서울"
    		String tempArea2=zipBean.getArea2().trim();//구
    		String tempArea3=zipBean.getArea3().trim();//동
    		String tempArea4=zipBean.getArea4().trim();//나머지
    %>
  <tr><td>
          <a href="JavaScript:sendAddress('<%=tempZipcode%>',
                                                           '<%=tempArea1%>',
                                                           '<%=tempArea2%>',
                                                           '<%=tempArea3%>',
                                                           '<%=tempArea4%>')">
          <%=tempZipcode%>&nbsp;<%=tempArea1%>&nbsp;
          <%=tempArea2%>&nbsp;<%=tempArea3%>&nbsp;
          <%=tempArea4%>
  </a><br>
   <%
             }//for
    	  }//inner (else)
      }//if(check.equals("n")){
   %>
    </td></tr>
    <tr><td align="center"><br>
<a href="JavaScript:this.close()">닫기</a>                      
    </table>
  </center>
</body>
</html>