package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//�߰�
import board1.db.*;//BoardDataBean,BoardDBMgr
import action.CommandAction;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		  //list.jsp->num,pageNum
		  int num = Integer.parseInt(request.getParameter("num"));
		  String pageNum = request.getParameter("pageNum");//��������ȣ
		  
		  System.out.println("list.jsp���� �Ѿ�°�");
		  System.out.println("num="+num+",pageNum="+pageNum);
		   
			  Board1DBMgr dbPro = new Board1DBMgr();
			  Board1DataBean article = dbPro.getArticle(num);

			  //content.jsp->�ۼ���,�ۻ���,�۴亯��
			  int ref = article.getRef();
			  int re_step = article.getRe_step();
			  int re_level = article.getRe_level();
			  
			  System.out.println("ã�� �������� ref="+ref);
			  System.out.println
			     ("re_step="+re_step+",re_level="+re_level);
		 
	    //content.jsp���� ����� ���ִ� ������ ��ü�� ���� ����
	    request.setAttribute("num", new Integer(num));
	    request.setAttribute("pageNum", new Integer(pageNum));
	    request.setAttribute("article", article);
		//Controller->content.jsp
		return "/content.jsp";
	}
}
