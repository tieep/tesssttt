package GUI;



import BUS.BUS_qlkh;
import BUS.ChitietHD_BUS;
import BUS.Hoadon_BUS;
import BUS.Nhanvien_BUS;
import BUS.SanPhamBUS;
import BUS.SizeBUS;
import DTO.ChitietHD_DTO;
import DTO.Hoadon_DTO;
import DTO.Nhanvien_DTO;
import DTO.SanPhamDTO;
import DTO.SizeDTO;
import DTO.model_qlkh;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.pdfa.PdfADocument;
import com.itextpdf.kernel.geom.Rectangle;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import static com.itextpdf.kernel.pdf.PdfName.BaseFont;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.styledxmlparser.jsoup.nodes.Element;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class inPDF {
    private Hoadon_DTO hdDTO;
    private ArrayList<ChitietHD_DTO> cthdDTO;
    private model_qlkh kh;
    private Nhanvien_DTO nvDTO;
    private String MAHD;
    private PdfFont font;
    public inPDF(String MAHD) throws SQLException, FileNotFoundException, IOException{
        this.MAHD = MAHD;
        getData(MAHD);
        init();
    }
    private void getData(String MAHD) throws SQLException{
         Hoadon_BUS hdBUS = new Hoadon_BUS();
        ChitietHD_BUS cthsBUS = new ChitietHD_BUS(MAHD);
        BUS_qlkh khBUS = new BUS_qlkh();
        Nhanvien_BUS nvBUS = new Nhanvien_BUS();
        SanPhamBUS spBUS = new SanPhamBUS();
        //lay ra hhoa don dto
         ArrayList<Hoadon_DTO> listhdDTO = hdBUS.dshoadon;
         for(Hoadon_DTO h: listhdDTO){
             System.out.println("Hd xet "+h.getMaHD()+" hd hien tai "+MAHD);
             if(h.getMaHD().equals(MAHD)){
                  hdDTO = h;
                  break;
             }
                
         }
         
         //lay ra chi tiet oa don dto
         cthdDTO = cthsBUS.getList();
         
         //lay ra khach hang dto
//         kh = new model_qlkh(3, "Oanh le", "123456789", 230);
//       
         for(model_qlkh k: khBUS.getlist()){
           
             if(k.getMakh() ==  hdDTO.getMaKH())
                 kh = k;
         }
         
         //lay ra nhan vien
         System.out.println("so luong nv"+nvBUS.listnv.size());
         for(Nhanvien_DTO n: nvBUS.listnv){
            
             if(n.getManv().equals(hdDTO.getMaNV()))
                 nvDTO = n;
         }
         
         System.out.println("MAKH"+hdDTO.getMaKH());
          System.out.println("Ten kahch hang" +kh.getTen());
           System.out.println("Ten nhan vien"+nvDTO.getTennv());
           for(ChitietHD_DTO c : cthdDTO){
               System.out.println("TChi tiet: "+c.getMaSP()+" "+c.getSl());
           }
           
    }
    private void init() throws FileNotFoundException, IOException {
        String path=MAHD+".pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        
        PageSize customPageSize = new PageSize(200, PageSize.A6.getHeight());
        pdfDoc.setDefaultPageSize(customPageSize);
        
        Document doc = new Document(pdfDoc);
        
        Paragraph nameStore = new Paragraph(new Text(Cacthuoctinh_phuongthuc_chung.storeName.toUpperCase()).setBold());
        Paragraph headerHD = new Paragraph(new Text("Phiếu thanh toán").setBold());
        Paragraph labelNgay = new Paragraph();
        Paragraph labelMAHD = new Paragraph();
        Paragraph labelNV = new Paragraph();
        Paragraph labelKH = new Paragraph();
        Paragraph labelDTL = new Paragraph("Điểm tích lũy");
        Paragraph labelDTLSM = new Paragraph("Điểm sau mua hàng");
        Paragraph labelThanks = new Paragraph("Xin cảm ơn quý khách!");
        Paragraph labelTTSP = new Paragraph();
        Paragraph labelTOTAL = new Paragraph();
        Paragraph labelGG = new Paragraph();
         FontProgram fontProgram = FontProgramFactory.createFont("./Tahoma 400.ttf");
         font = PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H, true);
        
        doc.setMargins(20, 8, 0, 8);// can le cho trang pdf
        
        
        //layout tên cửa hàng
       // nameStore.setFontColor(ColorConstants.RED);
        nameStore.setTextAlignment(TextAlignment.CENTER); 
        nameStore.setFont(font);
        nameStore.setFontSize(12);
   //   nameStore.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        doc.add(nameStore);
        
        
        //layout hearder hóa đơn
        headerHD.setTextAlignment(TextAlignment.CENTER); 
        headerHD.setFontSize(8);
              headerHD.setFont(font);
         doc.add(headerHD);
         
         //layout số hóa đơn
        labelMAHD.add("Số hóa đơn: ");
        labelMAHD.add(hdDTO.getMaHD());
        labelMAHD.setFontSize(6);
          labelMAHD.setFont(font);
        doc.add(labelMAHD);
         
         //layout ngay hóa đơn
        
         labelNgay.add("Ngày: ");
          labelNgay.add(hdDTO.getNgayHD()+" "+hdDTO.getThoigian());
        labelNgay.setFontSize(6);
          labelNgay.setMarginTop(0f);
        doc.add(labelNgay);
       
        
        labelNV.add("Nhân viên: ");
        labelNV.add(nvDTO.getManv()+" - "+nvDTO.getTennv());
        labelNV.setMarginTop(0f);
        labelNV.setFont(font);
        labelNV.setFontSize(6);
        doc.add(labelNV);
         
        
       
        labelKH.add("Khách hàng: ");
        labelKH.add(kh.getMakh()+" - "+kh.getTen());
        labelKH.setFont(font);
        labelKH.setMarginTop(0f);
        labelKH.setMarginBottom(10f);
        labelKH.setFontSize(6);
        doc.add(labelKH);
        
        
        String []headerTable={"Tên sản phẩm","Size","SL","Đơn giá","Thành tiền"};
        float []colWidth={80,20,20,30,35};
        Table chitietsanpham = new Table(colWidth);
        for(String i : headerTable){
            Paragraph title = new Paragraph(new Text(i).setBold());
            title.setFontSize(5);
             title.setFont(font);
            title.setTextAlignment(TextAlignment.CENTER); 
            chitietsanpham.addCell(title);
        }
        
        
        SanPhamBUS spBUS = new SanPhamBUS();
        for(ChitietHD_DTO c : cthdDTO){
            
             Paragraph infor = new Paragraph((spBUS.select_by_id(c.getMaSP()).getTenSP()));
                infor.setFontSize(4);
                infor.setFont(font);
            infor.setTextAlignment(TextAlignment.CENTER); 
            chitietsanpham.addCell(infor);
            
            Paragraph infor0=new Paragraph(c.getMaSize()) ;
            infor0.setFontSize(4);
            infor0.setTextAlignment(TextAlignment.CENTER); 
            chitietsanpham.addCell(infor0);
            
              Paragraph infor1 = new Paragraph(c.getSl()+"");
                infor1.setFontSize(4);
            infor1.setTextAlignment(TextAlignment.CENTER); 
            chitietsanpham.addCell(infor1);
            
            Paragraph infor2 = new Paragraph((int)c.getGia()+"");
                infor2.setFontSize(4);
            infor2.setTextAlignment(TextAlignment.CENTER); 
            chitietsanpham.addCell(infor2);
            
            
              Paragraph infor3 = new Paragraph((int)c.getTt()+"");
                infor3.setFontSize(4);
            infor3.setTextAlignment(TextAlignment.CENTER); 
            chitietsanpham.addCell(infor3);
            
        }
        
        doc.add(chitietsanpham);
        
        
        labelTOTAL.add(new Text("Tổng cộng ").setBold());
        labelTOTAL.setFont(font);
        labelTOTAL.setFontSize(6);
        labelTOTAL.setMarginTop(10f);
        labelTOTAL.add((hdDTO.getTongTien()+hdDTO.getGiamgia())+"");
        labelTOTAL.setMarginBottom(0);
        doc.add(labelTOTAL);
        
        
         labelGG.add(new Text("Tiền giảm giá ").setBold());
         labelGG.setFont(font);
        labelGG.setFontSize(6);
        labelGG.add((hdDTO.getGiamgia())+"");
         labelGG.setMarginBottom(0);
        doc.add(labelGG);
        
        
        
                labelTTSP.add(new Text("Thành tiền ").setBold());
        labelTTSP.setFontSize(6);
        labelTTSP.setFont(font);
        labelTTSP.add((hdDTO.getTongTien())+"");
         labelTTSP.setMarginBottom(0);
        doc.add(labelTTSP);
        
        
         
        
   
labelThanks.setFont(font);
labelThanks.setMarginTop(20);
labelThanks.setFontSize(8);
        labelThanks.setTextAlignment(TextAlignment.CENTER);
        doc.add(labelThanks);
        doc.close();
       
         
         
         
    }
    public static void main(String[] args) throws FileNotFoundException, SQLException, IOException {
        inPDF in= new inPDF("HD4");
        
    }
}