package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import lys.board.*;

// /updateForm.do=>action.UpdateFormAction->/updateForm.jsp
public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		  //content.jsp->글수정->updateForm.do?num=3&pageNum=1
		    int num=Integer.parseInt(request.getParameter("num"));//게시물번호 정수
			String pageNum=request.getParameter("pageNum");//페이지 번호
			//null->content.jsp에서의 수정부분의 링크부분을 확인할 것(오타유무 확인)
			System.out.println("UpdateFormAction에서의 pageNum=>"+pageNum+" ,num=>"+num);
			
			BoardDAO dbPro=new BoardDAO();
			BoardDTO article=dbPro.updateGetArticle(num);//조회수가 증가X
			
			//2. 서버의 메모리에 저장->request객체, 로그인->sesseion 객체
			request.setAttribute("pageNum",pageNum );
			request.setAttribute("article", article);//${article.num},,,,
			
		return "/updateForm.jsp";
	}
}
