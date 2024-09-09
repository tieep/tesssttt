/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.khachHangBUS;
import DTO.khachHangDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addKhachHangGUI extends JFrame implements MouseListener {

    private int chieu_cao, chieu_rong;
    private addKhachHang addKh;
    private khachHangGUI khGUI;

    public addKhachHangGUI(khachHangGUI khGUI) {
        chieu_cao = 300;
        chieu_rong = 400;
        this.khGUI = khGUI;
        init();
    }

    private void init() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(chieu_rong, chieu_cao);
        setBackground(Color.WHITE);
        addKh = new addKhachHang(getWidth(), getHeight());

        add(addKh, BorderLayout.CENTER);
        addKh.btnHuy.addMouseListener(this);
        addKh.btnXacNhan.addMouseListener(this);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private class addKhachHang extends JPanel {

        private Font font_text = new Font("Tahoma", Font.BOLD, 15);
        private JTextField tfTen, tfSdt;
        private JPanel btnXacNhan, btnHuy;

        public addKhachHang(int w, int h) {
            tfTen = new JTextField();
            tfSdt = new JTextField();
            init(w, h);
        }

        public void init(int w, int h) {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(chieu_rong + 20, chieu_cao));

            JPanel titleGUI_wrap = new JPanel(new BorderLayout());
            titleGUI_wrap.setPreferredSize(new Dimension(chieu_rong, 40));
            JLabel titleGUI = new JLabel("Thêm Khách Hàng", JLabel.CENTER);
            titleGUI.setFont(Cacthuoctinh_phuongthuc_chung.font_header);
            titleGUI_wrap.add(titleGUI, BorderLayout.CENTER);
            add(titleGUI_wrap, BorderLayout.NORTH);

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.gridwidth = GridBagConstraints.REMAINDER;

            JLabel lbTen = new JLabel("Tên khách hàng:");
            lbTen.setFont(font_text);
            lbTen.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
            gbc.gridy = 0;
            inputPanel.add(lbTen, gbc);

            tfTen.setPreferredSize(new Dimension(300, 30));
            gbc.gridy = 1;
            inputPanel.add(tfTen, gbc);

            JLabel lbSdt = new JLabel("Số điện thoại:");
            lbSdt.setFont(font_text);
            lbSdt.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
            gbc.gridy = 2;
            inputPanel.add(lbSdt, gbc);

            tfSdt.setPreferredSize(new Dimension(300, 30));
            gbc.gridy = 3;
            inputPanel.add(tfSdt, gbc);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            buttonPanel.setPreferredSize(new Dimension(chieu_rong, 40));

            btnXacNhan = new JPanel();
            btnXacNhan.setPreferredSize(new Dimension(120, 30));
            cssBtn(btnXacNhan, "Xác nhận", "btnXacNhan");
            buttonPanel.add(btnXacNhan);

            btnHuy = new JPanel();
            btnHuy.setPreferredSize(new Dimension(120, 30));
            cssBtn(btnHuy, "Hủy", "btnHuy");
            buttonPanel.add(btnHuy);

            gbc.gridy = 4;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0.0;
            inputPanel.add(buttonPanel, gbc);
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            add(inputPanel, BorderLayout.CENTER);
        }

        private void cssBtn(JPanel b, String text, String name) {
            JLabel t = new JLabel(text, JLabel.CENTER);
            t.setForeground(Color.WHITE);
            b.setName(name);
            b.add(t);
            b.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
            b.setPreferredSize(new Dimension(100, (int) b.getPreferredSize().getHeight()));
            b.setOpaque(true);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            JPanel btn = (JPanel) e.getSource();
            switch (btn.getName()) {
                case "btnHuy":
                    Object[] options = {"Có", "Không"};
                    int r1 = JOptionPane.showOptionDialog(null, "Những thông tin sẽ không được lưu sau khi thoát!\nBạn có muốn tiếp tục thoát?", "Thoát", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (r1 == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                    break;
                case "btnXacNhan":
                    String name = addKh.tfTen.getText();
                    String phone = addKh.tfSdt.getText();
                    khachHangDTO kh_moi = new khachHangDTO(name, phone);
                    khachHangBUS busKH = new khachHangBUS();
                    boolean success = true;
                    for (khachHangDTO kh : busKH.getDs_khachHang()) {
                        if (kh.getSoDienThoai().equals(kh_moi.getSoDienThoai())) {
                            success = false;
                            break;
                        }
                    }
                    if (success) {
                        busKH.themkh(kh_moi);
                        khGUI.them_mot_kh(kh_moi);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại số điện thoại đã tồn tại!");
                    }
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        try {
            JPanel btn = (JPanel) e.getSource();
            btn.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
            btn.setOpaque(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        try {
            JPanel btn = (JPanel) e.getSource();
            btn.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
            btn.setOpaque(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
