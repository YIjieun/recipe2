package calc;//1. package 최상위패키지명...;//기능별로 분리

//2. public class로 작성->외부에서 (calResult.jsp에서 메서드를 호출)
public class CalcBean {
    //3. 입력받아서 처리하는 것이므로 멤버변수를 private로 지정
	//문자열이 기본으로 int로 계산해줘야함.("2"->2)
    private int num1;//input 태그와 name과 이름을 같게 해줘야 액션태그 사용가능
    private int num2;//input 태그와 name과 이름을 같게  해줘야 액션태그 사용가능
   //input 태그와 name과 이름을 같게  해줘야 액션태그 사용가능
    private String operator="";//연산자->null(객체의 초기값을 반드시 줘야한다.)
    //추가
    private int result;//계산목적
    
    //Setter Getter
    public int getNum1() {
		return num1;
	}
	public int getNum2() {
		return num2;
	}
	public String getOperator() {
		return operator;
	}
	public void setResult(int result) {
		this.result = result;
	}
	  public void setNum1(int num1) {
	        this.num1 = num1;
	        System.out.println("setNum1() 호출됨");
	    }
	public void setNum2(int num2) {
        this.num2 = num2;
        System.out.println("setNum2() 호출됨");
    }
    public void setOperator(String operator) {
        this.operator = operator;
        System.out.println("setOperator() 호출됨");
    }
    public int getResult() {
		return result;
	}
	//웹상에서 호출해서 실행되는 메서드=>비지니스 로직 메서드
	public void calculate() throws Exception{//멤버변수로 사용할 값이 있으면 매개변수X, 멤벼변수가 X면 매개변수O
    	  if(operator.equals("+")){
    		  result= num1+num2;
          }else if(operator.equals("-")){
        	  result= num1-num2;
          }else if(operator.equals("*")){
        	  result= num1*num2;
          }else if(operator.equals("/")){
        	  result= num1/num2;            
          }
    }
}