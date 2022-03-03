package yje.board;

//DBConnectionMgr(DB접속, 관리), BoardDTO(매개변수,반환형,데이터를 담는 역할)

import java.sql.*;
import java.util.*;

public class BoardDAO {//MemberDAO
	
	private DBConnectionMgr pool=null;//1.연결객체선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;//select
	private String sql="";//실행시킬 SQL구문 저장
	
	//2. 생성자를 통해서 연결=->의존관계
	public BoardDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
			System.out.println("pool=>"+pool);
		}catch(Exception e) {
			System.out.println("DB접속 오류=>");
		}
	}
	
	//3.메서드 작성(페이징 처리를 위한 메서드 작성)=>총레코드수(=총게시물수=총회원수)
	//select count(*) from board;  select count(*) from member;
	public int getArticleCount() {//getmemberCount()->MemberDAO에서 작성
		int x=0;
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			sql="select count(*) from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();//select구문
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X
			}
		}catch(Exception e) {
			System.out.println("getArticleCount() 에러발생"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//2.글목록보기에 대한 메서드 구현->레코드 한개 이상->한 페이지당 10개씩 끊어서 보여준다.
	//1)레코드의 시작번호 2)불러올 레코드의 갯수(ex 10,20,30)
	//public List<MemberDTO> getMemberList(int start,int end){
	public List<BoardDTO> getArticles(int start,int end){
		List<BoardDTO> articleList=null;//ArrayList articleList=null;
		
		try {
			con=pool.getConnection();
			/*
			 * 그룹번호(ref)가 가장 최신의 글을 중심으로 정렬하되, 만약에 level이 같은 경우에는
			 * step값으로 오름차순을 통해서 몇번째 레코드번호를 기준으로 해서 몇개까지( limit ?,?) 정렬할 것인가를 
			 * 지정해주는 SQL구문
			 */		
			sql="select * from board order by ref desc,re_step limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);//mysql은 레코드순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);//불러와서 담을 갯수(ex 10)
			rs=pstmt.executeQuery();//select구문
			if(rs.next()) {//보여주는 결과가 1개라도 있다면
				articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간생성 통째로 담을때!
				do {
					BoardDTO article=new BoardDTO();//new MemberDTO();필드별로 담기 위해서
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));//부적합한 열입니다.->필드오타
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));//글제목
					article.setPasswd(rs.getString("passwd"));
					
					article.setReg_date(rs.getTimestamp("reg_date"));//작성날짜
					article.setReadcount(rs.getInt("readcount"));//조회수 default->0
					article.setRef(rs.getInt("ref"));//그룹번호->신규글과 답변글을 묶어주는 역할
					article.setRe_step(rs.getInt("re_step"));//답변글이 나오는 순서
					article.setRe_level(rs.getInt("re_level"));//들여쓰기(답변의 깊이) depth
					
					article.setContent(rs.getString("content"));//글내용
					article.setIp(rs.getString("ip"));//글쓴이 ip->request.getRemoteAddr()
					//추가
					articleList.add(article);//생략하면 데이터가 저장 X->for문 에러유발(NullPointerException)
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("getArticles() 에러 유발"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;//NullPointerException 조심
	} 
	//------게시판의 글쓰기 및 답변 글쓰기
	//insert into board values(?,?,,,,)
	public void insertArticle(BoardDTO article) {//(MemberDTO mem)
		//1.articel->신규글인지 답변글(기존 게시물번호)인지 확인
		int num=article.getNum();//0 신규글 <->0이 아닌 경우->양수(1이상~) 답변글(=old)
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		
		int number=0;//데이터를 저장하기 위한 게시물번호(=New)
		System.out.println("insertArticle 메서드의 내부 num=>"+num);//0 신규글
		System.out.println("ref=>"+ref+" ,re_step=>"+re_step+" ,re_level=>"+re_level);
		
		try {
			con=pool.getConnection();
			sql="select max(num) from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();//select 구문
			if(rs.next()) {//보여주는 결과가 있다면 
				number=rs.getInt(1)+1;//최대값+1=>현재 넘버값+1로 저장
			}else {
				number=1;//테이블에 한개의 데이터도 없다면 최초부여값 1
			}
			//답변글이라면(양수이면서 1이상인경우)
			if(num!=0) {//댓글다는 로직
				//같은 그룹번호(ref)를 가지면서 나보다 step값이 큰 게시물을 찾아서 그 step값을 하나 증가시켜라.
				sql="update board set re_step=re_step+1 where ref=? and re_step > ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,ref);
				pstmt.setInt(2, re_step);
				int update=pstmt.executeUpdate();
				System.out.println("댓글수정유무(update)=>"+update);//1 성공 or 0 실패
				//답변글->댓글이 안달릴때 확인
				re_step=re_step+1;
				re_level=re_level+1;
			}else {//신규글이라면 num=0
				ref=number;//num=1, ref=1(1,2,3,4,,,,)
				re_step=0;//답변순서 X
				re_level=0;//답변자체 X
			}
			//12개 ->num,reg_date,readcount(생략)->default
			//작성날짜=sysdate(oracle), now()(mysql)
			//SQLSyn~sql 구문오류
			sql="insert into board(writer,email,subject,passwd,reg_date,";
			sql+=" ref,re_step,re_level,content,ip)values(?,?,?,?,?,?,?,?,?,?)";
			//sql+=" ref,re_step,re_level,content,ip)values(?,?,?,?,now(),?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, article.getWriter());//웹상에서 이미 데이터 저장된 상태(setWriter(~))
			pstmt.setNString(2, article.getEmail());
			pstmt.setNString(3, article.getSubject());
			pstmt.setNString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getReg_date());//생략가능->5번째에 ? 대신에 now() 쓰면!! 
			
			//---------ref,re_step,re_level에 대한 계산이 적용된 상태에서 저장
			pstmt.setInt(6,ref);//최대값+1
			pstmt.setInt(7,re_step);//0
			pstmt.setInt(8,re_level);//0
			//------------------------------------------------------
			pstmt.setNString(9, article.getContent());
			pstmt.setNString(10, article.getIp());//request.getRemoteAddr();
			int insert=pstmt.executeUpdate();//insert구문
			System.out.println("게시판의 글쓰기 성공유무(insert)=>"+insert);//1 or 0
			
		}catch(Exception e) {
			System.out.println("insertArticle() 메서드 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);//rs가 왜필요?
		}	
	}
	//글 상세보기
	// <a href="content.jsp?num=3&pageNum=1">
	//형식) select * from board where num=3;
	//형식) update board set readcount=readcount+1 where num=3;
	//public MemberDTO getMember(String id){~}
	public BoardDTO getArticle(int num) {//조회수 증가
		
		BoardDTO article=null;
			
			try {
					con=pool.getConnection();
					
					sql="update board set readcount=readcount+1 where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,num);
					int update=pstmt.executeUpdate();
					System.out.println("조회수 증가 유무(update)=>"+update);//1
					
					sql="select * from board where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,num);
					rs=pstmt.executeQuery();
					
					if(rs.next()) {//보여주는 결과가 1개라도 있다면
						article=this.makeArticleFromResult();
						/*
						article=new BoardDTO();//new MemberDTO(); 필드별로 담기 위해서
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));//부적합한 열입니다.->필드오타
						article.setEmail(rs.getString("email"));
						article.setSubject(rs.getString("subject"));//글제목
						article.setPasswd(rs.getString("passwd"));
						article.setReg_date(rs.getTimestamp("reg_date"));//작성날짜
						article.setReadcount(rs.getInt("readcount"));//조회수 default->0
						article.setRef(rs.getInt("ref"));//그룹번호->신규글과 답변글을 묶어주는 역할
						article.setRe_step(rs.getInt("re_step"));//답변글이 나오는 순서
						article.setRe_level(rs.getInt("re_level"));//들여쓰기(답변의 깊이) depth							
						article.setContent(rs.getString("content"));//글내용
						article.setIp(rs.getString("ip"));//글쓴이 ip->request.getRemoteAddr()	
						*/	
				}
			}catch(Exception e) {
				System.out.println("getArticle() 에러 유발"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return article;//content.jsp에서 받아서 출력->NullProinterException
		} 
	//-----------중복된 레코드 한개를 담을 수 있는 메서드를 따로 만들어서 처리------------	
	//접근지정자가 private가 되는 경우 외부에서 호출되면 안되고 내부에서만 호출되는 메서드 작성
	private BoardDTO makeArticleFromResult() throws Exception {
		BoardDTO article=new BoardDTO();//new MemberDTO(); 필드별로 담기 위해서
		article.setNum(rs.getInt("num"));
		article.setWriter(rs.getString("writer"));//부적합한 열입니다.->필드오타
		article.setEmail(rs.getString("email"));
		article.setSubject(rs.getString("subject"));//글제목
		article.setPasswd(rs.getString("passwd"));
		article.setReg_date(rs.getTimestamp("reg_date"));//작성날짜
		article.setReadcount(rs.getInt("readcount"));//조회수 default->0
		article.setRef(rs.getInt("ref"));//그룹번호->신규글과 답변글을 묶어주는 역할
		article.setRe_step(rs.getInt("re_step"));//답변글이 나오는 순서
		article.setRe_level(rs.getInt("re_level"));//들여쓰기(답변의 깊이) depth							
		article.setContent(rs.getString("content"));//글내용
		article.setIp(rs.getString("ip"));//글쓴이 ip->request.getRemoteAddr()	
		
		return article;
	}	
	
	//글수정
	//1) 수정할 데이터를 찾을 메서드 필요-> select * from board where num=?
	public BoardDTO updateGetArticle(int num) {
		BoardDTO article=null;
		
		try {
				con=pool.getConnection();				
				sql="select * from board where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,num);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {//보여주는 결과가 1개라도 있다면
					article=this.makeArticleFromResult();//this생략가능.반환형을 통해서 객체를 얻어온다.
			}
		}catch(Exception e) {
			System.out.println("updateGetArticle() 에러 유발"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;//content.jsp에서 받아서 출력	
	}
	//2.수정시켜주 메서드 작성->본인인지 확인절차=>회원탈퇴(암호를 비교->탈퇴)와 동일하다.)
	//BoardDTO 통으로 받아옴
	public int updateArticle(BoardDTO article) {//insertArticle(BoardDTO article)
		String dbpasswd="";//DB상에서 찾은 암호를 저장
		int x=-1;//게시물의 수정 유무
		
		try {
			con=pool.getConnection();
			//오라클일때사용 mysql일때는 사용X
			//con.setAutoCommit(false);//트랜잭션처리부분  , 자동으로 commit되는 걸 방지!
			sql="select passwd from board where num=?";//sql구문먼저 확인(오타)
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, article.getNum());//index ~ 에러유발->작성 안하면!!
			rs=pstmt.executeQuery();//select 구문에 사용.
			//암호를 찾았다면
			if(rs.next()) {
				dbpasswd=rs.getNString("passwd");
				System.out.println("dbpasswd=>"+dbpasswd);
				//dbpasswd(DB상에 저장된 암호)==passwd(웹상에서 입력한 암호)
				if(dbpasswd.equals(article.getPasswd())) {//본인인증
					sql="update board set writer=?,email=?,subject=?,passwd=?,";
					sql+=" content=? where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getEmail());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getPasswd());
					pstmt.setString(5, article.getContent());
					pstmt.setInt(6, article.getNum());
			
					int update=pstmt.executeUpdate();//insert,delete,update구문에 사용.
					System.out.println("게시판의 글수정 성공유무=>"+update);
					//con.commit();//실제 테이블에 반영 오라클에서만 사용
					x=1;//글수정 성공
				}else {//암호가 틀린경우
					x=0;//글수정 실패
				}
			}else {//암호가 존재하지 않은 경우
				x=-1;
			}
		}catch(Exception e) {
			System.out.println("updateArticle()실행 오류=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	//					select passwd from board where num=?
	//게시물 삭제->delete from board where num=?
	public int deleteArticle(int num, String passwd) {//회원탈퇴 기능과 동일
		
		String dbpasswd="";//DB상에서 찾은 암호를 저장
		int x=-1;//게시물의 삭제 유무
		
		try {
			con=pool.getConnection();
			//오라클일때사용 mysql일때는 사용X
			//con.setAutoCommit(false);//트랜잭션처리부분  , 자동으로 commit되는 걸 방지!
			sql="select passwd from board where num=?";//sql구문먼저 확인(오타)
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);//index ~ 에러유발->작성 안하면!!
			rs=pstmt.executeQuery();//select 구문에 사용.		
			//암호를 찾았다면
			if(rs.next()) {
				dbpasswd=rs.getNString("passwd");
				System.out.println("dbpasswd=>"+dbpasswd);
				//dbpasswd(DB상에 저장된 암호)==passwd(웹상에서 입력한 암호)
				if(dbpasswd.equals(passwd)) {//본인인증
					sql="delete from board where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, num);		
					int delete=pstmt.executeUpdate();//insert,delete,update구문에 사용.
					System.out.println("게시판의 글삭제 성공유무=>"+delete);
					//con.commit();//실제 테이블에 반영 오라클에서만 사용
					x=1;//글삭제 성공
				}else {//암호가 틀린경우
					x=0;//글삭제 실패
				}
			}
		}catch(Exception e) {
			System.out.println("deleteArticle()실행 오류=>"+e);//Log객체
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;		
	}
	
}
