package GUI;

import BUS.BUS_qlkh;
import BUS.ChitietHD_BUS;
import BUS.Nhanvien_BUS;
import BUS.SanPhamBUS;
import DAO.ConnectDataBase;
import DTO.ChitietHD_DTO;
import DTO.Nhanvien_DTO;
import DTO.model_qlkh;
import DTO.nhacungcapDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public final class ChitietHD_GUI extends JPanel {

    private JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9;
    private int chieurong, chieucao;
    private Font f = new Font("Tahoma", Font.BOLD, 14);
    private ConnectDataBase mySQL = new ConnectDataBase();
    private ChitietHD_BUS dscthd;
    private String[] titleOrder = {"Mã hóa đơn", "Thời gian", "Tên khách hàng", "Tên nhân viên"};
    private String[] titlePayment = {"Tổng cộng","Giảm giá","Thành tiền"};
    private JLabel[] JL_valuePay;
    private DefaultTableModel tableModel;
    private boolean isEditingEnabled = false;
    private JTable table;
    public ChitietHD_GUI(int chieurong, int chieucao, String maHD, String ngayHD, String time, String maKH, String maNV, String giamgia, String tongtien) throws SQLException {
        this.chieurong = chieurong;
        this.chieucao = chieucao;
        init(maHD, ngayHD, time, maKH, maNV, giamgia, tongtien);
    }

    public void init(String maHD, String ngayHD, String time, String maKH, String maNV, String giamgia, String tongtien) throws SQLException {
        this.setPreferredSize(new Dimension(chieurong, chieucao));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setVisible(true);
        //--------------------------------SHOW_Title-------------------------------
        p1 = new JPanel(new BorderLayout());
        p1.setBackground(Color.white);

        JLabel title = new JLabel("CHI TIẾT HÓA ĐƠN", JLabel.CENTER);

 
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 28));
        title.setForeground(Color.decode("#145369"));
        p1.add(title, BorderLayout.CENTER);
        p1.setPreferredSize(new Dimension(chieurong, (int)title.getPreferredSize().getHeight()  +20));
        add(p1);

        //--------------------------------SHOW_IDHD-------------------------------
        JPanel pInforOrder = new JPanel(new GridLayout(4,1));
        for (int i = 0; i < titleOrder.length; i++) {
            
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 7));
            
            p.setBackground(Color.white);
            JLabel t = new JLabel(titleOrder[i]);
            t.setFont(f);
            t.setForeground(Color.decode("#2596be"));
            t.setPreferredSize(new Dimension(chieurong/4,(int)t.getPreferredSize().getHeight()));
            p.add(t);
            p.setPreferredSize(new Dimension(chieurong,(int)p.getPreferredSize().getHeight()));
            JLabel value = null;
            
            switch (i) {
                case 0: {
                   
                    value = new JLabel(maHD);
                    break;
                }
                case 1: {
                    value = new JLabel(time + ",  " + ngayHD);
                    break;
                }
                case 2: {

                    BUS_qlkh khBUS = new BUS_qlkh();
                    for (model_qlkh k : khBUS.getlist()) {
                        
                        if (String.valueOf(k.getMakh()).equals(maKH)) {
                            value = new JLabel(k.getTen());

                        }
                    }
                    break;
                }
                case 3: {
                    Nhanvien_BUS nvBUS = null;
                    try{
                        nvBUS = new Nhanvien_BUS();
                    }catch(SQLException ex){
                        
                    }
                    
                    for (Nhanvien_DTO n : nvBUS.getlist()) {
                        if (n.getManv().equals(maNV)) {
                            value = new JLabel(n.getTennv());

                        }
                    }
                    break;
                }

            }
            value.setFont(f);
            value.setForeground(Color.decode("#2596be"));
            
            p.add(value);
            pInforOrder.add(p);
        }
        add(pInforOrder);
        //--------------------------------SHOW_SANPHAM_CTHD-------------------------------
        JPanel pDetails = new JPanel();
        pDetails.setBackground(Color.white);
        
        String[] columnNames = {"Tên SP", "Size", "SL", "Đơn giá", "Tổng"};
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
        dscthd = new ChitietHD_BUS(maHD);
        addDataInTable(dscthd.getList());
        
        cssHeaderTable(table.getTableHeader());
        //css cho rows cua table
        cssDataTable();

        // Tạo đối tượng JScrollPane và gán JTable vào để cuộn nếu cần
        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        // Tạo JFrame và thêm JScrollPane vào giao diện
        pDetails.add(scrollPane);
        

