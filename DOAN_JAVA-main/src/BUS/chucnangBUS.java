/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;
import DTO.chucnangDTO;
import DAO.chucnangDAO;
/**
 *
 * @author hp
 */
public class chucnangBUS {
    private ArrayList<chucnangDTO> list;
    
    public chucnangBUS(){
        list = new ArrayList<>();
        init();
    }
    
    private void init(){
        chucnangDAO dao = new chucnangDAO();
        list = dao.listChucNang();
    }
    
    public  ArrayList<chucnangDTO> getList(){
        return list;
    }
}
