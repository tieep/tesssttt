package DAO;

import DTO.KhoDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class KhoDAO {

    private ConnectDataBase mySQL;

    public KhoDAO() {
        try {
            mySQL = new ConnectDataBase();
        } catch (SQLException e) {
            System.out.println("That bai");
        }
    }

    public ArrayList<KhoDTO> list(ArrayList<String> data_filters) {
        String tuNgay = data_filters.get(0);
        String denNgay = data_filters.get(1);
        ArrayList<KhoDTO> ds = new ArrayList<>();
        try {
            mySQL.connect();
            String sql = """
                         SELECT sp.MASP, l.TENLOAI, sp.TENSP, s.TENSIZE, ctsp.SOLUONG AS SOLUONGTON, 
                                IFNULL(SOLUONGNHAP, 0) AS SOLUONGNHAP, IFNULL(SOLUONGXUAT, 0) AS SOLUONGXUAT
                         FROM sanpham sp
                         JOIN chitietsanpham ctsp ON sp.MASP = ctsp.MASP
                         JOIN size s ON ctsp.MASIZE = s.MASIZE
                         JOIN loai l ON sp.MALOAI = l.MALOAI
                         LEFT JOIN 
                             (SELECT MASP, MASIZE, SUM(SOLUONG) AS SOLUONGXUAT
                              FROM chitiethoadon cthd
                              JOIN hoadon hd ON cthd.SOHD = hd.SOHD
                              WHERE hd.NGAYHD BETWEEN '"""+ tuNgay+"' AND '"+ denNgay +"'\n"
                    + "     GROUP BY MASP, MASIZE) AS cthd ON sp.MASP = cthd.MASP AND ctsp.MASIZE = cthd.MASIZE\n"
                    + "LEFT JOIN \n"
                    + "    (SELECT MASP, MASIZE, SUM(SOLUONG) AS SOLUONGNHAP\n"
                    + "     FROM chitietphieunhap ctpn\n"
                    + "     JOIN phieunhap pn ON ctpn.MAPN = pn.MAPN\n"
                    + "     WHERE pn.NGAYNHAP BETWEEN '"+ tuNgay+"' AND '"+ denNgay +"'\n"
                    + "     GROUP BY MASP, MASIZE) AS ctpn ON sp.MASP = ctpn.MASP AND ctsp.MASIZE = ctpn.MASIZE;";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String maSP = rs.getNString("MASP");
                String tenLoai = rs.getNString("TENLOAI");
                String tenSP = rs.getNString("TENSP");
                String size = rs.getNString("TENSIZE");
                int slTon = rs.getInt("SOLUONGTON");
                int slNhap = rs.getInt("SOLUONGNHAP");
                int slXuat = rs.getInt("SOLUONGXUAT");
                KhoDTO kho = new KhoDTO(maSP, tenLoai, tenSP, size, slTon, slNhap, slXuat);
                ds.add(kho);
            }
            rs.close();
            mySQL.disconnect();

        } catch (SQLException ex) {

        }
        return ds;
    }

    public static void main(String[] args) {
        KhoDAO k = new KhoDAO();
        ArrayList<String> currentday = new ArrayList<>();
        currentday.add("07/09/2024");
        currentday.add("07/09/2024");
        System.out.println(k.list(currentday).size());
//        System.out.println(k.list().get(0).getTonDK());
    }
}
