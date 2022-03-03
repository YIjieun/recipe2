package board1.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;

import board1.db.Board1DBMgr;//������ �޼��带 ȣ��

//������ ������ ���ָ鼭 deletePro.jsp
public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	    //pageNum�� deleteForm.jsp���� �Ѿ��
		int num = Integer.parseInt(request.getParameter("num"));
	    String pageNum = request.getParameter("pageNum");
	    String passwd = request.getParameter("passwd");
	    
	    System.out.println("deleteForm.jsp���� �Ѿ�°�");
	    System.out.println
	      ("num="+num+",pageNum="+pageNum+",passwd="+passwd);
	    
	    //deleteArticle()ȣ��
	    Board1DBMgr dbPro = new Board1DBMgr();
	    int check=dbPro.deleteArticle(num,passwd);
		
		//�ش�信 �����ؼ� ����� �Ӽ����� ����
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return "/deletePro.jsp";
	}
}
