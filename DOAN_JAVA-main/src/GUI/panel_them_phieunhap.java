package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BUS.SanPhamBUS;
import BUS.chitietphieunhap_BUS;
import BUS.nhacungcapBUS;
import BUS.phieunhap_BUS;
import DAO.DAO_phieunhap;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import DTO.chitietphieunhap_DTO;
import DTO.nhacungcapDTO;
import DTO.phieunhap_DTO;
import java.sql.SQLException;

public class panel_them_phieunhap extends JPanel implements MouseListener{
	private JPanel[] jp;
	private JLabel[] jl,jlma;
	private JTextField jtncc, jt[];
	private phieunhap_DTO phieunhap_DTO;
	private chitietphieunhap_DTO chitietphieunhap_DTO;
	private int soluong;
	private int w,k;
	private JPanel danhsach;
	private double tongtien;
	private String newMAPN;
	private JFrame j;
	private ArrayList<panel_con_nhapsanpham> ds,ds_gia_thap_hon;
	private panel_con_nhapsanpham panel_con_nhapsanpham;
	private  String r[];
	private phieunhap_BUS phieunhap_BUS;
	private chitietphieunhap_BUS chitietphieunhap_BUS;
	private SanPhamBUS sanPhamBUS;
	private SanPhamDTO sanPhamDTO;
	private phieunhap_GUI phieunhap_GUI;
	private JTextField jt_loinhuan;
	private JComboBox<String> option_ncc;
	private double loinhuan;
	private DecimalFormat format_double;
        private JLabel jl_manv;
	
	
	public panel_them_phieunhap(int w,int h,phieunhap_GUI phieunhap_GUI,TaiKhoanDTO taiKhoanDTO) {
		jp = new JPanel[7];
		jt = new JTextField[6];  
		jl = new JLabel[8]; 
		
		String [] T = {"MAPN","Ngày","MANV"};
		soluong = 1;
		Font f = new Font("Arial", Font.BOLD, 15);
		
		DAO_phieunhap c = new DAO_phieunhap();
		int y = c.select_max() +1;
		if (y < 10) {
			newMAPN = "PN00" +y;
		} else if (y >= 10 && y < 100) {
			newMAPN = "PN0"+y;
		} else if (y >= 100) {
			newMAPN = "PN"+y;
		}
		
		 r = new String[] {newMAPN,LocalDate.now()+""};
		jlma = new JLabel[2];
		
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(w,h));
		this.w = w;
		this.k =1;
		this.j = j;
		
		ds = new ArrayList<panel_con_nhapsanpham>();
		phieunhap_BUS = new phieunhap_BUS();
		ds_gia_thap_hon = new ArrayList<panel_con_nhapsanpham>();
		
		this.phieunhap_GUI = phieunhap_GUI;
		ArrayList<String> list_ncc = new ArrayList<String>();
		nhacungcapBUS nhacungcapBUS = new nhacungcapBUS();
		sanPhamBUS = new SanPhamBUS();
		
		format_double = new DecimalFormat("#,###.0");
        
		
		
		for (nhacungcapDTO ncc : nhacungcapBUS.getList()) {
			list_ncc.add(ncc.getMANCC());
		}
		/////////// tilte phieu nhap /////////////
		
		JLabel title = new JLabel("PHIẾU NHẬP MỚI",JLabel.LEFT);
		
		title.setPreferredSize(new Dimension(w,30));
		title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		title.setBackground(Color.decode("#60A3BC"));title.setOpaque(true);
		
		
		////////////////// khoảng cách từ title center/////////////////////////
		
		
		
		JPanel jpp = new JPanel();
		jpp.setPreferredSize(new Dimension(w,80));
		jpp.setLayout(new FlowLayout(0,0,0));

		
		jpp.add(title);
		
		
		
		//////////////////////////// MAPN  NGAY      ///////////////////////
	
		JPanel center = new JPanel();
		center.setLayout( new BorderLayout());
		
		
			jl[0] = new JLabel(T[0]+ " :",JLabel.LEFT);
			jl[0].setPreferredSize(new Dimension(50,20));
			jlma[0] = new JLabel(r[0]);
			jlma[0].setPreferredSize(new Dimension(50,20));
			jp[0] = new  JPanel();
			jp[0].setPreferredSize(new Dimension(w/5-35,50));
			jp[0].setLayout(new FlowLayout(1,0,0));
			
