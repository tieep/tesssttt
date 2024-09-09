/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import DTO.chucnangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author hp
 */
public class chucnangDAO {
    private ConnectDataBase c;
    
    public chucnangDAO(){
        try{
             c = new ConnectDataBase();
        }catch(SQLException e){
            System.out.println("That bai");
        }
       
    }
    
    public ArrayList<chucnangDTO> listChucNang(){
        ArrayList<chucnangDTO> list = new ArrayList<>();
        try{
            c.connect();
            String query="SELECT * FROM chucnang";
            try (ResultSet result = c.executeQuery(query)) {
                while(result.next()){
                    chucnangDTO item = new chucnangDTO(result.getString("MACHUCNANG"),result.getString("TENCHUCNANG"));
                    list.add(item);
                }
            }
            System.out.println("Lay danh sach chuc nang thanh cong");
            c.disconnect();
        }catch(SQLException e){
            System.out.println("Lay danh sach chuc nang that bai");
        }
        return list;
    }
    
    public void add(chucnangDTO item){
        try{
            c.connect();
            String query= "INSERT INTO chucnang VALUES ('"+item.getMACHUCNANG()+"','"+item.getTENCHUCNANG()+"');";
            c.executeUpdate(query);
            System.out.println("Them chuc nang thanh cong");
            c.disconnect();
        }catch(SQLException e){
            System.out.println("Them chuc nang that bai");
        }
    }
    public chucnangDTO search(String x){
        try{
            c.connect();
            
            String query= "SELECT * FROM chucnang WHERE MACHUCNANG='"+x+"' OR TENCHUCNANG='"+x+"'";
            ResultSet re = c.executeQuery(query);
            while(re.next()){
                return new chucnangDTO(re.getString("MACHUCNANG"),re.getString("TENCHUCNANG"));
            }
            c.disconnect();
        }catch(SQLException e){
        }
        return null;
    }
    
    public void delete(chucnangDTO item){
        try{
            c.connect();
            String query = "DELETE FROM chucnang WHERE MACHUCNANG = '"+item.getMACHUCNANG()+"'";
            c.executeUpdate(query);
            c.disconnect();
            System.out.println("Xoa chuc nang thanh cong");
        }catch(SQLException e){
            System.out.println("Xoa chuc nang that bai");
        }
    }
    
    public static void main(String[] args) {
//        ArrayList<chucnangDTO> list = new ArrayList<>();
        chucnangDAO c= new chucnangDAO();
//        list=c.listChucNang();
//        for(chucnangDTO i : list){
//            System.out.println(i.getMACHUCNANG()+" "+i.getTENCHUCNANG());
//        }
//        
        chucnangDTO c_new= c.search("Quản lý tài khoản");
        System.out.println(c_new.getMACHUCNANG());
        //c.delete(c_new);
    }
    
}
