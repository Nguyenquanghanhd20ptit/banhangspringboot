package laptrinh.modell;

import laptrinh.entity.product;

public class cart {
	private int quanty;
	private double totalPrice;
	private product product;
	public cart(int quanty, double totalPrice, laptrinh.entity.product product) {
		super();
		this.quanty = quanty;
		this.totalPrice = totalPrice;
		this.product = product;
	}
	public cart() {
		super();
	}
	public int getQuanty() {
		return quanty;
	}
	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	
	
}
