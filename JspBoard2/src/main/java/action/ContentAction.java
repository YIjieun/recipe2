package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import lys.board.*;


// /content.do?num=3&pageNum=1
public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		//1.writePro.jsp의 자바처리구문 대신
	
		int num=Integer.parseInt(request.getParameter("num"));//게시물번호 정수
		String pageNum=request.getParameter("pageNum");//페이지 번호
		System.out.println("ContentAction의 pageNum=>"+pageNum+" ,num=>"+num);
		
		BoardDAO dbPro=new BoardDAO();
		BoardDTO article=dbPro.getArticle(num);//조회수 
		//링크문자열의 길이를 줄이기위해서
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		System.out.println("content.do의 매개변수 확인용");
		System.out.println("ref=>"+ref+",re_step=>"+re_step+",re_level=>"+re_level);

		//2.실행결과를 서버의 메모리에 저장->request에 저장->jsp ${키명}으로 불러올 수 있도록 공유
		request.setAttribute("num", num);//${num}//article에 속해있음
		request.setAttribute("pageNum", pageNum);//${pageNum}
		request.setAttribute("article", article);//${article.ref},${article.re_step} ,${article.re_level} 
		//ref,re_step,re_level은 article에 속해 있음.
		
		//3.공유->페이지 이동
		return "/content.jsp";
	}
}
