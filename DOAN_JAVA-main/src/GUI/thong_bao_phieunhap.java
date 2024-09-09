package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BUS.chitietphieunhap_BUS;
import BUS.chitietsanpham_BUS;
import BUS.phieunhap_BUS;
import DTO.chitietphieunhap_DTO;
import DTO.chitietsanpham_DTO;
import DTO.phieunhap_DTO;
import static java.awt.image.ImageObserver.ALLBITS;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class thong_bao_phieunhap extends JPanel implements MouseListener{
	private JFrame j;
	
	private JLabel jl_n1,jl_n2, jl_yes, jl_no,jl_c2;
	private JPanel panel_north, panel_center,panel_n1, panel_n2, panel_c1,panel_c2,panel_c3;
	private String content;
	private phieunhap_GUI phieunhap_GUI;
	private chitietphieunhap_BUS chitietphieunhap_BUS;
	
	public thong_bao_phieunhap( String t,phieunhap_GUI phieunhap_GUI) {
		this.phieunhap_GUI = phieunhap_GUI;
		
		this.setSize(400,400);
		
		this.content = t;
		this.setLayout(new BorderLayout());
		panel_north = new JPanel();
		
		Font f = new Font("Arial", Font.BOLD, 16);
		Font fys = new Font("Arial",Font.BOLD,20);
		Font x = new Font("Arial", ALLBITS, 20);
		
		this.j = j;
		
		
		panel_north.setPreferredSize(new Dimension(0,30));
		panel_north.setBackground(Color.decode("#0097A7"));panel_north.setOpaque(true);
		panel_north.setLayout(new GridLayout(1,2,0,0));
		
		
		
		panel_n2 = new JPanel();

		panel_n2.setLayout(new FlowLayout(2,0,0));
		
		
		panel_n1 = new JPanel();
		panel_n1.setLayout(new FlowLayout(0));

		
		
		jl_n1 = new JLabel("thông báo");
		ImageIcon icon_tb = new  ImageIcon("./src/hinh_anh/thong_bao.png");
		jl_n1.setIcon(icon_tb);
		jl_n1.setFont(f);
		
		jl_n2 = new JLabel("X",JLabel.CENTER);
		jl_n2.setPreferredSize(new Dimension(0,0));
		jl_n2.setFont(x); 
		jl_n2.setForeground(Color.white);
		jl_n2.setPreferredSize(new Dimension(40,30));
                
		
		
		
		panel_n2.add(jl_n2);	 
		panel_n2.setBackground(Color.decode("#0097A7"));
		panel_n1.add(jl_n1);
		panel_n1.setBackground(Color.decode("#0097A7"));
		
		
		panel_north.add(panel_n1);
		panel_north.add(panel_n2);
		
		
			
		panel_center = new JPanel();
		panel_center.setLayout(new FlowLayout());
		
		
		panel_c1 = new JPanel();
		panel_c1.setPreferredSize(new Dimension(1500,50));

		
			
		panel_c2 = new JPanel();
		panel_c2.setPreferredSize(new Dimension(this.getWidth(),(this.getHeight()-30)/3));
		jl_c2 = new JLabel("<html>"+ t +"</html>",JLabel.LEFT);
//                jl_c2.setBackground(Color.white);jl_c2.setOpaque(true);
		jl_c2.setPreferredSize(new Dimension(panel_c2.getPreferredSize().width-panel_c2.getPreferredSize().width/3,panel_c2.getPreferredSize().height-panel_c2.getPreferredSize().height/3));
		jl_c2.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		panel_c2.setLayout(new FlowLayout(1));
		
		panel_c2.add(jl_c2);
//		panel_c2.setLayout(new BorderLayout());
		
		
		
		
		panel_c3 = new JPanel();
		panel_c3.setPreferredSize(new Dimension(this.getWidth(),(this.getHeight()-30)/3));
		jl_yes = new JLabel("yes", JLabel.CENTER);jl_yes.setFont(fys);
		jl_yes.setPreferredSize(new Dimension(60,25));
		jl_yes.setBackground(Color.decode("#3498db"));jl_yes.setOpaque(true);
		
		
		jl_no = new JLabel("no",JLabel.CENTER);jl_no.setFont(fys);
		jl_no.setPreferredSize(new Dimension(60,25));
		jl_no.setBackground(Color.decode("#3498db"));jl_no.setOpaque(true);
		
		panel_c3.setLayout(new FlowLayout(1,60,5));
		panel_c3.add(jl_yes);
		panel_c3.add(jl_no);

		panel_center.add(panel_c1);
		panel_center.add(panel_c2);
		panel_center.add(panel_c3);
		
		
		jl_n2.addMouseListener(this);
		jl_yes.addMouseListener(this);
		jl_no.addMouseListener(this);
		
		this.add(panel_north, BorderLayout.NORTH);
		this.add(panel_center, BorderLayout.CENTER);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jl_yes ) {
			if (this.content.contains("Hoàn thành nhập sản phẩm")) {
				
			this.phieunhap_GUI.Frame_them_phieunhap().add_phieunhap();
			this.phieunhap_GUI.Frame_them_phieunhap().them_chitietphieunhap();
			this.phieunhap_GUI.Frame_them_phieunhap().update_chitietsanpham();
//			this.phieunhap_GUI.Frame_them_phieunhap().update_price();
			
			String t =  phieunhap_GUI.Frame_them_phieunhap().panel_them_phieunhap().kiem_tra_gia_update();
			if (t.contains("SP")  ) {
                            this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                            this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
                                this.phieunhap_GUI.thong_bao_update_thongtin(t);
                                
//				frame_thong_bao_phieunhap c = new frame_thong_bao_phieunhap(t, phieunhap_GUI);
			} else {
				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
                                
				this.phieunhap_GUI.Frame_them_phieunhap().setVisible(false);
                                this.phieunhap_GUI.return_null_frame_them_phieu_nhap();
                                
                                this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                                this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
                                
//                                this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
//                        this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
			}
				
			
			
				
			}
			/////////////////////////////////////////// thay đổi giá  /////////////////////////////
			if(this.content.contains("thấp hơn giá cũ bạn có muốn cập nhật?")) {
				
				this.phieunhap_GUI.update_gia_thap_hon();
				
				
                                this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                                this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
				this.phieunhap_GUI.Frame_them_phieunhap().setVisible(false);
                                this.phieunhap_GUI.return_null_frame_them_phieu_nhap();
                                
				
			}
			if (this.content.contains("Bạn có muốn thay đổi dữ liệu")) {
					this.phieunhap_GUI.update_ctpn_sau_chinh_sua();
                            try {
                                this.phieunhap_GUI.update_ctsp_sau_chinh_sua();
                            } catch (SQLException ex) {
                                Logger.getLogger(thong_bao_phieunhap.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                this.phieunhap_GUI.refresh_giu_ctpn();
                            } catch (SQLException ex) {
                                Logger.getLogger(thong_bao_phieunhap.class.getName()).log(Level.SEVERE, null, ex);
                            }
					this.phieunhap_GUI.update_phieunhap();
					
					String t = this.phieunhap_GUI.thong_bao_doi_gia();
					if (t.contains("SP")) {
						JOptionPane.showMessageDialog(j,t );
					}
					JOptionPane.showMessageDialog(this.phieunhap_GUI.frame_thong_bao_phieunhap(),"Sửa thành công" );
                                        this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                                        this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
					
			}
			if (this.content.contains("Hủy bỏ các hoạt động và tiếp tục tìm kiếm")) {
				this.phieunhap_GUI.return_false_clicksua();
				this.phieunhap_GUI.return_false_clickedxoa();
				this.phieunhap_GUI.dinh_dang();
				
                                
                                this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                                this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
			} 
			if(this.content.contains("Làm mới bảng danh sách và hủy bỏ tất cả hoạt dộng")) {
				
				this.phieunhap_GUI.return_false_clickedxoa();
				this.phieunhap_GUI.return_false_clicksua();
				this.phieunhap_GUI.dinh_dang();
				this.phieunhap_GUI.Refresh_moi();
				
                                this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                                this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
			}
			if (this.content.contains("Thoát trạng thái sửa và bắt đầu xóa")) {
				this.phieunhap_GUI.return_false_clicksua();
				this.phieunhap_GUI.return_true_clicked_xoa();
				this.phieunhap_GUI.dinh_dang();
                            try {
                                this.phieunhap_GUI.refresh_giu_ctpn();
                            } catch (SQLException ex) {
                                Logger.getLogger(thong_bao_phieunhap.class.getName()).log(Level.SEVERE, null, ex);
                            }
				
                                
                                this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                                this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
				JOptionPane.showMessageDialog(this.phieunhap_GUI.frame_thong_bao_phieunhap(),"Click vào phiếu nhập bạn muốn xóa" );
			}
			if (this.content.contains("Thoát chế độ xóa và hủy các thao tác")) {
				this.phieunhap_GUI.return_false_clickedxoa();
				this.phieunhap_GUI.return_true_clicked_sua();
                                this.phieunhap_GUI.dinh_dang();
				this.phieunhap_GUI.Refresh_moi();
				
                                this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                                this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
                                JOptionPane.showMessageDialog(this.phieunhap_GUI.frame_thong_bao_phieunhap(),"Click vào phiếu nhập bạn muốn sửa");
				
			}
			if (this.content.contains("Hủy bỏ các thay đổi")) {
				this.phieunhap_GUI.return_false_clicksua();
                            try {
                                this.phieunhap_GUI.refresh_giu_ctpn();
                            } catch (SQLException ex) {
                                Logger.getLogger(thong_bao_phieunhap.class.getName()).log(Level.SEVERE, null, ex);
                            }
				this.phieunhap_GUI.dinh_dang();
				
                                this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                                this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
			}
                        if(this.content.contains("Trả về giá trị ban đầu")){
                            this.phieunhap_GUI.tra_ve_gia_tri_cu();
                            
                            this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                            this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
                        }
                        if(this.content.contains("Xác nhận xóa ?")){
                           
                            try {
                            
                                chitietsanpham_BUS ctspBUS = new chitietsanpham_BUS();
                                phieunhap_BUS pnBUS = new phieunhap_BUS();
                                for(phieunhap_DTO pnDTO : this.phieunhap_GUI.panel_bang_dspn.ds_chon_xoa()){
                                    chitietphieunhap_BUS ctpn = new chitietphieunhap_BUS(pnDTO);
                                    boolean canDelete = true;
                                    for(chitietphieunhap_DTO ctpnDTO : ctpn.getList()){
                                       
                                             System.out.println("MAPN " +pnDTO.getMAPN()+" ,MASP "+ctpnDTO.getMasp()+" ,MASIZE "+ctpnDTO.getMasize()+" ,SOLUONG "+ctpnDTO.getSoluong());
                                        
                                             for(chitietsanpham_DTO ctspDTO : ctspBUS.getlist()){
                                           if(ctspDTO.getMASP().equals(ctpnDTO.getMasp()) && ctspDTO.getMASIZE().equals(ctpnDTO.getMasize()) ){
                                               if( ctspDTO.getSoluong()>=ctpnDTO.getSoluong())
                                                    System.out.println("MASP "+ctpnDTO.getMasp()+" candelete "+canDelete);
                                               else canDelete=false;
                                           }
                                        }
                                    }
                                   if(canDelete){
                                        
                                         JOptionPane.showMessageDialog(null,"Xóa phiếu nhập "+pnDTO.getMAPN()+" thành công");
                                         ctpn.delete();
                                         pnBUS.delete(pnDTO);
                                         for(chitietphieunhap_DTO ctpnDTO : ctpn.getList()){
                                              for(chitietsanpham_DTO ctspDTO : ctspBUS.getlist()){
                                                  if(ctpnDTO.getMasp().equals(ctspDTO.getMASP()) && ctpnDTO.getMasize().equals(ctspDTO.getMASIZE())){
                                                      ctspDTO.setSoluong(ctpnDTO.getSoluong()-ctpnDTO.getSoluong());
                                                      ctspBUS.updateAfterTT(ctspDTO);
                                                  }
                                                        
                                              }
                                         }
                                   }else{
                                       JOptionPane.showMessageDialog(null,"Không thể xóa phiếu nhập "+pnDTO.getMAPN()+" do số lượng trong kho không đủ để xóa");
                                   }

                                }
                              
                            } catch (SQLException ex) {
                                Logger.getLogger(thong_bao_phieunhap.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                            this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
                        }
                       
			
			
		}
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == jl_no) {
                    this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                    this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
                    try {
                        this.phieunhap_GUI.Frame_them_phieunhap().setVisible(false);
                        this.phieunhap_GUI.return_null_frame_them_phieu_nhap();
                    } catch (Exception e2) {
                    }
			
		}
                if (e.getSource() == jl_n2){
                    this.phieunhap_GUI.frame_thong_bao_phieunhap().setVisible(false);
                    this.phieunhap_GUI.return_null_frame_thong_bao_phieunhap();
//                    this.setVisible(false);
                }
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated methodjl stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		 if (e.getSource() == jl_yes) {
				jl_yes.setBackground(Color.decode("#2980b9"));
				jl_yes.setOpaque(true);
			}
			 if (e.getSource() == jl_no) {
				jl_no.setBackground(Color.decode("#2980b9"));jl_no.setOpaque(true);
			}
			 if (e.getSource() == jl_n2 ) {
				 jl_n2.setBackground(Color.red);
				 jl_n2.setOpaque(true);
					
				}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == jl_n2) {
			jl_n2.setBackground(Color.decode("#0097A7"));
			jl_n2.setOpaque(true);
		}
		if (e.getSource() == jl_yes) {
			jl_yes.setBackground(Color.decode("#3498db"));jl_yes.setOpaque(true);
		}
		if (e.getSource() == jl_no) {
			jl_no.setBackground(Color.decode("#3498db"));jl_no.setOpaque(true);
		}
	}
		
}
