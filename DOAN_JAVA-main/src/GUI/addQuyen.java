/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.quyenBUS;
import DTO.nhacungcapDTO;
import DTO.quyenDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author hp
 */
public class addQuyen extends JFrame implements MouseListener {

    private class JP_addQuyen extends JPanel {
        public JLabel error;
        public JTextField getData;
        private String title = "Tên quyền";
        private Font font_data = new Font("Tahoma", Font.PLAIN, 14);
        protected JPanel btn_exit;
        protected JPanel btn_submit;
        
         public JP_addQuyen(int chieurong, int chieucao) {
            
            getData = new JTextField();
            error = new JLabel();
            btn_exit = new JPanel();
                    btn_submit = new JPanel();
            init(chieurong, chieucao);
        }
         
         private void init(int chieurong, int chieucao) {
              setLayout(new FlowLayout(3, 0, 0));
            setPreferredSize(new Dimension(chieurong+20, chieucao));
            JPanel titleGUI_wrap = new JPanel(new BorderLayout());
            titleGUI_wrap.setPreferredSize(new Dimension(chieurong, 40));
            JLabel titleGUI = new JLabel("Thêm quyền ".toUpperCase(), JLabel.CENTER);
            titleGUI.setFont(Cacthuoctinh_phuongthuc_chung.font_header);
            titleGUI_wrap.add(titleGUI, BorderLayout.CENTER);
            add(titleGUI_wrap);
            
            JPanel item = new JPanel(new FlowLayout(3, 10, 0));
                item.setPreferredSize(new Dimension(chieurong, 100));
                JLabel lb_title = new JLabel(title);
                lb_title.setPreferredSize(new Dimension(chieurong, 30));
                lb_title.setFont(font_data);
                lb_title.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                item.add(lb_title);

                getData.setPreferredSize(new Dimension(chieurong -10, 30));
                item.add(getData);

                error = new JLabel("");
                error.setFont(new Font("Tahoma", Font.ITALIC, 14) {
                });
                error.setPreferredSize(new Dimension(chieurong, 20));
                error.setForeground(Cacthuoctinh_phuongthuc_chung.error);
                item.add(error);

                add(item);
            
             JPanel btn_wrap = new JPanel(new FlowLayout(1));

            btn_exit = new JPanel();
            cssBtn(btn_exit, "Hủy", "btn_exit");

            btn_submit = new JPanel();
            cssBtn(btn_submit, "Xác nhận", "btn_submit");
            btn_wrap.setPreferredSize(new Dimension(chieurong, (int) btn_submit.getPreferredSize().getHeight() + 20));
            btn_wrap.add(btn_submit);
            btn_wrap.add(btn_exit);
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            add(btn_wrap);    
                
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
    private int chieurong, chieucao;
    private JP_addQuyen addQuyen;
    private boolean flag_ten;
    private phanquyen pqGUI;
    public addQuyen(phanquyen quyenGUI) {
        this.pqGUI=quyenGUI;
        chieurong  = 250;
        chieucao = 200;
        flag_ten= false;
        init();
    }
    
    private void init() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(chieurong, chieucao);
        setBackground(Color.WHITE);
        addQuyen = new JP_addQuyen(getWidth(), getHeight());
        addQuyen.btn_exit.addMouseListener(this);
        addQuyen.btn_submit.addMouseListener(this);
        add(addQuyen, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
     
        setVisible(true);
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        try{
             JPanel btn = (JPanel) e.getSource();
            switch (btn.getName()) {
            case "btn_exit":
                    Object[] options = {"Có", "Không"};
                    int r1 = JOptionPane.showOptionDialog(null, "Những thông tin sẽ không được lưu sau khi thoát!\nBạn có muốn tiếp tục thoát?", "Thoát", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                    if (r1 == JOptionPane.YES_OPTION) {
                         dispose();
                    } else {
                      
                    }

                    break;
                case "btn_submit":
                    String ten = addQuyen.getData.getText();
                    quyenBUS quyenBUS = new quyenBUS();
                    if (ten.equals("")) {
                        addQuyen.error.setText("Không được để trống");
                    } else if (!quyenBUS.checkTENNCC(ten)) {
                        addQuyen.error.setText("Tên chỉ chứa chữ cái");
                    } else {
                        flag_ten = true;
                        addQuyen.error.setText("");
                    }
                    if (flag_ten) {
                        Object[] options1 = {"Có", "Không"};
                        int r2 = JOptionPane.showOptionDialog(null, "Bạn đã chắc chắn với thông tin nhập vào?", "Thêm quyền", JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE,null,options1,options1[0]);
                        if (r2 == JOptionPane.YES_OPTION) {
                            quyenDTO qDTO = new quyenDTO(null,ten);
                            quyenBUS.add(qDTO);
                            pqGUI.addJP_NameQuyen(qDTO);
                            JOptionPane.showMessageDialog(null, "Thêm quyền mới thành công!");
                            dispose();

                        } else {
                            // Thực hiện hành động khi người dùng chọn No hoặc đóng cửa sổ
                        }

                    }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
