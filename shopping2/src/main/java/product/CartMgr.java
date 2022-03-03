package product;

import java.util.Hashtable;
//�߰�
import order.*;
///////////////////////
public class CartMgr {

    private Hashtable hCart = new Hashtable();

    public CartMgr() {
    }
    
	/*��ٱ��Ͽ� ��ǰ�� ��Ͻ�Ű�� �޼ҵ�*/
    public void addCart(OrderBean order) {

		//OrderBean���κ��� ������ ��ǰ�� ��ǰ��ȣ�� ������ �����´�.
        String product_no = order.getProduct_no();
        int quantity = order.getQuantity();

		//������ ��ǰ������ ��� 1�� �̻��� ��� ����ȴ�.
        if (quantity > 0) {
            if (hCart.containsKey(product_no)) { //������ ��ǰ�� �̹� ��ٱ��Ͽ� �ִ��� �˻�

			    //�̹� ��ٱ��Ͽ� �ִ� ��ǰ�̶�� ��ٱ��Ͽ� �ִ� ��ǰ�� ������ �����´�.
                OrderBean tempOrder = (OrderBean) hCart.get(product_no);

				//��ٱ��Ͽ� �ִ� ��ǰ������ ���� ��û�� ������ �����ش�.
                quantity += tempOrder.getQuantity();
                tempOrder.setQuantity(quantity);	//�Ѽ����� ����
                hCart.put(product_no, tempOrder);//��ٱ��ϸ� ������ ����
            } else {
                 //�ߺ��� ��ǰ�� �ƴ϶�� ��ٱ��Ͽ� ���Ӱ� ��ǰ�� �߰�
                hCart.put(product_no, order);
            }
        }
    }
   
    /*��ٱ��Ͽ� ��ϵ� ��ǰ����Ʈ�� �˾ƺ���*/
    public Hashtable getCartList() {
        return hCart;
    }
    
	/*��ٱ����� ������ �����ϱ� ���ؼ� ��ٱ��� ������ ���� �Է� */
    public void updateCart(OrderBean order) {
        String product_no = order.getProduct_no();
        hCart.put(product_no, order);
    }

    /*�ؽ����̺� ����� ��ٱ����� ����� ���� */
    public void deleteCart(OrderBean order) {
        String product_no = order.getProduct_no();
        hCart.remove(product_no);
    }

}