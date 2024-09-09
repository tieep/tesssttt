package GUI;

import BUS.quyenBUS;
import DTO.TaiKhoanDTO;
import DTO.quyenDTO;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Cursor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoreScreen extends JFrame{
    public int chieucao;
    public int chieurong;
    public JPanel title_bar;
    private JLabel exitStore;
    public MenuChucNangStore menu;
    protected CenterContentStore centerContent;
  
public TaiKhoanDTO tkUSER ;
  public quyenDTO quyenUser;
     public StoreScreen(TaiKhoanDTO tkUSER){
         this.tkUSER = tkUSER;
         quyenBUS qBUS  = new quyenBUS();
         quyenUser = qBUS.searchquyenDTO(tkUSER.getMaQuyen());
        chieurong=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        chieucao=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-40;
       init();
    }
    public StoreScreen(){
        tkUSER =new TaiKhoanDTO("NV012", "hoa", "1234", "2016-09-03", "QQLHT", 1);
        quyenBUS qBUS  = new quyenBUS();
         quyenUser = qBUS.searchquyenDTO(tkUSER.getMaQuyen());
        chieurong=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        chieucao=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-40;
       init();
    }

    
    private void init(){
        setSize(chieurong, chieucao);
       // setExtendedState(JFrame.MAXIMIZED_BOTH); // Đặt JFrame full màn hình
        // thanh dau tien cua chuong trinh gom: logo, ten cua hang, nut thanh chuong trinh 

       title_bar= new JPanel();
        title_bar.setLayout(new BorderLayout());

        JPanel title_bar_left= new JPanel();
        title_bar_left.setLayout(new FlowLayout(1,10,10));
        JLabel logoStore= new JLabel(Cacthuoctinh_phuongthuc_chung.logoStore); 
        title_bar_left.add(logoStore);
        JLabel storeName=new JLabel(Cacthuoctinh_phuongthuc_chung.storeName);
        storeName.setFont(Cacthuoctinh_phuongthuc_chung.font_header);
        storeName.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        title_bar_left.add(storeName);
        title_bar_left.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        title_bar_left.setOpaque(true);

        JPanel title_bar_right= new JPanel();
        title_bar_right.setLayout(new FlowLayout(1,10,10));
        title_bar_right.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        title_bar_right.setOpaque(true);
        exitStore= new JLabel(Cacthuoctinh_phuongthuc_chung.exit_icon);
        exitStore.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                title_bar_right.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                title_bar_right.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                title_bar_right.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                
               
            }
        });
        
        title_bar_right.add(exitStore);
        
        
        title_bar.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        title_bar.setOpaque(true);
        title_bar.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);

        title_bar.add(title_bar_left,BorderLayout.WEST);
        title_bar.add(title_bar_right,BorderLayout.EAST);

         
        //end
        
      
        
        
        menu= new MenuChucNangStore(tkUSER.getMaQuyen(),(int)chieurong/7,chieucao,this);
        
        centerContent= new CenterContentStore(chieurong-(int)menu.getPreferredSize().getWidth()-20, chieucao-20-(int)title_bar.getPreferredSize().getHeight(), this);
        //end

        setLayout(new BorderLayout());
        add(centerContent,BorderLayout.CENTER);
        
        add(menu,BorderLayout.WEST);
        add(title_bar,BorderLayout.NORTH);
        
        getContentPane().setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
        
       
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }
    
    public TaiKhoanDTO getTaiKhoanDTO(){
        return this.tkUSER;
    }
    public static void main(String[] args) {
//        TaiKhoanDTO d = new TaiKhoanDTO("AD001", "", "", "", "QQLBH", WIDTH);
        StoreScreen s= new StoreScreen();
    }
}
