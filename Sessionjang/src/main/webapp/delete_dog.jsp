<%@ page contentType="text/html;charset=UTF-8"
        import ="java.util.*,dog.DogBean"
%>

<% 
//먼저 dog_cart.jsp의 삭제에서 삭제시킬 값을 받을 파라미터 설정
//
String dogName=request.getParameter("dogName");
if(dogName==null)//장바구니 비우기 경우
{
//장바구니 비우기(세션을 지움)
session.invalidate();//세션을 모두 비우는 메소드-->지울지 문의하는 코드 추가해 볼것.
response.sendRedirect("kim_shop.html");
}
else //특정 아이템 삭제인 경우
{
  Vector dogVector=(Vector)session.getAttribute("dogList");

//삭제시킬 항목을 벡터에서 찾기...
for(int i=0;i<dogVector.size();i++)
{
  DogBean dog=(DogBean)dogVector.elementAt(i);
   if(dogName.equals(dog.getName()))//강아지의 이름이 같을 경우
	{    
      dogVector.removeElementAt(i);//기존의 것은 제거시킨다.
  break;//첫번째에서 선택한 항목이 나올 경우가 있기에...break...시켜서 빠져나온다.
	}
  }
   session.setAttribute("dogList",dogVector);
   response.sendRedirect("dog_cart.jsp");//나중에 바꿀예정임...
}

%>
