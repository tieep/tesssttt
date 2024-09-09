package GUI;

import BUS.chitietquyenBUS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridBagConstraints;
import java.awt.Component;
import DTO.chitietquyenDTO;

import DAO.ConnectDataBase;
import java.sql.*;
import BUS.chucnangBUS;
import DAO.chitietquyenDAO;
import DAO.chucnangDAO;
import DTO.chucnangDTO;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MenuChucNangStore extends JPanel implements MouseListener {

    private StoreScreen SS_main;
    private JLabel JL_nameStaff;
    private ArrayList<chucnangDTO> cnDTO_listByMAQUYEN;
    private ArrayList<String> String_listNameChucnang;
    private Font font_chucnang;
    private JPanel JP_inforNhanvien;
    public JPanel JP_includeChucnangMenu;
    private int chieurong, chieucao;
    private int int_selectedItem;
    private String MAQUYEN;
    private JPanel JP_selected;

    public MenuChucNangStore(String maquyen, int chieurong, int chieucao, StoreScreen s) {
        SS_main = s;
        this.MAQUYEN = maquyen;
        this.chieurong = chieurong;
        this.chieucao = chieucao;
        int_selectedItem = -1;

        init();

    }

    public void init() {
        setPreferredSize(new Dimension(chieurong, 0));
        setLayout(new BorderLayout());
        setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        setOpaque(true);

        JP_inforNhanvien = new JPanel();
        JP_inforNhanvien.setLayout(new FlowLayout(1, 0, 15));
        JP_inforNhanvien.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        JP_inforNhanvien.setOpaque(true);
        JP_inforNhanvien.setPreferredSize(new Dimension(chieurong, chieurong + chieurong / 3));

        //ép kích thuoc anh
        Image originalImage = Cacthuoctinh_phuongthuc_chung.storeLogoLogin.getImage();

        // Kích thước mới bạn muốn
        int newWidth = 170;
        int newHeight = 170;

        // Thay đổi kích thước của hình ảnh
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        JLabel logoStore = new JLabel(new ImageIcon(resizedImage));
        logoStore.setPreferredSize(new Dimension(chieurong, chieurong));
        logoStore.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        logoStore.setOpaque(true);
        JP_inforNhanvien.add(logoStore);

        JL_nameStaff = new JLabel(SS_main.tkUSER.getUsername());
        JL_nameStaff.setFont(Cacthuoctinh_phuongthuc_chung.font_header);
        JL_nameStaff.setForeground(Cacthuoctinh_phuongthuc_chung.light_gray);
        JP_inforNhanvien.add(JL_nameStaff);

        add(JP_inforNhanvien, BorderLayout.NORTH);

        repaintMenu();

        JPanel south = new JPanel();
        south.setPreferredSize(new Dimension(chieurong, 20));
        south.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        south.setOpaque(true);
        add(south, BorderLayout.SOUTH);

    }

    private void repaintMenu() {
        cnDTO_listByMAQUYEN = lístChucnang(this.MAQUYEN);
        if (cnDTO_listByMAQUYEN.size() < 9) {

            JP_includeChucnangMenu = new JPanel(new FlowLayout());
        } else {

            JP_includeChucnangMenu = new JPanel(new GridLayout(0, 1));
        }

        JP_includeChucnangMenu.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        JP_includeChucnangMenu.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(JP_includeChucnangMenu);
        scrollPane.setBorder(null);

        for (int i = 0; i < cnDTO_listByMAQUYEN.size(); i++) {
            String nameIcon = "./src/images/";
            switch (cnDTO_listByMAQUYEN.get(i).getTENCHUCNANG()) {
                case "Nhà cung cấp":
                    nameIcon += "nhacungcap_icon.png";
                    break;
                case "Tài khoản cá nhân":
                    nameIcon += "user_icon.png";
                    break;
                case "Sản phẩm":
                    nameIcon += "shirt_icon.png";
                    break;
                case "Hoá đơn":
                    nameIcon += "bill_icon.png";
                    break;
                case "Nhân viên":
                    nameIcon += "staff_icon.png";
                    break;
                case "Tài khoản":
                    nameIcon += "account_icon.png";
                    break;
                case "Khách hàng":
                    nameIcon += "customer_icon.png";
                    break;
                case "Loại":
                    nameIcon += "loaiSP_icon.png";
                    break;
                case "Size":
                    nameIcon += "size_icon.png";
                    break;
                case "Phiếu nhập":
                    nameIcon += "receipt_icon.png";
                    break;
                case "Phân quyền":
                    nameIcon += "phanquyen_icon.png";
                    break;
                case "Thống kê":
                    nameIcon += "thongke_icon.png";
                    break;
                case "Đăng xuất":
                    nameIcon += "signout_icon.png";
                    break;
                case "Kho":
                    nameIcon += "icons8-warehouse-24.png";
                    break;
            }
            JPanel chucnang;
            if (cnDTO_listByMAQUYEN.size() < 9) {

                JP_includeChucnangMenu.setPreferredSize(new Dimension(chieurong, 40));
                chucnang = new chucnangGUI(cnDTO_listByMAQUYEN.get(i).getTENCHUCNANG(), nameIcon, (int) JP_includeChucnangMenu.getPreferredSize().getWidth(), (int) JP_includeChucnangMenu.getPreferredSize().getHeight());
            } else {
                chucnang = new chucnangGUI(cnDTO_listByMAQUYEN.get(i).getTENCHUCNANG(), nameIcon, (int) scrollPane.getPreferredSize().getWidth(), 40);
            }
            chucnang.setName(cnDTO_listByMAQUYEN.get(i).toString());
            chucnang.addMouseListener(this);

            JP_includeChucnangMenu.add(chucnang);

        }
        // JScrollPane scrollListChucnang= new JScrollPane(listChucnangMenu);
        //scrollListChucnang.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Luôn hiển thị thanh cuộn dọc
        //add(scrollListChucnang);
        add(scrollPane, BorderLayout.CENTER);

    }

    public ArrayList<chucnangDTO> lístChucnang(String MAQUYEN) {
        String sql = "SELECT * FROM chitietquyen WHERE MAQUYEN='" + MAQUYEN + "' AND HANHDONG='Xem'";
        chitietquyenDAO ctqDAO = new chitietquyenDAO();
        ArrayList<chitietquyenDTO> listChitietQuyen = ctqDAO.executeQuery(sql);
        ArrayList<chucnangDTO> listChucnang = new ArrayList<>();

        for (chitietquyenDTO i : listChitietQuyen) {
            chucnangDAO cnDAO = new chucnangDAO();
            chucnangDTO k = cnDAO.search(i.getMACHUCNANG());
            boolean flag_tontai = false;
            for (chucnangDTO j : listChucnang) {
                if (j.equals(k)) {
                    flag_tontai = true;
                    break;
                }
            }
            if (!flag_tontai) {
                listChucnang.add(k);
            }
        }
        listChucnang.add(new chucnangDTO("NULLDX", "Đăng xuất"));
        return listChucnang;
    }

    public ArrayList<String> lístNameChucnang(String MAQUYEN) {
        String sql = "SELECT * FROM chitietquyen WHERE MAQUYEN='" + MAQUYEN + "'  AND HANHDONG='Xem'";
        chitietquyenDAO ctqDAO = new chitietquyenDAO();
        ArrayList<chitietquyenDTO> listChitietQuyen = ctqDAO.executeQuery(sql);
        ArrayList<String> listNameChucnang = new ArrayList<>();

        for (chitietquyenDTO i : listChitietQuyen) {
            chucnangDAO cnDAO = new chucnangDAO();
            String name = cnDAO.search(i.getMACHUCNANG()).getTENCHUCNANG();
            if (!listNameChucnang.contains(name)) {
                listNameChucnang.add(name);
            }
        }
        listNameChucnang.add("Đăng xuất");
        return listNameChucnang;
    }

    public void changeColorJPanelChildFromParent(JPanel parent, int index, Color bg, Color fg) {
        Component[] components = parent.getComponents();
        for (int i = 0; i < components.length; i++) {
            JPanel p = (JPanel) components[i];
            if (i == index) {
                changeColorJPanelChild(p, bg, fg);
            }
        }
    }

    public void changeColorJPanelChild(JPanel p, Color bg, Color fg) {
        p.setBackground(bg);
        p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Component[] components = p.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;

                label.setForeground(fg);

            }
        }
        MouseListener[] mouseListeners = p.getMouseListeners();
        for (MouseListener listener : mouseListeners) {
            p.removeMouseListener(listener);
        }

    }

    public int findSelectedItem(JPanel find, JPanel parent) {
        Component[] components = parent.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] == find) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (JP_selected != null) {
            changeColorJPanelChild(JP_selected, Cacthuoctinh_phuongthuc_chung.darkness_blue, Cacthuoctinh_phuongthuc_chung.sky_blue);
            JP_selected.addMouseListener(this);
        }

        JPanel itemChucnang = (JPanel) e.getSource();
        changeColorJPanelChild(itemChucnang, Cacthuoctinh_phuongthuc_chung.sky_blue, Cacthuoctinh_phuongthuc_chung.darkness_blue);
        JP_selected = itemChucnang;

        chucnangDTO cnSelelect = new chucnangDTO(itemChucnang.getName());
        switch (cnSelelect.getMACHUCNANG()) {
            case "TK": {
               
            try {
                SS_main.centerContent.changeCenterContent(new chucnangDTO("NULL" + cnSelelect.getMACHUCNANG(), "NULLTEN"), MAQUYEN);
            } catch (SQLException ex) {
                Logger.getLogger(MenuChucNangStore.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
            break;

            case "HD":
                chitietquyenBUS ctqBUS = new chitietquyenBUS();
                if (ctqBUS.search(new chitietquyenDTO(MAQUYEN, "HD", "Thêm")))
                   try {
                    SS_main.centerContent.changeCenterContent(new chucnangDTO("NULL" + cnSelelect.getMACHUCNANG(), "NULLTEN"), MAQUYEN);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuChucNangStore.class.getName()).log(Level.SEVERE, null, ex);
                } else
                   try {
                    SS_main.centerContent.changeCenterContent(cnSelelect, MAQUYEN);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuChucNangStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default: {
                try {
                    SS_main.centerContent.changeCenterContent(cnSelelect, MAQUYEN);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuChucNangStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JPanel menuItem = (JPanel) e.getSource();

        menuItem.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        menuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Component[] components = menuItem.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;

                label.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);

            }
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JPanel menuItem = (JPanel) e.getSource();
        menuItem.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        Component[] components = menuItem.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;

                label.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);

            }
        }
    }
}
