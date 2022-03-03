

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Notice
 */
@WebServlet("/Notice")
public class Notice extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");//html+한글처리
		PrintWriter out=response.getWriter(); //ctrl+shift+o(저동 import)	
		out.println("<html><head></head>");
		out.println("<body>");
		//<%-------------------------------------
		out.println("<h2>오늘의 공지사항</h2>");
		//notice->202217.txt
		String fileName=""; //불러올 파일명 저장
		Calendar cal=Calendar.getInstance();//Date d= new Data();
		fileName+=cal.get(Calendar.YEAR);//형식)날짜객체명.get(정적상수명);->""+"2022"
		fileName+=cal.get(Calendar.MONTH)+1;//0~11월+1을 줘야 정확한 월이 표시되다.(1월이 0 부터 시작)
		fileName+=cal.get(Calendar.DATE);//윤년도 자동계산->"202217"
		fileName+=".txt";//"202217.txt"
		//경로명+파일명=>절대경로(폴더로 찾아서 경로복사해서 붙임. 맨끝에 끝에/ 붙여야함)
		String realPath="C:/webtest/4.jsp/2.back-end/sou2/ServletTest/src/main/webapp/notice/"+fileName;
		System.out.println("realPath=>"+realPath);
		try {
			//FileInputStream(영문) or FileReader(한글)
			BufferedReader br=new BufferedReader(new FileReader(realPath));
			String line="";// 한줄씩 읽어들여서 저장할 변수
			//계속 불러올 수 있는 상태라면
			while((line=br.readLine())!=null) {
				out.println(line+"<br>");//접속자 (클라이언트)컴퓨터의 브라우저로 전송				
			}
			br.close();
		}catch(IOException e) {
			System.out.println("불러올 파일의 경로와 파일명을 다시 확인해주세요."+e);
		}catch(Exception e) {
			System.out.println("오늘은 공지사항이 없습니다.");
		}
		out.println("<p align=center>");
		out.println("<hr>");
		out.println("<input type='submit' value='창닫기' onclick='window.close()'>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
