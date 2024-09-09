package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class frame_thong_bao_update extends JFrame{
	private view_thong_bao view_thong_bao;
	private view_quan_li_khach_hang view_quan_li_khach_hang;
	public frame_thong_bao_update(int w,int h,String t,view_quan_li_khach_hang view_quan_li_khach_hang) {
		this.view_thong_bao = new view_thong_bao(w, h,t,view_quan_li_khach_hang);
		this.view_quan_li_khach_hang = view_quan_li_khach_hang;
		this.setSize(w,h);
		
		
		this.add(view_thong_bao);
		
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.setSize(w,h);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
		
	}
	
	
}
