package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.BoldAction;

import DTO.phieunhap_DTO;

public class panel_center_bang_dspn extends JPanel {
	private ArrayList<phieunhap_DTO> dspn;
	private ArrayList<panel_con_phieunhap> ds_panel_con_phieunhap;
	private phieunhap_GUI phieunhap_GUI;
	public panel_center_bang_dspn(int width,ArrayList<phieunhap_DTO> dspn,phieunhap_GUI phieunhap_GUI) {
		int i = 0;
		this.setPreferredSize(new Dimension(width,30));
		this.dspn = dspn;
		this.ds_panel_con_phieunhap = new ArrayList<panel_con_phieunhap>();
		this.setLayout(new FlowLayout());
		this.phieunhap_GUI = phieunhap_GUI;
		
		
		for (phieunhap_DTO h : dspn) {
			panel_con_phieunhap k = new panel_con_phieunhap(this.getPreferredSize().width-40, h,this.phieunhap_GUI);
			this.ds_panel_con_phieunhap.add(k);
			i++;
			this.add(k);
		}
		this.setPreferredSize(new Dimension(width,i*40));

	}
	public ArrayList<phieunhap_DTO> ds_chon_xoa(){
		ArrayList<phieunhap_DTO> ds = new ArrayList<phieunhap_DTO>();
		for (panel_con_phieunhap h : ds_panel_con_phieunhap) {
			if(h.chon_xoa()) {
				ds.add(h.getpn());
			}
		}
		return ds;
	}
	

	
}
