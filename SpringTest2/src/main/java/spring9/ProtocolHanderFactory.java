package spring9;

import java.util.Map;

class RestHandler{
	//new RestHanlder()->public RestHandler(){} 기본생성자는 만들어서 호출
}
class SoapHandler{
	//SoapHandler.java
}

public class ProtocolHanderFactory {
	//Map객체->HashMap,Hashtable
	private Map<String,Object> map;

	public void setMap(Map<String, Object> map) {
		this.map = map;
		System.out.println("setMap() 호출됨=>"+map);
	}
}
