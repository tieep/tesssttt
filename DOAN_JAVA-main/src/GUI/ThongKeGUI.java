package GUI;

import BUS.ThongKeBus;
import BUS.loaiSPBUS;
import DAO.ConnectDataBase;
import DTO.ThongKeDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class ThongKeGUI extends JPanel {

    private ConnectDataBase mySQL;

    JPanel pnHeader;
    JPanel pnBieuDo;
    JPanel pnDoanhThu;
    double doanhThu;
    JTable table;
    String[] header = {"Mã sản phẩm", "Mã loại", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành Tiền"};
    ArrayList<ThongKeDTO> ds;
    DefaultTableModel df;
    Font fontHeader = new Font("Tahoma", Font.BOLD, 20);
    Font font = new Font("Tahoma", Font.BOLD, 13);
    int width, height;
    JLabel lblHeader;

    // Định dạng sử dụng dấu phân cách hàng nghìn
    DecimalFormat FormatInt = new DecimalFormat("#,###");

    // Định dạng với dấu phân cách hàng nghìn và 2 số thập phân
    DecimalFormat FormatDouble = new DecimalFormat("#,###.##");

    public ThongKeGUI(int width, int height) {
        this.width = width;
        this.height = height;

        try {
            mySQL = new ConnectDataBase();
        } catch (SQLException e) {
            System.out.println("That bai");
        }

        LocalDate ngayHienTai = LocalDate.now();

        // Định dạng ngày theo định dạng dd/MM/yyyy
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ngayDinhDang = ngayHienTai.format(dinhDang);

        init(width, height);

    }

    public void tinhDoanhThu() {
        int sum = 0;
        for (int i = 0; i < ds.size(); i++) {
            sum += ds.get(i).getThanhTien();
        }
        this.doanhThu = sum;
    }

    public void ShowdoanhThu(ArrayList<String> data_filters) {
        lblHeader.setText("THỐNG KÊ DOANH THU");
        df.setRowCount(0);
        ds = new ArrayList<>();
        ThongKeBus thkBus = new ThongKeBus();
        ds = thkBus.listDoanhThu(data_filters);
        for (ThongKeDTO row : ds) {
            //gọi lại hàm format cho cách hiện thị cho các số có giá trị quá lớn hoặc nhở
            String donGia = FormatDouble.format(row.getDonGia());
            String soLuong = FormatInt.format(row.getSoLuong());
            String thanhTien = FormatDouble.format(row.getThanhTien());
            Object[] data = {row.getMaSP(), row.getMaLoai(), row.getTenSP(), soLuong, donGia, thanhTien};
            df.addRow(data);
        }
        table.setModel(df);
        df.fireTableDataChanged();
        tinhDoanhThu();
        BarChart_DoanhThu chart = new BarChart_DoanhThu("Sơ đồ thống kê doanh thu", ds, data_filters);
        pnBieuDo.removeAll();
        pnBieuDo.add(chart,BorderLayout.CENTER);
        this.revalidate(); // Cập nhật bố trí
        this.repaint();
    }

    public void ShowbanChay(ArrayList<String> data_filters) {
        lblHeader.setText("SẢN PHẨM BÁN CHẠY");
        df.setRowCount(0);
        ds = new ArrayList<>();
        ThongKeBus thkBus = new ThongKeBus();
        ds = thkBus.listBanChay(data_filters);
        for (ThongKeDTO row : ds) {
            //gọi lại hàm format cho cách hiện thị cho các số có giá trị quá lớn hoặc nhở
            String donGia = FormatDouble.format(row.getDonGia());
            String soLuong = FormatInt.format(row.getSoLuong());
            String thanhTien = FormatDouble.format(row.getThanhTien());
            Object[] data = {row.getMaSP(), row.getMaLoai(), row.getTenSP(),
                soLuong, donGia, thanhTien};
            df.addRow(data);
        }
        table.setModel(df);
        df.fireTableDataChanged();
        PieChart_BanChay demo = new PieChart_BanChay("Sản phẩm bán chạy", ds);
        pnBieuDo.removeAll();
        pnBieuDo.add(demo,BorderLayout.CENTER);
        this.revalidate(); // Cập nhật bố trí
        this.repaint();
    }

    public void initPnDoanhThu() {
        // Tính toán doanh thu trước
        tinhDoanhThu(); // Đảm bảo gọi tinhDoanhThu trước khi cập nhật nhãn
        if (pnDoanhThu == null) {
            pnDoanhThu = new JPanel(new FlowLayout());
            pnDoanhThu.setPreferredSize(new Dimension(width, 50));
        }

        String dt = FormatDouble.format(doanhThu); // Định dạng doanh thu
        JLabel lblDoanhThu = new JLabel("Tổng doanh thu: " + dt + " VNĐ");
        lblDoanhThu.setFont(font);

        pnDoanhThu.removeAll(); // Xóa các thành phần cũ để đảm bảo không bị trùng
        pnDoanhThu.add(lblDoanhThu); // Thêm nhãn mới
        this.add(pnDoanhThu, BorderLayout.SOUTH); // Đảm bảo thêm vào đúng vị trí

        this.revalidate(); // Cập nhật bố trí
        this.repaint(); // Vẽ lại giao diện
    }

    public void init(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

        ///Header
        pnHeader = new JPanel();
        pnHeader.setLayout(new FlowLayout());
        pnHeader.setBorder(new EmptyBorder(20, 0, 20, 0));
        lblHeader = new JLabel("THỐNG KÊ DOANH THU", JLabel.CENTER);
        lblHeader.setFont(fontHeader);
        pnHeader.add(lblHeader);
        this.add(pnHeader, BorderLayout.NORTH);
        
        ///Biểu đồ
        pnBieuDo = new JPanel();
        pnBieuDo.setLayout(new BorderLayout());
        pnBieuDo.setPreferredSize(new Dimension(width,height/2));
        pnBieuDo.setBorder(BorderFactory.createEmptyBorder(0, 20, 40, 20));
        this.add(pnBieuDo,BorderLayout.NORTH);
        
        ///tạo bảng thống kê
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

        //cột "Số lượng"
        columnModel.getColumn(3).setPreferredWidth(60);

        //cột "Đơn giá"
        columnModel.getColumn(4).setPreferredWidth(90);

        //cột "Thành tiền"
        columnModel.getColumn(5).setPreferredWidth(150);

        // Bỏ kẻ dọc
        table.setShowVerticalLines(false);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));
        tableHeader.setBackground(Color.LIGHT_GRAY); // Đặt màu nền cho tiêu đề là màu xám nhạt
        tableHeader.setFont(font);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane jp = new JScrollPane(table);
        this.add(jp, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        ThongKeGUI thongKeGUI = new ThongKeGUI(800, 600);
        ArrayList<String> currentday = new ArrayList<>();
        currentday.add("2023/01/01");
        currentday.add("2024/01/01");
        currentday.add("Tất cả");
        thongKeGUI.ShowdoanhThu(currentday);
        thongKeGUI.initPnDoanhThu();

//              ShowbanChay("2023/01/01", "2024/05/04", 1);
//        thongKeGUI.ShowbanChay(currentday);
        JFrame frame = new JFrame("Thống Kê Sản Phẩm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.add(thongKeGUI); // Thêm GUI vào JFrame
        frame.setVisible(true); // Hiển thị cửa sổ
    }
}
