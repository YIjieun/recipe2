<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.*,lys.board.*" %>
<!-** 테이블에 저장된 데이터를 가져와서 json 표기법형태로 출력하시오 **-  -->
[
  <%
      Connection con=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      DBConnectionMgr pool=null;
      String sql="";
  
      try{
    	  pool=DBConnectionMgr.getInstance();
    	  con=pool.getConnection();
    	  System.out.println("getBoardJson.js의 con=>"+con);
    	  sql="select * from board order by num asc";
    	  pstmt=con.prepareStatement(sql);
    	  rs=pstmt.executeQuery();
    	  while(rs.next()){
    		  //[{num:1,writer:'홍길동',~},{num:2~]
    		  int num=rs.getInt("num");
    		  String writer=rs.getString("writer");
    		  String subject=rs.getString("subject");
    		  String content=rs.getString("content");
    		  if(rs.getRow() > 1){ //rs.getRow()->레코드 갯수 얻어오는 메서드 (한개이상이라면)
    			  out.print(",");
    		  }%>
    		{
    		  "num":<%=num%>,<br>
    		  "writer":<%=writer %>,<br>
    		  "subject":<%=subject %>,<br>
    		  "content":<%=content %><br>
    		}  
   <% 
    	  } //while
      }catch(Exception e){
    	  out.println("getBoardJson.jsp에 에러유발=>"+e);
      }finally{
    	  pool.freeConnection(con, pstmt, rs);
      }
  %>
]