			jp[0].add(jl[0]);
			jp[0].add(jlma[0]);
			
			jp[0].setBorder(BorderFactory.createEmptyBorder(10, 25, 0, 0));
			
			jpp.add(jp[0]);
			
		
		/////////////////////////////// Ngày //////////////////////////////////
		jl[1] = new JLabel(T[1]+ " :",JLabel.LEFT);
		jl[1].setPreferredSize(new Dimension(40,20));
		jlma[1] = new JLabel(r[1]);
		jlma[1].setPreferredSize(new Dimension(60,20));
		jp[1] = new  JPanel();
		jp[1].setPreferredSize(new Dimension(w/5-25,50));
		jp[1].setLayout(new FlowLayout(1,0,0));
		
		jp[1].add(jl[1]);
		jp[1].add(jlma[1]);
		
		jp[1].setBorder(BorderFactory.createEmptyBorder(10, 25, 0, 0));
		
		jpp.add(jp[1]);
		
		
		
		/////////////////////////////// Mã NV/////////////////////////////////////////
		jl[2] = new JLabel(T[2],JLabel.LEFT);
		jl[2].setPreferredSize(new Dimension(50,20));
                
                
		jl_manv = new JLabel(taiKhoanDTO.getMaNV());
                
                
		jp[2] = new  JPanel();
		jp[2].setPreferredSize(new Dimension(w/5+15,50));
		jp[2].setLayout(new FlowLayout(1,0,0));
		
		
		jp[2].add(jl[2]);
		jp[2].add(jl_manv);
		jp[2].setBorder(BorderFactory.createEmptyBorder(10, 25, 0, 0));
		jpp.add(jp[2]);
		
		////////////////////////////////// MA NCC /////////////////////////////////////////////////////
		JLabel jlncc = new JLabel("MANCC",JLabel.LEFT);
		jlncc.setPreferredSize(new Dimension(50,20));
		
		
		option_ncc = new JComboBox<String>(list_ncc.toArray(new String[list_ncc.size()]));
		option_ncc.setPreferredSize(new Dimension(70,20));
		
		
		
		
		JPanel pncc = new JPanel();
		pncc.setPreferredSize(new Dimension(w/5+15,50));
		pncc.setLayout(new FlowLayout(1,0,0));
		
		pncc.setBorder(BorderFactory.createEmptyBorder(10, 25, 0, 0));
		
		pncc.add(jlncc);
		pncc.add(option_ncc);
		
		jpp.add(pncc);
		
		
		////////////////////////// lợi nhuận ///////////////////////////////////////////
		
