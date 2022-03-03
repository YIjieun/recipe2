package studentdb;

//String,int->Integer(래퍼클래스)=>DTO or VO이름으로 작성이 가능
public class Student {

	//private int id;
	private Integer id;//학생번호
	private Integer age;//나이
	private String name;//이름
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
