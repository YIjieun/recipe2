package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;

public class WriteFormAction implements CommandAction {

	//@Override=>requestPro메서드가 오버라이딩 한 메서드 임을 증명
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
	    //list.jsp->신규글, content.jsp->답변글
	    int num=0, ref=1, re_step=0, re_level=0;

	    //답변글을 위한 파라미터값을 받아옴(content.jsp)
	   try{
	        //content.jsp에서 값이 넘어왔다면
	      if(request.getParameter("num")!=null){
	        num = Integer.parseInt(request.getParameter("num"));
	        ref = Integer.parseInt(request.getParameter("ref"));
	        re_step = Integer.parseInt(request.getParameter("re_step"));
	        re_level = Integer.parseInt(request.getParameter("re_level"));
	         }
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }

	    //변수의값을 request객체에 저장->jsp에서 불러다 사용가능
	    request.setAttribute("num", new Integer(num));
	    request.setAttribute("ref", new Integer(ref));
	    request.setAttribute("re_step", new Integer(re_step));
	    request.setAttribute("re_level", new Integer(re_level));
	    
		return "/writeForm.jsp";
	}
}
