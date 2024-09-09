/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author hp
 */
import DTO.chitietquyenDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class chitietquyenDAO {
    private ConnectDataBase c;
    
    public chitietquyenDAO(){
        try{
             c = new ConnectDataBase();
        }catch(SQLException e){}
    }
    
    public ArrayList<chitietquyenDTO> listChitietquyen(){
        ArrayList<chitietquyenDTO> list = new ArrayList<>();
        try{
            c.connect();
            String query="SELECT * FROM chitietquyen";
            try (ResultSet result = c.executeQuery(query)) {
                while(result.next()){
                    chitietquyenDTO item = new chitietquyenDTO(result.getString("MAQUYEN"),result.getString("MACHUCNANG"),result.getString("HANHDONG"));
                    list.add(item);
                }
                result.close();
            }
            
            c.disconnect();
        }catch(SQLException e){}
        return list;
    }
    
    public void add(chitietquyenDTO item){
        try{
            c.connect();
            String query= "INSERT INTO chitietquyen VALUES ('"+item.getMAQUYEN()+"','"+item.getMACHUCNANG()+"','"+item.getHANHDONG()+"');";
            c.executeUpdate(query);
            c.disconnect();
        }catch(SQLException e){}
    }
    public ArrayList<chitietquyenDTO> executeQuery(String Query){
        ArrayList<chitietquyenDTO> list = new ArrayList<>();
        try{
            c.connect();
            try (ResultSet result = c.executeQuery(Query)) {
                while(result.next()){
                    chitietquyenDTO item = new chitietquyenDTO(result.getString("MAQUYEN"),result.getString("MACHUCNANG"),result.getString("HANHDONG"));
                    list.add(item);
                }
                result.close();
            }
            c.disconnect();
        }catch(SQLException E){}
        return list;
    }
    public boolean seach(chitietquyenDTO i){
        try{
             c.connect();
             String sql ="SELECT * FROM chitietquyen WHERE MAQUYEN='"+i.getMAQUYEN()+"' AND MACHUCNANG='"+i.getMACHUCNANG()+"' AND HANHDONG='"+i.getHANHDONG()+"'";
             try (ResultSet result = c.executeQuery(sql)) {
                 return result.next();
             }
        }catch(SQLException e){
             return false;
        }
    }
//    public boolean search(String where){
//        try{
//            c.connect();
//            String query= "SELECT * FROM chitietquyen "+where;
//            try (ResultSet result = c.executeQuery(query)) {
////                c.disconnect();
//                
//                return result.next();
//            }
//       }catch(SQLException e){
//           
//            return false;// goi ham search bi loi
//        }
//        
//    }
    
    public ArrayList<chitietquyenDTO> searchReturnArray(String sql){
        ArrayList<chitietquyenDTO> list = new ArrayList<>();
        try{
            c.connect();
            
            try (ResultSet result = c.executeQuery(sql)) {
                
                  while(result.next()){
                      list.add(new chitietquyenDTO(result.getString("MAQUYEN"), result.getString("MACHUCNANG"), result.getString("HANHDONG")));
                  }
                  c.disconnect();
                result.close();
                
            }
       }catch(SQLException e){}
        return list;
    }
    public void delete(chitietquyenDTO item){
        try{
            c.connect();
            String query = "DELETE FROM chitietquyen WHERE MACHUCNANG = '"+item.getMACHUCNANG()+"' AND MAQUYEN='"+item.getMAQUYEN()+"' AND HANHDONG='"+item.getHANHDONG()+"'";
            c.executeUpdate(query);
            c.disconnect();
            System.out.println("Xoa chitietquyen thanh cong");
        }catch(SQLException e){
            System.out.println("Xoa chitietquyen  that bai");
        }
    }
    
    
    
}
