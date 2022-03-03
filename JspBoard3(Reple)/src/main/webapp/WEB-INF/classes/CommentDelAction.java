package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import board1.db.Board1DBMgr;//������ �޼��带 ȣ��
import db.*;
import java.io.*;

//������ ������ ���ָ鼭 deletePro.jsp
public class CommentDelAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");
		int num=Integer.parseInt(request.getParameter("num"));
		int reboard1_num=Integer.parseInt(request.getParameter("reboard1_num"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		
		Board1DBMgr manager= new Board1DBMgr();
		manager.deleteComment(reboard1_num);
	
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();  
		out.println("<script language='javascript'>");  
		out.println("alert('������ ���� �Ǿ����ϴ�.');");  
		out.println("location.href = \"/board1/content.do?num="+num+"&pageNum="+pageNum+"\";");  
		out.println("</script>");  
		
		return "/board1/content.do";
	}
}
