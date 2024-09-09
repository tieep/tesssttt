package BUS;

import java.util.ArrayList;

import DAO.DAO_chitietphieunhap;
import DTO.chitietphieunhap_DTO;
import DTO.phieunhap_DTO;

public class chitietphieunhap_BUS {
	private phieunhap_DTO h;
	private ArrayList<chitietphieunhap_DTO> ds;
	public chitietphieunhap_BUS(phieunhap_DTO h) {
		this.h = h;
		newlist();
	}
	
	public void newlist() {
		DAO_chitietphieunhap c = new DAO_chitietphieunhap();
		ds = c.selectby_id(h);
		
	}
	
	public void add (chitietphieunhap_DTO h) {
		ds.add(h);
		DAO_chitietphieunhap c = new DAO_chitietphieunhap();
		c.add(h);
	}
        public void delete(){
            DAO_chitietphieunhap c = new DAO_chitietphieunhap();
            
            for(chitietphieunhap_DTO ct : ds)
		c.delete(ct);
        }
	public void set(chitietphieunhap_DTO d) {
		int index = -1;
		for (int i = 0; i < ds.size() ; i++) {
			if (ds.get(i).getMapn().equals(d.getMapn())  && ds.get(i).equals(d.getMasp()) && ds.get(i).equals(d.getMasize()) ) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			ds.set(index, d);
		}
		
		DAO_chitietphieunhap c = new DAO_chitietphieunhap();
		c.set(d);
	}
        public ArrayList<chitietphieunhap_DTO> getList(){
            return ds;
        }
        public static void main(String[] args) {
            System.out.println("BUS.chitietphieunhap_BUS.main()");
    }
}
