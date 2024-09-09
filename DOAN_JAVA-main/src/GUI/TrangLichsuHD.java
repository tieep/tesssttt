package GUI;

import GUI.ChitietHD_GUI;
import DTO.Hoadon_DTO;
import BUS.Hoadon_BUS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;

//import java.sql.*;
public final class TrangLichsuHD extends JPanel implements MouseListener{

    public boolean inHD = false;
    public String MAHDSelect;
    public JPanel left, right;
    private JPanel p;
    private Hoadon_BUS lshd;
    private int chieurong, chieucao;
    private Color backGroundColor;
    private Font f = new Font("Tahoma", Font.BOLD, 16);
    public  JPanel currentSelectedPanel = null;
    private String[] columnNames = {"MAHD","Ngày" , "Thời gian", "MAKH", "MANV", "Giảm", "Thanh toán"};
    public TrangLichsuHD(int chieurong, int chieucao) throws SQLException {
        this.chieurong = chieurong;
        this.chieucao = chieucao;
        init();

    }

    public void init() throws SQLException {
        MAHDSelect = null;
        currentSelectedPanel = null;
        this.setPreferredSize(new Dimension(chieurong, chieucao));
        this.setLayout(new BorderLayout(5,0));
        
        lshd = new Hoadon_BUS();
        left = new JPanel(new FlowLayout(1,0,0));
        left.setPreferredSize(new Dimension((int)chieurong /2  , 35 * lshd.dshoadon.size()));
        left.setBackground(Color.white);
        
        right = new JPanel(new FlowLayout(1,0,0));
        
        right.setBackground(Color.white);
        
        

        p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(new JLabel("Chưa lựa chọn Hóa đơn để hiển thị chi tiết", JLabel.CENTER), BorderLayout.CENTER);
        p.setPreferredSize(new Dimension(chieurong * 3 / 5, 0));
        p.setBackground(Color.WHITE);
        right.add(p);

        

        //left.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.decode("#60A3BC")));

        // create title
        
        JPanel titlePanel = new JPanel(new GridLayout(1, columnNames.length,0,0));
        
        titlePanel.setBackground(Color.decode("#60A3BC"));
        for (String col : columnNames) {
            JLabel l = null;
            if(col.equals(columnNames[columnNames.length - 1]))
                l = new JLabel("<html><p style='text-align: center;padding-bottom:2px;'>"+col+"</p></html>", JLabel.CENTER);
            else
                l = new JLabel(col, JLabel.CENTER);
            
            l.setBorder(BorderFactory.createEmptyBorder(10, 0, 8, 0));
            l.setFont(new Font("Tahoma", Font.BOLD, 13));
            l.setForeground(Color.white);
            titlePanel.add(l);
        }
        titlePanel.setPreferredSize(new Dimension((int)left.getPreferredSize().getWidth() ,(int)titlePanel.getPreferredSize().getHeight()));
        left.add(titlePanel);

        
        for (Hoadon_DTO hd : lshd.dshoadon) {
            addHD_gui(this, hd);
        }
        
        JScrollPane scrollPane = new JScrollPane(left); // Wrap 'left' panel with JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       scrollPane.setPreferredSize(new Dimension((int)left.getPreferredSize().getWidth() + 20, (int)left.getPreferredSize().getHeight())); // Set preferred size
       scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
       this.add(scrollPane,BorderLayout.CENTER); // Add JScrollPane instead of 'left' directly
        right.setPreferredSize(new Dimension(chieurong - (int)scrollPane.getPreferredSize().getWidth(), chieucao));
//    this.add(left);
        this.add(right,BorderLayout.EAST);
    }

