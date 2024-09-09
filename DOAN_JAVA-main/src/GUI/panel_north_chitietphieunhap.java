package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class panel_north_chitietphieunhap extends JPanel{
	private JLabel jl[];
	
	public panel_north_chitietphieunhap(int w) {
		String[] t = {"MASP","Số lượng","Mã size" ,"Giá nhập","Thành tiền"}; 
		jl = new JLabel[5];
		
		
		for (int i = 0; i<5;i++) {
			jl[i] = new JLabel(t[i],JLabel.CENTER);
			jl[i].setForeground(Color.decode("#60A3BC"));
			this.add(jl[i]);
			
		}
		
		
		
		this.setPreferredSize(new Dimension(w-100,30));
		
		this.setLayout(new GridLayout(1,5));
	}
}
