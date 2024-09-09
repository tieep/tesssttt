package GUI;

import BUS.SanPhamBUS;
import BUS.khachHangBUS;
import BUS.loaiSPBUS;
import BUS.nhacungcapBUS;
import BUS.phieunhap_BUS;
import BUS.quyenBUS;
import DTO.SanPhamDTO;
import DTO.loaiSP;
import DTO.quyenDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
public class SearchInStore extends JPanel implements MouseListener {

    private JTextField name;
    private JTextField cheapestPrice;
    private JTextField highestPrice;
    private JComboBox<String> typeShirt;
    private JComboBox<String> sortPoint;
    private JComboBox<String> typeChucvu;
    private JComboBox<String> typeTrangthai;
    private JSpinner startDate;
    private JSpinner endDate;
    private JButton reset;
    private JButton submit;
    private Font font_title;
    private String MACHUCNANG;
    private ArrayList<String> listTitle;
    public ArrayList<Component> listComponentTimkiem;
    private JPanel pageContent;
    private int thongkeloai = 0;

    // private String[] titleTimkiem={"Theo tên"};
    public SearchInStore(String MACHUCNANG, Component pageContent) {

        this.pageContent = (JPanel) pageContent;

        this.MACHUCNANG = MACHUCNANG;
        listComponentTimkiem = new ArrayList<>();
        init();

    }

    public SearchInStore(String MACHUCNANG, Component pageContent, int thongkeloai) {

        this.pageContent = (JPanel) pageContent;

        this.MACHUCNANG = MACHUCNANG;
        this.thongkeloai = thongkeloai;
        listComponentTimkiem = new ArrayList<>();
        init();

    }

    public void cssBtn(JButton b) {
        b.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        b.setPreferredSize(new Dimension(80, (int) b.getPreferredSize().getHeight()));
        b.setOpaque(true);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.addMouseListener(this);
    }