    public void addHD_gui(TrangLichsuHD hdGUI, Hoadon_DTO hd) {
        JPanel pa = new JPanel(new GridLayout(1, columnNames.length,0,0));
        pa.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#0A3D62")));
        pa.setBackground(Color.white);
        
        

        JLabel lab1 = new JLabel(hd.getNgayHD(), JLabel.CENTER);
        JLabel lab2 = new JLabel(Integer.toString(hd.getMaKH()), JLabel.CENTER);
        JLabel lab3 = new JLabel((String) hd.getMaNV(), JLabel.CENTER);
        JLabel lab4 = new JLabel((String) hd.getMaHD(), JLabel.CENTER);
        JLabel lab5 = new JLabel(Integer.toString(hd.getTongTien()), JLabel.CENTER);
        JLabel lab6 = new JLabel(Integer.toString(hd.getGiamgia()), JLabel.CENTER);
        JLabel lab7 = new JLabel((String) hd.getThoigian(), JLabel.CENTER);
        pa.add(lab4);
        pa.add(lab1);
        pa.add(lab7);
        pa.add(lab2);
        pa.add(lab3);
        pa.add(lab6);
        pa.add(lab5);
        pa.setPreferredSize(new Dimension((int)left.getPreferredSize().getWidth(),30));
        left.add(pa);
        pa.addMouseListener(this);
        pa.setName(hd.getMaHD());
    }
    private ArrayList<String> extractLabelContents(JPanel pa) {
                ArrayList<String> contents = new ArrayList<>();
                Component[] components = pa.getComponents();
                for (Component component : components) {
                    if (component instanceof JLabel) {
                        JLabel label = (JLabel) component;
                        contents.add(label.getText());
                    }
                }
                return contents;
            }
    public void control() throws SQLException {
        this.setPreferredSize(new Dimension(chieurong, chieucao));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        right = new JPanel();
        right.setPreferredSize(new Dimension(chieurong * 3 / 5, 0));
        right.setBackground(Color.white);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(new JLabel("(Trống)", JLabel.CENTER), BorderLayout.CENTER);
        p.setPreferredSize(new Dimension(chieurong * 3 / 5, 0));
        p.setBackground(Color.WHITE);
        right.add(p);

        left = new JPanel();
        left.setPreferredSize(new Dimension(chieurong * 2 / 5, 0));
        left.setBackground(Color.white);
        left.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4, Color.decode("#60A3BC")));

        JPanel Panel = new JPanel();
        Panel.setLayout(null);
        Panel.setBackground(Color.white);
        Panel.setPreferredSize(new Dimension((chieurong) * 2 / 5 - 4, 40));

        JButton submit = new JButton("Hoàn tất");
        submit.setBounds(10, 0, 90, 30);
        submit.setBackground(Color.decode("#0A3D62"));
        submit.setForeground(Color.white);
        Panel.add(submit);
        left.add(Panel);
        // create title
        String[] columnNames = {"Ngay", "Thoigian", "KH", "Nhanvien", "Hoadon", "Giamgia", "Thanhtien"};
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(1, 0, 0));
        titlePanel.setBackground(Color.decode("#60A3BC"));
        titlePanel.setPreferredSize(new Dimension((chieurong), 30));
        for (String col : columnNames) {

            JLabel l = new JLabel(col, JLabel.CENTER);
            l.setPreferredSize(new Dimension(((chieurong) * 2 / 5) / 7, 30));
            l.setFont(new Font(l.getFont().getName(), Font.CENTER_BASELINE, 12));
            l.setForeground(Color.white);
            titlePanel.add(l);
        }
        left.add(titlePanel);
        left.setPreferredSize(new Dimension((chieurong * 2 / 5), chieucao + chieucao/2));
        JScrollPane scrollPane = new JScrollPane(left); // Wrap 'left' panel with JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension((chieurong * 2 / 5), chieucao - (int) left.getPreferredSize().getHeight())); // Set preferred size
        this.add(scrollPane); // Add JScrollPane instead of 'left' directly
