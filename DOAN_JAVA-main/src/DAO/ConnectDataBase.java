/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


/**
 *
 * @author hp
 */
//import com.mysql.cj.jdbc.Driver;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;


import java.sql.*;

public class ConnectDataBase {
    private Connection conn;
    private String url;
    private String dbName;
    private String driver;
    private String userName; 
    private String password;


    // Phương thức khởi tạo có tên giống với tên lớp
    public ConnectDataBase() throws SQLException {
        url = "jdbc:mysql://localhost:3306/";
        dbName = "qlba";
        driver = "com.mysql.cj.jdbc.Driver";
        userName = "root";
        password = "";
    }

   public ConnectDataBase(String url, String dbName, String driver, String userName, String password) {
      this.url = url;
      this.dbName = dbName;
      this.driver = driver;
      this.userName = userName;
      this.password = password;
   }

   public void connect() throws SQLException {
      try {
          System.out.println("tai sao");
         Class.forName(driver);
         conn = DriverManager.getConnection(url + dbName + "?sslMode=DISABLED", userName, password);
      } catch (ClassNotFoundException e) {
//         throw new SQLException("Driver not found");
      }
      
   }


public void disconnect() {
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
   public ResultSet executeQuery(String sql){//executeQuery() được sử dụng để thực thi các câu lệnh SELECT trả về dữ liệu trong ResultSet
       ResultSet rs = null;
       try {
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            
        } catch (SQLException ex) {
            System.out.println("Thêm thất bại");
            System.out.println(ex);
            System.out.println(sql);    

        }
       return rs;
   }
   
   public void executeUpdate(String sql) throws SQLException {//executeUpdate() trong JDBC được sử dụng để thực thi các câu lệnh SQL như INSERT, UPDATE, DELETE hoặc các câu lệnh khác như CREATE TABLE, ALTER TABLE và DROP TABLE
      //connect();
      try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.println("Thực hiện thất bại" + ex.getMessage());
        }
   }

 public boolean executeupdate(String sql) {
    boolean success = false;
    try {
        connect();
        Statement st = conn.createStatement();
        int rowsAffected = st.executeUpdate(sql);
        if (rowsAffected > 0) {
            success = true;
        }
        disconnect();
    } catch (SQLException ex) {
       
    }
    return success;
}

    public static void main(String[] args) throws SQLException {
        ConnectDataBase cn=new ConnectDataBase();
        cn.connect();
        if(cn.conn==null)
        {
            System.out.println("Kết nối thất bạii");
        }
        else
            System.out.println("Kêt nối thành công");
//        String sql="DELETE FROM chucnang WHERE MACHUCNANG='QLSP' ";
//        cn.executeUpdate(sql);
    }
   
}