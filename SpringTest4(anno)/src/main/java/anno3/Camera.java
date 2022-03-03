package anno3;

import org.springframework.beans.factory.annotation.Required;

public class Camera extends Object{
	
	private int number;//카메라수 0

	@Required
	public void setNumber(int number) {
		this.number = number;
		System.out.println("setNumber()호출됨!");
	}
	//메소드이름 위에 기술=>메서드명이 오버라이딩이 된 메서드인지 확인해주는 기능을 가진 어노테이션
	@Override
	public String toString() {//Object에서 상속받은 것
		// TODO Auto-generated method stub
		return "Camera[number="+number+"]";
	}
}
