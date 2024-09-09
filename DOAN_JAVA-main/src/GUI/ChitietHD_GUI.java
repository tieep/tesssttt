
package GUI;

import BUS.ChitietHD_BUS;
import DAO.ConnectDataBase;
import DTO.ChitietHD_DTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public final class ChitietHD_GUI extends JPanel{
    private JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9;
    private int chieurong,chieucao;
    private Font f = new Font("Tahoma", Font.BOLD, 14);
    private ConnectDataBase mySQL = new ConnectDataBase();
    private ChitietHD_BUS dscthd;
    
    public ChitietHD_GUI(int chieurong,int chieucao,String maHD, String ngayHD,String time, String maKH, String maNV,String giamgia, String tongtien) throws SQLException {
       this.chieurong=chieurong;
       this.chieucao=chieucao;
       init(maHD,ngayHD,time,maKH,maNV,giamgia,tongtien);
    }
    
    public void init(String maHD, String ngayHD,String time, String maKH, String maNV, String giamgia, String tongtien) throws SQLException{   
        this.setPreferredSize(new Dimension(3*chieurong/5,chieucao));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);
        //--------------------------------SHOW_Title-------------------------------
            p1 = new JPanel();
                p1.setBackground(Color.white); 
                p1.setLayout(new BorderLayout());
                    JLabel title = new JLabel("CHI TIET HOA DON", JLabel.CENTER);
                        p1.setPreferredSize(new Dimension(title.getWidth(),80));
                        title.setAlignmentX(Component.CENTER_ALIGNMENT);
                        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 28));
                        title.setForeground(Color.decode("#145369"));
            p1.add(title,BorderLayout.CENTER);
            
        //--------------------------------SHOW_IDHD-------------------------------
            p2 = new JPanel();
                p2.setBackground(Color.white); 
                p2.setLayout(new FlowLayout(0,50,0));
                    JLabel HD = new JLabel("Hoa Don", JLabel.CENTER);
                        HD.setPreferredSize(new Dimension(200, 40));
                        HD.setFont(f);
                    JLabel idHD = new JLabel(maHD, JLabel.LEFT);
                        idHD.setPreferredSize(new Dimension(200, 40));
                        idHD.setFont(f);
                        idHD.setForeground(Color.decode("#2596be"));
            p2.add(HD);
            p2.add(idHD);
            
        //--------------------------------SHOW_DATETIME-------------------------------
            p3 = new JPanel();
                p3.setBackground(Color.white); 
                p3.setLayout(new FlowLayout(0,50,0));
                    JLabel datetime = new JLabel("Thoi Gian", JLabel.CENTER);
                        datetime.setPreferredSize(new Dimension(200, 40));
                        datetime.setFont(f);
                    JLabel date = new JLabel(ngayHD, JLabel.LEFT);
                        date.setPreferredSize(new Dimension(100, 40));
                        date.setFont(f);
                        date.setForeground(Color.decode("#2596be"));
                        JLabel t = new JLabel(time, JLabel.LEFT);
                        t.setPreferredSize(new Dimension(80, 40));
                        t.setFont(f);
                        t.setForeground(Color.decode("#2596be"));
            p3.add(datetime);
            p3.add(date);
            p3.add(t);
            
        //--------------------------------SHOW_TENKH-------------------------------
            p4 = new JPanel();
                p4.setBackground(Color.white);
                p4.setLayout(new FlowLayout(0, 50, 0)); // Đảm bảo chỉ cần truyền vào alignment và khoảng cách giữa các thành phần
                    JLabel KH = new JLabel("Ten Khach Hang", JLabel.CENTER);
                        KH.setPreferredSize(new Dimension(200, 40));
                        KH.setFont(f);
            p4.add(KH);
                try {
                    String sql = "SELECT TENKH FROM `hoadon`,`khachhang` WHERE hoadon.MAKH=khachhang.MAKH and hoadon.MAKH = '" + maKH + "';";
                    mySQL.connect();
                    ResultSet rs = mySQL.executeQuery(sql);
                    while(rs.next()) { // Kiểm tra xem có kết quả từ truy vấn không trước khi lấy dữ liệu
                        JLabel name = new JLabel(rs.getString("TENKH"), JLabel.LEFT);
                            name.setPreferredSize(new Dimension(200, 40));
                            name.setFont(f);
                            name.setForeground(Color.decode("#2596be"));
                    p4.add(name);  
                    }
                    rs.close();
                    mySQL.disconnect();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

        //--------------------------------SHOW_IDNV-------------------------------
            p5 = new JPanel();
                p5.setBackground(Color.white);
                p5.setLayout(new FlowLayout(0,50,0));
                    JLabel NV = new JLabel("Ma Nhan Vien", JLabel.CENTER);
                        NV.setPreferredSize(new Dimension(200, 40));
                        NV.setFont(f);
                    JLabel idNV = new JLabel(maNV, JLabel.LEFT);
                        idNV.setPreferredSize(new Dimension(200, 40));
                        idNV.setFont(f);
                        idNV.setForeground(Color.decode("#2596be"));
            p5.add(NV);
            p5.add(idNV);
           
        //--------------------------------SHOW_SANPHAM_CTHD-------------------------------
            p6 = new JPanel();
                p6.setBackground(Color.white);
                p6.setPreferredSize(new Dimension((chieurong*3/5), 250));
                    String[] columnNames = {"Ten SP", "size", "So luong","Đongia"," Tong"};
                    JPanel pa = new JPanel();
                        pa.setLayout(new FlowLayout(1,10,0));
                        pa.setBackground(Color.decode("#60A3BC"));
                        pa.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#0A3D62")));
                        pa.setForeground(Color.red);
                        for (String col : columnNames) {
                            JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension(((chieurong*3/5)-60)/5, 40));
                                l.setFont(new Font(l.getFont().getName(), Font.BOLD, 16));
                                l.setForeground(Color.decode("#0A3D62"));
                        pa.add(l);
                        }
                p6.add(pa);
                
                    JPanel p = new JPanel();
                        p.setLayout(new FlowLayout(0,0,3));
                        p.setPreferredSize(new Dimension((chieurong*3/5), 200));
                        p.setBackground(Color.white);
                    dscthd = new ChitietHD_BUS(maHD);
                        for (ChitietHD_DTO sp : dscthd.list) {
                            JPanel panel = new JPanel();
                                panel.setLayout(new FlowLayout(0,5,0));
                                panel.setPreferredSize(new Dimension((chieurong*3/5)-2, 40));
                                panel.setBackground(Color.decode("#d3eaf2"));
                                int dongia = (int)sp.getGia();
                                int tong = (int)sp.getSl()*dongia;
                                JLabel lab1 = new JLabel( sp.getTenSP(), JLabel.CENTER);
                                JLabel lab2 = new JLabel( sp.getMaSize(), JLabel.CENTER);
                                JLabel lab3 = new JLabel(Integer.toString(sp.getSl()), JLabel.CENTER);
                                JLabel lab4 = new JLabel(Integer.toString(dongia), JLabel.CENTER);
                                JLabel lab5 = new JLabel(Integer.toString(tong) , JLabel.CENTER);
                                    lab1.setPreferredSize(new Dimension(((chieurong*3/5))/5, 40));
                                    lab2.setPreferredSize(new Dimension(((chieurong*3/5)-50)/5, 40));
                                    lab3.setPreferredSize(new Dimension(((chieurong*3/5)-50)/5, 40));
                                    lab4.setPreferredSize(new Dimension(((chieurong*3/5)-20)/5, 40));
                                    lab5.setPreferredSize(new Dimension(((chieurong*3/5)-50)/5, 40));
                            panel.add(lab1);
                            panel.add(lab2);
                            panel.add(lab3);
                            panel.add(lab4);
                            panel.add(lab5);
                        p.add(panel);  
                        }
            JScrollPane scrollPane = new JScrollPane(p);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension((chieurong * 3 / 5), 200));
            p6.add(scrollPane); 

        //--------------------------------SHOW_TONGTIEN-------------------------------
            p7 = new JPanel();
                p7.setBackground(Color.white); 
                p7.setLayout(new FlowLayout(0,40,0));
                p7.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#60A3BC")));
                    JLabel note = new JLabel("Tong cong", JLabel.CENTER);
                        note.setPreferredSize(new Dimension(150, 40));
                        note.setFont(f);
                        int s = Integer.parseInt(giamgia) + Integer.parseInt(tongtien);
                        JLabel contentnote = new JLabel(String.valueOf(s), JLabel.RIGHT);
                        contentnote.setPreferredSize(new Dimension((chieurong*3/5)-280, 40));
                        contentnote.setFont(f);
            p7.add(note);
            p7.add(contentnote);
            
            
        //--------------------------------SHOW_GIAMGIA-------------------------------
            p9 = new JPanel();
                p9.setBackground(Color.white); 
                p9.setLayout(new FlowLayout(0,40,0));
                    JLabel gg = new JLabel("Giam gia", JLabel.CENTER);
                        gg.setPreferredSize(new Dimension(150, 40));
                        gg.setFont(f);
                    JLabel g = new JLabel( giamgia, JLabel.RIGHT);
                        g.setPreferredSize(new Dimension((chieurong*3/5)-280, 40));
                        g.setFont(f);
            p9.add(gg);
            p9.add(g);

        //--------------------------------SHOW_THANHTIEN-------------------------------
            p8 = new JPanel();
                p8.setBackground(Color.white); 
                p8.setLayout(new FlowLayout(0,40,0));
                    JLabel amountpaid = new JLabel("THANH TIEN", JLabel.CENTER);
                        amountpaid.setPreferredSize(new Dimension(150, 40));
                        amountpaid.setFont(new Font("Tahoma", Font.BOLD, 18));
                    JLabel sum = new JLabel(tongtien, JLabel.RIGHT);
                        sum.setPreferredSize(new Dimension((chieurong*3/5)-280, 40));
                        sum.setFont(new Font("Tahoma", Font.BOLD, 18));
                        sum.setForeground(Color.red);
            p8.add(amountpaid);
            p8.add(sum);
            
            
            this.add(p1);
            this.add(p2);
            this.add(p3);
            this.add(p4);
            this.add(p5);
            this.add(p6);
            this.add(p7);
            this.add(p9);
            this.add(p8);               
}
    
     public static void main (String[] args) throws SQLException{
        JFrame f = new JFrame ();
        f.setSize(1200,800);
        ChitietHD_GUI p = new ChitietHD_GUI(1200,800,"HD001","2024-04-03","23:03:38","Ha Duy","NV001","200000","2100000");
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
