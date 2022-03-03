package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import board1.db.*;//BoardDataBean,BoardDBMgr
import action.CommandAction;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		  //list.jsp->num,pageNum
		  int num = Integer.parseInt(request.getParameter("num"));
		  String pageNum = request.getParameter("pageNum");//페이지번호
		  
		  System.out.println("list.jsp에서 넘어온값");
		  System.out.println("num="+num+",pageNum="+pageNum);
		   
			  Board1DBMgr dbPro = new Board1DBMgr();
			  Board1DataBean article = dbPro.getArticle(num);

			  //content.jsp->글수정,글삭제,글답변글
			  int ref = article.getRef();
			  int re_step = article.getRe_step();
			  int re_level = article.getRe_level();
			  
			  System.out.println("찾은 데이터의 ref="+ref);
			  System.out.println
			     ("re_step="+re_step+",re_level="+re_level);
		 
	    //content.jsp에서 사용할 수있는 변수와 객체를 서로 공유
	    request.setAttribute("num", new Integer(num));
	    request.setAttribute("pageNum", new Integer(pageNum));
	    request.setAttribute("article", article);
		//Controller->content.jsp
		return "/content.jsp";
	}
}
