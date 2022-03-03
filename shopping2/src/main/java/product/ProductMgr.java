package product;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import javax.servlet.http.HttpServletRequest;
//DBConnectionMgr.class�߰�
import hewon.*;
//////////////////////////////
//orderBean.class�߰�
import order.*;
////////////////////////////
import java.sql.*;
import java.util.Vector;


public class ProductMgr {

    private DBConnectionMgr pool = null;

    public ProductMgr() {
        try {
            pool = DBConnectionMgr.getInstance();
        } catch (Exception e) {
            System.out.println("Error : Ŀ�ؼ� �������� ����!!");
        }
    }
    /*��ǰ�� ��Ͻ�Ű�� �޼ҵ�*/
    public boolean insertProduct(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
			//C:/Tomcat 4.1/webapps/shopping/data
            String uploadDir = "C:/Tomcat 4.1/webapps/shopping/data/";
            MultipartRequest multi = new MultipartRequest(req, uploadDir, 5 * 1024 * 1024,
				                    "euc-kr", new DefaultFileRenamePolicy());

            con = pool.getConnection();
            String query = "insert into shop_product(name, price, detail, date, stock, image)"
                    + "values(?, ?, ?, now(), ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, multi.getParameter("name"));
            pstmt.setString(2, multi.getParameter("price"));
            pstmt.setString(3, multi.getParameter("detail"));
            pstmt.setString(4, multi.getParameter("stock"));

            if (multi.getFilesystemName("image") == null) {
                pstmt.setString(5, "ready.gif");
            } else {
                pstmt.setString(5, multi.getFilesystemName("image"));
            }
            int count = pstmt.executeUpdate();
            if (count == 1) result = true;

        } catch (Exception ex) {
            System.out.println("Exception :" + ex);
        } finally {
            pool.freeConnection(con, pstmt);
        }
        return result;
    }

    /* ��ǰ��ȣ�� ���� �ڼ��� ��ǰ�� ������ �˷��ִ� �޼ҵ� ���� */
	
    public ProductBean getProduct(String no) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ProductBean product = null;

        try {
            con = pool.getConnection();
            String query = "select * from shop_product where no=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new ProductBean();
                product.setNo(rs.getInt("no"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setDetail(rs.getString("detail"));
                product.setDate(rs.getString("date"));
                product.setStock(rs.getString("stock"));
                product.setImage(rs.getString("image"));
            }
        } catch (Exception ex) {
            System.out.println("Exception :" + ex);
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return product;

    }  

    /* ��ǰ�� ������ ���� �޼ҵ� */
    public boolean updateProduct(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean result = false;
		//���� ���� ��θ� ����
        String uploadDir = "C:/Tomcat 4.1/webapps/shopping/data/";

        try {
            con = pool.getConnection();
       MultipartRequest multi = new MultipartRequest(req, uploadDir, 5 * 1024 * 1024, 
				                 "euc-kr", new DefaultFileRenamePolicy());

            if (multi.getFilesystemName("image") == null) {
String query = "update shop_product set name = ?, price = ?,detail = ?, stock = ?  where no = ?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, multi.getParameter("name"));
                pstmt.setString(2, multi.getParameter("price"));
                pstmt.setString(3, multi.getParameter("detail"));
                pstmt.setString(4, multi.getParameter("stock"));
                pstmt.setString(5, multi.getParameter("no"));
            } else {
				//���ο� �̹����� ���ε��Ͽ��ٸ� ���ο� �̹����� ����
String query = "update shop_product set name =? ,price = ?, detail = ?,stock = ?, image = ?  where no = ?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, multi.getParameter("name"));
                pstmt.setString(2, multi.getParameter("price"));
                pstmt.setString(3, multi.getParameter("detail"));
                pstmt.setString(4, multi.getParameter("stock"));
                pstmt.setString(5, multi.getFilesystemName("image"));
                pstmt.setString(6, multi.getParameter("no"));
            }
            int count = pstmt.executeUpdate();
            if (count == 1) result = true;
        } catch (Exception ex) {
            System.out.println("Exception :" + ex);
        } finally {
            pool.freeConnection(con, pstmt);
        }
        return result;
    }

   /*�ش��ǰ�� �������� ���̱� ���� �޼ҵ� ���� */
    public void reduceProduct(OrderBean order) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = pool.getConnection();
            String query = "update shop_product set stock = (stock - ? ) where no = ? ";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, order.getQuantity());
            pstmt.setString(2, order.getProduct_no());
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception :" + ex);
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }
    
	/*���� ��ϵ� ��ǰ����Ʈ�� ������ �˷��ִ� �޼ҵ�    */
    public Vector getProductList() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Vector vProduct = new Vector();

        try {
            con = pool.getConnection();
            String query = "select * from shop_product order by no desc";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                ProductBean product = new ProductBean();
                product.setNo(rs.getInt("no"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setDetail(rs.getString("detail"));
                product.setDate(rs.getString("date"));
                product.setStock(rs.getString("stock"));
                product.setImage(rs.getString("image"));
                vProduct.add(product);
            }
        } catch (Exception ex) {
            System.out.println("Exception :" + ex);
        } finally {
            pool.freeConnection(con, stmt, rs);
        }
        return vProduct;
    }
      

    /* ��ϵ� ��ǰ�� ������Ű�� �޼ҵ�*/
    public boolean deleteProduct(String no) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            con = pool.getConnection();
            String query = "delete from shop_product where no = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, no);
            int count = pstmt.executeUpdate();
            if (count == 1) result = true;
        } catch (Exception ex) {
            System.out.println("Exception :" + ex);
        } finally {
            pool.freeConnection(con, pstmt);
        }
        return result;
    }
}