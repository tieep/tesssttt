package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import DAO.DAO_qlks;
import DTO.model_qlkh;



public class panel_center_bang_dskh extends JPanel {
	private panel_con_qlkh con_qlkh;
	private  ArrayList<model_qlkh> ds_kh_update, ds_kh_xoa ;
	private ArrayList<panel_con_qlkh> dspanel_con_qlkh;
	private panel_bang_dskh panel_bang_dskh;
	private view_quan_li_khach_hang view_quan_li_khach_hang;
	private boolean clicked_xoa;
	

	// khởi tạo panel center và truyền vào nó 1 mảng danh sách 
	public panel_center_bang_dskh(int w, ArrayList<model_qlkh> ds) {
		
			clicked_xoa = false;
			this.dspanel_con_qlkh = new ArrayList<panel_con_qlkh>();
			this.ds_kh_update = new ArrayList<model_qlkh>();
			
			this.setLayout(new FlowLayout());
			this.setPreferredSize(new Dimension(w,30));
			int i = 0;
			for (model_qlkh h1 : ds ) {
				con_qlkh = new panel_con_qlkh(this,h1);
				this.dspanel_con_qlkh.add(con_qlkh);
				this.add(con_qlkh);
				
				i++;	
			}
			this.setPreferredSize(new Dimension(w,35*i));
		}
	// chuyển thành chế độ update 
	public void panel_center_dskh_update() {
		for(panel_con_qlkh h : dspanel_con_qlkh) {
			h.panel_con_dskh_che_do_update();
		}
	}
// kiểm tra tất cả cá khách hàng có thay đổi gì so với thông tin cũ không 
	public Boolean sosanh_update() {
		
		for (panel_con_qlkh h : dspanel_con_qlkh) {
			
			if (!h.sosanh()) {
				ds_kh_update.add(h.get_kh_update());
				return false;
			}
		}
		return true;
	}	
	public void bang_ds_search(ArrayList<model_qlkh> ds) {
		
	}
	
	public ArrayList<panel_con_qlkh> ds(){
		return this.dspanel_con_qlkh;
	}
	public void so_sanh() {
		this.ds_kh_update = new ArrayList<model_qlkh>();
		for (panel_con_qlkh h : dspanel_con_qlkh) {
			if(!h.sosanh()) {
				ds_kh_update.add(h.get_kh_update());
			}	
		}
	}
	public ArrayList<model_qlkh> ds_kh_update(){
		this.so_sanh();
		return this.ds_kh_update;
	}
	public void return_true_clicked_xoa() {
		clicked_xoa = true;
	}
	public void return_false_clicked_xoa() {
		clicked_xoa = false;
	}
	public boolean clicked__xoa() {
		return this.clicked_xoa;
	}
	
	
	
	public ArrayList<model_qlkh> ds_kh_xoa(){
		this.ds_kh_xoa = new ArrayList<model_qlkh>();
		for (panel_con_qlkh h : dspanel_con_qlkh ) {
			if (h.khach_hang_xoa()) {
				this.ds_kh_xoa.add(h.get_kh());
			}
		}
		return this.ds_kh_xoa;
	}
	public boolean xac_nhan() {
		for (panel_con_qlkh h : dspanel_con_qlkh ) {
			if (h.khach_hang_xoa()) {
				return true;
			}
		}
		return false;
	}
	public void update_ve_che_do_xem() {
		for(panel_con_qlkh h : this.dspanel_con_qlkh) {
			h.update_ve_che_do_xem();
		}
	}
}
