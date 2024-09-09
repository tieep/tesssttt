
package DAO;

import DTO.Nhanvien_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nhanvien_DAO {
        public ConnectDataBase mySQL; 
    public Nhanvien_DAO() throws SQLException {
        mySQL = new ConnectDataBase();
    }
    public ArrayList<Nhanvien_DTO> list()
    {
        ArrayList<Nhanvien_DTO> dsnv = new ArrayList<>();
        try {
            mySQL.connect();
            String sql = "SELECT * FROM nhanvien WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String manv = rs.getString("MANV");
                String tennv = rs.getString("TENNV");
                String chucvu = rs.getString("CHUCVU");
                int sdt = rs.getInt("SDT");
                String diachi = rs.getString("DIACHI");
                String email  = rs.getString("EMAIL");
                int tt = rs.getInt("TRANGTHAI");
                Nhanvien_DTO nv = new Nhanvien_DTO (manv, tennv, chucvu, sdt, diachi, email,tt );
                dsnv.add(nv);
            }
            System.out.println("Lay danh sach nhan vien thanh cong");
            rs.close();        
           mySQL.disconnect();
        }
        catch (SQLException ex) {
            System.out.println("Lay danh sach nhan vien that bai");
        }
            return dsnv;
    }
    
    public Nhanvien_DTO showNV(String id){
    try{
        mySQL.connect();
        String sql= "SELECT * FROM nhanvien WHERE MANV='"+id+"';";
           ResultSet rs = mySQL.executeQuery(sql);
                String tennv = rs.getString("TENNV");
                String chucvu = rs.getString("CHUCVU");
                int sdt = rs.getInt("SDT");System.out.println(sdt);
                String diachi = rs.getString("DIACHI");
                String email  = rs.getString("EMAIL");
                int tt = rs.getInt("TRANGTHAI");
                Nhanvien_DTO nv = new Nhanvien_DTO (id, tennv, chucvu, sdt, diachi, email,tt );        System.out.println("Lấy thông tin nhân viên thành công!");
        return nv;
     }
        catch (SQLException ex) {
            System.out.println("Lay thông tin nhân viên that bai");
        }
            return null;
    }
    
    public void add(Nhanvien_DTO item) throws SQLException {
    mySQL.connect();
    String query= "INSERT INTO nhanvien VALUES ('" + item.getManv() +"','"+ item.getTennv() +"','" +item.getChucvu() +"','" +item.getSdt() + "','" +item.getDiachi() +"','" +item.getEmail()+"','" +item.getTrangthai()+"');";
    boolean result = mySQL.executeupdate(query);
    if(result) {
        System.out.println("Thêm nhân viên thành công!");
    } else {
        System.out.println("Thêm nhân viên thất bại!");
    }
    mySQL.disconnect();
}

  
    public void update(Nhanvien_DTO item) throws SQLException {
    mySQL.connect();
    String query= "UPDATE nhanvien set TENNV = '" + item.getTennv()+"', CHUCVU='"+ item.getChucvu()+ "', SDT='" + item.getSdt() + "', DIACHI='" + item.getDiachi()+ "', EMAIL='" + item.getEmail() + "' WHERE MANV='" + item.getManv() + "'";
    boolean result = mySQL.executeupdate(query);
    if(result) {
        System.out.println("Cập nhật thông tin nhân viên thành công!");
    } else {
        System.out.println("Cập nhật thông tin nhân viên thất bại!");
    }
    mySQL.disconnect();
}
    
    
public void update_tt(Nhanvien_DTO item,int tt) throws SQLException {
    mySQL.connect();
    String query= "UPDATE nhanvien set TRANGTHAI = '" + tt + "' WHERE MANV='" + item.getManv() + "'";
    boolean result = mySQL.executeupdate(query);
    if(result) {
        System.out.println("Đổi trạng thái nhân viên thành công!");
    } else {
        System.out.println("Đổi trạng thái nhân viên thất bại!");
    }
    mySQL.disconnect();
}
    
public void delete(String m) throws SQLException {
    mySQL.connect();
    String query= "DELETE FROM nhanvien WHERE MANV = '" + m +"';";
    boolean result = mySQL.executeupdate(query);
    if(result) {
        System.out.println("Xóa nhân viên thành công!");
    } else {
        System.out.println("Xóa nhân viên thất bại!");
    }
    mySQL.disconnect();
}

public boolean check_accNV(String id) throws SQLException {
    boolean success = false; // Khởi tạo biến success với giá trị mặc định là false
    mySQL.connect();
    try {
        String query = "SELECT *  FROM taikhoan WHERE MANV = '"+id+"';";
        ResultSet rs = mySQL.executeQuery(query);
        if (rs.next()) {
                success = true; // Đặt success thành true nếu có ít nhất một dòng dữ liệu
            }
        }
     catch (SQLException ex) {
    } finally {
        mySQL.disconnect(); // Đảm bảo rằng kết nối sẽ được đóng sau khi sử dụng
    }
    return success;
}

    public ArrayList<Nhanvien_DTO> search(String in4) {
    ArrayList<Nhanvien_DTO> dsnv = new ArrayList<>();
    try {
        mySQL.connect();
        String sql = null ;
        if (Character.isLetter(in4.charAt(0))) {
            String[] ids = {"AD", "NV", "QL"};
            for (String id : ids) {
                if (in4.startsWith(id)) {
                   sql = "SELECT * FROM nhanvien WHERE MANV='" + in4 + "';"; 
                   break; // Thêm break để thoát khỏi vòng lặp khi tìm thấy MANV phù hợp
                }
            }
            if(sql == null) { // Nếu không tìm thấy MANV phù hợp, sử dụng in4 làm Tên NV
                sql = "SELECT * FROM nhanvien WHERE TENNV='" + in4 + "';";
            }
        } else {   
            sql = "SELECT * FROM nhanvien WHERE SDT=" + Integer.parseInt(in4.substring(1)) + ";";
        }
        try (ResultSet rs = mySQL.executeQuery(sql)){
            while (rs.next()) {
                String id = rs.getString("MANV");
                String tennv = rs.getString("TENNV");
                String chucvu = rs.getString("CHUCVU");
                int sdt = rs.getInt("SDT");
                String diachi = rs.getString("DIACHI");
                String email  = rs.getString("EMAIL");
                int tt = rs.getInt("TRANGTHAI");
                Nhanvien_DTO nv = new Nhanvien_DTO (id, tennv, chucvu, sdt, diachi, email,tt );   
                dsnv.add(nv);
                System.out.println(nv.getManv()+"--"+nv.getTennv());
            }
            System.out.println("Tìm kiếm NV thành công!");
        }
        mySQL.disconnect();
    } catch (SQLException ex) {
        System.out.println("Tìm kiếm NV thất bại");
    }
    return dsnv;
}

    
    public static void main (String[] args) throws SQLException{
        Nhanvien_DAO nv = new Nhanvien_DAO();
//        Nhanvien_DTO n1 = new Nhanvien_DTO("NV005","haha","Nhân viên",987666789 ,"TP HCM","6383uyejn@gmail.com");
//        Nhanvien_DTO n2 = new Nhanvien_DTO("NV5","UYEN","Nhân viên",987666789 ,"TP HCM","6383uyejn@gmail.com",1);
////        nv.update(n2);
//        nv.add(n2);
        boolean re = nv.check_accNV("QL4");
        System.out.println(re);
        ArrayList<Nhanvien_DTO> list = nv.search("NV1");
        
    }
}
