package GUI;

import BUS.Nhanvien_BUS;
import BUS.TaiKhoanBUS;
import BUS.quyenBUS;
import DTO.TaiKhoanDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;

public class TaiKhoanGUI extends JPanel implements MouseListener {

    JPanel pnThaoTacTK_main;
    TaiKhoanBUS tkBUS = new TaiKhoanBUS();
    ArrayList<TaiKhoanDTO> dstk = new ArrayList<TaiKhoanDTO>();
    TaiKhoanDTO selectedTK = new TaiKhoanDTO();
    int choiceSua = 0;

    private JPanel pnHead;
    private JPanel pnContentParent;
    private JPanel[] pnContent;
    private int width, height;
    private Color normal = Color.decode("#0A3D62");
    Color hover = Color.decode("#60A3BC");

    Font font = new Font("Tahoma", Font.BOLD, 13);
    Font font_family = new Font("Tahoma", Font.PLAIN, 12);

    public TaiKhoanGUI() {
        dstk = tkBUS.getDsTK();//truyen du lieu vao lop GUI
        init();
    }

    public TaiKhoanGUI(int width, int height) {
        this.width = width;
        this.height = height;

        dstk = tkBUS.getDsTK();//truyen du lieu vao lop GUI

        init();

    }

    public void init() {
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        JPanel mainPanel = initPnContentParent(dstk);
        pnThaoTacTK_main = new JPanel();
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(pnThaoTacTK_main, BorderLayout.EAST);
    }

    private JPanel initPnContentParent(ArrayList<TaiKhoanDTO> dstk) {
        pnHead = new JPanel();
        pnHead.setLayout(new GridLayout(1, 7));
        pnHead.setPreferredSize(new Dimension(width - 450, 35)); //---------------
        Border borderBottom = BorderFactory.createMatteBorder(0, 0, 2, 0, normal);
        pnHead.setBorder(borderBottom);

        String[] thuocTinh = {"Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Ngày tạo", "Mã Quyền", "Tình Trạng", "Mở/Khoá tài khoản"};
        JLabel[] lblHead = new JLabel[thuocTinh.length];
        for (int i = 0; i < lblHead.length; i++) {
            lblHead[i] = new JLabel(thuocTinh[i], JLabel.CENTER);
            lblHead[i].setFont(font);
            lblHead[i].setPreferredSize(new Dimension(width, 35)); //---------------
            pnHead.add(lblHead[i]);
        }

        pnContentParent = new JPanel();
        pnContentParent.setLayout(new BoxLayout(pnContentParent, BoxLayout.Y_AXIS));
        borderBottom = BorderFactory.createMatteBorder(0, 0, 1, 0, normal);

        pnContent = new JPanel[dstk.size()];
        for (int i = 0; i < dstk.size(); i++) {
            final int id = i;
            pnContent[i] = new JPanel();
            pnContent[i].setLayout(new GridLayout(1, 7));
            pnContent[i].setPreferredSize(new Dimension(width - 450, 35)); // Giữ chiều cao cố định
            pnContent[i].setMaximumSize(new Dimension(width, 35)); //----------------
            pnContent[i].setBorder(borderBottom);
            pnContent[i].setBackground(Color.decode("#FFFFFF"));
            pnContent[i].setOpaque(true);

            JLabel[] lblContent = new JLabel[thuocTinh.length];
            String[] value;

            value = new String[]{dstk.get(i).getMaNV(), dstk.get(i).getUsername(), "********",
                dstk.get(i).getNgayTao(), dstk.get(i).getMaQuyen(), (dstk.get(i).getState() == 1) ? "Đang hoạt động" : "Đã khoá"};

            for (int j = 0; j < thuocTinh.length - 1; j++) {
                lblContent[j] = new JLabel(value[j], JLabel.CENTER);
                lblContent[j].setPreferredSize(new Dimension(width, 35)); //----------------
                lblContent[j].setForeground(normal);
                lblContent[j].setFont(font_family);
                lblContent[0].setFont(new Font("Tahoma", Font.BOLD, 12));
                pnContent[i].add(lblContent[j]);
            }


            ImageIcon icon = new ImageIcon("./src/images/User-Lock.png");

            Image scaledImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
            lblContent[lblContent.length - 1] = new JLabel(resizedIcon, JLabel.CENTER);
            lblContent[lblContent.length - 1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            pnContent[i].add(lblContent[lblContent.length - 1]);
            pnContent[i].addMouseListener(this);
            pnContentParent.add(pnContent[i]);

            lblContent[lblContent.length - 5].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1){
                        lblContent[lblContent.length - 5].setText("********");
                    }
                    if (e.getClickCount() == 2) {
                        lblContent[lblContent.length - 5].setText(dstk.get(id).getPassword());
                    }
                }
            });
            
            lblContent[lblContent.length - 1].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (dstk.get(id).getState() == 1) {
                        JPanel panel = new JPanel();
                        panel.setForeground(Color.RED); // Thiết lập màu chữ cho header

                        Object[] options = {"Có", "Không"};
                        int result = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn khoá user này không?", "Xác nhận", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                        if (result == JOptionPane.YES_OPTION) {
                            dstk.get(id).setState(0);
                            lblContent[lblContent.length - 2].setText("Đã khoá");
                        }

                    } else {
                        Object[] options = {"Có", "Không"};
                        int result = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn mở khoá cho user này không?", "Xác nhận", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                        if (result == JOptionPane.YES_OPTION) {
                            dstk.get(id).setState(1);
                            lblContent[lblContent.length - 2].setText("Đang hoạt động");
                        }
                    }
                    tkBUS.set(dstk.get(id));
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    lblContent[lblContent.length - 1].setBackground(Color.LIGHT_GRAY);
                    lblContent[lblContent.length - 1].setOpaque(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    lblContent[lblContent.length - 1].setBackground(null);
                }
            });
        }
        JScrollPane scrollPane = new JScrollPane(pnContentParent);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(pnHead, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }
