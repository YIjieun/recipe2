package controller;

import java.io.*;//�����Է�
import javax.servlet.*;
import javax.servlet.http.*;
//�߰�
import java.util.*;//HashMap,Properties ��ü�ʿ�
import action.CommandAction;//requestPro�޼ҵ带 �������̵�


public class ControllerAction extends HttpServlet {
	
	//��ɾ�� ��ɾ� ó�� Ŭ������ ������ �����������
	//�������̽� ��ü = new �ڽ�Ŭ������();
	private Map commandMap = new HashMap();
	
	//�ʱ�ȭ �����ִ� �޼ҵ�(�����ڿ� ���� ����)
	//������ ��������ָ鼭 CommandPro.properties���� �о����
	
	public void init(ServletConfig config) 
	                    throws ServletException {
		
		//web.xml���� propertyConfig�� �ش��ϴ� init-param����
		//�ҷ�����
	 String props = config.getInitParameter("propertyConfig");
	 System.out.println("props="+props);
	 
	 //��ɾ�� ó��Ŭ������ ���������� ������ Properties��ü����
	 Properties pr = new Properties();
	 
	 FileInputStream f = null;//�����Է½�Ʈ�� ��ü
	 
	    try{
	    	//CommandPro.properties������ ������ �о��.
	    	f = new FileInputStream(props);
	    	//CommandPro.properties������ ������ Properies��ü�� ����
	    	pr.load(f);
	    	
	    }catch(IOException e){
	    	throw new ServletException(e);
	    }finally{
	      if(f!=null) try{f.close();}catch(IOException ex){}
	    }
	 
	  //�޸𸮿��� Ű���� �ش��ϴ� Ŭ������(�̸�)�� �˻�
	  Iterator keyIter = pr.keySet().iterator();//Hashtable��ü
	  
	   while(keyIter.hasNext()){//�˻��� �����Ͱ� �� �ִµ���
	 
		 String command = (String)keyIter.next();//�˻���ɾ�
	     String className = pr.getProperty(command);
	     System.out.println("init()�� command="+command);
	     System.out.println("init()�� className="+className);
	     
		 try{
			 //�ش�Ŭ������ ��ü�� ����
			 Class commandClass = Class.forName(className);
			 
			 //�ش�Ŭ������ ���� ��ü�� ����
		   Object commandInstance = commandClass.newInstance();
		   //Map��ü�� CommandMap�� ��ü�� ����
		   commandMap.put(command, commandInstance);
		   
		  }catch(ClassNotFoundException e){//Ŭ�������� Ʋ�����
			throw new ServletException(e);  
		  }catch(InstantiationException e){//��ü���� �Ұ�
				throw new ServletException(e);
		  }catch(IllegalAccessException e){//������ ����
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

	//������� ��û�� ���� �������� �̵������ִ� �޼���
	private void requestPro(HttpServletRequest request, 
			                HttpServletResponse response)
	             throws ServletException,IOException {
		
	  String view = null;//�̵��� ���������� ����
	  CommandAction com = null;//ó���ϴ� Ŭ���� ��ü�� ����
	  
	  try{
		 String command = request.getRequestURI();
		 System.out.println
		   ("request.getRequestURI()"+request.getRequestURI());
		 //���ؽ�Ʈ���� ���ؼ� ���� ��ɾ��� ���ؿ���
		 //���ؽ�Ʈ�� �������� ���ؿ��� �ڵ�(/)
		 if(command.indexOf(request.getContextPath())==0){
		    command = command.substring
		              (request.getContextPath().length());
		    System.out.println
		    ("request.getContextPath()"+request.getContextPath());
		    System.out.println("requestPro()�� command="+command);
		 }
		 //��ɾ ���� ó��Ŭ���� ��ü�� ����.
		 com = (CommandAction)commandMap.get(command);
		 view = com.requestPro(request, response);//list.jsp
		 System.out.println("���� ��ü com="+com);
		 System.out.println("�̵��ϴ� ������view="+view);
	  }catch(Throwable e){
		  throw new ServletException(e);
	  }
	  //�̵��� �������� ����
	  //dispatcher��ü->�� �������� ��� �� ������������
	  RequestDispatcher dispatcher = 
		  request.getRequestDispatcher(view);
	  System.out.println("dispatcher��ü="+dispatcher);
	  dispatcher.forward(request, response);//forward�� �̵�
	}
}
