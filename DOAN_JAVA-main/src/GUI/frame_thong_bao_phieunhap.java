package GUI;

import javax.swing.JFrame;

public class frame_thong_bao_phieunhap extends JFrame{
	public frame_thong_bao_phieunhap(String t,phieunhap_GUI phieunhap_GUI ) {
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		thong_bao_phieunhap v = new thong_bao_phieunhap(t,phieunhap_GUI);
                
                        
		this.add(v);
		this.setUndecorated(true);
		this.setVisible(true);
	}
}
