package action;

import javax.servlet.http.*;
 //HttpServletRequest,HttpServletResponse

public interface CommandAction {
   //������ �߻�޼ҵ带 ����
	public String requestPro(HttpServletRequest request,
			                 HttpServletResponse response) 
	                          throws Throwable;
}
