/**
 *  �Խ����� �����͸� ����,��¿����� ����ϴ� ����
 */
package board1.db;

import java.sql.*;

public class Board1DataBean {

	//������� ����
	private int num;//�Խ����� �Խù���ȣ
	private String writer;//�ۼ���
	private String subject;//������
	private String email;//�̸���
	private String content;//�۳���
	private String passwd;//����,����->�������� Ȯ��
	private Timestamp reg_date;//�ۼ���¥
	private int ref;//�׷��ȣ
	private int re_step;//�亯�ۻ����� ����
	private int re_level;//�鿩����
	private int comment_count;//���� ī��Ʈ
	
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
