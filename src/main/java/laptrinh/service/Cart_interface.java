package laptrinh.service;

import java.util.HashMap;

import laptrinh.modell.cart;

public interface Cart_interface {
	public HashMap<Integer, cart> AddCart(int id ,HashMap<Integer,cart> Cart);
	public HashMap<Integer, cart> EditCart(int id,int quanty,HashMap<Integer, cart> Cart);
	public HashMap<Integer, cart> DeleteCart(int id,HashMap<Integer, cart> Cart);
	public int TotalQuanty(HashMap<Integer, cart> Cart );
	public double TotalPrice(HashMap<Integer, cart> Cart);
}
