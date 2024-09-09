package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.util.ArrayList;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import BUS.chitietphieunhap_BUS;
import BUS.chitietsanpham_BUS;
import BUS.phieunhap_BUS;
import DAO.DAO_chitietphieunhap;
import DTO.chitietphieunhap_DTO;
import DTO.chitietsanpham_DTO;
import DTO.phieunhap_DTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;

public class chitietphieunhap_GUI extends JPanel implements MouseListener {
	private phieunhap_DTO phieunhap_DTO;
	private DAO_chitietphieunhap DAO_chiChitietphieunhap;
	private panel_center_chitietphieunhap panel_center_chitietphieunhap;
	private JPanel[] jp;
	private JLabel[] jl,jl0,jl2,jlsua,jltt;
	private JPanel center;
	private JTextField[] jtsua;
	private phieunhap_GUI phieunhap_GUI;
	private panel_bang_chitietphieunhap panel_bang_chitietphieunhap;
	private boolean clicked_chinhsua;
	private chitietphieunhap_BUS chitietphieunhap_BUS;
	private chitietsanpham_BUS chitietsanpham_BUS;
	private DecimalFormat d;
	private phieunhap_BUS phieunhap_BUS;
	private int w,h;
	
	public chitietphieunhap_GUI(int w,int h,phieunhap_DTO phieunhap_DTO,phieunhap_GUI phieunhap_GUI) throws SQLException {
		this.setPreferredSize(new Dimension(w,h));
		this.setLayout(new BorderLayout());
		this.phieunhap_DTO = phieunhap_DTO;
		this.DAO_chiChitietphieunhap = new DAO_chitietphieunhap();
		this.clicked_chinhsua = false;
		Font f = new Font(TOOL_TIP_TEXT_KEY, 1, 20);
		 d = new DecimalFormat("#,###.00") ;
		 this.w = w;
		 this.h = h;
		this.phieunhap_GUI = phieunhap_GUI;
		Color mau = Color.decode("#60A3BC");
		
		this.chitietphieunhap_BUS = new chitietphieunhap_BUS(this.phieunhap_DTO);
		this.chitietsanpham_BUS = new chitietsanpham_BUS();
		this.phieunhap_BUS = new phieunhap_BUS();
		this.setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
		
		JPanel panel_north = new JPanel();
		panel_north.setLayout(new FlowLayout(0,0,0));
		panel_north.setPreferredSize(new Dimension(w,80));
	
		
		jp = new JPanel[4] ;
		jl = new JLabel[3];
		jl0 = new JLabel[3];
		jl2 = new  JLabel[5];
		/////////////////////////// TITLE CHI TIET PHIEU NHAP /////////////////////////////////
		
		
		jl0[0] = new JLabel("CHI TIẾT PHIẾU NHẬP",JLabel.LEFT);
		jl0[0].setForeground(mau);
		jl0[0].setPreferredSize(new Dimension(w,20));
		jl0[0].setFont(f);
		
		
		

		
		
		JPanel p_title = new JPanel();
		p_title.setLayout(new FlowLayout());
		p_title.setPreferredSize(new Dimension(w,30));
		p_title.add(jl0[0]);
		
		
		//////////////////////////// MAPN + NAGY + MANV ////////////////////////
		
		jp[0] = new JPanel();
		jp[0].setLayout(new FlowLayout(0));
		jp[0].setPreferredSize(new Dimension(w,100));
		
		jp[0].add(p_title);
		
		
		
		
		jlsua = new JLabel[6];
		jtsua = new JTextField[4];
		
		
		jlsua[0] = new JLabel("MASP : " +phieunhap_DTO.getMAPN() + " -- Ngày : ");
		jp[0].add(jlsua[0]); 
		
		jlsua[1] = new JLabel(phieunhap_DTO.getNgay()+"");
		jp[0].add(jlsua[1]);
		
		jlsua[2] = new JLabel(" -- MANV : ");
		jp[0].add(jlsua[2]);
		
		jlsua[3] = new JLabel(phieunhap_DTO.getMANV());
		jp[0].add(jlsua[3]);
		
		jlsua[4] = new JLabel(" -- MANCC : ");
		jp[0].add(jlsua[4]);
		
		jlsua[5] = new JLabel(phieunhap_DTO.getMANCC());
		jp[0].add(jlsua[5]);
		
		
//		for (int i =0; i< 6; i++) {
//			jlsua[i].setForeground(mau);
//		}
		panel_north.add(jp[0]);

		
		///////////////////////// vạch cách title với content ////////////////////////////
		
		jl[1] = new JLabel("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		jl[1].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		panel_north.add(jl[1]);
		this.add(panel_north,BorderLayout.NORTH);

		
		////////////////////// danh sác sản phẩm nhập /////////////////////
		
		this.panel_bang_chitietphieunhap = new panel_bang_chitietphieunhap(w, phieunhap_DTO,phieunhap_GUI);
		
		 
		
		jp[2] = new JPanel();
		jp[2].setLayout(new BorderLayout());
		
		
		jp[2].add(panel_bang_chitietphieunhap);
		
		JScrollPane sc = new JScrollPane(jp[2], JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		

	
		this.add(sc,BorderLayout.CENTER);
		
		/////////////////////////////////// tong tien ///////////////////////////
		
		panel_center_chitietphieunhap = new panel_center_chitietphieunhap(w, phieunhap_DTO, phieunhap_GUI);
		String t = d.format(this.panel_center_chitietphieunhap.tongtien());
		jl[2] = new JLabel("Tổng tiền :" + t+"đ");
		
		
		jp[3] = new JPanel();
		jp[3].setLayout(new BorderLayout());
		
		jp[3].add(jl[2],BorderLayout.EAST);
		
		
		this.add(jp[3],BorderLayout.SOUTH);
		
		
		
	}
	
	
	public void che_do_xem() throws SQLException {
		this.removeAll();
		this.setPreferredSize(new Dimension(w,h));
		this.setLayout(new BorderLayout());
		this.phieunhap_DTO = phieunhap_DTO;
		this.DAO_chiChitietphieunhap = new DAO_chitietphieunhap();
		this.clicked_chinhsua = false;
		Font f = new Font(TOOL_TIP_TEXT_KEY, 1, 20);
		 d = new DecimalFormat("#,###.00") ;
		this.phieunhap_GUI = phieunhap_GUI;
		Color mau = Color.decode("#60A3BC");
		
		this.chitietphieunhap_BUS = new chitietphieunhap_BUS(this.phieunhap_DTO);
		this.chitietsanpham_BUS = new chitietsanpham_BUS();
		this.phieunhap_BUS = new phieunhap_BUS();
		this.setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
		
		JPanel panel_north = new JPanel();
		panel_north.setLayout(new FlowLayout(0,0,0));
		panel_north.setPreferredSize(new Dimension(w,80));
	
		
		jp = new JPanel[4] ;
		jl = new JLabel[3];
		jl0 = new JLabel[3];
		jl2 = new  JLabel[5];
		/////////////////////////// TITLE CHI TIET PHIEU NHAP /////////////////////////////////
		
		
		jl0[0] = new JLabel("CHI TIẾT PHIẾU NHẬP",JLabel.LEFT);
		jl0[0].setForeground(mau);
		jl0[0].setPreferredSize(new Dimension(w,20));
		jl0[0].setFont(f);
		
		
		

		
		
		JPanel p_title = new JPanel();
		p_title.setLayout(new FlowLayout());
		p_title.setPreferredSize(new Dimension(w,30));
		p_title.add(jl0[0]);
		
		
		//////////////////////////// MAPN + NAGY + MANV ////////////////////////
		
		jp[0] = new JPanel();
		jp[0].setLayout(new FlowLayout(0));
		jp[0].setPreferredSize(new Dimension(w,100));
		
		jp[0].add(p_title);
		
		
		
		
		jlsua = new JLabel[6];
		jtsua = new JTextField[4];
		
		
		jlsua[0] = new JLabel("MASP : " +phieunhap_DTO.getMAPN() + " -- Ngày : ");
		jp[0].add(jlsua[0]); 
		
		jlsua[1] = new JLabel(phieunhap_DTO.getNgay()+"");
		jp[0].add(jlsua[1]);
		
		jlsua[2] = new JLabel(" -- MANV : ");
		jp[0].add(jlsua[2]);
		
		jlsua[3] = new JLabel(phieunhap_DTO.getMANV());
		jp[0].add(jlsua[3]);
		
		jlsua[4] = new JLabel(" -- MANCC : ");
		jp[0].add(jlsua[4]);
		
		jlsua[5] = new JLabel(phieunhap_DTO.getMANCC());
		jp[0].add(jlsua[5]);
		
		
//		for (int i =0; i< 6; i++) {
//			jlsua[i].setForeground(mau);
//		}
		panel_north.add(jp[0]);

		
		///////////////////////// vạch cách title với content ////////////////////////////
		
		jl[1] = new JLabel("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		jl[1].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		panel_north.add(jl[1]);
		this.add(panel_north,BorderLayout.NORTH);

		
		////////////////////// danh sác sản phẩm nhập /////////////////////
		
		this.panel_bang_chitietphieunhap = new panel_bang_chitietphieunhap(this.getPreferredSize().width, phieunhap_DTO,phieunhap_GUI);
		
		 
		
		jp[2] = new JPanel();
		jp[2].setLayout(new BorderLayout());
		
		
		jp[2].add(panel_bang_chitietphieunhap);
		
		JScrollPane sc = new JScrollPane(jp[2], JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		

	
		this.add(sc,BorderLayout.CENTER);
		
		/////////////////////////////////// tong tien ///////////////////////////
		
		panel_center_chitietphieunhap = new panel_center_chitietphieunhap(w, phieunhap_DTO, phieunhap_GUI);
		String t = d.format(this.panel_center_chitietphieunhap.tongtien());
		jl[2] = new JLabel("Tổng tiền :" + t+"đ");
		
		
		jp[3] = new JPanel();
		jp[3].setLayout(new BorderLayout());
		
		jp[3].add(jl[2],BorderLayout.EAST);
		
		
		this.add(jp[3],BorderLayout.SOUTH);
	}
	
	public void che_do_sua() {
		if(!this.clicked_chinhsua) {
			this.clicked_chinhsua = true;
			this.panel_bang_chitietphieunhap.che_do_sua();
			
			jl0[0].setText("SỬA CHI TIẾT PHIẾU NHẬP");
			
			
			
			
			jtsua[0] = new JTextField(phieunhap_DTO.getNgay()+"");
			jp[0].remove(jlsua[1]);
			jp[0].add(jtsua[0],2);
			
			jtsua[0].addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!Character.isDigit(c) && c != '-') {
						e.consume();
					}
				}
			});
			
			
			
			jltt = new JLabel[2];
			
			jtsua[1] = new JTextField(phieunhap_DTO.getMANV());
			jp[0].remove(jlsua[3]);
			jp[0].add(jtsua[1],4);
			
			jtsua[2] = new JTextField(phieunhap_DTO.getMANCC());
			jp[0].remove(jlsua[5]);
			jp[0].add(jtsua[2],6);
			
			JPanel l = new JPanel();
			
			jltt[0] = new JLabel("Lưu",JLabel.CENTER);
                        jltt[0].setForeground(Color.white);
			jltt[0].setBackground(Color.decode("#0A3D62"));jltt[0].setOpaque(true);
			jltt[0].setPreferredSize(new Dimension(40,20));
			
			
			jltt[1] = new JLabel("Hủy",JLabel.CENTER);
                        jltt[1].setForeground(Color.white);
			jltt[1].setBackground(Color.decode("#0A3D62"));jltt[1].setOpaque(true);
			jltt[1].setPreferredSize(new Dimension(40,20));
			jltt[0].addMouseListener(this);
			jltt[1].addMouseListener(this);
			
			l.add(jltt[0]);
			l.add(jltt[1]);
			
			jp[3].add(l,BorderLayout.WEST);
			
			
			
			this.repaint();
			this.revalidate();
		} 
		
	}
	
