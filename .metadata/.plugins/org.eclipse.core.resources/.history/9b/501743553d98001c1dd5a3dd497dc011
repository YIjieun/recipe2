package spring6;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	//1.메모리 관리->xml파일이 여러개 존재->배열로 관리->파일명 부여
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] configLocation=new String[] {"applicationContext.xml"};
		
		//2.xml파일을 메모리에 올려줄 수 있는 클래스를 통해서 객체를 생성
		AbstractApplicationContext context=
											new ClassPathXmlApplicationContext(configLocation);
		//3.자바프로그램이 종료=>자동적으로 context객체도 같이 종료될 수 있도록 처리
		context.registerShutdownHook();
		//4.xml에서 만들어진 객체를 가져와서 처리
		/* 2.5 방식의 코딩(객체를 가져오고나서 원하는 자료형으로 형변환시켜서 사용)
		 * =>3.x 버전대로 코딩(4.x)(처음부터 형변환시켜서 객체를 가져오는 경우)
		 * 형식)getBean("의존성객체(꺼내올객체)를 얻어올 id",형변환을 할 클래스명.class)
		 */
		//SystemMoniter moniter=(SystemMoniter)context.getBean("moniter3");//2.5버전
		PerformanceMoniter moniter=context.getBean("performanceMoniter",PerformanceMoniter.class);//3.x버전
		PerformanceMoniter moniter2=context.getBean("performanceMoniter",PerformanceMoniter.class);//3.x버전
		PerformanceMoniter moniter3=context.getBean("performanceMoniter",PerformanceMoniter.class);//3.x버전
		
		//요청할때 한개의 객체가 만들어줘서 여러사람이 공유해서 사용을 하고 있다.(default)
		//회사에서 부서별로 프린터한대에 공유해서 사용
		System.out.println("moniter=>"+moniter);
		System.out.println("moniter2=>"+moniter2);
		System.out.println("moniter3=>"+moniter3);
		//5.context도 종료
		context.close();//메모리에 올려놓은 모든 Beans의 객체들을 모두 메모리에서 해제하라.
	}
}
