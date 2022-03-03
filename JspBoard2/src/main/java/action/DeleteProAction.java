package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import lys.board.*;

//UpdateProAcrion을 save as DeleteProAction
public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
			  
		 //<jsp:setProperty name="article" property="*" />
		 String pageNum=request.getParameter("pageNum");//입력X
		 String passwd=request.getParameter("passwd");//입력O
		 int num=Integer.parseInt(request.getParameter("num"));//입력X
		 System.out.println("DeleteProAction에서의 pageNum=>"+pageNum
				 						+" ,num=>"+num+" ,passwd=>"+passwd);
		 //------------------------------------------------------------------------
		 
		 //<jsp:useBean id="article"  class="lys.board.BoardDTO" />
		 BoardDTO article=new BoardDTO();

		 //BoardDAO=>updateArticle(BoardDTO객체)호출
		 BoardDAO dbPro=new BoardDAO();
		 int check=dbPro.deleteArticle(num,passwd);
		 //2개의 공유값 필요
		 request.setAttribute("pageNum", pageNum);//삭제한 레코드가 있는 페이지로 이동
		 request.setAttribute("check", check);//${check} 데이터 수정 성공유무
	
		return "/deletePro.jsp";//updatePro.jsp와 소스코드가 동일하다.
	}
}
