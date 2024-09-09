/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
/**
 *
 * @author hp
 */
public class chucnangGUI extends JPanel{
    private JLabel TENCHUCNANG;
    public JLabel nameIcon;
    public int chieucao;
    public int chieurong;
    public Font font_chucnang= new Font("Tahoma", Font.PLAIN, 14);
    public chucnangGUI(String tenchucnang,String nameIcon,int c,int r){
        TENCHUCNANG = new JLabel(tenchucnang);
        this.nameIcon= new JLabel(new ImageIcon(nameIcon),JLabel.CENTER);
        chieucao=c;
        chieurong=r;
        init();
    }
    
    private void init(){
        setPreferredSize(new Dimension(chieucao,chieurong));
        setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        setOpaque(true);
        setLayout(new BorderLayout(10,0));
        
        
        nameIcon.setPreferredSize(new Dimension(50,30));
        add( nameIcon,BorderLayout.WEST);
        
        TENCHUCNANG.setFont(font_chucnang);
        TENCHUCNANG.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
        add(TENCHUCNANG,BorderLayout.CENTER);
    }
    
    
    
}
