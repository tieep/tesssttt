package GUI;

import BUS.SanPhamBUS;
import BUS.loaiSPBUS;
import DTO.SanPhamDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;

public class ChucNangSanPhamGUI extends JFrame implements MouseListener {

    private JPanel pnHeader, pnContent, pnThaoTac, pnDsAnh;
    private JLabel exit, lblThem, lblHuy, lblLuu;
    private JTextField txtMaSP, txtTenSP, txtDonGia;
    private JComboBox<String> cbxTenLoai;
    private SanPhamGUI spGUI;
    private ArrayList<String> imageName; // tên ảnh đã chọn
    private JLabel imageNameLabel; //hien thi ten anh chon
    JPanel imagePanelDefault; //khi chưa chọn ảnh

    private int width, height;
    private int height_row = 30;

    private Color normal = Color.decode("#0A3D62");
    private Color hover = Color.decode("#60A3BC");
    private Color tieude = Color.decode("#60A3BC");
    private Color chu = Color.decode("#FFFFFF");
    private Font font_tieude = new Font("Tahoma", Font.BOLD, 18);
    private Font font = new Font("Tahoma", Font.BOLD, 13);
    private Font font_family = new Font("Tahoma", Font.PLAIN, 12);

    public ChucNangSanPhamGUI(SanPhamGUI spGUI, int width, int height) {
        this.spGUI = spGUI;
        this.width = width;
        this.height = height;
        init();
        initPnHead();
        initContent();
        setVisible(true);
    }

    public ChucNangSanPhamGUI(int width, int height) {
        this.width = width;
        this.height = height;
        init();
        initPnHead();
        initContent();
        setVisible(true);
    }

    public void init() {
        setSize(width, height);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        setLocationRelativeTo(null);
    }

    public void initPnHead() {
        pnHeader = new JPanel();
        pnHeader.setLayout(new BorderLayout());
        pnHeader.setPreferredSize(new Dimension(width, 36));
        pnHeader.setBackground(Color.decode("#60A3BC"));
        pnHeader.setOpaque(true);

        ImageIcon icon = new ImageIcon("./src/images/exit_icon.png");
        exit = new JLabel(icon, JLabel.CENTER);
        exit.setPreferredSize(new Dimension(36, 36));
        exit.setBackground(Color.RED);
        exit.setOpaque(true);
        exit.addMouseListener(this);
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnHeader.add(exit, BorderLayout.EAST);
        add(pnHeader, BorderLayout.NORTH);
    }

