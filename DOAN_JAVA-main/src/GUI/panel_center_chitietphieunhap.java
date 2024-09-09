package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import BUS.SanPhamBUS;
import DAO.DAO_chitietphieunhap;
import DTO.SanPhamDTO;
import DTO.chitietphieunhap_DTO;
import DTO.chitietsanpham_DTO;
import DTO.phieunhap_DTO;

public class panel_center_chitietphieunhap extends JPanel  implements MouseListener{
	private phieunhap_DTO phieunhap_DTO;
	private ArrayList<chitietphieunhap_DTO> ds;
	private double tongtien;
	private phieunhap_GUI phieunhap_GUI;
	private ArrayList<panel_con_chitietphieunhap> ds_panel_con_ctpn,ds_panel_con_sua;
	private chitietphieunhap_GUI chitietphieunhap_GUI;
	private SanPhamBUS sanPhamBUS;
	
	
	public panel_center_chitietphieunhap(int w,phieunhap_DTO phieunhap_DTO,phieunhap_GUI phieunhap_GUI) {
		this.ds_panel_con_ctpn = new ArrayList<panel_con_chitietphieunhap>();
		this.phieunhap_DTO = phieunhap_DTO;
		this.sanPhamBUS = new SanPhamBUS();
		
		DAO_chitietphieunhap c = new DAO_chitietphieunhap();
		this.ds = c.selectby_id(this.phieunhap_DTO);
		 tongtien = 0;
		int i = 1;
		
		for (chitietphieunhap_DTO h : ds) {
			panel_con_chitietphieunhap g = new panel_con_chitietphieunhap(w, h,phieunhap_GUI);
			this.ds_panel_con_ctpn.add(g);
			this.add(g);
			tongtien += h.getThanhtien();
			i++;
		}
		this.setLayout(new FlowLayout(1,0,10));
		this.setPreferredSize(new Dimension(w,40*i));
		System.out.println(this.tongtien);
		
		
		
		this.addMouseListener(this);
		
	}
	public double tongtien() {
		double i = 0;
		for (chitietphieunhap_DTO h : ds) {
			i += h.getThanhtien();
		}
		return i;
	}
	
	
	public double update_tongtien() {
		tongtien = 0;
		for (panel_con_chitietphieunhap h : ds_panel_con_ctpn) {
			this.tongtien += h.tong_tien();
		}
		return this.tongtien;
	}
	
	public void che_do_sua() {
		for (panel_con_chitietphieunhap h : ds_panel_con_ctpn) {
			h.che_do_sua();
		}
	}
	
	public void return_true_clickded_sua() {
		for (panel_con_chitietphieunhap h : ds_panel_con_ctpn) {
			h.return_true_clicked_sua();
		}
	}
	
	public boolean so_sanh() {
		for (panel_con_chitietphieunhap h : this.ds_panel_con_ctpn) {
			if (!h.so_sanh()) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<chitietphieunhap_DTO> ds_ctpn_da_chinh_sua(){
		ArrayList<chitietphieunhap_DTO> ds = new ArrayList<chitietphieunhap_DTO>();
		for (panel_con_chitietphieunhap h :  ds_panel_con_ctpn) {
			if (!h.so_sanh()) {
				ds.add(h.chitietphieunhap_da_sua());
			}
		}
		return ds;
	}
	
	public ArrayList<chitietsanpham_DTO> ds_ctsp_sau_chinhsua(){
		ArrayList<chitietsanpham_DTO> ds = new ArrayList<chitietsanpham_DTO>();
		for( panel_con_chitietphieunhap h : ds_panel_con_ctpn) {
			
			ds.add(h.ctsp_da_sua());
		}
		return ds;
	}
	
	public String thong_bao_thay_gia() {
		String t = "Sản phẩm";
		ArrayList<SanPhamDTO> ds = new ArrayList<SanPhamDTO>();
		for (panel_con_chitietphieunhap h : ds_panel_con_ctpn) {
			if (!h.so_sanh_gia_sanpham()) {
				ds.add(h.sanpham_da_chinhsua());
				t += " "+h.sanpham_da_chinhsua().getMaSP() + " ";
			}
		}
		t += "đã có sự thay đổi về giá vui lòng cập nhật lại ở trang sản phẩm";
		return t;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		String t = this.thong_bao_thay_gia();
		if (t.contains("SP")) {
			System.out.println(t);
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
 
	


	
}
