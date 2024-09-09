package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.html.HTML;

import BUS.BUS_qlkh;
import DAO.DAO_qlks;
import DTO.TaiKhoanDTO;
import DTO.model_qlkh;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class view_quan_li_khach_hang extends JPanel implements MouseListener{
	private BUS_qlkh BUS_qlkh = new BUS_qlkh();
	private JPanel[] jp,jp1,jp3,jp1_lr;
	private JLabel[] jl,jl1,jl3,jlha,jl1_r;
	private JTextField[] tf;
	private String[] timkiem,thaotac,hinhanh,option_so,option_kitu;
        private int diemmin,diemmax;
	private  Boolean clickedchinhsua,clickedxoa,clickedthem ;
	private ArrayList<model_qlkh> ds_kh_update_loi;
	private JPanel panel_north;
	private Border border_thaotac;
	private  panel_bang_dskh panel_bang_dskh;
	private JComboBox<String> optiondtl,optiontenkh,optionmkh;
	private frame_themkh frame_themkh;
	private frame_thong_bao_update frame_thong_bao_update;
        private String mkh,ten,sdt,diem;
	
	public view_quan_li_khach_hang(int w,int h,TaiKhoanDTO taiKhoanDTO){
		this.setPreferredSize(new Dimension(w,h));
		this.setLayout(new BorderLayout());
		jp = new JPanel[5];
		jp1 = new JPanel[4];
		jp3 = new JPanel[6];
		jl = new JLabel[4];
		jl1 = new JLabel[4];
		jl3 = new JLabel[6];
		jlha = new JLabel[6];
		jp1_lr = new JPanel[2];
		jl1_r = new JLabel[2];
		timkiem = new String[] {"Theo mã khách" , "Theo tên khách","theo so dien thoai", "theo điểm tích lũy"};
		thaotac = new String[] {"Thêm","Sửa","Xóa","Import Excel","Export Excel","In PDF"};
		hinhanh = new String[] {"./src/images/add_icon.png","./src/images/edit_icon.png","./src/images/remove_icon.png","./src/images/import_icon.png","./src/images/export_icon.png","./src/images/pdf_icon.png"};
		panel_north = new JPanel();
		option_so = new String[] {"","min-max","max-min"};
		option_kitu = new String[] {"","a-z","z-a"};
		clickedchinhsua = false;
		clickedxoa = false;
                clickedthem = false; 
		this.ds_kh_update_loi = new ArrayList<model_qlkh>();
		this.frame_thong_bao_update = null;
                this.frame_themkh = null;
		border_thaotac = BorderFactory.createMatteBorder(2, 0, 0, 0,Color.decode("#60A3BC") );
		 mkh = ten =sdt = diem = "";
		 diemmin = 0;
                diemmax = 1000000;
		
		panel_north.setLayout(new FlowLayout());
		panel_north.setPreferredSize(new Dimension(this.getPreferredSize().width,265));
		
		
		Font f1 = new Font(TOOL_TIP_TEXT_KEY, Font.BOLD, 15);
		
//	******************** label tieu de tim kiem ************************************ 
		
		jp[0] = new JPanel();jp[0].setPreferredSize(new Dimension(this.getPreferredSize().width-60,30));
		jp[0].setLayout(new FlowLayout(0,0,0));
		jl[0] = new JLabel("Tìm kiếm");jl[0].setFont(f1);

		
		jp[0].add(jl[0]);
		
		jp[1] = new JPanel();jp[1].setLayout(new FlowLayout(1,0,0));
		jp[1].setPreferredSize(new Dimension(this.getPreferredSize().width-60,80));
		jp[1].setBackground(Color.white);
		
		
		
		jp1_lr[0] = new JPanel();jp1_lr[0].setBackground(Color.white);
		jp1_lr[0].setPreferredSize(new Dimension(jp[1].getPreferredSize().width-150,80));
		jp1_lr[0].setLayout(new GridLayout(2,4));
		jp1_lr[0].setBorder(BorderFactory.createCompoundBorder(getBorder(), BorderFactory.createMatteBorder(0, 3, 0, 0, Color.decode("#60A3BC"))));
		for (int i = 0 ; i <4; i++ ) {
			jl[i] = new JLabel();
			
			jl1[i] = new JLabel(timkiem[i],JLabel.CENTER); 
			jp1_lr[0].add(jl1[i]);
			
			
		}
		
//		*********************   option tim kiem khach hang *********************
		
                    
////////////////////////////////////////////////// tMAKH //////////////////////////             
                
                
                tf= new JTextField[5];
                
                tf[0] = new JTextField();
                tf[0].setPreferredSize(new Dimension(150, 25));
                
               
                tf[0].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        mkh = tf[0].getText();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        mkh = tf[0].getText();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        mkh = tf[0].getText();
                              
                    }
                });
                
                
                ///////////////////////////////////////////// TEN KH ///////////////////////////////////////////
                tf[1] = new JTextField();
                tf[1].setPreferredSize(new Dimension(150, 25));
                
               
                  
                tf[1].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        ten = tf[1].getText();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        ten = tf[1].getText();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        ten = tf[1].getText();
                              
                    }
                });
                
        //////////////////////////////////////////////// SO DIEN THAOI /////////////////////////////////////////////
                tf[2]= new JTextField();
                        
                tf[2].setPreferredSize(new Dimension(150, 25));
                
                tf[2].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
			
			
		});
                
                tf[2].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        sdt = tf[2].getText();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        sdt = tf[2].getText();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        sdt = tf[2].getText();
                              
                    }
                });
                
                for (int  i = 0 ; i< 3; i++) {
			jp1[i] = new JPanel();
			jp1[i].setBackground(Color.white);
                        jp1[i].add(tf[i]);
		}
                
                
                
        //////////////////////////////////////////////////////////////// DIEM /////////////////////////////
        
            jp1[3] = new JPanel();
            jp1[3].setLayout(new FlowLayout());
            jp1[3].setBackground(Color.white);
            
            
            tf[3] = new JTextField();
            tf[3].setPreferredSize(new Dimension(90, 25));
             tf[3].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
			
			
		});
            tf[3].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        String t = tf[3].getText();
                        if (t.isEmpty()){
                            diemmin = 0;
                        } else {
                            diemmin = Integer.parseInt(t) ;
                        }
                        
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                       String t = tf[3].getText();
                        if (t.isEmpty()){
                            diemmin = 0;
                        } else {
                            diemmin = Integer.parseInt(t) ;
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        String t = tf[3].getText();
                        if (t.isEmpty()){
                            diemmin = 0;
                        } else {
                            diemmin = Integer.parseInt(t) ;
                        }
                              
                    }
                });
            
            
            
            
            tf[4] = new JTextField();
            tf[4].setPreferredSize(new Dimension(90, 25));
             tf[4].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
			
			
		});
             tf[4].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        String t = tf[4].getText();
                        if (t.isEmpty()){
                            diemmax = 1000000;
                        } else {
                            diemmax = Integer.parseInt(t) ;
                        }
                        
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                       String t = tf[4].getText();
                        if (t.isEmpty()){
                            diemmax = 1000000;
                        } else {
                            diemmax = Integer.parseInt(t) ;
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                       String t = tf[4].getText();
                        if (t.isEmpty()){
                            diemmax = 1000000;
                        } else {
                            diemmax = Integer.parseInt(t) ;
                        }
                              
                    }
                });
             
             
             
             
             
             
             
             jp1[3].add(tf[3]);
             JLabel den = new JLabel("đến");
            jp1[3].add(den);
            jp1[3].add(tf[4]);
                
		
		
		
		
		
		
		
		for (int  i = 0 ; i< 4; i++) {
			jp1_lr[0].add(jp1[i]);
		}
		
		jp1_lr[1] = new JPanel(); jp1_lr[1].setLayout(new FlowLayout(1,0,10));
		jp1_lr[1].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jp1_lr[1].setPreferredSize(new Dimension( jp[1].getPreferredSize().width -jp1_lr[0].getPreferredSize().width,80));
		jp1_lr[1].setBackground(Color.white);
		
		//***************************************** nút SEARCH và REFRESH**************************************************
		
		
		jl1_r[0] = new JLabel("Tìm kiếm",JLabel.CENTER);jl1_r[0].setBackground(Color.decode("#0A3D62"));jl1_r[0].setOpaque(true);
		jl1_r[0].setPreferredSize(new Dimension(jp1_lr[1].getPreferredSize().width*2/3,25));
		ImageIcon search = new ImageIcon("./src/images/search1.png");
		jl1_r[0].setIcon(search);
		jl1_r[0].setForeground(Color.white);
		jl1_r[1] = new JLabel("Làm mới",JLabel.CENTER);jl1_r[1].setBackground(Color.decode("#0A3D62"));jl1_r[1].setOpaque(true);
		jl1_r[1].setPreferredSize(new Dimension(jp1_lr[1].getPreferredSize().width*2/3,25));
		ImageIcon refresh = new ImageIcon("./src/images/refresh1.png");
		jl1_r[1].setIcon(refresh);
		jl1_r[1].setForeground(Color.white);
		
		jp1_lr[1].add(jl1_r[0]);
		jp1_lr[1].add(jl1_r[1]);
		
		jl1_r[1].addMouseListener(this);
		jl1_r[0].addMouseListener(this);
		
		
		
		jp[1].add(jp1_lr[0]);
		jp[1].add(jp1_lr[1]);
		
