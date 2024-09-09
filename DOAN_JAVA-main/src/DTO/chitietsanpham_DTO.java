package DTO;

public class chitietsanpham_DTO  {
	private String MASP;
	private String MASIZE;
	private int soluong;

	public chitietsanpham_DTO() {
		
	}
	public chitietsanpham_DTO(String MASP,String MASIZE, int soluong) {
		this.MASP = MASP;
		this.MASIZE = MASIZE;
		this.soluong = soluong;
		
	}

	@Override
	public String toString() {
		return "chitietsanpham_DTO [MASP=" + MASP + ", MASIZE=" + MASIZE + ", soluong=" + soluong + "]";
	}

	public String getMASP() {
		return MASP;
	}

	public void setMASP(String mASP) {
		MASP = mASP;
	}

	public String getMASIZE() {
		return MASIZE;
	}

	public void setMASIZE(String mASIZE) {
		MASIZE = mASIZE;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
}
