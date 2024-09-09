/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author hp
 */
import java.util.ArrayList;
import DTO.quyenDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
public class quyenDAO {
    private ConnectDataBase c;
    
     public  quyenDAO(){
        try{
             c = new ConnectDataBase();
        }catch(SQLException e){
        }
    }
     
     public ArrayList<quyenDTO> listQuyen(){
        ArrayList<quyenDTO> list = new ArrayList<>();
        try{
            c.connect();
            String query="SELECT * FROM quyen";
            try (ResultSet result = c.executeQuery(query)) {
                while(result.next()){
                    quyenDTO item = new quyenDTO(result.getString("MAQUYEN"),result.getString("TENQUYEN"));
                    list.add(item);
                }
            }
            c.disconnect();
        }catch(SQLException e){}
        return list;
    }
     
     public void add(quyenDTO item){
        try{
            c.connect();
            String query= "INSERT INTO quyen VALUES ('"+item.getMAQUYEN()+"','"+item.getTENQUYEN()+"');";
            c.executeUpdate(query);
            c.disconnect();
        }catch(SQLException e){}
    }
     
     public void delete(String maquyen){
        try{
            c.connect();
            String query = "DELETE FROM quyen WHERE MAQUYEN = '"+maquyen+"'";
            c.executeUpdate(query);
            c.disconnect();
        }catch(SQLException e){}
    }
     
        public void update(quyenDTO qNEW){
                    try {
            c.connect();

            String query = " UPDATE quyen SET TENQUYEN='" + qNEW.getTENQUYEN() + "' WHERE MAQUYEN='" + qNEW.getMAQUYEN()+ "'";
            c.executeUpdate(query);
            c.disconnect();
        } catch (SQLException e) {
        }
        }
//     
//     public static void main(String[] args) {
//        ArrayList<quyenDTO> list = new ArrayList<>();
//        quyenDAO c= new quyenDAO();
//        list=c.listQuyen();
//        for(quyenDTO i : list){
//            System.out.println(i.getMAQUYEN()+" "+i.getTENQUYEN());
//        }
//        
//        quyenDTO c_new= new quyenDTO("TEST", "THEMCHUCNANG");
//        c.delete(c_new);
//    }
}
