package anno2;

import javax.inject.Inject;

//문자를 전송=>기간을 정하기
public class SystemMoniter {
	
	private long periodTime;//기간
	//has a 관계

	/*2. 멤버변수에 선언
	//@Autowired//무조건 가져오라->가져올 빈객체가 없으면 에러유발
	//@Autowired(required=false)// (required=false)빈객체가 있으면 넣어주고 없으면 안넣어줘도 상관없음.
	private SmsSender sender;// byType으로 객체를 자동으로 넣어준다.
	 */
	
	@Inject
	private SmsSender sender;// byType으로 객체를 자동으로 넣어준다.
	
	//Setter Method를 이용
	public void setPeriodTime(long periodTime) {
		this.periodTime = periodTime;//this.periodTime=10;
		System.out.println("setPeriodTime() 호출됨.");
	}
	
	
	/*1.메서드에 선언
	@Required //호출을 안하면 에러나오게 설정, 디버깅용
	@Autowired //byType-자동으로 해당 티입의 객체를 찾아서 넣어주는 어노테이션(=주입(inject)) 
	//												멤버변수 or 메서드에 붙인다.(동시에 X)
	public void setSender(SmsSender sender) {//callByRef
		this.sender = sender;
		System.out.println("setSender() 호출됨.=>"+sender);//sender.toString()
	}
	*/
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "SystemMoniter[periodTime="+periodTime
											+", sender="+sender+"]";
	}
}
