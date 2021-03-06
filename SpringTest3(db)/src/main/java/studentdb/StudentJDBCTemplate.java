package studentdb;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;//spring-jdbc~.jar파일

//DB접속해서 crud 할 수 있는 클래스(StudentDAO클래스)
public class StudentJDBCTemplate implements StudentDAO {

	private DataSource ds;//DB접속->Bean.xml에서 불러다 사용
	//접속후 sql실행->pstmt와 역할이 비슷한 클래스
	private JdbcTemplate jt;//->query()(select),update()(insert,update,delete)처리
	
	@Override
	public void setDs(DataSource ds) {
		// TODO Auto-generated method stub
		this.ds=ds;
		this.jt=new JdbcTemplate(ds);//JdbcTemplate(DB정보객체)
		System.out.println("setDateSource()호출되서 DB연결됨(ds)=>"+ds);
	}

	@Override
	public void create(Integer id, String name, Integer age) {
		// TODO Auto-generated method stub
		//형식)jdbcTemplate객체명.update(1.실행시킬sql구문,2.입력받을 매개변수명)
		String sql="insert into student values(?,?,?)";
		jt.update(sql,id,name,age);
		System.out.println("생성된 레코드 이름(id)=>"+id+" ,name=>"+name+" ,age=>"+age);
	}
	//select구문->RowMapper클래스객체가 필요->mapRow()호출->결과를 리턴받아야 된다.
	@Override
	public Student getStudent(Integer id) {//StudentRowMapper전달
		// TODO Auto-generated method stub
		String sql="select * from student where id=?";
		//형식)반환값=jdbctemplate객체명.queryForObject
		//												(실행시킬sql구문,매개변수명(배열표시),RowMapper객체명지정)
		//StudentRowMapper sm=new StudentRowMapper();->sm 대신에 익명객체로 전달
		Student st=jt.queryForObject(sql,new Object[] {id},new StudentRowMapper());
		return st;
	}

	@Override
	public List<Student> listStudents() {//query(한개 이상의 레코드를 불러올때)
		// TODO Auto-generated method stub
		String sql="select * from student";
		//형식)반환값=jdbctemplate객체명.query
		//												(실행시킬sql구문,RowMapper객체명지정)
		List<Student> sts=jt.query(sql,new StudentRowMapper());
		return sts;
	}
	//-----------------------------------------------
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		String sql="delete from student where id=?";
		jt.update(sql,id);
		System.out.println("삭제된 레코드 이름(id)=>"+id);
	}

	@Override
	public void update(Integer id, Integer age) {
		// TODO Auto-generated method stub
		String sql="update student set age=? where id=?";
		//pstmt.setInt(1,age),pstmt.setInt(2,id)
		jt.update(sql,age,id);
		System.out.println("수정된 레코드 이름(id)=>"+id+" ,age=>"+age);
	}
}
