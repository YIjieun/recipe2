package dog;
public class DogBean  //뒤에 Bean로 이름을 붙이는 관례가 있다.
{
	private String name;
	private int count;//계산을 위해서 필요
	private double price;

	//많이 있을 수 있다.

    //디폴트 생성자 정의

	public DogBean() {}

	public DogBean(String name,int count,double price)
	{
      this.name=name;
	  this.count=count;
	  this.price=price;
	}

    //getter,setter 메소드 정의

	public void setName(String name)
	{
       this.name=name;
	}

	public void setCount(int count)
	{
       this.count=count;
	}

	public void setPrice(double  price)
	{
       this.price=price;
	}

	public String getName()
	{
       return name;
	}

	public int getCount()
	{
       return count;
	}

	public double getPrice()
	{
       return price;
	}
}
