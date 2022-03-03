package db; //데이터 저장빈...

import java.sql.*;

public class CommentDataBean{
	
	//멤버변수
	private int reboard1_num; //댓글테이블의 게시물번호
	private String writer_id; // 작성자
	private Timestamp reg_date; // 작성날짜
	private String content; // 글 내용
	private int num; //게시판의 게시물번호
	
	public int getReboard1_num() {
		return reboard1_num;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public String getContent() {
		return content;
	}
	public int getNum() {
		return num;
	}
	public void setReboard1_num(int reboard1_num) {
		this.reboard1_num = reboard1_num;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
	
	
