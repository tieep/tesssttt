package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAO.DAO_qlks;
import DTO.model_qlkh;
import static java.awt.image.ImageObserver.ALLBITS;





public class view_thong_bao extends JPanel implements MouseListener{
	private JFrame j;
	private view_quan_li_khach_hang view_quan_li_khach_hang;
	private JLabel jl_n1,jl_n2, jl_yes, jl_no,jl_c2;
	private JPanel panel_north, panel_center,panel_n1, panel_n2, panel_c1,panel_c2,panel_c3;
	private String content;
//	public view_thong_bao(int w, int h,String t) {
//		
//		
//		this.setSize(w,h);
//		
//		
//		this.setLayout(new BorderLayout());
//		panel_north = new JPanel();
//		
//		Font f = new Font("Arial", Font.BOLD, 16);
//		Font fys = new Font("Arial",Font.BOLD,20);
//		Font x = new Font("Arial", ALLBITS, 20);
//		
//		
//		
//		panel_north.setPreferredSize(new Dimension(0,30));
//		panel_north.setBackground(Color.decode("#0097A7"));panel_north.setOpaque(true);
//		panel_north.setLayout(new GridLayout(1,2,0,0));
//		
//		
//		
//		panel_n2 = new JPanel();
//
//		panel_n2.setLayout(new FlowLayout(2,0,0));
//		
//		
//		panel_n1 = new JPanel();
//		panel_n1.setLayout(new FlowLayout(0));
//
//		
//		
//		jl_n1 = new JLabel("thông báo");
//		ImageIcon icon_tb = new  ImageIcon("./src/hinh_anh/thong_bao.png");
//		jl_n1.setIcon(icon_tb);
//		jl_n1.setFont(f);
//		
//		jl_n2 = new JLabel("X",JLabel.CENTER);
//		jl_n2.setPreferredSize(new Dimension(0,0));
//		jl_n2.setFont(x); 
//		jl_n2.setForeground(Color.white);
//		jl_n2.setPreferredSize(new Dimension(40,30));
//		
//		
//		
//		panel_n2.add(jl_n2);	 
//		panel_n2.setBackground(Color.decode("#0097A7"));
//		panel_n1.add(jl_n1);
//		panel_n1.setBackground(Color.decode("#0097A7"));
//		
//		
//		panel_north.add(panel_n1);
//		panel_north.add(panel_n2);
//		
//		
//			
//		panel_center = new JPanel();
//		panel_center.setLayout(new FlowLayout());
//		
//		
//		panel_c1 = new JPanel();
//		panel_c1.setPreferredSize(new Dimension(1500,50));
//
//		
//			
//		panel_c2 = new JPanel();
//		panel_c2.setPreferredSize(new Dimension(this.getWidth(),(this.getHeight()-30)/3));
//		jl_c2 = new JLabel(t,JLabel.LEFT);jl_c2.setBackground(Color.white);jl_c2.setOpaque(true);
//		jl_c2.setPreferredSize(new Dimension(panel_c2.getPreferredSize().width-panel_c2.getPreferredSize().width/3,panel_c2.getPreferredSize().height-panel_c2.getPreferredSize().height/3));
//		jl_c2.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
//		panel_c2.setLayout(new FlowLayout(1));
//		
//		panel_c2.add(jl_c2);
////		panel_c2.setLayout(new BorderLayout());
//		
//		
//		
//		
//		panel_c3 = new JPanel();
//		panel_c3.setPreferredSize(new Dimension(this.getWidth(),(this.getHeight()-30)/3));
//		jl_yes = new JLabel("yes", JLabel.CENTER);jl_yes.setFont(fys);
//		jl_yes.setPreferredSize(new Dimension(60,25));
//		jl_yes.setBackground(Color.decode("#3498db"));jl_yes.setOpaque(true);
//		
//		
//		jl_no = new JLabel("no",JLabel.CENTER);jl_no.setFont(fys);
//		jl_no.setPreferredSize(new Dimension(60,25));
//		jl_no.setBackground(Color.decode("#3498db"));jl_no.setOpaque(true);
//		
//		panel_c3.setLayout(new FlowLayout(1,60,5));
//		panel_c3.add(jl_yes);
//		panel_c3.add(jl_no);
//
//		panel_center.add(panel_c1);
//		panel_center.add(panel_c2);
//		panel_center.add(panel_c3);
//		
//		
//		jl_n2.addMouseListener(this);
//		jl_yes.addMouseListener(this);
//		jl_no.addMouseListener(this);
//		
//		this.add(panel_north, BorderLayout.NORTH);
//		this.add(panel_center, BorderLayout.CENTER);
//		
//	}
//	
//	///////////// khởi tạo t view thông báo tương tác với view quản lí khách hàng 
//	
//	
//	
	public view_thong_bao(int w, int h,String t,view_quan_li_khach_hang view_quan_li_khach_hang) {
		this.content = t;
		
		this.view_quan_li_khach_hang = view_quan_li_khach_hang;
		this.setSize(w,h);
		
		
		this.setLayout(new BorderLayout());
		panel_north = new JPanel();
		
		Font f = new Font("Arial", Font.BOLD, 16);
		Font fys = new Font("Arial",Font.BOLD,14);
		Font x = new Font("Arial", ALLBITS, 20);
		
		
		
		panel_north.setPreferredSize(new Dimension(0,30));
		panel_north.setBackground(Color.decode("#0097A7"));panel_north.setOpaque(true);
		panel_north.setLayout(new GridLayout(1,2,0,0));
		
		
		
		panel_n2 = new JPanel();

		panel_n2.setLayout(new FlowLayout(2,0,0));
		
		
		panel_n1 = new JPanel();
		panel_n1.setLayout(new FlowLayout(0));

		
		
		jl_n1 = new JLabel("thông báo");
		ImageIcon icon_tb = new  ImageIcon("./src/hinh_anh/thong_bao.png");
		jl_n1.setIcon(icon_tb);
		jl_n1.setFont(f);
		
		jl_n2 = new JLabel("X",JLabel.CENTER);
		jl_n2.setPreferredSize(new Dimension(0,0));
		jl_n2.setFont(x); 
		jl_n2.setForeground(Color.white);
		jl_n2.setPreferredSize(new Dimension(40,30));
		
		
		
		panel_n2.add(jl_n2);	 
		panel_n2.setBackground(Color.decode("#0097A7"));
		panel_n1.add(jl_n1);
		panel_n1.setBackground(Color.decode("#0097A7"));
		
		
		panel_north.add(panel_n1);
		panel_north.add(panel_n2);
		
		
			
		panel_center = new JPanel();
		panel_center.setLayout(new FlowLayout());
		
		
		panel_c1 = new JPanel();
		panel_c1.setPreferredSize(new Dimension(1500,50));

		
			
		panel_c2 = new JPanel();
		panel_c2.setPreferredSize(new Dimension(this.getWidth(),(this.getHeight()-30)/3));
		jl_c2 = new JLabel("<html>"+ t+"<html>",JLabel.LEFT); 
		jl_c2.setPreferredSize(new Dimension(panel_c2.getPreferredSize().width-panel_c2.getPreferredSize().width/3,panel_c2.getPreferredSize().height-panel_c2.getPreferredSize().height/3));
		jl_c2.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		panel_c2.setLayout(new FlowLayout(1));
		panel_c2.add(jl_c2);
//		jl_c2.setBackground(Color.white);jl_c2.setOpaque(true);
		
		
		
		panel_c3 = new JPanel();
		panel_c3.setPreferredSize(new Dimension(this.getWidth(),(this.getHeight()-30)/3));
		jl_yes = new JLabel("Xác nhận", JLabel.CENTER);jl_yes.setFont(fys);
		jl_yes.setPreferredSize(new Dimension(80,25));
		jl_yes.setBackground(Color.decode("#3498db"));jl_yes.setOpaque(true);
		
		
		jl_no = new JLabel("Hủy",JLabel.CENTER);jl_no.setFont(fys);
		jl_no.setPreferredSize(new Dimension(50,25));
		jl_no.setBackground(Color.decode("#3498db"));jl_no.setOpaque(true);
		
		panel_c3.setLayout(new FlowLayout(1,60,5));
		panel_c3.add(jl_yes);
		panel_c3.add(jl_no);

		panel_center.add(panel_c1);
		panel_center.add(panel_c2);
		panel_center.add(panel_c3);
		
		
		jl_n2.addMouseListener(this);
		jl_yes.addMouseListener(this);
		jl_no.addMouseListener(this);
		
		this.add(panel_north, BorderLayout.NORTH);
		this.add(panel_center, BorderLayout.CENTER);
		
	}
	public ArrayList<model_qlkh> ds_kh_xoa(){
		return this.view_quan_li_khach_hang.ds_kh_xoa();
	}
	public void xoa_kh(ArrayList<model_qlkh> ds) {
		for (model_qlkh h : ds) {
			this.view_quan_li_khach_hang.getBus_qlkh().delete(h);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == jl_n2 ) {
			this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
		}
		if (e.getSource() == jl_yes) {
			int i = 0;	
			if (this.content.contains("Xác nhận xóa")) {
				this.view_quan_li_khach_hang.xoa_kh();
				JOptionPane.showMessageDialog(this, "Đã xóa thành công");
				this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
                                this.view_quan_li_khach_hang.return_null_framethongbao();

				this.view_quan_li_khach_hang.return_false_clicked_xoa();
				this.view_quan_li_khach_hang.dinh_dang();
				this.view_quan_li_khach_hang.refresh_bang_dskh();
			
			}
			if(this.content.contains("Bạn có muốn thay đổi thông tin của")) {
				boolean t = this.view_quan_li_khach_hang.ktra_tt_after_update();
				if (t) {
					for (model_qlkh m : this.view_quan_li_khach_hang.ds_kh_update()) {
                                            
						this.view_quan_li_khach_hang.getBus_qlkh().set(m);
						this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
                                                this.view_quan_li_khach_hang.return_null_framethongbao();
						this.view_quan_li_khach_hang.refresh_bang_dskh();

						this.view_quan_li_khach_hang.return_false_clicked_chinhsua();
						this.view_quan_li_khach_hang.dinh_dang();
						i++;	
					}
				} else {
					this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
                                        this.view_quan_li_khach_hang.return_null_framethongbao();
				}
				
				
			}
			else if (this.content.contains("Bạn có muốn hủy bỏ các thay đổi")) {
				this.view_quan_li_khach_hang.return_false_clicked_chinhsua();
				this.view_quan_li_khach_hang.return_false_clicked_xoa();
				this.view_quan_li_khach_hang.dinh_dang();
				this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
                                this.view_quan_li_khach_hang.return_null_framethongbao();
			} 
			else if (i != 0) {
				JOptionPane.showMessageDialog(this, "Đã thay đổi thành công");
			}
			 
			if (this.content.contains("Bạn có muốn hủy bỏ hoạt động chỉnh sửa và chuyển sang hoạt động xóa")) {
				this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
                                this.view_quan_li_khach_hang.return_null_framethongbao();
				this.view_quan_li_khach_hang.return_false_clicked_chinhsua();
				this.view_quan_li_khach_hang.return_true_clicked_xoa();
				this.view_quan_li_khach_hang.dinh_dang();
				JOptionPane.showMessageDialog(this, "Nhấp vào để chọn những khách hàng bạn muốn xóa");
				
			} 
			if (this.content.contains("Bạn có muốn hủy bỏ hoạt động xóa và chuyển sang hoạt động chỉnh sửa")) {
				this.view_quan_li_khach_hang.return_false_clicked_xoa();
				this.view_quan_li_khach_hang.return_true_clicked_chinhsua();
				this.view_quan_li_khach_hang.dinh_dang();
				this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
                                this.view_quan_li_khach_hang.return_null_framethongbao();
				
			}
			if (this.content.contains("Bạn có muốn hủy bỏ chỉnh sửa")) {
				this.view_quan_li_khach_hang.return_false_clicked_chinhsua();
				this.view_quan_li_khach_hang.dinh_dang();
				this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
                                this.view_quan_li_khach_hang.return_null_framethongbao();
			}
			if (this.content.contains("Bạn có muốn hủy bỏ xóa")) {
				this.view_quan_li_khach_hang.return_false_clicked_xoa();
				this.view_quan_li_khach_hang.dinh_dang();
				this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
                                this.view_quan_li_khach_hang.return_null_framethongbao();
                                
			}
			if (this.content.contains("Bạn có muốn hủy những thay đổi")){
				this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
                                this.view_quan_li_khach_hang.return_null_framethongbao();
				this.view_quan_li_khach_hang.return_false_clicked_chinhsua();
				this.view_quan_li_khach_hang.return_false_clicked_xoa();
				this.view_quan_li_khach_hang.dinh_dang();
				this.view_quan_li_khach_hang.search();
				
			}
			
		}
		if(e.getSource() == jl_no) {
			this.view_quan_li_khach_hang.return_frame_thong_bao().setVisible(false);
                        this.view_quan_li_khach_hang.return_null_framethongbao();
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
		 if (e.getSource() == jl_yes) {
				jl_yes.setBackground(Color.decode("#2980b9"));
				jl_yes.setOpaque(true);
			}
			 if (e.getSource() == jl_no) {
				jl_no.setBackground(Color.decode("#2980b9"));jl_no.setOpaque(true);
			}
			 if (e.getSource() == jl_n2 ) {
				 jl_n2.setBackground(Color.red);
				 jl_n2.setOpaque(true);
					
				}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == jl_n2) {
			jl_n2.setBackground(Color.decode("#0097A7"));
			jl_n2.setOpaque(true);
		}
		if (e.getSource() == jl_yes) {
			jl_yes.setBackground(Color.decode("#3498db"));jl_yes.setOpaque(true);
		}
		if (e.getSource() == jl_no) {
			jl_no.setBackground(Color.decode("#3498db"));jl_no.setOpaque(true);
		}
	}
		
	
}
