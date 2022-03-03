package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board1.db.*; //�޼���ȣ��
import action.CommandAction;

//updateForm.jsp���� ��ȯ�ɶ� �����ϴ� �����͸� ã�Ƽ� ���
public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	    //content.jsp->num,pageNum
	   int num = Integer.parseInt(request.getParameter("num"));
	   String pageNum = request.getParameter("pageNum");
	   System.out.println("������ ���� content.jsp���� �Ѿ�°�");
	   System.out.println("num="+num+",pageNum="+pageNum);
	   
		   Board1DBMgr dbPro = new Board1DBMgr();
		   Board1DataBean article = dbPro.updateGetArticle(num);
		
		//�ش��(updateForm.jsp)���� ����� �Ӽ��� ����
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		   
		return "/updateForm.jsp";
	}

}
