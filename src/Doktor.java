import java.io.Serializable;

public class Doktor extends user implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tcNo;
	private String sifre;

	public Doktor(String tcNo, String sifre) {
		this.tcNo = tcNo;
		this.sifre = sifre;
	}

	public String getTcNo() {
		return tcNo;
	}

	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

}
