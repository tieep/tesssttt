/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.SizeBUS;
import BUS.nhacungcapBUS;
import DTO.SizeDTO;
import DTO.loaiSP;
import DTO.nhacungcapDTO;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author hp
 */
public class SizeGUI extends JPanel {
      private int ccao, crong;
    public JTable table;
    private Font font_data = new Font("Tahoma", Font.PLAIN, 14);
    private DefaultTableModel tableModel;
    public boolean isEditingEnabled = false;
    
      public SizeGUI(int crong, int ccao) {
        this.ccao = ccao;
        this.crong = crong;
        init();
    }
      
       private void init() {

        // Tạo mảng chuỗi chứa tên cột
        String[] columnNames = {"MANCC", "Tên Size"};
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

           SizeBUS sizeBUS = new SizeBUS();
        addDataInTable(sizeBUS.getList());

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
       
       public void addDataInTable(ArrayList<SizeDTO> list) {
        Vector data;
        tableModel.setRowCount(0);
        for (SizeDTO n : list) {
            data = new Vector();
            data.add(n.getMASIZE());
            data.add(n.getTENSIZE());
            tableModel.addRow(data);
        }
        table.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

       
       public void addLineDataInTable(SizeDTO n) {
        Vector data = new Vector();
        data.add(n.getMASIZE());
        data.add(n.getTENSIZE());
        tableModel.addRow(data);
        tableModel.fireTableDataChanged();

    }

     private void cssHeaderTable(JTableHeader header) {
        header.setBackground(Cacthuoctinh_phuongthuc_chung.second_gray);
        header.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);

        header.setFont(Cacthuoctinh_phuongthuc_chung.font_header);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
    }

    private void cssDataTable() {
        table.setRowHeight(35);//chieo cao cua hang
        table.setFont(font_data);//font cho dât trong table
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(crong - table.getColumnModel().getColumn(0).getWidth() );
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Căn giữa dữ liệu trong các ô
        table.setDefaultRenderer(Object.class, centerRenderer);
    }  

    
    
    public ArrayList<String> getSelectedListSize() {
        ArrayList<String> MASIZEselected = new ArrayList<>();
        int[] quantity_rowSelected = table.getSelectedRows();
        for (int row : quantity_rowSelected) {
            MASIZEselected.add((String) table.getValueAt(row, 0));
        }
        return MASIZEselected;
    }
     public ArrayList<SizeDTO> getListSize() {
        ArrayList<SizeDTO> list = new ArrayList<>();
        TableModel model = table.getModel();

        for (int i = 0; i < table.getRowCount(); i++) {
            String masize = (String) model.getValueAt(i, 0);
            String tensize = (String) model.getValueAt(i, 1);


          
            
            list.add(new SizeDTO(masize,tensize));
        }
        return list;
    }
    

     public static void main(String[] args) {
        JFrame f =new JFrame();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.add(new SizeGUI(800,600));
                
                f.setVisible(true);
    }
}
