package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import DAO.DAO_qlks;
import DTO.model_qlkh;

public class panel_con_qlkh extends JPanel implements MouseListener{
	private JLabel[] jl = new JLabel[4];
	private String[] giatri,vd;
	private model_qlkh model_qlkh;
	private  Boolean clicked_chinhsua,clicked_xoa;
	private JTextField[] jt = new JTextField[4];
	private JLabel label;
	
	private panel_center_bang_dskh panel_center_bang_qlkh;
	
	public panel_con_qlkh(panel_center_bang_dskh panel_center_bang_qlkh, model_qlkh model_qlkh) {
		this.model_qlkh = model_qlkh;
		this.panel_center_bang_qlkh = panel_center_bang_qlkh;
		giatri = new  String[] {model_qlkh.getMakh()+"",model_qlkh.getTen(),model_qlkh.getSdt()+"",model_qlkh.getDiem()+""};
		
		clicked_chinhsua = false;
		clicked_xoa = false;
		
		;
		for (int i = 0; i < 4; i++) {
			jl[i] = new JLabel(giatri[i],JLabel.CENTER);
			
			this.add(jl[i]);
			jl[i].addMouseListener(this);		
			
		}
		this.setPreferredSize(new Dimension(panel_center_bang_qlkh.getPreferredSize().width-40,30));
		this.setLayout(new GridLayout(1,4));
		this.setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
	
		this.addMouseListener(this);
		
	}
	
	public void panel_con_dskh_che_do_update() {
		
		this.removeAll();
		label = new JLabel(this.model_qlkh.getMakh()+"",JLabel.CENTER);
		this.add(label);
		label.addMouseListener(this);
		jt[1] = new JTextField(this.model_qlkh.getTen(),JTextField.CENTER);
		this.add(jt[1]);
		jt[1].addMouseListener(this);
		jt[2] = new JTextField(this.model_qlkh.getSdt(),JTextField.CENTER);
		this.add(jt[2]);
		jt[2].addMouseListener(this);
		jt[3] = new JTextField(this.model_qlkh.getDiem()+"",JTextField.CENTER);
		this.add(jt[3]);
		jt[3].addMouseListener(this);
		this.setPreferredSize(new Dimension(panel_center_bang_qlkh.getPreferredSize().width-40,30));
		this.setLayout(new GridLayout(1,4));
		this.setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
		this.addMouseListener(this);
		this.repaint();
		this.revalidate();
	}
	public void panel_con_dskh_che_do_xoa() {
		this.panel_center_bang_qlkh.return_true_clicked_xoa();
		this.removeAll();
		for (int i = 0; i < 4; i++) {
			jl[i] = new JLabel(giatri[i],JLabel.CENTER);
			
			this.add(jl[i]);
			jl[i].addMouseListener(this);		
			
		}
		this.repaint();
		this.revalidate();
		
	}
	public void update_ve_che_do_xem() {
		this.removeAll();
		for (int i = 0; i < 4; i++) {
			jl[i] = new JLabel(giatri[i],JLabel.CENTER);
			
			this.add(jl[i]);
			jl[i].addMouseListener(this);		
			
		}
		this.repaint();
		this.revalidate();
	}
	public void return_true_clicked_xoa() {
		this.panel_center_bang_qlkh.return_true_clicked_xoa();
	}
	public void return_false_clicked_xoa() {
		this.panel_center_bang_qlkh.return_false_clicked_xoa();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		try {
			JLabel src = (JLabel) e.getSource();
			if (this.panel_center_bang_qlkh.clicked__xoa()) {
				if (!clicked_xoa) {
					clicked_xoa = true;
					this.setBackground(Color.decode("#60A3BC"));				
				} else if (clicked_xoa) {
					clicked_xoa = false;
					this.setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
					this.setBackground(null);
				}
				
				
				
			} else if (!this.panel_center_bang_qlkh.clicked__xoa()) {
				
				
				
				this.setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
				
				System.out.println();
			}
		} catch (Exception e2) {
			
		}
		
		
			
	
			
		
	}
	public boolean khach_hang_xoa() {
		return this.clicked_xoa;
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
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void ktra() {
		if (clicked_chinhsua) {
			this.setBorder(BorderFactory.createLineBorder(Color.red,2));
		} else if (!clicked_chinhsua) {
			this.setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
		}
				
	}
	public void turn_on() {
		clicked_chinhsua = true;
	}
	
	
	public Boolean sosanh() {
		model_qlkh h = DAO_qlks.getinstance().select_by_id(this.model_qlkh.getMakh());
		String diem = String.valueOf(h.getDiem());
		if (this.jt[1].getText().trim().equals(h.getTen()) && this.jt[2].getText().trim().equals(h.getSdt()) && this.jt[3].getText().trim().equals(diem) ) {
			
			return true;
			
		}
		return false;
		
	}
	public model_qlkh get_kh_update() {
		int dtl = Integer.parseInt(jt[3].getText());
		model_qlkh k = new model_qlkh(this.model_qlkh.getMakh(),jt[1].getText().trim(), jt[2].getText().trim(), dtl);
		return k;
	}
	
	public model_qlkh get_kh() {
		return this.model_qlkh;
	}

	
	
}
