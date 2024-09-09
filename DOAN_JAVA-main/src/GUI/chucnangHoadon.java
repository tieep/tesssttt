/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import BUS.chitietquyenBUS;
import DTO.chitietquyenDTO;
import DTO.chucnangDTO;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author hp
 */
public class chucnangHoadon extends JPanel implements MouseListener {

    private int ccao;
    private JPanel JP_listNameChucnangConCuaHoadon;
    private ArrayList<chucnangDTO> listChucnangCon;
    JPanel JP_contentCuaNameChucnangCon;
    private CenterContentStore centerContent;
    private int crong, heightJP_content;

    public chucnangHoadon(CenterContentStore centerContent, chucnangDTO cnDTO, String maquyen, String maNV) throws SQLException {
        this.centerContent = centerContent;
        ccao = (int) centerContent.pageContent.getPreferredSize().getHeight();
        crong = (int) centerContent.pageContent.getPreferredSize().getWidth();
        listChucnangCon = new ArrayList<>();

        init(cnDTO, maquyen,maNV);
    }

    public int getCrong() {
        return crong;
    }

    public int getHeightJPContent() {
        return heightJP_content;
    }

    private void init(chucnangDTO cnDTO, String maquyen, String maNV) throws SQLException {
        chitietquyenBUS ctqBUS = new chitietquyenBUS();
        if (ctqBUS.search(new chitietquyenDTO(maquyen, "HD", "Thêm"))) {
            listChucnangCon.add(new chucnangDTO("NULLHD", "NULLTEN"));
        }

        listChucnangCon.add(new chucnangDTO("HD", "Hóa đơn"));
        JP_listNameChucnangConCuaHoadon = new JPanel(new FlowLayout(3));
        for (chucnangDTO i : listChucnangCon) {
            JPanel btn_taikhoan = new JPanel(new BorderLayout());
            JLabel title_taikhoan;
            if (i.getTENCHUCNANG().contains("NULL")) {
                title_taikhoan = new JLabel("Hóa đơn chưa thanh toán", JLabel.CENTER);
            } else {
                title_taikhoan = new JLabel("Lịch sử hóa đơn", JLabel.CENTER);
            }
            btn_taikhoan.setPreferredSize(new Dimension((int) title_taikhoan.getPreferredSize().getWidth() + 50, (int) title_taikhoan.getPreferredSize().getHeight() + 20));
            btn_taikhoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            if (i.getMACHUCNANG().equals(cnDTO.getMACHUCNANG())) {
                title_taikhoan.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                btn_taikhoan.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                btn_taikhoan.setOpaque(true);
            } else {
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

                    chucnangDTO cn = new chucnangDTO(btn_clicked.getName());

                    try {
                        centerContent.changeCenterContent(cn, maquyen);
                    } catch (SQLException ex) {
                        Logger.getLogger(chucnangHoadon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

            JP_listNameChucnangConCuaHoadon.add(btn_taikhoan);
        }
        JP_listNameChucnangConCuaHoadon.setPreferredSize(new Dimension(crong, (int) JP_listNameChucnangConCuaHoadon.getPreferredSize().getHeight()));
        JP_listNameChucnangConCuaHoadon.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
        JP_listNameChucnangConCuaHoadon.setOpaque(true);

        JP_contentCuaNameChucnangCon = new JPanel(new BorderLayout(0,0));
        //JP_contentCuaNameChucnangCon.setPreferredSize(new Dimension(crong,ccao - (int)JP_listNameChucnangConCuaTaikhoan.getPreferredSize().getHeight()));
        JP_contentCuaNameChucnangCon.setBackground(Color.WHITE);
        JP_contentCuaNameChucnangCon.setOpaque(true);
        int heightJP_content = ccao - (int) JP_listNameChucnangConCuaHoadon.getPreferredSize().getHeight();

        switch (cnDTO.getMACHUCNANG()) {
            case "NULLHD":
//                ShoppingCartUI p = new ShoppingCartUI(crong, heightJP_content,
//                        view_chi_tiet_san_pham.dssptt, view_chi_tiet_san_pham.dsctsptt, view_chi_tiet_san_pham.soluong,
//                        view_chi_tiet_san_pham.maSizeThem, maNV);
                // p.setPreferredSize(new Dimension(crong,heightJP_content));
                JP_contentCuaNameChucnangCon.setBackground(Color.black);
                CartGUI p = new CartGUI(crong, heightJP_content);
                JP_contentCuaNameChucnangCon.add(p,BorderLayout.CENTER);
                break;
            case "HD":
                TrangLichsuHD hdGUI = new TrangLichsuHD(crong, heightJP_content);
                JP_contentCuaNameChucnangCon.add(hdGUI,BorderLayout.CENTER);
                break;
        }

        setLayout(new FlowLayout(3, 0, 0));
        add(JP_listNameChucnangConCuaHoadon);
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
}
