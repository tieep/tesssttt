
package DAO;

import DTO.ChitietHD_DTO;
import DTO.chitietsanpham_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChitietHD_DAO {
    public ConnectDataBase mySQL; 
    public ChitietHD_DAO() throws SQLException {
        mySQL = new ConnectDataBase();
    }
    
    public ArrayList<ChitietHD_DTO> list(String maHD)
    {
        ArrayList<ChitietHD_DTO> dscthd = new ArrayList<>();
        try {
           mySQL.connect();
           String sql="select `chitiethoadon`.`MASP`, `sanpham`.`TENSP`, `size`.`TENSIZE`, `chitiethoadon`.`SOLUONG`, `chitiethoadon`.`DONGIA`, (`chitiethoadon`.`SOLUONG`*`chitiethoadon`.`DONGIA`)as THANHTIEN from `size`, `sanpham`,`chitiethoadon` where `chitiethoadon`.`SOHD`='" +maHD +"' and `chitiethoadon`.`MASP`=`sanpham`.`MASP` and `chitiethoadon`.`MASIZE` = `size`.`MASIZE`;";
            try (ResultSet rs = mySQL.executeQuery(sql)) {
                while (rs.next()) {
                    String masp = rs.getString("MASP");
                    System.out.println(masp);
                    String tensp = rs.getString("TENSP");
                    System.out.println(tensp);
                    String size = rs.getString("TENSIZE"); // Corrected column name
                    System.out.println(size);
                    int sl = rs.getInt("SOLUONG");
                    System.out.println(sl);
                    double gia = rs.getDouble("DONGIA");
                    System.out.println(gia);
                    double tt = rs.getDouble("THANHTIEN"); // Corrected column name
                    System.out.println(tt);
                              
                    ChitietHD_DTO cthd = new ChitietHD_DTO(masp, tensp, size, sl, gia, tt);
                    dscthd.add(cthd);
                                System.out.println("Lay danh sach chi tiet hoa don thanh cong");

            }
            rs.close();        
           mySQL.disconnect();
        }
}
        catch (SQLException ex) {
            System.out.println("Lay danh sach chi tiet hoa don that bai");
        }
        return dscthd;   
     
    }
    

    // trả về danh sách chi tiết sản phẩm cần set số lượng
    public ArrayList<chitietsanpham_DTO> listtorestore(String mahd) throws SQLException {
        ArrayList<chitietsanpham_DTO> listupdate = new ArrayList<>();
        try {
           mySQL.connect();
            String sql ="SELECT cs.MASP as ID, cs.MASIZE as SIZE, (cs.SOLUONG + ch.SOLUONG) as SL FROM chitiethoadon ch INNER JOIN chitietsanpham cs ON ch.MASP = cs.MASP AND ch.MASIZE = cs.MASIZE WHERE ch.SOHD = '"+mahd+"';";
                try (ResultSet rs = mySQL.executeQuery(sql)) {
                    while (rs.next()) {
                    String id = rs.getString("ID");
                    System.out.println(id);
                    String s = rs.getString("SIZE");
                    System.out.println(s);
                    int slg = rs.getInt("SL"); // Corrected column name
                    System.out.println(slg);
                    chitietsanpham_DTO ctsp = new chitietsanpham_DTO(id, s, slg);
                    listupdate.add(ctsp);                   
                }
                System.out.println("Lay danh sach ctsp sau khi tra hang thanh cong!");
            rs.close();        
           mySQL.disconnect();
        }
    }
        catch (SQLException ex) {
            System.out.println("Lay danh sach ctsp sau khi tra hang that bai");
        }
        return listupdate;        
    }
    
     
    
    public boolean delete(String m) throws SQLException {
    boolean success = false;
    mySQL.connect();
    String query= "DELETE FROM chitiethoadon WHERE SOHD = '" + m +"';";
    boolean result = mySQL.executeupdate(query);
    if(result) {
        // cập nhật lại số lượng sản phẩm 
        System.out.println("Xoa san pham hoa don thanh cong!");
        success = true;
    } else {
        System.out.println("Xoa san pham hoa don that bai!");
    }
    mySQL.disconnect();
    return success;
}
public void add(ChitietHD_DTO item) throws SQLException {
    mySQL.connect();
    String query = "INSERT INTO chitiethoadon(`SOHD`, `MASP`, `MASIZE`, `SOLUONG`, `DONGIA`) VALUES ('" + item.getMaHD()+ "','" + item.getMaSP()+"','" + item.getMaSize()+ "','" + item.getSl()+"','" + item.getGia()+"');";
    mySQL.executeUpdate(query);
    mySQL.disconnect();
    }

     public void update(String idhd, String idsp, String size, int sl) throws SQLException {
            mySQL.connect(); // TODO Auto-generated catch block
            String sql = "update chitiethoadon set SOLUONG = " + sl +", MASIZE ='"+ size +"' where MASP = '" + idsp + "' and SOHD = '" + idhd +"';";
            mySQL.executeUpdate(sql);
            mySQL.disconnect();
	}
     
     public String[] get_AllSize(String masp) throws SQLException
    {
        DAO_chitietsanpham cpDAO = new DAO_chitietsanpham();
        String[] allSize = cpDAO.get_AllSIZE(masp);
        return allSize;
    }
     
    public static void main (String[] args) throws SQLException{
        ChitietHD_DAO cthd = new ChitietHD_DAO();
        ArrayList<ChitietHD_DTO> list = cthd.list("HD001");
//        ArrayList<chitietsanpham_DTO> listup = cthd.listtorestore("HD002");
        cthd.delete("HD1");

    }
}
