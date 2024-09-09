/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.TaiKhoanDTO;
import DTO.chucnangDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author hp
 */
public class CenterContentStore extends JPanel {

    StoreScreen SS_main;
    private int chieurong, chieucao;
    public JPanel search;
    public JPanel thaotac;
    public JPanel pageContent;

    public CenterContentStore(int chieurong, int chieucao, StoreScreen s) {
        SS_main = s;

        this.chieurong = chieurong;
        this.chieucao = chieucao;
        init();

    }

    private void init() {
//        setPreferredSize(new Dimension(chieurong, chieucao));
        setLayout(new FlowLayout(3, 10, 0));
        setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
        setOpaque(true);

        search = new JPanel();
        search.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
        search.setOpaque(true);
        add(search);
//
        thaotac = new JPanel();
        thaotac.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
        thaotac.setOpaque(true);
        add(thaotac);
//        
        pageContent = new JPanel();
        pageContent.setLayout(new BorderLayout());
        pageContent.add(new JLabel("Chưa lựa chọn chức năng", JLabel.CENTER), BorderLayout.CENTER);
        pageContent.setPreferredSize(new Dimension(chieurong, chieucao));
        pageContent.setBackground(Color.WHITE);
        pageContent.setOpaque(true);
        add(pageContent);
//

    }

