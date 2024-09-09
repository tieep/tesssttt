package DAO;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.SanPhamDTO;

import DTO.chitietsanpham_DTO;
import DTO.SanPhamDTO;

public class DAO_chitietsanpham {
	private static ConnectDataBase mySQL;
	private SanPhamDTO sanpham_DTO;
	public DAO_chitietsanpham() throws SQLException {	
            mySQL = new ConnectDataBase(); // TODO Auto-generated catch block
	}
        
	private static void ConnectDataBase() throws SQLException {
            mySQL = new ConnectDataBase();
    }

	public static ArrayList<String> select_size(String maSP){
		ArrayList<String> k = new ArrayList<String>();
		try {
			ConnectDataBase();
			mySQL.connect();
			
			String sql = "select MASIZE from size where MASIZE in ("
					+ "SELECT DISTINCT MASIZE FROM chitietsanpham WHERE MASP = '"+ maSP +"' )";
			
			ResultSet rs = mySQL.executeQuery(sql);
			while(rs.next()) {
				String t = rs.getString("MASIZE");
				k.add(t);
				
			}
			mySQL.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public ArrayList<chitietsanpham_DTO> select_all(){
		ArrayList<chitietsanpham_DTO> ds = new ArrayList<chitietsanpham_DTO>();
		
		try {
			mySQL.connect();
			
			String sql = "select * from chitietsanpham ";
			
			ResultSet rs = mySQL.executeQuery(sql);
			
			while (rs.next()) {
				
				String masp = rs.getString("MASP");
				String masize = rs.getString("MASIZE");
				int solong = rs.getInt("SOLUONG");
				
				chitietsanpham_DTO k = new chitietsanpham_DTO(masp, masize, solong);
				ds.add(k);
			}
			mySQL.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public void add(chitietsanpham_DTO d) throws SQLException {
            mySQL.connect(); // TODO Auto-generated catch block
            String sql = "INSERT INTO chitietsanpham (MASP,MASIZE,SOLUONG) "
                    + "VALUES ('" +d.getMASP() + "','" + d.getMASIZE() + "',"+d.getSoluong()+")";
            mySQL.executeUpdate(sql);
            mySQL.disconnect();
            System.out.println(sql);
	}
	
	public chitietsanpham_DTO search(String MASP,String MASIZE) {
		chitietsanpham_DTO h = null;
		try {
			mySQL.connect();
			String sql = "SELECT * FROM chitietsanpham WHERE MASP = '" + MASP + "' and MASIZE = '" + MASIZE +"'";
			
			ResultSet rs = mySQL.executeQuery(sql);
			
			while (rs.next()) {
				h = new chitietsanpham_DTO(MASP, MASIZE, rs.getInt("SOLUONG"));
			}
			mySQL.disconnect();
			
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return h ;
		
	}
        	public void updateAfterTT(chitietsanpham_DTO d) throws SQLException {
                    mySQL.connect(); // TODO Auto-generated catch block
                    String sql = "update chitietsanpham set SOLUONG = " + d.getSoluong() +" where MASP = '" + d.getMASP()  + "' and MASIZE = '" + d.getMASIZE() +"'";
                    mySQL.executeUpdate(sql);
                    mySQL.disconnect();
	}
	
	public void update(chitietsanpham_DTO d) throws SQLException {
            mySQL.connect(); // TODO Auto-generated catch block
            String sql = "update chitietsanpham set SOLUONG = SOLUONG + " + d.getSoluong() +" where MASP = '" + d.getMASP()  + "' and MASIZE = '" + d.getMASIZE() +"'";
            mySQL.executeUpdate(sql);
            mySQL.disconnect();
	}
        
        public void Restore_pro (chitietsanpham_DTO cp) throws SQLException{
        mySQL.connect();
        String query= "UPDATE chitietsanpham set SOLUONG = '" + cp.getSoluong() + "' WHERE MASP='" + cp.getMASP() + "' AND MASIZE='" + cp.getMASIZE()+"';";
        boolean result = mySQL.executeupdate(query);
        if(result) {
            System.out.println("Phục hồi số lượng sản phẩm sau hủy hóa đơn thành công!");
        } else {
            System.out.println("Phục hồi số lượng sản phẩm sau hủy hóa đơn thất bại!");
        }
        mySQL.disconnect();    
        }
        
         public void Restore_pro (int sl, String id, String idsize) throws SQLException{
            System.out.println(id + "--"+ idsize + "--" + sl);
        mySQL.connect();
        String query= "UPDATE chitietsanpham set SOLUONG = '" + sl + "' WHERE MASP='" + id + "' AND MASIZE='" + idsize+"';";
        boolean result = mySQL.executeupdate(query);
        if(result) {
            System.out.println("Phục hồi số lượng sản phẩm sau sua hóa đơn thành công!");
        } else {
            System.out.println("Phục hồi số lượng sản phẩm sau sua hóa đơn thất bại!");
        }
        mySQL.disconnect();    
        }
         
        public String Get_Masize(String tensize){
            String idsize = "";
            try {
			mySQL.connect();
			String sql = "select size.MASIZE from size where size.TENSIZE = '" + tensize + "';";
			ResultSet rs = mySQL.executeQuery(sql);
			while (rs.next()) {
				idsize = rs.getString("MASIZE");
			}
			mySQL.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            return idsize;
        }
	public String[] get_AllSIZE(String id) {
            ArrayList<String> dataSize = new ArrayList<>();
        try {
            mySQL.connect();
            String sql = "select size.TENSIZE from chitietsanpham,size where chitietsanpham.MASP='" + id
                    + "' and chitietsanpham.MASIZE=size.MASIZE and size.TRANGTHAI=1;";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String value = rs.getString("TENSIZE");
                dataSize.add(value);
            }
            mySQL.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Chuyển danh sách thành mảng
                String[] dataArray = dataSize.toArray(new String[dataSize.size()]);
        return dataArray;
    }
    
    public int get_sl(String id, String size){
        int value = 0;
         try {
            mySQL.connect();
            String sql = "select chitietsanpham.SOLUONG from chitietsanpham, size WHERE chitietsanpham.MASP='"+ id +"' and chitietsanpham.MASIZE=size.MASIZE and size.TENSIZE='"+ size+"';";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                value = rs.getInt("SOLUONG");
            }
            mySQL.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }
	 public static void main(String[] args) throws SQLException {
	 	SanPhamDTO m = new SanPhamDTO("SP8", null,null, 0, args, 0);
	 	DAO_chitietsanpham c = new DAO_chitietsanpham();
//                c.get_sl("SP4", "onesize");
		}
//	// 	System.out.println(mySQL.select_all().toString());
		
		
		
	// }
	
}
