package anno5;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//형식)@Component("부여할 id이름지정")=>지정하지 않으면 무조건 기본 클래스이름을 통해서 가져온다.
@Component("home") //getBean("home")
public class HomeController2 {

	@Inject
	private Camera camera;

	@Autowired
	private Camera2 camera2;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "HomeController2[camera="+camera+" ,camera2="+camera2+"]";
	}
}
