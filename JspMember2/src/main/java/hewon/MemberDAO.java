package hewon;//같은 패키지이기에 import X

//웹상에서 호출할 메서드를 작성해서 불러다 사용할 클래스(DAO역할)
import java.sql.*;//DB관련
import java.util.*;//자료구조->Vector,ArrayList,Hashtable,,,

public class MemberDAO {//XXXXMgr or XXXXDAO 
	                                    //->DBConnectionMgr과 has a 관계

	//1).멤버변수에 연결할 클래스의 객체를 선언
	private DBConnectionMgr pool=null;//getConnection(), freeConnection()필요
	
	//2.공통으로 접속할 경우 필요로하는 멤버변수 선언(p401 선언 X)
	private Connection con=null;
	private PreparedStatement pstmt=null;//SQL실행 목적
	private ResultSet rs=null;//select 구문
	private String sql="";//실행시킬 SQL구문 저장목적
	
	//2)생성자를 통해서 자동으로 객체를 얻어올 수 있도록 연결
	public MemberDAO() {//상대방의 객체를 얻어오는 구문사용
		try {
			pool=DBConnectionMgr.getInstance();
			System.out.println("pool=>"+pool);
		}catch(Exception e) {
			System.out.println("DB연결 실패=>"+e);//e.toString()
		}
	}
	
