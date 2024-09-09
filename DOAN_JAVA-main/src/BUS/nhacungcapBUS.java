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
import DTO.nhacungcapDTO;
import DAO.nhacungcapDAO;
import GUI.nhacungcapGUI;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class nhacungcapBUS {

    private ArrayList<nhacungcapDTO> listNhacungcap;
    
    public nhacungcapBUS() {
        listNhacungcap = new ArrayList<>();
        init();
    }

    public boolean checkTENNCC(String t) {
        //tên nhà cung cấp không chứa số và các kí tự đặc biệt
        String regex = "^[\\p{L} ]+$";
        return t.matches(regex);
    }

    public boolean checkSDT(String t) {
        //tổng cộng 10 chữ số: bắt đầu là số 0
        String regex = "^0[0-9]{9}$";
        return t.matches(regex);
    }

    private void init() {
        nhacungcapDAO n = new nhacungcapDAO();
        listNhacungcap = n.listNhacungcap();
    }

    public ArrayList<nhacungcapDTO> getList() {
        return listNhacungcap;
    }

    private String createMANCC() {
        int max =0;
        for(int i=0;i<listNhacungcap.size();i++){
            String MANCClast = listNhacungcap.get(i).getMANCC();
             String so = MANCClast.replaceAll("[^0-9]","");
        int stt = Integer.parseInt(so) + 1;
        if(stt > max) max = stt;
        }
        return "NCC" + max;
      
        
    }

    public void add(nhacungcapDTO ncc) {

        ncc.setMANCC(createMANCC());
        listNhacungcap.add(ncc);
        nhacungcapDAO n = new nhacungcapDAO();
        n.add(ncc);
    }

    public void deleteInSQL(String maDelete){
        nhacungcapDAO nccDAO = new  nhacungcapDAO();
        nccDAO.delete(maDelete);
    }
    
    public void updateInSQL(){
        nhacungcapDAO nccDAO = new  nhacungcapDAO();
        for(nhacungcapDTO ncc : listNhacungcap){
            nccDAO.update(ncc);
        }
    }
    
    public void delete(String MANCC){
        for(int i=0;i<listNhacungcap.size();i++){
            if(listNhacungcap.get(i).getMANCC().equals(MANCC))
                listNhacungcap.remove(i);
        }
    }
    
    public ArrayList<nhacungcapDTO> search(ArrayList<String> data_filter){
        ArrayList<nhacungcapDTO> re = new ArrayList<>();
        for(String i : data_filter){
            for(nhacungcapDTO j : listNhacungcap){
                boolean cond = true;
                if(!data_filter.get(0).equals(""))
                    cond = j.getMANCC().toLowerCase().contains(i.toLowerCase()) || j.getTENNCC().toLowerCase().contains(i.toLowerCase()) || String.valueOf("0"+j.getSDT()).contains(i);
                if(cond)
                    re.add(j);
                       
                   
                     
            }
            
        
            
    }
        return re;
    }
    public boolean checkNewListNCC(ArrayList<nhacungcapDTO> newList) {
        boolean flag = true;
        for (int i = 0; i < listNhacungcap.size(); i++) {
            if (!listNhacungcap.get(i).equals(newList.get(i))) {
                if(newList.get(i).getTENNCC().equals("") || String.valueOf(newList.get(i).getSDT()).equals("")) continue;
                if (checkTENNCC(newList.get(i).getTENNCC()) && checkSDT("0"+String.valueOf(newList.get(i).getSDT()))) {
                    listNhacungcap.get(i).setTENNCC(newList.get(i).getTENNCC());
                    listNhacungcap.get(i).setSDT(newList.get(i).getSDT());
                }
                
                else {
                            flag = false;
                            break;
                            }
            }
        }
        return flag;
    }
    
    
    public boolean importExcelData(nhacungcapGUI nccGUI) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);


        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            DecimalFormat decimalFormat = new DecimalFormat("#");
            try {
                FileInputStream excelFile = new FileInputStream(selectedFile);
                Workbook workbook = new XSSFWorkbook(excelFile);
                Sheet sheet = workbook.getSheetAt(0);


                for (Row row : sheet) {

                    String ten="";
                    String sdt="";
                    int j=0;
                    for (Cell cell : row) {

                        switch (cell.getCellType()) {
                            case STRING:{
                                if((j++)==0){
                                    ten=cell.getStringCellValue();
                                    if(!checkTENNCC(ten)) return false;
                                }else{
                                    sdt=cell.getStringCellValue();
                                    if(!checkSDT(sdt)) return false;
                                }
                                break;
                            }
                        }
                    }
                     nhacungcapDTO nccDTO = new nhacungcapDTO(ten, sdt);
                    add(nccDTO);
                    nccGUI.addLineDataInTable(nccDTO);

                }

                workbook.close();
                excelFile.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

}
