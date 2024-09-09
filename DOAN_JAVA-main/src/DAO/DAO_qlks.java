package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import DTO.model_qlkh;


public class DAO_qlks {
	private ConnectDataBase c;
	
	public static DAO_qlks getinstance() {
		return new DAO_qlks();
	}

	public DAO_qlks() {
		try {
			c = new ConnectDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int add(model_qlkh t) {
		int ketqua = 0;
		try {
			c.connect();
//			Connection conn = jdbc_do_an.getconnection();
//			Statement st = conn.createStatement();
			String sql = "insert into khachhang(TENKH,SDT,DIEMTICHLUY)" +
					"value ('" +t.getTen()+"','" + t.getSdt() + "'," + t.getDiem()+")";
		
//			ketqua = st.executeUpdate(sql);
			c.executeUpdate(sql);
//			jdbc_do_an.close_connec(conn);
			c.disconnect();
			System.out.println("lenh da thuc thi : " + sql );
			
		}catch (SQLIntegrityConstraintViolationException e) {
			
			System.out.println("khach hang nay da ton tai");
			
		} catch (SQLException e) {  
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketqua;
		
	}

	
	public int update(model_qlkh t) {
		int i = 0;
		try {
//			Connection conn = jdbc_do_an.getconnection();
			c.connect();

//			Statement st = conn.createStatement();
			String sql = "update khachhang set TENKH = '" + t.getTen() +"', SDT ='" + t.getSdt() + 
						"', DIEMTICHLUY = " + t.getDiem() + " where MAKH = " + t.getMakh();
			
			c.executeUpdate(sql);
			
			
//			jdbc_do_an.close_connec(conn);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return i;
	}

	
	public int delete(model_qlkh t) {
		int i = 0;
		try {

			c.connect();

			String sql = "delete from khachhang where MAKH = " + t.getMakh();

			c.executeUpdate(sql);
			

			c.disconnect();
			System.out.println("hh");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	
	public ArrayList<model_qlkh> select_all(){
		ArrayList<model_qlkh> danhsach = new ArrayList<model_qlkh>();
		
		
		try {

			c.connect();
			String sql 	= "select * from khachhang";
			
			ResultSet rs = c.executeQuery(sql);
			while (rs.next()) {
				int ma_kh = rs.getInt("MAKH");
				String ten_kh = rs.getString("TENKH");
				String sdt = rs.getString("SDT");
				int diem = rs.getInt("DIEMTICHLUY");
				model_qlkh k = new model_qlkh(ma_kh, ten_kh, sdt, diem);
				danhsach.add(k);
			}

			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return danhsach;
	}
	
	
	
	public model_qlkh select_by_id(int t) {
		model_qlkh h = null ;
		try {
//			Connection conn = jdbc_do_an.getconnection(); 
//			Statement st = conn.createStatement();
			c.connect();
			String sql = "select * from khachhang where MAKH ='" + t +"'";
			ResultSet rs = c.executeQuery(sql);
			
			while(rs.next()) {
				String ten_kh = rs.getString("TENKH");
				String sdt = rs.getString("SDT");
				int dtl = rs.getInt("DIEMTICHLUY");
				 h = new model_qlkh(t, ten_kh, sdt, dtl);
			}
			
//			jdbc_do_an.getconnection().close();
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return h;
	}
	
	
	public ArrayList<model_qlkh> select_by_condision(String cond) {
		
		return null;
	}
	
	public ArrayList<String> select_sdt(){
		ArrayList<String> ds = new ArrayList<String>();
 		try {
//			Connection conn = jdbc_do_an.getconnection();
//			Statement st = conn.createStatement();
 			c.connect();
			String sql = "select sdt from khachhang";
			ResultSet rs = c.executeQuery(sql);
			
			while (rs.next()) {
				String i = rs.getString("SDT");	
				ds.add(i);
			}
			
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ds;
	}
	

	public ArrayList<model_qlkh> search(String ma_kh,String ten,String sdt,int diemmin,int diemmax){
		ArrayList<model_qlkh> h = new ArrayList<model_qlkh>();
	
		
	try {
	 
                
		String sql = "select * from khachhang where TENKH like '%" + ten + "%' and SDT like '%" + sdt +"%' " +
				"and MAKH like '%" + ma_kh +"%' "+ "and DIEMTICHLUY BETWEEN "+ diemmin  +" AND " + diemmax  ;
                        
                
                
                
				
		          System.out.println(sql);
		c.connect();
		
		ResultSet rs = c.executeQuery(sql);
		
		while(rs.next()) {
			int makh = rs.getInt("MAKH");
			String tenkh = rs.getString("TENKH");
			String sdt1 = rs.getString("SDT");
			int dtl = rs.getInt("DIEMTICHLUY");
			model_qlkh k1 = new model_qlkh(makh, tenkh, sdt1, dtl);
			h.add(k1);
		}
//		jdbc_do_an.close_connec(conn);
		c.disconnect();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return h;
	}
        public boolean increaseDTL(int maKH, int dtl) {
		try {
//			Connection conn = jdbc_do_an.getconnection();
			c.connect();

//			Statement st = conn.createStatement();
			String sql = "update khachhang set " + 
						"DIEMTICHLUY = DIEMTICHLUY + " + dtl + " where MAKH = " + maKH;
			
			c.executeUpdate(sql);
			
			
//			jdbc_do_an.close_connec(conn);
			c.disconnect();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	public boolean setDTL(int maKH, int dtl) {
		try {
//			Connection conn = jdbc_do_an.getconnection();
			c.connect();

//			Statement st = conn.createStatement();
			String sql = "update khachhang set " + 
						"DIEMTICHLUY = " + dtl + " where MAKH = " + maKH;
			
			c.executeUpdate(sql);
			
			
//			jdbc_do_an.close_connec(conn);
			c.disconnect();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	
		public ArrayList<String> select_hanhdong_qlkh(String MAQUYEN) {
			ArrayList<String> ds = new ArrayList<String>();
			
			try {
				c.connect();
				
				String sql = "SELECT HANHDONG FROM chitietquyen WHERE MACHUCNANG = 'KH' AND  MAQUYEN ='" + MAQUYEN +"'";
				
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

		    public int getDiemTichLuy(String maKH) {
        // Kết nối cơ sở dữ liệu (cần thay đổi thông tin kết nối dựa trên cấu hình của bạn)
        try {
			c.connect();
            String sql = "SELECT DIEMTICHLUY FROM KhachHang WHERE MaKH =" + maKH;
            // Đặt giá trị cho tham số maKH
            // Thực thi truy vấn và nhận kết quả trả về
            ResultSet rs = c.executeQuery(sql);
            // Nếu có kết quả trả về, lấy giá trị DIEMTICHLUY và trả về
            if (rs.next()) {
                return rs.getInt("DIEMTICHLUY");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Trả về giá trị mặc định nếu không lấy được từ cơ sở dữ liệu
        return 0;
    }

	
}
