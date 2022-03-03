package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import lys.board.*;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		  request.setCharacterEncoding("utf-8");//한글처리
		  //BoardDTO=>Setter Method(5)+hidden(num)+action(pageNum)
		 //<jsp:setProperty name="article" property="*" />
		 String pageNum=request.getParameter("pageNum");
		 System.out.println("UpdateProAction에서의 pageNum=>"+pageNum);
		 //------------------------------------------------------------------------
		 //<jsp:useBean id="article"  class="lys.board.BoardDTO" />
		 BoardDTO article=new BoardDTO();
		 	 
		 article.setNum(Integer.parseInt(request.getParameter("num")));//hidden
	     article.setWriter(request.getParameter("writer"));
	     article.setEmail(request.getParameter("email"));
	     article.setSubject(request.getParameter("subject"));
	     article.setContent(request.getParameter("content"));//글내용
	     article.setPasswd(request.getParameter("passwd"));

		 //BoardDAO=>updateArticle(BoardDTO객체)호출
		 BoardDAO dbPro=new BoardDAO();
		 int check=dbPro.updateArticle(article);
		 //2개의 공유값 필요
		 request.setAttribute("pageNum", pageNum);//수정한 레코드가 있는 페이지로 이동
		 request.setAttribute("check", check);//${check} 데이터 수정 성공유무
	
		return "/updatePro.jsp";
	}
}
