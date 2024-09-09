package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class panel_north_bang_dspn extends JPanel{
	private String[] thuoctinh;
	private JLabel[] jl;
	public panel_north_bang_dspn (int width) {
		thuoctinh = new String[] {"MAPN","MANV","NGÀY NHẬP","TỔNG TIỀN","MANCC"};
		jl = new JLabel[5];
		for (int i = 0; i < 5; i++) {
			jl[i] = new JLabel(thuoctinh[i],JLabel.CENTER);
			
			this.add(jl[i]);
			jl[i].setForeground(Color.decode("#60A3BC"));
		}
		
		this.setLayout(new GridLayout(1,5));
		this.setPreferredSize(new Dimension(width-40,20));
		
		
		
	}
}