//		**************** chữ thao tác ở trên các chức năng ********************
		
		jp[2] = new JPanel();jp[2].setLayout(new FlowLayout(0,0,0));
		jp[2].setPreferredSize(new Dimension(this.getPreferredSize().width-60,25));
		jl[2] = new JLabel("Thao tác");
		jl[2].setFont(f1);
		jp[2].add(jl[2]);
		
		jp[3] = new JPanel();
		jp[3].setPreferredSize(new Dimension(this.getPreferredSize().width-40,100));
//		jp[3].setBackground(Color.pink);
		jp[3].setLayout(new FlowLayout(0,10,0));
		
//     ********************************** thao tác Thêm  ***********************************
		
		
			ImageIcon ttac_them = new ImageIcon(hinhanh[0]);
			jp3[0] = new JPanel();jp3[0].setPreferredSize(new Dimension(100,100));jp3[0].setLayout(new FlowLayout());
			jp3[0].setBackground(Color.white);
			jl3[0] = new JLabel(thaotac[0],JLabel.CENTER);jl3[0].setPreferredSize(new Dimension(100,50));
		
			jp3[0].setBorder(border_thaotac);
			jlha[0] = new JLabel("",JLabel.CENTER);jlha[0].setIcon(ttac_them);
			jlha[0].setPreferredSize(new Dimension(jp3[0].getPreferredSize().width,40));
			jp3[0].add(jl3[0]);
			jp3[0].add(jlha[0]);
			
			
			
			
			jlha[0].addMouseListener(this);
			jl3[0].addMouseListener(this);                                                                                                                                
			jp3[0].addMouseListener(this);
			
		
		
		//////////////////////////// nút sửa  ////////////////////////////////////////////////
		ImageIcon ttac_sua = new ImageIcon(hinhanh[1]);
		jp3[1] = new JPanel();jp3[1].setPreferredSize(new Dimension(100,100));jp3[1].setLayout(new FlowLayout());
		jp3[1].setBackground(Color.white);
		jl3[1] = new JLabel(thaotac[1],JLabel.CENTER);jl3[1].setPreferredSize(new Dimension(100,50));
	
		jp3[1].setBorder(border_thaotac);
		jlha[1] = new JLabel("",JLabel.CENTER);jlha[1].setIcon(ttac_sua);
		jlha[1].setPreferredSize(new Dimension(jp3[1].getPreferredSize().width,40));
		jp3[1].add(jl3[1]);
		jp3[1].add(jlha[1]);
		
		
		
		jlha[1].addMouseListener(this);
		jl3[1].addMouseListener(this);                                                                                                                                
		jp3[1].addMouseListener(this);
		
		//////////////////////////////////////// nút xóa ////////////////////////////////
		ImageIcon ttac_xoa = new ImageIcon(hinhanh[2]);
		jp3[2] = new JPanel();jp3[2].setPreferredSize(new Dimension(100,100));jp3[2].setLayout(new FlowLayout());
		jp3[2].setBackground(Color.white);
		jl3[2] = new JLabel(thaotac[2],JLabel.CENTER);jl3[2].setPreferredSize(new Dimension(100,50));
	
		jp3[2].setBorder(border_thaotac);
		jlha[2] = new JLabel("",JLabel.CENTER);jlha[2].setIcon(ttac_xoa);
		jlha[2].setPreferredSize(new Dimension(jp3[2].getPreferredSize().width,40));
		jp3[2].add(jl3[2]);
		jp3[2].add(jlha[2]);
		
		jlha[2].addMouseListener(this);
		jl3[2].addMouseListener(this);                                                                                                                                
		jp3[2].addMouseListener(this);
		
		/////////////////////////////// Import Excel ////////////////////////////////////////
                
                ImageIcon ttac_import = new ImageIcon(hinhanh[3]);
		jp3[3] = new JPanel();jp3[3].setPreferredSize(new Dimension(100,100));jp3[3].setLayout(new FlowLayout());
		jp3[3].setBackground(Color.white);
		jl3[3] = new JLabel(thaotac[3],JLabel.CENTER);jl3[3].setPreferredSize(new Dimension(100,50));
	
		jp3[3].setBorder(border_thaotac);
		jlha[3] = new JLabel("",JLabel.CENTER);jlha[3].setIcon(ttac_xoa);
		jlha[3].setPreferredSize(new Dimension(jp3[3].getPreferredSize().width,40));
		jp3[3].add(jl3[3]);
		jp3[3].add(jlha[3]);
		
		jlha[3].addMouseListener(this);
		jl3[3].addMouseListener(this);                                                                                                                                
		jp3[3].addMouseListener(this);
                //////////////////////////////////////////////// Export excel ////////////////////////////////////////////////
                
                ImageIcon ttac_export = new ImageIcon(hinhanh[4]);
		jp3[4] = new JPanel();jp3[4].setPreferredSize(new Dimension(100,100));jp3[4].setLayout(new FlowLayout());
		jp3[4].setBackground(Color.white);
		jl3[4] = new JLabel(thaotac[4],JLabel.CENTER);jl3[4].setPreferredSize(new Dimension(100,50));
	
		jp3[4].setBorder(border_thaotac);
		jlha[4] = new JLabel("",JLabel.CENTER);jlha[4].setIcon(ttac_xoa);
		jlha[4].setPreferredSize(new Dimension(jp3[4].getPreferredSize().width,40));
		jp3[4].add(jl3[4]);
		jp3[4].add(jlha[4]);
		
		jlha[4].addMouseListener(this);
		jl3[4].addMouseListener(this);                                                                                                                                
		jp3[4].addMouseListener(this);
                ///////////////////////////////////////// In PDF //////////////////////////////////////////////////
                
                
                ImageIcon ttac_inpdf = new ImageIcon(hinhanh[5]);
		jp3[5] = new JPanel();jp3[5].setPreferredSize(new Dimension(100,100));jp3[5].setLayout(new FlowLayout());
		jp3[5].setBackground(Color.white);
		jl3[5] = new JLabel(thaotac[5],JLabel.CENTER);jl3[5].setPreferredSize(new Dimension(100,50));
	
		jp3[5].setBorder(border_thaotac);
		jlha[5] = new JLabel("",JLabel.CENTER);jlha[5].setIcon(ttac_xoa);
		jlha[5].setPreferredSize(new Dimension(jp3[5].getPreferredSize().width,40));
		jp3[5].add(jl3[5]);
		jp3[5].add(jlha[5]);
		
		jlha[5].addMouseListener(this);
		jl3[5].addMouseListener(this);                                                                                                                                
		jp3[5].addMouseListener(this);
		///////////////////////////////////////////////////// hiện danh sách ///////////////////////////////////
	
		jp[4] = new JPanel();jp[4].setLayout(new BorderLayout());	
		jp[4].setBackground(Color.white);
		
		jp[4].add(panel_bang_danh_sach_kh());
                
		
		//////////////////////////////////// add các hành động thao tác theo mã quyền /////////////////////////////////////
		
		for (String t : BUS_qlkh.select_hanhdong_qlkh(taiKhoanDTO.getMaQuyen())) {
			System.out.println(t);
			
			switch (t) {
			case "Thêm":
				jp[3].add(jp3[0]);
				break;
			case "Sửa" :
				jp[3].add(jp3[1]);
				break;
			case "Xóa" :
				jp[3].add(jp3[2]);
				break;
                        case "Import Excel"   : 
                                jp[3].add(jp3[3]);
                            break;
                        case "Export Excel" :
                                jp[3].add(jp3[4]);
                            break;
                        case "In PDF" :
                                jp[3].add(jp3[5]);
                            break;
			default:
				break;
			}
		}
		
		
	
		
		
		
		panel_north.add(jp[0]);
		panel_north.add(jp[1]);
		panel_north.add(jp[2]);
		panel_north.add(jp[3]);
		this.add(panel_north,BorderLayout.NORTH);
		this.add(jp[4],BorderLayout.CENTER);

		
		
	}
	public JPanel panel_bang_danh_sach_kh() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.setBackground(Color.white);