		jt_loinhuan = new JTextField("1.23456");
		jt_loinhuan.setPreferredSize(new Dimension(70,20));
		jt_loinhuan.setForeground(Color.GRAY);
		jt_loinhuan.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (jt_loinhuan.getText().equals("")) {
					jt_loinhuan.setText("1.23456");
					jt_loinhuan.setForeground(Color.gray);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (jt_loinhuan.getText().equals("1.23456")) {
					jt_loinhuan.setText("");
					jt_loinhuan.setForeground(Color.black);
				}
			
			}
		});
		
		
		
		
		
		
		
		jt_loinhuan.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != '.') {
					e.consume();
				}
			}
		});
		
	
		
		
		JLabel loinhuan = new JLabel("Lợi nhuận (%)",JLabel.CENTER);
		loinhuan.setPreferredSize(new Dimension(90,20));
		
		
		
		JPanel ploinhuan = new JPanel();
		ploinhuan.setPreferredSize(new Dimension(w/5,50));
		ploinhuan.setLayout(new FlowLayout(1,0,0));
		
		ploinhuan.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
	
		
		ploinhuan.add(loinhuan);
		ploinhuan.add(jt_loinhuan);
		
		jpp.add(ploinhuan);
		
		
		
		this.add(jpp,BorderLayout.NORTH);
		
		
		
		
		//////////////////// SẢN PHẨM NHẬP //////////////////////
		
		
		panel_con_nhapsanpham = new panel_con_nhapsanpham(w-90,k,this);
		ds.add(panel_con_nhapsanpham);
		k++;
		
		
		jp[3] = new JPanel();
		jp[3].setPreferredSize(new Dimension(w,80));
		jp[3].setLayout(new FlowLayout(0,20,10));
		jp[3].add(panel_con_nhapsanpham);
		
		
		JPanel dssp = new JPanel();
		dssp.setLayout(new BorderLayout());
		JScrollPane sc = new JScrollPane(dssp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		
		dssp.add(jp[3]);
		center.add(sc,BorderLayout.CENTER);
		
		
		////////////////////////// Tổng tiền ////////////////////////////
		
		
		
		JPanel jp_south_dssp = new JPanel();
		
		jp_south_dssp.setPreferredSize(new Dimension(w,30));
		jl[5] = new JLabel("Tổng tiền : " + tongtien);
		
		jp_south_dssp.add(jl[5]);
		center.add(jp_south_dssp,BorderLayout.SOUTH);
		
		
		
		
		
		///////////// icons them san pham ///////////////
		
		jl[4] = new JLabel();
		ImageIcon icon_add = new ImageIcon("./src/images/add_icon.png");
		jl[4].setIcon(icon_add);
		jl[4].addMouseListener(this);
		
		
		
		

		
		jp[4] = new JPanel();
		jp[4].setPreferredSize(new Dimension(w,50));
		jp[4].setLayout(new FlowLayout(0));
		jp[4].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		jp[4].add(jl[4]);
		
		
		
		/////////////////////////// nút xác nhận ///////////////////////////
		
		jl[6] = new JLabel("Xác nhận",JLabel.CENTER);
		jl[6].setPreferredSize(new Dimension(80,30));
		jl[6].setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		jl[6].setForeground(Color.white);
		jl[6].setBackground(Color.decode("#60A3BC")); jl[6].setOpaque(true);
		jl[6].setFont(f);
		jl[6].addMouseListener(this);
		
		/////////////////////////////// nút hủy //////////////////////
		
		jl[7] = new JLabel("Hủy",JLabel.CENTER);jl[7].addMouseListener(this);
		jl[7].setPreferredSize(new Dimension(50,30));
		jl[7].setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		jl[7].setForeground(Color.white);
		jl[7].setBackground(Color.decode("#60A3BC")); jl[7].setOpaque(true);
		jl[7].setFont(f);
                
                jl[7].addMouseListener(this);
		
		
		jp[5] = new JPanel();
		jp[5].setPreferredSize(new Dimension(w,30));
		jp[5].add(jl[6]);
		jp[5].add(jl[7]);
		
		JPanel panel_south = new JPanel();
		panel_south.setPreferredSize(new Dimension(w,120));
		
		panel_south.setLayout(new FlowLayout());
		
		panel_south.add(jp[4]);
		panel_south.add(jp[5]);
		
		
		this.add(panel_south,BorderLayout.SOUTH);

		
		
		
		
		
		
		
		this.add(center,BorderLayout.CENTER);
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
	}
	
	

	public ArrayList<panel_con_nhapsanpham> ds_hang_them(){
		return this.ds;
	}
	
	
	
	public double loi_nhuan() {
		loinhuan = Double.parseDouble(jt_loinhuan.getText());
		return loinhuan;
	}
	
	public void tong_tien_cong(double t) {
 		 tongtien += t;
 		String l = format_double.format(tongtien);
		jl[5].setText("Tổng tiền : " + l);
		
	}
	
	public void tong_tien_tru(double t) {
		tongtien -= t;
		String l = format_double.format(tongtien);
		jl[5].setText("Tổng tiền : " +	l);
	}
	
	public String get_newMAPN() {
		return this.r[0];
	}
	
	public void them_phieunhap() {
            
		this.phieunhap_DTO = new phieunhap_DTO(r[0],jl_manv.getText() , tongtien,(String) option_ncc.getSelectedItem());
		this.phieunhap_BUS.add(phieunhap_DTO);	
		
		
	}
	
	public void them_chitietphieunhap() {
		this.chitietphieunhap_BUS = new chitietphieunhap_BUS(phieunhap_DTO);
		for (panel_con_nhapsanpham h : ds) {
			chitietphieunhap_BUS.add(h.return_chitietphieunhap());
			
		}
	}
	

	
	
	
	public boolean kiem_tra_rong() {
		if (jt_loinhuan.getText().equals("1.23456")) {
			return true;
		}
		for (panel_con_nhapsanpham d : ds ) {
			d.xacnhan();
			if (d.kiem_tra_rong()) {
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean kiem_tra_trung() {
		ArrayList<chitietphieunhap_DTO> ds_ctpn = new ArrayList<chitietphieunhap_DTO>();
		for (panel_con_nhapsanpham h : ds) {
			ds_ctpn.add(h.return_chitietphieunhap());
			System.out.println(h.return_chitietphieunhap().toString());
		}
		
		for(int i = 0 ; i < ds_ctpn.size() ; i++) {
			for (int j = i+1 ; j < ds_ctpn.size(); j++) {
				if (ds_ctpn.get(i).getMasp().equals(ds_ctpn.get(j).getMasp())   && ds_ctpn.get(i).getMasize().equals(ds_ctpn.get(j).getMasize())   ) {
					return true;
				}
			}
		}
		return false;		
		
	}
	
	
	public String kiem_tra_gia_update() {
		String t = "Giá mới";
		int i = 0;
		for (panel_con_nhapsanpham h : ds) {
			if (h.giamoi_caohon_giacu()) {
				h.update_price();
			} else {
				i++;
				ds_gia_thap_hon.add(h);
				t +=  " " + h.return_sanpham().getMaSP() ;
				
			}
		}
		t += " thấp hơn giá cũ bạn có muốn cập nhật?"; 
		
		if (i > 0) {
//			this.phieunhap_GUI.thong_bao(t);
			for (panel_con_nhapsanpham h : ds_gia_thap_hon ) {
				h.return_sanpham().toString();
			}
		}
		return t;
	}
	
	
	
	
//	public void update_price() {
//		String t = this.kiem_tra_gia_update();
//		if(t.contains("SP")) {
//			frame_thong_bao_phieunhap c = new frame_thong_bao_phieunhap(t, this.phieunhap_GUI);
//		}
//		
//	}
	
	
	public void update_chitietsanpham() throws SQLException {
		for (panel_con_nhapsanpham h : ds) {
			h.update_chitietsanpham();
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jl[4]) {
			panel_con_nhapsanpham h = new panel_con_nhapsanpham(w-90, k,this);
			this.ds.add(h);
			k++;
			
			jp[3].add(h);
			jp[3].setPreferredSize(new Dimension(jp[3].getPreferredSize().width,jp[3].getPreferredSize().height+80));

			this.revalidate();
		}
		
	
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == jl[6]) {
			
		}
		/////////////////////// hủy
		if (e.getSource() == jl[7]) {
			this.phieunhap_GUI.Frame_them_phieunhap().setVisible(false);
                        this.phieunhap_GUI.return_null_frame_them_phieu_nhap();
		}
		
		////////////////////////////////////////// NÚT XÁC NHẬN ///////////////////////////////////////
		if (e.getSource() == jl[6]){
			
			
			if (this.kiem_tra_rong()) {
				JOptionPane.showMessageDialog(this,"Thông tin chưa đầy đủ");
			} else if (this.kiem_tra_trung()){
				JOptionPane.showMessageDialog(this,"2 sản phầm bị trùng MASP và MASIZE");
				
			}
			else if(!this.kiem_tra_rong() && !this.kiem_tra_trung()) {
				String t = "Hoàn thành nhập sản phẩm";
                                this.phieunhap_GUI.thong_bao_update_thongtin(t);
				
			}
			
		}
                
		
	}
	
	public void update_gia_thap_hon() {
		for (panel_con_nhapsanpham h : ds_gia_thap_hon) {
			h.update_price();
		}
	}
	


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == jl[4]) {
			jl[4].setBackground(Color.cyan);
			jl[4].setOpaque(true);
			
		}
		if (e.getSource() == jl[6]) {
			jl[6].setBackground(Color.decode("#2980b9"));
		}
		if (e.getSource() == jl[7]) {
			jl[7].setBackground(Color.red);jl[7].setOpaque(true);
		}
		
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == jl[4]) {
			jl[4].setBackground(null);
			jl[4].setOpaque(true);
		}
		if (e.getSource() == jl[6]) {
			jl[6].setBackground(Color.decode("#60A3BC"));
		}
		if (e.getSource() == jl[7]) {
			jl[7].setBackground(Color.decode("#60A3BC"));
		}
		
	}
}
