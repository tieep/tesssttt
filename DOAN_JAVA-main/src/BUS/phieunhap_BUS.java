package BUS;

import java.util.ArrayList;

import DAO.ConnectDataBase;
import DAO.DAO_phieunhap;
import DTO.phieunhap_DTO;

public class phieunhap_BUS {
	private ArrayList<phieunhap_DTO> dspn;
	public phieunhap_BUS() {
		newlist();
	}
	
	public void newlist() {
		DAO_phieunhap c = new DAO_phieunhap();
		this.dspn = c.select_all();
	}
	
	
	public void add(phieunhap_DTO h) {
		DAO_phieunhap c = new DAO_phieunhap();
		c.add(h);
	}
	
	
	public void delete(phieunhap_DTO h) {
		DAO_phieunhap c = new DAO_phieunhap();
		c.delete(h);
	}
	
	
	public void update(phieunhap_DTO h) {
		DAO_phieunhap c = new DAO_phieunhap();
		c.update(h);
	}
	
	
	public int stt_max() {
		DAO_phieunhap c = new DAO_phieunhap();
		return c.select_max();
	}
	
	
	public phieunhap_DTO select_by_id(String t ) {
		DAO_phieunhap c = new DAO_phieunhap();
		return c.select_byid(t);
		
	}
	public ArrayList<phieunhap_DTO> dsPN(){
		return this.dspn;
	}
        
        public ArrayList<String> hanhdong_phieunhap(String MAQUYEN){
           
            DAO_phieunhap c = new DAO_phieunhap();
           return c.select_hanhdong_phieunhap(MAQUYEN);
        }
	public ArrayList<phieunhap_DTO> search(String MAPN,String MANV,String ngaytruoc, String ngaysau, double giabe, double gialon , String mancc){
		DAO_phieunhap c = new DAO_phieunhap();
		return c.search(MAPN, MANV, ngaytruoc, ngaysau, giabe, gialon, mancc);
	}
	
}
