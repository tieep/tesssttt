/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.khachHangBUS;
import DTO.khachHangDTO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class khachHangGUI extends JPanel {

    private int chieu_cao, chieu_rong;
    public JTable table;
    private Font font_text = new Font("Tahoma", Font.PLAIN, 14);
    private DefaultTableModel dtm;

    public khachHangGUI(int h, int w) {
        this.chieu_cao = h;
        this.chieu_rong = w;
        init();
    }

    public void init() {
        setLayout(new BorderLayout());
        String[] cot = {"Mã KH", "Tên khách hàng", "Số điện thoại", "Điểm tích lũy"};
        dtm = new DefaultTableModel(cot, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(dtm);
        styleTable(table);

        khachHangBUS busKH = new khachHangBUS();
        reloadData(busKH.getDs_khachHang());

        JScrollPane scrPane = new JScrollPane(table);
        scrPane.setPreferredSize(new Dimension(chieu_rong, chieu_cao));
        scrPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrPane, BorderLayout.CENTER);
    }

    private void styleTable(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        header.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        header.setFont(new Font("Tahoma", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        table.setRowHeight(35);
        table.setFont(font_text);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(chieu_rong - table.getColumnModel().getColumn(0).getWidth() - table.getColumnModel().getColumn(2).getWidth() - table.getColumnModel().getColumn(3).getWidth());
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    public void reloadData(ArrayList<khachHangDTO> ds) {
        dtm.setRowCount(0);
        for (khachHangDTO kh : ds) {
            Object[] row = {kh.getMaKH(), kh.getTenKH(), kh.getSoDienThoai(), kh.getDiem()};
            dtm.addRow(row);
        }
    }

    public ArrayList<Integer> lay_sd_chon() {
        ArrayList<Integer> DSMaKh = new ArrayList<>();
        int[] sl_dong = table.getSelectedRows();
        for (int row : sl_dong) {
            DSMaKh.add(Integer.parseInt(table.getValueAt(row, 0) + ""));
        }
        return DSMaKh;
    }
    
    public void them_mot_kh(khachHangDTO kh) {
        dtm.addRow(new Object[]{kh.getMaKH(),kh.getTenKH(),kh.getSoDienThoai(),kh.getDiem()});
        dtm.fireTableDataChanged();
    }
    
    public void suaMotKhachHang(khachHangDTO kh) {
    int rowCount = dtm.getRowCount();
    for (int i = 0; i < rowCount; i++) {
        if (dtm.getValueAt(i, 0).equals(kh.getMaKH())) {
            dtm.setValueAt(kh.getTenKH(), i, 1);  
            dtm.setValueAt(kh.getSoDienThoai(), i, 2);  
            dtm.setValueAt(kh.getDiem(), i, 3);  
            break; 
        }
    }
    dtm.fireTableDataChanged();
}
    
    public khachHangDTO lay_mot_kh(int id) {
        khachHangBUS bus = new khachHangBUS();
        ArrayList<khachHangDTO> ds = bus.getDs_khachHang();
        for(khachHangDTO kh : ds) {
            if(kh.getMaKH() == id){
                return kh;
            }
        }
        return null;
    }
    
    public int lay_id_table() {
        int chon = table.getSelectedRow();
        int id = Integer.parseInt(table.getValueAt(chon, 0) + "");
        return id;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new khachHangGUI(900, 600));
        f.pack();
        f.setVisible(true);
        
    }
}
