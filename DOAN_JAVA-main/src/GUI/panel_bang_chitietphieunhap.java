package GUI;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DTO.chitietphieunhap_DTO;
import DTO.chitietsanpham_DTO;
import DTO.phieunhap_DTO;

public class panel_bang_chitietphieunhap extends JPanel  {
	private panel_center_chitietphieunhap panel_center_chitietphieunhap;
	private panel_north_chitietphieunhap panel_north_chitietphieunhap;
	public panel_bang_chitietphieunhap(int w,phieunhap_DTO phieunhap_DTO,phieunhap_GUI phieunhap_GUI) {
		this.panel_center_chitietphieunhap = new panel_center_chitietphieunhap(w, phieunhap_DTO, phieunhap_GUI);
		this.panel_north_chitietphieunhap = new panel_north_chitietphieunhap(w);
		
		this.setLayout(new BorderLayout());
		
		JScrollPane sc = new JScrollPane(panel_center_chitietphieunhap, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(panel_north_chitietphieunhap,BorderLayout.NORTH);
		this.add(sc, BorderLayout.CENTER);
		
		
	}
	public void che_do_sua() {
		this.panel_center_chitietphieunhap.che_do_sua();
	}
	
	
	
	///////////////////////////////////////// trả các clickded_sua của các panel chi tiết phiếu nhập con thành true ///////////////////
	public void return_true_clickde_sua() {
		this.panel_center_chitietphieunhap.return_true_clickded_sua();
	}
	
	
	public boolean so_sanh() {
		return this.panel_center_chitietphieunhap.so_sanh();
	}
	
	
	public ArrayList<chitietphieunhap_DTO> ds_ctpn_da_chinh_sua(){
		return this.panel_center_chitietphieunhap.ds_ctpn_da_chinh_sua();
	}
	
	
	public ArrayList<chitietsanpham_DTO> ds_ctsp_da_chinh_sua(){
		return this.panel_center_chitietphieunhap.ds_ctsp_sau_chinhsua();
	}
	
	public String thong_bao_doi_gia() {
		return this.panel_center_chitietphieunhap.thong_bao_thay_gia();
	}
	
	
	
	public double gettongtien() {
		return this.panel_center_chitietphieunhap.update_tongtien();
	}
}
