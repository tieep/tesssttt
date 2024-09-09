/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.SanPhamBUS;
import BUS.chitietsanpham_BUS;
import DTO.SizeDTO;
import DTO.chitietsanpham_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class SizeDAO {

    private ConnectDataBase c;

    public SizeDAO() {
        try {
            c = new ConnectDataBase();
        } catch (SQLException e) {
        }
    }

    public ArrayList<SizeDTO> listSizeRemoveTrangthai0() {
        ArrayList<SizeDTO> list = new ArrayList<>();

        try {
            c.connect();
            String query = "SELECT * FROM size";
            ResultSet result = c.executeQuery(query);
            while (result.next()) {
                if (result.getInt("TRANGTHAI") == 1) {
                    list.add(new SizeDTO(result.getString("MASIZE"), result.getString("TENSIZE")));
                }

            }

            c.disconnect();
        } catch (SQLException e) {
        }

        return list;
    }

    public ArrayList<SizeDTO> listSize() {
        ArrayList<SizeDTO> list = new ArrayList<>();

        try {
            c.connect();
            String query = "SELECT * FROM size";
            ResultSet result = c.executeQuery(query);
            while (result.next()) {

                list.add(new SizeDTO(result.getString("MASIZE"), result.getString("TENSIZE")));

            }

            c.disconnect();
        } catch (SQLException e) {
        }

        return list;
    }

    public void add(SizeDTO item) {
        try {
            c.connect();
            
            String query = "INSERT INTO size(MASIZE,TENSIZE) VALUES ('" + item.getMASIZE() + "','" + item.getTENSIZE() + "');";
            c.executeUpdate(query);
            c.disconnect();
        } catch (SQLException e) {
        }
    }

    public void update(SizeDTO item) {
        try {
            c.connect();

            String query = " UPDATE size SET TENSIZE='" + item.getTENSIZE() + "' WHERE MASIZE='" + item.getMASIZE() + "'";
            c.executeUpdate(query);
            c.disconnect();
        } catch (SQLException e) {
        }
    }

    public void delete(String m) {
        try {
            c.connect();
            chitietsanpham_BUS ctspBUS = new chitietsanpham_BUS();
            ArrayList<chitietsanpham_DTO> listCTSP =ctspBUS.getlist();
            boolean flag = true;
  
            for(chitietsanpham_DTO i : listCTSP){
                System.out.println("MASIZE "+i.getMASIZE());
                if(i.getMASIZE().equals(m)){
                     flag=false;
                     break;
                }
                   
            }
            String query="";
            if(!flag)
                query = "UPDATE size SET TRANGTHAI = 0 WHERE MASIZE = '" + m + "'";
            else
                query = "DELETE FROM size WHERE MASIZE = '"+m+"'";
            System.out.println("Cau query " +query);
            c.executeUpdate(query);
            c.disconnect();
        } catch (SQLException e) {
        }
    }

}