//    this.add(left);
        this.add(right);
        lshd = new Hoadon_BUS();
        for (Hoadon_DTO hd : lshd.dshoadon) {
            show_control(this, hd);
        }
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    reloadPage();
                } catch (SQLException ex) {
                    Logger.getLogger(Trangnhanvien_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    public void show_control(TrangLichsuHD hdGUI, Hoadon_DTO hd) {
        JPanel pa = new JPanel();
        pa.setLayout(new FlowLayout(1, 0, 0));
        pa.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#0A3D62")));
        pa.setBackground(Color.white);
        pa.setPreferredSize(new Dimension(((chieurong) * 2 / 5) - 4, 30));

        JLabel lab1 = new JLabel(hd.getNgayHD(), JLabel.CENTER);
        JLabel lab2 = new JLabel(Integer.toString(hd.getMaKH()), JLabel.CENTER);
        JLabel lab3 = new JLabel((String) hd.getMaNV(), JLabel.CENTER);
        JLabel lab4 = new JLabel((String) hd.getMaHD(), JLabel.CENTER);
        JLabel lab5 = new JLabel(Integer.toString(hd.getTongTien()), JLabel.CENTER);
        JLabel lab6 = new JLabel(Integer.toString(hd.getGiamgia()), JLabel.CENTER);
        JLabel lab7 = new JLabel((String) hd.getThoigian(), JLabel.CENTER);
        lab1.setPreferredSize(new Dimension(((chieurong) * 2 / 5) / 7, 30));
        lab2.setPreferredSize(new Dimension(((chieurong) * 2 / 5) / 8, 30));
        lab3.setPreferredSize(new Dimension(((chieurong) * 2 / 5) / 7, 30));
        lab4.setPreferredSize(new Dimension(((chieurong) * 2 / 5) / 7, 30));
        lab6.setPreferredSize(new Dimension(((chieurong) * 2 / 5) / 7, 30));
        lab5.setPreferredSize(new Dimension(((chieurong) * 2 / 5) / 7, 30));
        lab7.setPreferredSize(new Dimension(((chieurong) * 2 / 5) / 7, 30));

        pa.add(lab1);
        pa.add(lab7);
        pa.add(lab2);
        pa.add(lab3);
        pa.add(lab4);
        pa.add(lab6);
        pa.add(lab5);
        left.add(pa);

        JPanel control = new JPanel();
        control.setVisible(false);
        control.setBounds((chieurong - 300) / 2, (chieucao - 280) / 2, chieurong * 2 / 5, 70);
        control.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
        control.setLayout(new FlowLayout(1, 5, 0));
        JLabel title = new JLabel("Chọn thao tác với hóa đơn?", JLabel.LEFT);
        title.setForeground(Color.decode("#60A3BC"));
        title.setPreferredSize(new Dimension(248, 50));
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 14));
        control.add(title);

        // Tạo nút "Sửa"
        JButton editButton = new JButton("Sửa");
        editButton.setPreferredSize(new Dimension(60, 30));
        editButton.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        editButton.setForeground(Color.white);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    right.removeAll();
                    list_SPHD k = new list_SPHD(chieurong * 3 / 5, chieucao, hd);
                    right.add(k);
                    right.revalidate();
                    right.repaint();
                } catch (SQLException ex) {
                    Logger.getLogger(TrangLichsuHD.class.getName()).log(Level.SEVERE, null, ex);
                }
                control.setVisible(false);
            }
        });

        // Tạo nút "Xóa"
        JButton deleteButton = new JButton("Xóa");
        deleteButton.setPreferredSize(new Dimension(60, 30));
        deleteButton.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        deleteButton.setForeground(Color.white);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Có", "Không"};
                int r2 = JOptionPane.showOptionDialog(null, "Sau khi xóa hóa đơn sẽ bị hủy và hoàn tất thao tác trả hàng \nBạn chắc chắn muốn xóa hóa đơn?", "Xóa hóa đơn ", JOptionPane.YES_NO_OPTION
                        ,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if (r2 == JOptionPane.YES_OPTION) {
                    try {
                        lshd.delete(hd.getMaHD());
                    } catch (SQLException ex) {
                        Logger.getLogger(TrangLichsuHD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công!");
                    try {
                        hdGUI.reloadPage();
                    } catch (SQLException ex) {
                        Logger.getLogger(TrangLichsuHD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    // Thực hiện hành động khi người dùng chọn No hoặc đóng cửa sổ
                }
            }
        });

        // Tạo nút "Hủy"
        JButton cancelButton = new JButton("Hủy");
        cancelButton.setPreferredSize(new Dimension(60, 30));
        cancelButton.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.setVisible(false);

            }
        });

        // Thêm nút "Sửa", "Xóa" và "Hủy" vào panel control
        control.add(editButton);
        control.add(deleteButton);
        control.add(cancelButton);
        control.setVisible(false);
        left.add(control);

        pa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel HDItem = (JPanel) e.getSource();
                if (HDItem != currentSelectedPanel) {
                    if (currentSelectedPanel != null) {
                        resetPanelState(currentSelectedPanel);
                    }
                    currentSelectedPanel = HDItem;

                    HDItem.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
                    changeColorJPanelChild(HDItem, Cacthuoctinh_phuongthuc_chung.darkness_blue, Color.white);
                    Component[] components = HDItem.getParent().getComponents();
                    for (Component comp : components) {
                        if (comp instanceof JPanel && comp != HDItem) {
                            JPanel panel = (JPanel) comp;
                            if (panel.getMouseListeners().length > 0) {
                                resetPanelState(panel);
                            }
                        }
                    }
                }
                control.setVisible(true);
                control.revalidate();
                control.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JPanel HDItem = (JPanel) e.getSource();

                if (HDItem != currentSelectedPanel) {
                    HDItem.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
                    HDItem.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
                    HDItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    Component[] components = HDItem.getComponents();
                    for (Component component : components) {
                        if (component instanceof JLabel label) {
                            label.setForeground(Color.decode("#0A3D62"));
                        }
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JPanel HDItem = (JPanel) e.getSource();

                if (HDItem != currentSelectedPanel) {
                    resetPanelState(HDItem);
                }
            }

            private void resetPanelState(JPanel panel) {
                panel.setBackground(Color.white);
                panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));

                Component[] components = panel.getComponents();
                for (Component component : components) {
                    if (component instanceof JLabel label) {
                        label.setForeground(Color.black);
                    }
                }
            }

            private ArrayList<String> extractLabelContents(JPanel pa) {
                ArrayList<String> contents = new ArrayList<>();
                Component[] components = pa.getComponents();
                for (Component component : components) {
                    if (component instanceof JLabel) {
                        JLabel label = (JLabel) component;
                        contents.add(label.getText());
                    }
                }
                return contents;
            }
        });
    }

    public void search(ArrayList<Hoadon_DTO> list) throws SQLException {
        this.setPreferredSize(new Dimension(chieurong, chieucao));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        right = new JPanel();
        right.setPreferredSize(new Dimension(chieurong * 3 / 5, 0));
        right.setBackground(Color.white);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(new JLabel("Chưa lựa chọn Hóa đơn để hiển thị chi tiết", JLabel.CENTER), BorderLayout.CENTER);
        p.setPreferredSize(new Dimension(chieurong * 3 / 5, 0));
        p.setBackground(Color.WHITE);
        right.add(p);
        left = new JPanel();
        left.setPreferredSize(new Dimension(chieurong * 2 / 5, 0));
        left.setBackground(Color.white);
        left.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4, Color.decode("#60A3BC")));
