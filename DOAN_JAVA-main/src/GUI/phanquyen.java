/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.sql.ResultSet;
import javax.swing.JPanel;
import DTO.chitietquyenDTO;
import DTO.chucnangDTO;
import DAO.chitietquyenDAO;
import BUS.chitietquyenBUS;
import BUS.chucnangBUS;
import DTO.quyenDTO;
import BUS.quyenBUS;
import DAO.ConnectDataBase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author hp
 */
public class phanquyen extends JPanel implements MouseListener {

    private int ccao, crong;
    private JTable table;
    private Font font_data = new Font("Tahoma", Font.PLAIN, 14);
    private DefaultTableModel tableModel;
    private JPanel JP_listNameQuyen;
    private String[] columnNames = {"", "Xem", "Thêm", "Xóa", "Sửa"};
    public quyenDTO currentQuyen;
    private quyenDTO startQuyen;
    private ArrayList<quyenDTO> listQuyen;
    private ArrayList<chucnangDTO> listChucnang;
    private JPanel JP_quyenSelected;
    public boolean isEditingEnabled = false;

    public phanquyen(int crong, int ccao, quyenDTO quyenUser) {
        this.ccao = ccao;
        this.crong = crong;
        startQuyen = quyenUser;
        currentQuyen = new quyenDTO(startQuyen.getMAQUYEN(), startQuyen.getTENQUYEN());
        init();
    }

