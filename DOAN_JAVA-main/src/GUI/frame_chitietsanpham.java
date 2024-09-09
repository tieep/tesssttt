/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.SanPhamDTO;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author LENOVO
 */
public class frame_chitietsanpham extends JFrame{
       public frame_chitietsanpham(SanPhamDTO h) throws SQLException{
           this.setSize(600,450);
           this.setLocationRelativeTo(null);
           this.setUndecorated(true);
           view_chi_tiet_san_pham k = new view_chi_tiet_san_pham(h,this);
           this.add(k);
           
           this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           this.setVisible(true);
       }
       
}
