package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board1.db.*; //메서드호출
import action.CommandAction;

//updateForm.jsp으로 전환될때 수정하는 데이터를 찾아서 출력
public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	    //content.jsp->num,pageNum
	   int num = Integer.parseInt(request.getParameter("num"));
	   String pageNum = request.getParameter("pageNum");
	   System.out.println("수정을 위해 content.jsp에서 넘어온값");
	   System.out.println("num="+num+",pageNum="+pageNum);
	   
		   Board1DBMgr dbPro = new Board1DBMgr();
		   Board1DataBean article = dbPro.updateGetArticle(num);
		
		//해당뷰(updateForm.jsp)에서 사용할 속성을 공유
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		   
		return "/updateForm.jsp";
	}

}
