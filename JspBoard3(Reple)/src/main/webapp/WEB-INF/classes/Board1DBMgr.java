/**
 *  게시판의 글쓰기,답변기능..등을 구현하는 비지니스로직빈
 */

package board1.db;

import java.sql.*;
import java.util.*;
import db.*;

public class Board1DBMgr {

   private DBConnectionMgr pool = null;

   //DBConnectionMgr클래스의 객체를 생성
   public Board1DBMgr() {
	 try{
		 pool = DBConnectionMgr.getInstance();
	 }catch(Exception e){
		 System.out.println("Error:커넥션 연결실패!");
	 }
  }
  
  //게시판의 레코드수를 구해주는 메소드->select구문
  public  int  getArticleCount(){
	 //DB접속
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 
	 int x=0;//레코드수를 저장할 변수
	 
	 //레코드수를 구해주는 코딩
	 try{
		 con = pool.getConnection();
	 String sql="select count(*) from board1";	 
	 pstmt = con.prepareStatement(sql); 
	 rs = pstmt.executeQuery();
	 //결과확인
	  if(rs.next()){ //레코드수를 구했다면
		  x = rs.getInt(1);
	  }
	 }catch(Exception e){
		System.out.println(" getArticle()에러");
		System.out.println(" 에러라인번호 35");
		System.out.println(e);
	 }finally{
	    pool.freeConnection(con, pstmt, rs);
	 }
	 //DB접속해제
	 return x;
  }
  /* 게시판의 레코드수를 구해주는 메소드(검색어 추가) */
  