//        JPanel p = new JPanel();
//        p.setLayout(new FlowLayout(0, 0, 3));
//        p.setPreferredSize(new Dimension((chieurong * 3 / 5), 200));
//        p.setBackground(Color.white);
//        dscthd = new ChitietHD_BUS(maHD);
//        for (ChitietHD_DTO sp : dscthd.list) {
//            JPanel panel = new JPanel();
//            panel.setLayout(new FlowLayout(0, 5, 0));
//            panel.setPreferredSize(new Dimension((chieurong * 3 / 5) - 2, 40));
//            panel.setBackground(Color.decode("#d3eaf2"));
//            int dongia = (int) sp.getGia();
//            int tong = (int) sp.getSl() * dongia;
//            JLabel lab1 = new JLabel(sp.getTenSP(), JLabel.CENTER);
//            JLabel lab2 = new JLabel(sp.getMaSize(), JLabel.CENTER);
//            JLabel lab3 = new JLabel(Integer.toString(sp.getSl()), JLabel.CENTER);
//            JLabel lab4 = new JLabel(Integer.toString(dongia), JLabel.CENTER);
//            JLabel lab5 = new JLabel(Integer.toString(tong), JLabel.CENTER);
//            lab1.setPreferredSize(new Dimension(((chieurong * 3 / 5)) / 5, 40));
//            lab2.setPreferredSize(new Dimension(((chieurong * 3 / 5) - 50) / 5, 40));
//            lab3.setPreferredSize(new Dimension(((chieurong * 3 / 5) - 50) / 5, 40));
//            lab4.setPreferredSize(new Dimension(((chieurong * 3 / 5) - 20) / 5, 40));
//            lab5.setPreferredSize(new Dimension(((chieurong * 3 / 5) - 50) / 5, 40));
//            panel.add(lab1);
//            panel.add(lab2);
//            panel.add(lab3);
//            panel.add(lab4);
//            panel.add(lab5);
//            p.add(panel);
//        }
//        JScrollPane scrollPane = new JScrollPane(p);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        
//        p6.add(scrollPane);

        
        JPanel pPayment = new JPanel(new GridLayout(3,1));
        int discount = Integer.parseInt(giamgia);
        int total = Integer.parseInt(tongtien);
        for(int i = 0; i<titlePayment.length ;i++){
            JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
            jp.setBackground(Color.white);
            JLabel t = new JLabel(titlePayment[i]);
            t.setFont(new Font("Tahoma",Font.PLAIN,17));
            t.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
            t.setPreferredSize(new Dimension(chieurong/5,(int)t.getPreferredSize().getHeight()));
            jp.add(t);
            jp.setPreferredSize(new Dimension(chieurong,(int)jp.getPreferredSize().getHeight()));
            JLabel value  =null;
            switch (i){
                case 0:{
                    value = new JLabel(discount+total+"",JLabel.CENTER);
                    break;
                }
                case 1:{
                    value = new JLabel(discount+"",JLabel.CENTER);
                    break;
                }
                case 2:{
                    value = new JLabel(total+"",JLabel.CENTER);
                    break;
                }
            }
            value.setFont(new Font("Tahoma",Font.PLAIN,17));
            value.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
            jp.add(value);
            pPayment.add(jp);
        }
    pDetails.setPreferredSize(new Dimension(chieurong, chieucao - (int)p1.getPreferredSize().getHeight() - (int)pInforOrder.getPreferredSize().getHeight() - (int)pPayment.getPreferredSize().getHeight()));
    scrollPane.setPreferredSize(new Dimension(chieurong, (int)pDetails.getPreferredSize().getHeight()));    
    this.add(scrollPane); 
       add(pPayment);
        
    }
    public void addDataInTable(ArrayList<ChitietHD_DTO> list) {
        Vector data;
        tableModel.setRowCount(0);
        SanPhamBUS spBUS = new SanPhamBUS();
        for (ChitietHD_DTO n : list) {
            data = new Vector();
            data.add(spBUS.select_by_id(n.getMaSP()).getTenSP());
            data.add(n.getMaSize());
            data.add(n.getSl());
            data.add((int)n.getGia());
            data.add(n.getSl() * (int)n.getGia());
            tableModel.addRow(data);
        }
        table.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }
    private void cssHeaderTable(JTableHeader header) {
        header.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        header.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        header.setFont(new Font("Tahoma",Font.BOLD,15));
        header.setPreferredSize(new Dimension(header.getWidth(), 30));
    }

    private void cssDataTable() {
       // {"Tên SP", "Size", "Số lượng", "Đơn giá", "Tổng"};
        table.setRowHeight(35);//chieo cao cua hang
        table.setFont(new Font("Tahoma",Font.PLAIN,14));//font cho dât trong table
        table.getColumnModel().getColumn(0).setPreferredWidth((int)chieurong/2);
        table.getColumnModel().getColumn(1).setPreferredWidth((int)chieurong/10);
        table.getColumnModel().getColumn(2).setPreferredWidth((int)chieurong/10);
        table.getColumnModel().getColumn(3).setPreferredWidth((int)chieurong/7);
        table.getColumnModel().getColumn(4).setPreferredWidth(chieurong - (int)chieurong/2 - (int)chieurong*2/10 - (int)chieurong/7);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Căn giữa dữ liệu trong các ô
        table.setDefaultRenderer(Object.class, centerRenderer);
    }
    public static void main(String[] args) throws SQLException {
        JFrame f = new JFrame();
        f.setSize(1200, 800);
        ChitietHD_GUI p = new ChitietHD_GUI(1200, 800, "HD11", "2024-04-03", "23:03:38", "1", "AD1", "200000", "2100000");
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
