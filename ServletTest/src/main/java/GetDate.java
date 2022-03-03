

import java.io.*; //입출력
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class GetDate
 */
//@WebServlet("/GetDate")
public class GetDate extends HttpServlet {

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("웹상에서 get방식으로 접속할때마다 자동으로 호출되는 메서드");
		response.setContentType("text/html;charset=utf-8");//html+한글처리
		PrintWriter out=response.getWriter(); //ctrl+shift+o(저동 import)	
		out.println("<html><head></head>");
		out.println("<body>");
		//<%-------------------------------------
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String addr=request.getParameter("addr");
		out.println("name=>"+name+" ,addr=>"+addr);
		//%>-------------------------------------
		out.println("</body></html>");
	}
}
