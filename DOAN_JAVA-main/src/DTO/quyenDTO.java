/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author hp
 */
public class quyenDTO {
    private String MAQUYEN;
    private String TENQUYEN;
    
    public quyenDTO(String m,String t){
        MAQUYEN=m;
        TENQUYEN=t;
    }
    public quyenDTO(String toString){
        String []s= toString.split(",");
        MAQUYEN=s[0];
        TENQUYEN=s[1];
    }
    public String getMAQUYEN(){
        return MAQUYEN;
    }
    public void setMAQUYEN(String s){
        MAQUYEN=s;
    }
    public String getTENQUYEN(){
        return TENQUYEN;
    }
    public void setTENQUYEN(String s){
        TENQUYEN=s;
    }
    @Override
    public String toString(){
        return MAQUYEN+","+TENQUYEN;
    }
    public static void main(String[] args) {
        String m="QQL,QUYỀN QUẢN LÍ";
        quyenDTO k= new quyenDTO(m);
        System.out.println(k.getMAQUYEN());
        System.out.println(k.getTENQUYEN());
    }
    
}
