package studentdb;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

//RowMapper인터페이스->select구문을 실행(mapRow()호출)=>DTO에 담아서 결과를 리턴
//RowMapper<BoardVO>=>형변환을 시켜서 받을려고 제너릭을 준거임
public class StudentRowMapper implements RowMapper<Student> {
	
	//callBack 메서드->내부적으로 자동적으로 호출해주는 메서드
	//1.ResultSet객체를 반환(테이블 정보), 2. 검색된 레코드갯수->갯수만큼 for문을 돌려서 담아줘서 리턴
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("mapRow() 호출됨(rowNum)=>"+rowNum);
		//while(rs.next()){담아주는 구문}
		Student st=new Student();//StudentDTO객체를 생성=>검색된 갯수만큼 필드별로 담기
		st.setId(rs.getInt("id"));
		st.setName(rs.getString("name"));
		st.setAge(rs.getInt("age"));
		return st;//query()의 반환값으로 전달받을 수 있다.
	}
}
