//Controller에서 사용하는 액션클래스를 작성
//list.jsp에서 호출하는 메서드를 작성,이동하는 기능
package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import board1.db.*;//메서드 호출(빈즈객체 필요)
import java.util.*;//Collection,List
import action.CommandAction;

public class ListAction implements CommandAction {
   
	public String requestPro(HttpServletRequest request,
            HttpServletResponse response) 
             throws Throwable {
		
		  //디폴트 페이지를 지정->1
		   String pageNum = request.getParameter("pageNum");
		   String search = request.getParameter("search");
		   String searchtext = request.getParameter("searchtext");
		   
		  //페이징 처리해주는 메소드를 호출
		  Board1DBMgr bdPro = new Board1DBMgr();
		  int count = bdPro.getArticleSearchCount(search,searchtext);
		  System.out.println("검색된 레코드수="+count);
		  
		  Hashtable<String,Integer> pgList = bdPro.pageList(pageNum, count);
		  List articleList = null;//글 내용들
		  
		  //페이지별로 몇개의 레코드를 보여줄 것인지 결정
		  if(count > 0){ 
			System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));//페이징처리
			
		    articleList = bdPro.getBoardArticles(pgList.get("startRow"), pgList.get("pageSize"), search, searchtext);
		  }else{ //데이터가 없다는 표시
			articleList = Collections.EMPTY_LIST; 
		  }
		
		  request.setAttribute("search",search);
		  request.setAttribute("searchtext",searchtext);
		  request.setAttribute("pgList",pgList);
		  request.setAttribute("articleList",articleList);
		  //이동하는 페이지로 전환
		  return "/list.jsp";
	}
}
