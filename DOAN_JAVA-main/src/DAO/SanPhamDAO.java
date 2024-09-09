package DAO;

import DTO.SanPhamDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO {

    private ConnectDataBase mySQL;

    public SanPhamDAO() {
        try {
            mySQL = new ConnectDataBase();
        } catch (SQLException e) {
            System.out.println("That bai");
        }
    }

    public ArrayList<SanPhamDTO> list() {
        ArrayList<SanPhamDTO> dssp = new ArrayList<>();
        try {
            mySQL.connect();
            String query = "SELECT s.MASP, s.MALOAI, s.PRICE, s.TENSP, s.TRANGTHAI, GROUP_CONCAT(h.TENHINH) AS TENHINH "
                    + "FROM sanpham s "
                    + "LEFT JOIN hinh h ON s.MASP = h.MASP "
                    + "GROUP BY s.MASP";
            ResultSet rs = mySQL.executeQuery(query);
            while (rs.next()) {
                SanPhamDTO sanPham = new SanPhamDTO();
                sanPham.setMaSP(rs.getString("MASP"));
                sanPham.setMaLoai(rs.getString("MALOAI"));
                sanPham.setPrice(rs.getDouble("PRICE"));
                sanPham.setTenSP(rs.getString("TENSP"));
                sanPham.setTrangThai(rs.getInt("TRANGTHAI"));

                // Truy cập vào mảng tenHinh chỉ khi có dữ liệu
                if (rs.getString("TENHINH") != null) {
                    String[] tenHinh = rs.getString("TENHINH").split(",");
                    // Sắp xếp mảng tenHinh theo thứ tự tăng dần
                    Arrays.sort(tenHinh);
                    sanPham.setTenHinh(tenHinh);
                } else {
                    // Xử lý trường hợp không có hình ảnh
                    sanPham.setTenHinh(new String[0]); // Gán mảng rỗng cho trường tenHinh
                }

                dssp.add(sanPham);
            }
            rs.close();
     
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("danh sách = " + dssp.size());
        return dssp;
    }

    public void add(SanPhamDTO sp) {
        try {
            mySQL.connect();
            String query = "INSERT INTO sanpham VALUES ('"
                    + sp.getMaSP() + "', '"
                    + sp.getMaLoai() + "', '"
                    + sp.getPrice() + "', '"
                    + sp.getTenSP() + "', '"
                    + sp.getTrangThai() + "')";
            mySQL.executeUpdate(query);

            for (int i = 0; i < sp.getTenHinh().length; i++) {
                query = "INSERT INTO hinh VALUES ('";
                query += sp.tenHinh[i] + "', '"
                        + sp.getMaSP() + "')";
                mySQL.executeUpdate(query);
            }
            mySQL.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void set(SanPhamDTO sp) {
        try {
            mySQL.connect();

            // Xoá các hình ảnh cũ
            String deleteQuery = "DELETE FROM hinh WHERE MASP='" + sp.getMaSP() + "'";
            mySQL.executeUpdate(deleteQuery);

            // Thêm các hình ảnh mới
            for (int i = 0; i < sp.getTenHinh().length; i++) {
                String insertQuery = "INSERT INTO hinh VALUES ('" + sp.getTenHinh()[i] + "', '" + sp.getMaSP() + "')";
                mySQL.executeUpdate(insertQuery);
            }

            // Cập nhật thông tin sản phẩm
            String updateQuery = "UPDATE sanpham SET "
                    + "MALOAI='" + sp.getMaLoai() + "', "
                    + "PRICE='" + sp.getPrice() + "', "
                    + "TENSP='" + sp.getTenSP() + "', "
                    + "TRANGTHAI='" + sp.getTrangThai() + "' "
                    + "WHERE MASP ='" + sp.getMaSP() + "'";
            mySQL.executeUpdate(updateQuery);

            mySQL.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String maSP) {
        try {
            mySQL.connect();
            String query = "UPDATE sanpham SET "
                    + "TRANGTHAI='0' "
                    + "WHERE MASP='" + maSP + "'";
            System.out.println(query);
            mySQL.executeUpdate(query);
            mySQL.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        SanPhamDAO sp = new SanPhamDAO();
        ArrayList<SanPhamDTO> d = new ArrayList<>();
        d = sp.list();
        for (int i = 0; i < d.size(); i++) {
            SanPhamDTO sanPham = d.get(i);
            System.out.println("Mã sản phẩm: " + sanPham.getMaSP());
            System.out.println("Mã loại: " + sanPham.getMaLoai());
            System.out.println("Tên sản phẩm: " + sanPham.getTenSP());
            System.out.println("Giá: " + sanPham.getPrice());
            System.out.println("Trang thai: " + sanPham.getTrangThai());
            String[] tenHinh = sanPham.getTenHinh();
            System.out.println("Danh sách hình ảnh:");
            for (String ten : tenHinh) {
                System.out.println(ten);
            }
            System.out.println("---------------------");
        }
        System.out.println(d.size());

//        String t[] = {"b.jpg", "p.jpg", "o.jpg"};
//        SanPhamDTO m = new SanPhamDTO("maSP2", "maloai01", "name01", 12450, t, 1);
////        sp.add(m);
////        sp.set(m);
//            sp.delete("maSP2");
    }
}
