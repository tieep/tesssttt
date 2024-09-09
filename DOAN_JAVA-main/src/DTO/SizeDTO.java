/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author hp
 */
public class SizeDTO {
    private String MASIZE;
    private String TENSIZE;
    
    public SizeDTO(String ma, String ten){
        MASIZE = ma;
        TENSIZE = ten;
        
    }
    public SizeDTO(String ten){
        MASIZE = "";
        TENSIZE = ten;
       
    }
    
    public void setMASIZE(String s){
        MASIZE=s;
    }
    
    public void setTENSIZE(String s){
        TENSIZE=s;
    }
   
    
    public String getMASIZE(){
        return MASIZE;
    }
    
    public String getTENSIZE(){
        return TENSIZE;
    }
   
 
     public boolean equals(SizeDTO sizeDTOnew){
         if(MASIZE.equals(sizeDTOnew.getMASIZE()) && TENSIZE.equals(sizeDTOnew.getTENSIZE())) return true;
         return false;
     }
}
