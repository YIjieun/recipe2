package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lys.board.BoardDAO;
import lys.board.BoardDTO;

//UpdateFormAction와 기능이 동일
public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		 int num=Integer.parseInt(request.getParameter("num"));//삭제할 게시물번호 정수
		String pageNum=request.getParameter("pageNum");//삭제할 게시물이 있는 페이지 번호
		//null->content.jsp에서의 삭제부분의 링크부분을 확인할 것(오타유무 확인)
		System.out.println("DeleteFormAction에서의 pageNum=>"+pageNum+" ,num=>"+num);
			
		//2. 서버의 메모리에 저장->request객체, 로그인->sesseion 객체
		request.setAttribute("pageNum",pageNum );
		request.setAttribute("num", num);
		
		return "/deleteForm.jsp";// "/notice/deleteForm.jsp" =>공지사항 게시판
											 // "/reply/deldeteForm.jsp" =>답변게시판
	}
}
