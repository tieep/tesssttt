package DTO;


public class TaiKhoanDTO {
    private String maNV,username, password, ngayTao, maQuyen;
    private int state;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String maNV, String username, String password, String ngayTao, String maQuyen, int state) {
        this.maNV = maNV;
        this.username = username;
        this.password = password;
        this.ngayTao = ngayTao;
        this.maQuyen = maQuyen;
        this.state = state;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
