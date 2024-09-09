
package GUI;

import BUS.Nhanvien_BUS;
import DTO.Nhanvien_DTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateNV_GUI extends JFrame implements MouseListener {


    private class setNhanvien extends JPanel {
        public JLabel[] error;
        String id ="";
        public JTextField[] getData;
        private String[] title = {"Tên", "Số điện thoại","Địa chỉ","Email","Chức vụ"};
        private Font font_data = new Font("Tahoma", Font.PLAIN, 14);
        protected JPanel btn_exit;
        protected JPanel btn_submit;
        private JLabel chucvu;
        public setNhanvien(int chieurong, int chieucao, Nhanvien_DTO nv) {
            getData = new JTextField[title.length-1];
            error = new JLabel[title.length];
            init(chieurong, chieucao, nv);
        }

        private void init(int chieurong, int chieucao, Nhanvien_DTO nv  ) {

            setLayout(new FlowLayout(3, 0, 0));
            setPreferredSize(new Dimension(chieurong+20, chieucao));
            JPanel titleGUI_wrap = new JPanel(new BorderLayout());
            titleGUI_wrap.setPreferredSize(new Dimension(chieurong, 40));
            JLabel titleGUI = new JLabel("Sửa thông tin nhân viên".toUpperCase(), JLabel.CENTER);
            titleGUI.setFont(Cacthuoctinh_phuongthuc_chung.font_header);
            titleGUI.setPreferredSize(new Dimension(chieurong, 40));
            titleGUI_wrap.add(titleGUI, BorderLayout.CENTER);
            add(titleGUI_wrap);
            getData[0] = new JTextField(nv.getTennv());
            getData[1] = new JTextField("0" + String.valueOf(nv.getSdt()));
            getData[2] = new JTextField(nv.getDiachi());
            getData[3] = new JTextField(nv.getEmail());
            id = nv.getManv();
            for (int i = 0; i < title.length; i++) {
                JPanel item = new JPanel(new FlowLayout(3, 10, 0));
                item.setPreferredSize(new Dimension(chieurong, 100));
                JLabel lb_title = new JLabel(title[i]);
                lb_title.setPreferredSize(new Dimension(chieurong, 30));
                lb_title.setFont(font_data);
                lb_title.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                item.add(lb_title);
                if (i<= 3){
                getData[i].setPreferredSize(new Dimension(chieurong -10, 30));
                item.add(getData[i]);
                }
                else{
                    chucvu = new JLabel (nv.getChucvu(),JLabel.LEFT);
                    chucvu.setPreferredSize(new Dimension(chieurong, 30));
                    chucvu.setFont(font_data);
                    item.add(chucvu);
                }
                error[i] = new JLabel("");
                error[i].setFont(new Font("Tahoma", Font.ITALIC, 14) {
                });
                error[i].setPreferredSize(new Dimension(chieurong, 20));
                error[i].setForeground(Cacthuoctinh_phuongthuc_chung.error);
                item.add(error[i]);

                add(item);
            }
           
            JPanel btn_wrap = new JPanel(new FlowLayout(2,10,0));

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
    private setNhanvien setNV;
    private boolean flag_ten, flag_sdt, flag_dc, flag_email, flag_cv;
    private Trangnhanvien_GUI nvGUI;
    public UpdateNV_GUI(Trangnhanvien_GUI nvGUI, Nhanvien_DTO nv) {
        this.nvGUI= nvGUI;
        chieurong  = 400;
        chieucao  = 600;
        flag_ten = flag_sdt = flag_dc = flag_email = flag_cv =  false;
        init(nv);
    }

    private void init(Nhanvien_DTO nv) {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(chieurong, chieucao);
        setBackground(Color.WHITE);
        setNV = new setNhanvien(getWidth(), getHeight(), nv);
        setNV.btn_exit.addMouseListener(this);
        setNV.btn_submit.addMouseListener(this);
        add(setNV, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        try {
            JPanel btn = (JPanel) e.getSource();
            switch (btn.getName()) {
                case "btn_exit":
                    Object[] options = {"Có", "Không"};
                    int r1 = JOptionPane.showOptionDialog(null, "Những thông tin sẽ không được lưu sau khi thoát!\nBạn có muốn tiếp tục?", "Thoát", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                    if (r1 == JOptionPane.YES_OPTION) {
                        dispose();
                    } else {
                        // Thực hiện hành động khi người dùng chọn No hoặc đóng cửa sổ
                    }

                    break;
                case "btn_submit":
                    String ten = setNV.getData[0].getText();
                    String sdt = setNV.getData[1].getText();
                    String dchi = setNV.getData[2].getText();
                    String email = setNV.getData[3].getText();
                    String cv = setNV.chucvu.getText();
                
                    
                    Nhanvien_BUS nvBUS = new Nhanvien_BUS();
                    
                    if (!nvBUS.checkTENNV(ten)) {
                        setNV.error[0].setText("Tên chỉ chứa chữ cái");
                    } else {
                        flag_ten = true;
                     if (flag_ten == true){
                        setNV.error[1].setText("");
                        System.out.println(ten);
                        }                    }
                    
                    if (!nvBUS.checkSDT(sdt)) {
                        setNV.error[1].setText("Chứa 10 kí tự số và bắt đầu là số 0");
                    } else {
                        flag_sdt = true;
                        if (flag_sdt == true){
                        setNV.error[1].setText("");
                        System.out.println(sdt);
                        }
                    }
                    
                     if (!nvBUS.checkDCHI(dchi)) {
                        setNV.error[2].setText("Địa chỉ không vượt quá 250 ký tự!");
                    } else {
                        flag_dc = true;
                        if (flag_dc == true){
                        setNV.error[1].setText("");
                        System.out.println(dchi);
                        }
                    } 
                    
                     if (!nvBUS.checkEMAIL(email)) {
                        setNV.error[3].setText("Địa chỉ email không hợp lệ!");
                    } else {
                        flag_email = true;
                      if (flag_email == true){
                        setNV.error[1].setText("");
                        System.out.println(email);
                        }                    }
                 
                    
                    if (flag_ten && flag_sdt && flag_email && flag_dc ) {
                        Object[] options1 = {"Có", "Không"};
                        int r2 = JOptionPane.showOptionDialog(null, "Bạn đã chắc chắn với thông tin nhập vào?", "Sửa thông tin nhân viên ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options1,options1[0]);
                        if (r2 == JOptionPane.YES_OPTION) {
                            System.out.println(setNV.id);
                            Nhanvien_DTO new_nv = new Nhanvien_DTO(setNV.id,ten, cv,  Integer.parseInt(sdt), dchi, email,1);
                            nvBUS.update(new_nv);
                            nvGUI.reloadPagecontrol();                            
                            JOptionPane.showMessageDialog(null, "Sửa thông tin nhân viên thành công!");
                            dispose();

                        } else {
                            // Thực hiện hành động khi người dùng chọn No hoặc đóng cửa sổ
                        }

                    }

                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

//    public static void main(String[] args) throws SQLException {
//        Nhanvien_DTO nv = new Nhanvien_DTO("NV005","haha","Nhân viên",987666789 ,"329gyfejkdbsih","6383uyejn@gmail.com");
//        Trangnhanvien_GUI nvGUI = new Trangnhanvien_GUI(1200,700);
//        UpdateNV_GUI k = new UpdateNV_GUI(nvGUI,nv);
//    }
}


