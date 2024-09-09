/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.nhacungcapBUS;
import DAO.ConnectDataBase;
import DTO.chitietquyenDTO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.util.Vector;
import DTO.nhacungcapDTO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author hp
 */
public class nhacungcapGUI extends JPanel {

    private int ccao, crong;
    public JTable table;
    private Font font_data = new Font("Tahoma", Font.PLAIN, 14);
    private DefaultTableModel tableModel;
    public boolean isEditingEnabled = false;

    public nhacungcapGUI(int crong, int ccao) {
        this.ccao = ccao;
        this.crong = crong;
        init();
    }

    private void init() {

        // Tạo mảng chuỗi chứa tên cột
        String[] columnNames = {"MANCC", "Tên nhà cung cấp", "Số điện thoại"};
        // Tạo đối tượng DefaultTableModel với dữ liệu và tên cột
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);

        // Tạo đối tượng JTable và gán mô hình dữ liệu
        table = new JTable(tableModel) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return isEditingEnabled;
            }

        };

        nhacungcapBUS nccBUS = new nhacungcapBUS();
        addDataInTable(nccBUS.getList());

        // css cho Header cua Table
        cssHeaderTable(table.getTableHeader());
        //css cho rows cua table
        cssDataTable();

        // Tạo đối tượng JScrollPane và gán JTable vào để cuộn nếu cần
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(crong, ccao));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        // Tạo JFrame và thêm JScrollPane vào giao diện
        add(scrollPane);
    }

    public void addDataInTable(ArrayList<nhacungcapDTO> list) {
        Vector data;
        tableModel.setRowCount(0);
        for (nhacungcapDTO n : list) {
            data = new Vector();
            data.add(n.getMANCC());
            data.add(n.getTENNCC());
            data.add(n.getSDT());
            tableModel.addRow(data);
        }
        table.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

    public void addLineDataInTable(nhacungcapDTO ncc) {
        Vector data = new Vector();
        data.add(ncc.getMANCC());
        data.add(ncc.getTENNCC());
        data.add(ncc.getSDT());
        tableModel.addRow(data);
        tableModel.fireTableDataChanged();

    }

    private void cssHeaderTable(JTableHeader header) {
        header.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        header.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        header.setFont(Cacthuoctinh_phuongthuc_chung.font_header);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
    }

    private void cssDataTable() {
        table.setRowHeight(35);//chieo cao cua hang
        table.setFont(font_data);//font cho dât trong table
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(crong - table.getColumnModel().getColumn(0).getWidth() - table.getColumnModel().getColumn(2).getWidth());
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Căn giữa dữ liệu trong các ô
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    public ArrayList<String> getSelectedListNCC() {
        ArrayList<String> MANCCselected = new ArrayList<>();
        int[] quantity_rowSelected = table.getSelectedRows();
        for (int row : quantity_rowSelected) {
            MANCCselected.add((String) table.getValueAt(row, 0));
        }
        return MANCCselected;
    }

    public ArrayList<nhacungcapDTO> getListNCC() {
        ArrayList<nhacungcapDTO> list = new ArrayList<>();
        TableModel model = table.getModel();

        for (int i = 0; i < table.getRowCount(); i++) {
            String mancc = (String) model.getValueAt(i, 0);
            String tenncc = (String) model.getValueAt(i, 1);

            int sdt = 0; // Mặc định số điện thoại là 0 nếu dữ liệu không hợp lệ

            String sdtStr = String.valueOf(model.getValueAt(i, 2)); // Chuyển đổi Object sang String

// Kiểm tra nếu chuỗi có thể chuyển đổi thành số nguyên
            try {
                sdt = Integer.parseInt(sdtStr);
            } catch (NumberFormatException ex) {
            }
            
            nhacungcapDTO nccDTO = new nhacungcapDTO(mancc, tenncc, sdt);
            System.out.println("new ncc"+mancc+" "+tenncc+" "+sdt);
            list.add(nccDTO);
        }
        return list;
    }

    public static void main(String[] args) {
        JFrame f =new JFrame();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.add(new nhacungcapGUI(800,600));
                
                f.setVisible(true);
    }

}
