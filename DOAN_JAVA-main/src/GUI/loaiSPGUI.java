/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.loaiSPBUS;
import BUS.nhacungcapBUS;
import DTO.loaiSP;
import DTO.nhacungcapDTO;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author hp
 */
public class loaiSPGUI extends JPanel{
    private int ccao, crong;
    public JTable table;
    private Font font_data = new Font("Tahoma", Font.PLAIN, 14);
    private DefaultTableModel tableModel;
    public boolean isEditingEnabled = false;
    
    
    public loaiSPGUI(int r,int d){
        this.crong = r;
        this.ccao = d;
        
        init();
    }
    
     private void init() {
          String[] columnNames = {"MALOAI", "Tên loại", "Tình trạng"};
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
        
      loaiSPBUS lBUS = new loaiSPBUS();
        addDataInTable(lBUS.getList());
        
        
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
     
     
     public void addDataInTable(ArrayList<loaiSP> list){
         Vector data;
        tableModel.setRowCount(0);
        for (loaiSP n : list) {
            data = new Vector();
            data.add(n.getMALOAI());
            data.add(n.getTENLOAI());
            
            data.add((n.getTINHTRANG()==1)?"Đang bán":"Ngừng bán");
            tableModel.addRow(data);
            
        }
        table.setModel(tableModel);
        tableModel.fireTableDataChanged();
     }
     
     public void addLineDataInTable(loaiSP n) {
        Vector data = new Vector();
        data.add(n.getMALOAI());
            data.add(n.getTENLOAI());
        data.add((n.getTINHTRANG()==1)?"Đang bán":"Ngừng bán");
        tableModel.addRow(data);
        tableModel.fireTableDataChanged();

    }
     
     public ArrayList<String> getSelectedListLoai() {
        ArrayList<String> MALOAIselected = new ArrayList<>();
        int[] quantity_rowSelected = table.getSelectedRows();
        for (int row : quantity_rowSelected) {
            MALOAIselected.add((String) table.getValueAt(row, 0));
        }
        return MALOAIselected;
    }

    
     public void changeStatusLoai(){
        loaiSPBUS lBUS = new loaiSPBUS();
        ArrayList<loaiSP> list = lBUS.getList();
        for(int i = 0; i < list.size();i++){
            TableColumn testColumn = table.getColumnModel().getColumn(table.getColumnCount()-1);
            JComboBox<String> comboBox = new JComboBox<>();
            comboBox.addItem("Đang bán");
            comboBox.addItem("Ngừng bán");
            comboBox.setSelectedItem((list.get(i).getTINHTRANG()==1)?"Đang bán":"Ngừng bán");
            testColumn.setCellEditor(new DefaultCellEditor(comboBox));
        }
      
     }
     
     private void cssHeaderTable(JTableHeader header) {
        header.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        header.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
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
    
    public ArrayList<loaiSP> getListLoai() {
        ArrayList<loaiSP> list = new ArrayList<>();
        TableModel model = table.getModel();

        for (int i = 0; i < table.getRowCount(); i++) {
            String maloai = (String) model.getValueAt(i, 0);
            String tenloai = (String) model.getValueAt(i, 1);
String tinhtrang = (String) model.getValueAt(i, 2);
           int tt = (tinhtrang.equals("Đang bán"))?1:0;

          
            
            list.add(new loaiSP(maloai,tenloai,tt));
        }
        return list;
    }
     
    public static void main(String[] args) {
        JFrame f =new JFrame();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.add(new loaiSPGUI(800,600));
                
                f.setVisible(true);
    }
}
