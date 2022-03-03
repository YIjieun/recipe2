package controller;

import java.io.*;//파일입력
import javax.servlet.*;
import javax.servlet.http.*;
//추가
import java.util.*;//HashMap,Properties 객체필요
import action.CommandAction;//requestPro메소드를 오버라이딩


public class ControllerAction extends HttpServlet {
	
	//명령어와 명령어 처리 클래스를 저장할 멤버변수선언
	//인터페이스 객체 = new 자식클래스명();
	private Map commandMap = new HashMap();
	
	//초기화 시켜주는 메소드(생성자와 같은 역할)
	//서블릿을 실행시켜주면서 CommandPro.properties파일 읽어오기
	
	public void init(ServletConfig config) 
	                    throws ServletException {
		
		//web.xml에서 propertyConfig에 해당하는 init-param값을
		//불러오기
	 String props = config.getInitParameter("propertyConfig");
	 System.out.println("props="+props);
	 
	 //명령어와 처리클래스의 매핑정보를 저장할 Properties객체생성
	 Properties pr = new Properties();
	 
	 FileInputStream f = null;//파일입력스트림 객체
	 
	    try{
	    	//CommandPro.properties파일의 내용을 읽어옴.
	    	f = new FileInputStream(props);
	    	//CommandPro.properties파일의 정보를 Properies객체에 저장
	    	pr.load(f);
	    	
	    }catch(IOException e){
	    	throw new ServletException(e);
	    }finally{
	      if(f!=null) try{f.close();}catch(IOException ex){}
	    }
	 
	  //메모리에서 키값에 해당하는 클래스명(이름)을 검색
	  Iterator keyIter = pr.keySet().iterator();//Hashtable객체
	  
	   while(keyIter.hasNext()){//검색할 데이터가 들어가 있는동안
	 
		 String command = (String)keyIter.next();//검색명령어
	     String className = pr.getProperty(command);
	     System.out.println("init()의 command="+command);
	     System.out.println("init()의 className="+className);
	     
		 try{
			 //해당클래스의 객체를 생성
			 Class commandClass = Class.forName(className);
			 
			 //해당클래스에 대한 객체를 생성
		   Object commandInstance = commandClass.newInstance();
		   //Map객체인 CommandMap에 객체를 저장
		   commandMap.put(command, commandInstance);
		   
		  }catch(ClassNotFoundException e){//클래스명이 틀린경우
			throw new ServletException(e);  
		  }catch(InstantiationException e){//객체생성 불가
				throw new ServletException(e);
		  }catch(IllegalAccessException e){//논리적인 예외
			throw new ServletException(e);
		  }
	   }
   }//init
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   requestPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    requestPro(request,response);
	}

	//사용자의 요청에 따라서 페이지를 이동시켜주는 메서드
	private void requestPro(HttpServletRequest request, 
			                HttpServletResponse response)
	             throws ServletException,IOException {
		
	  String view = null;//이동할 페이지명을 저장
	  CommandAction com = null;//처리하는 클래스 객체를 선언
	  
	  try{
		 String command = request.getRequestURI();
		 System.out.println
		   ("request.getRequestURI()"+request.getRequestURI());
		 //컨텍스트명을 통해서 뒤의 명령어을 구해오기
		 //컨텍스트의 시작점을 구해오는 코딩(/)
		 if(command.indexOf(request.getContextPath())==0){
		    command = command.substring
		              (request.getContextPath().length());
		    System.out.println
		    ("request.getContextPath()"+request.getContextPath());
		    System.out.println("requestPro()의 command="+command);
		 }
		 //명령어에 대한 처리클래스 객체를 얻어옴.
		 com = (CommandAction)commandMap.get(command);
		 view = com.requestPro(request, response);//list.jsp
		 System.out.println("얻어온 객체 com="+com);
		 System.out.println("이동하는 페이지view="+view);
	  }catch(Throwable e){
		  throw new ServletException(e);
	  }
	  //이동할 페이지를 지정
	  //dispatcher객체->그 페이지의 경로 및 파일정보저장
	  RequestDispatcher dispatcher = 
		  request.getRequestDispatcher(view);
	  System.out.println("dispatcher객체="+dispatcher);
	  dispatcher.forward(request, response);//forward로 이동
	}
}
