/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Hoadon_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hoadon_DAO {
    private  ConnectDataBase mySQL  ;
    public Hoadon_DAO() throws SQLException{
        mySQL = new ConnectDataBase();
   }
    
    public ArrayList<Hoadon_DTO> listchucnang()
    {
        ArrayList<Hoadon_DTO> dshd = new ArrayList<>();
        try {
            mySQL.connect();
            String sql = "SELECT * FROM hoadon WHERE 1";
            try (ResultSet rs = mySQL.executeQuery(sql)) {
                while(rs.next())
                {
                    String maHD = rs.getString("SOHD");
                    String ngayHD = rs.getDate("NGAYHD").toString();
                    System.out.println(ngayHD);
                    String thoigian = rs.getTime("THOIGIAN").toString();
                    System.out.println(thoigian);
                    int maKH = rs.getInt("MAKH");
                    String maNV = rs.getString("MANV");
                    int giamgia = rs.getInt("TIENGIAMGIA");
                    int tongtien = rs.getInt("TONGTIEN");
                    
                    Hoadon_DTO hd = new Hoadon_DTO(maHD, ngayHD,thoigian, maKH, maNV,giamgia, tongtien);
                    dshd.add(hd);
                }
            }
             System.out.println("Lay danh sach hoa don thanh cong");
             mySQL.disconnect();
        } catch (SQLException ex) {
            System.out.println("Lay danh sach hoa don that bai");
        }
        
        return dshd;
    }
    
    
    // xóa hóa đơn
    public void delete(String m) throws SQLException {
    mySQL.connect();
    String query= "DELETE FROM hoadon WHERE SOHD = '" + m +"';";
    boolean result = mySQL.executeupdate(query);
    mySQL.disconnect();
    
}
   
   public boolean add(Hoadon_DTO item) {
        try {
            mySQL.connect();
            String query = "INSERT INTO hoadon (`SOHD`, `NGAYHD`, `MAKH`, `MANV`, `TONGTIEN`, `TIENGIAMGIA`, `THOIGIAN`)  VALUES ('" + item.getMaHD()+ "','" + item.getNgayHD()+ "','" + item.getMaKH()+ "','" + item.getMaNV()+ "','" + item.getTongTien()+ "','" + item.getGiamgia()+ "','" + item.getThoigian()+ "');";
            mySQL.executeUpdate(query);
            mySQL.disconnect();
            return true;
        } catch (SQLException e) {
        }
        return false;
    } 
    
   public void updatehd(String id,int tt) throws SQLException {
            mySQL.connect(); // TODO Auto-generated catch block
            String sql = "update hoadon set TONGTIEN = " + tt +" where SOHD = '" +id  + "';";
            mySQL.executeUpdate(sql);
            mySQL.disconnect();
	}
    public ArrayList<Hoadon_DTO> searchFillData(ArrayList<String> data_filter) {
        try {
            ArrayList<Hoadon_DTO> dshd = new ArrayList<>();
            mySQL.connect(); // TODO Auto-generated catch block
            String sql = "SELECT * FROM hoadon WHERE SOHD ='"+data_filter.get(0)+"' OR NGAYHD BETWEEN '"+data_filter.get(1)+"' AND '"+data_filter.get(2)+"'";
            try (ResultSet rs = mySQL.executeQuery(sql)) {
                while(rs.next())
                {
                    String maHD = rs.getString("SOHD");
                    String ngayHD = rs.getDate("NGAYHD").toString();
                   
                    String thoigian = rs.getTime("THOIGIAN").toString();
                   
                    int maKH = rs.getInt("MAKH");
                    String maNV = rs.getString("MANV");
                    int giamgia = rs.getInt("TIENGIAMGIA");
                    int tongtien = rs.getInt("TONGTIEN");
                    
                    Hoadon_DTO hd = new Hoadon_DTO(maHD, ngayHD,thoigian, maKH, maNV,giamgia, tongtien);
                    dshd.add(hd);
                }
                }
           
            mySQL.disconnect();
            System.out.println("danh sach tim kiem " +dshd.size());
             return dshd;
        } catch (SQLException ex) {
            Logger.getLogger(Hoadon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Hoadon_DTO searchHoadon_DTO(String mahd) {
        Hoadon_DTO hdDTO = null;
        try {
            mySQL.connect();
            String sql = "SELECT * FROM hoadon WHERE SOHD='" + mahd + "';";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String maHD = rs.getString("SOHD");
                String ngayHD = rs.getDate("NGAYHD").toString();
                String thoigian = rs.getTime("THOIGIAN").toString();
                int maKH = rs.getInt("MAKH");
                String maNV = rs.getString("MANV");
                int giamgia = rs.getInt("TIENGIAMGIA");
                int tongtien = rs.getInt("TONGTIEN");
                
                hdDTO = new Hoadon_DTO(maHD, ngayHD, thoigian, maKH, maNV, giamgia, tongtien);
            }
        }catch (SQLException ex) {
        System.out.println("Tìm kiếm thất bại");
        }
        return hdDTO;
    }
    public ArrayList<Hoadon_DTO> search_for_ID(String id) {
    ArrayList<Hoadon_DTO> dshd = new ArrayList<>();
    try {
        mySQL.connect();
        String sql ;
        if (id.charAt(0) == 'H') {
            sql = "SELECT * FROM hoadon WHERE SOHD='" + id + "';";
        } else {
            if (id.charAt(0) == 'N') {
                sql = "SELECT * FROM hoadon WHERE MANV='" + id + "';";
            } else {
                sql = "SELECT * FROM hoadon WHERE MAKH='" + id + "';";
            }
        }
        try (ResultSet rs = mySQL.executeQuery(sql)){
            while (rs.next()) {
                String maHD = rs.getString("SOHD");
                String ngayHD = rs.getDate("NGAYHD").toString();
                String thoigian = rs.getTime("THOIGIAN").toString();
                int maKH = rs.getInt("MAKH");
                String maNV = rs.getString("MANV");
                int giamgia = rs.getInt("TIENGIAMGIA");
                int tongtien = rs.getInt("TONGTIEN");
                System.out.println(maHD+"--"+ngayHD+"--"+thoigian+"--"+maKH+"--"+maNV);
                Hoadon_DTO hd = new Hoadon_DTO(maHD, ngayHD, thoigian, maKH, maNV, giamgia, tongtien);
                dshd.add(hd);
            }
        }
        System.out.println("Tìm kiếm HD theo id thành công!");
        mySQL.disconnect();
    } catch (SQLException ex) {
        System.out.println("Tìm kiếm HD theo id thất bại");
    }
    return dshd;
    }
    
   public ArrayList<Hoadon_DTO> search_for_Date(String begin, String end) throws ParseException {
    ArrayList<Hoadon_DTO> dshd = new ArrayList<>();
    SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");

    try {
        mySQL.connect();

        Date fromDate = sdfInput.parse(begin);
        Date toDate = sdfInput.parse(end);

        String fromDateFormatted = sdfOutput.format(fromDate);
        String toDateFormatted = sdfOutput.format(toDate);

        String sql = "SELECT * FROM hoadon WHERE NGAYHD BETWEEN '"+fromDateFormatted+ "' AND '"+toDateFormatted+"';";
            try (ResultSet rs = mySQL.executeQuery(sql)) {
                while (rs.next()) {
                    String maHD = rs.getString("SOHD");
                    String ngayHD = rs.getString("NGAYHD");
                    String thoigian = rs.getString("THOIGIAN");
                    int maKH = rs.getInt("MAKH");
                    String maNV = rs.getString("MANV");
                    int giamgia = rs.getInt("TIENGIAMGIA");
                    int tongtien = rs.getInt("TONGTIEN");
                    Hoadon_DTO hd = new Hoadon_DTO(maHD, ngayHD, thoigian, maKH, maNV, giamgia, tongtien);
                    System.out.println(hd.getMaHD()+"--"+hd.getNgayHD()+"--"+hd.getThoigian()+"--"+hd.getMaKH()+"--"+hd.getMaNV());
                    dshd.add(hd);
                }
            }
        mySQL.disconnect();
    } catch (SQLException ex) {
        System.out.println("Tìm kiếm HD theo id thất bại");
    }
    return dshd;
}

    
    public ArrayList<Hoadon_DTO> search_for_IDDate (String id, String begin, String end) throws ParseException {
        ArrayList<Hoadon_DTO> dshd_id = new ArrayList<>();
        dshd_id = search_for_ID(id);
        ArrayList<Hoadon_DTO> dshd_date = new ArrayList<>();
        dshd_date = search_for_Date(begin, end);
        ArrayList<Hoadon_DTO> dshd = new ArrayList<>();
        for(Hoadon_DTO hd1 : dshd_id){
            for(Hoadon_DTO hd2 : dshd_date){
                if(hd1.getMaHD().equals(hd2.getMaHD()))
                {
                    for(Hoadon_DTO hd : dshd)
                        if(!hd.getMaHD().equals(hd2.getMaHD()))
                            dshd.add(hd1);
                }
            }
        }
        return dshd;
    }
     
     public static void main (String[] args) throws SQLException{
        Hoadon_DAO hd = new Hoadon_DAO();
        ArrayList<Hoadon_DTO> a = hd.listchucnang();
    }
}
