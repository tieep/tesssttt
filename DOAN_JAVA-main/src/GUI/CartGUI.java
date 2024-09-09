package GUI;

import BUS.BUS_qlkh;
import BUS.ChitietHD_BUS;
import BUS.Hoadon_BUS;
import BUS.SizeBUS;
import BUS.chitietsanpham_BUS;
import DAO.DAO_chitietsanpham;
import DTO.ChitietHD_DTO;
import DTO.Hoadon_DTO;
import DTO.SanPhamDTO;
import DTO.SizeDTO;
import DTO.chitietsanpham_DTO;
import DTO.model_qlkh;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class CartGUI extends JPanel implements MouseListener {

    private int width, height;
    private JPanel wrapHeaderCart;
    private JPanel wrapCenterCart;
    private JPanel wrapBottomCart;
    private JScrollPane scrollPane;
    private JButton pay;
    private JLabel[] updateQuantity;
    private JLabel[] btnMinusQuantity;
    private JLabel[] showQuantity;
    private JLabel[] btnPlusQuantity;
    private JButton[] remove;
    private JLabel[] JL_value;
    private JLabel[] JL_InforCus;
    private model_qlkh kh;
    private JCheckBox checkBox1;
    public static ArrayList<SanPhamDTO> dsspAdd = new ArrayList<SanPhamDTO>();
    public static ArrayList<chitietsanpham_DTO> dsctspAdd = new ArrayList<chitietsanpham_DTO>();

    public CartGUI(int w, int h) {
        width = w;
        height = h;
        init();
    }

    private void init() {
        headerCart();

        centerCart();

        bottomCart();
        setLayout(new BorderLayout(0, 10));
        add(wrapHeaderCart, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);
        add(wrapBottomCart, BorderLayout.SOUTH);
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#60A3BC")));
    }

    private void headerCart() {
        wrapHeaderCart = new JPanel(new BorderLayout(0, 0));
        int hHeader = height / 11;
        wrapHeaderCart.setPreferredSize(new Dimension(width, hHeader));

        JPanel wrapSearchMAKH = new JPanel(new FlowLayout(3, 10, 0));

        JPanel searchMAKH = new JPanel(new GridLayout(2, 1));

        searchMAKH.setPreferredSize(new Dimension(width / 6, hHeader));
        JLabel MAKH = new JLabel("Tìm theo MAKH", JLabel.CENTER);
        JTextField inputMAKH = new JTextField((kh == null) ? "" : (kh.getMakh() + ""));

        searchMAKH.add(MAKH);
        searchMAKH.add(inputMAKH);

        JButton iconSearch = new JButton(new ImageIcon("./src/images/search_icon.png"));
        iconSearch.setFocusPainted(false);
        iconSearch.setBackground(Color.decode("#D9D9D9"));

        iconSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String MAKH = inputMAKH.getText();
                String regexMAKH = "^[0-9]+$";
                if (!MAKH.equals("")) {
                    if (MAKH.matches(regexMAKH)) {
                        BUS_qlkh khBUS = new BUS_qlkh();
                        for (model_qlkh k : khBUS.getlist()) {
                            if (k.getMakh() == Integer.parseInt(MAKH)) {
                                kh = k;
                            }
                        }
                        if (kh == null) {
                            JOptionPane.showMessageDialog(null, "Khách hàng không tồn tại", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JL_InforCus[0].setText(kh.getTen());
                            JL_InforCus[1].setText(kh.getDiem() + "");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "MAKH chỉ chứa số", "Sai định dạng", JOptionPane.WARNING_MESSAGE);
                        inputMAKH.requestFocus();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Mời nhập MAKH.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    JL_InforCus[0].setText(" ");
                    JL_InforCus[1].setText("");
                    inputMAKH.requestFocus();
                }
            }
        });
        wrapSearchMAKH.add(searchMAKH);
        wrapSearchMAKH.add(iconSearch);

        JPanel wrapInforKH = new JPanel(new GridLayout(2, 1));

        String[] columnNames = {"Tên khách hàng", "Điểm"};
        JL_InforCus = new JLabel[2];
        for (int i = 0; i < 2; i++) {
            JPanel line = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

            switch (i) {
                case 0:
                    JLabel col1 = new JLabel(columnNames[0], JLabel.CENTER);
                    col1.setPreferredSize(new Dimension(width / 3, (int) col1.getPreferredSize().getHeight()));
                    line.add(col1);
                    line.add(new JLabel(columnNames[1], JLabel.CENTER));

                    break;
                case 1:
                    JL_InforCus[0] = new JLabel((kh == null) ? " " : kh.getTen(), JLabel.CENTER);
                    JL_InforCus[0].setPreferredSize(new Dimension(width / 3, (int) JL_InforCus[0].getPreferredSize().getHeight()));
                    line.add(JL_InforCus[0]);
                    JL_InforCus[1] = new JLabel((kh == null) ? " " : kh.getDiem() + "", JLabel.CENTER);
                    line.add(JL_InforCus[1]);

                    break;
            }
            wrapInforKH.add(line);
        }

        wrapInforKH.setPreferredSize(new Dimension(width / 2, hHeader));

//        wrapInforKH.add(TENKH);
//        wrapInforKH.add(POINT);
        checkBox1 = new JCheckBox("Dùng điểm");
        checkBox1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dsspAdd.size() != 0) {
                    if (kh.getDiem() == 0) {
                        JOptionPane.showMessageDialog(null, "Không hợp lệ!\nKhách hàng không có điểm tích lũy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        checkBox1.setSelected(false);
                    } else {
                        afterChecked();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể dùng điểm do chưa có sản phẩm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    checkBox1.setSelected(false);
                }

            }
        });

        setPreferredSize(new Dimension(width, height));

        wrapHeaderCart.add(wrapSearchMAKH, BorderLayout.WEST);
        wrapHeaderCart.add(wrapInforKH, BorderLayout.CENTER);
        wrapHeaderCart.add(checkBox1, BorderLayout.EAST);

    }

    private void afterChecked() {
        double discount = kh.getDiem() * 1000;
        double subTotal = Double.parseDouble(JL_value[0].getText());
        double total = Double.parseDouble(JL_value[2].getText());
        if (checkBox1.isSelected()) {

            if (discount > subTotal) {
                sumTotal(subTotal);
                JL_InforCus[1].setText((int) (discount - subTotal) / 1000 + "");
            } else {
                JL_InforCus[1].setText(0 + "");
                sumTotal(discount);
            }
        } else {
            JL_InforCus[1].setText(kh.getDiem() + "");
            sumTotal(0);
        }

    }

    private void centerCart() {

        wrapCenterCart = new JPanel(new FlowLayout(1, 0, 10));

        int heightProduct = 120;

        wrapCenterCart.setPreferredSize(new Dimension(width - 10, (int) heightProduct * (dsctspAdd.size() + 1)));
        btnMinusQuantity = new JLabel[dsctspAdd.size()];
        showQuantity = new JLabel[dsctspAdd.size()];
        btnPlusQuantity = new JLabel[dsctspAdd.size()];

        remove = new JButton[dsctspAdd.size()];
        if (!dsspAdd.isEmpty()) {
            for (SanPhamDTO s : dsspAdd) {
                for (int i = 0; i < dsctspAdd.size(); i++) {
                    if (s.getMaSP().equals(dsctspAdd.get(i).getMASP())) {
                        wrapCenterCart.add(product(i, s, dsctspAdd.get(i).getMASIZE(), dsctspAdd.get(i).getSoluong()));
                    }
                }
            }
        }

        scrollPane = new JScrollPane(wrapCenterCart);

        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#60A3BC")));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }

    private JPanel product(int index, SanPhamDTO sp, String masizeluachon, int quantity) {
        System.out.println("chayyyy");
        JPanel product = new JPanel(new BorderLayout(10, 0));

        JPanel wrapCenter = new JPanel(new GridLayout(3, 1, 0, 5));

        JLabel price = new JLabel((int) sp.getPrice() + "", JLabel.CENTER);
        JLabel titleSize = new JLabel("Size", JLabel.CENTER);

        remove[index] = new JButton(new ImageIcon("./src/images/remove_icon.png"));
        remove[index].addMouseListener(this);
        remove[index].setFocusPainted(false);
        remove[index].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel wrapName = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapName.setBackground(Color.white);
        wrapName.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        JLabel name = new JLabel(sp.getTenSP(), JLabel.CENTER);
        name.setOpaque(true);
        name.setBackground(Color.white);
        name.setForeground(Color.decode("#0A3D62"));

        name.setFont(new Font("Tahoma", Font.PLAIN, 18));
        wrapName.add(name);

        JPanel wrapSize = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        wrapSize.add(titleSize);
        wrapSize.setBackground(Color.white);
        titleSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SizeBUS sizeBUS = new SizeBUS();
        SizeDTO sizeDTO = sizeBUS.getSizeDTO(masizeluachon);
        JLabel sizeSelected = new JLabel(sizeDTO.getTENSIZE(), JLabel.CENTER);
        sizeSelected.setFont(new Font("Tahoma", Font.PLAIN, 16));
        wrapSize.add(sizeSelected);

        JPanel wrapPrice_Quantity = new JPanel(new BorderLayout());
        wrapPrice_Quantity.setBackground(Color.white);
        wrapPrice_Quantity.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        JPanel wrapQuantity = new JPanel(new GridLayout(1, 3, 2, 0));

        wrapQuantity.setPreferredSize(new Dimension(width / 9, 20));

        btnMinusQuantity[index] = new JLabel("-", JLabel.CENTER);
        btnMinusQuantity[index].setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMinusQuantity[index].setOpaque(true);
        btnMinusQuantity[index].setBackground(Color.decode("#60A3BC"));
        btnMinusQuantity[index].setForeground(Color.white);
        btnMinusQuantity[index].addMouseListener(this);
        btnMinusQuantity[index].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        chitietsanpham_BUS ctsp_bus = null;
        try {
            ctsp_bus = new chitietsanpham_BUS();
        } catch (SQLException ex) {
            Logger.getLogger(CartGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        showQuantity[index] = new JLabel(quantity + "", JLabel.CENTER);
        showQuantity[index].setFont(new Font("Tahoma", Font.BOLD, 14));
        showQuantity[index].setOpaque(true);
        showQuantity[index].setBackground(Color.white);
        showQuantity[index].setName(ctsp_bus.getSoLuong(sp.getMaSP(), masizeluachon) + "");

        btnPlusQuantity[index] = new JLabel("+", JLabel.CENTER);
        btnPlusQuantity[index].setFont(new Font("Tahoma", Font.BOLD, 14));
        btnPlusQuantity[index].setOpaque(true);
        btnPlusQuantity[index].setBackground(Color.decode("#60A3BC"));
        btnPlusQuantity[index].setForeground(Color.white);
        btnPlusQuantity[index].addMouseListener(this);
        btnPlusQuantity[index].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        wrapQuantity.add(btnMinusQuantity[index]);
        wrapQuantity.add(showQuantity[index]);
        wrapQuantity.add(btnPlusQuantity[index]);

        price.setFont(new Font("Tahoma", Font.PLAIN, 16));
        price.setOpaque(true);
        price.setBackground(Color.white);
        price.setForeground(Color.decode("#60A3BC"));
        wrapPrice_Quantity.add(price, BorderLayout.WEST);
        wrapPrice_Quantity.add(wrapQuantity, BorderLayout.EAST);

        wrapCenter.add(wrapName);
        wrapCenter.add(wrapSize);
        wrapCenter.add(wrapPrice_Quantity);
//        wrapSouth.add();
        remove[index].setPreferredSize(new Dimension(width / 12, (int) product.getPreferredSize().getHeight()));
        remove[index].setOpaque(true);
        remove[index].setBackground(Color.decode("#D9D9D9"));
        wrapCenter.setBackground(Color.white);
        product.setBackground(Color.white);
        product.add(wrapCenter, BorderLayout.CENTER);
        product.add(remove[index], BorderLayout.EAST);
        product.setPreferredSize(new Dimension(width * 2 / 3, (int) product.getPreferredSize().getHeight()));

        return product;
    }

    private void bottomCart() {
        wrapBottomCart = new JPanel(new BorderLayout(0, 0));

        JPanel centerBottom = new JPanel(new GridLayout(3, 1, 0, 0));

        pay = new JButton("Thanh toán");
        pay.setBackground(Color.decode("#0A3D62"));
        pay.setForeground(Color.decode("#60A3BC"));
        pay.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pay.addMouseListener(this);
        pay.setFocusPainted(false);
        pay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel[] JL_title = new JLabel[3];
        JL_value = new JLabel[3];
        JPanel[] JP_line = new JPanel[3];
        String[] title = {"Tổng cộng", "Giảm giá", "Thành tiền"};
        String[] values = null;
        sumTotal(0);

        for (int i = 0; i < JP_line.length; i++) {
            JP_line[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

            JP_line[i].setBackground(Color.white);
            JL_title[i] = new JLabel(title[i]);

            if (i != JP_line.length - 1) {
                JL_title[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
                JL_value[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
            } else {
                JL_title[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
                JL_value[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
            }

            JL_title[i].setPreferredSize(new Dimension(width / 8, (int) JL_title[i].getPreferredSize().getHeight()));
            JP_line[i].add(JL_title[i]);
            JP_line[i].add(JL_value[i]);

            centerBottom.add(JP_line[i]);
        }
        wrapBottomCart.setBackground(Color.blue);
        wrapBottomCart.add(centerBottom, BorderLayout.CENTER);
        wrapBottomCart.add(pay, BorderLayout.EAST);
    }

    public void sumTotal(double discount) {
        String[] values = null;
        if (dsspAdd.isEmpty()) {
            values = new String[]{"", "", ""};
        } else {
            double sum = 0;
            for (SanPhamDTO s : dsspAdd) {
                for (chitietsanpham_DTO c : dsctspAdd) {
                    if (s.getMaSP().equals(c.getMASP())) {
                        sum += (s.getPrice() * c.getSoluong());
                    }
                }
            }
            values = new String[]{(int) sum + "", (int) discount + "", (int) (sum - discount) + ""};
        }

        for (int i = 0; i < values.length; i++) {
            if (JL_value[i] == null) {
                JL_value[i] = new JLabel(values[i]);
            } else {
                JL_value[i].setText(values[i]);
            }
        }
    }

    private void refeshCart() {
        this.removeAll();
        kh = null;
        dsspAdd.clear();
        dsctspAdd.clear();

        init();
        this.revalidate();
        this.repaint();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Cart GUI");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new CartGUI(800, 600));
        f.pack(); // Adjust size to fit components
        f.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == pay) {
            if (kh == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập khách hàng của hóa đơn này", "", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    chitietsanpham_BUS ctspBUS = new chitietsanpham_BUS();
                    ChitietHD_BUS ctBUS = new ChitietHD_BUS();
                    Hoadon_BUS hdBUS = new Hoadon_BUS();
                    String maHD = hdBUS.createMAHD();
                    String ngayHD = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    String thoigian = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    String maNV = StoreScreen.tkUSER.getMaNV();
                    int giamgia = Integer.parseInt(JL_value[1].getText());
                    int tongTien = Integer.parseInt(JL_value[2].getText());

                    Hoadon_DTO hd = new Hoadon_DTO(maHD, ngayHD, thoigian, kh.getMakh(), maNV, giamgia, tongTien);
                    hdBUS.addInSQL(hd);

                    for (chitietsanpham_DTO c : dsctspAdd) {
                        Double price = null;

                        for (SanPhamDTO s : dsspAdd) {
                            if (s.getMaSP().equals(c.getMASP())) {
                                price = s.getPrice();
                            }
                        }
                        ChitietHD_DTO cthd = new ChitietHD_DTO(maHD, c.getMASP(), c.getMASIZE(), c.getSoluong(), price);
                        ctBUS.addInSQL(cthd);
                        for (chitietsanpham_DTO ct : ctspBUS.getlist()) {
                            if (c.getMASP().equals(ct.getMASP()) && c.getMASIZE().equals(ct.getMASIZE())) {
                                chitietsanpham_DTO ctsp = new chitietsanpham_DTO(ct.getMASP(), ct.getMASIZE(), ct.getSoluong() - c.getSoluong());
                                ctspBUS.updateAfterTT(ctsp);
                            }
                        }

                    }

                    if (checkBox1.isSelected()) {
                        kh.setDiem(Integer.parseInt(JL_InforCus[1].getText()));
                    } else {
                        kh.setDiem((int) (kh.getDiem() + tongTien / 10000));
                    }
                    BUS_qlkh khBUS = new BUS_qlkh();
                    khBUS.set(kh);

                    refeshCart();
                    JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    Object[] options = {"Có", "Không"};
                    int r1 = JOptionPane.showOptionDialog(null, "Bạn có muốn in hóa đơn này", "In hóa đơn", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (r1 == JOptionPane.YES_OPTION) {
                        try {
                            inPDF in = new inPDF(maHD);
                            JOptionPane.showMessageDialog(null,
                                    "In thành công!");
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,
                                    "In thất bại!");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null,
                                    "In thất bại!");
                        }

                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Thêm hóa đơn thất bại! Vui lòng thử lại", "Lỗi", JOptionPane.WARNING_MESSAGE);
                }
            }

            return;
        } else if (e.getSource() instanceof JLabel) {
            switch (((JLabel) e.getSource()).getText()) {
                case "-":
                    for (int i = 0; i < btnMinusQuantity.length; i++) {
                        if (e.getSource() == btnMinusQuantity[i]) {
                            int valueCurrent = Integer.parseInt(showQuantity[i].getText());
                            if (valueCurrent - 1 != 0) {
                                showQuantity[i].setText((valueCurrent - 1) + "");
                                dsctspAdd.get(i).setSoluong(valueCurrent - 1);
                            } else {
                                break;
                            }

                        }
                    }

                    break;
                case "+":
                    for (int i = 0; i < btnPlusQuantity.length; i++) {
                        if (e.getSource() == btnPlusQuantity[i]) {
                            int valueCurrent = Integer.parseInt(showQuantity[i].getText());
                            if (valueCurrent + 1 <= Integer.parseInt(showQuantity[i].getName())) {
                                showQuantity[i].setText((valueCurrent + 1) + "");
                                dsctspAdd.get(i).setSoluong(valueCurrent + 1);
                            } else {
                                break;
                            }

                        }
                    }

                    break;
                default:
                    break;
            }
            if (kh != null) {
                JL_InforCus[0].setText(kh.getTen());
                JL_InforCus[1].setText(kh.getDiem() + "");
                checkBox1.setSelected(false);
            }
            sumTotal(0);

        } else {
            for (int i = 0; i < remove.length; i++) {
                if (e.getSource() == remove[i]) {

                    String MASPremove = dsctspAdd.get(i).getMASP();
                    dsctspAdd.remove(i);
                    boolean flag = false;
                    for (chitietsanpham_DTO c : dsctspAdd) {
                        if (c.getMASP().equals(MASPremove)) {
                            flag = true;
                            break;
                        }
                    }

                    // Nếu sản phẩm không còn trong dsctspAdd, loại bỏ khỏi dsspAdd
                    if (!flag) {
                        dsspAdd.removeIf(s -> s.getMaSP().equals(MASPremove));
                    }

                    this.removeAll(); // Xóa tất cả các thành phần hiện tại

                    // Thêm lại các sản phẩm và các thành phần liên quan
                    init();
                    // Cập nhật kích thước và giao diện

                    this.revalidate(); // Cập nhật layout của wrapCenterCart
                    this.repaint(); // Vẽ lại wrapCenterCart

                    break;
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() instanceof JLabel) {
            JLabel jl = (JLabel) e.getSource();
            jl.setOpaque(true);
            jl.setBackground(Color.decode("#0A3D62"));
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel jl = (JLabel) e.getSource();
            jl.setOpaque(true);
            jl.setBackground(Color.decode("#60A3BC"));
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
