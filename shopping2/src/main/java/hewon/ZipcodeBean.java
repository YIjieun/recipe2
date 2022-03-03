package hewon;

public class ZipcodeBean {

	private String zipcode;	//�����ȣ�� ������ ����
	private String area1; //���� �Ǵ� ���� ������ ����
	private String area2; //�� �Ǵ� �ҵ��ø� ������ ����
	private String area3; //�� �Ǵ� ��,���� ������ ����
	private String area4; //�ּ��� ������ ������ ����

	public void setZipcode(String zipcode){
		this.zipcode=zipcode; 
	}

	public void setArea1(String area1){
		this.area1=area1; 
	}

	public void setArea2(String area2){
		this.area2=area2; 
	}

	public void setArea3(String area3){
		this.area3=area3; 
	}

	public void setArea4(String area4){
		this.area4=area4; 
	}


    public String getZipcode(){
 		return zipcode; 
 	}

    public String getArea1(){
 		return area1; 
 	}
	 public String getArea2(){
 		return area2; 
 	}
	 public String getArea3(){
 		return area3; 
 	}
	 public String getArea4(){
 		return area4; 
 	}
}