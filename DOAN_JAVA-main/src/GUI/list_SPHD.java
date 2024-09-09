


package GUI;

import BUS.ChitietHD_BUS;

import DTO.ChitietHD_DTO;
import DTO.Hoadon_DTO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.util.Vector;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author hp
 */
public class list_SPHD extends JPanel {

    private int ccao, crong;
    public JTable table;
    private JPanel totalPanel,salePanel,Finaltotal, controlPanel;
    private Font font_data = new Font("Tahoma", Font.PLAIN, 14);
    private DefaultTableModel tableModel;
    public boolean isEditingEnabled = false;
    private Font f = new Font("Tahoma", Font.BOLD, 16);
    private String new_size, old_size, new_sl, old_sl;
    public list_SPHD(int crong, int ccao, Hoadon_DTO hd) throws SQLException {
        this.ccao = ccao;
        this.crong = crong;
        init(crong, ccao, hd);
    }

    private void init(int crong, int ccao, Hoadon_DTO hd) throws SQLException {
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);
        int giamgia = hd.getGiamgia();
        
        String[] columnNames = {"IDSP","Tên SP", "SIZE", "Số lượng", "Đơn giá", "Tổng"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        table = new JTable(tableModel) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return isEditingEnabled;
            }

        }; 
        ChitietHD_BUS cthdBUS = new ChitietHD_BUS( hd.getMaHD());
        addDataInTable(cthdBUS.getList());
        // lay size va sl ban dau
        String[] list_oldSize = new String[tableModel.getRowCount()];
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object value = table.getValueAt(i, 2); 
            list_oldSize[i] = value.toString();
            System.out.println(list_oldSize[i]);
        }
        String[] list_oldSL = new String[tableModel.getRowCount()];
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object value = table.getValueAt(i, 3);
            list_oldSL[i] = value.toString();
            System.out.println(list_oldSL[i]);
        }
        cssHeaderTable(table.getTableHeader());
        cssDataTable();
        table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                if (column==2) {
                    String idsp = (String) table.getValueAt(row, 0); 
                    try {
                        editCell_Size(row, column,cthdBUS, idsp);
                    } catch (SQLException ex) {
                        Logger.getLogger(list_SPHD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (column==3){
                    String idsp = (String) table.getValueAt(row, 0); 
                    String size = (String) table.getValueAt(row, 2); 
                    int sl = Integer.parseInt(table.getValueAt(row, 3).toString()); // Chuyển đổi từ String sang int
                        System.out.println("MASP:" + idsp +" - SIZE:"+ size);
                    try {
                        editCell_SL(row, column, giamgia,cthdBUS, idsp,size,sl);
                    } catch (SQLException ex) {
                        Logger.getLogger(list_SPHD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    });
        controlPanel = new JPanel();
        controlPanel.setBackground(Color.white);
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        controlPanel.setPreferredSize(new Dimension((crong)-4, 40));

    // Tạo nút "lưu"
    JButton saveButton = new JButton("Lưu");
    saveButton.setPreferredSize(new Dimension(80, 30));
    saveButton.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
    saveButton.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
    saveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] options = {"Có", "Không"};
             int r1 = JOptionPane.showOptionDialog(null, "Những thông tin đã thay đổi sẽ được cập nhật. \nBạn chắc chắn muốn lưu thay đổi?", "Lưu ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if (r1 == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < tableModel.getRowCount(); i++) { 
                        old_sl=list_oldSL[i];
                        old_size=list_oldSize[i];
                        new_size = (String) table.getValueAt(i, 2); 
                        new_sl = (String) table.getValueAt(i, 3); 
                        String id = (String) table.getValueAt(i, 0); 
                        if(new_size!=old_size || new_sl!=old_sl)// nếu có thay đổi dữ liệu thì cập nhật
                        {
                        try {
                            cthdBUS.restore(id,Integer.parseInt(old_sl)+ cthdBUS.get_slsp(id, old_size), old_size);
                            cthdBUS.restore(id,cthdBUS.get_slsp(id, old_size) - Integer.parseInt(new_sl), new_size);
                            cthdBUS.update(hd.getMaHD(), id, cthdBUS.Get_Masize(new_size), Integer.parseInt(new_sl));
                            System.out.println(id +"//"+cthdBUS.Get_Masize(new_size)+"//"+Integer.valueOf(new_sl));
                            Component[] components = Finaltotal.getComponents();
                            for (Component c : components) {
                                if (c == components[1]) {
                                    JLabel label = (JLabel) c;
                                    int tt = Integer.parseInt(label.getText());
                                    cthdBUS.updatehd(hd.getMaHD(), tt);                  
                }
            }
        }
                        catch (SQLException ex) {
                            Logger.getLogger(list_SPHD.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                    }
                        JOptionPane.showMessageDialog(null, "Sửa sản phẩm hóa đơn thành công!\n Bấm \"Hoàn tất\" để cập nhật lại thông tin hóa đơn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                }
        }
    });
    controlPanel.add(saveButton);
    // Tạo nút "Thoát"
    JButton exitButton = new JButton("Thoát");
    exitButton.setPreferredSize(new Dimension(80, 30));
    exitButton.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
    exitButton.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
    exitButton.setFont(new Font("Tahoma", Font.BOLD, 14));
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] options = {"Có", "Không"};
            int r2 = JOptionPane.showOptionDialog(null, "Những thông tin thay đổi sẽ không được cập nhật. \nBạn chắc chắn muốn rời khỏi mà chưa bấm nút \"Lưu\"?", "Thoát ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if (r2 == JOptionPane.YES_OPTION) {
                    removePage();
        }
        }
    });
    controlPanel.add(exitButton);
    this.add(controlPanel);
        JPanel p1 = new JPanel();
                p1.setBackground(Color.white); 
                p1.setLayout(new BorderLayout());
                    JLabel title = new JLabel("DANH SÁCH SẢN PHẨM", JLabel.CENTER);
                        p1.setPreferredSize(new Dimension(title.getWidth(),200));
                        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 28));
                        title.setForeground(Color.decode("#145369"));
            p1.add(title,BorderLayout.CENTER);
        this.add(p1);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(crong, ccao));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);
        addTotalPanel(calculateTotalBill()); 
        addSalePanel(giamgia);
        addFinalTotal(calculateTotalBill(),giamgia);
    
    }
    
    
    private void addTotalPanel(int totalBill) {
    if (totalPanel != null) {
        this.remove(totalPanel);
    }
    
    // Tạo panel mới
    totalPanel = new JPanel();
    totalPanel.setBackground(Color.white);
    totalPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 0));
    totalPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#60A3BC")));

    JLabel note = new JLabel("Tổng cộng", JLabel.CENTER);
    note.setPreferredSize(new Dimension(150, 60));
    note.setFont(f);

    JLabel contentnote = new JLabel(String.valueOf(totalBill), JLabel.RIGHT);
    contentnote.setPreferredSize(new Dimension((crong) - 280, 60));
    contentnote.setFont(f);

    totalPanel.add(note);
    totalPanel.add(contentnote);

    // Thêm panel mới vào list_SPHD
    this.add(totalPanel);
    this.revalidate();
    this.repaint();
}
    
    private void addSalePanel(int giamgia) {
        if (salePanel != null) {
            this.remove(salePanel);
        }
        salePanel = new JPanel();
                salePanel.setBackground(Color.white); 
                salePanel.setLayout(new FlowLayout(0,40,0));
                    JLabel gg = new JLabel("Giảm giá", JLabel.CENTER);
                        gg.setPreferredSize(new Dimension(150, 40));
                        gg.setFont(f);
                    JLabel g = new JLabel(String.valueOf(giamgia), JLabel.RIGHT);
                        g.setPreferredSize(new Dimension((crong)-280, 40));
                        g.setFont(f);
            salePanel.add(gg);
            salePanel.add(g);
            this.add(salePanel);  
            this.revalidate();
    this.repaint();
    }
    private void addFinalTotal(int t, int g) {
        if (Finaltotal != null) {
        this.remove(Finaltotal);}
        Finaltotal = new JPanel();
        Finaltotal.setBackground(Color.white);
        Finaltotal.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 0));

        JLabel note = new JLabel("THÀNH TIỀN", JLabel.CENTER);
        note.setPreferredSize(new Dimension(150, 40));
        note.setFont(new Font("Tahoma", Font.BOLD, 18));

        JLabel contentnote = new JLabel(String.valueOf(t-g), JLabel.RIGHT);
        contentnote.setPreferredSize(new Dimension((crong) - 280, 60));
        contentnote.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentnote.setForeground(Color.red);

        Finaltotal.add(note);
        Finaltotal.add(contentnote);
        
    this.add(Finaltotal);
    this.revalidate();
    this.repaint();
    }
    
    

    public void addDataInTable(ArrayList<ChitietHD_DTO> list) {
    Vector data;
    tableModel.setRowCount(0);
    for (ChitietHD_DTO n : list) {
        data = new Vector();
        data.add(n.getMaSP());
        data.add("");
        data.add(n.getMaSize());
        data.add(n.getSl());
        data.add((int)n.getGia());
        data.add((int)n.getTt());
        tableModel.addRow(data);
    }
    table.setModel(tableModel);
    tableModel.fireTableDataChanged();
}



    private void cssHeaderTable(JTableHeader header) {
        header.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        header.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        header.setFont(Cacthuoctinh_phuongthuc_chung.font_header);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
    }
    
    
    private void editCell_Size(int row, int column, ChitietHD_BUS cthdBUS, String idsp) throws SQLException {
                String[] listSize = cthdBUS.get_AllSize(idsp);
                JComboBox<String> comboBox = new JComboBox<>(listSize);
                Object[] options = {"Có", "Không"};
                int result = JOptionPane.showOptionDialog(null, comboBox, "Chọn size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE ,null,options,options[0]);
                if (result == JOptionPane.OK_OPTION) {
                    String newValue = (String) comboBox.getSelectedItem();
                    table.setValueAt(newValue, row, column);
                }
            }

    private void editCell_SL(int row, int column, int g, ChitietHD_BUS cthdBUS, String id,String s, int a) throws SQLException {
                System.out.println("MASP:" + id +" - SIZE:"+ s);
                String sl = String.valueOf(cthdBUS.get_slsp(id, s));
                int max = cthdBUS.get_slsp(id, s)+ a;
                String newValue = JOptionPane.showInputDialog(null, "Nhập số lượng:", "MAX = " + max);
                if (newValue != null) {
                    if( check_sl(newValue,max)){
                        table.setValueAt(newValue, row, column);
                        updateTotal(newValue,row,g);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Số liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

    public boolean check_sl(String new_sl, int max_sl) {
        int x = Integer.parseInt(new_sl);
        if (x>0 && x <= (max_sl))
            return  true;
        else return false;
        
    }


    private void updateTotal(String sl, int row, int g) {
        int quantity = Integer.parseInt(sl); // Chuyển đổi chuỗi sang số nguyên
    int unitPrice = (int) tableModel.getValueAt(row, 4);
    int total = quantity * unitPrice;
    tableModel.setValueAt(total, row, 5); // Cập nhật lại giá trị của cột tổng (cột thứ 5)
    int totalBill = calculateTotalBill(); // Tính toán tổng hóa đơn sau khi cập nhật
    addTotalPanel(totalBill); // Thêm panel mới hiển thị tổng hóa đơn
    addSalePanel(g);
    addFinalTotal(totalBill,g);
}
private void addControl(){
    
    }

    private void cssDataTable() {
        table.setRowHeight(35);//chieo cao cua hang
        table.setFont(font_data);//font cho dât trong table
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Căn giữa dữ liệu trong các ô
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    
    private int calculateTotalBill() {
    int totalBill = 0;
    for (int row = 0; row < tableModel.getRowCount(); row++) {
        int totalForRow = (int) tableModel.getValueAt(row, 5);
        totalBill += totalForRow;
    }
    return totalBill;
}  
    
    private void removePage(){
    this.removeAll();
     this.setBackground(Color.white); 
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                JPanel p = new JPanel(); 
                p.setLayout(new BorderLayout());
                p.add(new JLabel("(Trống)",JLabel.CENTER),BorderLayout.CENTER);
                p.setBackground(Color.WHITE);
            this.add(p);
        this.revalidate();
        this.repaint();
}
}
//    public static void main(String[] args) throws SQLException {
//        JFrame f =new JFrame();
//        f.setLocationRelativeTo(null);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//        f.add(new list_SPHD(800,600,"HD1"));
//                
//                f.setVisible(true);
//    }


 

