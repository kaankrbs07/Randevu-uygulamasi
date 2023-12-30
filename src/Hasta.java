
import java.io.Serializable;

public class Hasta extends user implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tcno;
	private String sifre;

	public Hasta(String tcNo, String sifre) {
		this.tcno = tcNo;
		this.sifre = sifre;
	}

	public String getTcNo() {
		return tcno;
	}

	public void setTcNo(String tcNo) {
		this.tcno = tcNo;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

}