    private void init() {
        //danh sach btn quyen

        quyenBUS qBUS = new quyenBUS();
        listQuyen = qBUS.getList();
        JP_listNameQuyen = new JPanel(new FlowLayout(3));
        for (quyenDTO i : listQuyen) {
            JPanel btn_quyen = new JPanel(new BorderLayout());
            JLabel title_quyen = new JLabel(i.getTENQUYEN(), JLabel.CENTER);
            btn_quyen.setPreferredSize(new Dimension((int) title_quyen.getPreferredSize().getWidth() + 50, (int) title_quyen.getPreferredSize().getHeight() + 20));
            btn_quyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            if (i.getMAQUYEN().equals(startQuyen.getMAQUYEN())) {
                title_quyen.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                btn_quyen.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                btn_quyen.setOpaque(true);
                JP_quyenSelected = btn_quyen;
            } else {
                title_quyen.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                btn_quyen.setBackground(Cacthuoctinh_phuongthuc_chung.second_gray);
                btn_quyen.setOpaque(true);
            }

            title_quyen.setFont(new Font("Tahoma", Font.BOLD, 15));
            btn_quyen.setName(i.toString());
            btn_quyen.add(title_quyen, BorderLayout.CENTER);
            btn_quyen.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ((JLabel) JP_quyenSelected.getComponents()[0]).setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                    JP_quyenSelected.setBackground(Cacthuoctinh_phuongthuc_chung.second_gray);
                    JP_quyenSelected.setOpaque(true);

                    JPanel btn = (JPanel) e.getSource();
                    ((JLabel) btn.getComponents()[0]).setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                    btn.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                    btn.setOpaque(true);
                    JP_quyenSelected = btn;
                    repaintDataInTable(btn.getName());
                }
            });
            JP_listNameQuyen.add(btn_quyen);
        }

        chucnangBUS cnBUS = new chucnangBUS();
        listChucnang = cnBUS.getList();
        //Tiêu đề cột dọc
        // Tạo đối tượng DefaultTableModel với dữ liệu và tên cột
        tableModel = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    // Trả về kiểu dữ liệu mặc định cho cột đầu tiên
                    return super.getColumnClass(columnIndex);
                } else {
                    // Trả về kiểu dữ liệu Boolean cho các cột còn lại
                    return Boolean.class;
                }
            }
        };
        tableModel.setColumnIdentifiers(columnNames);

        table = new JTable(tableModel) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return isEditingEnabled;
            }

        };
        addDataInTable();

        // css cho Header cua Table
        cssHeaderTable(table.getTableHeader());
        //css cho rows cua table
        cssDataTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(crong, ccao));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        // Tạo JFrame và thêm JScrollPane vào giao diện
        setLayout(new FlowLayout(3));
        add(JP_listNameQuyen);
        add(scrollPane);
    }

    public void deleteJP_NameQuyen(quyenDTO qDTO) {
        Component[] JP_childNameQuyen = JP_listNameQuyen.getComponents();
        for (int i = 0; i < JP_childNameQuyen.length; i++) {
            JPanel p = (JPanel) JP_childNameQuyen[i];

            quyenDTO quyenCLick = new quyenDTO(p.getName());
            if (quyenCLick.getMAQUYEN().equals(startQuyen.getMAQUYEN())) {
                ((JLabel) p.getComponents()[0]).setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                p.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                p.setOpaque(true);
                p.repaint();
                p.validate();
                JP_quyenSelected = p;
            } else if (quyenCLick.getMAQUYEN().equals(qDTO.getMAQUYEN())) {
                JP_listNameQuyen.remove(i);
                break;
            }
        }
        JP_listNameQuyen.revalidate(); // Cập nhật layout
        JP_listNameQuyen.repaint();
        repaintDataInTable(startQuyen.toString());
    }

    public void addJP_NameQuyen(quyenDTO qDTO) {
        JPanel btn_quyen = new JPanel(new BorderLayout());
        JLabel title_quyen = new JLabel(qDTO.getTENQUYEN(), JLabel.CENTER);
        btn_quyen.setPreferredSize(new Dimension((int) title_quyen.getPreferredSize().getWidth() + 50, (int) title_quyen.getPreferredSize().getHeight() + 20));
        btn_quyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        title_quyen.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_quyen.setName(qDTO.toString());
        btn_quyen.add(title_quyen, BorderLayout.CENTER);
        title_quyen.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        btn_quyen.setBackground(Cacthuoctinh_phuongthuc_chung.second_gray);
        btn_quyen.setOpaque(true);
        btn_quyen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                ((JLabel) JP_quyenSelected.getComponents()[0]).setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                JP_quyenSelected.setBackground(Cacthuoctinh_phuongthuc_chung.second_gray);
                JP_quyenSelected.setOpaque(true);

                JPanel btn = (JPanel) e.getSource();
                ((JLabel) btn.getComponents()[0]).setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                btn.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                btn.setOpaque(true);
                JP_quyenSelected = btn;
                repaintDataInTable(btn.getName());

            }
        });
        JP_listNameQuyen.add(btn_quyen);
        JP_listNameQuyen.repaint();
        JP_listNameQuyen.revalidate();
    }

    private void addDataInTable() {
        Vector data;
        tableModel.setRowCount(0);

        chitietquyenBUS ctqBUS = new chitietquyenBUS();
        for (chucnangDTO i : listChucnang) {
            data = new Vector();
            data.add(i.getTENCHUCNANG());
            
                for (String hd : columnNames) {
                    if (!hd.equals("")) {
                        if (ctqBUS.search(new chitietquyenDTO(startQuyen.getMAQUYEN(), i.getMACHUCNANG(), hd))) {
                            data.add(true);
                        } else {
                            data.add(false);
                        }
                    }
                }
            

            tableModel.addRow(data);
        }

        table.setModel(tableModel);
    }

    private void repaintDataInTable(String mq) {
        quyenDTO new_quyen = new quyenDTO(mq);
        currentQuyen.setMAQUYEN(new_quyen.getMAQUYEN());
        currentQuyen.setTENQUYEN(new_quyen.getTENQUYEN());
        chitietquyenBUS ctqBUS = new chitietquyenBUS();
        TableModel model = table.getModel();
        for (int i = 0; i < listChucnang.size(); i++) {
            for (int j = 1; j < columnNames.length; j++) {
                if (ctqBUS.search(new chitietquyenDTO(currentQuyen.getMAQUYEN(), listChucnang.get(i).getMACHUCNANG(), columnNames[j]))) {
                    model.setValueAt(true, i, j);
                } else {
                    model.setValueAt(false, i, j);
                }
            }
        }
    }

    private void cssHeaderTable(JTableHeader header) {
        header.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        header.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        header.setFont(new Font("Tahoma", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
    }

    private void cssDataTable() {
        table.setRowHeight(35);//chieo cao cua hang
        table.setFont(font_data);//font cho dât trong table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Căn giữa dữ liệu trong các ô
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    public void updateTENQUYEN(quyenDTO qDTO, int status) {
        System.out.println("Quyen dang xet "+qDTO.toString());
        Component[] JP_childNameQuyen = JP_listNameQuyen.getComponents();
        for (int i = 0; i < JP_childNameQuyen.length; i++) {
            JPanel p = (JPanel) JP_childNameQuyen[i];
            quyenDTO duyet = new quyenDTO(p.getName());
           System.out.println("Quyen dang duyet "+duyet.toString());
           System.out.println("Quyen dang duyet co giong "+duyet.getMAQUYEN().equals(qDTO.getMAQUYEN()));
           
            if (duyet.getMAQUYEN().equals(qDTO.getMAQUYEN())) {
                
                switch (status) {
                    case 0:

                        JTextField tenquyen = new JTextField(qDTO.getTENQUYEN());
                        p.removeAll();
                        p.add(tenquyen);
                        break;
                    case 1: {
                        String new_tenquyen = ((JTextField) p.getComponents()[0]).getText();
                        quyenDTO new_quyen = new quyenDTO(p.getName());
                        new_quyen.setTENQUYEN(new_tenquyen);
                        currentQuyen.setTENQUYEN(new_tenquyen);
                        p.setName(new_quyen.toString());
                        JLabel title_quyen = new JLabel(new_tenquyen, JLabel.CENTER);
                        title_quyen.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                        title_quyen.setFont(new Font("Tahoma", Font.BOLD, 15));
                        p.removeAll();
                        p.add(title_quyen);
                        break;
                    }

                    case 2: {
                        JLabel title_quyen = new JLabel(currentQuyen.getTENQUYEN(), JLabel.CENTER);
                        title_quyen.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                        title_quyen.setFont(new Font("Tahoma", Font.BOLD, 15));
                        p.removeAll();
                        p.add(title_quyen);
                        repaintDataInTable(p.getName());
                        break;
                    }

                }

                p.repaint();
                p.validate();
                break;
            }
        }
        JP_listNameQuyen.revalidate(); // Cập nhật layout
        JP_listNameQuyen.repaint();
    }

    public ArrayList<chitietquyenDTO> getListUpdateCtqTheoMAUQYEN() {
     
        ArrayList<chitietquyenDTO> listCtqTheoMAQUYEN = new ArrayList<>();

        String q = currentQuyen.getMAQUYEN();

        TableModel model = table.getModel();
        for (int i = 0; i < listChucnang.size(); i++) {
            
            for (int j = 1; j < columnNames.length; j++) {

                if (model.getValueAt(i, j).equals(true)) {

                    chitietquyenDTO ctqNew = new chitietquyenDTO(q, listChucnang.get(i).getMACHUCNANG(), columnNames[j]);

                    listCtqTheoMAQUYEN.add(ctqNew);
                }

            }
        }
     
        return listCtqTheoMAQUYEN;
    }
public void thaydoiJTable(){
    isEditingEnabled = true;
    
}

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel btn = (JPanel) e.getSource();
        btn.setBackground(Color.red);
        btn.setOpaque(true);
//        this.MAQUYEN= btn.getName();
//        addDataInTable();
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