	//3.요구분석에 따른 웹상에서 호출할 메서드를 작성
	//1)회원로그인
	//select id,passwd from member where id='nup' and passwd='1234';
	public boolean loginCheck(String id,String passwd) {
		//1.DB연결
		boolean check=false;//지역변수
		//2.SQL실행->결과
		try {
		    con=pool.getConnection();//이미 만들어진 Connection 반환
		    System.out.println("con=>"+con);
		    sql="select id,passwd from member where id=? and passwd=?";//제일먼저 확인
		    //pstmt객체 생략->NullPointerException 발생됨
		    pstmt=con.prepareStatement(sql);//con.prepareStatement(실행시킬 sql구문)
		    pstmt.setString(1, id);//1.?의 순서 2.저장할값(매개변수명)
		    pstmt.setString(2, passwd);
		    rs=pstmt.executeQuery();
		    check=rs.next();//데이터가 존재=>true or 없으면 false
		}catch(Exception e) {
			System.out.println("loginCheck() 실행에러유발=>"+e);
		}finally{//3.메모리 해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check;//LoginProc.jsp에서 받아서 페이지 이동시킬때 필요
	}
	
	//2)중복 id체크
	//select id from member where id='nup';
	public boolean checkId(String id) {
		boolean check=false;//중복id 체크
		//2.SQL실행->결과
		try {
		    con=pool.getConnection();//이미 만들어진 Connection 반환
		    sql="select id from member where id=?";//제일먼저 확인
		    //pstmt객체 생략->NullPointerException 발생됨
		    pstmt=con.prepareStatement(sql);//con.prepareStatement(실행시킬 sql구문)
		    pstmt.setString(1, id);//1.?의 순서 2.저장할값(매개변수명)
		    rs=pstmt.executeQuery();
		    check=rs.next();//데이터가 존재=>true or 없으면 false
		}catch(Exception e) {
			System.out.println("checkId() 실행에러유발=>"+e);
		}finally{//3.메모리 해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check;//IdCheck.jsp에서 받아서 존재유무확인
	}
	
	//3)우편번호 ->ZipCheck.jsp에서 호출
	//public Vector<ZipcodeDTO>
	public ArrayList<ZipcodeDTO> zipcodeRead(String area3){
		//레코드 한개이상 담을 변수(객체)선언
		ArrayList<ZipcodeDTO> zipList=new ArrayList();//미리생성
		try {
			con=pool.getConnection();
			//select * from zipcode where area3 like '미아2동%';
		  sql="select * from zipcode where area3 like '"+area3+"%'";//sql구문확인가능
		  //sql="select * from zipcode where area3 like ?";
		  pstmt=con.prepareStatement(sql);
		 // pstmt.setString(1, "%"+area3+"%");//sql구문을 확인불가X
		  rs=pstmt.executeQuery();
		  System.out.println("검색된 sql구문 확인=>"+sql);
		  //검색된 레코드의 값을 필드별로 담는 소스코드->찾은 레코드1개->if(rs.next())
		  //                                                   한개이상----------->while(rs.next())
		  while(rs.next()){
			  ZipcodeDTO tempZipcode=new ZipcodeDTO();
			  tempZipcode.setZipcode(rs.getString("zipcode"));//"142-824"값이 정해진X
			  tempZipcode.setArea1(rs.getString("area1"));//시->부적합열
			  tempZipcode.setArea2(rs.getString("area2"));//null
			  tempZipcode.setArea3(rs.getString("area3"));//null
			  tempZipcode.setArea4(rs.getString("area4"));//null
			  //ArrayList에 담는 구문을 작성
			  zipList.add(tempZipcode);//vList.add(tempZipcode);
		  }
		}catch(Exception e) {
			System.out.println("zipcodeRead() 실행오류=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
	   return zipList;//ZipCheck.jsp에서 메서드의 반환값->for문->null
	}
	
	//4)회원가입->insert into member values(?,?,,,,)->웹상에서 확인(반환값 O)
	public boolean memberInsert(MemberDTO mem) {
		boolean check=false;//회원가입 성공유무
		//--트랜잭션->오라클은 필수(All or Nothing)=>자동으로 commit(X)
		try {
			con=pool.getConnection();
			//추가
			con.setAutoCommit(false);//default->con.setAutoCommit(true);
			//----------------------------
			sql="insert into member values(?,?,?,?,?,?,?,?)";//sql구문
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mem.getMem_id());
			pstmt.setString(2, mem.getMem_passwd());
			pstmt.setString(3, mem.getMem_name());
			pstmt.setString(4, mem.getMem_email());
			pstmt.setString(5, mem.getMem_phone());
			pstmt.setString(6, mem.getMem_zipcode());
			pstmt.setString(7, mem.getMem_address());
			pstmt.setString(8, mem.getMem_job());
			
			int insert=pstmt.executeUpdate();//반환값 1(성공), 0 (실패) (메모리에 insert 성공)
			con.commit();//실제 테이블에 반영
			System.out.println("insert(데이터 입력유무)=>"+insert);
			if(insert > 0) {//if(insert==1){
				check=true;//데이터 성공확인
			}	
		}catch(Exception e) {
			System.out.println("memberInsert() 실행오류=>"+e);//e.toString()
		}finally {
			pool.freeConnection(con,pstmt);//rs->select구문할때만 필요
		}
		return check;//memberInsert.jsp에서 메서드의 반환값
	}
	
	//5)회원수정->특정 회원 찾기
	//select * from member where id='nup'; //매개변수 1개 반환값이 객체인 메서드
	public MemberDTO getMember(String mem_id) {
		
		MemberDTO mem=null;//id값에 해당되는 레코드 한개를 저장
		try {
			con=pool.getConnection();
		  sql="select * from member where id=?";
		  pstmt=con.prepareStatement(sql);
		  pstmt.setString(1, mem_id);//sql구문을 확인불가X
		  rs=pstmt.executeQuery();
		  //검색된 레코드의 값을 필드별로 담는 소스코드->찾은 레코드1개->if(rs.next())
		  //                                                   한개이상----------->while(rs.next())
		  if(rs.next()){
			  //찾은값->Setter Method의 매개변수로 저장=->웹에 출력=>Getter Method
			  mem=new MemberDTO();//null
			  mem.setMem_id(rs.getString("id"));//<%=mem.getMem_id()%>
			  mem.setMem_passwd(rs.getString("passwd"));
			  mem.setMem_name(rs.getString("name"));
			  mem.setMem_phone(rs.getString("phone"));
			  mem.setMem_zipcode(rs.getString("zipcode"));
			  mem.setMem_address(rs.getString("address"));
			  mem.setMem_email(rs.getString("e_mail"));
			  mem.setMem_job(rs.getString("job"));
		  }
		}catch(Exception e) {
			System.out.println("getMember() 실행오류=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
	   return mem;//MemberUpdate.jsp에서 메서드의 반환값
	}
	
	//6)찾은 회원을 수정=>회원가입해주는 메서드와 동일(sql구문이 다르다)
	public boolean memberUpdate(MemberDTO mem) {//SangDTO sang
		boolean check=false;//회원수정 성공유무
		
		//에러가 발생이 되면 흐름에 따라서 디버깅 코딩을 해야 한다.
		System.out.println("==memberUpdate() 내부==");
		System.out.println("mem.getMem_id()=>"+mem.getMem_id());//null
		//----------------------------------------------------------------------
		try {
			con=pool.getConnection();
			//추가
			con.setAutoCommit(false);//default->con.setAutoCommit(true);
			//----------------------------
			sql="update member set passwd=?,name=?,e_mail=?,phone=?,"
			       +" zipcode=?, address=?, job=?  where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mem.getMem_passwd());
			pstmt.setString(2, mem.getMem_name());
			pstmt.setString(3, mem.getMem_email());
			pstmt.setString(4, mem.getMem_phone());
			pstmt.setString(5, mem.getMem_zipcode());
			pstmt.setString(6, mem.getMem_address());
			pstmt.setString(7, mem.getMem_job());
			pstmt.setString(8, mem.getMem_id());//null
			
			int update=pstmt.executeUpdate();//반환값 1(성공), 0 (실패) (메모리에 insert 성공)
			con.commit();//실제 테이블에 반영
			System.out.println("update(데이터 수정유무)=>"+update);
			if(update==1) {
				check=true;//데이터 수정성공확인
			}	
		}catch(Exception e) {
			System.out.println("memberUpdate() 실행오류=>"+e);//e.toString()
			try{con.rollback();}catch(Exception e2) {e2.printStackTrace();}
		}finally {
			pool.freeConnection(con,pstmt);//rs->select구문할때만 필요
		}
		return check;//memberUpdate.jsp에서 메서드의 반환값
	}
	//7)회원탈퇴
	
	//8)회원리스트=>과제) 게시판의 글목록보기->테이블의 필드,형태만 변경
	

}