    private void initContent() {
        pnContent = new JPanel();
        pnContent.setLayout(new BoxLayout(pnContent, BoxLayout.Y_AXIS));
        pnContent.setPreferredSize(new Dimension(width, height));
        pnContent.add(Box.createRigidArea(new Dimension(0, 10)));

        pnDsAnh = new JPanel();
        pnDsAnh.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnDsAnh.setMinimumSize(new Dimension(200, 250));
        pnDsAnh.setPreferredSize(new Dimension(200, 250));
        pnDsAnh.setMaximumSize(new Dimension(600, 500));

        imageName = new ArrayList<>();
        ImageIcon icon = new ImageIcon("./src/images/t-shirt.png");
        Image scaledImage = icon.getImage().getScaledInstance(174, 210, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(resizedIcon, JLabel.CENTER);
        imagePanelDefault = new JPanel();
        imagePanelDefault.setLayout(new BorderLayout());
        imagePanelDefault.setPreferredSize(new Dimension(174, 230));
        imagePanelDefault.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imagePanelDefault.add(label);
        pnDsAnh.add(imagePanelDefault);

        // Tạo JLabel để hiển thị tên hình ảnh đã chọn
        imageNameLabel = new JLabel("Không có ảnh", JLabel.CENTER); // Mặc định là không có hình ảnh được chọn
        imageNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageNameLabel.setPreferredSize(new Dimension(200, 30)); // Đặt kích thước cố định để tránh bị dịch chuyển

        JButton chooseImageButton = new JButton("Chọn ảnh");
        chooseImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseImageButton.setPreferredSize(new Dimension(100, 30));
        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnDsAnh.remove(imagePanelDefault);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true); // Cho phép chọn nhiều tệp
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "gif"));

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = fileChooser.getSelectedFiles();

                    int checkHinhTrung = 0; //biến xem khi sửa ảnh,tên ảnh có bị trùng/không trùng(1/0)
                    for (File selectedFile : selectedFiles) {
                        for (int i = 0; i < imageName.size(); i++) {
                            if (selectedFile.getName().equals(imageName.get(i))) {
                                checkHinhTrung = 1;
                                break;
                            }
                        }
                        if (checkHinhTrung == 1) {
                            JOptionPane.showMessageDialog(null,
                                    "Tên hình không được trùng !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        } else {
                            imageName.add(selectedFile.getName());
                            imageNameLabel.setText("Ảnh đã chọn: " + imageName); // Hiển thị tên tệp

                            addImage(selectedFile); // Thêm hình ảnh vào pnDsAnh
                        }
                    }
                }
            }
        });

        pnContent.add(pnDsAnh);
        pnContent.add(Box.createRigidArea(new Dimension(0, 5)));
        pnContent.add(imageNameLabel);
        pnContent.add(Box.createRigidArea(new Dimension(0, 5)));
        pnContent.add(chooseImageButton);
        pnContent.add(Box.createRigidArea(new Dimension(0, 20))); // Tạo khoảng cách giữa các hàng

        //------------------- thông tin cần chỉnh sửa --------------------------
        String thuocTinh[] = {"Loại:", "Mã sản phẩm:", "Tên sản phẩm:"};
        //------------ Mã loại -----------------
        JPanel pnMaLoai = new JPanel();
        pnMaLoai.setLayout(new GridLayout(1, 1, 10, 10));
        pnMaLoai.setPreferredSize(new Dimension(300, height_row));
        pnMaLoai.setMaximumSize(new Dimension(300, height_row));
        JLabel lbl1 = new JLabel(thuocTinh[0], JLabel.LEFT);
        lbl1.setFont(font);
        lbl1.setPreferredSize(new Dimension(100, height_row)); // Kích thước cố định
        //---- Tạo combobox cbxMaLoai
        loaiSPBUS maLoai = new loaiSPBUS();
        cbxTenLoai = new JComboBox<>();
        cbxTenLoai.setMinimumSize(new Dimension(200, height_row));
        cbxTenLoai.setPreferredSize(new Dimension(200, height_row));
        cbxTenLoai.setMaximumSize(new Dimension(200, height_row));
        for (int i = 0; i < maLoai.getList().size(); i++) {
            cbxTenLoai.addItem(maLoai.getList().get(i).getTENLOAI());
        }
        cbxTenLoai.setSelectedIndex(0);

        // Tạo ScrollPane cho combobox cbxMaLoai 
        cbxTenLoai.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                JComboBox combo = (JComboBox) e.getSource();
                BasicComboPopup popup = (BasicComboPopup) combo.getUI().getAccessibleChild(combo, 0);
                JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);

                // Đặt kích thước cuộn
                scrollPane.setPreferredSize(new Dimension(100, 100)); // Chiều cao tối đa của popup
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // Không cần xử lý gì ở đây
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // Không cần xử lý gì ở đây
            }
        });

        pnMaLoai.add(lbl1);
        pnMaLoai.add(cbxTenLoai);

        //------------ Mã sản phẩm -----------------
        JPanel pnMaSP = new JPanel();
        pnMaSP.setLayout(new GridLayout(1, 1, 10, 10));
        pnMaSP.setPreferredSize(new Dimension(300, height_row));
        pnMaSP.setMaximumSize(new Dimension(300, height_row));
        JLabel lblMaSP = new JLabel(thuocTinh[1], JLabel.LEFT);
        lblMaSP.setFont(font);
        lblMaSP.setPreferredSize(new Dimension(100, height_row)); // Kích thước cố định

        txtMaSP = new JTextField();
        txtMaSP.setMinimumSize(new Dimension(200, height_row)); // Kích thước cố định
        txtMaSP.setPreferredSize(new Dimension(200, height_row)); // Kích thước cố định
        txtMaSP.setMaximumSize(new Dimension(200, height_row)); // Kích thước cố định
        txtMaSP.setEnabled(false);
        pnMaSP.add(lblMaSP);
        pnMaSP.add(txtMaSP);

        //-------------- Tên sản phẩm -------------------
        JPanel pnTenSP = new JPanel();
        pnTenSP.setLayout(new GridLayout(1, 1, 10, 10));
        pnTenSP.setPreferredSize(new Dimension(300, height_row));
        pnTenSP.setMaximumSize(new Dimension(300, height_row));
        JLabel lblTenSP = new JLabel(thuocTinh[2], JLabel.LEFT);
        lblTenSP.setFont(font);
        lblTenSP.setPreferredSize(new Dimension(100, height_row)); // Kích thước cố định
        txtTenSP = new JTextField();
        txtTenSP.setMinimumSize(new Dimension(200, height_row));
        txtTenSP.setPreferredSize(new Dimension(200, height_row));
        txtTenSP.setMaximumSize(new Dimension(200, height_row)); // Kích thước cố định

        pnTenSP.add(lblTenSP);
        pnTenSP.add(txtTenSP);

        // Them các panel thuộc tính vào pnContent
        pnContent.add(pnMaLoai);
        pnContent.add(Box.createRigidArea(new Dimension(0, 10))); // Tạo khoảng cách giữa các hàng
        pnContent.add(pnMaSP);
        pnContent.add(Box.createRigidArea(new Dimension(0, 10))); // Tạo khoảng cách giữa các hàng
        pnContent.add(pnTenSP);
        pnContent.add(Box.createRigidArea(new Dimension(0, 10))); // Tạo khoảng cách giữa các hàng

        // Thêm pnContent vào JPanel chính
        this.add(pnContent, BorderLayout.CENTER); // Thêm panel chính vào JFrame hoặc JPanel cha
        setAutoMaSP(); // Đặt mã sản phẩm ban đầu

        //-------------------- các nút thêm, sửa, xoá --------------------------
        pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new FlowLayout());
        lblHuy = new JLabel("Huỷ", JLabel.CENTER);
        lblHuy.setPreferredSize(new Dimension(80, 30));
        lblHuy.setForeground(chu);
        lblHuy.setFont(font);
        lblHuy.setBackground(normal);
        lblHuy.setOpaque(true);
        lblHuy.addMouseListener(this);
        lblHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void addImage(File file) {
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(174, 230));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(174, 210));
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        JButton deleteButton = new JButton("X");
        deleteButton.setPreferredSize(new Dimension(20, 20));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnDsAnh.remove(imagePanel); // Xóa panel hình ảnh
                imageName.remove(file.getName());
                if (imageName.isEmpty()) {
                    pnDsAnh.add(imagePanelDefault);
                    imageNameLabel.setText("Không có ảnh");

                } else {
                    imageNameLabel.setText("Ảnh đã chọn: " + imageName);
                }
                revalidate();
                repaint();
            }
        });
        imagePanel.add(deleteButton, BorderLayout.NORTH);

        ImageIcon icon = new ImageIcon(file.getPath());
        Image scaledImage = icon.getImage().getScaledInstance(174, 210, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        pnDsAnh.add(imagePanel);
        revalidate();
        repaint();
    }

    //--------------------- Thao tac ---------------------------------------
    public void initThem() {
        JLabel title = new JLabel("Thêm sản phẩm", JLabel.CENTER);
        title.setFont(font_tieude);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        pnHeader.add(title, BorderLayout.WEST);

        lblThem = new JLabel("Thêm", JLabel.CENTER);
        lblThem.setPreferredSize(new Dimension(80, 30));
        lblThem.setForeground(Color.WHITE);
        lblThem.setFont(font);
        lblThem.setBackground(normal);
        lblThem.setOpaque(true);
        lblThem.addMouseListener(this);
        lblThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnThaoTac.add(lblThem);
        pnThaoTac.add(lblHuy);
        pnContent.add(pnThaoTac);
        setVisible(true);
    }

    public void initSua() {
        //-------------- Giá sản phẩm -------------------
        txtMaSP.setText("");
        JPanel pnGiaSP = new JPanel();
        pnGiaSP.setLayout(new GridLayout(1, 1, 10, 10));
        pnGiaSP.setPreferredSize(new Dimension(300, height_row));
        pnGiaSP.setMaximumSize(new Dimension(300, height_row));
        JLabel lblGiaSP = new JLabel("Đơn giá", JLabel.LEFT);
        lblGiaSP.setFont(font);
        lblGiaSP.setPreferredSize(new Dimension(100, height_row)); // Kích thước cố định
        txtDonGia = new JTextField();
        txtDonGia.setMinimumSize(new Dimension(200, height_row));
        txtDonGia.setPreferredSize(new Dimension(200, height_row));
        txtDonGia.setMaximumSize(new Dimension(200, height_row)); // Kích thước cố định

        pnGiaSP.add(lblGiaSP);
        pnGiaSP.add(txtDonGia);

        //thêm 2 thành phần mới vào pnContent
        pnContent.add(pnGiaSP);
        pnContent.add(Box.createRigidArea(new Dimension(0, 10))); // Tạo khoảng cách giữa các hàng

        JLabel title = new JLabel("Cập nhật tài khoản", JLabel.CENTER);
        title.setFont(font_tieude);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        pnHeader.add(title, BorderLayout.WEST);
        lblLuu = new JLabel("Lưu", JLabel.CENTER);
        lblLuu.setPreferredSize(new Dimension(80, 30));
        lblLuu.setForeground(chu);
        lblLuu.setFont(font);
        lblLuu.setBackground(normal);
        lblLuu.setOpaque(true);
        lblLuu.addMouseListener(this);
        lblLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnThaoTac.add(lblLuu);
        pnThaoTac.add(lblHuy);
        pnContent.add(pnThaoTac);
        setVisible(true);
    }
