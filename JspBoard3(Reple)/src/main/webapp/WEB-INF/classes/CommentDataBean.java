package db; //������ �����...

import java.sql.*;

public class CommentDataBean{
	
	//�������
	private int reboard1_num; //������̺��� �Խù���ȣ
	private String writer_id; // �ۼ���
	private Timestamp reg_date; // �ۼ���¥
	private String content; // �� ����
	private int num; //�Խ����� �Խù���ȣ
	
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
	
	
