package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

public class SanPhamBUS {

    private ArrayList<SanPhamDTO> dsSP;

    public SanPhamBUS() {
        list();
    }

    public ArrayList<SanPhamDTO> getDsSP() {
        return dsSP;
    }

    public void setDsSP(ArrayList<SanPhamDTO> dsSP) {
        this.dsSP = dsSP;
    }
    
    public void list() {
        SanPhamDAO spDAO = new SanPhamDAO();
        dsSP = spDAO.list();
    }
    
    public void add(SanPhamDTO sp){
        dsSP.add(sp);
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.add(sp);
    }
    
    public void set(SanPhamDTO sp){
        for(int i=0; i<dsSP.size(); i++){
            if(dsSP.get(i).getMaSP().equalsIgnoreCase(sp.getMaSP())){
                dsSP.set(i, sp);
                SanPhamDAO spDAO = new SanPhamDAO();
                spDAO.set(sp);
            }
        }
    }
    
    public void delete(String maSP){
        for(int i =0; i<dsSP.size(); i++){
            if(dsSP.get(i).getMaSP().equalsIgnoreCase(maSP)){
                dsSP.remove(i);
                SanPhamDAO spDAO = new SanPhamDAO();
                spDAO.delete(maSP);
                return;
            }
        }
    }
    
    public SanPhamDTO select_by_id(String MASP) {
    	SanPhamDTO sp = new SanPhamDTO();
    	for (SanPhamDTO h : dsSP) {
    		if (h.getMaSP().equals(MASP)) {
    			sp = h;
    		}
    	}
    	return sp;
    }
//     public static void main(String[] args) {
//         SanPhamBUS n = new SanPhamBUS();
//         String t[] = {"b.jpg", "p.jpg", "o.jpg"};
//         SanPhamDTO m = new SanPhamDTO("maSP2", "maloai01", "name01", 12450, t, 1);
       
// //        n.add(m);
//         n.delete("maSP2");
//          System.out.println(n.dsSP.size());
        
//     }
}