    public void changeCenterContent(chucnangDTO cnDTO, String maquyen, int thongkeloai) {
        SearchInStore JP_search = new SearchInStore(cnDTO.getMACHUCNANG(), pageContent, thongkeloai);
        showSearch(JP_search);

        pageContent.removeAll();

        Component[] JP_childSearch = JP_search.getComponents();
        if ((int) search.getPreferredSize().getWidth() + (int) thaotac.getPreferredSize().getWidth() <= chieurong) {
            if (JP_childSearch.length != 0) {
                pageContent.setPreferredSize(new Dimension(chieurong, chieucao - (int) search.getPreferredSize().getHeight() - 5));
            } else {
                pageContent.setPreferredSize(new Dimension(chieurong, chieucao - (int) thaotac.getPreferredSize().getHeight() - 15));
            }
        } else {
            pageContent.setPreferredSize(new Dimension(chieurong, chieucao - (int) search.getPreferredSize().getHeight() - (int) thaotac.getPreferredSize().getHeight()));
        }

        chucnangThongke cnThK = new chucnangThongke(this, cnDTO, maquyen, thongkeloai);
        ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, cnThK, SS_main);
        showThaotac(JP_thaotac);
        showPageContent(cnThK);

    }

    public void changeCenterContent(chucnangDTO cnDTO, String maquyen) throws SQLException {
        this.search.removeAll();
        this.search.revalidate();
        this.search.repaint();
        this.thaotac.removeAll();
        this.thaotac.revalidate();
        this.thaotac.repaint();
//        if (cnDTO.getMACHUCNANG().equals("KH")) {
//
//            this.pageContent.removeAll();
//            this.pageContent.setLayout(new BorderLayout(0, 0));
//
////            view_quan_li_khach_hang qlkh = new view_quan_li_khach_hang(chieurong, chieucao, SS_main.getTaiKhoanDTO());
//            khachHangGUI kh = new khachHangGUI(chieurong,chieucao);
//            this.pageContent.add(kh, BorderLayout.CENTER);
//
//            this.pageContent.revalidate();
//            this.pageContent.repaint();
//
//            return;
//        }
//        if (cnDTO.getMACHUCNANG().equals("PN")) {
//
//            this.pageContent.removeAll();
//            this.pageContent.setLayout(new BorderLayout(0, 0));
//
//            JPanel pn = new JPanel();
//            TaiKhoanDTO h = new TaiKhoanDTO();
//            phieunhap_GUI phieunhap = new phieunhap_GUI(chieurong, chieucao, SS_main.getTaiKhoanDTO());
//            this.pageContent.add(phieunhap, BorderLayout.CENTER);
//            view_quan_li_khach_hang qlkh = new view_quan_li_khach_hang(chieurong, chieucao, SS_main.getTaiKhoanDTO());
//            this.pageContent.add(qlkh, BorderLayout.CENTER);
//
//            this.pageContent.revalidate();
//            this.pageContent.repaint();
//
//            return;
//        }
        if (cnDTO.getMACHUCNANG().equals("PN")) {

            this.pageContent.removeAll();
            this.pageContent.setLayout(new BorderLayout(0, 0));

            JPanel pn = new JPanel();
            TaiKhoanDTO h = new TaiKhoanDTO();
            phieunhap_GUI phieunhap = new phieunhap_GUI(chieurong, chieucao, SS_main.getTaiKhoanDTO());
            this.pageContent.add(phieunhap, BorderLayout.CENTER);

            this.pageContent.revalidate();
            this.pageContent.repaint();

            return;
        }

        SearchInStore JP_search = new SearchInStore(cnDTO.getMACHUCNANG(), pageContent);
        showSearch(JP_search);

        pageContent.removeAll();

        Component[] JP_childSearch = JP_search.getComponents();
        if ((int) search.getPreferredSize().getWidth() + (int) thaotac.getPreferredSize().getWidth() <= chieurong) {
            if (JP_childSearch.length != 0) {
                pageContent.setPreferredSize(new Dimension(chieurong, chieucao - (int) search.getPreferredSize().getHeight() - 5));
            } else {
                pageContent.setPreferredSize(new Dimension(chieurong, chieucao - (int) thaotac.getPreferredSize().getHeight() - 15));
            }
        } else {
            pageContent.setPreferredSize(new Dimension(chieurong, chieucao - (int) search.getPreferredSize().getHeight() - (int) thaotac.getPreferredSize().getHeight()));
        }
        int widthPageContent = (int) pageContent.getPreferredSize().getWidth();
        int heightPageContent = (int) pageContent.getPreferredSize().getHeight();
        switch (cnDTO.getMACHUCNANG()) {

            case "NULLTK": {
                chucnangTaikhoan cntk = new chucnangTaikhoan(this, cnDTO, maquyen);
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, cntk);
                showThaotac(JP_thaotac);
                showPageContent(cntk);
                break;
            }
            case "NULLHD": {
                String maNV = SS_main.getTaiKhoanDTO().getMaNV();
                chucnangHoadon cnhd;
                try {
                    cnhd = new chucnangHoadon(this, cnDTO, maquyen, maNV);
                    ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, cnhd);
                    showThaotac(JP_thaotac);
                    showPageContent(cnhd);
                } catch (SQLException ex) {
                    Logger.getLogger(CenterContentStore.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            }

            case "NCC": {

                nhacungcapGUI nccGUI = new nhacungcapGUI(widthPageContent, heightPageContent);
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, nccGUI);
                showThaotac(JP_thaotac);
                showPageContent(nccGUI);
                break;
            }
            case "LOAI": {
                loaiSPGUI lGUI = new loaiSPGUI(widthPageContent, heightPageContent);
                System.out.println("TAII SAOAOO");
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, lGUI);
                showThaotac(JP_thaotac);
                showPageContent(lGUI);
                break;
            }
            case "NV": {
                try {
                    Trangnhanvien_GUI nvGUI = new Trangnhanvien_GUI(widthPageContent, heightPageContent);
                    ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, nvGUI);
                    showThaotac(JP_thaotac);
                    showPageContent(nvGUI);
                    break;
                } catch (SQLException ex) {
                    Logger.getLogger(CenterContentStore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case "SP": {
                SanPhamGUI spGUI = new SanPhamGUI(widthPageContent, heightPageContent, pageContent.getBackground());
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, spGUI);
                showThaotac(JP_thaotac);
                showPageContent(spGUI);
                break;
            }
            case "TK": {
                chucnangTaikhoan cntk = new chucnangTaikhoan(this, cnDTO, maquyen);
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, cntk);
                showThaotac(JP_thaotac);
                showPageContent(cntk);
                break;
            }

            case "HD": {
                String maNV = SS_main.getTaiKhoanDTO().getMaNV();
                chucnangHoadon cnhd;
                try {
                    cnhd = new chucnangHoadon(this, cnDTO, maquyen, maNV);
                    ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, cnhd);
                    showThaotac(JP_thaotac);
                    showPageContent(cnhd);
                } catch (SQLException ex) {
                    Logger.getLogger(CenterContentStore.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            }

            case "PQ": {
                phanquyen pq = new phanquyen(widthPageContent, heightPageContent, SS_main.quyenUser);
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, pq, SS_main);
                showThaotac(JP_thaotac);
                showPageContent(pq);
                break;
            }

            case "SIZE": {
                SizeGUI sizeGUI = new SizeGUI(widthPageContent, heightPageContent);
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, sizeGUI);
                showThaotac(JP_thaotac);
                showPageContent(sizeGUI);
                break;
            }

            case "NULLThK": {
                chucnangThongke cnThK = new chucnangThongke(this, cnDTO, maquyen, 0);
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, cnThK, SS_main);
                showThaotac(JP_thaotac);
                showPageContent(cnThK);
                break;
            }

            case "NULLDX":
                JPanel dx = new JPanel();
                dx.setPreferredSize(new Dimension(widthPageContent, heightPageContent));
                dx.setBackground(Color.WHITE);
                dx.setOpaque(true);
                showPageContent(dx);
                Object[] options = {"Có", "Không"};
                int r2 = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn đăng xuất?", "Đăng xuất", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (r2 == JOptionPane.YES_OPTION) {
                    SS_main.dispose();
                    LoginUI login = new LoginUI();
                }
                break;
            case "PN": {
                TaiKhoanDTO d = new TaiKhoanDTO();
                JPanel pn = new JPanel();
                pn.setPreferredSize(new Dimension(widthPageContent, heightPageContent));
                pn.add(new JLabel("Đây là trang phiếu nhập"));
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, pn);
                showThaotac(JP_thaotac);
                showPageContent(pn);
                break;
            }
