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
@Table(name = "product")
@Data
public class product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int category_id;
	private String ten;
	private String img;
	private double price;
	private int quanty;
	private String detail;
	private boolean top_selling;
	private boolean  new_product;
	
	@ManyToOne
	@JoinColumn(name = "category_id",insertable=false, updatable=false)
	private category  category;
	public product( int category_id, String ten, String img, double price, int quanty, String detail,
			boolean top_selling, boolean new_product) {
		super();
		this.category_id = category_id;
		this.ten = ten;
		this.img = img;
		this.price = price;
		this.quanty = quanty;
		this.detail = detail;
		this.top_selling = top_selling;
		this.new_product = new_product;
	}
	public product() {
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuanty() {
		return quanty;
	}
	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public boolean isTop_selling() {
		return top_selling;
	}
	public void setTop_selling(boolean top_selling) {
		this.top_selling = top_selling;
	}
	public boolean isNew_product() {
		return new_product;
	}
	public void setNew_product(boolean new_product) {
		this.new_product = new_product;
	}
	public category getCategory() {
		return category;
	}
	public void setCategory(category category) {
		this.category = category;
	}
	
}
