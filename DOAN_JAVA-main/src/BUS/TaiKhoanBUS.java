package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TaiKhoanBUS {
    private ArrayList<TaiKhoanDTO> dsTK;

    public TaiKhoanBUS() {
        list();
    }
    
    public void list(){
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        dsTK = new ArrayList<>();
        dsTK = tkDAO.list();
    }
    
    public boolean checkUSERNAME(String t) {
        //Username không chứa số và các kí tự đặc biệt
        String regex = "^[\\p{L}0-9]+$";
        return t.matches(regex);
    }
    
     public boolean checkPASSWORD(String t) {
        //password co the chua chu so, chu cai hoa va thuong, ki tu !, ki tu @
        String regex = "[a-zA-Z0-9!@]+$";
        return t.matches(regex);
    }
     public TaiKhoanDTO searchTaikhoanDTO(String USERNAME, String PASSWORD){
         for(TaiKhoanDTO t : dsTK){
             if(t.getUsername().equals(USERNAME) && t.getPassword().equals(PASSWORD))
                 return t;
         }
         return null;
     }
    
    public void add(TaiKhoanDTO tk){
        dsTK.add(tk);
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        tkDAO.add(tk);  
    }
    
    public void delete (String maNV){
        for(TaiKhoanDTO t : dsTK){
            if(t.getMaNV().equalsIgnoreCase(maNV)){
                dsTK.remove(t);
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();
                tkDAO.delete(maNV);
                return;
            }
        }
    }
    
    public void set(TaiKhoanDTO t){
        for(int i=0; i<dsTK.size(); i++){
            if(dsTK.get(i).getMaNV().equalsIgnoreCase(t.getMaNV())){
                dsTK.set(i, t);
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();
                tkDAO.set(t);
            }
        }
    }

    public ArrayList<TaiKhoanDTO> getDsTK() {
        return dsTK;
    }
    
    //on thi mơi them
    public ArrayList<TaiKhoanDTO> search(String state){
        int statenew=0;
        if(state.equals("Đang hoạt động"))
            statenew=1;
        ArrayList<TaiKhoanDTO> dsSearch = new ArrayList<>();
        for(int i=0; i<dsTK.size(); i++){
            if(statenew == dsTK.get(i).getState()){
                dsSearch.add(dsTK.get(i));
            }
        }
        return dsSearch;
    }
    
    public static void main(String[] args) {
        TaiKhoanBUS t = new TaiKhoanBUS();
        TaiKhoanDTO d = new TaiKhoanDTO("NV012", "hoa1234", "1234", "2016-09-03", "AD", 1);
        //t.set(d);
        ArrayList<TaiKhoanDTO> dsSearch = new ArrayList<>();
        dsSearch=t.search("Đã khoá");
        for(TaiKhoanDTO ds:dsSearch){
            System.out.println(ds.getUsername()+" "+ds.getState());
        }
    }
   
    
}
