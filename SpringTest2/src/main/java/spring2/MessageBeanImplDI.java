package spring2;

public class MessageBeanImplDI implements MessageBeanDI {

	//DI를 이용해서 객체를 가져오는 방법->저장(멤버 변수)
	private String name1,name2;//생성자를 통해서 저장(constructor injection)->생성자 주입
	private String greeting;//Setter Method (Setter Injection)
	
	//----------------------has a 관계--------------------------
	private OutFile outF;//private OutFileImpl outF;(X) 클래스와 클래스 직접 연결X
	//수정=>연결된 다른 클래스내용을 가능한 덜 수정할 수 있도록 인터페이스를 대신 사용하기 때문에

	public void setOutF(OutFile outF) {
		this.outF = outF;
		System.out.println("setOutF() 호출됨=>"+outF);
	}
	//----------------------------------------------------
	//1.<constructor-arg>태그를 통해서 값을 임의로 지정해서 값을 전달
	public MessageBeanImplDI(String name1, String name2) {
		this.name1 = name1;//this.name1="대한민국"
		this.name2 = name2;//this.name2="서울"
		System.out.println("MessageBeanImplDI 생성자 호출됨!");
	}

	//2.<property>태그를 이용해서 호출->임의값을 전달
	public void setGreeting(String greeting) {
		this.greeting = greeting;//this.greeting="안녕";
		System.out.println("setGreeting() 호출됨!");
	}

	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		String message=greeting+name1+","+name2+"!";
		System.out.println("message=>"+message);
		//OutF객체를 이용해서 out(message)호출
		try {
			outF.out(message);//->파일생성
		}catch(Exception e) {
			e.printStackTrace();
		}
		//----------------------------------------
	}
}
