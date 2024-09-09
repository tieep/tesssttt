package BUS;

import DTO.Hoadon_DTO;
import DAO.Hoadon_DAO;
import DTO.chitietsanpham_DTO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Hoadon_BUS {

    public static int getNumberOfRow() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static boolean addHoaDon(Hoadon_DTO hoaDon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public ArrayList<Hoadon_DTO> dshoadon;

    public Hoadon_BUS() throws SQLException {
        dshoadon = new ArrayList<>();
        list();
    }

    public void list() throws SQLException {
        Hoadon_DAO dshd = new Hoadon_DAO();
        dshoadon = dshd.listchucnang();
    }

    public void delete(String mahd) throws SQLException {
        Hoadon_DAO hdDAO = new Hoadon_DAO();
        hdDAO.delete(mahd);
    }

    public Hoadon_DTO searchHoadon_DTO(String mahd) {
        Hoadon_DTO hdDTO = null;
        try {
            Hoadon_DAO hdDAO = new Hoadon_DAO();
            hdDTO = hdDAO.searchHoadon_DTO(mahd);
        } catch (SQLException ex) {
            Logger.getLogger(Hoadon_BUS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hdDTO;
    }

    public ArrayList<Hoadon_DTO> search_for_ID(String id) throws SQLException {
        Hoadon_DAO hdDAO = new Hoadon_DAO();
        ArrayList<Hoadon_DTO> dshd = hdDAO.search_for_ID(id);
        return dshd;
    }

    public ArrayList<Hoadon_DTO> search_for_Date(String begin, String end) throws SQLException, ParseException {
        Hoadon_DAO hdDAO = new Hoadon_DAO();
        ArrayList<Hoadon_DTO> dshd = hdDAO.search_for_Date(begin, end);
        return dshd;
    }

    public ArrayList<Hoadon_DTO> search_for_IDDate(String id, String begin, String end) throws SQLException, ParseException {
        Hoadon_DAO hdDAO = new Hoadon_DAO();
        ArrayList<Hoadon_DTO> dshd = hdDAO.search_for_IDDate(id, begin, end);
        return dshd;
    }

    public ArrayList<Hoadon_DTO> search(ArrayList<String> data_filter) {
        ArrayList<Hoadon_DTO> re = new ArrayList<>();
        for (String i : data_filter) {
            for (Hoadon_DTO j : dshoadon) {
                String idHD = j.getMaHD();
                if (idHD.equalsIgnoreCase(i)) {
                    re.add(j);
                }
                int idKH = j.getMaKH();
                if (idKH == Integer.parseInt(i)) {
                    re.add(j);
                }
                String idNV = j.getMaNV();
                if (idNV.equalsIgnoreCase(i)) {
                    re.add(j);
                }
            }
        }
        return re;
    }

    public ArrayList<Hoadon_DTO> searchFillData(ArrayList<String> data_filter) {
        Hoadon_DAO hdDAO;
        try {
            hdDAO = new Hoadon_DAO();
            return hdDAO.searchFillData(data_filter);
        } catch (SQLException ex) {
            Logger.getLogger(Hoadon_BUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String createMAHD() {
        int max = 0;
        for (int i = 0; i < dshoadon.size(); i++) {
            String MAHDlast = dshoadon.get(i).getMaHD();
            String so = MAHDlast.replaceAll("[^0-9]", "");
            int stt = Integer.parseInt(so) + 1;
            if (stt > max) {
                max = stt;
            }
        }
        return "HD" + max;

    }

    public boolean addInSQL(Hoadon_DTO item) {
        try {
            Hoadon_DAO hdDAO = new Hoadon_DAO();
            return hdDAO.add(item);

        } catch (SQLException ex) {
            Logger.getLogger(Hoadon_BUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//     public static void main(String[] args) throws SQLException {
//        Hoadon_BUS cthd = new Hoadon_BUS();
//        Hoadon_DTO hd = new Hoadon_DTO("HD001","2023-08-13",3,"NV003",1260000,100000);
//        cthd.delete(hd);
}