  public  int  getArticleSearchCount(String search,
		                             String searchtext){
		 //DB접속
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql=null;//sql저장
		 
		 int x=0;//레코드수를 저장할 변수
		 
		 //레코드수를 구해주는 코딩
		 try{
			 con = pool.getConnection();
			 
			 if(search == null || search == ""){
		     sql="select count(*) from board1"; //검색이 없는 경우
			 }else{ //검색을 선택했다면
			    if(search.equals("subject_content")){
    sql="select count(*) from board1 where subject like '%"+
                searchtext+"%' or content like '%"+
                searchtext+"%'";
			    }else{ //작성자(writer),제목
	sql="select count(*) from board1 where "+search+" like '%"+
	            searchtext+"%'";		    	
			    }
			 }
		 System.out.println("검색sql="+sql);
		 pstmt = con.prepareStatement(sql); 
		 rs = pstmt.executeQuery();
		 //결과확인
		  if(rs.next()){ //레코드수를 구했다면
			  x = rs.getInt(1);
			  System.out.println("검색된 총레코드수="+x);
		  }
		 }catch(Exception e){
			System.out.println(" getArticle()에러");
			System.out.println(" 에러라인번호 35");
			System.out.println(e);
		 }finally{
		    pool.freeConnection(con, pstmt, rs);
		 }
		 //DB접속해제
		 return x;
	  }
  //게시판에 페이징 처리해주는 메서드(새로 추가)
  public Hashtable pageList(String pageNum,int count){
	  Hashtable<String,Integer> pgList = 
		                       new Hashtable<String,Integer>();
	  
	  //페이징 처리에 대한 변수 설정
	  int pageSize=10;//numPerPage(페이지당 보여주는 레코드수)
	  int blockSize=10;//pagePerBlock(블럭당 보여주는 레코드수)
	  
	   //디폴트 페이지를 지정->1
	   if(pageNum==null){ //페이지가 설정이 되어있지않다면
	      pageNum = "1";//nowPage(현재 페이지)
	   }
	   
	   //nowPage(현재 페이지->현재 선택된 페이지)
	   int currentPage = Integer.parseInt(pageNum);
	  //현재 페이지의 글목록에 표시할 시작글번호(레코드번호)
	  //             (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21
	  int startRow = (currentPage-1)*pageSize+1;
	  int endRow = currentPage*pageSize;//1*10=10,2*10=20,3*10=30

	  System.out.println("endRow:"+currentPage+"*"+pageSize);

	  int number = 0;//각 페이지의 게시물 시작번호(beginPerPage)
	  List articleList = null;//검색된 데이터를 담을 그릇선언
	  
	  //각 페이지의 시작 게시물번호
	  //페이지당 보여주는 첫번째 게시물번호
	  //int beginPerPage =   nowPage * numPerPage;// 1*10=10,9,8,7	
	  //       122-(1-1)*10=122,122-(2-1)*10=112
	  number = count-(currentPage-1)*pageSize;
	  //총 페이지수
	  int pageCount=count/pageSize+(count%pageSize==0?0:1);
	  int startPage=1;
	  
	  //이전블럭의 유무
	  if(currentPage%blockSize!=0){
		  startPage = currentPage/blockSize*blockSize+1;
	  }else{
		  startPage=((currentPage/blockSize)-1)*blockSize+1;
	  }
	  
	  int endPage=startPage+blockSize-1;//1+3-1=3;
	  
	  if(endPage > pageCount){
		  endPage = pageCount;
	  }
	  
	  pgList.put("pageSize", pageSize);
	  pgList.put("blockSize",blockSize);
	  pgList.put("currentPage", currentPage);
	  pgList.put("startRow", startRow);
	  pgList.put("endRow",  endRow);
	  pgList.put("count", count);
	  pgList.put("number", number);
	  pgList.put("startPage", startPage);
	  pgList.put("endPage", endPage);
	  pgList.put("pageCount", pageCount);
	  
	  return pgList;
	  
  }
  //게시판의 페이징처리를 해주는 메소드->우편번호검색
  //(각 페이지의 출력할 레코드번호,페이지당 출력할 레코드수)
  public List getArticles(int start,int end){
	     
	   //DB접속
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 //벡터보다 메모리사용이 덜 사용이 되기때문에
		 List articleList = null;//찾은 데이터를 담을 그릇
		 
		 //레코드수를 구해주는 코딩
		 try{
			 con = pool.getConnection();
		 String sql="select * from board1 order by ref desc, re_step asc limit ?,?";
		 pstmt = con.prepareStatement(sql); 
		 pstmt.setInt(1,start-1 );//내부에서 0부터 시작
		 pstmt.setInt(2, end);
		 rs = pstmt.executeQuery();
		 //결과확인
		  if(rs.next()){ //레코드수를 구했다면
			 //먼저 do~while사이의 값을 먼저 실행->누적
			 articleList = new ArrayList(end);//10개
			 do{
			 Board1DataBean article = new Board1DataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				//articleList에 담기
				articleList.add(article);//null에러유발
			 }while(rs.next());
		  }
		 }catch(Exception e){
			System.out.println(" getArticles에러");
			System.out.println(" 에러라인번호 67");
			System.out.println(e);
		 }finally{
		    pool.freeConnection(con, pstmt, rs);
		 }
		 //DB접속해제
		 return articleList;
  }
  //검색에 대한 찾아주는 메서드
  public List getBoardArticles(int start,int end,
		                       String search,
		                       String searchtext){
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  List articleList = null;//찾은 레코드를 담을 객체선언
	  String sql=null;
	  
	  try{
		  con = pool.getConnection();
		  
		  if(search == null || search == ""){
//	  sql="select * from board1 order by ref desc,re_step asc limit ?,?"; 
//	  sql="select b.* from ( select r.*, rownum as rnum from (select * from board1 order by num desc) r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
	      sql="select b.* from ( select r.*, rownum as rnum from (select * from board1_view order by num desc) r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
		  }else{ //제목+본문을 선택
			 if(search.equals("subject_content")){
//	  sql="select * from board1 where subject like '%"+searchtext+"%' or content like '%"+searchtext+"%' order by ref desc,re_step asc limit ?,?";
//	  sql ="select b.* from ( select r.*, rownum as rnum from ( select * from board1 where subject like '%"+searchtext+"%' or content like '%"+searchtext+"%' order by num desc) r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
	      sql ="select b.* from ( select r.*, rownum as rnum from ( select * from board1_view where subject like '%"+searchtext+"%' or content like '%"+searchtext+"%' order by num desc) r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
			 }else{ //제목,작성자를 선택
//	  sql="select * from board1 where "+search+" like '%"+searchtext+"%' order by ref desc,re_step asc limit ?,?";		 		  
//	  sql = "select b.* from ( select r.*, rownum as rnum from ( select * from board1  where "+search+" like '%"+searchtext+"%') r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
	      sql = "select b.* from ( select r.*, rownum as rnum from ( select * from board1_view  where "+search+" like '%"+searchtext+"%') r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
		     }
		  }
		pstmt=con.prepareStatement(sql);
		System.out.println("검색어sql="+sql);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			articleList = new ArrayList();
			do{
				Board1DataBean article = new Board1DataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setComment_count(rs.getInt("comment_count"));

				//articleList에 담기
				articleList.add(article);//null에러유발
			}while(rs.next());
		}
	  }catch(Exception e){
		 System.out.println("===getboard1Articles()에러==");
		 System.out.println("에러라인223");
		 e.printStackTrace();
	  }finally{
		  pool.freeConnection(con,pstmt,rs);
	  }
	  return articleList;
  }
  
  //글쓰기 및 답변달기
  //writeForm.jsp->writePro.jsp
  public void insertArticle(Board1DataBean article){
	  
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;//select구문->최대게시물번호
	  
	  //답변을 위한 변수도 같이 선언
	  int num = article.getNum();//content.jsp에서 넘김
	  int ref = article.getRef();
	  int re_step = article.getRe_step();
	  int re_level = article.getRe_level();
	  
	  System.out.println("content.jsp에서 넘긴값");
	  System.out.println("num="+num+",ref="+ref);
	  System.out.println
	      ("re_step="+re_step+",re_level="+re_level);
	  
	  int number=0;//앞으로 집어넣을 게시물번호
	  String sql="";//sql문장을 저장
	  
	  try{
		 con = pool.getConnection(); //연결객체얻기
		 
		 //신규글을 글쓰기->맨끝에 추가(최대 게시물번호)
		 pstmt = con.prepareStatement
		                   ("select max(num) from board1");
		 rs = pstmt.executeQuery();
		 
		 //board1테이블의 최대값을 구해왔다면 ->1
		 if(rs.next())
			number = rs.getInt(1)+1;
		 else
			number = 1;//처음 입력하는 경우->default (1) 
		 
		 //답변글인지 신규글인지를 판별하는 코딩
		 if(num!=0){ //답변글이라면 
			/*sql="update board1 set re_step = re_step+1 where ref = ? and re_step > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2,re_step);
			int update = pstmt.executeUpdate();
			System.out.println
			      ("데이터삽입위치성공update="+update);*/
			//답변을 달기위해서
			re_step = re_step+1;
			re_level = re_level+1;
			num=number;
		 }else{ //신규글이라면 
			ref = number;//num와 ref는 기능이 기본적으로 같다.
			re_step = 0;
			re_level = 0;
			System.out.println("ref = "+ref+", number= "+number);
			num= number;
		 }
		 //신규글이든,답변글이든 데이터를 insert코딩
		 sql="insert into board1(writer,email,subject,passwd,"+
		     " reg_date,ref,re_step,re_level,content,num)values"+
		     "(?,?,?,?,?,?,?,?,?,?)";
		 
		   pstmt = con.prepareStatement(sql);
		   pstmt.setString(1, article.getWriter());
		   pstmt.setString(2, article.getEmail());
		   pstmt.setString(3, article.getSubject());
		   pstmt.setString(4, article.getPasswd());
		   pstmt.setTimestamp(5, article.getReg_date());
		   //주의할것.->전에 미리 저장된 content.jsp값을
		   //넣어줘야 한다.->전혀 다른 위치에 답변달기됨.
		   pstmt.setInt(6,ref);
		   pstmt.setInt(7,re_step);
		   pstmt.setInt(8,re_level);
		   pstmt.setString(9,article.getContent());
		   pstmt.setInt(10, num);
		   int insert= pstmt.executeUpdate();
		   System.out.println("글쓰기성공유무insert="+insert);
		 
	  }catch(Exception e){
		System.out.println("insertArticle()에러발생");
		System.out.println("에러라인113");
		System.out.println(e);
	  }finally{
		 pool.freeConnection(con, pstmt,rs);  
	  }
  }
  //글 상세보기<-회원정보 id검색과 유사
  public Board1DataBean getArticle(int num){
	  
	     //DB접속
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 //찾은 데이터를 담을 객체선언
		 Board1DataBean article = null;//찾은 데이터를 담을 그릇
		 
		 //글상세보기->조회수를 증가시키면서 데이터출력
		 try{
			 con = pool.getConnection();
			 String sql1="select * from board1 where num=?";
			 pstmt = con.prepareStatement(sql1);
			 pstmt.setInt(1, num);
			 rs = pstmt.executeQuery();
			 //결과확인
			  if(rs.next()){ //레코드수를 구했다면
					article = new Board1DataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
			  }
			 }catch(Exception e){
				System.out.println(" getArticle()에러");
				System.out.println(" 에러라인번호 206");
				System.out.println(e);
			 }finally{
				pool.freeConnection(con, pstmt, rs);
		 }
		 //DB접속해제
		 return article;
  }
  //글수정하기위한 게시물을 찾는 메소드
  public Board1DataBean updateGetArticle(int num){
	
	     //DB접속
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 //찾은 데이터를 담을 객체선언
		 Board1DataBean article = null;//찾은 데이터를 담을 그릇
		 
		 //글상세보기->조회수를 증가시키면서 데이터출력
		 try{
			 con = pool.getConnection();
 
		 String sql2="select * from board1 where num=?";
		 pstmt = con.prepareStatement(sql2);
		 pstmt.setInt(1, num);
		 rs = pstmt.executeQuery();
		 //결과확인
		  if(rs.next()){ //레코드수를 구했다면
			    article = new Board1DataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
		  }
		 }catch(Exception e){
			System.out.println(" updateGetArticle()에러");
			System.out.println(" 에러라인번호 258");
			System.out.println(e);
		 }finally{
		    pool.freeConnection(con, pstmt, rs);
		 }
		 //DB접속해제
		 return article;
  }
  //글 수정하기->insert와 유사
  public int updateArticle(Board1DataBean article){
	  
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;//select구문->암호를 체크
	  
	  String dbpasswd = "";//DB의 암호를 저장할 변수
	  String sql="";//sql문장을 저장
	  int x = -1;//1->수정성공,0->실패
	  
	  try{
		 con = pool.getConnection(); //연결객체얻기
		 //수정할 데이터에 대한 암호를 체크
		 pstmt = con.prepareStatement
		           ("select passwd from board1 where num=?");
		 pstmt.setInt(1, article.getNum());
		 rs = pstmt.executeQuery();
		 
		if(rs.next()){ //num에 대한 암호가 있다면
			dbpasswd = rs.getString("passwd");
			  //DB상의 암호와 웹상의 암호가 같다면
		    if(dbpasswd.equals(article.getPasswd())){
		    	
		 sql="update board1 set writer=?,email=?,subject=?, "+
		     "passwd=?,content=? where num = ?";
		      
		   pstmt = con.prepareStatement(sql);
		   pstmt.setString(1, article.getWriter());
		   pstmt.setString(2, article.getEmail());
		   pstmt.setString(3, article.getSubject());
		   pstmt.setString(4, article.getPasswd());
		   pstmt.setString(5, article.getContent());
		   pstmt.setInt(6, article.getNum());
		 
		   int update= pstmt.executeUpdate();
		   System.out.println("글수정성공유무update="+update);
		        x=1;//글수정 성공
		    }else{
		    	x=0;//글수정 실패->암호가 틀린경우
		    }//else
		 }//if(rs.next()){
	  }catch(Exception e){
		System.out.println("updateArticle()에러발생");
		System.out.println("에러라인 304");
		System.out.println(e);
	  }finally{
		 pool.freeConnection(con, pstmt,rs);  
	  }
	  return x;
  }
  //글 삭제하기
  public int deleteArticle(int num,String passwd){
	  
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;//select구문->암호를 체크
	  
	  String dbpasswd = "";//DB의 암호를 저장할 변수
	  String sql="";//sql문장을 저장
	  int x = -1;//1->삭제성공,0->실패
	  
	  try{
		 con = pool.getConnection(); //연결객체얻기
		 //수정할 데이터에 대한 암호를 체크
		 pstmt = con.prepareStatement
		           ("select passwd from board1 where num=?");
		 pstmt.setInt(1,num);
		 rs = pstmt.executeQuery();
		 System.out.println("삭제rs="+rs);
		 
		if(rs.next()){ //num에 대한 암호가 있다면
			dbpasswd = rs.getString("passwd");
			
			System.out.println("DB상의 암호dbpasswd="+dbpasswd);
			System.out.println("웹상의 암호passwd="+passwd);
			  //DB상의 암호와 웹상의 암호가 같다면
		    if(dbpasswd.equals(passwd)){
		    	
		 sql="delete from board1 where num = ?";
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1,num);
		  
		   int delete= pstmt.executeUpdate();
		   System.out.println("글삭제성공유무delete="+delete);
		        x=1;//글삭제 성공
		    }else{
		    	x=0;//글삭제 실패->암호가 틀린경우
		    }//else
		 }//if(rs.next()){
	  }catch(Exception e){
		System.out.println("deleteArticle()에러발생");
		System.out.println("에러라인 355");
		System.out.println(e);
	  }finally{
		 pool.freeConnection(con, pstmt,rs);  
	  }
	  return x;
  }


	/* 
			댓글
							*/
	public List commentDetail(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List commentList=null; //찾은 레코드를 담을 객체 선언

		try{
			con=pool.getConnection();
			String sql = "select * from reply_board1 where num=?";
			pstmt=con.prepareStatement(sql);
			System.out.println("sql="+sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()==true){

				commentList=new ArrayList();
				do{ 
					CommentDataBean comment=new CommentDataBean();
					comment.setReboard1_num(rs.getInt("reboard1_num"));
					comment.setWriter_id(rs.getString("writer_id"));
					comment.setReg_date(rs.getTimestamp("reg_date"));
					comment.setContent(rs.getString("content"));
					comment.setNum(rs.getInt("num"));

					commentList.add(comment);
				}while(rs.next());
			}
		}catch(Exception e){
			System.out.println("==commentDetail()에러==");
			System.out.println("에러라인 549");
			e.printStackTrace();
		}//3. DB 해제
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return commentList;
	}

	public int deleteComment(int reboard1_num){
		
		//1. DB 연결
		Connection con=null;
		PreparedStatement pstmt=null;
		int x=-1; //데이터 삭제 유무
		String sql=""; //sql기억
		//2. DB 작업
		try{
			con=pool.getConnection();
			con.setAutoCommit(false);
			sql="delete from reply_board1 where reboard1_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,reboard1_num);
			int delete=pstmt.executeUpdate();
			System.out.println("댓글 삭제유무="+delete);
			con.commit();
		}catch(Exception e){
			System.out.println("==deleteComment()에러==");
			System.out.println("에러라인 565");
			e.printStackTrace();
		}//3. DB 해제
		finally{
			pool.freeConnection(con, pstmt);
		}
		return x;
	}


	//댓글쓰기
	public void addComment(CommentDataBean comment){
		//1. DB 연결
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql=null;
		//2. DB 작업
		try{
			con=pool.getConnection();
			
			System.out.println("comment.getWriter_id() = +++++++++"+comment.getWriter_id());
			System.out.println("comment.getReg_date() = +++++++++"+comment.getReg_date());
			System.out.println("comment.getContent() = +++++++++"+comment.getContent());
			System.out.println("comment.getNum() = +++++++++"+comment.getNum());
			//데이터 추가하는 코딩
			sql="insert into reply_board1 values(reboard1_num_seq.NEXTVAL,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,comment.getWriter_id());
			pstmt.setTimestamp(2,comment.getReg_date());
			pstmt.setString(3,comment.getContent());
			pstmt.setInt(4,comment.getNum());
			
			int replay=pstmt.executeUpdate();
		    System.out.println("댓글삽입유무 replay="+replay);
		
		}catch(Exception e){
			System.out.println("==addComment()에러==");
			System.out.println("에러라인 804");
			e.printStackTrace();
		}//3. DB 해제
		finally{
			pool.freeConnection(con, pstmt);
		}
	}

  public CommentDataBean updateArticle(int num){
	
	     //DB접속
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 //찾은 데이터를 담을 객체선언
		 CommentDataBean article = null;//찾은 데이터를 담을 그릇
		 
		 //글상세보기->조회수를 증가시키면서 데이터출력
		 try{
			 con = pool.getConnection();
 
		 String sql2="select * from reply_board1 where num=?";
		 pstmt = con.prepareStatement(sql2);
		 pstmt.setInt(1, num);
		 rs = pstmt.executeQuery();
		 //결과확인
		  if(rs.next()){ //레코드수를 구했다면
			    article = new CommentDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter_id(rs.getString("writer_id"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setContent(rs.getString("content"));
		  }
		 }catch(Exception e){
			System.out.println(" updateGetArticle()에러");
			System.out.println(" 에러라인번호 258");
			System.out.println(e);
		 }finally{
		    pool.freeConnection(con, pstmt, rs);
		 }
		 //DB접속해제
		 return article;
  }
}
