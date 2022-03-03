package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import board1.db.Board1DBMgr;//������ �޼��带 ȣ��
import db.*;
import java.io.*;
import java.util.Collections;
import java.util.List; 
import java.sql.Timestamp;

//������ ������ ���ָ鼭 deletePro.jsp
public class CommentProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		

        request.setCharacterEncoding("euc-kr");
		int num=Integer.parseInt(request.getParameter("num"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		String content = request.getParameter("content");
		String writer_id = request.getParameter("writer_id");

		//CommentForm commentForm=(CommentForm)form;
		CommentDataBean comment = new CommentDataBean();
		//PropertyUtils.copyProperties(comment,commentForm);
		//�޼��� ȣ��
	    comment.setNum(Integer.parseInt(request.getParameter("num")));
	    comment.setWriter_id(request.getParameter("writer_id"));
	    comment.setContent(request.getParameter("content"));
	    comment.setReg_date(new Timestamp(System.currentTimeMillis()));				
		Board1DBMgr manager= new Board1DBMgr();
		manager.addComment(comment);
	
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();  
		out.println("<script language='javascript'>");  
		out.println("location.href = \"../board1/content.do?num="+num+"&pageNum="+pageNum+"\";");  
		out.println("</script>");  

		return "/board1/content.do";
	}
}
