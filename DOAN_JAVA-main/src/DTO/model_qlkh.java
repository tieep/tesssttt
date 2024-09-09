package DTO;

import BUS.BUS_qlkh;
import DAO.DAO_qlks;

public class model_qlkh {
	private int makh;
	private String ten;
	private String sdt;

	private int diem;
	
	public model_qlkh(String ten, String sdt) {
		this.ten = ten;
		this.sdt = sdt;
		this.diem = 0;

	}
	public model_qlkh(int makh, String ten,String sdt,int diem) {
		this.makh = makh;
		this.ten = ten;
		this.sdt = sdt;
		this.diem = diem;
	}
        public model_qlkh(){
            
        }
	public int getMakh() {
		return makh;
	}

	public void setMakh(int makh) {
		this.makh = makh;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public int getDiem() {
		return diem;
	}

	public void setDiem(int diem) {
		this.diem = diem;
	}

	@Override
	public String toString() {
		return "model_qlkh [makh=" + makh + ", ten=" + ten + ", sdt=" + sdt + ", diem=" + diem + "]";
	}
	
        public void copyFrom(model_qlkh k){
            this.makh = k.getMakh();
            this.ten = k.getTen();
            this.sdt = k.getSdt();
            this.diem = k.getDiem();
        }
	public static void main(String[] args) {
		model_qlkh k1 = new model_qlkh("long", "012");
		System.out.println(k1.toString());
	}
}
