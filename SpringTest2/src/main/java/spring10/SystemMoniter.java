package spring10;

//has a 관계
public class SystemMoniter {
	
	//(1)byType=>@Autowired
	/*
	private PhoneCall call;//byType=>PhonCall과 같은 자료형의 객체를 찾아와서
										//			멤버변수에 자동으로 저장
										//	<property>태그를 사용하지 않고도 호출이 가능
	//Setter Method를 이용
	public void setCall(PhoneCall call) {//<property name="call"></property>
		this.call = call;
		System.out.println("setCall()호출(call)=>"+call);
	}*/
	
	//(2)byName->@Resource와 같은 개념=>Type으로 객체를 찾아서 넣어주는 것이 아니라
	//														객체이름으로 찾아서 자동으로 객체를 넣어주는 개념
	private PhoneCall phonecall;//type과 이름을 같게 설정해준다.(보통 디폴트로 멤버변수이름을)
	
	public void setPhonecall(PhoneCall phonecall) {
		this.phonecall = phonecall;
		System.out.println("setPhonecall()호출(phonecall)=>"+phonecall);
	}

	//추가
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[PhoneCall 객체]=>"+phonecall;
	}
}
