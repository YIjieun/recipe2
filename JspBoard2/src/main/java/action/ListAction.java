package action;

//추가
import lys.board.*;//BoardDAO
import java.text.SimpleDateFormat;
import java.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//요청명령어에 해당되는 명령어처리클래스=액션클래스=>스프링(컨트롤러 클래스)
public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//1. /list.jsp에서 처리했던 자바코드를 =>결과를 request에 저장하고 이동->jsp로 이동	
	  String pageNum=request.getParameter("pageNum");
	  //추가(검색분야,검색어)
	  String search=request.getParameter("search");//검색분야
	  String searchtext=request.getParameter("searchtext");//검색어
	  System.out.println("ListAction에서의 매개변수 확인");
	  System.out.println("pageNum=>"+pageNum+" ,search=>"+search
			  						+" ,searchtext=>"+searchtext);
	  
	  int count=0;//총레코드수
	  List<BoardDTO> articleList=null;//화면에 출력할 레코드를 저장할 변수
	  
	  BoardDAO dbPro=new BoardDAO();
	  count=dbPro.getArticleSearchCount(search,searchtext);
	  System.out.println("현재 레코드수(count)=>"+count);
	  //1.화면에 출력할 페이지번호, 2.출력할 레코드갯수
	  Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
	  
	  if(count > 0){
		  System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
		  articleList=dbPro.getBoardArticles(pgList.get("startRow"),//첫번째레코드번호
				  											pgList.get("pageSize"),//불러올갯수
				  											search,searchtext);//검색분야,검색어
		  System.out.println("ListAction의 articleList=>"+articleList);//null->메서드 내용확인
	  }else {//count==0
		  articleList=Collections.EMPTY_LIST;//비어있는 List객체-> 정적상수		  
	  }

	  //2. 처리한 결과를 공유(서버메모리에 저장)=>이도할 페이지에 공유해서 사용(request)
	  request.setAttribute("search", search);//${search}
	  request.setAttribute("searchtext", searchtext);//${searchtext}
	  request.setAttribute("pgList", pgList);//10개의 페이징처리 정보
	  request.setAttribute("articleList", articleList);//${articleList}
	  
	  //3. 공유해서 이동할 수 있도록 페이지를 지정
		return "/list.jsp";//컨트롤러가 이동시키면서 공유시켜준다.->view
	}

}
