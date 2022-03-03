package spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//스프링 방식=>객체를 생성, 메서드 호출->처리방식을 xml파일로 설정
//스프링->자바 자체에서 제공된 패키지X =>외부에서 다운로드 받아야 된다.

public class HelloApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.xml파일의 위치를 불러오는 방법->절대경로(FileSystemResource)
		/*
		Resource resource = new FileSystemResource
				("C:\\webtest\\4.jsp\\2.back-end\\sou2\\SpringTest\\src\\spring\\applicationContext.xml");
		*/
		Resource resource=new ClassPathResource("/spring/applicationContext.xml");
		//2. 빈즈공장을 불러와서 객체를 얻어오기
		BeanFactory factory = new XmlBeanFactory(resource);//xml파일의 정보-> 메모리에 저장
		//3.factory에서 getBean("불러올 객체를 가져올 id값");
		//Message1 me = new Message1();
		//Message1 me = (Message1)factory.getBean("test");//형변환
		//Message2 me = (Message2)factory.getBean("test2");
		MessageInter me = (MessageInter)factory.getBean("test");
		me.sayHello("테스트");
	}
}