	public void return_true_clicked_sua() {
		this.panel_bang_chitietphieunhap.return_true_clickde_sua();
	}
	
	///////////////////////////////// kiểm tra xem dữ liệu đã được chỉnh sửa hay chưa 
	public boolean so_sanh() {
		LocalDate ngay = phieunhap_DTO.getNgay();
		String manv =phieunhap_DTO.getMANV();
		String mancc = phieunhap_DTO.getMANCC();
		try {
			ngay = LocalDate.parse(jtsua[0].getText().trim());
			manv = jtsua[1].getText().trim();
			mancc = jtsua[2].getText().trim();
			
		} catch (Exception e) {
			return true;
		}
		
		
		
		if (ngay.equals(phieunhap_DTO.getNgay()) && manv.equals(phieunhap_DTO.getMANV()) && mancc.equals(phieunhap_DTO.getMANCC()) && this.panel_bang_chitietphieunhap.so_sanh()) {
			return true;
		}
		return false;
	}
// ************************************** sử lí update *************************************************** ///
	//////////////////////////////////////////////update những ctpn đã được chỉnh sửa /////////////////////////
	
	public void update_ctpn_sau_chinh_sua(){
		for (chitietphieunhap_DTO h : this.panel_bang_chitietphieunhap.ds_ctpn_da_chinh_sua()) {
			chitietphieunhap_BUS.set(h);
		}
		
	}
	