//<<<<<<< HEAD
            case "KH": {
                khachHangGUI kh = new khachHangGUI(widthPageContent, heightPageContent);
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, kh);
                showThaotac(JP_thaotac);
                showPageContent(kh);
                break;
            }
            case "QLK": {
                KhoGUI k = new KhoGUI(widthPageContent, heightPageContent);
                LocalDate ngayHienTai = LocalDate.now();

                // Định dạng ngày theo định dạng dd/MM/yyyy
                DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String ngayDinhDang = ngayHienTai.format(dinhDang);
                
                ArrayList<String> currentday = new ArrayList<>();
                currentday.add(ngayDinhDang);
                currentday.add(ngayDinhDang);
                String tuNgay = currentday.get(0);
                String denNgay = currentday.get(1);
                System.out.println(tuNgay+" den "+ denNgay);
                k.data(currentday);
                ThaotacInStore JP_thaotac = new ThaotacInStore(cnDTO.getMACHUCNANG(), maquyen, k, SS_main);
                showThaotac(JP_thaotac);
                showPageContent(k);
//>>>>>>> e21f13e080e87a8f6b4836c080defab66dad1c9b
                break;
            }
            default:
                JPanel p = new JPanel();
                p.add(new JLabel("Chưa có chức năng này", JLabel.CENTER));
                showPageContent(p);
                break;
        }
    }

    public void showPageContent(Component page) {
//        SS_main.pageContent.setPreferredSize(new Dimension(chieurong,this.chieucao));
        this.pageContent.setLayout(new BorderLayout(0, 0));
        this.pageContent.add(page, BorderLayout.CENTER);

        this.pageContent.revalidate();
        this.pageContent.repaint();
    }

    public void showThaotac(JPanel thaotac) {

        this.thaotac.removeAll();
        this.thaotac.add(thaotac);

        this.thaotac.revalidate();
        this.thaotac.repaint();
    }

    public void showSearch(JPanel search) {
        this.search.removeAll();

        this.search.add(search);

        this.search.revalidate();
        this.search.repaint();
    }
}
