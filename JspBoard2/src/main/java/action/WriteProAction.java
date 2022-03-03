package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import lys.board.*;//BoardDTO,BoardDAO
import java.sql.Timestamp;//필드의 날짜자료형때문에 필요

//글쓰기->writePro.do
public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//1.writePro.jsp의 자바처리구문 대신
		
	     request.setCharacterEncoding("utf-8");//한글처리
	     //   <jsp:useBean id="article" class="yje.board.BoardDTO" />
	     BoardDTO article=new BoardDTO();
	 
	     //   <jsp:setProperty property="*" name="article"/>
	     article.setNum(Integer.parseInt(request.getParameter("num")));//hidden
	     article.setWriter(request.getParameter("writer"));
	     article.setEmail(request.getParameter("email"));
	     article.setSubject(request.getParameter("subject"));
	     article.setPasswd(request.getParameter("passwd"));
	     System.out.println("WriteProAction에서의 re_level값");
	    System.out.println("request.getParameter(\"re_level\")=>"+request.getParameter("re_level"));
	     article.setReg_date(new Timestamp(System.currentTimeMillis()));//오늘 날짜 수동저장=>~.getReg_date() ->now()
	     article.setRef(Integer.parseInt(request.getParameter("ref")));//hidden
	     article.setRe_step(Integer.parseInt(request.getParameter("re_step")));//hidden
	     article.setRe_level(Integer.parseInt(request.getParameter("re_level")));//hidden
	     //조회수->자동으로 0을 default
	     article.setContent(request.getParameter("content"));//글내용
	     article.setIp(request.getRemoteAddr());//원격 ip주소 저장
	     
	     BoardDAO dbPro=new BoardDAO();
	     dbPro.insertArticle(article);
	     //list.jsp로 페이지 이동
		//3.공유->페이지 이동
		return "/writePro.jsp";// /list.do로 처리->/list.jsp
	}

}
