package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.DAO_qlks;
import DTO.model_qlkh;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;

public class view_them_kh extends JPanel implements MouseListener{
	private JTextField[] tf;
	private JLabel[] jl;
	private JPanel[] jp;
	private JPanel jp1,jpn,jpc;
	private JLabel jln;
	private view_quan_li_khach_hang view_quan_li_khach_hang;
	private frame_themkh frame_themkh;
	private panel_center_bang_dskh panel_center_bang_dskh;
	
	
	
	public view_them_kh(int w,int h,view_quan_li_khach_hang view_quan_li_khach_hang) {
		
		this.setPreferredSize(new Dimension(w,h));
		this.setLayout(new BorderLayout());
		this.view_quan_li_khach_hang = view_quan_li_khach_hang;
		
		
		jpn = new JPanel();jpn.setPreferredSize(new Dimension(this.getPreferredSize().width,30));
		jpn.setLayout(new FlowLayout(0));
		
		jpn.setBackground(Color.decode("#60A3BC"));
		jln = new JLabel("Thêm khách hàng mới");
		ImageIcon ic_add = new ImageIcon("./src/hinh_anh/add1.png");
		jln.setIcon(ic_add);jln.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jpn.add(jln);
		
		jpc = new JPanel();
		jpc.setLayout(new FlowLayout());
		
		
		
		
		Font f = new Font(TOOL_TIP_TEXT_KEY, Font.BOLD, 14);
		jp1 = new JPanel();
		jp1.setPreferredSize(new Dimension(this.getPreferredSize().width,30));
		
		jp = new JPanel[5];
		jl = new JLabel[4];
		tf = new JTextField[2];
		
		jp[0] = new JPanel() ;
		jp[0].setPreferredSize(new Dimension(this.getPreferredSize().width-40,30));
//		jp[0].setBackground(Color.blue);
		jp[0].setLayout(new FlowLayout(1,0,0));
		jl[0] = new JLabel("Tên khách hàng",JLabel.CENTER);
		jl[0].setPreferredSize(new Dimension(jp[0].getPreferredSize().width/2-20,30));
		
		tf[0] = new JTextField();
		tf[0].setPreferredSize(new Dimension(jp[0].getPreferredSize().width/2+20,30));
		jp[0].add(jl[0]);
		jp[0].add(tf[0]);
		
		
		
		jp[1] = new JPanel();
		jp[1].setPreferredSize(new Dimension(this.getPreferredSize().width-40,30));
		
		
		
		jp[2] = new JPanel();
		jp[2].setPreferredSize(new Dimension(this.getPreferredSize().width-40,30));
//		jp[2].setBackground(Color.blue);
		jp[2].setLayout(new FlowLayout(1,0,0));
		jl[1] = new JLabel("Số điện thoại",JLabel.CENTER);
		jl[1].setPreferredSize(new Dimension(jp[0].getPreferredSize().width/2-20,30));
		
		tf[1] = new JTextField();
		tf[1].setPreferredSize(new Dimension(jp[0].getPreferredSize().width/2+20,30));
		
		jp[2].add(jl[1]);
		jp[2].add(tf[1]);
		
		
		
		jp[3] = new JPanel();
		jp[3].setPreferredSize(new Dimension(this.getPreferredSize().width-40,20));
		
		
		
		jp[4] = new JPanel();
		jp[1].setPreferredSize(new Dimension(this.getPreferredSize().width-40,30));
		jp[4].setLayout(new FlowLayout(1,30,0));
		jl[2] = new JLabel("Thêm",JLabel.CENTER);jl[2].setFont(f);jl[2].addMouseListener(this);
		jl[2].setPreferredSize(new Dimension(50,30));jl[2].setBackground(Color.decode("#60A3BC"));jl[2].setOpaque(true);
		jl[3] = new JLabel("Hủy",JLabel.CENTER);jl[3].setFont(f);jl[3].addMouseListener(this);
		jl[3].setPreferredSize(new Dimension(50,30));jl[3].setBackground(Color.decode("#60A3BC"));jl[3].setOpaque(true);
		
		
		jp[4].add(jl[2]);
		jp[4].add(jl[3]);
		
			
		jpc.add(jp1);
		jpc.add(jp[0]);
		jpc.add(jp[1]);
		jpc.add(jp[2]);
		jpc.add(jp[3]);
		jpc.add(jp[4]);
		
		this.add(jpn,BorderLayout.NORTH);
		this.add(jpc,BorderLayout.CENTER);
		
	}

	
	public String getsdt() {
		return tf[1].getText();
	}
	
	
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == jl[2]) {
			 
			if (this.view_quan_li_khach_hang.kiem_tra_rong(tf[0].getText())) {
				JOptionPane.showMessageDialog(this, "Bạn chưa điền tên");
				System.out.println("loi");
			}
			else if (this.view_quan_li_khach_hang.kiem_tra_rong(tf[1].getText())) {
				JOptionPane.showMessageDialog(this, "Bạn chưa điền sdt");
				
				} else {
					if (!this.view_quan_li_khach_hang.kiem_tra_sdt(tf[1].getText()) ||( tf[1].getText().length() != 11   && tf[1].getText().length() != 10  )) {
						JOptionPane.showMessageDialog(this, "Số điện thoại không đúng");
						
					}
					if (this.view_quan_li_khach_hang.kiem_tra_sdt_trung(tf[1].getText())) {
						JOptionPane.showMessageDialog(this, "Số điện thoại đã được sử dụng");				
						}		
				}
			if (this.view_quan_li_khach_hang.kiem_tra_hop_le(tf[0].getText(), tf[1].getText())) {
				model_qlkh h = new model_qlkh(tf[0].getText().trim(), tf[1].getText().trim());
				this.view_quan_li_khach_hang.getBus_qlkh().add(h);
				
				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
					
				tf[0].setText(null);
				tf[1].setText(null);
				this.view_quan_li_khach_hang.refresh_bang_dskh();
												
			}				
		}
		if (e.getSource() == jl[3]) {
			
			this.view_quan_li_khach_hang.return_frame_themkh().setVisible(false);
                        this.view_quan_li_khach_hang.return_null_frame_themkh();
                        
                        
			
			
			
			
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
		if (e.getSource() == jl[2]) {
			jl[2].setBackground(Color.decode("#2980b9"));jl[2].setOpaque(true);
		}
		if (e.getSource()==jl[3]) {
			jl[3].setBackground(Color.decode("#2980b9"));jl[3].setOpaque(true);
		}
		
		
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == jl[2]) {
			jl[2].setBackground(Color.decode("#60A3BC"));jl[2].setOpaque(true);
		}
		if (e.getSource()==jl[3]) {
			jl[3].setBackground(Color.decode("#60A3BC"));jl[3].setOpaque(true);
		}
		
	}
	
	
	
}
