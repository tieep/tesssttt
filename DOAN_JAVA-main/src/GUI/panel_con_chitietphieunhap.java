package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BUS.SanPhamBUS;
import BUS.chitietphieunhap_BUS;
import BUS.chitietsanpham_BUS;
import BUS.phieunhap_BUS;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import DTO.chitietphieunhap_DTO;
import DTO.chitietsanpham_DTO;
import DTO.phieunhap_DTO;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class panel_con_chitietphieunhap extends JPanel implements MouseListener{
	private JPanel jp[];
	private JLabel[] jl;
	private JTextField jt[];
	private chitietphieunhap_DTO h;
	private phieunhap_BUS phieunhap_BUS;
	private DecimalFormat format_double;
	private int soluong;
	private double gianhap,thanhtien;
	private boolean clicked_sua;
	private chitietphieunhap_BUS chitietphieunhap_BUS;
	private phieunhap_GUI phieunhap_GUI;
	private SanPhamBUS sanPhamBUS;
	
	
	public panel_con_chitietphieunhap(int w,chitietphieunhap_DTO h,phieunhap_GUI phieunhap_GUI) {
		jt = new JTextField[4];
		this.setLayout(new GridLayout(1,5));
		this.h = h;
		this.phieunhap_GUI = phieunhap_GUI;
		this.phieunhap_BUS = new phieunhap_BUS();
		 format_double = new DecimalFormat("#,###.0");
		 soluong = h.getSoluong();
		 gianhap = h.getGianhap();
		String thanh_tien = format_double.format(h.getThanhtien());
		String gia_nhap = format_double.format(h.getGianhap());
		
		String [] t = {h.getMasp(),h.getSoluong()+"",h.getMasize(),gia_nhap,thanh_tien};
		jl = new JLabel[5];
		this.clicked_sua = false;
		
		sanPhamBUS = new SanPhamBUS();
		
		
		for (int i = 0; i < 5 ; i++) {
			jl[i] = new JLabel(t[i],JLabel.CENTER);
			this.add(jl[i]);
			jl[i].addMouseListener(this);
		}
		
		this.setPreferredSize(new Dimension(w+60,30));
		this.setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
		
	}
	public void che_do_sua() {
		
		////////////////////////////// 	số lượng /////////////////////////////////
		
		
		jt[0] = new JTextField(soluong+"");
		
		jt[0].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
                                jt[0].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        
                        
                        chitietsanpham_BUS mn=null;
                        try {
                            mn = new chitietsanpham_BUS ();
                        } catch (SQLException ex) {
                            Logger.getLogger(panel_con_chitietphieunhap.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        int hangton = mn.getSoLuong(h.getMasp(), h.getMasize());
                         int solg = Integer.parseInt(jt[0].getText().replaceAll(",", "")) - h.getSoluong() + hangton;
                         System.out.println(solg + "soluogn so luong");
                         if ( solg < 0){
                         JOptionPane.showMessageDialog(phieunhap_GUI, "Số lượng còn lại đã ít hơn 0, vui lòng nhập lại");
                         jt[0].setText(h.getSoluong()+"");
                         return;
                        }
                    }
                });
			}
			
			
		});
		
		jt[0].getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			
			
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(jt[0].getText().equals("")) {
					thanhtien = 0;
					
				} else {
					thanhtien = Integer.parseInt(jt[0].getText().replaceAll(",", "")) * Double.parseDouble(jt[1].getText().replaceAll(",", ""));
					
					
				}
				
				jl[4].setText(format_double.format(thanhtien));
				phieunhap_GUI.cap_nhap_tongtien();
				
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(jt[0].getText().equals("")) {
					thanhtien = 0 ;
					
				} else {
					
					thanhtien = Integer.parseInt(jt[0].getText().replaceAll(",", "")) * Double.parseDouble(jt[1].getText().replaceAll(",", ""));
					
					
				}
				
				jl[4].setText(format_double.format(thanhtien));
				phieunhap_GUI.cap_nhap_tongtien();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(jt[0].getText().equals("")) {
					thanhtien = 0 ;
					
				} else {
					thanhtien = Integer.parseInt(jt[0].getText().replaceAll(",", "")) * Double.parseDouble(jt[1].getText().replaceAll(",", ""));	
				}
				jl[4].setText(format_double.format(thanhtien));
				phieunhap_GUI.cap_nhap_tongtien();
				
			}
		});
		
		
		this.remove(jl[1]);
		this.add(jt[0],1);
		///////////////////////////// giá nhập ////////////////////////////////////////
		String gianhap_cu = format_double.format(h.getGianhap());
		jt[1] = new JTextField(gianhap_cu);
		
		jt[1].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!='.' && c!=',') {
					e.consume();
				}
			}
			
			
		});
		
		jt[1].getDocument().addDocumentListener(new DocumentListener() {
			
			
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(jt[1].getText().equals("")) {
					thanhtien = 0;
				} else {
					thanhtien = Double.parseDouble(jt[1].getText().replaceAll(",", "")) * Integer.parseInt(jt[0].getText().replaceAll(",", ""));
					
					
				}
				jl[4].setText(format_double.format(thanhtien));
				phieunhap_GUI.cap_nhap_tongtien();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(jt[1].getText().equals("")) {
					thanhtien = 0;
				} else {
					
					
					thanhtien = Double.parseDouble(jt[1].getText().replaceAll(",", "")) * Integer.parseInt(jt[0].getText().replaceAll(",", ""));
					
				}
				jl[4].setText(format_double.format(thanhtien));
				phieunhap_GUI.cap_nhap_tongtien();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(jt[1].getText().equals("")) {
					thanhtien = 0;
				} else {
					thanhtien = Double.parseDouble(jt[1].getText().replaceAll(",", "")) * Integer.parseInt(jt[0].getText().replaceAll(",", ""));
					
				}
				
				jl[4].setText(format_double.format(thanhtien));
				phieunhap_GUI.cap_nhap_tongtien();
			}
		});

		
		
		this.remove(jl[3]);
		this.add(this.add(jt[1]),3);
		
		
		this.repaint();
		this.revalidate();
		this.addMouseListener(this);
		
	}
	
	public void return_true_clicked_sua() {
		this.clicked_sua = true;
	}
	
	////////////////////////////////////////////////////////////////// sử lí update /////////////////////////////////
	public boolean so_sanh() {
		
		try {
			int sluong = Integer.parseInt(jt[0].getText().replaceAll(",", ""));
			double gnhap = Double.parseDouble(jt[1].getText().replaceAll(",", ""));
			
			if (soluong != sluong || gnhap != gianhap) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	
	
	
	public chitietphieunhap_DTO chitietphieunhap_da_sua() {
		int sluong = Integer.parseInt(jt[0].getText().replaceAll(",", ""));
		double gnhap = Double.parseDouble(jt[1].getText().replaceAll(",", ""));
		thanhtien = sluong * gnhap;
		
		chitietphieunhap_DTO m = new chitietphieunhap_DTO(h.getMapn(), h.getMasp(), sluong, gnhap, this.thanhtien, h.getMasize());
		return m;
	}
	
	public chitietsanpham_DTO ctsp_da_sua() {
		
		int sluong = Integer.parseInt(jt[0].getText().replaceAll(",", "")) - soluong;
		
		chitietsanpham_DTO m = new chitietsanpham_DTO(h.getMasp(), h.getMasize(), sluong);
		
		return m;
	}
	
	
	public double tong_tien() {
		return this.thanhtien;
	}
	////////////////////////////////////////////////---------------
	public SanPhamDTO sanpham_da_chinhsua() {
		
		
		SanPhamDTO m =  sanPhamBUS.select_by_id(h.getMasp());
		
		m.setPrice(gianhap);
		return m;
		
	}
	public boolean so_sanh_gia_sanpham() {
		
		
		
		if (Double.parseDouble(jt[1].getText().replaceAll(",", "")) != h.getGianhap()) {
			return false;
		}
		return true;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if ((JLabel)e.getSource() == jl[0]) {
			
		}
		if (e.getSource() == jl[2]) {
			
			
			
			SanPhamDTO m = sanPhamBUS.select_by_id(h.getMasp());
			
			System.out.println(Double.parseDouble(jt[1].getText().replaceAll(",", "")));
			System.out.println("va");
			System.out.println(m.getPrice());
			System.out.println(so_sanh_gia_sanpham());
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
