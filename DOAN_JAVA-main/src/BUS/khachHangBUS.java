/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.khachHangDAO;
import DTO.khachHangDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class khachHangBUS {

    private ArrayList<khachHangDTO> ds_khachHang;
    private khachHangDAO dao = new khachHangDAO();

    public khachHangBUS() {
        ds_khachHang = new ArrayList<>();
        ds_khachHang = dao.ds_khachHang();
    }

    public ArrayList<khachHangDTO> getDs_khachHang() {
        return ds_khachHang;
    }

    private boolean kiemtra_Ten(String ten) {
        String regex = "^[a-zA-ZÀ-ỹ\\s]+$";
        return ten.matches(regex);
    }

    private boolean kiemtra_Sdt(String sdt) {
        String regex = "^0\\d{9}$";
        return sdt.matches(regex);
    }

    private boolean kiemtra_Diem(int diem) {
        String regex = "^\\d+$";
        String sdiem = diem + "";
        return sdiem.matches(regex);
    }

    public void themkh(khachHangDTO kh) {
        if (kiemtra_Ten(kh.getTenKH()) && kiemtra_Sdt(kh.getSoDienThoai())) {
            dao.themKH(kh);
            Object[] options = {"Đồng ý"};
            JOptionPane.showOptionDialog(null,
                    "Thêm khách hàng thành công",
                    "Thông báo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
        } else if (kiemtra_Ten(kh.getTenKH()) == false) {
            Object[] options = {"Đồng ý"};
            JOptionPane.showOptionDialog(null,
                    "Tên khách hàng không được chứa ký tự đặc biệt",
                    "Thông báo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[0]);
        } else if (kiemtra_Sdt(kh.getSoDienThoai()) == false) {
            Object[] options = {"Đồng ý"};
            JOptionPane.showOptionDialog(null,
                    "Số điện thoại bắt đầu bằng số 0 và chỉ chứa số",
                    "Thông báo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[0]);
        }
    }

    public void capnhatkh(khachHangDTO kh) {
        if (kiemtra_Diem(kh.getDiem()) && kiemtra_Ten(kh.getTenKH()) && kiemtra_Sdt(kh.getSoDienThoai())) {
            dao.capnhatKH(kh);
            Object[] options = {"Đồng ý"};
            JOptionPane.showOptionDialog(null,
                    "Cập nhật khách hàng thành công" + kh.getTenKH(),
                    "Thông báo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
        } else if (kiemtra_Ten(kh.getTenKH()) == false) {
            Object[] options = {"Đồng ý"};
            JOptionPane.showOptionDialog(null,
                    "Tên khách hàng không được chứa ký tự đặc biệt",
                    "Thông báo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[0]);
        } else if (kiemtra_Sdt(kh.getSoDienThoai()) == false) {
            Object[] options = {"Đồng ý"};
            JOptionPane.showOptionDialog(null,
                    "Số điện thoại bắt đầu bằng số 0 và chỉ chứa số",
                    "Thông báo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[0]);
        } else if (kiemtra_Diem(kh.getDiem()) == false) {
            Object[] options = {"Đồng ý"};
            JOptionPane.showOptionDialog(null,
                    "Chỉ chứa số dương",
                    "Thông báo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[0]);
        }
    }

    public void xoakhSql(int id) {
        Object[] options = {"Đồng ý"};
        for (khachHangDTO kh : ds_khachHang) {
            if (kh.getMaKH() == id) {
                dao.xoaKH(id);
//                JOptionPane.showOptionDialog(null,
//                        "Xóa khách hành thành công",
//                        "Thông báo",
//                        JOptionPane.DEFAULT_OPTION,
//                        JOptionPane.INFORMATION_MESSAGE,
//                        null,
//                        options,
//                        options[0]);
                return;
            }
        }
//        JOptionPane.showOptionDialog(null,
//                "Xóa khách hàng không thành công",
//                "Thông báo",
//                JOptionPane.DEFAULT_OPTION,
//                JOptionPane.INFORMATION_MESSAGE,
//                null,
//                options,
//                options[0]);
    }

    public void xoakh(int MALOAI) {
        for (int i = 0; i < ds_khachHang.size(); i++) {
            if (ds_khachHang.get(i).getMaKH() == MALOAI) {
                ds_khachHang.remove(i);
            }
        }
    }

    public ArrayList<khachHangDTO> search(ArrayList<String> data_filter) {
        ArrayList<khachHangDTO> re = new ArrayList<>();

        for (khachHangDTO kh : ds_khachHang) {
            // Kiểm tra tìm kiếm theo tên hoặc số điện thoại
            boolean checkTenKh = kh.getTenKH().toLowerCase().contains(data_filter.get(0).toLowerCase());
            boolean checkSdt = kh.getSoDienThoai().toLowerCase().equals(data_filter.get(0).toLowerCase());
            boolean isCheck = false;

            if (data_filter.get(0).equals("")) { // Nếu không có từ khóa tìm kiếm
                isCheck = true; // In ra tất cả khách hàng
            } else {
                isCheck = checkSdt || checkTenKh; // Tìm kiếm theo tên hoặc số điện thoại
            }

            if (isCheck) {
                re.add(kh);
            }
        }

        // Sắp xếp danh sách theo điều kiện lọc
        if (data_filter.size() > 1) {
            if (data_filter.get(1).equals("Thấp đến cao")) {
                re.sort(Comparator.comparingInt(khachHangDTO::getDiem)); // Sắp xếp tăng dần
            } else if (data_filter.get(1).equals("Cao đến thấp")) {
                re.sort(Comparator.comparingInt(khachHangDTO::getDiem).reversed()); // Sắp xếp giảm dần
            }
        }


        return re;
    }

    public ArrayList<khachHangDTO> sap_xep(String order) {
        return dao.sap_xep(order);
    }

    public static void main(String[] args) {
        khachHangBUS bus = new khachHangBUS();
        ArrayList<String> data_filter = new ArrayList<>();
        data_filter.add("Monkey");
        data_filter.add("");

        ArrayList<khachHangDTO> ds = bus.search(data_filter);
        for (khachHangDTO x : ds) {
            System.out.println("array "+x.toString());
        }
    }
}
