/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author hp
 */
public class chucnangDTO {
    private String MACHUCNANG;
    private String TENCHUCNANG;
    
    public chucnangDTO(String m,String t){
        MACHUCNANG=m;
        TENCHUCNANG=t;
    }
    public chucnangDTO(String s){
        String []arr_s=s.split(",");
        MACHUCNANG=arr_s[0];
        TENCHUCNANG=arr_s[1];
    }
    public String getMACHUCNANG(){
        return MACHUCNANG;
    }
    public void setMACHUCNANG(String s){
        MACHUCNANG=s;
    }
    public String getTENCHUCNANG(){
        return TENCHUCNANG;
    }
    public void setTENCHUCNANG(String s){
        TENCHUCNANG=s;
    }
    public String toString(){
        return MACHUCNANG+","+TENCHUCNANG;
    }
    public boolean equals(chucnangDTO obj) {
        if(this.MACHUCNANG.equals(obj.getMACHUCNANG()) && this.TENCHUCNANG.equals(obj.getTENCHUCNANG())) return true;
        return false;
    }
}
