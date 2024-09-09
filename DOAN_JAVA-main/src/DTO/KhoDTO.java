package DTO;

public class KhoDTO {
    private String maSP, loai, tenSP, size;
    private int slTon, tonDK, slNhap, slXuat;

    public KhoDTO() {
    }

    public KhoDTO(String maSP, String loai, String tenSP, String size, int slTon, int slNhap, int slXuat) {
        this.maSP = maSP;
        this.loai = loai;
        this.tenSP = tenSP;
        this.size = size;
        this.slTon = slTon;
        this.slNhap = slNhap;
        this.slXuat = slXuat;
        
        this.tonDK = slTon - slNhap + slXuat;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSlTon() {
        return slTon;
    }

    public void setSlTon(int slTon) {
        this.slTon = slTon;
    }

    public int getTonDK() {
        return tonDK;
    }

    public void setTonDK(int tonDT) {
        this.tonDK = tonDT;
    }

    public int getSlNhap() {
        return slNhap;
    }

    public void setSlNhap(int slNhap) {
        this.slNhap = slNhap;
    }

    public int getSlXuat() {
        return slXuat;
    }

    public void setSlXuat(int slXuat) {
        this.slXuat = slXuat;
    }
    

}