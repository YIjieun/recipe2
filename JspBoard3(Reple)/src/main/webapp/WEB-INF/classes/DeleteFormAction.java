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
		System.out.println("������ ���� content.jsp���� �Ѿ�°�");
		System.out.println("num="+num+",pageNum="+pageNum);
	
		//�ش�信�� ����� �Ӽ��� ����
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum",new Integer(pageNum));
		
		return "/deleteForm.jsp";
	}
}
