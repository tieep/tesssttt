/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author hp
 */
public class loaiSP {

    private String MALOAI;
    private String TENLOAI;
    private int TINHTRANG;

    public loaiSP(String MALOAI, String TENLOAI) {
        this.MALOAI = MALOAI;
        this.TENLOAI = TENLOAI;
        this.TINHTRANG = 1;
    }
    
    public loaiSP( String TENLOAI){
         this.MALOAI = "";
        this.TENLOAI = TENLOAI;
        this.TINHTRANG = 1;
    }
    
    public loaiSP(String MALOAI, String TENLOAI,int TINHTRANG) {
        this.MALOAI = MALOAI;
        this.TENLOAI = TENLOAI;
        this.TINHTRANG = TINHTRANG;
    }
    
    public void setMALOAI(String s){
        MALOAI=s;
    }
    
    public void setTENLOAI(String s){
        TENLOAI=s;
    }
    
    public void setTINHTRANG(int s){
        TINHTRANG=s;
    }
    
    public String getMALOAI(){
        return MALOAI;
    }
    
    public String getTENLOAI(){
        return TENLOAI;
    }
    
     public int getTINHTRANG(){
         return TINHTRANG;
     }
 public  String toString(){
     return MALOAI+" "+TENLOAI+" "+((TINHTRANG==0)?"Ngừng bán":"Đang bán");
 }
   
}
