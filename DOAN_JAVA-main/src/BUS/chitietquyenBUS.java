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
import DTO.chitietquyenDTO;
import DAO.chitietquyenDAO;

public class chitietquyenBUS {

    private ArrayList<chitietquyenDTO> listChitietquyen;

    public chitietquyenBUS() {
        listChitietquyen = new ArrayList<>();
        init();
    }

    private void init() {
        chitietquyenDAO ctq = new chitietquyenDAO();
        listChitietquyen = ctq.listChitietquyen();
    }

    public ArrayList<chitietquyenDTO> getList() {
        return listChitietquyen;
    }

    public ArrayList<chitietquyenDTO> getListCtqTheoMAQUYEN(String maquyen) {
        ArrayList<chitietquyenDTO> listCtqTheoMAQUYEN = new ArrayList<>();
        for (chitietquyenDTO i : listChitietquyen) {
            if (i.getMAQUYEN().equals(maquyen)) {
                listCtqTheoMAQUYEN.add(i);
            }
        }
        return listCtqTheoMAQUYEN;
    }

    public boolean search(chitietquyenDTO ctqS) {
        ArrayList<chitietquyenDTO> listCtqTheoMAQUYEN = getListCtqTheoMAQUYEN(ctqS.getMAQUYEN());
        for (chitietquyenDTO i : listCtqTheoMAQUYEN) {
            boolean checkEqualMACHUCNANG = i.getMACHUCNANG().equals(ctqS.getMACHUCNANG());
            boolean checkEqualHANHDONG = i.getHANHDONG().equals(ctqS.getHANHDONG());
            if (checkEqualMACHUCNANG && checkEqualHANHDONG) {
                return true;
            }
        }
        return false;
    }

    public void updateChitietquyen(ArrayList<chitietquyenDTO> listCtqNEW, String maquyen) {
        //them nhung chi tiet quyen luc sau vao luc dau

        ArrayList<chitietquyenDTO> listCtqTheoMAQUYENbandau = getListCtqTheoMAQUYEN(maquyen);
        chitietquyenDAO ctqDAO = new chitietquyenDAO();
        for (chitietquyenDTO i : listCtqTheoMAQUYENbandau) {
            boolean flagTontai = false;
            System.out.println("Chi tiet quyen dang xet" + i.toString());
            for (chitietquyenDTO j : listCtqNEW) {
                boolean checkEqualMAQUYEN = i.getMAQUYEN().equals(j.getMAQUYEN());
                if (checkEqualMAQUYEN) {
                    boolean checkEqualMACHUCNANG = i.getMACHUCNANG().equals(j.getMACHUCNANG());
                    boolean checkEqualHANHDONG = i.getHANHDONG().equals(j.getHANHDONG());
                    if (checkEqualMACHUCNANG && checkEqualHANHDONG) {
                        flagTontai = true;
                        break;
                    }
                }

            }

            if (!flagTontai) {
                System.out.println("Chi tiet quyen xoa" + i.toString());

                ctqDAO.delete(i);
                //xóa khỏi database

            }
        }
        for (chitietquyenDTO i : listCtqNEW) {
            boolean flagTontai = false;
            for (chitietquyenDTO j : listCtqTheoMAQUYENbandau) {
                boolean checkEqualMAQUYEN = i.getMAQUYEN().equals(j.getMAQUYEN());

                if (checkEqualMAQUYEN) {
                    boolean checkEqualMACHUCNANG = i.getMACHUCNANG().equals(j.getMACHUCNANG());
                    boolean checkEqualHANHDONG = i.getHANHDONG().equals(j.getHANHDONG());
                    if (checkEqualMACHUCNANG && checkEqualHANHDONG) {
                        flagTontai = true;
                        break;
                    }
                }
            }
            if (!flagTontai) {

                ctqDAO.add(i);

            }
        }

        //tim kiem luc sau co chua ban dau khong
    }

}
