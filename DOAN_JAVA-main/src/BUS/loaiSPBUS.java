/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.loaiSPDAO;
import DAO.nhacungcapDAO;
import DTO.loaiSP;
import DTO.nhacungcapDTO;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class loaiSPBUS {

    private ArrayList<loaiSP> list;

    public loaiSPBUS() {
        list = new ArrayList<>();
        init();
    }

    private void init() {
        loaiSPDAO lDAO = new loaiSPDAO();
        list = lDAO.listLoaiSPRemoveTrangthai2();
    }

    public ArrayList<loaiSP> getList() {
        return list;
    }

    public ArrayList<loaiSP> getListFull() {
        loaiSPDAO n = new loaiSPDAO();
        return n.listLoaiSP();
    }

    public boolean checkTENLOAI(String t) {
        //tên nhà cung cấp không chứa số và các kí tự đặc biệt
        String regex = "^[\\p{L} ]+$";
        return t.matches(regex);
    }

    private String createMALOAI() {
        int max = 0;

        ArrayList<loaiSP> listFull = getListFull();
        for (int i = 0; i < listFull.size(); i++) {
            String MALOAIlast = listFull.get(i).getMALOAI();

            String so = MALOAIlast.replaceAll("[^0-9]", "");
            int stt = Integer.parseInt(so) + 1;
            if (stt > max) {
                max = stt;
            }
        }
        return "LOAI" + max;

    }

    public void add(loaiSP loaiDTO) {

        loaiDTO.setMALOAI(createMALOAI());
        list.add(loaiDTO);
        loaiSPDAO n = new loaiSPDAO();
        n.add(loaiDTO);
    }

    public void updateInSQL() {
        loaiSPDAO loaiDAO = new loaiSPDAO();
        for (loaiSP ncc : list) {
            loaiDAO.update(ncc);
        }
    }

    public void delete(String MALOAI) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMALOAI().equals(MALOAI)) {
                list.remove(i);
            }
        }
    }

    public void deleteInSQL(String maDelete) {
        loaiSPDAO loaiDAO = new loaiSPDAO();
        loaiDAO.delete(maDelete);
    }

    public boolean checkNewListLoai(ArrayList<loaiSP> newList) {

        boolean flag = true;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(newList.get(i))) {
                if (newList.get(i).getTENLOAI().equals("")) {
                    continue;
                }
                if (!list.get(i).getTENLOAI().equals(newList.get(i).getTENLOAI())) {
                    if (checkTENLOAI(newList.get(i).getTENLOAI())) {
                        for (loaiSP s : list) {
                            if (s.getTENLOAI().equals(newList.get(i).getTENLOAI())) {
                                flag = false;
                                break;
                            }
                        }

                    } else {
                        flag = false;
                        break;
                    }
                }
                if(flag){
                        list.get(i).setTENLOAI(newList.get(i).getTENLOAI());
                    list.get(i).setTINHTRANG(newList.get(i).getTINHTRANG());
                   }
            }
        }
        return flag;
    }

    public boolean checkTINHTRANG(String MALOAI) {
        for (loaiSP j : list) {
            if (j.getMALOAI().equals(MALOAI)) {
                return (j.getTINHTRANG() == 1);
            }
        }
        return true;
    }

    public ArrayList<loaiSP> search(ArrayList<String> data_filter) {
        ArrayList<loaiSP> re = new ArrayList<>();

        for (loaiSP j : list) {

            boolean checkMALOAI = j.getMALOAI().toLowerCase().contains(data_filter.get(0).toLowerCase());
            boolean checkTENLOAI = j.getTENLOAI().toLowerCase().contains(data_filter.get(0).toLowerCase());
            int status = (data_filter.get(1).equals("Đang bán")) ? 1 : 0;
            boolean checkStatus = j.getTINHTRANG() == status;
            boolean cond = true;
            if (data_filter.get(0).equals("")) {
                if (!data_filter.get(1).equals("Tất cả")) {
                    cond = checkStatus;
                }
            } else {
                if (!data_filter.get(1).equals("Tất cả")) {
                    cond = checkMALOAI || checkTENLOAI && checkStatus;
                } else {
                    cond = checkMALOAI || checkTENLOAI;
                }
            }
            if (cond) {
                re.add(j);

            }
        }
        for (loaiSP i : re) {
            System.out.println("Mang " + i.toString());
        }

        return re;
    }

    public loaiSP getloaiSP(String maloai) {
        for (loaiSP s : list) {
            if (s.getMALOAI().equals(maloai)) {
                return s;
            }
        }
        return null;
    }

}
