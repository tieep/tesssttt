package DAO;

import java.sql.ResultSet;
import BUS.loaiSPBUS;
import DTO.ThongKeDTO;
import GUI.ThongKeGUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThongKeDAO {
    public ConnectDataBase mySQL;

    public ThongKeDAO() {
        try {
            mySQL = new ConnectDataBase();
        } catch (SQLException e) {
            System.out.println("That bai");
        }
    }
    public ArrayList<ThongKeDTO> listDoanhThu(ArrayList<String> data_filters){
        String tuNgay = data_filters.get(0);
        String denNgay = data_filters.get(1);
        String loai = data_filters.get(2);
        loaiSPBUS loaiBUS = new loaiSPBUS();
        for (int i = 0; i < loaiBUS.getListFull().size(); i++) {
            if (loaiBUS.getListFull().get(i).getTENLOAI().equals(loai)) {
                loai = loaiBUS.getListFull().get(i).getMALOAI();
            }
        } 
        ArrayList<ThongKeDTO> ds = new ArrayList<>();
        String query = "SELECT * FROM"
                + "("
                + "    SELECT sp.MASP, sp.MALOAI,sp.TENSP,SUM(cthd.SOLUONG) AS SL,"
                + "           sp.PRICE, sp.PRICE * SUM(cthd.SOLUONG) AS TT"
                + "    FROM hoadon hd"
                + "    INNER JOIN chitiethoadon cthd ON hd.SOHD = cthd.SOHD"
                + "    INNER JOIN sanpham sp ON sp.MASP = cthd.MASP"
                + "    WHERE hd.NGAYHD BETWEEN '" + tuNgay + "' AND '" + denNgay + "'"
                + "    GROUP BY sp.MASP, sp.MALOAI, sp.TENSP, sp.PRICE"
                + ")"
                + "AS subquery ";
        if (!loai.equalsIgnoreCase("Tất cả")) {
            query += "where subquery.maloai ='" + loai + "'";
        }
        System.out.println(query);
        try {
            mySQL.connect();
            ResultSet rs = mySQL.executeQuery(query);
            while (rs.next()) {
                String maSP = rs.getString("MASP");
                String maLoai = rs.getString("MALOAI");
                String tenSP = rs.getString("TENSP");
                int soLuong = rs.getInt("SL");
                double donGia = rs.getDouble("PRICE");
                double thanhTien = rs.getDouble("TT");
                ThongKeDTO thongKe = new ThongKeDTO(maSP, maLoai, tenSP, soLuong, donGia, thanhTien);
                ds.add(thongKe);
            }
            rs.close();
            mySQL.disconnect();

        } catch (SQLException ex) {
        }
        return ds;
    }
    public ArrayList<ThongKeDTO> listBanChay(ArrayList<String> data_filters){
        String tuNgay = data_filters.get(0);
        String denNgay = data_filters.get(1);
        int top = Integer.parseInt(data_filters.get(2));
        ArrayList<ThongKeDTO> ds = new ArrayList<>();
        String query = "SELECT * FROM"
                + "("
                + "    SELECT sp.MASP, sp.MALOAI, sp.TENSP, SUM(cthd.SOLUONG) AS SL,"
                + "           sp.PRICE, sp.PRICE * SUM(cthd.SOLUONG) AS TT"
                + "    FROM hoadon hd"
                + "    INNER JOIN chitiethoadon cthd ON hd.SOHD = cthd.SOHD"
                + "    INNER JOIN sanpham sp ON sp.MASP = cthd.MASP"
                + "    WHERE hd.NGAYHD BETWEEN '" + tuNgay + "' AND '" + denNgay + "'"
                + "    GROUP BY sp.MASP, sp.MALOAI, sp.TENSP, sp.PRICE"
                + ")"
                + " AS subquery ";

        // Sắp xếp theo số lượng sản phẩm bán ra (SL) và giới hạn top 10
        query += "ORDER BY subquery.SL DESC LIMIT " + top;

        System.out.println(query);
        try {
            mySQL.connect();
            ResultSet rs = mySQL.executeQuery(query);
            while (rs.next()) {
                String maSP = rs.getString("MASP");
                String maLoai = rs.getString("MALOAI");
                String tenSP = rs.getString("TENSP");
                int soLuong = rs.getInt("SL");
                double donGia = rs.getDouble("PRICE");
                double thanhTien = rs.getDouble("TT");
                ThongKeDTO thongKe = new ThongKeDTO(maSP, maLoai, tenSP, soLuong, donGia, thanhTien);
                ds.add(thongKe);
            }
            rs.close();
            mySQL.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
}
