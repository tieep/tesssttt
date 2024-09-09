package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;	

import DTO.phieunhap_DTO;

public class panel_bang_dspn1 extends JPanel {
	private panel_north_bang_dspn panel_north_bang_dspn;
	panel_center_bang_dspn panel_center_bang_dspn;
	private phieunhap_GUI g;
	
	public panel_bang_dspn1(int w,ArrayList<phieunhap_DTO> d, phieunhap_GUI g ) {
		this.setLayout(new BorderLayout());
		
		this.g = g;
		panel_north_bang_dspn panel_north_bang_dspn = new panel_north_bang_dspn(w-40);
		
		JPanel p1 = new JPanel();
		p1.add(panel_north_bang_dspn);
		
		this.add(p1,BorderLayout.NORTH);
		
		
		 panel_center_bang_dspn = new panel_center_bang_dspn(w-40, d, g);
		
		
		
		
		JScrollPane scc = new JScrollPane(panel_center_bang_dspn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		scc.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(scc, BorderLayout.CENTER);
//		this.setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
                this.setBackground(Color.decode("#60A3BC"));
	}
	public ArrayList<phieunhap_DTO> ds_chon_xoa(){
		return this.panel_center_bang_dspn.ds_chon_xoa();
	}
	
}
