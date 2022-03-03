/**
 *  �Խ����� �۾���,�亯���..���� �����ϴ� �����Ͻ�������
 */

package board1.db;

import java.sql.*;
import java.util.*;
import db.*;

public class Board1DBMgr {

   private DBConnectionMgr pool = null;

   //DBConnectionMgrŬ������ ��ü�� ����
   public Board1DBMgr() {
	 try{
		 pool = DBConnectionMgr.getInstance();
	 }catch(Exception e){
		 System.out.println("Error:Ŀ�ؼ� �������!");
	 }
  }
  
  //�Խ����� ���ڵ���� �����ִ� �޼ҵ�->select����
  public  int  getArticleCount(){
	 //DB����
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 
	 int x=0;//���ڵ���� ������ ����
	 
	 //���ڵ���� �����ִ� �ڵ�
	 try{
		 con = pool.getConnection();
	 String sql="select count(*) from board1";	 
	 pstmt = con.prepareStatement(sql); 
	 rs = pstmt.executeQuery();
	 //���Ȯ��
	  if(rs.next()){ //���ڵ���� ���ߴٸ�
		  x = rs.getInt(1);
	  }
	 }catch(Exception e){
		System.out.println(" getArticle()����");
		System.out.println(" �������ι�ȣ 35");
		System.out.println(e);
	 }finally{
	    pool.freeConnection(con, pstmt, rs);
	 }
	 //DB��������
	 return x;
  }
  /* �Խ����� ���ڵ���� �����ִ� �޼ҵ�(�˻��� �߰�) */
  
