package anno4;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;//@Resource와 연관이 있는 클래스

public class HomeController2 {

	private Camera camera;

	@Resource(name="camera5")
	public void setCamera(Camera camera) {//id가 camera5인 클래스 객체를 찾아서 저장
		this.camera = camera;
		System.out.println("setCamera()호출됨");
	}
	//1. 빈즈객체 생성전에 초기화 작업을 하고자 할때 (=생성자)
	//물건사기전에 돈을 지불
	@PostConstruct
	public void init() {
		System.out.println("빈즈객체 생성전에 초기화 작업을 하고자 할때 (=생성자)");
	}

	//객체가 생성된 후에 작업->메모리 작업
	@PreDestroy
	public void close() {
		System.out.println("빈즈객체 생성후에 메모리해재(close())호출됨.)");
	}
	/*
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "HomeController[camera2="+camera2+" ,camera3="
											+camera3+" , camera4="+camera4+"]";
	}
*/
}
