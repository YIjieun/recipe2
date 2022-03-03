package action;

import javax.servlet.http.*;
 //HttpServletRequest,HttpServletResponse

public interface CommandAction {
   //공통의 추상메소드를 선언
	public String requestPro(HttpServletRequest request,
			                 HttpServletResponse response) 
	                          throws Throwable;
}
