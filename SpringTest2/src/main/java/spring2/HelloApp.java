package spring2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class HelloApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     //1.xml파일의 위치를 불러오는 방법
		Resource resource=new ClassPathResource("/spring2/initContext.xml");
	   //2.빈즈공장을 불러와서 객체를 생성
		BeanFactory factory=new XmlBeanFactory(resource);//xml파일의 정보->메모리에 올림
	 //3.factory에서 getBean("불러올 객체를 가져올 id값을 지정")
		
		MessageBeanDI bean=(MessageBeanDI)factory.getBean("mBean");
		bean.sayHello();
	}
}
