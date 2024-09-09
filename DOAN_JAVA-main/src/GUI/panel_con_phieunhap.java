package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DTO.SanPhamDTO;
import DTO.phieunhap_DTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class panel_con_phieunhap extends JPanel implements MouseListener{
	
	private phieunhap_DTO phieunhap_DTO;
	private String[] giatri;
	private JLabel jl[];
	private JTextField jt[];
	private phieunhap_GUI phieunhap_GUI;
	private boolean clickedxoa;
	public panel_con_phieunhap(int x,phieunhap_DTO h,phieunhap_GUI phieunhap_GUI) {
		this.phieunhap_DTO = h;
		this.setPreferredSize(new Dimension(x,30));
		this.phieunhap_GUI = phieunhap_GUI;
		this.clickedxoa = false;
		
		DecimalFormat format_double = new DecimalFormat("#,###.0");
		String l = format_double.format(h.getTongtien());
		
		
		giatri = new String[]{h.getMAPN(),h.getMANV(),h.getNgay()+"",l,h.getMANCC()};
		jl = new JLabel[5];
		
		for (int i = 0; i< 5; i++) {
			jl[i] = new JLabel(giatri[i],JLabel.CENTER);
			
			this.add(jl[i]);
		}
		this.setLayout(new GridLayout(1,5));
		this.setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
		
		this.addMouseListener(this);
	}

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (this.phieunhap_GUI.clicked_sua()) {
			if (this.phieunhap_GUI.so_sanh()) {
                            try {
                                this.phieunhap_GUI.show_chitietphieunhap_chinhsua(phieunhap_DTO);
                            } catch (SQLException ex) {
                                Logger.getLogger(panel_con_phieunhap.class.getName()).log(Level.SEVERE, null, ex);
                            }
			} else if (!this.phieunhap_GUI.so_sanh()) {
				String t = "Bạn có muốn hủy bỏ các chỉnh sửa";
				frame_thong_bao_phieunhap m = new frame_thong_bao_phieunhap(t, phieunhap_GUI);
			}
				
				
			
			
		} else if (!this.phieunhap_GUI.clicked_sua() && !this.phieunhap_GUI.clickedxoa()) {
                    try {
                        this.phieunhap_GUI.show_chitietphieunhap(phieunhap_DTO);
                    } catch (SQLException ex) {
                        Logger.getLogger(panel_con_phieunhap.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
		
		
		if (this.phieunhap_GUI.clickedxoa()) {
			if (!this.clickedxoa) {
				
				this.clickedxoa = true;
				this.setBackground(Color.decode("#60A3BC"));
				
			} else if (this.clickedxoa) {
				this.clickedxoa = false;
				this.setBackground(null);
			}
			
		} 
	}
	
	public boolean chon_xoa() {
		return this.clickedxoa;
	}
	
	public phieunhap_DTO getpn() {
		return this.phieunhap_DTO;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
