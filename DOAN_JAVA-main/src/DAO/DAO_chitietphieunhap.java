package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.chitietphieunhap_DTO;
import DTO.phieunhap_DTO;


public class DAO_chitietphieunhap {
	private ConnectDataBase c;
	public DAO_chitietphieunhap() {
		try {
			c = new ConnectDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<chitietphieunhap_DTO> selectby_id(phieunhap_DTO h){
		ArrayList<chitietphieunhap_DTO> ds = new ArrayList<chitietphieunhap_DTO>();
		try {
			
			c.connect();
			String sql = "select * from chitietphieunhap where MAPN = '" + h.getMAPN() +"'";
			
			ResultSet rs = c.executeQuery(sql);
			while (rs.next()) {
				String mapn = rs.getString("MAPN");
				String masp = rs.getString("MASP");
				int soluong = rs.getInt("SOLUONG");
				double gianhap = rs.getDouble("GIANHAP");
				double thanhtien = rs.getDouble("THANHTIEN");
				String masize = rs.getString("MASIZE");
				
				chitietphieunhap_DTO d = new chitietphieunhap_DTO(mapn, masp, soluong, gianhap, thanhtien, masize);
				ds.add(d);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
		
	}
	
	public void add(chitietphieunhap_DTO h ) {
		try {
			c.connect();
			
			String sql = "INSERT INTO chitietphieunhap (MAPN,MASP,SOLUONG,GIANHAP,THANHTIEN,MASIZE) " +
					"values ('" + h.getMapn() +"','" + h.getMasp() +"'," + h.getSoluong() +"," + h.getGianhap()
					+ "," + h.getThanhtien() + ",'" + h.getMasize() + "')";
			
			c.executeUpdate(sql);
			
			System.out.println("lenh da thuc hien " + sql);
			
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void set(chitietphieunhap_DTO d) {
		try {
			c.connect();
			
			
			String sql = "UPDATE chitietphieunhap \r\n"
					+ "SET SOLUONG  = " +d.getSoluong() +"  ,GIANHAP =" +d.getGianhap()+ ",THANHTIEN  = SOLUONG * GIANHAP "        
					+ " WHERE MAPN = '"+d.getMapn()+ "'  AND  MASP = '" + d.getMasp() +"'  AND  MASIZE = '" + d.getMasize()+"'";
					
			c.executeUpdate(sql);
			
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void delete(chitietphieunhap_DTO h){
    try {
			c.connect();
			
			
			String sql = "DELETE FROM chitietphieunhap WHERE MAPN = '"+h.getMapn()+"' and MASP = '"+h.getMasp()+"' and MASIZE = '"+h.getMasize()+"'";
					
					
			c.executeUpdate(sql);
			
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	
	public static void main(String[] args) {
		phieunhap_DTO m = new phieunhap_DTO("PN006", "", 0, "");
		DAO_chitietphieunhap c = new DAO_chitietphieunhap();
		for (chitietphieunhap_DTO h : c.selectby_id(m)) {
			System.out.println(h.toString()); 
		}
		
	}
}
