package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import board1.db.Board1DBMgr;//삭제할 메서드를 호출
import db.*;
import java.io.*;

//실제로 삭제를 해주면서 deletePro.jsp
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
		out.println("alert('덧글이 삭제 되었습니다.');");  
		out.println("location.href = \"/board1/content.do?num="+num+"&pageNum="+pageNum+"\";");  
		out.println("</script>");  
		
		return "/board1/content.do";
	}
}
