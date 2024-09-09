/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUI.ThongTinTaiKhoan;
import DTO.chucnangDTO;
import DTO.quyenDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author hp
 */
public class chucnangTaikhoan extends JPanel implements MouseListener{
    TaiKhoanGUI tkGUI;
    private int ccao, crong;
    private  JPanel JP_listNameChucnangConCuaTaikhoan;
    private ArrayList<chucnangDTO> listChucnangCon;
    public JPanel JP_contentCuaNameChucnangCon;
    private CenterContentStore centerContent;
    public chucnangTaikhoan(CenterContentStore centerContent,chucnangDTO cnDTO,String maquyen) throws SQLException {
        this.centerContent=centerContent;
        ccao = (int)centerContent.pageContent.getPreferredSize().getHeight();
        crong = (int)centerContent.pageContent.getPreferredSize().getWidth();
        listChucnangCon = new ArrayList<>();
        
        init(cnDTO,maquyen);
    }
    private void init(chucnangDTO cnDTO,String maquyen) throws SQLException {
        listChucnangCon.add(new chucnangDTO("NULLTK","NULLTEN"));
        listChucnangCon.add(new chucnangDTO("TK","Tài khoản"));
        JP_listNameChucnangConCuaTaikhoan = new JPanel(new FlowLayout(3));
        for(chucnangDTO i: listChucnangCon){
            JPanel btn_taikhoan = new JPanel(new BorderLayout());
            JLabel title_taikhoan;
            if(i.getTENCHUCNANG().contains("NULL"))
                title_taikhoan = new JLabel("Tài khoản cá nhân", JLabel.CENTER);
            else
                 title_taikhoan = new JLabel("Tài khoản người dùng khác", JLabel.CENTER);
            btn_taikhoan.setPreferredSize(new Dimension((int) title_taikhoan.getPreferredSize().getWidth() + 50, (int) title_taikhoan.getPreferredSize().getHeight() + 20));
            btn_taikhoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            if(i.getTENCHUCNANG().equals(cnDTO.getTENCHUCNANG())){
                title_taikhoan.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
            btn_taikhoan.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
            btn_taikhoan.setOpaque(true);
            }
            else{
                 title_taikhoan.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
            btn_taikhoan.setBackground(Cacthuoctinh_phuongthuc_chung.second_gray);
            btn_taikhoan.setOpaque(true);
            }
            

            title_taikhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
            btn_taikhoan.setName(i.toString());
            btn_taikhoan.add(title_taikhoan, BorderLayout.CENTER);
            btn_taikhoan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPanel btn_clicked = (JPanel) e.getSource();
                   chucnangDTO cn= new chucnangDTO(btn_clicked.getName());
                    try {
                        centerContent.changeCenterContent(cn, maquyen);
                    } catch (SQLException ex) {
                        Logger.getLogger(chucnangTaikhoan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            
             JP_listNameChucnangConCuaTaikhoan.add(btn_taikhoan);
        }
        JP_listNameChucnangConCuaTaikhoan.setPreferredSize(new Dimension(crong,(int)JP_listNameChucnangConCuaTaikhoan.getPreferredSize().getHeight()));
           JP_listNameChucnangConCuaTaikhoan.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
        JP_listNameChucnangConCuaTaikhoan.setOpaque(true);
        
        JP_contentCuaNameChucnangCon= new JPanel();
        //JP_contentCuaNameChucnangCon.setPreferredSize(new Dimension(crong,ccao - (int)JP_listNameChucnangConCuaTaikhoan.getPreferredSize().getHeight()));
        JP_contentCuaNameChucnangCon.setBackground(Color.WHITE);
        JP_contentCuaNameChucnangCon.setOpaque(true);
        int heightJP_content=ccao - (int)JP_listNameChucnangConCuaTaikhoan.getPreferredSize().getHeight();
        switch (cnDTO.getMACHUCNANG()) {
            case "NULLTK":
                ThongTinTaiKhoan tttk;
            try {
                tttk = new ThongTinTaiKhoan(centerContent.SS_main, crong,heightJP_content, JP_contentCuaNameChucnangCon.getBackground());
                 tttk.initThaoTac_macdinh();
                JP_contentCuaNameChucnangCon.add(tttk);
            } catch (SQLException ex) {
                Logger.getLogger(chucnangTaikhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
               
//                ThongTinTaiKhoan tttk = new ThongTinTaiKhoan(centerContent.SS_main, crong,heightJP_content, JP_contentCuaNameChucnangCon.getBackground());
               
                break;
            case "TK":
//                TrangTaiKhoan tk = new TrangTaiKhoan(widthPageContent,600);
                 tkGUI = new TaiKhoanGUI(crong-20,heightJP_content-20);
//                JScrollPane tk_scrollPane = new JScrollPane(tkGUI);
//                tk_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                JP_contentCuaNameChucnangCon.add(tkGUI);
                break;
        }
        
        
        setLayout(new FlowLayout(3,0,0));
        add(JP_listNameChucnangConCuaTaikhoan);
        add(JP_contentCuaNameChucnangCon);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//     public static void main(String[] args) {
//        int cao = 600, rong = 1000;
//        chucnangTaikhoan h = new chucnangTaikhoan(rong, cao);
//        JFrame frame = new JFrame("JTable Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//
//        frame.add(h, BorderLayout.CENTER);
//
//        frame.setVisible(true);
//    }
}
