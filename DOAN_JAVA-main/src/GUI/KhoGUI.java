package GUI;

import BUS.KhoBUS;
import DTO.KhoDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class KhoGUI extends JPanel{
    JTable table;
    int width, height;
    String[] header = {"Mã sản phẩm", "Mã loại", "Tên sản phẩm", "Size", "SL tồn", "Tồn đầu kỳ", "SL nhập", "SL xuất"};
    ArrayList<KhoDTO> ds;
    DefaultTableModel df;
    Font fontHeader = new Font("Tahoma", Font.BOLD, 20);
    Font font = new Font("Tahoma", Font.BOLD, 13);
    
    public KhoGUI(int width, int height) {
        this.width = width;
        this.height = height;
        init(width, height);
    }
    
    public void init(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        
        //tạo bảng thống kê
        table = new JTable();
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(30); //thiết lập chiều cao các cột
        
        // Thiết lập dữ liệu cho JTable
        df = new DefaultTableModel(header, 0);
        
        table.setModel(df);
        // Canh giữa nội dung trong mỗi ô trong cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < header.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Đặt kích thước cho các cột
        TableColumnModel columnModel = table.getColumnModel();

        // Cột "Mã sản phẩm"
        columnModel.getColumn(0).setPreferredWidth(80);

        //Cột "Mã loại"
        columnModel.getColumn(1).setPreferredWidth(50);

        //Cột "Tên sản phẩm"
        columnModel.getColumn(2).setPreferredWidth(300);
        
        //cột "Size"
        columnModel.getColumn(3).setPreferredWidth(60);

        //cột "SL tồn"
        columnModel.getColumn(4).setPreferredWidth(60);

        //cột "Tồn đầu tháng"
        columnModel.getColumn(5).setPreferredWidth(60);

        //cột "SL nhập"
        columnModel.getColumn(6).setPreferredWidth(60);
        
        //cột "SL xuất"
        columnModel.getColumn(7).setPreferredWidth(60);
        
        // Bỏ kẻ dọc
        table.setShowVerticalLines(false);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));
        tableHeader.setBackground(Color.LIGHT_GRAY); // Đặt màu nền cho tiêu đề là màu xám nhạt
        tableHeader.setFont(font);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane jp = new JScrollPane(table);
         this.add(jp,BorderLayout.CENTER);
    }
    
    public void data(ArrayList<String> data_filters){
        df.setRowCount(0);
        ds = new ArrayList<>();
        KhoBUS kBUS = new KhoBUS();
        ds = kBUS.list(data_filters);
        for (KhoDTO row : ds){
            Object[] data = {row.getMaSP(), row.getLoai(), row.getTenSP(),
                row.getSize(), row.getSlTon(), row.getTonDK(),row.getSlNhap(),row.getSlXuat()};
            df.addRow(data);
        }
        table.setModel(df);
        df.fireTableDataChanged();
    }
    public static void main(String[] args) {
        KhoGUI k = new KhoGUI(800, 600);
        ArrayList<String> currentday = new ArrayList<>();
        currentday.add("2024/09/06");
        currentday.add("2024/09/06");
        k.data(currentday);
        JFrame frame = new JFrame("Thống Kê Sản Phẩm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.add(k); // Thêm GUI vào JFrame
        frame.setVisible(true); // Hiển thị cửa sổ
    }
}