//-------------------------------------------- Giao dien thao tac them sua xoa ---------------------------------------------   
    private JPanel pnHeaderThaoTac, pnContentThaoTac, pnChucNangThaoTac;
    JLabel exit;
    JTextField txtUsername;
    JPasswordField pwfMK; //mật khẩu
    JComboBox<String> cbxMaQuyen;
    JComboBox<String> cbxMaNV;
    JLabel lblHuy, lblThem, lblCapNhat; //nút thêm, lưu, huy

    Color tieude = Color.decode("#60A3BC");
    Color chu = Color.decode("#FFFFFF"); //mau chu cua 2 nút huy, them
    Font font_tieude = new Font("Tahoma", Font.BOLD, 18);

    public void initPnThaoTacTK(int width, int height) throws SQLException {
        JPanel pnThaoTacTK = new JPanel();
        pnThaoTacTK.setLayout(new BorderLayout());
        pnThaoTacTK.setPreferredSize(new Dimension(width, height));
        pnThaoTacTK.setMaximumSize(new Dimension(width, height));

        pnHeaderThaoTac = new JPanel();
        pnHeaderThaoTac.setLayout(new BorderLayout());
        pnHeaderThaoTac.setPreferredSize(new Dimension(width, 36));
        pnHeaderThaoTac.setBackground(Color.decode("#60A3BC"));
        pnHeaderThaoTac.setOpaque(true);
        ImageIcon icon = new ImageIcon("./src/images/exit_icon.png");
        exit = new JLabel(icon, JLabel.CENTER);
        exit.setPreferredSize(new Dimension((int) pnHeaderThaoTac.getPreferredSize().getHeight(),
                (int) pnHeaderThaoTac.getPreferredSize().getHeight()));
        exit.setBackground(Color.red);
        exit.setOpaque(true);
        exit.addMouseListener(this);
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnHeaderThaoTac.add(exit, BorderLayout.EAST);
        pnThaoTacTK.add(pnHeaderThaoTac, BorderLayout.NORTH);

        pnContentThaoTac = new JPanel();
        pnContentThaoTac.setLayout(new FlowLayout());
        pnContentThaoTac.setPreferredSize(new Dimension(width, height));
        pnThaoTacTK.add(pnContentThaoTac, BorderLayout.CENTER);

        txtUsername = new JTextField();

        txtUsername = new JTextField();
        txtUsername.setFont(font_family);
        txtUsername.setBorder(new EmptyBorder(0, 5, 0, 5));
        txtUsername.setPreferredSize(new Dimension(150, 20));
        initContentThaoTac();

        pnChucNangThaoTac = new JPanel();
        pnChucNangThaoTac.setLayout(new FlowLayout(1, 20, 20));
        pnChucNangThaoTac.setPreferredSize(new Dimension(width, 50));
        pnContentThaoTac.add(pnChucNangThaoTac);

        //Chuc nang
        lblHuy = new JLabel("Huỷ", JLabel.CENTER);
        lblHuy.setPreferredSize(new Dimension(80, 30));
        lblHuy.setForeground(chu);
        lblHuy.setFont(font);
        lblHuy.setBackground(normal);
        lblHuy.setOpaque(true);
        lblHuy.addMouseListener(this);
        lblHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnThaoTacTK_main.add(pnThaoTacTK);
    }

    private void initContentThaoTac() throws SQLException {
        //Mã nhân viên
        Nhanvien_BUS nv = new Nhanvien_BUS();
        cbxMaNV = new JComboBox<>();
        for (int i = 0; i < nv.listnv.size(); i++) {
            cbxMaNV.addItem(nv.listnv.get(i).getManv());
        }
        cbxMaNV.setSelectedIndex(0);

        // ----------Tạo ScrollPane cho combobox cbxMaNV -------//
        cbxMaNV.addPopupMenuListener(new PopupMenuListener() {
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

        //Mã quyền
        quyenBUS quyen = new quyenBUS();
        cbxMaQuyen = new JComboBox<>();
        for (int i = 0; i < quyen.getList().size(); i++) {
            cbxMaQuyen.addItem(quyen.getList().get(i).getMAQUYEN());
        }
        cbxMaQuyen.setSelectedIndex(0);

        // ----------Tạo ScrollPane cho combobox cbxMaQuyen -------//
        cbxMaQuyen.addPopupMenuListener(new PopupMenuListener() {
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

        //----------------Mật khẩu-------------------------------------
        pwfMK = new JPasswordField();
        pwfMK.setPreferredSize(new Dimension(130, 30));
        pwfMK.setBorder(new EmptyBorder(0, 5, 0, 5));

        // Tạo JLabel để chuyển đổi hiển thị mật khẩu
        ImageIcon eyeClosedIcon = new ImageIcon("./src/images/eye-off-icon.png");
        ImageIcon eyeOpenIcon = new ImageIcon("./src/images/eye-icon.png");

        Image closedImage = eyeClosedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image openImage = eyeOpenIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        ImageIcon resizedClosedIcon = new ImageIcon(closedImage);
        ImageIcon resizedOpenIcon = new ImageIcon(openImage);

        JLabel lbl = new JLabel(resizedClosedIcon, JLabel.CENTER);
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Sử dụng biến boolean để theo dõi trạng thái hiển thị mật khẩu
        final boolean[] isPasswordVisible = {false};

        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isPasswordVisible[0]) {
                    // Ẩn mật khẩu
                    pwfMK.setEchoChar('\u2022'); // Đặt lại echo character
                    lbl.setIcon(resizedClosedIcon); // Thay đổi hình ảnh
                    isPasswordVisible[0] = false; // Cập nhật trạng thái
                } else {
                    // Hiển thị mật khẩu như văn bản bình thường
                    pwfMK.setEchoChar((char) 0); // Tắt echo
                    lbl.setIcon(resizedOpenIcon); // Thay đổi hình ảnh
                    isPasswordVisible[0] = true; // Cập nhật trạng thái
                }
            }
        });

        JPanel pnMK = new JPanel();
        pnMK.setPreferredSize(new Dimension(150, 20));
        pnMK.setBackground(Color.WHITE);
        pnMK.setOpaque(true);
        pnMK.setLayout(new BorderLayout());
        pnMK.add(pwfMK, BorderLayout.WEST);
        pnMK.add(lbl, BorderLayout.EAST);
        //-------------------------------------------------------------------
        String[] text = {"Mã nhân viên", "Username", "Password", "Mã quyền"};
        JLabel[] lbltext = new JLabel[4];
        for (int i = 0; i < lbltext.length; i++) {
            lbltext[i] = new JLabel(text[i]);
            lbltext[i].setFont(font);
            lbltext[i].setForeground(normal);
        }

        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.ipadx = 5;
        gbc.ipady = 5;
        for (int i = 0; i < lbltext.length; i++) {
            gbc.gridy = i;
            gbc.gridx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(lbltext[i], gbc);
        }
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cbxMaNV, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtUsername, gbc);

        gbc.gridy = lbltext.length - 2;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(pnMK, gbc);

        gbc.gridy = lbltext.length - 1;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cbxMaQuyen, gbc);

        pnContentThaoTac.add(panel);
    }

    public void initThem() {
        JLabel title = new JLabel("Thêm tài khoản", JLabel.CENTER);
        title.setFont(font_tieude);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        pnHeaderThaoTac.add(title, BorderLayout.WEST);

        lblThem = new JLabel("Thêm", JLabel.CENTER);
        lblThem.setPreferredSize(new Dimension(80, 30));
        lblThem.setForeground(Color.WHITE);
        lblThem.setFont(font);
        lblThem.setBackground(normal);
        lblThem.setOpaque(true);
        lblThem.addMouseListener(this);
        lblThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnChucNangThaoTac.add(lblThem);
        pnChucNangThaoTac.add(lblHuy);

    }

    public void initSua() {
        choiceSua = 1;
        JLabel title = new JLabel("Cập nhật tài khoản", JLabel.CENTER);
        title.setFont(font_tieude);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        pnHeaderThaoTac.add(title, BorderLayout.WEST);
        cbxMaNV.setEnabled(false);
        setTT();

        lblCapNhat = new JLabel("Lưu", JLabel.CENTER);
        lblCapNhat.setPreferredSize(new Dimension(80, 30));
        lblCapNhat.setForeground(chu);
        lblCapNhat.setFont(font);
        lblCapNhat.setBackground(normal);
        lblCapNhat.setOpaque(true);
        lblCapNhat.addMouseListener(this);
        lblCapNhat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnChucNangThaoTac.add(lblCapNhat);
        pnChucNangThaoTac.add(lblHuy);

    }

    public void setTT() {
        cbxMaNV.setSelectedItem(selectedTK.getMaNV());
        txtUsername.setText(selectedTK.getUsername());
        pwfMK.setText(selectedTK.getPassword());
        cbxMaQuyen.setSelectedItem(selectedTK.getMaQuyen());
        System.out.println(selectedTK.getMaNV());
    }

    public void clear(int x) {
        for (int i = 0; i < dstk.size(); i++) {
            if (i != x) {
                pnContent[i].setBackground(Color.WHITE);
            }
        }
    }

