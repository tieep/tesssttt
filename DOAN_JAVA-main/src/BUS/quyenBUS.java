/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author hp
 */
import java.util.ArrayList;
import DTO.quyenDTO;
import DAO.quyenDAO;
import DTO.TaiKhoanDTO;
public class quyenBUS {
    private ArrayList<quyenDTO> list;
    
    public quyenBUS(){
        list = new ArrayList<>();
        init();
    }
    
    private void init(){
        quyenDAO dao = new quyenDAO();
        list = dao.listQuyen();
    }
    
    public  ArrayList<quyenDTO> getList(){
        return list;
    }
    
    public static void main(String[] args) {
        quyenBUS p = new quyenBUS();
        ArrayList<quyenDTO> list = p.getList();
        for(quyenDTO i: list)
              System.out.println(i.getMAQUYEN()+" "+i.getTENQUYEN());
    }
    public boolean checkTENNCC(String t) {
        //tên nhà cung cấp không chứa số và các kí tự đặc biệt
        String regex = "^[\\p{L} ]+$";
        return t.matches(regex);
    }
    public boolean checkCanDelete(quyenDTO quyenDelete){
         TaiKhoanBUS tkBUS = new TaiKhoanBUS();
                        ArrayList<TaiKhoanDTO> listTK = new ArrayList<>();
                        listTK = tkBUS.getDsTK();
                        for(TaiKhoanDTO t : listTK){
                             System.out.println("tAI KHOAN"+t.getMaQuyen());
                            System.out.println("QUYEN CAN XOA"+quyenDelete.getMAQUYEN());
                            if(t.getMaQuyen().equals(quyenDelete.getMAQUYEN())) return false;
                        }
          return true;
    }
    public void delete(String maquyen){
         for(int i=0;i<list.size();i++){
            if(list.get(i).getMAQUYEN().equals(maquyen))
            {
                list.remove(i);
                deleteInSQL(maquyen);
                break;
            }
        }
    }
    public void deleteInSQL(String maquyen){
        quyenDAO qDAO = new  quyenDAO();
        qDAO.delete(maquyen);
    }
    public void add(quyenDTO quyen) {

        quyen.setMAQUYEN(createMAQUYEN());
        System.out.println(createMAQUYEN());
        list.add(quyen);
        quyenDAO n = new quyenDAO();
        n.add(quyen);
    }

    private String createMAQUYEN() {
       int max =0;
        for(int i=0;i<list.size();i++){
            String MAQUYENlast = list.get(i).getMAQUYEN();
             String so = MAQUYENlast.replaceAll("[^0-9]","");
             if(!so.equals("")){
                        int stt = Integer.parseInt(so) + 1;
                        if(stt > max) max = stt;
             }
 
        }
        return "QUYEN" + max;
      
        
    }
    
    public void updateTENQUYEN(quyenDTO qNEW){
        for(quyenDTO k : list){
            if((k.getMAQUYEN().equals(qNEW.getMAQUYEN()))){
                if(!k.getTENQUYEN().equals(qNEW.getTENQUYEN()))
                {
                    k.setTENQUYEN(qNEW.getTENQUYEN());
                    updateTENQUYENInSQL(qNEW);
                }
                break;
            }
        }
    }
    
     private void updateTENQUYENInSQL(quyenDTO qNEW){
         quyenDAO qDAO = new quyenDAO();
         qDAO.update(qNEW);
     }
     
     public quyenDTO searchquyenDTO(String MAQUYEN){
         for(quyenDTO t : list){
             if(t.getMAQUYEN().equals(MAQUYEN))
                 return t;
         }
         return null;
     }
    
}