//        this.add(left);
//        this.add(right);
        // create title
        String[] columnNames = {"Ngay", "Thoigian", "KH", "Nhanvien", "Hoadon", "Giamgia", "Thanhtien"};
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(1, 0, 0));
        titlePanel.setBackground(Color.decode("#60A3BC"));
        for (String col : columnNames) {

            JLabel l = new JLabel(col, JLabel.CENTER);
            l.setPreferredSize(new Dimension(((chieurong) * 2 / 5) / 7, 30));
            l.setFont(new Font(l.getFont().getName(), Font.CENTER_BASELINE, 12));
            l.setForeground(Color.white);
            titlePanel.add(l);
        }
        left.add(titlePanel);
        for (Hoadon_DTO hd : list) {
            addHD_gui(this, hd);
        }
        left.setPreferredSize(new Dimension((chieurong * 2 / 5), chieucao + chieucao/2));
        JScrollPane scrollPane = new JScrollPane(left); // Wrap 'left' panel with JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension((chieurong * 2 / 5), chieucao - (int) left.getPreferredSize().getHeight())); // Set preferred size
        this.add(scrollPane); // Add JScrollPane instead of 'left' directly
//    this.add(left);
        this.add(right);
    }

    public void removePanel(JPanel itemHD, JPanel control) {
        this.remove(itemHD); // Xóa panel itemNV khỏi container
        this.remove(control); // Xóa panel control khỏi container
        this.revalidate(); // Cập nhật container
        this.repaint(); // Vẽ lại container để hiển thị sự thay đổi
    }

    public void changeColorJPanelChild(JPanel p, Color bg, Color fg) {
        p.setBackground(bg);
        p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Component[] components = p.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setForeground(fg);

            }
        }
        MouseListener[] mouseListeners = p.getMouseListeners();
        for (MouseListener listener : mouseListeners) {
            p.removeMouseListener(listener);
        }

    }

