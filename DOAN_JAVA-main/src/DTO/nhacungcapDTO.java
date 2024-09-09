/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author hp
 */
public class nhacungcapDTO {
    private String MANCC;
    private String TENNCC;
    private int SDT;
    
    public nhacungcapDTO(String ma, String ten, int s){
        MANCC = ma;
        TENNCC = ten;
        SDT = s;
    }
    public nhacungcapDTO(String ten,String sdt){
        MANCC = "";
        TENNCC = ten;
        SDT = Integer.parseInt(sdt);
    }
    
    public void setMANCC(String s){
        MANCC=s;
    }
    
    public void setTENNCC(String s){
        TENNCC=s;
    }
    
    public void setSDT(int s){
        SDT=s;
    }
    
    public String getMANCC(){
        return MANCC;
    }
    
    public String getTENNCC(){
        return TENNCC;
    }
    
     public int getSDT(){
         return SDT;
     }
 
     public boolean equals(nhacungcapDTO nccDTOnew){
         if(MANCC.equals(nccDTOnew.getMANCC()) && TENNCC.equals(nccDTOnew.getTENNCC()) && SDT == nccDTOnew.getSDT()) return true;
         return false;
     }
}
