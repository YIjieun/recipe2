package test;

import java.io.IOException;
import javax.servlet.*;//서블릿
import javax.servlet.annotation.WebServlet;//서블릿의 요청에 대한 경로를 지정
import javax.servlet.http.*;//요청(HttpServlet)을 받아서 처리해주는 기능(ex 상속(사장))


/**
 * Servlet implementation class SimpleController
 */
@WebServlet("/SimpleController")
public class SimpleController extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRequest(request, response);
	}
	
	//Get or Post와 상관없이 동일한 처리를 하고 싶다.(jsp (X) ~.do(요청명령어))
	private void processRequest(HttpServletRequest request, 
			                                   HttpServletResponse response) 
			                        throws ServletException, IOException {
	   //1.요청명령어를 입력을 받아서 분석(매개변수로 전달)
		String type=request.getParameter("type");
		System.out.println("type=>"+type);
		//greeting->안녕하세요,  date->오늘 날짜출력, X ->invalid Type(에러메세지 출력)
	 //2.요청결과에 따른 요청값을 저장할 변수 선언
		Object resultObject=null;//String or java.util.Date 둘다 저장이 가능한 자료형
		//3.요청명령어 분리
		if(type==null || type.contentEquals("greeting")) {
			resultObject="안녕하세요!!!";
		}else if(type.contentEquals("date")) {
			resultObject=new java.util.Date();
		}else {//전혀 모르는 매개변수를 전달
			resultObject="Invalid Type!";
		}
		//4.처리결과->어떻게 jsp에 전송해줄까?(request객체를 이용해서 서버에 등록 O)
		request.setAttribute("result", resultObject);//forward액션태그가 필요
		//5.forward액션태그를 사용X->forward시켜주는 객체가 필요->RequestDispatcher클래스
		//getRequestDispatcher("/이동시킬 페이지명")=>simple.jsp의 위치와 정보를 얻어다주는객체반환
		RequestDispatcher dispatcher=request.getRequestDispatcher("/simple.jsp");
		//6.forward메서드->데이터를 공유시키면서 페이지를 이동시키는 메서드가 필요
		dispatcher.forward(request, response);
	}
}
