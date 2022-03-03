package order;

//DB����
import hewon.*;
//////////////////
import java.sql.*;
import java.util.Vector;

public class OrderMgr {

    private DBConnectionMgr pool = null;

    public OrderMgr() {
        try {
            pool = DBConnectionMgr.getInstance();
        } catch (Exception e) {
            System.out.println("Error : Ŀ�ؼ� �������� ����!!");
        }
    }

    public void insertOrder(OrderBean order) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = pool.getConnection();
			
			//�ֹ����̺� ������� �ֹ��� �Է��ϱ� ���� ������ �ۼ���
            String query = "insert into shop_order(id, product_no, quantity, date, state)"
                    + " values(?, ?, ? ,now() ,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, order.getId());//������� ID�� �Է�
            pstmt.setString(2, order.getProduct_no());//�ֹ��� ��ǰ��ȣ�� �Է�
            pstmt.setInt(3, order.getQuantity());//�ֹ������� �Է�
            pstmt.setString(4, "1");//�ֹ����¸� �Է�=>1�� ���� �������� �ǹ�
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception :" + ex);
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }

   /*�־��� ID�� �ش��ϴ� ������� �ֹ������� �������� ���� �޼ҵ� */
    public Vector getOrder(String id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector vResult = new Vector();

        try {
            con = pool.getConnection();
            String query = "select * from shop_order where id = ? order by no desc";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);//������� ID�� ����
            rs = pstmt.executeQuery();
            while (rs.next()) { //�ֹ������� ���� ���=> �ֹ������� ����
                OrderBean order = new OrderBean();
                order.setId(rs.getString("id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setDate(rs.getString("date"));
                order.setNo(rs.getString("no"));
                order.setState(rs.getString("state"));
                order.setProduct_no(rs.getString("product_no"));
                vResult.add(order);//�ֹ������� �ϳ� �̻��� �ǹǷ� Vector�� ��� 
				                   //�����ְ� �ȴ�.
            }
        } catch (Exception ex) {
            System.out.println("Exception :" + ex);
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return vResult;
    }

    // �����ڿ��� �����ϴ� �޼ҵ� 
    public Vector getOrderList() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Vector vResult = new Vector();

        try {
            con = pool.getConnection();

			//�ֹ������� �������� ���� ���� �ۼ�
            String query = "select * from shop_order order by no desc";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                OrderBean order = new OrderBean();
                order.setId(rs.getString("id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setDate(rs.getString("date"));
                order.setNo(rs.getString("no"));
                order.setState(rs.getString("state"));
                order.setProduct_no(rs.getString("product_no"));
                vResult.add(order);//���� �ֹ������� ������ ��� �ִ� OrderBean��
				                   //Vector�� ��ƵӴϴ�.
            }
        } catch (Exception ex) {
            System.out.println("Exception :" + ex);
        } finally {
            pool.freeConnection(con, stmt, rs);
        }
        return vResult;
    }

   /*�ֹ��� ������ �˷��ִ� �޼ҵ� */
    public OrderBean getOrderDetail(String no) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderBean order = null;

        try {
            con = pool.getConnection();

			//�ֹ��� ������ �������� ���� ������ �ۼ�
            String query = "select * from shop_order where no = ?";
            pstmt = con.prepareStatement(query);

			//������ �ֹ� �󼼳����� ���� �ֹ���ȣ�� ������ �ش�.
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                order = new OrderBean();
                order.setId(rs.getString("id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setDate(rs.getString("date"));
                order.setNo(rs.getString("no"));
                order.setState(rs.getString("state"));
                order.setProduct_no(rs.getString("product_no"));
            }
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return order;
    }

    /*�ֹ�ó�� ���¸� �����ϱ� ���� �޼ҵ带 �ۼ� */
    public boolean updateOrder(String no, String state) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean result = false;
        try {
            con = pool.getConnection();

			//�ֹ�ó�� ���¸� �����ϱ� ���� ������ �ۼ�
            String query = "update shop_order set state = ? where no = ?";
            pstmt = con.prepareStatement(query);

			//�����ϰ��� �ϴ� �ֹ����¿� �ֹ���ȣ�� ������ �ش�.
            pstmt.setString(1, state);
            pstmt.setString(2, no);
            int count = pstmt.executeUpdate();
            if (count == 1) result = true;
        } catch (Exception ex) {
            System.out.println("Exception :" + ex);
        } finally {
            pool.freeConnection(con, pstmt);
        }
        return result;
    }

     /*�ֹ������� �����ϱ� ���� �޼ҵ�*/
    public boolean deleteOrder(String no) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            con = pool.getConnection();

			//�ֹ������� �����ϱ� ���� ������ �ۼ�
            String query = "delete from shop_order where no = ?";
            pstmt = con.prepareStatement(query);

			//������ �ֹ���ȣ�� ����
            pstmt.setString(1, no);
            int count = pstmt.executeUpdate();

			//�����۾��� ������ ����� �ϳ��� ��� ���������� ó��
			//�� ����̹Ƿ� result�� true�� ����

            if (count == 1) result = true;
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        } finally {
            pool.freeConnection(con, pstmt);
        }
        return result;
    }
   
}
