package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.ConnectDataBase;
import DAO.DAO_qlks;
import DAO.Hoadon_DAO;
import DTO.model_qlkh;

public class BUS_qlkh {
	private ArrayList<model_qlkh> dskh;
	public BUS_qlkh() {
		newlist();
	} 
	
	
	
	public void newlist() {
		DAO_qlks DAO_kh = new DAO_qlks();
		dskh = DAO_kh.select_all();
		
		
	}
	public void add(model_qlkh kh) {
		dskh.add(kh);
		DAO_qlks DAO_kh = new DAO_qlks();
		DAO_kh.add(kh);
	}
	public void delete(model_qlkh kh) {
		
		for (model_qlkh k : dskh) {
			if (k.getMakh() == kh.getMakh()) {
				dskh.remove(kh);
				DAO_qlks d = new DAO_qlks();
				d.delete(kh);
				return;
			}
		}
	}
	public void set(model_qlkh k) {
		for (int i = 0; i < dskh.size() ; i++) {
			if (dskh.get(i).getMakh() == k.getMakh()) {
				dskh.set(i, k);
				DAO_qlks d = new DAO_qlks();
				d.update(k);
				return;
			}
		}
	}
	public void search(String ma, String ten,String sdt,int diemmin,int diemmax) {
		DAO_qlks d = new DAO_qlks();
		dskh = d.search(ma, ten, sdt, diemmin,diemmax);
		
	}
	public ArrayList<model_qlkh> getlist(){
		return this.dskh;
	}
	
	public void refresh_list() {
		DAO_qlks d = new DAO_qlks();
		this.dskh = d.select_all();
	}
	
	public model_qlkh selecby_id(int mkh) {
		model_qlkh kh = null;
		for (model_qlkh h : dskh) {
			if (h.getMakh() == mkh) {
				kh = h; 
			}
		}
		return kh;
	}
	public int makh() {
		int max = 0;
		for (model_qlkh h : dskh) {
			if (max < h.getMakh()) {
				max = h.getMakh();
			}
		}
		return max+1;
	}
	
	public ArrayList<String> select_hanhdong_qlkh(String MAQUYEN){
		DAO_qlks c = new DAO_qlks();
		return c.select_hanhdong_qlkh(MAQUYEN);
	}
	public boolean increaseDTL(int maKH, int dtl) {
		DAO_qlks dao_qlks = new DAO_qlks();
		return dao_qlks.increaseDTL(maKH, dtl);
	}

	public boolean setDTL(int maKH, int dtl) {
		DAO_qlks dao_qlks = new DAO_qlks();
		return dao_qlks.setDTL(maKH, dtl);
	}
    public int getDiemTichLuy(String maKH) {
        int diemTL = 0;
        try {
            ConnectDataBase mySQL = new ConnectDataBase();
            mySQL.connect();
            String query = "SELECT DIEMTICHLUY FROM khachhang WHERE MAKH ="+ maKH;
            ResultSet resultSet = mySQL.executeQuery(query);

            if (resultSet.next()) {
                diemTL = resultSet.getInt("DIEMTICHLUY");
            }
          
            mySQL.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Hoadon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diemTL ;
    }
	
	public static void main(String[] args) {
		BUS_qlkh h = new BUS_qlkh();
		for (model_qlkh k : h.getlist()) {
			System.out.println(k.toString());
		}
	}
}