	public void update_ctsp_sau_chinh_sua() throws SQLException {
		for (chitietsanpham_DTO h : this.panel_bang_chitietphieunhap.ds_ctsp_da_chinh_sua()) {
			System.out.println(h.toString());
			chitietsanpham_BUS.update(h);
		}
	}
		
	public void set_tongtien() {
		String t = d.format(this.panel_bang_chitietphieunhap.gettongtien());
		this.jl[2].setText("Tổng tiền : " + t + "đ");
	}
	
	public phieunhap_DTO phieunhapmoi() {
		phieunhap_DTO m = phieunhap_DTO;	
		LocalDate ngay = LocalDate.parse(jtsua[0].getText()); 
		String manv = jtsua[1].getText().trim();
		String mancc = jtsua[2].getText().trim();
			
		m.setMANV(manv);
		m.setNgay(ngay);
		m.setMANCC(mancc);
			
		return m;
	}
	

	
	public void update_phieunhap() {
		phieunhap_BUS.update(this.phieunhapmoi());
	}

	public String thong_bao_thay_doi_gia() {
		return this.panel_bang_chitietphieunhap.thong_bao_doi_gia();
	}
        
        public void return_gia_tri_cu(){
            try {
                this.phieunhap_GUI.show_chitietphieunhap_chinhsua(phieunhap_DTO);
            } catch (SQLException ex) {
                Logger.getLogger(chitietphieunhap_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}
	
	

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println();
		if (e.getSource() == jltt[0]) {
			
			if (jtsua[0].getText().isEmpty() || jtsua[1].getText().isEmpty() || jtsua[2].getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Điền đầy dủ thông tin Ngày, MANV, MANCC");
			}
			 else if (this.so_sanh()) {
				 
				JOptionPane.showMessageDialog(phieunhap_GUI, "Dữ liệu giống dữ liệu cũ");
			}
			 else if (!this.so_sanh()) {
				 	
					String t = "Bạn có muốn thay đổi dữ liệu";
					this.phieunhap_GUI.thong_bao_update_thongtin(t);
				}
		}
		if (e.getSource() == jltt[1]) {
                    
                    this.phieunhap_GUI.thong_bao_update_thongtin("Trả về giá trị ban đầu");
                    
//			this.phieunhap_GUI.show_chitietphieunhap_chinhsua(phieunhap_DTO);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == jltt[0]){
                    jltt[0].setBackground(Color.decode("#60A3BC"));
                    jltt[0].setOpaque(true);
                }
                
                    if (e.getSource() == jltt[1]){
                    jltt[1].setBackground(Color.decode("#60A3BC"));
                    jltt[1].setOpaque(true);
                }
                
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == jltt[0]){
                    jltt[0].setBackground(Color.decode("#0A3D62"));
                    jltt[0].setOpaque(true);
                }
                
                    if (e.getSource() == jltt[1]){
                    jltt[1].setBackground(Color.decode("#0A3D62"));
                    jltt[1].setOpaque(true);
                }
		
	}
	
	

}
