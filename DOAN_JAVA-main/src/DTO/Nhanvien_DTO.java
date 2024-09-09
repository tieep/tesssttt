
package DTO;

public class Nhanvien_DTO {
    private String manv, tennv, chucvu, diachi, email;
    private int sdt,trangthai;

    public Nhanvien_DTO() {
    }

    public Nhanvien_DTO(String manv, String tennv, String chucvu, int sdt, String diachi, String email,int tt) {
        this.manv = manv;
        this.tennv = tennv;
        this.chucvu = chucvu;
        this.diachi = diachi;
        this.email = email;
        this.sdt = sdt;
        trangthai = tt;
    }

    public String getManv() {
        return manv;
    }

    public String getTennv() {
        return tennv;
    }

    public String getChucvu() {
        return chucvu;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getEmail() {
        return email;
    }

    public int getSdt() {
        return sdt;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
    
}
