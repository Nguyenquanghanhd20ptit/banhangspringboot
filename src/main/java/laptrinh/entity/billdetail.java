package laptrinh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name = "billdetail")
@Data
public class billdetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int bills_id;
	private int product_id;
	private int quanty;
	private double totalprice;
	
	@ManyToOne
	@JoinColumn(name = "bills_id",insertable=false, updatable=false)
	private bills bills;
	
	@ManyToOne
	@JoinColumn(name = "product_id",insertable=false, updatable=false)
	private product product;
	
	public billdetail(int bills_id, int product_id, int quanty, double totalprice) {
		super();
		this.bills_id = bills_id;
		this.product_id = product_id;
		this.quanty = quanty;
		this.totalprice = totalprice;
	}

	public billdetail() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBills_id() {
		return bills_id;
	}

	public void setBills_id(int bills_id) {
		this.bills_id = bills_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public bills getBills() {
		return bills;
	}

	public void setBills(bills bills) {
		this.bills = bills;
	}

	public product getProduct() {
		return product;
	}

	public void setProduct(product product) {
		this.product = product;
	}
	
	
	
}
