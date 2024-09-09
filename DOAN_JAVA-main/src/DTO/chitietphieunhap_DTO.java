package DTO;

public class chitietphieunhap_DTO {
	private String mapn;
	private String masp;
	private int soluong;
	private double gianhap;
	private double thanhtien;
	private String masize;
	public chitietphieunhap_DTO(String mapn,String masp,int soluong,double gianhap,double thanhtien,String masize) {
		this.mapn = mapn;
		this.masp = masp;
		this.soluong = soluong;
		this.gianhap = gianhap;
		this.thanhtien = thanhtien;
		this.masize = masize;
	}
	public String getMapn() {
		return mapn;
	}
	public void setMapn(String mapn) {
		this.mapn = mapn;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public double getGianhap() {
		return gianhap;
	}
	public void setGianhap(double gianhap) {
		this.gianhap = gianhap;
	}
	public double getThanhtien() {
		return thanhtien;
	}
	public void setThanhtien(double thanhtien) {
		this.thanhtien = thanhtien;
	}
	public String getMasize() {
		return masize;
	}
	public void setMasize(String masize) {
		this.masize = masize;
	}
	@Override
	public String toString() {
		return "chitietphieunhap_DTO [mapn=" + mapn + ", masp=" + masp + ", soluong=" + soluong + ", gianhap=" + gianhap
				+ ", thanhtien=" + thanhtien + ", masize=" + masize + "]";
	}
	
	
}
