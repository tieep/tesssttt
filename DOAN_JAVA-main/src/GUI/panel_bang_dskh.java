package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DTO.model_qlkh;

public class panel_bang_dskh extends JPanel{
	private panel_north_bang_dskh panel_north_bang_dskh;
	private panel_center_bang_dskh panel_center_bang_dskh;
	private JPanel jp,panel_north;
	

	public panel_bang_dskh(int w,int h,ArrayList<model_qlkh> ds){
		
		this.setLayout(new BorderLayout());
		panel_north = new JPanel();
		panel_north_bang_dskh = new panel_north_bang_dskh(w-40);
		panel_center_bang_dskh = new panel_center_bang_dskh(w-40,ds);
		
		jp = new JPanel();
		jp.add(panel_center_bang_dskh);
		
		JScrollPane sc = new JScrollPane(jp,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_north.add(panel_north_bang_dskh);
		
		System.out.println(sc.getPreferredSize().width);
		
		this.add(panel_north, BorderLayout.NORTH);
		this.add(sc,BorderLayout.CENTER);	
	}
	
	
	
	
	
	
	public void panel_bang_dskh_update() {
		this.panel_center_bang_dskh.panel_center_dskh_update();
	}
	
	public Boolean sosanh_update() {
		if (!panel_center_bang_dskh.sosanh_update()) {
			return false;
		}
		return true;
	}
	
	public ArrayList<model_qlkh> ds_kh_update(){
		return panel_center_bang_dskh.ds_kh_update();
	}
	public ArrayList<panel_con_qlkh> ds(){
		return this.panel_center_bang_dskh.ds();
	}
	public void so_sanh() {
		this.panel_center_bang_dskh.so_sanh();
	}
	public void return_true_clicked_xoa() {
		this.panel_center_bang_dskh.return_true_clicked_xoa();
	}
	public void return_false_clicked_xoa() {
		this.panel_center_bang_dskh.return_false_clicked_xoa();
	}
	public ArrayList<model_qlkh> ds_kh_xoa(){
		return this.panel_center_bang_dskh.ds_kh_xoa();
	}
	public boolean xac_nhan() {
		if (this.panel_center_bang_dskh.xac_nhan()) {
			return true;
		}
		return false;
	}
	public void update_ve_che_do_xem() {
		this.panel_center_bang_dskh.update_ve_che_do_xem();
	}
}
