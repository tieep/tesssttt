package DTO;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class phieunhap_DTO {
	private String MAPN;
	private String MANV;
	private LocalDate ngay;
	private double tongtien;
	private String MANCC;
	
	public phieunhap_DTO(String MAPN,String MANV,double tongtien, String MANCC) {
		this.MAPN = MAPN;
		this.MANV = MANV;
		this.ngay = LocalDate.now();
		this.tongtien = tongtien;
		this.MANCC = MANCC;
		
	}
	
	
	public phieunhap_DTO(String MAPN,String MANV,LocalDate ngay,double tongtien, String MANCC) {
		this.MAPN = MAPN;
		this.MANV = MANV;
		this.ngay = ngay;
		this.tongtien = tongtien;
		this.MANCC = MANCC;	
	}


	public String getMAPN() {
		return MAPN;
	}



	public void setMAPN(String mAPN) {
		MAPN = mAPN;
	}



	public String getMANV() {
		return MANV;
	}



	public void setMANV(String mANV) {
		MANV = mANV;
	}



	public LocalDate getNgay() {
		return ngay;
	}



	public void setNgay(LocalDate ngay) {
		this.ngay = ngay;
	}



	public double getTongtien() {
		return tongtien;
	}



	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}



	public String getMANCC() {
		return MANCC;
	}



	public void setMANCC(String mANCC) {
		MANCC = mANCC;
	}

	
	@Override
	public String toString() {
		return "phieunhap_DTO [MAPN=" + MAPN + ", MANV=" + MANV + ", ngay=" + ngay + ", tongtien=" + tongtien
				+ ", MANCC=" + MANCC + "]";
	}
	public static void main(String[] args) {
		System.out.println(LocalDate.now());
	}
}