// reload trang nhân viên
    public void reloadPage() throws SQLException {
        // Xóa toàn bộ nội dung của frame
        this.removeAll();

        // Khởi tạo lại giao diện mới
        this.init();

        // Vẽ lại frame
        revalidate();
        repaint();
    }

    public void reloadPagecontrol() throws SQLException {
        this.removeAll();
        this.control();
        revalidate();
        repaint();
    }

    public void reloadSearch(ArrayList<Hoadon_DTO> listhd) throws SQLException {
        this.removeAll();
        this.search(listhd);
        revalidate();
        repaint();
    }

    public void SearchHD(ArrayList<String> data_filter) throws SQLException, ParseException {
   if(lshd.searchFillData(data_filter)!= null)
    this.reloadSearch(lshd.searchFillData(data_filter));
}


    public static void main(String[] args) throws SQLException {
        JFrame f = new JFrame();
        f.setSize(1000, 800);
        TrangLichsuHD p = new TrangLichsuHD(1000, 800);
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
                JPanel HDItem = (JPanel) e.getSource();
                HDItem.setBackground(Cacthuoctinh_phuongthuc_chung.second_gray);
                if(currentSelectedPanel != null && HDItem != currentSelectedPanel){
                    currentSelectedPanel.setBackground(Color.white);
                    currentSelectedPanel.addMouseListener(this);
                }
                currentSelectedPanel = HDItem;
                MAHDSelect = HDItem.getName();
                
                MouseListener[] mouseListeners = HDItem.getMouseListeners();
                for (MouseListener listener : mouseListeners) {
                    HDItem.removeMouseListener(listener);
                } 
                ArrayList<String> LCont = extractLabelContents(HDItem);
                inHD = true;
                
                try {//{"MAHD","Ngày" , "Thời gian", "MAKH", "MANV", "Giảm", "Thanh toán"};
                    //String maHD, String ngayHD,String time, String maKH, String maNV,String giamgia, String tongtien
                    
                    ChitietHD_GUI s = new ChitietHD_GUI((int)right.getPreferredSize().getWidth(), chieucao, LCont.get(0), LCont.get(1), LCont.get(2), LCont.get(3), LCont.get(4), LCont.get(5), LCont.get(6));
                    right.removeAll();
                    right.add(s);
                    right.revalidate();
                    right.repaint();
                
                } catch (SQLException ex) {
                    Logger.getLogger(TrangLichsuHD.class.getName()).log(Level.SEVERE, null, ex);
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
                JPanel HDItem = (JPanel) e.getSource();

                HDItem.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
                HDItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JPanel HDItem = (JPanel) e.getSource();

                HDItem.setBackground(Color.white);
            }
}
