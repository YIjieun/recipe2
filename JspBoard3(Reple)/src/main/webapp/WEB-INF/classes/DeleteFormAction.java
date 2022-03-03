package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		   //content.jsp->num,pageNum
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		System.out.println("삭제를 위해 content.jsp에서 넘어온값");
		System.out.println("num="+num+",pageNum="+pageNum);
	
		//해당뷰에서 사용할 속성을 저장
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum",new Integer(pageNum));
		
		return "/deleteForm.jsp";
	}
}
