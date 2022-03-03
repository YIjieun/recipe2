/**
 *  게시판의 데이터를 저장,출력용으로 사용하는 빈즈
 */
package board1.db;

import java.sql.*;

public class Board1DataBean {

	//멤버변수 선언
	private int num;//게시판의 게시물번호
	private String writer;//작성자
	private String subject;//글제목
	private String email;//이메일
	private String content;//글내용
	private String passwd;//수정,삭제->본인임을 확인
	private Timestamp reg_date;//작성날짜
	private int ref;//그룹번호
	private int re_step;//답변글사이의 정렬
	private int re_level;//들여쓰기
	private int comment_count;//덧글 카운트
	
	public int getNum() {
		return num;
	}
	public String getWriter() {
		return writer;
	}
	public String getSubject() {
		return subject;
	}
	public String getEmail() {
		return email;
	}
	public String getContent() {
		return content;
	}
	public String getPasswd() {
		return passwd;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public int getRef() {
		return ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public int getComment_count() {
		return comment_count;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setReg_date(Timestamp regDate) {
		reg_date = regDate;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public void setRe_step(int reStep) {
		re_step = reStep;
	}
	public void setRe_level(int reLevel) {
		re_level = reLevel;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

}
