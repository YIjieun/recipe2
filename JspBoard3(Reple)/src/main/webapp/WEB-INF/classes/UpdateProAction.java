package board1.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.db.Board1DBMgr;
import board1.db.Board1DataBean;
import action.CommandAction;

//������ ������ ���ָ鼭 updatePro.jsp
public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//updateForm.jsp->updatePro.jsp(�ѱ�ó��)
	     request.setCharacterEncoding("euc-kr");
	 
	    //pageNum�� updateForm.jsp���� �Ѿ��
	    String pageNum = request.getParameter("pageNum");
	    //������ ������ ����� �ڹٺ��� 
	    Board1DataBean article = new Board1DataBean();
	  
	    article.setNum(Integer.parseInt(request.getParameter("num")));
	    article.setWriter(request.getParameter("writer"));
	    article.setEmail(request.getParameter("email"));
	    article.setSubject(request.getParameter("subject"));
	    article.setContent(request.getParameter("content"));
	    article.setPasswd(request.getParameter("passwd"));
	    
	    //updateArticle()ȣ��
	    Board1DBMgr dbPro = new Board1DBMgr();
	    int check=dbPro.updateArticle(article);
		
		//�ش�信 �����ؼ� ����� �Ӽ����� ����
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return "/updatePro.jsp";
	}
}
