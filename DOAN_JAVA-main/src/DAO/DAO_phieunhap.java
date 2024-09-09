package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DTO.phieunhap_DTO;

public class DAO_phieunhap {
	private ConnectDataBase c;
	public DAO_phieunhap() {
		try {
			c = new ConnectDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<phieunhap_DTO> select_all(){
		ArrayList<phieunhap_DTO> ds = new ArrayList<phieunhap_DTO>();
		try {
			c.connect();
			String sql = "select * from phieunhap";
			ResultSet rs = c.executeQuery(sql);
			while (rs.next()) {
				String MAPN = rs.getString("MAPN");
				String MANV = rs.getString("MANV");
				LocalDate ngay = rs.getDate("NGAYNHAP").toLocalDate();
				double tontien = rs.getDouble("TONGTIEN");
				String MANCC = rs.getString("MANCC");
				phieunhap_DTO h = new phieunhap_DTO(MAPN, MANV, ngay, tontien, MANCC);
				ds.add(h);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public void add(phieunhap_DTO h) {
		try {
			c = new ConnectDataBase();
			c.connect();
			String sql = "insert into phieunhap (MAPN,MANV,NGAYNHAP,TONGTIEN,MANCC) " + 
					"values ('"+h.getMAPN()+"','" +h.getMANV()+"','"+h.getNgay()+"'," +h.getTongtien() + 
					",'"+h.getMANCC()+"')";
			 c.executeUpdate(sql);
			System.out.println("lenh da thuc thi " + sql);
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(phieunhap_DTO h) {
		try {
			c = new ConnectDataBase();
			c.connect();
			
			String sql = "delete from phieunhap where MAPN = '" + h.getMAPN()+"'";
			
			c.executeUpdate(sql);
			
			c.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void update (phieunhap_DTO h ) {
		try {
			c = new ConnectDataBase();
			c.connect();
			String sql = "update phieunhap set MANV = '" + h.getMANV() + "',NGAYNHAP = '" + h.getNgay() + "', TONGTIEN = "  
					+ h.getTongtien() +",MANCC = '" + h.getMANCC() + "'" +
					"where MAPN = '" + h.getMAPN() + "'" ;
			c.executeUpdate(sql);
			c.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	public int select_max() {
		int i = 0;
		try {
			c.connect();
			String sql = "SELECT MAX(DEM) AS maxx FROM phieunhap";
			
			ResultSet rs = c.executeQuery(sql);
			
			 if (rs.next()) {
				 i = rs.getInt("maxx");
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	
	 	public phieunhap_DTO select_byid(String t) {
	 		phieunhap_DTO h = null;
	 		try {
	 			
				c.connect();
				String sql = "select * from phieunhap where MAPN ='" + t +"'";
				
				ResultSet rs = c.executeQuery(sql);
				while(rs.next()) {
					String MAPN = rs.getString("MAPN");
					String MANV = rs.getString("MANV");
					LocalDate ngay = rs.getDate("NGAYNHAP").toLocalDate();
					double tongtien = rs.getDouble("TONGTIEN");
					String MANCC = rs.getString("MANCC");
					h = new phieunhap_DTO(MAPN, MANV, tongtien, MANCC);
					
				}
				
				c.disconnect();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		return h;
	 	}
	 	public ArrayList<phieunhap_DTO> search(String MAPN , String MANV ,String ngaytruoc,String ngaysau,double giabe, double gialon,String MANCC) {     
	 		ArrayList<phieunhap_DTO> ds = new ArrayList<phieunhap_DTO>();
	 		
	 		
	 		
	 		
	 		
	 		try {
				c.connect();
				
				String sql = "	SELECT * FROM phieunhap"
						+ "	 WHERE MAPN LIKE '%"+ MAPN +"%'"
						+ "	 AND MANV LIKE '%" +  MANV +"%'"
						+ "	 AND NGAYNHAP BETWEEN '"+ ngaytruoc +"' AND '"+ngaysau +  "' "
						+ "	 AND TONGTIEN BETWEEN "+ giabe  +" AND " + gialon  +" AND "
						+ "	 MANCC LIKE '%"+ MANCC +"%'"
						
						;
				System.out.println(sql);
				ResultSet rs = c.executeQuery(sql);
		
				while (rs.next()) {
					String mapn = rs.getString("MAPN");
					String manv = rs.getString("MANV");
					LocalDate ngay = rs.getDate("NGAYNHAP").toLocalDate();
					double tongtien = rs.getDouble("TONGTIEN");
					String mancc = rs.getString("MANCC");
					phieunhap_DTO m = new phieunhap_DTO(mapn, manv, ngay, tongtien, mancc);
					ds.add(m);
					
				}
				c.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		return ds;
	 	}
                
                public ArrayList<String> select_hanhdong_phieunhap(String MAQUYEN) {
			ArrayList<String> ds = new ArrayList<String>();
			
			try {
				c.connect();
				
				String sql = "SELECT HANHDONG FROM chitietquyen WHERE MACHUCNANG = 'PN' AND  MAQUYEN ='" + MAQUYEN +"'";
				
			 ResultSet rs = c.executeQuery(sql);
			 
			 while(rs.next()) {	
				 String t = rs.getString("HANHDONG");
				 ds.add(t);
			 }
			 c.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return ds;
		}
	
	public static void main(String[] args) {
		DAO_phieunhap h = new DAO_phieunhap();
		phieunhap_DTO m = new phieunhap_DTO("pn008", "nc0010", 14,"");
		ArrayList<phieunhap_DTO> ds = h.search("a-z","a-z", 	"2024-05-05", "2024-05-07", 500, 700,"" );
		for (phieunhap_DTO j : ds) {
			System.out.println(j.toString());
		}
		
	}
	
}
