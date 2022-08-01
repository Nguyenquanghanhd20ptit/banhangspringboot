package laptrinh.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bills")
@Data
public class bills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  int id;
	private String hoten;
	private String email;
	private String diachi;
	private String sdt;
	private double totalprice;
	private int quanty;
	private String ghichu;
	
	@OneToMany(mappedBy = "bills",fetch = FetchType.LAZY)
	private List<billdetail> billdetails;
	
	public bills(String hoten, String email, String diachi, String sdt, double totalprice, int quanty, String ghichu) {
		super();
		this.hoten = hoten;
		this.email = email;
		this.diachi = diachi;
		this.sdt = sdt;
		this.totalprice = totalprice;
		this.quanty = quanty;
		this.ghichu = ghichu;
	}
	public bills() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public int getQuanty() {
		return quanty;
	}
	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}
	public String getGhichu() {
		return ghichu;
	}
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
	public List<billdetail> getBilldetails() {
		return billdetails;
	}
	public void setBilldetails(List<billdetail> billdetails) {
		this.billdetails = billdetails;
	}
	
	
}