//--------------------------Hàm xủ lý -------------------------------------------
    public boolean check_Usename_Password(String username, String password) {
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Username không được để trống, xin vui lòng nhập username !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        for (int i = 0; i < dstk.size(); i++) {
            if (dstk.get(i).getUsername().equalsIgnoreCase(username)) {
                JOptionPane.showMessageDialog(null,
                        "Username đã tồn tại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null,
                    "Password phải từ 8 kí tự", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean check_MaNV(String maNV) {
        for (int i = 0; i < dstk.size(); i++) {
            if (dstk.get(i).getMaNV().equalsIgnoreCase((String) cbxMaNV.getSelectedItem())) {
                JOptionPane.showMessageDialog(null,
                        "Nhân viên đã có tài khoản, xin vui lòng chọn lại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
        return false;
    }

    public void refresh() {
        this.removeAll(); // Xóa tất cả các thành phần hiện tại
        dstk = tkBUS.getDsTK(); // Tải lại dữ liệu

        JPanel mainPanel = initPnContentParent(dstk); // Tạo lại JPanel chính
        this.add(mainPanel, BorderLayout.CENTER); // Thêm lại JPanel chính

        this.revalidate(); // Cập nhật lại giao diện
        this.repaint(); // Vẽ lại giao diện
    }

    public void AddTK() {
        String maNV = (String) cbxMaNV.getSelectedItem();
        String username = txtUsername.getText();
        char[] pass = pwfMK.getPassword(); // Lấy dữ liệu đã nhập từ JPasswordField
        String password = new String(pass);
        String maQuyen = (String) cbxMaQuyen.getSelectedItem();

        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();

        // Định dạng ngày theo yêu cầu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển đổi LocalDate thành chuỗi theo định dạng
        String date = today.format(formatter);

        if (!check_MaNV(maNV)) {
            if (check_Usename_Password(username, password)) {
                System.out.println("OK");
                TaiKhoanDTO tk = new TaiKhoanDTO(maNV, username, password, date, maQuyen, 1);
                tkBUS.add(tk);
                refresh();
                this.revalidate(); // Cập nhật lại giao diện
                this.repaint(); // Vẽ lại giao diện
                JOptionPane.showMessageDialog(null,
                        "Bạn đã thêm tài khoản thành công !", "Thông báo", JOptionPane.DEFAULT_OPTION);
            }
        }
    }

    public void EditTK() {
        String maNV = (String) cbxMaNV.getSelectedItem();
        String username = txtUsername.getText();
        char[] pass = pwfMK.getPassword(); // Lấy dữ liệu đã nhập từ JPasswordField
        String password = new String(pass);
        String maQuyen = (String) cbxMaQuyen.getSelectedItem();

        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();

        // Định dạng ngày theo yêu cầu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển đổi LocalDate thành chuỗi theo định dạng
        String date = today.format(formatter);

        TaiKhoanDTO tk = new TaiKhoanDTO(maNV, username, password, date, maQuyen, 1);
        for (int i = 0; i < dstk.size(); i++) {
            if (dstk.get(i).getMaNV().equalsIgnoreCase(maNV)) {
                if (!username.isEmpty()) {
                    tkBUS.set(tk);
                    refresh();
                    break;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Username không được để trống, xin vui lòng nhập username !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }

            }
        }
        JOptionPane.showMessageDialog(null,
                "Bạn đã lưu tài khoản thành công !", "Thông báo", JOptionPane.DEFAULT_OPTION);
    }

    public void DeleteTK() {
        tkBUS.delete(selectedTK.getMaNV());
        selectedTK = null;
        refresh();
    }

    public void SearchTK(ArrayList<String> data_filter) {
        String timkiem = data_filter.get(0);
        String state = data_filter.get(1);
        ArrayList<TaiKhoanDTO> ds = new ArrayList<>();
        //trường hợp username rỗng
        if (timkiem.isEmpty()) {
            switch (state) {
                case "Đang hoạt động":
                    for (int i = 0; i < dstk.size(); i++) {
                        if (dstk.get(i).getState() == 1) {
                            ds.add(dstk.get(i));
                        }
                    }
                    break;
                case "Đã khóa":
                    for (int i = 0; i < dstk.size(); i++) {
                        if (dstk.get(i).getState() == 0) {
                            ds.add(dstk.get(i));
                        }
                    }
                    break;
                default:
                    ds = dstk;
                    break;
            }
        } else {
            //trường hợp username khác rỗng
            ArrayList<TaiKhoanDTO> newDS = new ArrayList<>();
            for (int i = 0; i < dstk.size(); i++) {
                String username = dstk.get(i).getUsername().toLowerCase();
                String maNV = dstk.get(i).getMaNV().toLowerCase();
                String maQuyen = dstk.get(i).getMaQuyen().toLowerCase();
                timkiem = timkiem.toLowerCase();
                if (username.contains(timkiem) || maNV.contains(timkiem)
                        || maQuyen.contains(timkiem)) {
                    newDS.add(dstk.get(i));
                }
            }

            switch (state) {
                case "Đang hoạt động":
                    for (int i = 0; i < newDS.size(); i++) {
                        if (newDS.get(i).getState() == 1) {
                            ds.add(newDS.get(i));
                        }
                    }
                    break;
                case "Đã khóa":
                    for (int i = 0; i < newDS.size(); i++) {
                        if (newDS.get(i).getState() == 0) {
                            ds.add(newDS.get(i));
                        }
                    }
                    break;
                default:
                    ds = newDS;
                    break;
            }
        }

        this.removeAll(); // Xóa tất cả các thành phần hiện tại
        JPanel mainPanel = initPnContentParent(ds); // Tạo lại JPanel chính

        this.add(mainPanel, BorderLayout.CENTER); // Thêm lại JPanel chính

        this.revalidate(); // Cập nhật lại giao diện

        this.repaint(); // Vẽ lại giao diện
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass() == JPanel.class) {
            // Xử lý các dòng tài khoản
            JPanel pn = (JPanel) e.getSource();
            for (int i = 0; i < dstk.size(); i++) {
                if (pn == pnContent[i]) {
                    selectedTK = dstk.get(i);
                    pn.setBackground(hover);
                    pn.setOpaque(true);
                    if (choiceSua == 1) {

                        setTT();
                    }

                    clear(i);
                }
            }
        } else {
            JLabel lbl = (JLabel) e.getSource();
            if (lbl == exit || lbl == lblHuy) {
                //exit
                this.remove(pnThaoTacTK_main);
                clear(-1);
                selectedTK = new TaiKhoanDTO();
                this.revalidate(); // Cập nhật lại container
                this.repaint();
            }
            if (lbl == lblCapNhat) {
                EditTK();
                clear(-1);
                selectedTK = new TaiKhoanDTO();
            }
            if (lbl == lblThem) {
                AddTK();
                clear(-1);
                selectedTK = new TaiKhoanDTO();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().getClass() == JPanel.class) {
            JPanel pn = (JPanel) e.getSource();

            pn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) throws SQLException {
        ArrayList<String> data_filter = new ArrayList<>();
        data_filter.add("");
        data_filter.add("Đã khoá");

        TaiKhoanGUI t = new TaiKhoanGUI(1000, 500);
        t.initPnThaoTacTK(400, 500);
        t.initThem();
//        t.initSua();

        t.SearchTK(data_filter);
        JFrame f = new JFrame();
        f.add(t);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 500);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

}
