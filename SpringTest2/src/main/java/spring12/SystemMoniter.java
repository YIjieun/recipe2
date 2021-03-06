package spring12;

import javax.inject.Inject;//(어노테이션)@Inject에 관련된 클래스를 불러와야 됩니다.

//문자를 전송=>기간을 정하기
public class SystemMoniter {
	
	private long periodTime;//기간
	//has a 관계
	@Inject
	private SmsSender sender;// byType으로 객체를 자동으로 넣어준다.

	//Setter Method를 이용
	public void setPeriodTime(long periodTime) {
		this.periodTime = periodTime;//this.periodTime=10;
		System.out.println("setPeriodTime() 호출됨.");
	}
	/* 소스코드 줄이기
	public void setSender(SmsSender sender) {//callByRef
		this.sender = sender;
		System.out.println("setSender() 호출됨.");//sender.toString()
	}
	*/

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "SystemMoniter[periodTime="+periodTime
											+", sender="+sender+"]";
	}
}