//-------------------------------- Hàm xử lý --------------------------------------

    public void setAutoMaSP() {
        SanPhamBUS spBUS = new SanPhamBUS();

        int count = 0;
        for (int i = 0; i < spBUS.getDsSP().size(); i++) {
            if (spBUS.getDsSP().get(i).getMaSP().startsWith("SP")) {
                count++;
            }
        }
        txtMaSP.setText("SP" + (count + 1));
    }

    public boolean check_TenSP(String tenSP) {
        if (tenSP.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Tên sản phẩm không được để trống, xin vui lòng nhập Tên sản phẩm !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean check_Gia(String giaSP) {
        if (giaSP.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Giá sản phẩm không được để trống, xin vui lòng nhập Giá sản phẩm!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            double gia = Double.parseDouble(giaSP);
            if (gia < 0) {
                JOptionPane.showMessageDialog(null,
                        "Giá sản phẩm phải lớn hơn hoặc bằng 0, xin vui lòng nhập lại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Giá sản phẩm phải là số, xin vui lòng nhập lại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void EditSP() {
        loaiSPBUS loai = new loaiSPBUS();
        String tenLoai = (String) cbxTenLoai.getSelectedItem();
        String maLoai = null;
        for (int i = 0; i < loai.getList().size(); i++) {
            if (loai.getList().get(i).getTENLOAI().equals(tenLoai)) {
                maLoai = loai.getList().get(i).getMALOAI();
                break;
            }
        }
        String maSP = txtMaSP.getText();
        String tenSP = txtTenSP.getText();

        String[] tenHinh = imageName.toArray(new String[imageName.size()]);
        String gia = txtDonGia.getText();

        if (check_TenSP(tenSP) && check_Gia(gia)) {
            SanPhamDTO sp = new SanPhamDTO(maSP, maLoai, tenSP, Double.parseDouble(gia), tenHinh, 1);
            dispose();
            JOptionPane.showMessageDialog(null,
                    "Bạn đã lưu sản phẩm thành công!", "Thông báo", JOptionPane.DEFAULT_OPTION);
            spGUI.EditSP(sp);
            revalidate();
            repaint();

        }
    }

    // Đổ dữ liệu sản phẩm đã chọn
    public void setTT() {
        loaiSPBUS loaiBUS = new loaiSPBUS();
        String tenLoai = "";
        for (int i = 0; i < loaiBUS.getList().size(); i++) {
            if (loaiBUS.getList().get(i).getMALOAI().equals(spGUI.selectedSP.getMaLoai())) {
                tenLoai = loaiBUS.getList().get(i).getTENLOAI();
            }
        }
        cbxTenLoai.setSelectedItem(tenLoai);
        txtMaSP.setText(spGUI.selectedSP.getMaSP());
        txtTenSP.setText(spGUI.selectedSP.getTenSP());
        txtDonGia.setText(spGUI.selectedSP.getPrice() + "");
        pnDsAnh.removeAll();
        if (spGUI.selectedSP.tenHinh.length == 0) {
            imageNameLabel.setText("Không có ảnh");
            pnDsAnh.add(imagePanelDefault);
        } else {
            for (int i = 0; i < spGUI.selectedSP.tenHinh.length; i++) {
                imageName.add(spGUI.selectedSP.tenHinh[i]);
                File file = new File("./src/images/" + spGUI.selectedSP.tenHinh[i]);
                addImage(file);
            }
            imageNameLabel.setText("Ảnh đã được chọn: " + imageName);
        }
        revalidate();
        repaint();
    }

    public void AddSP() {
        loaiSPBUS loai = new loaiSPBUS();
        String tenLoai = (String) cbxTenLoai.getSelectedItem();
        String maLoai = null;
        for (int i = 0; i < loai.getList().size(); i++) {
            if (loai.getList().get(i).getTENLOAI().equals(tenLoai)) {
                maLoai = loai.getList().get(i).getMALOAI();
                break;
            }
        }
        String maSP = txtMaSP.getText();
        String tenSP = txtTenSP.getText();
        String[] tenHinh = imageName.toArray(new String[imageName.size()]);
        SanPhamDTO sp = new SanPhamDTO(maSP, maLoai, tenSP, 0, tenHinh, 1);
        if (check_TenSP(tenSP)) {
            this.dispose();
            JOptionPane.showMessageDialog(null,
                    "Bạn đã thêm sản phẩm thành công !", "Thông báo", JOptionPane.DEFAULT_OPTION);
            spGUI.AddSP(sp);
            spGUI.revalidate(); // Cập nhật lại giao diện
            spGUI.repaint(); // Vẽ lại giao diện
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel lbl = (JLabel) e.getSource();
        if (lbl == exit || lbl == lblHuy) {
            this.dispose();
            spGUI.clear(-1);
            spGUI.selectedSP = new SanPhamDTO();
        }
        if (lbl == lblThem) {
            AddSP();
            spGUI.selectedSP = new SanPhamDTO();
            spGUI.clear(-1);
        }
        if (lbl == lblLuu) {
            EditSP();
            spGUI.selectedSP = new SanPhamDTO();
            spGUI.clear(-1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Không cần triển khai
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Không cần triển khai
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Không cần triển khai
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Không cần triển khai
    }

    public static void main(String[] args) {
        SanPhamGUI t = new SanPhamGUI(700, 700, Color.darkGray);
        ChucNangSanPhamGUI gui = new ChucNangSanPhamGUI(t,450, 650);
//        gui.initThem();
        gui.initSua();
    }
}
