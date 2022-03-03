package board1.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;

import board1.db.Board1DBMgr;//삭제할 메서드를 호출

//실제로 삭제를 해주면서 deletePro.jsp
public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	    //pageNum이 deleteForm.jsp에서 넘어옴
		int num = Integer.parseInt(request.getParameter("num"));
	    String pageNum = request.getParameter("pageNum");
	    String passwd = request.getParameter("passwd");
	    
	    System.out.println("deleteForm.jsp에서 넘어온값");
	    System.out.println
	      ("num="+num+",pageNum="+pageNum+",passwd="+passwd);
	    
	    //deleteArticle()호출
	    Board1DBMgr dbPro = new Board1DBMgr();
	    int check=dbPro.deleteArticle(num,passwd);
		
		//해당뷰에 공유해서 사용할 속성값을 저장
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return "/deletePro.jsp";
	}
}
