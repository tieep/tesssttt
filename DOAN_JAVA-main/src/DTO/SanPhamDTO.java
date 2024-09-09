package DTO;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

public class SanPhamDTO {

    private String maSP, maLoai, tenSP;
    private double price;
    public String[] tenHinh;
    private int soLuong, trangThai;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String maSP, String maLoai, String tenSP, double price, String[] tenHinh, int trangThai) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.tenSP = tenSP;
        this.price = price;
        this.tenHinh = tenHinh;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String[] getTenHinh() {
        return tenHinh;
    }

    @Override
    public String toString() {
        return "SanPhamDTO [maSP="+maSP + ", maLoai="+maLoai + ", tenSP="+ tenSP
                + ", price=" + price + ", tenHinh=" + Arrays.toString(tenHinh)
                + ", trangThai=" + trangThai + "]";
    }

    public void setTenHinh(String[] tenHinh) {
        this.tenHinh = tenHinh;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
