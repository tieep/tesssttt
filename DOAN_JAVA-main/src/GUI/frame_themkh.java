package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class frame_themkh extends JFrame{
	private view_them_kh view_them_kh;
	private JPanel p;
	private view_quan_li_khach_hang view_quan_li_khach_hang;
	
	public frame_themkh(view_quan_li_khach_hang view_quan_li_khach_hang) {
		this.view_quan_li_khach_hang = view_quan_li_khach_hang;
		this.init();
		this.setVisible(true);
                
	}
	public void init() {
		view_them_kh = new view_them_kh(300, 300,view_quan_li_khach_hang);
		this.setLayout(new FlowLayout());
		this.setSize(new Dimension(view_them_kh.getPreferredSize().width,view_them_kh.getPreferredSize().height));
		this.add(view_them_kh);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
