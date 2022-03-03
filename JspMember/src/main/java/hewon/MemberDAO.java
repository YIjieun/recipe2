package hewon;//같은 패키지이기에 import X

//웹상에서 호출할 메서드를 작성해서 불러다 사용할 클래스(DAO역할)
import java.sql.*;//DB관련
import java.util.*;//자료구조->Vector,ArrayList,Hashtable,,,



public class MemberDAO {//XXXXMgr or XXXXDAO 
										//->DBConnectionMgr과 has a 관계
	
	//1). 멤버변수에 연결할 클래스의 객체를 선언
	private DBConnectionMgr pool=null;//getConnection(), freeConnection()필요
	
	//2. 공통으로 접속할 경우 필요로하는 멤버변수 선언!!!(p401 선언X)
	private Connection con=null;
	private PreparedStatement pstmt=null;//SQL 실행 목적
	private ResultSet rs=null;//select 구문
	private String sql="";//실행시킬 SQL구문 저장목적
	
	//2) 생성자를 통해서 자동으로 객체를 얻어올 수 있도록 연결
	public MemberDAO() {//상대방의 객체를 얻어오는 구문사용
		try {
			pool=DBConnectionMgr.getInstance();
			System.out.println("pool=>"+pool);
		}catch(Exception e) {
			System.out.println("DB연결 실패=>"+e);//e.toString()
		}		
	}
	//3. 요구분석에 따른 웹상에서 호출할 메서드를 작성
	//1) 회원로그인
	//select id,passwd from member where id='nup' and passwd='1234';
	//있다 없다만 판단하기에 자료형은 int가 아니고 boolean!! 메모리 절약
	public boolean loginCheck(String id,String passwd) {// 반환값O 매개변수O
		//1.DB연결
		boolean check=false;//초기값 false-> '없음'
		
		//2.SQL실행->결과
		try {
			con=pool.getConnection();//이미 만들어진 Connection 객체를 반환
			System.out.println("con=>"+con);
			sql="select id,passwd from member where id=? and passwd=?";//제일먼저 확인
			//pstmt 객체 생략->NullPointException 발생됨.
			pstmt=con.prepareStatement(sql);//con.prepareStatement(실행시킬 sql구문)
			pstmt.setString(1, id);//1. ?의 순서 2.저장할값(매개변수명)
			pstmt.setString(2, passwd);
			rs=pstmt.executeQuery();//select구문
			check=rs.next();//데이터가 존재=> true or 없으면 false
		}catch(Exception e) {
			System.out.println("loginCheck() 실행에러유발=>"+e);
		}finally {//3. 메모리 해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check;// LoginProc.jsp에서 받아서 페이지 이동시킬때 필요
	}
		
	//2) 중복 id 체크
	//select id from member where id='nup';
	public boolean checkId(String id) {
		boolean check=false;//중복id 체크		
			//2.SQL실행->결과
			try {
				con=pool.getConnection();//이미 만들어진 Connection 객체를 반환
				sql="select id from member where id=?";//제일먼저 확인
				//pstmt 객체 생략->NullPointException 발생됨.
				pstmt=con.prepareStatement(sql);//con.prepareStatement(실행시킬 sql구문)
				pstmt.setString(1, id);//1. ?의 순서 2.저장할값(매개변수명)
				rs=pstmt.executeQuery();//select구문
				check=rs.next();//데이터가 존재=> true or 없으면 false
			}catch(Exception e) {
				System.out.println("CheckId() 실행에러유발=>"+e);
			}finally {//3. 메모리 해제
				pool.freeConnection(con, pstmt, rs);
			}
			return check;// Idcheck.jsp에서 받아서 존재유무확인
		}
	
	//3) 우편번호 ->ZipCheck.jsp에서 호출
	//public Vector<ZipcodeDTO>->예전방식
	public ArrayList<ZipcodeDTO> zipcodeRead(String area3){
		//레코드 1개 이상 담을 변수(객체)선언
		ArrayList<ZipcodeDTO> zipList=new ArrayList();//미리생성
		try {
			con=pool.getConnection();					//'%미아2동%'
			//select * from zipcode where area3 like '미아2동%';
			sql="select * from zipcode where area3 like '"+area3+"%'";//sql구문의 에러 확인O
			//	sql="select * from zipcode where area3 like? ";
			pstmt=con.prepareStatement(sql);
			//pstmt.setString(1, "%"+area3+"%");//sql구문의 에러 확인 불가X
			rs=pstmt.executeQuery();
			System.out.println("검색된 sql구문 확인=>"+sql);
			//검색된 레코드의 값을 필드 별로 담는 소스코드->찾은 레코드1개->if(rs.next())
																	//한개 이상----->while(rs.next())
			while(rs.next()) {
				ZipcodeDTO tempZipcode=new ZipcodeDTO();
				tempZipcode.setZipcode(rs.getString("zipcode"));//"142-824"값이 정해진X
				tempZipcode.setArea1(rs.getString("area1"));//시
				tempZipcode.setArea2(rs.getString("area2"));//null
				tempZipcode.setArea3(rs.getString("area3"));//null
				tempZipcode.setArea4(rs.getString("area4"));//null
				//ArrayList에 담는 구문을 작성
				zipList.add(tempZipcode);//vector->vList.add(tempZipcode);
			}
		}catch(Exception e) {
			System.out.println("zipcodeRead() 실행오류=>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return zipList;//ZipCheck.jsp에서 메서드의 반환값->for문
	}

	//4) 회원가입->insert into member values(?,?,,,,)->웹상에서 확인필요(반환값O)
		public boolean memberInsert(MemberDTO mem) {//boolean->성공?실패?
			boolean check=false;//회원가입 성공유무
			//--트랜잭션->오라클은 필수(All of Nothing)=>자동으로 commit(X)
				try {
					con=pool.getConnection();
					//추가
					con.setAutoCommit(false);//default->con.setAutoConnit(true); 오라클일 경우 사용
					//----------------------------------------------
					sql="insert into member values(?,?,?,?,?,?,?,?)";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1,mem.getMem_id());//첫번째 ? 자리
					pstmt.setString(2,mem.getMem_passwd());//두번째 ? 자리
					pstmt.setString(3,mem.getMem_name());//세번째 ? 자리
					pstmt.setString(4,mem.getMem_email());//네번째 ? 자리
					pstmt.setString(5,mem.getMem_phone());//다섯번째 ? 자리
					pstmt.setString(6,mem.getMem_zipcode());//여섯번째 ? 자리
					pstmt.setString(7,mem.getMem_address());//일곱번째 ? 자리
					pstmt.setString(8,mem.getMem_job());//여덟번째 ? 자리
					
				   int insert =pstmt.executeUpdate();//반환값 1(성공), 0(실패), (메모리에 insert 성공상태)
				   con.commit();//실제 테이블에 반영  commit중요!!!!!
				   
				   System.out.println("insert(데이터 입력유무)=>"+insert);
				   if(insert>0) {//if(insert==1)
					   check=true;//데이터 성공확인
				   }					
			}catch(Exception e) {
				System.out.println("memberInsert() 실행오류=>"+e);//e.toString()
			}finally {
				pool.freeConnection(con,pstmt);//rs->select구문시에만 필요
			}
			return check;//memberInsert.jsp에서 메서드 반환값
		}
	//5) 회원수정-> 특정 회원 찾기
		//select * from member where id='nup';//매개변수 1개 반환값이 객체인 메서드
		public MemberDTO getMember(String mem_id) {
			
			MemberDTO mem=null;//id값에 해당되는 레코드 한개를 저장
			try {
				con=pool.getConnection();		
				sql="select * from member where id=?";//sql구문의 에러 확인O 
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,mem_id);//sql구문의 에러 확인 불가X
				rs=pstmt.executeQuery();
				//System.out.println("검색된 sql구문 확인=>"+sql);
				//검색된 레코드의 값을 필드 별로 담는 소스코드->찾은 레코드1개->if(rs.next())
																		//한개 이상----->while(rs.next())
				if(rs.next()) {
					//찾은값->Setter Method의 매개변수로 저장=->웹에 출력=>Getter Method
					mem=new MemberDTO();
					mem.setMem_id(rs.getString("id"));//<%=mem.getMem_id()%>
					mem.setMem_passwd(rs.getString("passwd"));
					mem.setMem_name(rs.getString("name"));
					mem.setMem_email(rs.getString("e_mail"));//e_mail조심하기!!
					mem.setMem_phone(rs.getString("phone"));
					mem.setMem_zipcode(rs.getString("zipcode"));
					mem.setMem_address(rs.getString("address"));
					mem.setMem_job(rs.getString("job"));					
				}
			}catch(Exception e) {
				System.out.println("getMember() 실행오류=>"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return mem;//MemberUpdate.jsp에서 메서드의 반환값
		}
	
	//6) 찾은 회원을 수정=>회원가입래주는 메서드와 동일(sql구문이 다르다)
		public boolean memberUpdate(MemberDTO mem) {//ex) 상품수정 SangDTO sang
			
			boolean check=false;//회원수정 성공유무
			
				//에러가 발생이 되면 흐름에 따라서 디비깅 코딩을 해야한다.
				System.out.println("===memberUpdata() 내부===");
				System.out.println("mem.getMem_id()=>"+mem.getMem_id());//null
				//--------------------------------------------------------------------------
				try {
					con=pool.getConnection();
					//추가
					con.setAutoCommit(false);//default->con.setAutoConnit(true); 오라클일 경우 사용
					//----------------------------------------------
					sql="update member set passwd=?,name=?,e_mail=?,phone=?,zipcode=?,"
							+" address=?, job=?  where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1,mem.getMem_passwd());//첫번째 ? 자리
					pstmt.setString(2,mem.getMem_name());//두번째 ? 자리
					pstmt.setString(3,mem.getMem_email());//세번째 ? 자리
					pstmt.setString(4,mem.getMem_phone());//네번째 ? 자리
					pstmt.setString(5,mem.getMem_zipcode());//다섯번째 ? 자리
					pstmt.setString(6,mem.getMem_address());//여섯번째 ? 자리
					pstmt.setString(7,mem.getMem_job());//일곱번째 ? 자리
					pstmt.setString(8,mem.getMem_id());//여덟번째 ? 자리
					
				   int update =pstmt.executeUpdate();//반환값 1(성공), 0(실패), (메모리에 update 성공상태)
				   con.commit();//실제 테이블에 반영  commit중요!!!!!
				   
				   System.out.println("update(데이터 수정유무)=>"+update);
				   if(update==1) {//if(insert>0)
					   check=true;//데이터 수정성공확인
				   }					
			}catch(Exception e) {
				System.out.println("memberUpdate() 실행오류=>"+e);//e.toString()
				try{con.rollback();}catch(Exception e2) {e.printStackTrace();}
			}finally {
				pool.freeConnection(con,pstmt);//rs->select구문시에만 필요
			}
			return check;//memberUpdate.jsp에서 메서드 반환값
		}
	//7) 회원탈퇴=>게시판의 글수정,글삭제하기와 소스코드가 동일
		//select passwd from member where id='nup'
		//delete from member where id='nup'//id
		public int memberDelete (String id, String passwd) {//웹상에서 입력한 암호
			String dbpasswd="";//DB상에서 찾은 암호를 저장
			int x=-1;//회원탈퇴 유무
			
			try {
				con=pool.getConnection();
				//추가
				con.setAutoCommit(false);//트랜잭션처리부분  , 자동으로 commit되는 걸 방지!
				sql="select passwd from member where id=?";//sql구문먼저 확인(오타)
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);//index ~ 에러유발->작성 안하면!!
				rs=pstmt.executeQuery();//select 구문에 사용.
				//암호를 찾았다면
				if(rs.next()) {
					dbpasswd=rs.getNString("passwd");
					System.out.println("dbpasswd=>"+dbpasswd);
					//dbpasswd(DB상에 저장된 암호)==passwd(웹상에서 입력한 암호)
					if(dbpasswd.equals(passwd)) {//본인인증
						pstmt=con.prepareStatement("delete from member where id=?");
						pstmt.setString(1, id);
						int delete=pstmt.executeUpdate();//insert,delete,update구문에 사용.
						System.out.println("delete(회원탈퇴 성공유무)=>"+delete);
						con.commit();//실제 테이블에 반영
						x=1;//회원탈퇴 성공
					}else {//암호가 틀린경우
						x=0;//회원탈퇴 실패
					}
				}else {//암호가 존재하지 않은 경우
					x=-1;
				}
			}catch(Exception e) {
				System.out.println("memberDelete()실행 오류=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return x;//deletePro.jsp에서 반환 받아서 x
		}
		
	//8) 회원리스트=> 과제) 게시판의 글목록보기-> 테이블의 필드, 형태만 변경
		
		//3.메서드 작성(페이징 처리를 위한 메서드 작성)=>총레코드수(=총게시물수=총회원수)
		//select count(*) from board;  select count(*) from member;
	/*
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
		/*		sql="select * from board order by ref desc,re_step limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, start-1);//mysql은 레코드순번이 내부적으로 0부터 시작
				pstmt.setInt(2, end);//불러와서 담을 갯수(ex 10)
				rs=pstmt.executeQuery();//select구문
				if(rs.next()) {//보여주는 결과가 1개라도 있다면
					articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간생성
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
		} */
		
		
		
		//------게시판의 글쓰기 및 답변 글쓰기
		//insert into board values(?,?,,,,)
		/*
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
				//답변글이라면
				if(num!=0) {
					;//내일 to be continued
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
		}*/
}
