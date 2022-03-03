 package hewon;
 
 import java.sql.*;
 import java.util.*;
 import hewon.DBConnectionMgr;
 import hewon.RegisterBean;
 import hewon.ZipcodeBean;

 public class MemberMgr {
    
 	private DBConnectionMgr pool = null;//DBConnectionMgr��ü�� ����
 	public MemberMgr() {
 	 try{
 	    pool = DBConnectionMgr.getInstance();
 	   }catch(Exception e){
 	      System.out.println("Error : Ŀ�ؼ� �������� ����");
 	   }
     }//MemberMgr()
 
    public Vector getMemberList() {//ArrayList으로 변경
	   Connection con = null;
	   Statement stmt = null;
       ResultSet rs = null;
     
       Vector vecList = new Vector();
       try {
          con = pool.getConnection();
          String strQuery = "select * from member";
          stmt = con.createStatement();
          rs = stmt.executeQuery(strQuery);
          
		  while (rs.next()) {
             RegisterBean regBean = new RegisterBean();
		 	 regBean.setMem_id (rs.getString("id"));
			 regBean.setMem_passwd (rs.getString("passwd"));
 			 regBean.setMem_name (rs.getString("name"));
				/*주민번호 날리기
				 * regBean.setMem_num1 (rs.getString("mem_num1")); regBean.setMem_num2
				 * (rs.getString("mem_num2"));
				 */
 			 regBean.setMem_email (rs.getString("e_mail"));
 			 regBean.setMem_phone (rs.getString("phone"));
 			 regBean.setMem_zipcode (rs.getString("zipcode"));
 			 regBean.setMem_address (rs.getString("address"));
 			 regBean.setMem_job (rs.getString("job"));
             vecList.add(regBean);
          }
       } catch (Exception ex) {
          System.out.println("Exception" + ex);
       } finally {
          if(rs!=null)   try{rs.close();}  catch(SQLException e){}
		  if(stmt!=null) try{stmt.close();}catch(SQLException e){}
	      if(con!=null) try{con.close();}catch(SQLException e){}
       }
       return vecList;
    }
    /////ȸ��ID�� üũ�ϴ� �޼ҵ屸��/////////////////////
   public boolean checkId(String id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean checkCon = false;
        try {
            con = pool.getConnection();
            String strQuery = "select id from member where id = ?";
            pstmt = con.prepareStatement(strQuery);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            checkCon = rs.next();

        }catch(Exception ex) {
            System.out.println("Exception" + ex);
        }finally{
            pool.freeConnection(con,pstmt,rs);
        }
        return checkCon;
    }
    
	//�����ȣ �ҷ�����//////////////////////
    public Vector zipcodeRead(String area3)  {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector vecList = new Vector();//���Ͱ�ü�� ����
        try {
            con = pool.getConnection();
            String strQuery = "select * from zipcode where area3  like '"+area3+"%'";
            pstmt = con.prepareStatement(strQuery);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ZipcodeBean tempZipcode = new ZipcodeBean();
                tempZipcode.setZipcode(rs.getString("zipcode"));
                tempZipcode.setArea1(rs.getString("area1"));
                tempZipcode.setArea2(rs.getString("area2"));
                tempZipcode.setArea3(rs.getString("area3"));
                tempZipcode.setArea4(rs.getString("area4"));
                vecList.addElement(tempZipcode);
            }

        }catch(Exception ex) {
            System.out.println("Exception" + ex);
        }finally{
             pool.freeConnection(con,pstmt,rs);
        }
        return vecList;
    }
	/////ȸ������ �޼ҵ� ����//////////////////////////////
      public boolean memberInsert(RegisterBean regBean) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            con = pool.getConnection();
            String strQuery = "insert into member values(?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(strQuery);
            pstmt.setString(1,regBean.getMem_id());
            pstmt.setString(2,regBean.getMem_passwd());
            pstmt.setString(3,regBean.getMem_name());
            pstmt.setString(4,regBean.getMem_num1());
            pstmt.setString(5,regBean.getMem_num2());
            pstmt.setString(6,regBean.getMem_email());
            pstmt.setString(7,regBean.getMem_phone());
            pstmt.setString(8,regBean.getMem_zipcode());
            pstmt.setString(9,regBean.getMem_address());
            pstmt.setString(10,regBean.getMem_job());
            int count = pstmt.executeUpdate();

            if(count>0){
                flag = true; //�����Ͱ� �Է��� �Ǹ� > 0
            }
        }catch(Exception ex) {
            System.out.println("Exception" + ex);
        }finally{
             pool.freeConnection(con,pstmt,rs);
        }
        return flag;
    }

    //�α��� üũ�ϴ� �޼ҵ� ����///////////////////////////
    public boolean loginCheck(String id, String passwd)  {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginCon = false;
        try {
            con = pool.getConnection();
            String strQuery = "select id, passwd from member where id = ? and passwd = ?";
            pstmt = con.prepareStatement(strQuery);
            pstmt.setString(1,id);
            pstmt.setString(2,passwd);
            rs = pstmt.executeQuery();
            loginCon = rs.next();
        }catch(Exception ex) {
            System.out.println("Exception" + ex);
        }finally{
             pool.freeConnection(con,pstmt,rs);
        }
        return loginCon;
    }
	/*�����ϰ��� �ϴ� ȸ���� ������ �ҷ����� �޼ҵ�  */
	public RegisterBean getMember(String mem_id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        RegisterBean regBean = null;

        try {
            con = pool.getConnection();
            String strQuery = "select * from member where id=?";
            pstmt = con.prepareStatement(strQuery);
            pstmt.setString(1, mem_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                regBean = new RegisterBean();
                regBean.setMem_id(rs.getString("id"));
                regBean.setMem_passwd(rs.getString("passwd"));
                regBean.setMem_name(rs.getString("name"));
                regBean.setMem_num1(rs.getString("mem_num1"));
                regBean.setMem_num2(rs.getString("mem_num2"));
                regBean.setMem_phone(rs.getString("phone"));
                regBean.setMem_zipcode(rs.getString("zipcode"));
                regBean.setMem_address(rs.getString("address"));
                regBean.setMem_email(rs.getString("e_mail"));
                regBean.setMem_job(rs.getString("job"));
            }
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return regBean;
    }
    /*��� ȸ���� ������ ������ִ� �޼ҵ�*/
    public Vector getMemberAdminList() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Vector vecList = new Vector();

        try {
            con = pool.getConnection();
            String strQuery = "select * from member";
            stmt = con.createStatement();
            rs = stmt.executeQuery(strQuery);

            while (rs.next()) {
                RegisterBean regBean = new RegisterBean();
                regBean.setMem_id(rs.getString("id"));
                regBean.setMem_passwd(rs.getString("passwd"));
                regBean.setMem_name(rs.getString("name"));
                regBean.setMem_num1(rs.getString("mem_num1"));
                regBean.setMem_num2(rs.getString("mem_num2"));
                regBean.setMem_phone(rs.getString("phone"));
                regBean.setMem_email(rs.getString("e_mail"));
                vecList.add(regBean);
            }
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
        } finally {
            pool.freeConnection(con, stmt, rs);
        }
        return vecList;
    }
   //
	/* ȸ���� ������ �����ϴ� �޼ҵ� */
	 public boolean memberUpdate(RegisterBean regBean) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean flag = false;
        try {
            con = pool.getConnection();
            String strQuery = "update member set passwd=?, name=?, mem_num1=?, mem_num2=?,";
            strQuery = strQuery + " e_mail=?, phone=?, zipcode=?,address=?,job=?";
            strQuery = strQuery + " where id=? ";

            pstmt = con.prepareStatement(strQuery);
            pstmt.setString(1, regBean.getMem_passwd());
            pstmt.setString(2, regBean.getMem_name());
            pstmt.setString(3, regBean.getMem_num1());
            pstmt.setString(4, regBean.getMem_num2());
            pstmt.setString(5, regBean.getMem_email());
            pstmt.setString(6, regBean.getMem_phone());
            pstmt.setString(7, regBean.getMem_zipcode());
            pstmt.setString(8, regBean.getMem_address());
            pstmt.setString(9, regBean.getMem_job());
            pstmt.setString(10, regBean.getMem_id());
            int count = pstmt.executeUpdate();

            if (count == 1) {
                flag = true;
            }
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
        } finally {
            pool.freeConnection(con, pstmt);
        }
        return flag;
    }
  //
   public boolean adminCheck(String admin_id, String admin_passwd) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginCon = false;
        try {
            con = pool.getConnection();
String strQuery = "select admin_id, admin_passwd from admin where admin_id = ? and admin_passwd = ?";
            pstmt = con.prepareStatement(strQuery);
            pstmt.setString(1, admin_id);
            pstmt.setString(2, admin_passwd);
            rs = pstmt.executeQuery();
            loginCon = rs.next();
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return loginCon;
    }
 //
 }//class