    public JPanel itemInput(String titleInput) {
        JPanel wrap = new JPanel();
        wrap.setLayout(new GridLayout(2, 1, 0, 10));

        if (!titleInput.equalsIgnoreCase("đến")) {
            font_title = new Font("Tahoma", Font.PLAIN, 14);
            JLabel title = new JLabel(titleInput + "", JLabel.CENTER);
            title.setBackground(Color.WHITE);
            title.setOpaque(true);
            title.setFont(font_title);
            //   title.setPreferredSize(new Dimension(chieurong, 28));
            wrap.add(title);
        }
        switch (titleInput) {
            case "Theo tên":
            case "Tất cả":
            case "Theo tên sản phẩm, theo MASP":
            case "Theo mã nhân viên, theo tên đăng nhập, theo mã quyền":
            case "Theo tên hoặc Sdt":
            case "Theo tên nhân viên, theo MANV, theo SĐT":
            case "Theo MAPN,MANV,MANCC":
            case "Theo MAHD, theo MAKH, theo MANV":
            case "Theo tên loại hoặc MALOAI":
                name = new JTextField();

                listComponentTimkiem.add(name);
                wrap.add(name);
                break;
            case "Giá thấp nhất":
                cheapestPrice = new JTextField();
                listComponentTimkiem.add(cheapestPrice);
                wrap.add(cheapestPrice);
                break;
            case "Giá cao nhất":

                highestPrice = new JTextField();
                listComponentTimkiem.add(highestPrice);
                wrap.add(highestPrice);
                break;
            case "Tình trạng": {
                switch (MACHUCNANG) {
                    case "TK":
                        typeTrangthai = new JComboBox<>(new String[]{"Tất cả", "Đang hoạt động", "Đã khóa"});
                        break;
                    case "LOAI":
                        typeTrangthai = new JComboBox<>(new String[]{"Tất cả", "Đang bán", "Ngừng bán"});
                        break;
                }
                listComponentTimkiem.add(typeTrangthai);
                wrap.add(typeTrangthai);
                wrap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;
            }

            case "Theo chức vụ": {
                quyenBUS qBUS = new quyenBUS();
                ArrayList<quyenDTO> listChucvu = qBUS.getList();
                Vector chucvu = new Vector();

                chucvu.add("Tất cả");

                for (quyenDTO q : listChucvu) {
                    chucvu.add(q.getTENQUYEN());
                }
                typeChucvu = new JComboBox<>(chucvu);
                listComponentTimkiem.add(typeChucvu);
                wrap.add(typeChucvu);
                wrap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;
            }

            case "Điểm tích lũy":
                sortPoint = new JComboBox<>(new String[]{"Ngẫu nhiên", "Thấp đến cao", "Cao đến thấp"});
                listComponentTimkiem.add(sortPoint);
                wrap.add(sortPoint);
                wrap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;
            case "Theo loại": {
                Vector loai = new Vector();
                loaiSPBUS loaiBUS = new loaiSPBUS();

                loai.add("Tất cả");

                switch (MACHUCNANG) {
                    case "SP": {
                        for (loaiSP q : loaiBUS.getList()) {
                            if (loaiBUS.checkTINHTRANG(q.getMALOAI())) {
                                loai.add(q.getTENLOAI());
                            }
                        }
                        break;
                    }
                    case "NULLThK": {
                        for (loaiSP q : loaiBUS.getListFull()) {
                            loai.add(q.getTENLOAI());
                        }
                    }

                }

                typeShirt = new JComboBox<>(loai);
                listComponentTimkiem.add(typeShirt);
                wrap.add(typeShirt);
                wrap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;
            }
            case "Top": {
                SanPhamBUS spBUS = new SanPhamBUS();
                ArrayList<SanPhamDTO> listLoai = spBUS.getDsSP();
                Vector top = new Vector();
                for (int i = 1; i <= listLoai.size(); i++) {
                    top.add(i + "");
                }

                typeShirt = new JComboBox<>(top);
                listComponentTimkiem.add(typeShirt);
                wrap.add(typeShirt);
                wrap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;
            }

            case "Ngày bắt đầu":
            case "Ngày tạo TK":
            case "Theo ngày nhập": {
                startDate = new JSpinner(new SpinnerDateModel());
                JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(startDate, "yyyy/MM/dd");
                startDate.setEditor(dateEditor);
                listComponentTimkiem.add(startDate);
                wrap.add(startDate);
                break;
            }
            case "Ngày kết thúc": {
                endDate = new JSpinner(new SpinnerDateModel());
                JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(endDate, "yyyy/MM/dd");
                endDate.setEditor(dateEditor);
                listComponentTimkiem.add(endDate);
                wrap.add(endDate);
                break;
            }
            case "đến":
                JLabel den = new JLabel("đến", JLabel.CENTER);
                den.setBackground(Color.WHITE);
                den.setOpaque(true);
                wrap.add(den);
                break;
        }
        wrap.setBackground(Color.WHITE);
        wrap.setOpaque(true);
        return wrap;
    }

    private JPanel btn() {
        JPanel btn = new JPanel();
        btn.setLayout(new FlowLayout(1));
        btn.setPreferredSize(new Dimension(80, 60));

        reset = new JButton("XÓA");

        cssBtn(reset);
        submit = new JButton("TÌM");
        cssBtn(submit);
        btn.add(submit);
        btn.add(reset);
        btn.setBackground(Color.WHITE);
        btn.setOpaque(true);

        return btn;
    }

