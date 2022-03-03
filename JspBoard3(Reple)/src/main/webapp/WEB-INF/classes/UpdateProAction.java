package board1.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.db.Board1DBMgr;
import board1.db.Board1DataBean;
import action.CommandAction;

//실제로 수정을 해주면서 updatePro.jsp
public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//updateForm.jsp->updatePro.jsp(한글처리)
	     request.setCharacterEncoding("euc-kr");
	 
	    //pageNum이 updateForm.jsp에서 넘어옴
	    String pageNum = request.getParameter("pageNum");
	    //수정할 데이터 저장된 자바빈즈 
	    Board1DataBean article = new Board1DataBean();
	  
	    article.setNum(Integer.parseInt(request.getParameter("num")));
	    article.setWriter(request.getParameter("writer"));
	    article.setEmail(request.getParameter("email"));
	    article.setSubject(request.getParameter("subject"));
	    article.setContent(request.getParameter("content"));
	    article.setPasswd(request.getParameter("passwd"));
	    
	    //updateArticle()호출
	    Board1DBMgr dbPro = new Board1DBMgr();
	    int check=dbPro.updateArticle(article);
		
		//해당뷰에 공유해서 사용할 속성값을 저장
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return "/updatePro.jsp";
	}
}
