package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class panel_north_bang_dskh extends JPanel{
	private JLabel[] jl1;
	private String[] thuoctinh;
	public panel_north_bang_dskh(int w) {
		this.setPreferredSize(new Dimension(w,30));
		thuoctinh = new String[] {"MAKH","Tên khách hàng", "Số điện thoại", "Điểm tích lũy"};
		Font f1 = new Font(TOOL_TIP_TEXT_KEY, Font.BOLD, 14);
		
		jl1 = new JLabel[4];
		for (int i = 0; i < 4; i++) {
			jl1[i] = new JLabel(thuoctinh[i],JLabel.CENTER);
			jl1[i].setFont(f1);
			jl1[i].setForeground(Color.decode("#60A3BC"));
			this.add(jl1[i]);
			this.setLayout(new GridLayout(1,4));
		}
		
		
	}
	
}