    private void init() {

        listTitle = new ArrayList<>();
        switch (MACHUCNANG) {

            case "NULLTK": {
                String[] list = {};
                listTitle.addAll(Arrays.asList(list));
                break;
            }
            case "NULLHD": {
                String[] list = {};
                listTitle.addAll(Arrays.asList(list));
                break;
            }
            case "PQ": {
                String[] list = {};
                listTitle.addAll(Arrays.asList(list));
                break;
            }
            //={"Theo tên","Giá thấp nhất","đến","Giá cao nhất","Theo loại",}
            case "NCC": {
                String[] list = {"Tất cả"};
                listTitle.addAll(Arrays.asList(list));
                //submit.setName(TENCHUCNANG);
                break;
            }

//            case "Tài khoản cá nhân": {
//                String[] list = {};
//                listTitle.addAll(Arrays.asList(list));
//                break;
//            }
            case "SP": {
                String[] list = {"Theo tên sản phẩm, theo MASP", "Theo loại"};
                listTitle.addAll(Arrays.asList(list));
                break;
            }
            case "TK": {
                String[] list = {"Theo mã nhân viên, theo tên đăng nhập, theo mã quyền", "Tình trạng"};
                listTitle.addAll(Arrays.asList(list));
                break;
            }
            case "KH": {
                String[] list = {"Theo tên hoặc Sdt", "Điểm tích lũy"};
                listTitle.addAll(Arrays.asList(list));
                break;
            }

//            case "Giỏ hàng": {
//                String[] list = {};
//                listTitle.addAll(Arrays.asList(list));
//                break;
//            }
            case "NV": {
                String[] list = {"Theo tên nhân viên, theo MANV, theo SĐT"};
                listTitle.addAll(Arrays.asList(list));
                break;
            }
            case "PN": {
                String[] list = {"Theo MAPN,MANV,MANCC"};
                listTitle.addAll(Arrays.asList(list));
                break;
            }
            case "NULLThK": {

                switch (thongkeloai) {
                    case 0:
                        String[] list1 = {"Ngày bắt đầu", "đến", "Ngày kết thúc", "Theo loại"};
                        listTitle.addAll(Arrays.asList(list1));
                        break;
                    case 1:
                        String[] list2 = {"Ngày bắt đầu", "đến", "Ngày kết thúc", "Top"};
                        listTitle.addAll(Arrays.asList(list2));
                        break;
                }

                break;
            }

            case "HD": {

                String[] list = {"Theo MAHD, theo MAKH, theo MANV", "Ngày bắt đầu", "đến", "Ngày kết thúc"};
                listTitle.addAll(Arrays.asList(list));
                break;
            }
            case "LOAI": {
                String[] list = {"Theo tên loại hoặc MALOAI", "Tình trạng"};
                listTitle.addAll(Arrays.asList(list));
                break;
            }
            case "SIZE": {
                String[] list = {};
                listTitle.addAll(Arrays.asList(list));
                break;
            }
            case "QLK": {
                String[] list = {"Ngày bắt đầu", "đến", "Ngày kết thúc"};
                listTitle.addAll(Arrays.asList(list));
                break;
            }

        }
        if (!listTitle.isEmpty()) {

            JPanel jp_title = new JPanel();
            jp_title.setLayout(new BorderLayout());
            jp_title.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
            jp_title.setOpaque(true);
            JLabel jl_title = new JLabel("<html><h1 style='font-family:Tahoma;font-weight:600;font-size:16;'>Tìm kiếm</h1></html>");

            jp_title.add(jl_title, BorderLayout.WEST);

            JPanel x = new JPanel();

            font_title = new Font("Tahoma", Font.PLAIN, 14);

            x.setLayout(new FlowLayout(3, 20, 0));
            x.setBackground(Color.white);
            x.setOpaque(true);
//            x.setPreferredSize(new Dimension(chieurong, 80));
            for (String titleTimkiem1 : listTitle) {

                JPanel child_x = itemInput(titleTimkiem1);

                x.add(child_x);
            }

            JPanel btn = new JPanel(new BorderLayout());
            btn.add(btn(), BorderLayout.CENTER);
            x.add(btn);

            Border top = BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK);
            Border arround = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
            x.setBorder(BorderFactory.createCompoundBorder(top, arround));

            setLayout(new FlowLayout(3, 0, 0));
            jp_title.setPreferredSize(new Dimension((int) jl_title.getPreferredSize().getWidth() + 10, (int) jl_title.getPreferredSize().getHeight()));
            add(jp_title);
            x.setPreferredSize(new Dimension((int) x.getPreferredSize().getWidth(), 70));
            setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
            setOpaque(true);
            add(x);
        }

    }

    public void searchOfChucnang(ArrayList<String> data_filter) throws SQLException, ParseException {
        Component[] components = pageContent.getComponents();
        switch (MACHUCNANG) {
            case "NCC": {
                nhacungcapBUS nccBUS = new nhacungcapBUS();
                nhacungcapGUI nccGUI = (nhacungcapGUI) components[0];

                nccGUI.addDataInTable(nccBUS.search(data_filter));
                nccGUI.repaint();
                nccGUI.validate();

                break;
            }
            case "LOAI": {
                loaiSPBUS loaiBUS = new loaiSPBUS();
                loaiSPGUI loaiGUI = (loaiSPGUI) components[0];
                System.out.println("Du lieu tim kiem" + data_filter.toString());

                loaiGUI.addDataInTable(loaiBUS.search(data_filter));
                loaiGUI.repaint();
                loaiGUI.validate();

                break;
            }
            case "NULLThK":

                chucnangThongke tkGUI = (chucnangThongke) components[0];
                JPanel jp_content = tkGUI.JP_contentCuaLoaiThongke;
                Component[] jp_con = jp_content.getComponents();
                ThongKeGUI thongke = (ThongKeGUI) jp_con[0];
                switch (thongkeloai) {
                    case 0:

                        thongke.ShowdoanhThu(data_filter);
                        thongke.initPnDoanhThu();
                        break;
                    case 1:

                        thongke.ShowbanChay(data_filter);
                        break;
                }

                thongke.repaint();
                thongke.validate();
                break;
            case "TK":
                chucnangTaikhoan cntk = (chucnangTaikhoan) components[0];
                JPanel pnContent = cntk.JP_contentCuaNameChucnangCon;
                Component[] pnCon = pnContent.getComponents();
                TaiKhoanGUI taikhoan = (TaiKhoanGUI) pnCon[0];
                taikhoan.SearchTK(data_filter);
                taikhoan.repaint();
                taikhoan.validate();
                break;
            case "PN":{
                phieunhap_BUS pnBUS=new phieunhap_BUS();
                phieunhap_GUI pnGUI=(phieunhap_GUI) components[0];
                
                pnGUI.repaint();
                pnGUI.validate();
                break;
            }
            case "SP":
                SanPhamGUI spGUI = (SanPhamGUI) components[0];
                spGUI.SearchSP(data_filter);
                spGUI.repaint();
                spGUI.validate();
                break;
            case "HD":
                JOptionPane.showMessageDialog(null, "Sắp có tính năng này!");
//                chucnangHoadon cnhd = (chucnangHoadon) components[0];
//                JPanel pnCont = cnhd.JP_contentCuaNameChucnangCon;
//                Component[] pn = pnCont.getComponents();
//                TrangLichsuHD lshd = (TrangLichsuHD) pn[0];
//                lshd.SearchHD(data_filter);
//                lshd.repaint();
//                lshd.validate();
                break;
            case "NV":
                Trangnhanvien_GUI nvGUI = (Trangnhanvien_GUI) components[0];
                nvGUI.SearchHD(data_filter);
                nvGUI.repaint();
                nvGUI.validate();
                break;
//<<<<<<< HEAD
            case "KH": {
                khachHangBUS busKH = new khachHangBUS();
                khachHangGUI khGUI = (khachHangGUI) components[0];
                khGUI.reloadData(busKH.search(data_filter));
                khGUI.repaint();
                khGUI.validate();

                break;
            }
//=======
            case "QLK":
                KhoGUI k = (KhoGUI) components[0];
                k.data(data_filter);
                k.repaint();
                k.validate();
                break;
//>>>>>>> e21f13e080e87a8f6b4836c080defab66dad1c9b
        }
        
    }


    public void resetOfChucnang() throws SQLException, ParseException {
        ArrayList<String> data_filter = new ArrayList<>();
        switch (MACHUCNANG) {
            case "NCC": {

                data_filter.add("");

                break;
            }
            case "LOAI": {
                data_filter.add("");
                data_filter.add("Tất cả");
                break;
            }
            case "NULLThK": {
                LocalDate ngayHienTai = LocalDate.now();

                // Định dạng ngày theo định dạng dd/MM/yyyy
                DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String ngayDinhDang = ngayHienTai.format(dinhDang);
                data_filter.add(ngayDinhDang);
                data_filter.add(ngayDinhDang);
                switch (thongkeloai) {
                    case 0:
                        data_filter.add("Tất cả");

                        break;
                    case 1:

                        data_filter.add("1");
                        break;
                }
                break;
            }
            case "TK":
                data_filter.add("");
                data_filter.add("Tất cả");
                break;
            case "SP":
                data_filter.add("");
                data_filter.add("Tất cả");
                break;
            case "HD": {
                data_filter.add("");
                data_filter.add("Tất cả");
                break;
            }
            case "NV": {
                data_filter.add("");
                data_filter.add("Tất cả");
                break;
            }
            case "QLK": {
                LocalDate ngayHienTai = LocalDate.now();

                // Định dạng ngày theo định dạng dd/MM/yyyy
                DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String ngayDinhDang = ngayHienTai.format(dinhDang);
                data_filter.add(ngayDinhDang);
                data_filter.add(ngayDinhDang);
                break;
            }
        }
        searchOfChucnang(data_filter);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ArrayList<String> data_filter = new ArrayList<>();
        JButton btn = (JButton) e.getSource();
        if (btn == submit) {
//            TaiKhoanGUI tk = new TaiKhoanGUI((int)pageContent.getPreferredSize().getWidth(),(int)pageContent.getPreferredSize().getHeight());
//            TaiKhoanGUI tk = new TaiKhoanGUI(500, 500);
//            tk.SearchTK(name.getText(), typeTrangthai.getSelectedIndex());
            for (Component c : listComponentTimkiem) {
                if (c instanceof JTextField) {
                    JTextField text = (JTextField) c;
                    data_filter.add(text.getText());
                } else if (c instanceof JComboBox<?>) {
                    JComboBox<String> comboBox = (JComboBox<String>) c;
                    String selectedItem = (String) comboBox.getSelectedItem();

                    data_filter.add(selectedItem);

                } else {
                    JSpinner date = (JSpinner) c;
                    Date selectedDate = (Date) date.getValue();
                    // Định dạng ngày tháng năm thành chuỗi
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String dateString = dateFormat.format(selectedDate);
                    // In ra giá trị đã chọn dưới dạng chuỗi
                    data_filter.add(dateString);
                }
            }
            try {
                searchOfChucnang(data_filter);
            } catch (SQLException ex) {
                Logger.getLogger(SearchInStore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(SearchInStore.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            for (Component c : listComponentTimkiem) {
                if (c instanceof JTextField) {
                    JTextField text = (JTextField) c;
                    text.setText("");
                } else if (c instanceof JComboBox<?>) {
                    JComboBox<String> comboBox = (JComboBox<String>) c;
                    switch (MACHUCNANG) {
                        case "NULLThK": {
                            switch (thongkeloai) {
                                case 0:
                                    comboBox.setSelectedItem("Tất cả");

                                    break;
                                case 1:

                                    comboBox.setSelectedItem("1");
                                    break;
                            }
                            break;
                        }
                        default:
                            comboBox.setSelectedItem("Tất cả");
                            break;

                    }

                } else {

                    JSpinner date = (JSpinner) c;

                    date.setModel(new SpinnerDateModel());

                    JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(date, "yyyy/MM/dd");
                    date.setEditor(dateEditor);
                }
            }
            try {
                resetOfChucnang();
            } catch (SQLException ex) {
                Logger.getLogger(SearchInStore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(SearchInStore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        try {
            JButton menuItem = (JButton) e.getSource();
            menuItem.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
            menuItem.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
            menuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } catch (Exception ex) {
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        try {
            JButton menuItem = (JButton) e.getSource();
            menuItem.setForeground(Color.WHITE);
            menuItem.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        } catch (Exception ex) {
        }

    }
}
