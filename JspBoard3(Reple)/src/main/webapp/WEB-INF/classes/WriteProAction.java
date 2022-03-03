package board1.action;

import javax.servlet.http.*;

import board1.db.*;//빈즈의 메서드 호출
import java.sql.Timestamp;
import action.CommandAction;

public class WriteProAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		    //writeForm.jsp->writePro.jsp(한글처리)
		     request.setCharacterEncoding("euc-kr");
		 
		    Board1DataBean article = new Board1DataBean();
		    article.setNum(Integer.parseInt(request.getParameter("num")));
		    article.setWriter(request.getParameter("writer"));
		    article.setEmail(request.getParameter("email"));
		    article.setSubject(request.getParameter("subject"));
		    article.setPasswd(request.getParameter("passwd"));
		    article.setReg_date(new Timestamp(System.currentTimeMillis()));
		    article.setRef(Integer.parseInt(request.getParameter("ref")));
		    article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		    article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		    article.setContent(request.getParameter("content"));
		    
		    //insertArticle()호출
		    Board1DBMgr dbPro = new Board1DBMgr();
		    dbPro.insertArticle(article);
		  
		return "writePro.jsp";
	}
}
