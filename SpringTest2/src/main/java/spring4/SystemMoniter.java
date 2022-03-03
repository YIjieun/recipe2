package spring4;

//문자를 전송=>기간을 정하기
public class SystemMoniter {
	
	private long periodTime;//기간
	//has a 관계
	private SmsSender sender;

	//생성자를 이용->c네임스페이스를 이용해서 설정이 가능
	public SystemMoniter(long periodTime, SmsSender sender) {
		this.periodTime = periodTime;
		this.sender = sender;
		System.out.println("SystemMoniter() 생성자 호출됨!");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "SystemMoniter[periodTime="+periodTime
											+", sender="+sender+"]";
	}
}
