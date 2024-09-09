package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import BUS.SanPhamBUS;
import BUS.SizeBUS;
import BUS.chitietsanpham_BUS;

import DTO.SanPhamDTO;
import DTO.SizeDTO;
import DTO.chitietsanpham_DTO;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class view_chi_tiet_san_pham extends JPanel implements MouseListener {

    private JPanel pn, pn1, pn2, pc, pcL, pcr;
    private JPanel[] pc1, pc2;
    private JLabel[] jlc1, jlc2, jl_mls;
    private JLabel jln1, jln2;
    public static int soluong;
    private SanPhamDTO sanpham_DTO;
    private SanPhamBUS sanpham_BUS;
    private chitietsanpham_BUS chitietsanpham_BUS;
    private JComboBox<String> optionsize;
    private frame_chitietsanpham j;
    public static ArrayList<SanPhamDTO> dssptt = new ArrayList<SanPhamDTO>();
    public static ArrayList<chitietsanpham_DTO> dsctsptt = new ArrayList<chitietsanpham_DTO>();
    public static String maSizeThem;
    ImageIcon h0, h1, h2;
    
    // Định dạng sử dụng dấu phân cách hàng nghìn
    DecimalFormat FormatInt = new DecimalFormat("#,###"); //phuong them

    public view_chi_tiet_san_pham(SanPhamDTO sanpham_DTO, frame_chitietsanpham j) throws SQLException {
        soluong = 1;

        this.sanpham_DTO = sanpham_DTO;
        this.sanpham_BUS = new SanPhamBUS();
        this.chitietsanpham_BUS = new chitietsanpham_BUS();
        this.j = j;

        ArrayList<String> bangsize = chitietsanpham_BUS.select_masize_by_MASP(sanpham_DTO);
         ArrayList<String> tensize = new ArrayList<>();
        SizeBUS sizeBUS = new SizeBUS();
        for(String i: bangsize){
            for(SizeDTO s : sizeBUS.getList()){
                if(s.getMASIZE().equals(i))
                    tensize.add(s.getTENSIZE());
            }
        }

        String h00 = "";
        String h01 = "";
        String h02 = "";
        if (sanpham_DTO.tenHinh.length == 1) {
            try {
                h00 = "./src/images/" + this.sanpham_DTO.getTenHinh()[0];
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            try {
                h00 = "./src/images/t-shirt.png";
                h01 = "./src/images/" + this.sanpham_DTO.getTenHinh()[0];

                h02 = "./src/images/" + this.sanpham_DTO.getTenHinh()[1];
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        jlc1 = new JLabel[3];
        this.setPreferredSize(new Dimension(600, 450));

        Font x = new Font("Arial", ALLBITS, 20);
        Font tensp = new Font("Arial", Font.BOLD, 17);
        Font giohang = new Font("Arial", Font.BOLD, 13);
        Font size = new Font("Arial", ALLBITS, 15);
        // *************** panel north
        // ***********///////////////////////////////////////
        pn = new JPanel();
        pn.setLayout(new GridLayout());
        pn.setPreferredSize(new Dimension(0, 30));

        pn1 = new JPanel();
        pn1.setLayout(new FlowLayout(0));
        pn1.setBackground(Color.decode("#60A3BC"));
        jln1 = new JLabel("Chi tiết sản phẩm "+sanpham_DTO.getMaSP());
        pn1.add(jln1);

        pn2 = new JPanel();
        pn2.setLayout(new FlowLayout(2, 0, 0));
        pn2.setBackground(Color.decode("#60A3BC"));
        jln2 = new JLabel("X", jln2.CENTER);
        jln2.setPreferredSize(new Dimension(40, 30));
        jln2.setFont(x);
        jln2.addMouseListener(this);
        pn2.add(jln2);
        pn.add(pn1);
        pn.add(pn2);
        // **************************panel
        // center*****************//////////////////////////////////////////
        pc = new JPanel();
        pc.setLayout(new GridLayout(1, 2));
        pcL = new JPanel();
        pcL.setLayout(new FlowLayout());

        pc1 = new JPanel[3];

        jlc1[0] = new JLabel();

        pc1[0] = new JPanel();
        pc1[0].setPreferredSize(new Dimension(300, 20));

        pc1[1] = new JPanel();
        pc1[1].setPreferredSize(new Dimension(300, 260));

        // *************** hình lớn ****************///////////////////////////////////
        jlc1[0] = new JLabel();
        jlc1[0].setPreferredSize(new Dimension(190, 250));
        jlc1[0].setBackground(Color.white);
        jlc1[0].setOpaque(true);

        ImageIcon h0 = new ImageIcon(h00);
        Image scaledImage_lon = h0.getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH);
        ImageIcon h_to = new ImageIcon(scaledImage_lon);

        jlc1[0].setIcon(h_to);

        pc1[1].add(jlc1[0]);

        pc1[2] = new JPanel();
        pc1[2].setPreferredSize(new Dimension(300, 40));
        pc1[2].setLayout(new FlowLayout(1, 25, 10));

        //////////////////////////// *** 2 hình nhỏ **//////////////////////////////////
        if (!h01.equals("")) {
            jlc1[1] = new JLabel();
            jlc1[1].setPreferredSize(new Dimension(40, 30));
            h1 = new ImageIcon(h01);
            Image scaledImage_be1 = h1.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
            ImageIcon h_be1 = new ImageIcon(scaledImage_be1);
            
            scaledImage_lon = h1.getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH);
            h_to.setImage(scaledImage_lon);
            
            jlc1[1].setIcon(h_be1);
            jlc1[1].setBackground(Color.white);
            jlc1[1].setOpaque(true);
            jlc1[1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jlc1[1].addMouseListener(this);
            pc1[2].add(jlc1[1]);
        }

        if (!h02.equals("")) {
            jlc1[2] = new JLabel();
            jlc1[2].setPreferredSize(new Dimension(40, 30));
            h2 = new ImageIcon(h02);
            Image scaledImage_be2 = h2.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
            ImageIcon h_be2 = new ImageIcon(scaledImage_be2);

            jlc1[2].setIcon(h_be2);
            jlc1[2].setBackground(Color.white);
            jlc1[2].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jlc1[2].setOpaque(true);

            jlc1[2].addMouseListener(this);
            pc1[2].add(jlc1[2]);
        }

        pcL.add(pc1[0]);
        pcL.add(pc1[1]);
        pcL.add(pc1[2]);

        pcr = new JPanel();
        pc2 = new JPanel[7];
        jlc2 = new JLabel[9];
        pc2[0] = new JPanel();
        pc2[0].setPreferredSize(new Dimension(300, 20));
        pc2[1] = new JPanel();
        pc2[1].setPreferredSize(new Dimension(300, 260));
        pc2[1].setLayout(new GridLayout(5, 1));

        // ***************** 7 dòng bên phải********************
        for (int i = 2; i < 7; i++) {

            pc2[i] = new JPanel();
            pc2[i].setLayout(new FlowLayout(0, 10, 0));
            pc2[i].setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        }

        ////////////////////////////// tên sản phẩm ///////////////////////
        jlc2[0] = new JLabel("<html><body style='width:200px; display:flex; flex-wrap: wrap;'>" + sanpham_DTO.getTenSP()
                + "</body></html>", JLabel.CENTER);
        jlc2[0].setFont(tensp);
        // jlc2[0].setPreferredSize(new Dimension(80, 0));
        pc2[2].setPreferredSize(new Dimension(60, 0));
        pc2[2].add(jlc2[0]);

        // //////////////////////////////////////// size S M L
        // ///////////////////////////////////////////////////////////////////////
        jlc2[1] = new JLabel("M", JLabel.CENTER);
        jlc2[1].setPreferredSize(new Dimension(40, 30));
        jlc2[1].setBackground(Color.white);
        jlc2[1].setOpaque(true);
        jlc2[1].setFont(size);
        jlc2[1].addMouseListener(this);
        jlc2[2] = new JLabel("L", JLabel.CENTER);
        jlc2[2].setPreferredSize(new Dimension(40, 30));
        jlc2[2].setBackground(Color.white);
        jlc2[2].setOpaque(true);
        jlc2[2].setFont(size);
        jlc2[2].addMouseListener(this);

        jlc2[3] = new JLabel("XL", JLabel.CENTER);
        jlc2[3].setPreferredSize(new Dimension(40, 30));
        jlc2[3].setBackground(Color.white);
        jlc2[3].setOpaque(true);
        jlc2[3].setFont(size);
        jlc2[3].addMouseListener(this);

        optionsize = new JComboBox<String>(tensize.toArray(new String[0]));
        optionsize.setPreferredSize(new Dimension(120, 30));
        optionsize.addActionListener(new ActionListener(){
            SanPhamDTO s= sanpham_DTO;
            @Override
            public void actionPerformed(ActionEvent e) {
                
               jlc2[6].setText("1");
               jlc2[4].setText(FormatInt.format(s.getPrice())+"");
            }
            
        });
        pc2[3].add(optionsize);

        /////////////////////////////// //////////////////////////////
        pc2[5].setLayout(new FlowLayout(0, 0, 0));
        pc2[5].setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

        //////////////////// dấu trừ ///////////////////////////////////
        jlc2[5] = new JLabel("-", JLabel.CENTER);
        jlc2[5].setPreferredSize(new Dimension(40, 20));
        jlc2[5].addMouseListener(this);
        jlc2[5].setBackground(Color.decode("#0A3D62"));
        jlc2[5].setOpaque(true);
        jlc2[5].setForeground(Color.white);
        jlc2[5].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ///////////////////////////////// số lượng //////////////////////////
        jlc2[6] = new JLabel(soluong + "", JLabel.CENTER);
        jlc2[6].setPreferredSize(new Dimension(80, 20));
        jlc2[6].setBackground(Color.white);
        jlc2[6].setOpaque(true);

        /////////////////////////// dấu cộng
        /////////////////////////// ///////////////////////////////////////////////
        jlc2[7] = new JLabel("+", JLabel.CENTER);
        jlc2[7].setPreferredSize(new Dimension(40, 20));
        jlc2[7].setBackground(Color.decode("#0A3D62"));
        jlc2[7].setOpaque(true);
        jlc2[7].setForeground(Color.white);
        jlc2[7].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlc2[7].addMouseListener(this);

        pc2[4].add(jlc2[5]);
        pc2[4].add(jlc2[6]);
        pc2[4].add(jlc2[7]);

        /////////////////////////////////////////// giá ///////////////////////////
        String gia = FormatInt.format(this.sanpham_DTO.getPrice() * soluong);
        jlc2[4] = new JLabel(gia + "");
        jlc2[4].setFont(tensp);
        pc2[5].add(jlc2[4]);

        pc2[6].setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        jlc2[8] = new JLabel("Thêm vào giỏ", JLabel.CENTER);
        jlc2[8].setFont(giohang);
        jlc2[8].setForeground(Color.white);
        jlc2[8].setPreferredSize(new Dimension(100, 30));
        jlc2[8].setBackground(Color.decode("#0A3D62"));
        jlc2[8].setOpaque(true);
        jlc2[8].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlc2[8].addMouseListener(this);
        jlc2[8].addMouseListener(new MouseListener() {
            // @Override
            // public void mouseClicked(MouseEvent e) {
            // view_chi_tiet_san_pham.dssptt.add(sanpham_DTO);
            // }

            @Override
            public void mousePressed(MouseEvent e) {
           
                int soluong = Integer.parseInt(jlc2[6].getText());//so luong ma nguoi dung chon khi bam them vao gio
                
               
                String tensize = (String) optionsize.getSelectedItem();//ten size nguoi dung chon khi bam them vao gio
                
                SizeBUS sizeBUS = new SizeBUS();
                for(SizeDTO s : sizeBUS.getList()){//duyet mang size de lay ra ma size
                    if(s.getTENSIZE().equals(tensize)) maSizeThem=s.getMASIZE();//lay ra ma size tu ten size ma nguoi dung lua chon
                }
                
                
                
                chitietsanpham_DTO ctsp = new chitietsanpham_DTO(sanpham_DTO.getMaSP(),maSizeThem,soluong);// chi tiet san pham ma nguoi dung them vao gio hang
                System.out.println("MASP "+sanpham_DTO.getMaSP()+" ,MASIZE "+maSizeThem+" ,soluong them vao gio hang "+soluong);
                
                boolean checkIsFull = true;
                try {
                    chitietsanpham_BUS ctspBUS = new chitietsanpham_BUS();
                    int trongkho=0;
                    int tronggiohang=0;
                    for(chitietsanpham_DTO c : ctspBUS.getlist()){
                        if(c.getMASP().equals(sanpham_DTO.getMaSP()) && c.getMASIZE().equals(maSizeThem)){
                            trongkho=c.getSoluong();
                        }
                    }
                    for(chitietsanpham_DTO ctsptt : view_chi_tiet_san_pham.dsctsptt){
                                    if(ctsptt.getMASP().equals(sanpham_DTO.getMaSP()) && ctsptt.getMASIZE().equals(maSizeThem)){
                                        tronggiohang=ctsptt.getSoluong();
                                    }
                                }
                    if(trongkho-tronggiohang<soluong) checkIsFull= false;
                } catch (SQLException ex) {
                    Logger.getLogger(view_chi_tiet_san_pham.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(checkIsFull){
                    boolean flag = true;// true la chua co ton tai
                for(SanPhamDTO spDTO: dssptt){
                    if(spDTO.getMaSP().equals(sanpham_DTO.getMaSP()))  flag = false;// false la co ton tai
                    //kiem tra san pham vua bam them vao gio, da co trong gio hang chua
                    //neu tim thay san pham vua them vao gio da ton tai trong gio hang thi chuyen trang thai sang false
                }
                if(flag) // neu flag = tue la chua ton tai thi them vao dssptt, false thi khong them vao dssptt
                    view_chi_tiet_san_pham.dssptt.add(sanpham_DTO);
                
                
                 flag = true;// true la chua co ton tai
                for(chitietsanpham_DTO ctspDTO: dsctsptt){
                    if(ctspDTO.getMASP().equals(sanpham_DTO.getMaSP()) && ctspDTO.getMASIZE().equals(maSizeThem) ) {
                        ctspDTO.setSoluong(ctspDTO.getSoluong()+soluong);
                        flag = false;//neu co tim thay san pham vua them trong chitietsan pham va size = size thi flag=false
                        //kiem tra size san pham vua them vao gio hang da co trong gio hang chua
                        //neu size cua san pham vua them da co trong gio hang thi cap nhat tai chi tiet san pham do, so luong += soluong vua them
                        //neu size cua san pham vua them chua co trong gio hang thi them chi tiet san pham do vao dsctsptt
                    }
                }
                if(flag)//falg= true thi them vao dsctsptt
                    view_chi_tiet_san_pham.dsctsptt.add(ctsp);
                JOptionPane.showMessageDialog(null,
                    "Bạn đã thêm sản phẩm thành công ! \n"
                            + "Hãy vào hoá đơn để xem đơn hàng vừa thêm.", "Thông báo", JOptionPane.DEFAULT_OPTION);
                                j.dispose();
                }
                else      
                JOptionPane.showMessageDialog(null,"Thêm thất bại! \nDo số lượng trong kho không đủ.");
                           
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method
                // 'mouseReleased'");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method
                // 'mouseEntered'");

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method
                // 'mouseExited'");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                // view_chi_tiet_san_pham.dssptt.add(sanpham_DTO);
                // throw new UnsupportedOperationException("Unimplemented method
                // 'mouseClicked'");
            }

        });

        pc2[6].add(jlc2[8]);

        for (int i = 2; i < 7; i++) {
            pc2[1].add(pc2[i]);
        }

        jl_mls = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            jl_mls[i] = new JLabel();
        }
        jl_mls[0] = jln2;
        jl_mls[1] = jlc1[0];
        jl_mls[2] = jlc1[1];
        jl_mls[3] = jlc1[2];
        jl_mls[4] = jlc2[1];
        jl_mls[5] = jlc2[2];
        jl_mls[6] = jlc2[3];
        jl_mls[7] = jlc2[5];
        jl_mls[8] = jlc2[7];
        jl_mls[9] = jlc2[8];

        pcr.add(pc2[0]);
        pcr.add(pc2[1]);

        pc.add(pcL);
        pc.add(pcr);

        this.setLayout(new BorderLayout());
        this.add(pn, BorderLayout.NORTH);
        this.add(pc);

    }

    public int tang_sl() {
        return ++this.soluong;

    }

    public int giam_sl() {
        if (soluong == 0) {
            JOptionPane.showMessageDialog(this, "Số lượng không thể bé hơn 0 ");

        } else {
            return --this.soluong;
        }
        return soluong;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jln2) {
            j.setVisible(false);

        }
        if (e.getSource() == jlc2[5]) {
            int soluong = Integer.parseInt(jlc2[6].getText());//
             if(soluong==1)
                 JOptionPane.showMessageDialog(this, "Số lượng không thể bé hơn 1 ");
             else{
                 jlc2[6].setText((soluong-1) + "");
           String gia = FormatInt.format(this.sanpham_DTO.getPrice() * (soluong-1));
            jlc2[4].setText(gia + "");
             }

        }
        if (e.getSource() == jlc2[7]) {
            int soluongmaxcuasanpham=0;
            String maSizeThem="";
            String tensize = (String)optionsize.getSelectedItem();
             SizeBUS sizeBUS = new SizeBUS();
                for(SizeDTO s : sizeBUS.getList()){//duyet mang size de lay ra ma size
                    if(s.getTENSIZE().equals(tensize)) maSizeThem=s.getMASIZE();//lay ra ma size tu ten size ma nguoi dung lua chon
                }
        for(chitietsanpham_DTO c : chitietsanpham_BUS.getlist()){
            if(c.getMASP().equals(sanpham_DTO.getMaSP()) && c.getMASIZE().equals(maSizeThem))
                soluongmaxcuasanpham=c.getSoluong();
        }

            
            int soluonghiencotronggiohang=0;
            for(SanPhamDTO s : dssptt ){
                if(sanpham_DTO.getMaSP().equals(s.getMaSP())){
                    for(chitietsanpham_DTO c : dsctsptt){
                        if(s.getMaSP().equals(c.getMASP()) && c.getMASIZE().equals((String)optionsize.getSelectedItem()))
                            soluonghiencotronggiohang=c.getSoluong();
                    }
                    break;
                }
            }
           
            int soluong = Integer.parseInt(jlc2[6].getText());//
            if(soluongmaxcuasanpham>=(soluong+1+soluonghiencotronggiohang)){
                jlc2[6].setText((soluong+ 1) + "");
                String gia = FormatInt.format(this.sanpham_DTO.getPrice() * (soluong+1));
                jlc2[4].setText(gia+ "");
            }
            else{
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại do số lượng trong kho không đủ!");
            }
        }
        if (e.getSource() == jlc1[1]) {
            Image scaledImage_be2 = h1.getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH);
            ImageIcon h_be2 = new ImageIcon(scaledImage_be2);

            jlc1[0].setIcon(h_be2);

        }

        if (e.getSource() == jlc1[2]) {
            Image scaledImage_be2 = h2.getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH);
            ImageIcon h_be2 = new ImageIcon(scaledImage_be2);

            jlc1[0].setIcon(h_be2);

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == jln2) {
            jln2.setBackground(Color.red);
            jln2.setOpaque(true);
        }
        if (e.getSource() == jlc2[1]) {
            jlc2[1].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
        }
        if (e.getSource() == jlc2[2]) {
            jlc2[2].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
        }
        if (e.getSource() == jlc2[3]) {
            jlc2[3].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
        }
        if (e.getSource() == jlc2[5]) {
            jlc2[5].setBackground(Color.decode("#2980b9"));
            jlc2[5].setOpaque(true);
        }
        if (e.getSource() == jlc2[7]) {
            jlc2[7].setBackground(Color.decode("#2980b9"));
            jlc2[7].setOpaque(true);
        }
        if (e.getSource() == jlc2[8]) {
            jlc2[8].setBackground(Color.decode("#2980b9"));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == jln2) {
            jln2.setBackground(null);
        }
        if (e.getSource() == jlc2[1]) {
            jlc2[1].setBorder(BorderFactory.createLineBorder(Color.white));
        }
        if (e.getSource() == jlc2[2]) {
            jlc2[2].setBorder(BorderFactory.createLineBorder(Color.white));
        }
        if (e.getSource() == jlc2[3]) {
            jlc2[3].setBorder(BorderFactory.createLineBorder(Color.white));
        }
        if (e.getSource() == jlc2[5]) {
            jlc2[5].setBackground(Color.decode("#0A3D62"));
            jlc2[5].setOpaque(true);
        }
        if (e.getSource() == jlc2[7]) {
            jlc2[7].setBackground(Color.decode("#0A3D62"));
            jlc2[7].setOpaque(true);
        }
        if (e.getSource() == jlc2[8]) {
            jlc2[8].setBackground(Color.decode("#0A3D62"));
        }

    }

}
