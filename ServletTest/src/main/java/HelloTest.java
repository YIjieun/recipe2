/*
 * 서블릿=> (현재로는 톰켓)서버에서 실행되어서 실행결과(text/html)을 브라우저로 재전송해주는 프로그램
 * 
 * 서블릿을 만드는 조건(setvlet-api.jar)->classpath에 외부 파일로 추가해야한다.
 * 
 * 1. import javax.servlet.*;//서블릿 클래스 ->*파일만 불러온다.
 * 		import javax.servlet.http.*;//웹상에서의 접속에 관련된 정보 클래스
 * 
 * 2. 반드시 public class로 작성해야 한다.->외부에서 접근이 가능하게 만들기 위해서
 * 3. 반드시 HttpServlet클래스를 상속받아야 한다.=>상속=>doGet(),doPost() 메서드 때문에
 * 
 * 		get방식(사이트접속)->doGet() 자동으로 호출->객체명.메서드()(X) 수동
 * 		post방식으로 요청(ex 회원가입, 로그인)=>doPost() 자동으로 호출
 */

import java.io.IOException; //입출력 예오처리
import java.io.PrintWriter;

import javax.servlet.ServletConfig; // 서블릿의 초기환경설정 할때 필요
import javax.servlet.ServletException; //서블릿의 오류발생시 처리해주는 예외처리 클래스
import javax.servlet.annotation.WebServlet; //웹상에서 서블릿을 실행하기위해 어떻게 접근?
import javax.servlet.http.HttpServlet; // 반드시 상속받아야할 서블릿 클래스
import javax.servlet.http.HttpServletRequest; //요청을 받아서 처리해주는 객체(request)
import javax.servlet.http.HttpServletResponse; //응답을 받아서 처리해주는 객체(response)

/**
 * Servlet implementation class HelloTest
 * 어노테이션->환경설정을 편하게 할 목적으로 만들어진 키워드 
 * @키워드명("/경로를 지정")->가상경로도 가능(보완)
 */

//@WebServlet("/abc/tes/imsi/HelloTest")//경로를 수동으로 고칠수 있다.
public class HelloTest extends HttpServlet {

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("서블릿이 실행시 제일먼저 호출되는 메서드(init)");
		System.out.println("생성자와 같은역할(서블릿의 초기값을 설정)");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("소스코드를 변경해서 다시 새로고침해서 확인(reload)");
		System.out.println("서블릿이 종료할때 (메모리 해제할때 사용)");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *  요청을 받아서 처리해주는 메서드(request(요청),response(재전송)객체가 필요하다.)->Get방식
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("웹상에서 get방식으로 접속할때마다 자동으로 호출되는 메서드");
		//1. 어떤형태의 문서를 보내줄지 결정
		response.setContentType("text/html;charset=utf-8");//html+한글처리
		//2. PrintWriter out=new PrintWriter();
		PrintWriter out=response.getWriter(); //ctrl+shift+o(저동 import)
		//3. html문서로 만들어준다.
		out.println("<html><head></head>");
		out.println("<body>");
		out.println("<h2>Hello Servlet 테스트</h2>");
		//추가
		out.println("<table border=1>");
		 for(int i=2;i<=9;i++) {//행(단)
			 out.println("<tr>");
			 for(int j=1;j<10;j++) {
				 out.println("<td>");
				 out.println(i+"*"+j+"="+(i*j));
				 out.println("</td>");
			 }
			 out.println("</tr>");
		 }
		out.println("</table>");
		out.println("</body></html>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