//		panel_bang_dskh = new panel_bang_dskh(this.getPreferredSize().width, this.getPreferredSize().height, DAO_qlks.getinstance().select_all());
		panel_bang_dskh = new panel_bang_dskh(this.getPreferredSize().width, this.getPreferredSize().height, BUS_qlkh.getlist());

		p.add(panel_bang_dskh);
		return p;
		
	}
	
	public void refresh_bang_dskh() {
		 jp[4].removeAll();
		 jp[4].repaint();
		 this.BUS_qlkh = new BUS_qlkh();
		 jp[4].add(panel_bang_danh_sach_kh());
		 jp[4].revalidate();
		 
	}
	
	public ArrayList<model_qlkh>  ds_kh_update() {
		return this.panel_bang_dskh.ds_kh_update();
	}
	
	public ArrayList<model_qlkh> ds_kh_xoa(){
		
		return this.panel_bang_dskh.ds_kh_xoa();
	}

	

	public Boolean ktra_tt_after_update() {
		for (model_qlkh h : this.panel_bang_dskh.ds_kh_update()) {
			if (!this.kiem_tra_tt_update(h)) {
//				JOptionPane.showMessageDialog(this, "Thông tin của khách hàng có mã " + h.getMakh() + " không hợp lệ" );
				return false;
			}
		}
		return true;
		
	}
	public void xoa_kh() {
		for (model_qlkh h : this.ds_kh_xoa()) {
//			DAO_qlks.getinstance().delete(h);
			BUS_qlkh.delete(h);
		}
	}
	
	public void return_true_clicked_chinhsua() {
		clickedchinhsua = true;
	}
	
	public void return_true_clicked_xoa() {
		clickedxoa = true;
	}
        
        public void return_true_clicked_them(){
            clickedthem = true;
        }
	
	public void return_false_clicked_chinhsua() {
		clickedchinhsua = false;
	}
	
	public void return_false_clicked_xoa() {
		clickedxoa = false;
	}
        
        public void return_false_clicked_them(){
            this.clickedthem = false;
        }
        
        public void return_null_framethongbao(){
            this.frame_thong_bao_update = null;
        }
        
        public void return_null_frame_themkh(){
            this.frame_themkh = null;
        }
        
        public frame_thong_bao_update return_frame_thong_bao(){
            return this.frame_thong_bao_update;
        }
        
        public frame_themkh return_frame_themkh(){
            return this.frame_themkh;
        }
        
	
	public void dinh_dang() {
		if (clickedchinhsua) {
			jp3[1].setBackground(Color.decode("#356a7e"));
			this.panel_bang_dskh.panel_bang_dskh_update();
			jl3[1].setText("Lưu");
			jlha[1].setIcon(new ImageIcon("./src/images/save.png"));
		} else if (!clickedchinhsua) {
			jp3[1].setBorder(border_thaotac);
			jp3[1].setBackground(Color.white);
			this.update_ve_che_do_xem();
			jl3[1].setText("Sửa");
			jlha[1].setIcon(new ImageIcon(hinhanh[1]));
		}
		if (clickedxoa) {
			this.panel_bang_dskh.return_true_clicked_xoa();
			jp3[2].setBackground(Color.decode("#356a7e"));
			jl3[2].setText("Xác nhận xóa");
		} else if (!clickedxoa) {
			jp3[2].setBorder(border_thaotac);
			jp3[2].setBackground(Color.white);
			jl3[2].setText("Xóa");
		}
	}
	public void update_ve_che_do_xem() {
		this.panel_bang_dskh.update_ve_che_do_xem();
	}
	public boolean kiem_tra_rong(String t) {
		if (t== null||t.trim().isEmpty()) {
			return true;
			}
		else return false;
	}
	public boolean kiem_tra_sdt(String t) {
		for (int i = 0; i < t.length();i++) {
			if (!Character.isDigit(t.charAt(i))){
				return false;
			}
		}
		return true;
	}

	public boolean kiem_tra_sdt_trung(String t) {
		for (model_qlkh h : BUS_qlkh.getlist()) {
			if(t.equals(h.getSdt())) {
				return true;
			}
		}
		return false;
	}
	public boolean kiem_tra_hop_le(String ten, String sdt) {
			if (!kiem_tra_rong(sdt)&&!kiem_tra_rong(ten)&& kiem_tra_sdt(sdt)&& !kiem_tra_sdt_trung(sdt) &&(sdt.length() == 10 || sdt.length() == 11) ) {
				return true;
			}
			return false;
	}
	public Boolean kiem_tra_tt_update(model_qlkh h) {
			String loi;
			String sdt = this.BUS_qlkh.selecby_id(h.getMakh()).getSdt();
		if (kiem_tra_rong(h.getTen())) {
			System.out.println("Bạn chưa điền tên khách hàng có mã "+ h.getMakh() );
			loi = "Bạn chưa điền tên khách hàng có mã "+ h.getMakh();
			JOptionPane.showMessageDialog(this, loi);
			return false;
		} else if(kiem_tra_rong(h.getSdt())) {
			System.out.println("Bạn chưa điền số điện thoại của khách hàng có mã " + h.getMakh());
			loi = "Bạn chưa điền số điện thoại của khách hàng có mã " + h.getMakh();
			JOptionPane.showMessageDialog(this, loi);
			return false;
		} else if (!kiem_tra_sdt(h.getSdt()) || !(h.getSdt().trim().length() == 10 || h.getSdt().trim().length() == 11 )) {
			System.out.println("Số điện thoại của khách hàng có mã " + h.getMakh() + " không hợp lệ");
			loi = "Số điện thoại của khách hàng có mã " + h.getMakh() + " không hợp lệ";
			JOptionPane.showMessageDialog(this, loi);
			return false;
		}else if (kiem_tra_sdt_trung(h.getSdt()) && !h.getSdt().equals(this.BUS_qlkh.selecby_id(h.getMakh()).getSdt()) ) {
			
			System.out.println("Số điện thoại của khách hàng có mã " + h.getMakh() + " đã được sử dụng");
			loi = "Số điện thoại của khách hàng có mã " + h.getMakh() + " đã được sử dụng";
			JOptionPane.showMessageDialog(this, loi);
			return false;
		}
		else if (h.getDiem() > 1000000) {
			loi = "Điểm của khách hàng số " +h.getMakh() + " không vượt quá 1000000000";
			JOptionPane.showMessageDialog(this, loi);
			return false ;
		}
		
		return true;
	}
	public BUS_qlkh getBus_qlkh() {
		return this.BUS_qlkh;
	}
	public void search() {
                    
                    System.out.println(diemmin + " va " + diemmax);
			BUS_qlkh.search(mkh, ten, sdt, diemmin,diemmax);
			ArrayList<model_qlkh> ds1 = BUS_qlkh.getlist();
			
			this.panel_bang_dskh = new panel_bang_dskh(this.getPreferredSize().width, this.getPreferredSize().height, ds1);
			jp[4].removeAll();
			jp[4].add(panel_bang_dskh);
			jp[4].repaint();
			jp[4].revalidate();
	}
	@Override
	
	public void mouseClicked(MouseEvent e) {
		
		//************************** nút thêm khách hàng***********************
		
		
		if (e.getSource() == jlha[0] || e.getSource() == jl3[0] || e.getSource() == jp3[0]) {		
			
			if (clickedchinhsua) {
                            if (this.frame_thong_bao_update != null){
                                this.frame_thong_bao_update.toFront();
                            } else {
                                String t = "Bạn có muốn hủy bỏ chỉnh sửa";
				this.frame_thong_bao_update  = new frame_thong_bao_update(400, 350, t, this);
                            }
				
				
			}
			if (clickedxoa) {
                            if (this.frame_thong_bao_update != null){
                                this.frame_thong_bao_update.toFront();
                            } else {
                                String t = "Bạn có muốn hủy bỏ xóa";
				this.frame_thong_bao_update  = new frame_thong_bao_update(400, 350, t, this);
                            }
				
			}
			if (!clickedchinhsua && !clickedxoa){
                            if (this.frame_themkh != null){
                                this.frame_themkh.toFront();
                            } else {
                                frame_themkh = new frame_themkh(this);
                            }
				
				
			} 
			
		}
		
		//************************ nút sửa khách hàng**************************
		
		if (e.getSource() == jlha[1] || e.getSource() == jl3[1] || e.getSource() == jp3[1]) {
			if(!clickedchinhsua) {
				if (!clickedxoa) {
					clickedchinhsua = true;
					dinh_dang();
				}
				else if (clickedxoa) {
                                    if (this.frame_thong_bao_update != null){
                                        this.frame_thong_bao_update.toFront();
                                    } else {
                                        String t = "Bạn có muốn hủy bỏ hoạt động xóa và chuyển sang hoạt động chỉnh sửa";
					this.frame_thong_bao_update  = new frame_thong_bao_update(400, 350, t, this);
                                    }
					
				}
				
				
			}
			else if(clickedchinhsua) {
				if (this.panel_bang_dskh.sosanh_update()) {
					clickedchinhsua = false;
					dinh_dang();
				

				}
				
				else if (!this.panel_bang_dskh.sosanh_update()) {
					int i = 0;
					
					for (model_qlkh h : this.panel_bang_dskh.ds_kh_update()){
						i++;
					}
                                        if (this.frame_thong_bao_update != null){
                                            this.frame_thong_bao_update.toFront();
                                        } else {
                                            String t = "Bạn có muốn thay đổi thông tin của " + i + " khách hàng" ;
					this.frame_thong_bao_update = new frame_thong_bao_update(400, 350,t,this);
                                        }
					
					
				}
				
				
			}
		}
		// ************************************ nút xóa 	********************************************
		
		if (e.getSource() == jlha[2] || e.getSource() == jl3[2] || e.getSource() == jp3[2]) {
			
			if (!clickedxoa) {
				if (clickedchinhsua) {
                                    if (this.frame_thong_bao_update != null){
                                        this.frame_thong_bao_update.toFront();
                                    } else {
                                        String t = "Bạn có muốn hủy bỏ hoạt động chỉnh sửa và chuyển sang hoạt động xóa";
					this.frame_thong_bao_update  = new frame_thong_bao_update(400, 350, t, this);
                                    }
					
				}
				else if (!clickedchinhsua) {	
					clickedxoa = true;
					JOptionPane.showMessageDialog(this, "Nhấp vào để chọn những khách hàng bạn muốn xóa");
					dinh_dang();
				}
				
				
				
			}
			else if (clickedxoa) {
				if (this.panel_bang_dskh.xac_nhan()) {
//					this.panel_bang_dskh.return_false_clicked_xoa();
					
					int i = 0;
					
					for (model_qlkh h  : this.panel_bang_dskh.ds_kh_xoa()) {
						i++;
					}
                                        if (this.frame_thong_bao_update != null){
                                            this.frame_thong_bao_update.toFront();
                                        } else {
                                            String t = "Xác nhận xóa "+ i + " khách hàng";
                                            frame_thong_bao_update = new frame_thong_bao_update(400, 350, t, this);
                                        }
					
								
				}
				if (!this.panel_bang_dskh.xac_nhan()) {
					clickedxoa = false;
					dinh_dang();
				}
				
			}
			
			
		}
//		******************************************nút REFRESH ********************************************
		if (e.getSource() == jl1_r[1]) {
			if (clickedchinhsua) {
				if (!this.panel_bang_dskh.sosanh_update()) {
                                    if (this.frame_thong_bao_update != null){
                                        this.frame_thong_bao_update.toFront();
                                    } else {
                                        String t = "Bạn có muốn hủy bỏ các thay đổi";					
					this.frame_thong_bao_update = new frame_thong_bao_update(400, 350, t, this);
                                    }
					
				} 
				if (this.panel_bang_dskh.sosanh_update()) {
					this.BUS_qlkh = new BUS_qlkh();
					clickedchinhsua = false;
					refresh_bang_dskh();
					this.dinh_dang();
				}
				
			}
			if (clickedxoa) {
                            if (this.frame_thong_bao_update != null){
                                this.frame_thong_bao_update.toFront();
                            } else {
                                String t = "Bạn có muốn hủy bỏ các thay đổi";
				this.frame_thong_bao_update  = new frame_thong_bao_update(400, 350, t, this);
                            }
				
			}
			if (!clickedchinhsua) {
				this.BUS_qlkh = new BUS_qlkh();
				refresh_bang_dskh();
			}
			 
		
				
			
			
			
			// nút SEARCH
		}
		if (e.getSource() == jl1_r[0]) {
			if(!clickedchinhsua && !clickedxoa) {
				this.search();
			
			} else if (clickedchinhsua || clickedxoa) {
                            if (this.frame_thong_bao_update != null){
                                this.frame_thong_bao_update.toFront();
                            } else {
                                String t = "Bạn có muốn hủy những thay đổi";
				this.frame_thong_bao_update  = new frame_thong_bao_update(400, 350, t, this);
                            }
				
			}
				
			
			
			
			
		}
		System.out.println();
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
		
		if(e.getSource() == jlha[0] || e.getSource() == jl3[0] || e.getSource() == jp3[0] ) {
			jp3[0].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
		}
		if(e.getSource() == jlha[1] || e.getSource() == jl3[1] || e.getSource() == jp3[1]) {
            	jp3[1].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
			
                }
		if(e.getSource() == jlha[2] || e.getSource() == jl3[2] || e.getSource() == jp3[2] ) {
			jp3[2].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
		}
                if(e.getSource() == jlha[3] || e.getSource() == jl3[3] || e.getSource() == jp3[3] ) {
                    
			jp3[3].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
		}
                if(e.getSource() == jlha[4] || e.getSource() == jl3[4] || e.getSource() == jp3[4] ) {
			jp3[4].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
		}
                
                if(e.getSource() == jlha[5] || e.getSource() == jl3[5] || e.getSource() == jp3[5] ) {
			jp3[5].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
		}
                
		
		if (e.getSource() == jl1_r[0]) {
			jl1_r[0].setBackground(Color.decode("#60A3BC"));
		}
		if (e.getSource() == jl1_r[1]) {
			jl1_r[1].setBackground(Color.decode("#60A3BC"));
			
		}
				
	}

	@Override
	
	public void mouseExited(MouseEvent e) {
//		 nút thêm
		if(e.getSource() == jlha[0] || e.getSource() == jl3[0] || e.getSource() == jp3[0] ) {
			jp3[0].setBorder(border_thaotac);
		}
//		nút sửa
		if(e.getSource() == jlha[1] || e.getSource() == jl3[1] || e.getSource() == jp3[1] ) {
			jp3[1].setBorder(border_thaotac);
		}
//		nút xóa
		if(e.getSource() == jlha[2] || e.getSource() == jl3[2] || e.getSource() == jp3[2] ) {
			jp3[2].setBorder(border_thaotac);
		}
//		 nút import 
                   if(e.getSource() == jlha[3] || e.getSource() == jl3[3] || e.getSource() == jp3[3] ) {
			jp3[3].setBorder(border_thaotac);
		}
//              nút export 
                if(e.getSource() == jlha[4] || e.getSource() == jl3[4] || e.getSource() == jp3[4] ) {
			jp3[4].setBorder(border_thaotac);
		}
//              nút in pdf
                if(e.getSource() == jlha[5] || e.getSource() == jl3[5] || e.getSource() == jp3[5] ) {
			jp3[5].setBorder(border_thaotac);
		}
                
		for (int  i = 0; i< 2; i++) {
			if (e.getSource() == jl1_r[i]) {
				jl1_r[i].setBackground(Color.decode("#0A3D62"));
				
			}
		}
		
	}
 

}
