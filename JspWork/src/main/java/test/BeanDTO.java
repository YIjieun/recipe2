package test;//1.package 최상위패키지명.하위패키지명,,,;->빈즈구분

//자바빈즈=>웹상에서 불러다 사용하는 클래스
//2.public class로 작성
public class BeanDTO {//XXXBean->XXXDTO,XXXDAO,XXXVO
	
	//3.private 멤버변수;
	private String name;//<input type="text" name="name">와 반드시 같게 설정해야한다.
	private String addr;//<input type="text" name="addr">
	
	public String getName() {//4.public을 이용해서 메서드 작성
		return name;//return this.name; this생략됨.
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("setName() 호출됨");
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
		System.out.println("setAddr() 호출됨");
	}
}
