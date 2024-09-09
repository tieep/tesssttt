/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
/**
 *
 * @author hp
 */
public class hanhdongGUI extends JPanel {

    public JLabel title;
    public JLabel icon;

    public hanhdongGUI(String titlehanhdong, String pathIcon) {
        title = new JLabel(titlehanhdong,JLabel.CENTER);
        icon = new JLabel(new ImageIcon(pathIcon));
        init();
    }

    private void init() {
        setLayout(new GridLayout(2, 1, 0, 0));

        title.setPreferredSize(new Dimension((int) title.getPreferredSize().getWidth() + 30, (int) title.getPreferredSize().getHeight() + 15));

        icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);
        add(icon);
        setBackground(Color.WHITE);
        setOpaque(true);
        Border top = BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK);
        Border arround = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);

        // thaotac.setBorder();
        setBorder(BorderFactory.createCompoundBorder(top, arround));

    }
    

}
