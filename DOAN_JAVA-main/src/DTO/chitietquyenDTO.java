/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author hp
 */
public class chitietquyenDTO {

    private String MAQUYEN;
    private String MACHUCNANG;
    private String HANHDONG;

    public chitietquyenDTO(String q, String cn, String hd) {
        MAQUYEN = q;
        MACHUCNANG = cn;
        HANHDONG = hd;
    }
public chitietquyenDTO(String s){
        String []arr_s=s.split(",");
        MAQUYEN=arr_s[0];
        MACHUCNANG=arr_s[1];
        HANHDONG=arr_s[2];
    }
    public void setMAQUYEN(String q) {
        MAQUYEN = q;
    }

    public void setMACHUCNANG(String cn) {
        MACHUCNANG = cn;
    }

    public void setHANHDONG(String hd) {
        HANHDONG = hd;
    }

    public String getMAQUYEN() {
        return MAQUYEN;
    }

    public String getMACHUCNANG() {
        return MACHUCNANG;
    }

    public String getHANHDONG() {
        return HANHDONG;
    }
    public String toString(){
        return MAQUYEN+","+MACHUCNANG+","+HANHDONG;
    }
}
