/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.SizeDAO;

import DTO.SizeDTO;

import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class SizeBUS {

    private ArrayList<SizeDTO> listSize;

    public SizeBUS() {
        listSize = new ArrayList<>();
        init();
    }

    private void init() {
        SizeDAO n = new SizeDAO();
        listSize = n.listSizeRemoveTrangthai0();
    }

    public ArrayList<SizeDTO> getListFull() {
        SizeDAO n = new SizeDAO();
        return n.listSize();
    }

    public ArrayList<SizeDTO> getList() {
        return listSize;
    }

    public SizeDTO getSizeDTO(String masize) {
        for (SizeDTO s : listSize) {
            if (s.getMASIZE().equals(masize)) {
                return s;
            }
        }
        return null;
    }

    public boolean checkTENSIZE(String t) {
        //tên nhà cung cấp không chứa các kí tự đặc biệt
        String regex = "^[\\p{L} 0-9]+$";
        return t.matches(regex);
    }

    private String createMASIZE() {
        int max = 0;
        ArrayList<SizeDTO> listFull = getListFull();
        for (int i = 0; i < listFull.size(); i++) {
            String MASIZElast = listFull.get(i).getMASIZE();
            String so = MASIZElast.replaceAll("[^0-9]", "");
            int stt = Integer.parseInt(so) + 1;
            if (stt > max) {
                max = stt;
            }
        }
        return "SIZE" + max;

    }

    public void updateInSQL() {
        SizeDAO sizeDAO = new SizeDAO();
        for (SizeDTO s : listSize) {
            sizeDAO.update(s);
        }
    }

    public void add(SizeDTO sizeDTO) {

        sizeDTO.setMASIZE(createMASIZE());
        listSize.add(sizeDTO);
        SizeDAO n = new SizeDAO();
        n.add(sizeDTO);
    }

    public void delete(String MASIZE) {
        for (int i = 0; i < listSize.size(); i++) {
            if (listSize.get(i).getMASIZE().equals(MASIZE)) {
                listSize.remove(i);
            }
        }
    }

    public void deleteInSQL(String maDelete) {
        SizeDAO sizeDAO = new SizeDAO();
        sizeDAO.delete(maDelete);
    }

    public boolean checkNewListSize(ArrayList<SizeDTO> newList) {
        boolean flag = true;
        for (int i = 0; i < listSize.size(); i++) {
            if (!listSize.get(i).equals(newList.get(i))) {
                if (newList.get(i).getTENSIZE().equals("")) {
                    continue;
                }
                    
                if (checkTENSIZE(newList.get(i).getTENSIZE())) {
                    for(SizeDTO s : listSize){
                        if(s.getTENSIZE().equals(newList.get(i).getTENSIZE())){
                            flag = false;
                    break;
                        }
                    }
                   if(flag)
                         listSize.get(i).setTENSIZE(newList.get(i).getTENSIZE());
                    
                   
                } else {
                    flag = false;
                    break;
                }
            }
        }
        
        return flag;
    }
}
