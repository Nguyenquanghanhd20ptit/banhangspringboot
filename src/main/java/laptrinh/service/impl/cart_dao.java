package laptrinh.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laptrinh.entity.product;
import laptrinh.modell.cart;
import laptrinh.repository.product_repository;
import laptrinh.service.Cart_interface;

@Service
public class cart_dao implements Cart_interface{
	@Autowired
	product_repository product_repository;
	@Override
	public HashMap<Integer, cart> AddCart(int id, HashMap<Integer, cart> Cart) {
		cart cart = new cart();
		product product = product_repository.getById(id);
		if(product != null && Cart.containsKey(id) ) {
			cart = Cart.get(id);
			cart.setQuanty(cart.getQuanty()+1);
	        cart.setTotalPrice(cart.getQuanty()*product.getPrice());
		}
		else {
			cart.setProduct(product);
			cart.setQuanty(1);
			cart.setTotalPrice(product.getPrice());
		}
		Cart.put(id, cart);
		return Cart;
	}

	@Override
	public HashMap<Integer, cart> EditCart(int id, int quanty, HashMap<Integer, cart> Cart) {
		if (Cart == null) {
			return Cart;
		}
		cart cart = new cart();
		product product = product_repository.getById(id);
		if(Cart.containsKey(id)) {
			cart = Cart.get(id);
			cart.setQuanty(quanty);
		    cart.setTotalPrice(quanty * product.getPrice());
		}
		Cart.put(id, cart);
		return Cart;
	}

	@Override
	public HashMap<Integer, cart> DeleteCart(int id, HashMap<Integer, cart> Cart) {
	      if (Cart == null) {
	    	  return Cart;
	     	}
	     if(Cart.containsKey(id)) {
	    	 Cart.remove(id);
	     }
	     return Cart;
	}

	@Override
	public int TotalQuanty(HashMap<Integer, cart> Cart) {
		int sum=0;
		for( Map.Entry<Integer, cart> itemsCart : Cart.entrySet()) {
			sum += itemsCart.getValue().getQuanty();
		}
		return sum;
	}

	@Override
	public double TotalPrice(HashMap<Integer, cart> Cart) {
		double sum=0;
		for(Map.Entry<Integer, cart> itemsCart : Cart.entrySet()) {
			sum+=itemsCart.getValue().getTotalPrice();
		}
		return sum;
	}

}
