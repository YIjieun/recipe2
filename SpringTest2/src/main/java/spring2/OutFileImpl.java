package spring2;

import java.io.FileWriter;
import java.io.IOException;

//파일을 생성->파일내부에 문자열을 출력시켜주는 프로그램
public class OutFileImpl implements OutFile {

	private String filePath;//경로명 및 만들어질 파일명 저장
	
	//setFilePath()호출하기 위해서 <property>태그를 줘야 된다.
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		 System.out.println("setFilePath() 호출됨!=>"+filePath);
	}

	//MessageBeanImplDI->sayHello(message)=>out(message)=>파일에 내영을 넣어주는 기능
	@Override
	public void out(String message) throws IOException {
		// TODO Auto-generated method stub
		//한글데이터를 저장=> FileOutputStream(영문),FileWriter(한글전용)
		FileWriter fw=new FileWriter(filePath);//c:/webtest/good.txt
		fw.write(message);
		fw.close();//종료
	}
}
