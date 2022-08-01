package laptrinh.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class category {
	@Id
	private int id;
	private String ten;
	private String description;
	
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	private List<product> products;

	public category(int id, String ten, String description) {
		super();
		this.id = id;
		this.ten = ten;
		this.description = description;
	}

	public category() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<product> getProducts() {
		return products;
	}

	public void setProducts(List<product> products) {
		this.products = products;
	}
	
	
}
