//Controller���� ����ϴ� �׼�Ŭ������ �ۼ�
//list.jsp���� ȣ���ϴ� �޼��带 �ۼ�,�̵��ϴ� ���
package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//�߰�
import board1.db.*;//�޼��� ȣ��(���ü �ʿ�)
import java.util.*;//Collection,List
import action.CommandAction;

public class ListAction implements CommandAction {
   
	public String requestPro(HttpServletRequest request,
            HttpServletResponse response) 
             throws Throwable {
		
		  //����Ʈ �������� ����->1
		   String pageNum = request.getParameter("pageNum");
		   String search = request.getParameter("search");
		   String searchtext = request.getParameter("searchtext");
		   
		  //����¡ ó�����ִ� �޼ҵ带 ȣ��
		  Board1DBMgr bdPro = new Board1DBMgr();
		  int count = bdPro.getArticleSearchCount(search,searchtext);
		  System.out.println("�˻��� ���ڵ��="+count);
		  
		  Hashtable<String,Integer> pgList = bdPro.pageList(pageNum, count);
		  List articleList = null;//�� �����
		  
		  //���������� ��� ���ڵ带 ������ ������ ����
		  if(count > 0){ 
			System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));//����¡ó��
			
		    articleList = bdPro.getBoardArticles(pgList.get("startRow"), pgList.get("pageSize"), search, searchtext);
		  }else{ //�����Ͱ� ���ٴ� ǥ��
			articleList = Collections.EMPTY_LIST; 
		  }
		
		  request.setAttribute("search",search);
		  request.setAttribute("searchtext",searchtext);
		  request.setAttribute("pgList",pgList);
		  request.setAttribute("articleList",articleList);
		  //�̵��ϴ� �������� ��ȯ
		  return "/list.jsp";
	}
}
