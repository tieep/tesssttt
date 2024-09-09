/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.khachHangDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class khachHangDAO {

    private ConnectDataBase c;

    public khachHangDAO() {
        try {
            c = new ConnectDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<khachHangDTO> ds_khachHang() {
        ArrayList<khachHangDTO> ds = new ArrayList<>();

        try {
            c.connect();
            String sql = "SELECT * FROM KHACHHANG";
            ResultSet rs = c.executeQuery(sql);
            while (rs.next()) {
                khachHangDTO kh = new khachHangDTO(rs.getInt("MAKH"),
                        rs.getString("TENKH"),
                        rs.getString("SDT"),
                        rs.getInt("DIEMTICHLUY"));
                ds.add(kh);
            }
            c.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public void themKH(khachHangDTO kh) {
        try {
            c.connect();
            String sql = "INSERT INTO KHACHHANG(TENKH,SDT,DIEMTICHLUY) "
                    + "VALUES('" + kh.getTenKH() + "','" + kh.getSoDienThoai() + "','" + kh.getDiem() + "')";
            c.executeUpdate(sql);
            c.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void capnhatKH(khachHangDTO kh) {
        try {
            c.connect();
            String sql = "UPDATE KHACHHANG"
                    + " SET TENKH='" + kh.getTenKH() + "', SDT='" + kh.getSoDienThoai() + "', DIEMTICHLUY='" + kh.getDiem() + "'"
                    + "WHERE MAKH='" + kh.getMaKH() + "'";
            c.executeUpdate(sql);
            c.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void xoaKH(int id) {
        try {
            c.connect();
            String sql = "DELETE FROM KHACHHANG WHERE MAKH=" + id;
            c.executeUpdate(sql);
            c.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<khachHangDTO> tim_kiem(String key) {
        ArrayList<khachHangDTO> ds = new ArrayList<>();

        try {
            c.connect();
            String sql = "SELECT * FROM KHACHHANG WHERE TENKH = '" + key + "' OR SDT = '" + key + "'";
            ResultSet rs = c.executeQuery(sql);
            while (rs.next()) {
                khachHangDTO kh = new khachHangDTO(rs.getInt("MAKH"),
                        rs.getString("TENKH"),
                        rs.getString("SDT"),
                        rs.getInt("DIEMTICHLUY"));
                ds.add(kh);
            }
            c.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ds;
    }
    
    public ArrayList<khachHangDTO> sap_xep(String order) {
        ArrayList<khachHangDTO> ds = new ArrayList<>();

        try {
            c.connect();
             String sql = "SELECT * FROM KHACHHANG ORDER BY DIEMTICHLUY " + order;
            ResultSet rs = c.executeQuery(sql);
            while (rs.next()) {
                khachHangDTO kh = new khachHangDTO(rs.getInt("MAKH"),
                        rs.getString("TENKH"),
                        rs.getString("SDT"),
                        rs.getInt("DIEMTICHLUY"));
                ds.add(kh);
            }
            c.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ds;
    }

    public static void main(String[] args) {
        khachHangDAO dao = new khachHangDAO();
        ArrayList<khachHangDTO> ds = dao.sap_xep("ASC");
        for (khachHangDTO c : ds) {
            System.out.println(c.getTenKH() + "     " + c.getDiem());
        }
    }
}
