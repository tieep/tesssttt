package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


class SignUp extends JFrame {
    private JPanel MainPanel,left, right,form;
    private JPanel p1,p2,p3,p4,p5,p6;
    private ImageIcon imageIcon;
    private JLabel imageLabel, namestore, signup, submit;

   
    public void init() {

        //this.setTitle("SignUp");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        
        MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.X_AXIS));
            left = new JPanel();
            right = new JPanel(); 
        
        //------------------------------------- AVT & STORE'S NAME---------------------------------------------------
                left.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue); // Sử dụng constructor của Color để tạo màu mới
                left.setPreferredSize(new Dimension(300, 0));
                left.setLayout(new FlowLayout(1,0,30));

        
                //----------STORE LOGO-------------
                    imageLabel = new JLabel(new ImageIcon("./src/images/store_logo_logout.png"));
                
                // -------- STORE'S NAME ---------
                    namestore = new JLabel(Cacthuoctinh_phuongthuc_chung.storeName.toUpperCase(), JLabel.CENTER);
                    namestore.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family, Font.BOLD, 24));
                    namestore.setForeground(Color.white);
       

        // -------------------------- FORM ĐĂNG KÝ -----------------------------
            right.setPreferredSize(new Dimension(300, 0));
            right.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue); 
            right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
            // tạo p1 - điều hướng ( exit / return )
                p1 = new JPanel();
                p1.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                    FlowLayout f = new FlowLayout();
                p1.setLayout(f);
                // RETURN
                    JLabel re = new JLabel(Cacthuoctinh_phuongthuc_chung.return_icon, JLabel.LEFT);
                    re.setBounds(1,5,50,20);
                    re.setPreferredSize(new Dimension(100, 20));
                    re.addMouseListener(new MouseAdapter() {
                        
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // TODO Auto-generated method stub
                             dispose();
                            setVisible(false);
                           
                            LoginUI l = new LoginUI();
                       
                        }
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            // TODO Auto-generated method stub
                            JLabel x= (JLabel) e.getSource();
                            x.removeAll();
                            x.setText("quay lại");
                            x.validate();
                            x.repaint();
                        }
                        public void mouseExited(MouseEvent e) {
                            // TODO Auto-generated method stub
                            JLabel x= (JLabel) e.getSource();
                            x.removeAll();
                            x.setText("");
                            x.validate();
                            x.repaint();
                        }
                    });
                    

                //EXIT
                    JLabel e = new JLabel(Cacthuoctinh_phuongthuc_chung.exit_icon,JLabel.RIGHT );
                    e.setBounds(240,5,40,20);
                    e.setPreferredSize(new Dimension(180, 30));
                    e.addMouseListener(new MouseAdapter() {
                        
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // TODO Auto-generated method stub
                            System.exit(0);
                       
                        }
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            // TODO Auto-generated method stub
                            JLabel x= (JLabel) e.getSource();
                            x.removeAll();
                            x.setText("đóng");
                            x.validate();
                            x.repaint();
                        }
                        public void mouseExited(MouseEvent e) {
                            // TODO Auto-generated method stub
                            JLabel x= (JLabel) e.getSource();
                            x.removeAll();
                            x.setText("");
                            x.validate();
                            x.repaint();
                        }
                    });
                        

                    p1.add(re);
                    p1.add(e);
        
        
            // tạo p2 - label ( Đăng ký )
                p2 = new JPanel();
                p2.setLayout( new BoxLayout(p2, BoxLayout.Y_AXIS));
                p2.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                    signup = new JLabel("Đăng ký", SwingConstants.CENTER);
                    signup.setAlignmentX(Component.CENTER_ALIGNMENT);
                    signup.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family, Font.BOLD, 22));
                    signup.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                    Component verticalStrut1 = Box.createVerticalStrut(20);
                    Component verticalStrut2 = Box.createVerticalStrut(10);
                    p2.add( verticalStrut2 ); 
                    p2.add(signup);
                    p2.add( verticalStrut1 );
        
        
            // tạo p3 - tên đăng nhập
                p3 = new JPanel();
                p3.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                    JPanel p = new JPanel();
                    p.setPreferredSize(new Dimension(300, 60));
                    p.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                        JLabel username = new JLabel("Tên đăng nhập:", JLabel.CENTER);
                        username.setPreferredSize(new Dimension(120, 30));
                        username.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.CENTER_BASELINE, 13));
                        username.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                        username.setOpaque(true);
                        username.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                        JTextField inpname = new JTextField();
                        inpname.setPreferredSize(new Dimension(140, 30));
                        JLabel warning1 = new JLabel("*Không được để trống!", JLabel.RIGHT);
                        warning1.setPreferredSize(new Dimension(240, 20));
                        warning1.setForeground(Cacthuoctinh_phuongthuc_chung.error);
                        warning1.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.PLAIN,13));

                    p.add(username);
                    p.add(inpname);
                    p.add(warning1);

                p3.add(p);     
        
            // tạo p4 - mật khẩu
                p4 = new JPanel();
                p4.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                    JPanel q = new JPanel();
                    q.setPreferredSize(new Dimension(400, 60));
                    q.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                        JLabel password = new JLabel("Mật khẩu:", JLabel.CENTER);
                        password.setPreferredSize(new Dimension(120, 30));
                        password.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family, Font.CENTER_BASELINE, 13));
                        password.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                        JPasswordField pass = new JPasswordField();
                        pass.setPreferredSize(new Dimension(140, 30));
                         JLabel warning2 = new JLabel("*Không được để trống!", JLabel.RIGHT);
                            warning2.setPreferredSize(new Dimension(240, 20));
                            warning2.setForeground(Cacthuoctinh_phuongthuc_chung.error);
                            warning2.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.PLAIN,13));

                        
                    q.add(password);
                    q.add(pass);
                    q.add(warning2);
                p4.add(q);
        
            // tạo p5 - nhập lại mk
                p5 = new JPanel();
                p5.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                    JPanel r = new JPanel();
                    r.setPreferredSize(new Dimension(420, 60));
                    r.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                        JLabel repassword = new JLabel("Nhập lại mật khẩu:", JLabel.LEFT);
                        repassword.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                        repassword.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family, Font.CENTER_BASELINE, 13));
                        repassword.setPreferredSize(new Dimension(120, 30));
                        repassword.setOpaque(true);
                        repassword.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                        JPasswordField repass = new JPasswordField();
                        repass.setPreferredSize(new Dimension(140, 30));
                        JLabel warning3 = new JLabel("*Không được để trống!", JLabel.RIGHT);
                        warning3.setPreferredSize(new Dimension(240, 20));
                        warning3.setForeground(Cacthuoctinh_phuongthuc_chung.error);
                        warning3.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.PLAIN,13));

                        
                    r.add(repassword);
                    r.add(repass);
                    r.add(warning3);
                p5.add(r);
        
        
            // tạo p6 - click xác nhận
                p6 = new JPanel();
                    JLabel submit = new JLabel("Xác nhận".toUpperCase(), JLabel.CENTER);
                    submit.setPreferredSize(new Dimension(100, 30));
                    submit.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                    submit.setForeground(Color.WHITE);
                    submit.setOpaque(true);
                    MouseListener mouseListener  ;
                    
        mouseListener = new MouseListener() {
            @Override
           public void mouseClicked(MouseEvent e) {
    String name = inpname.getText();
    String password = new String(pass.getPassword());
    String rePassword = new String(repass.getPassword());

    if (name.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Tên đăng nhập không được bỏ trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    } else if (!check_username(name)) {
        JOptionPane.showMessageDialog(null, "Tên đăng nhập không chứa ký tự đặc biệt!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (password.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Mật khẩu không được bỏ trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    } else if (password.length() < 5) {
        JOptionPane.showMessageDialog(null, "Mật khẩu phải trên 5 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (rePassword.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Mời xác nhận lại mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    } else if (!Arrays.equals(password.toCharArray(), rePassword.toCharArray())) {
        JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    JOptionPane.showMessageDialog(null, "Đăng ký tài khoản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    dispose();
    LoginUI login = new LoginUI();
}

////               
//            

        
            @Override
            public void mousePressed(MouseEvent e) {
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                submit.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
                submit.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                submit.setBorder(BorderFactory.createLineBorder(Cacthuoctinh_phuongthuc_chung.darkness_blue, 1));
                System.out.println("mouse Entered");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                submit.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                submit.setForeground(Color.WHITE);
                submit.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                System.out.println("mouse Exited");
            }
            
             public boolean check_username(String username){
                for (int i=0; i< username.length(); i++)
            {
               char c = username.charAt(i);
                if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c) ) 
                    return false;
                else
                    i++;
            }
            return true;
    }
    
    public boolean check_pass(char pass[]){
        if (pass.length < 5)
            return false;
        else return true;
    }
    
    public boolean check_repass(char pass[],char repass[]){
        for (int i=0; i< pass.length;i++)
        {
            for ( int j = i ; j==i; j++)
                if ( repass[j] == pass[i])
                    break;
                else return false;
        }
        return true;
    } 
    
          
        };
        submit.addMouseListener( mouseListener);  
                    Component verticalStrut = Box.createVerticalStrut(30);
                    p6.add(verticalStrut);
                    p6.add(submit);
                    p6.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);          

        // Thêm nhãn vào frame
        left.add(imageLabel);
        left.add(namestore);
        right.add(p1);
        right.add(p2);
        right.add(p3);
        right.add(p4);
        right.add(p5);
        right.add(p6);
        
        MainPanel.add(left);
        MainPanel.add(right); 
        this.add(MainPanel);
        this.setVisible(true);
    }

   
    public SignUp() {
        init();
    }
        
    public static void main(String []args){
    SignUp s= new SignUp();
    }
}
