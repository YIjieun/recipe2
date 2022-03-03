package board1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;

public class WriteFormAction implements CommandAction {

	//@Override=>requestPro�޼��尡 �������̵� �� �޼��� ���� ����
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
	    //list.jsp->�űԱ�, content.jsp->�亯��
	    int num=0, ref=1, re_step=0, re_level=0;

	    //�亯���� ���� �Ķ���Ͱ��� �޾ƿ�(content.jsp)
	   try{
	        //content.jsp���� ���� �Ѿ�Դٸ�
	      if(request.getParameter("num")!=null){
	        num = Integer.parseInt(request.getParameter("num"));
	        ref = Integer.parseInt(request.getParameter("ref"));
	        re_step = Integer.parseInt(request.getParameter("re_step"));
	        re_level = Integer.parseInt(request.getParameter("re_level"));
	         }
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }

	    //�����ǰ��� request��ü�� ����->jsp���� �ҷ��� ��밡��
	    request.setAttribute("num", new Integer(num));
	    request.setAttribute("ref", new Integer(ref));
	    request.setAttribute("re_step", new Integer(re_step));
	    request.setAttribute("re_level", new Integer(re_level));
	    
		return "/writeForm.jsp";
	}
}
