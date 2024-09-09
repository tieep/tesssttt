package BUS;

import DAO.Nhanvien_DAO;
import DTO.Nhanvien_DTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Nhanvien_BUS {

    public ArrayList<Nhanvien_DTO> listnv;
    
    public Nhanvien_BUS() throws SQLException{
        list();
    }
    
    public void list() throws SQLException{
        Nhanvien_DAO dsnv = new Nhanvien_DAO();
        listnv = dsnv.list();
    }
    
    public boolean checkTENNV(String t) {
        //tên nhân viên không chứa số và các kí tự đặc biệt
        String regex = "^[\\p{L} ]+$";
        return t.matches(regex);
    }

    public boolean checkSDT(String t) {
        //tổng cộng 10 chữ số: bắt đầu là số 0
        String regex = "^0[0-9]{9}$";
        return t.matches(regex);
    }
    
    public boolean checkDCHI(String address) {
                return address.length() <= 250;
    }
    
    public boolean checkEMAIL (String email) {
         String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    
    private int Maxid(String prefix) {
        int maxNumber = 0;
        for (int i = 0; i < listnv.size(); i++) {
            String employeeId = listnv.get(i).getManv();
            if (employeeId.startsWith(prefix)) {
                String numberPart = employeeId.substring(2);
                    int number = Integer.parseInt(numberPart);
                    if (number > maxNumber) {
                        maxNumber = number;
                        System.out.println(maxNumber);
                    }
            }
        }
            return maxNumber;
       
    }
    
    
    
    private String createidNV() {
        int stt = Maxid("NV") + 1;
        return   "NV" + stt;
    }
    
    private String createidQL() {
        int stt = Maxid("QL") + 1;
        return   "QL" + stt;
    }
    
    private String createidAD() {
        int stt = Maxid("AD") + 1;
        return   "AD" + stt;
    }
    

    public void add( String ten, String cv, int sdt, String dc,String e,int tt) throws SQLException {
        if ( cv == "Nhân viên")
        {
        String id = createidNV();
        Nhanvien_DTO nv = new Nhanvien_DTO(id,ten,cv,sdt,dc,e,tt);
        listnv.add(nv);
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        nvDAO.add(nv);
        }   
        else{
            if( cv == "Quản lý bán hàng" || cv == "Quản lý kho")
            {
                String id = createidQL();
                Nhanvien_DTO ql = new Nhanvien_DTO(id,ten,cv,sdt,dc,e,tt);
                listnv.add(ql);
                Nhanvien_DAO nvDAO = new Nhanvien_DAO();
                nvDAO.add(ql);
            }   
            else{
                String id = createidAD();
                Nhanvien_DTO ad = new Nhanvien_DTO(id,ten,cv,sdt,dc,e,tt);
                listnv.add(ad);
                Nhanvien_DAO nvDAO = new Nhanvien_DAO();
                nvDAO.add(ad);
            }
        }
    }
   
    public void setTT_newnv(Nhanvien_DTO nv) throws SQLException{
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        nvDAO.update_tt(nv, 1);
    }
    public void update(Nhanvien_DTO nv) throws SQLException {
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        nvDAO.update(nv);
    }
    
    public boolean check_accNV(Nhanvien_DTO nv) throws SQLException{
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        return nvDAO.check_accNV(nv.getManv());
    }
    
    public ArrayList<Nhanvien_DTO> search(String in4) throws SQLException{
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        ArrayList<Nhanvien_DTO> re = nvDAO.search(in4);
        return re;
    }
    public void delete(Nhanvien_DTO nv) throws SQLException
    {
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        nvDAO.delete(nv.getManv());
    }
    
    public void update_tt(Nhanvien_DTO nv) throws SQLException
    {
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        nvDAO.update_tt(nv, 0);

    }
    public boolean checkNewListNV(ArrayList<Nhanvien_DTO> newList) {
        boolean flag = true;
        for (int i = 0; i < listnv.size(); i++) {
            if (!listnv.get(i).equals(newList.get(i))) {
                if(newList.get(i).getTennv().equals("") || String.valueOf(newList.get(i).getSdt()).equals("")) continue;
                if (checkTENNV(newList.get(i).getTennv()) && checkSDT("0"+String.valueOf(newList.get(i).getSdt()))) {
                    listnv.get(i).setTennv(newList.get(i).getTennv());
                    listnv.get(i).setSdt(newList.get(i).getSdt());
                }
                
                else {
                            flag = false;
                            break;
                            }
            }
        }
        return flag;
    }
    
    public ArrayList<Nhanvien_DTO> getlist(){
        return this.listnv;
    }
    
         public static void main(String[] args) throws SQLException {
        Nhanvien_BUS nv = new Nhanvien_BUS();
        nv.add("UYEN","Nhân viên",987666789 ,"TP HCM","6383uyejn@gmail.com",1);
//        Nhanvien_DTO n2 = new Nhanvien_DTO("QL1","Phương Uyên","Nhân viên bán hàng",987666789 ,"Quận 8 TPHCM","uyen123@gmail.com",1);
////        nv.update(n2);
////        nv.list(); 
//        if (nv.check_accNV(n2))
//        {
//            System.out.println("NV có tài khoản");
//            nv.update_tt(n2);
//        }
//        else 
//        {
//            System.out.println("NV khong có tài khoản");
//            nv.delete(n2);
//        }
         };
}
