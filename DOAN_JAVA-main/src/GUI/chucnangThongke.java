/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.chucnangDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author hp
 */
public class chucnangThongke extends JPanel {

    private int ccao, crong;
    private JPanel JP_listNameChucnangConCuaThongke;
    private String[] listLoaiThongke = {"Thống kê doanh thu ", "Sản phẩm bán chạy"};
    public JPanel JP_contentCuaLoaiThongke;
    private CenterContentStore centerContent;
    public int thongkeloai;

    public chucnangThongke(CenterContentStore centerContent, chucnangDTO cnDTO, String maquyen, int thongkeloai) {
        this.centerContent = centerContent;
        ccao = (int) centerContent.pageContent.getPreferredSize().getHeight();
        crong = (int) centerContent.pageContent.getPreferredSize().getWidth();

        init(cnDTO, maquyen, thongkeloai);
    }

    private void init(chucnangDTO cnDTO, String maquyen, int thongkeloai) {
        JP_listNameChucnangConCuaThongke = new JPanel(new FlowLayout(3));

        for (int i = 0; i < listLoaiThongke.length; i++) {
            int indexPage = i;
            JPanel btn_taikhoan = new JPanel(new BorderLayout());
            JLabel title_taikhoan = new JLabel(listLoaiThongke[i], JLabel.CENTER);
            btn_taikhoan.setPreferredSize(new Dimension((int) title_taikhoan.getPreferredSize().getWidth() + 50, (int) title_taikhoan.getPreferredSize().getHeight() + 20));
            btn_taikhoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


            if (i == thongkeloai) {
                title_taikhoan.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                btn_taikhoan.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                btn_taikhoan.setOpaque(true);
            } else {
                title_taikhoan.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                btn_taikhoan.setBackground(Cacthuoctinh_phuongthuc_chung.second_gray);
                btn_taikhoan.setOpaque(true);

            }

            title_taikhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
            btn_taikhoan.setName(cnDTO.toString());
            btn_taikhoan.add(title_taikhoan, BorderLayout.CENTER);
            btn_taikhoan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPanel btn_clicked = (JPanel) e.getSource();

                    chucnangDTO cn = new chucnangDTO(btn_clicked.getName());

                    

                    centerContent.changeCenterContent(cn, maquyen, indexPage);
                }

            });

            JP_listNameChucnangConCuaThongke.add(btn_taikhoan);
        }
        JP_listNameChucnangConCuaThongke.setPreferredSize(new Dimension(crong, (int) JP_listNameChucnangConCuaThongke.getPreferredSize().getHeight()));
        JP_listNameChucnangConCuaThongke.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
        JP_listNameChucnangConCuaThongke.setOpaque(true);

        JP_contentCuaLoaiThongke = new JPanel();
        //JP_contentCuaNameChucnangCon.setPreferredSize(new Dimension(crong,ccao - (int)JP_listNameChucnangConCuaTaikhoan.getPreferredSize().getHeight()));
        JP_contentCuaLoaiThongke.setBackground(Color.WHITE);
        JP_contentCuaLoaiThongke.setOpaque(true);
        int heightJP_content = ccao - (int) JP_listNameChucnangConCuaThongke.getPreferredSize().getHeight();
        this.thongkeloai = thongkeloai;
        LocalDate ngayHienTai = LocalDate.now();

        // Định dạng ngày theo định dạng dd/MM/yyyy
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String ngayDinhDang = ngayHienTai.format(dinhDang);
        ArrayList<String> currentday = new ArrayList<>();
        currentday.add(ngayDinhDang);
        currentday.add(ngayDinhDang);
        ThongKeGUI tkGUI = new ThongKeGUI(crong - 20, heightJP_content - 20);
        switch (thongkeloai) {
            case 0:
     
                currentday.add("Tất cả");

                tkGUI.ShowdoanhThu(currentday);
                tkGUI.initPnDoanhThu();
                JP_contentCuaLoaiThongke.add(tkGUI);
                break;
            case 1:
                
                currentday.add("1");

                tkGUI.ShowbanChay(currentday);
                JP_contentCuaLoaiThongke.add(tkGUI);
                break;
        }

        setLayout(new FlowLayout(3, 0, 0));
        add(JP_listNameChucnangConCuaThongke);
        add(JP_contentCuaLoaiThongke);
    }

}