  public  int  getArticleSearchCount(String search,
		                             String searchtext){
		 //DB����
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql=null;//sql����
		 
		 int x=0;//���ڵ���� ������ ����
		 
		 //���ڵ���� �����ִ� �ڵ�
		 try{
			 con = pool.getConnection();
			 
			 if(search == null || search == ""){
		     sql="select count(*) from board1"; //�˻��� ���� ���
			 }else{ //�˻��� �����ߴٸ�
			    if(search.equals("subject_content")){
    sql="select count(*) from board1 where subject like '%"+
                searchtext+"%' or content like '%"+
                searchtext+"%'";
			    }else{ //�ۼ���(writer),����
	sql="select count(*) from board1 where "+search+" like '%"+
	            searchtext+"%'";		    	
			    }
			 }
		 System.out.println("�˻�sql="+sql);
		 pstmt = con.prepareStatement(sql); 
		 rs = pstmt.executeQuery();
		 //���Ȯ��
		  if(rs.next()){ //���ڵ���� ���ߴٸ�
			  x = rs.getInt(1);
			  System.out.println("�˻��� �ѷ��ڵ��="+x);
		  }
		 }catch(Exception e){
			System.out.println(" getArticle()����");
			System.out.println(" �������ι�ȣ 35");
			System.out.println(e);
		 }finally{
		    pool.freeConnection(con, pstmt, rs);
		 }
		 //DB��������
		 return x;
	  }
  //�Խ��ǿ� ����¡ ó�����ִ� �޼���(���� �߰�)
  public Hashtable pageList(String pageNum,int count){
	  Hashtable<String,Integer> pgList = 
		                       new Hashtable<String,Integer>();
	  
	  //����¡ ó���� ���� ���� ����
	  int pageSize=10;//numPerPage(�������� �����ִ� ���ڵ��)
	  int blockSize=10;//pagePerBlock(���� �����ִ� ���ڵ��)
	  
	   //����Ʈ �������� ����->1
	   if(pageNum==null){ //�������� ������ �Ǿ������ʴٸ�
	      pageNum = "1";//nowPage(���� ������)
	   }
	   
	   //nowPage(���� ������->���� ���õ� ������)
	   int currentPage = Integer.parseInt(pageNum);
	  //���� �������� �۸�Ͽ� ǥ���� ���۱۹�ȣ(���ڵ��ȣ)
	  //             (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21
	  int startRow = (currentPage-1)*pageSize+1;
	  int endRow = currentPage*pageSize;//1*10=10,2*10=20,3*10=30

	  System.out.println("endRow:"+currentPage+"*"+pageSize);

	  int number = 0;//�� �������� �Խù� ���۹�ȣ(beginPerPage)
	  List articleList = null;//�˻��� �����͸� ���� �׸�����
	  
	  //�� �������� ���� �Խù���ȣ
	  //�������� �����ִ� ù��° �Խù���ȣ
	  //int beginPerPage =   nowPage * numPerPage;// 1*10=10,9,8,7	
	  //       122-(1-1)*10=122,122-(2-1)*10=112
	  number = count-(currentPage-1)*pageSize;
	  //�� ��������
	  int pageCount=count/pageSize+(count%pageSize==0?0:1);
	  int startPage=1;
	  
	  //�������� ����
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
  //�Խ����� ����¡ó���� ���ִ� �޼ҵ�->�����ȣ�˻�
  //(�� �������� ����� ���ڵ��ȣ,�������� ����� ���ڵ��)
  public List getArticles(int start,int end){
	     
	   //DB����
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 //���ͺ��� �޸𸮻���� �� ����� �Ǳ⶧����
		 List articleList = null;//ã�� �����͸� ���� �׸�
		 
		 //���ڵ���� �����ִ� �ڵ�
		 try{
			 con = pool.getConnection();
		 String sql="select * from board1 order by ref desc, re_step asc limit ?,?";
		 pstmt = con.prepareStatement(sql); 
		 pstmt.setInt(1,start-1 );//���ο��� 0���� ����
		 pstmt.setInt(2, end);
		 rs = pstmt.executeQuery();
		 //���Ȯ��
		  if(rs.next()){ //���ڵ���� ���ߴٸ�
			 //���� do~while������ ���� ���� ����->����
			 articleList = new ArrayList(end);//10��
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
				//articleList�� ���
				articleList.add(article);//null��������
			 }while(rs.next());
		  }
		 }catch(Exception e){
			System.out.println(" getArticles����");
			System.out.println(" �������ι�ȣ 67");
			System.out.println(e);
		 }finally{
		    pool.freeConnection(con, pstmt, rs);
		 }
		 //DB��������
		 return articleList;
  }
  //�˻��� ���� ã���ִ� �޼���
  public List getBoardArticles(int start,int end,
		                       String search,
		                       String searchtext){
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  List articleList = null;//ã�� ���ڵ带 ���� ��ü����
	  String sql=null;
	  
	  try{
		  con = pool.getConnection();
		  
		  if(search == null || search == ""){
//	  sql="select * from board1 order by ref desc,re_step asc limit ?,?"; 
//	  sql="select b.* from ( select r.*, rownum as rnum from (select * from board1 order by num desc) r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
	      sql="select b.* from ( select r.*, rownum as rnum from (select * from board1_view order by num desc) r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
		  }else{ //����+������ ����
			 if(search.equals("subject_content")){
//	  sql="select * from board1 where subject like '%"+searchtext+"%' or content like '%"+searchtext+"%' order by ref desc,re_step asc limit ?,?";
//	  sql ="select b.* from ( select r.*, rownum as rnum from ( select * from board1 where subject like '%"+searchtext+"%' or content like '%"+searchtext+"%' order by num desc) r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
	      sql ="select b.* from ( select r.*, rownum as rnum from ( select * from board1_view where subject like '%"+searchtext+"%' or content like '%"+searchtext+"%' order by num desc) r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
			 }else{ //����,�ۼ��ڸ� ����
//	  sql="select * from board1 where "+search+" like '%"+searchtext+"%' order by ref desc,re_step asc limit ?,?";		 		  
//	  sql = "select b.* from ( select r.*, rownum as rnum from ( select * from board1  where "+search+" like '%"+searchtext+"%') r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
	      sql = "select b.* from ( select r.*, rownum as rnum from ( select * from board1_view  where "+search+" like '%"+searchtext+"%') r ) b where rnum >= ? and rnum <= ? order by ref desc,re_step asc";
		     }
		  }
		pstmt=con.prepareStatement(sql);
		System.out.println("�˻���sql="+sql);
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

				//articleList�� ���
				articleList.add(article);//null��������
			}while(rs.next());
		}
	  }catch(Exception e){
		 System.out.println("===getboard1Articles()����==");
		 System.out.println("��������223");
		 e.printStackTrace();
	  }finally{
		  pool.freeConnection(con,pstmt,rs);
	  }
	  return articleList;
  }
  
  //�۾��� �� �亯�ޱ�
  //writeForm.jsp->writePro.jsp
  public void insertArticle(Board1DataBean article){
	  
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;//select����->�ִ�Խù���ȣ
	  
	  //�亯�� ���� ������ ���� ����
	  int num = article.getNum();//content.jsp���� �ѱ�
	  int ref = article.getRef();
	  int re_step = article.getRe_step();
	  int re_level = article.getRe_level();
	  
	  System.out.println("content.jsp���� �ѱ䰪");
	  System.out.println("num="+num+",ref="+ref);
	  System.out.println
	      ("re_step="+re_step+",re_level="+re_level);
	  
	  int number=0;//������ ������� �Խù���ȣ
	  String sql="";//sql������ ����
	  
	  try{
		 con = pool.getConnection(); //���ᰴü���
		 
		 //�űԱ��� �۾���->�ǳ��� �߰�(�ִ� �Խù���ȣ)
		 pstmt = con.prepareStatement
		                   ("select max(num) from board1");
		 rs = pstmt.executeQuery();
		 
		 //board1���̺��� �ִ밪�� ���ؿԴٸ� ->1
		 if(rs.next())
			number = rs.getInt(1)+1;
		 else
			number = 1;//ó�� �Է��ϴ� ���->default (1) 
		 
		 //�亯������ �űԱ������� �Ǻ��ϴ� �ڵ�
		 if(num!=0){ //�亯���̶�� 
			/*sql="update board1 set re_step = re_step+1 where ref = ? and re_step > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2,re_step);
			int update = pstmt.executeUpdate();
			System.out.println
			      ("�����ͻ�����ġ����update="+update);*/
			//�亯�� �ޱ����ؼ�
			re_step = re_step+1;
			re_level = re_level+1;
			num=number;
		 }else{ //�űԱ��̶�� 
			ref = number;//num�� ref�� ����� �⺻������ ����.
			re_step = 0;
			re_level = 0;
			System.out.println("ref = "+ref+", number= "+number);
			num= number;
		 }
		 //�űԱ��̵�,�亯���̵� �����͸� insert�ڵ�
		 sql="insert into board1(writer,email,subject,passwd,"+
		     " reg_date,ref,re_step,re_level,content,num)values"+
		     "(?,?,?,?,?,?,?,?,?,?)";
		 
		   pstmt = con.prepareStatement(sql);
		   pstmt.setString(1, article.getWriter());
		   pstmt.setString(2, article.getEmail());
		   pstmt.setString(3, article.getSubject());
		   pstmt.setString(4, article.getPasswd());
		   pstmt.setTimestamp(5, article.getReg_date());
		   //�����Ұ�.->���� �̸� ����� content.jsp����
		   //�־���� �Ѵ�.->���� �ٸ� ��ġ�� �亯�ޱ��.
		   pstmt.setInt(6,ref);
		   pstmt.setInt(7,re_step);
		   pstmt.setInt(8,re_level);
		   pstmt.setString(9,article.getContent());
		   pstmt.setInt(10, num);
		   int insert= pstmt.executeUpdate();
		   System.out.println("�۾��⼺������insert="+insert);
		 
	  }catch(Exception e){
		System.out.println("insertArticle()�����߻�");
		System.out.println("��������113");
		System.out.println(e);
	  }finally{
		 pool.freeConnection(con, pstmt,rs);  
	  }
  }
  //�� �󼼺���<-ȸ������ id�˻��� ����
  public Board1DataBean getArticle(int num){
	  
	     //DB����
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 //ã�� �����͸� ���� ��ü����
		 Board1DataBean article = null;//ã�� �����͸� ���� �׸�
		 
		 //�ۻ󼼺���->��ȸ���� ������Ű�鼭 ���������
		 try{
			 con = pool.getConnection();
			 String sql1="select * from board1 where num=?";
			 pstmt = con.prepareStatement(sql1);
			 pstmt.setInt(1, num);
			 rs = pstmt.executeQuery();
			 //���Ȯ��
			  if(rs.next()){ //���ڵ���� ���ߴٸ�
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
				System.out.println(" getArticle()����");
				System.out.println(" �������ι�ȣ 206");
				System.out.println(e);
			 }finally{
				pool.freeConnection(con, pstmt, rs);
		 }
		 //DB��������
		 return article;
  }
  //�ۼ����ϱ����� �Խù��� ã�� �޼ҵ�
  public Board1DataBean updateGetArticle(int num){
	
	     //DB����
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 //ã�� �����͸� ���� ��ü����
		 Board1DataBean article = null;//ã�� �����͸� ���� �׸�
		 
		 //�ۻ󼼺���->��ȸ���� ������Ű�鼭 ���������
		 try{
			 con = pool.getConnection();
 
		 String sql2="select * from board1 where num=?";
		 pstmt = con.prepareStatement(sql2);
		 pstmt.setInt(1, num);
		 rs = pstmt.executeQuery();
		 //���Ȯ��
		  if(rs.next()){ //���ڵ���� ���ߴٸ�
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
			System.out.println(" updateGetArticle()����");
			System.out.println(" �������ι�ȣ 258");
			System.out.println(e);
		 }finally{
		    pool.freeConnection(con, pstmt, rs);
		 }
		 //DB��������
		 return article;
  }
  //�� �����ϱ�->insert�� ����
  public int updateArticle(Board1DataBean article){
	  
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;//select����->��ȣ�� üũ
	  
	  String dbpasswd = "";//DB�� ��ȣ�� ������ ����
	  String sql="";//sql������ ����
	  int x = -1;//1->��������,0->����
	  
	  try{
		 con = pool.getConnection(); //���ᰴü���
		 //������ �����Ϳ� ���� ��ȣ�� üũ
		 pstmt = con.prepareStatement
		           ("select passwd from board1 where num=?");
		 pstmt.setInt(1, article.getNum());
		 rs = pstmt.executeQuery();
		 
		if(rs.next()){ //num�� ���� ��ȣ�� �ִٸ�
			dbpasswd = rs.getString("passwd");
			  //DB���� ��ȣ�� ������ ��ȣ�� ���ٸ�
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
		   System.out.println("�ۼ�����������update="+update);
		        x=1;//�ۼ��� ����
		    }else{
		    	x=0;//�ۼ��� ����->��ȣ�� Ʋ�����
		    }//else
		 }//if(rs.next()){
	  }catch(Exception e){
		System.out.println("updateArticle()�����߻�");
		System.out.println("�������� 304");
		System.out.println(e);
	  }finally{
		 pool.freeConnection(con, pstmt,rs);  
	  }
	  return x;
  }
  //�� �����ϱ�
  public int deleteArticle(int num,String passwd){
	  
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;//select����->��ȣ�� üũ
	  
	  String dbpasswd = "";//DB�� ��ȣ�� ������ ����
	  String sql="";//sql������ ����
	  int x = -1;//1->��������,0->����
	  
	  try{
		 con = pool.getConnection(); //���ᰴü���
		 //������ �����Ϳ� ���� ��ȣ�� üũ
		 pstmt = con.prepareStatement
		           ("select passwd from board1 where num=?");
		 pstmt.setInt(1,num);
		 rs = pstmt.executeQuery();
		 System.out.println("����rs="+rs);
		 
		if(rs.next()){ //num�� ���� ��ȣ�� �ִٸ�
			dbpasswd = rs.getString("passwd");
			
			System.out.println("DB���� ��ȣdbpasswd="+dbpasswd);
			System.out.println("������ ��ȣpasswd="+passwd);
			  //DB���� ��ȣ�� ������ ��ȣ�� ���ٸ�
		    if(dbpasswd.equals(passwd)){
		    	
		 sql="delete from board1 where num = ?";
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1,num);
		  
		   int delete= pstmt.executeUpdate();
		   System.out.println("�ۻ�����������delete="+delete);
		        x=1;//�ۻ��� ����
		    }else{
		    	x=0;//�ۻ��� ����->��ȣ�� Ʋ�����
		    }//else
		 }//if(rs.next()){
	  }catch(Exception e){
		System.out.println("deleteArticle()�����߻�");
		System.out.println("�������� 355");
		System.out.println(e);
	  }finally{
		 pool.freeConnection(con, pstmt,rs);  
	  }
	  return x;
  }


	/* 
			���
							*/
	public List commentDetail(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List commentList=null; //ã�� ���ڵ带 ���� ��ü ����

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
			System.out.println("==commentDetail()����==");
			System.out.println("�������� 549");
			e.printStackTrace();
		}//3. DB ����
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return commentList;
	}

	public int deleteComment(int reboard1_num){
		
		//1. DB ����
		Connection con=null;
		PreparedStatement pstmt=null;
		int x=-1; //������ ���� ����
		String sql=""; //sql���
		//2. DB �۾�
		try{
			con=pool.getConnection();
			con.setAutoCommit(false);
			sql="delete from reply_board1 where reboard1_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,reboard1_num);
			int delete=pstmt.executeUpdate();
			System.out.println("��� ��������="+delete);
			con.commit();
		}catch(Exception e){
			System.out.println("==deleteComment()����==");
			System.out.println("�������� 565");
			e.printStackTrace();
		}//3. DB ����
		finally{
			pool.freeConnection(con, pstmt);
		}
		return x;
	}


	//��۾���
	public void addComment(CommentDataBean comment){
		//1. DB ����
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql=null;
		//2. DB �۾�
		try{
			con=pool.getConnection();
			
			System.out.println("comment.getWriter_id() = +++++++++"+comment.getWriter_id());
			System.out.println("comment.getReg_date() = +++++++++"+comment.getReg_date());
			System.out.println("comment.getContent() = +++++++++"+comment.getContent());
			System.out.println("comment.getNum() = +++++++++"+comment.getNum());
			//������ �߰��ϴ� �ڵ�
			sql="insert into reply_board1 values(reboard1_num_seq.NEXTVAL,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,comment.getWriter_id());
			pstmt.setTimestamp(2,comment.getReg_date());
			pstmt.setString(3,comment.getContent());
			pstmt.setInt(4,comment.getNum());
			
			int replay=pstmt.executeUpdate();
		    System.out.println("��ۻ������� replay="+replay);
		
		}catch(Exception e){
			System.out.println("==addComment()����==");
			System.out.println("�������� 804");
			e.printStackTrace();
		}//3. DB ����
		finally{
			pool.freeConnection(con, pstmt);
		}
	}

  public CommentDataBean updateArticle(int num){
	
	     //DB����
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 //ã�� �����͸� ���� ��ü����
		 CommentDataBean article = null;//ã�� �����͸� ���� �׸�
		 
		 //�ۻ󼼺���->��ȸ���� ������Ű�鼭 ���������
		 try{
			 con = pool.getConnection();
 
		 String sql2="select * from reply_board1 where num=?";
		 pstmt = con.prepareStatement(sql2);
		 pstmt.setInt(1, num);
		 rs = pstmt.executeQuery();
		 //���Ȯ��
		  if(rs.next()){ //���ڵ���� ���ߴٸ�
			    article = new CommentDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter_id(rs.getString("writer_id"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setContent(rs.getString("content"));
		  }
		 }catch(Exception e){
			System.out.println(" updateGetArticle()����");
			System.out.println(" �������ι�ȣ 258");
			System.out.println(e);
		 }finally{
		    pool.freeConnection(con, pstmt, rs);
		 }
		 //DB��������
		 return article;
  }
}
