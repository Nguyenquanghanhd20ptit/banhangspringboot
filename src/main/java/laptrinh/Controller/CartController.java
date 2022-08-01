package laptrinh.Controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import laptrinh.entity.product;
import laptrinh.modell.cart;
import laptrinh.repository.product_repository;
import laptrinh.service.Cart_interface;

@Controller
public class CartController {
	@Autowired
	product_repository product_repository;
	@Autowired
	Cart_interface cartDao;
	@GetMapping("/cart/{id}")
	public String Cart(Model model,@PathVariable(name = "id") int id,HttpServletRequest request, HttpSession  session) {
	    HashMap<Integer, cart> cart = (HashMap<Integer, laptrinh.modell.cart>) session.getAttribute("Cart");
	    if(cart == null) {
	    	cart = new HashMap<Integer, cart>();
	    }
	    cart  = cartDao.AddCart(id, cart);
	    session.setAttribute("Cart", cart);
	    session.setAttribute("TotalQuanty", cartDao.TotalQuanty(cart));
	    session.setAttribute("TotalPrice",cartDao.TotalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
	}
	
	@GetMapping("/listcart")
	public String listcart(Model model, HttpSession session) {
		return "user/cart";
	}
	@PostMapping("/editCart/{id}")
	public String editcart(Model model, @PathVariable(name = "id")int id,HttpServletRequest request,HttpSession session) {
		HashMap<Integer, cart> cart = (HashMap<Integer, laptrinh.modell.cart>) session.getAttribute("Cart");
		if(cart ==  null) {
			cart = new HashMap<Integer, cart>();
		}
		String quanty = request.getParameter("quanty");
		cart = cartDao.EditCart(id, Integer.parseInt(quanty), cart);
		 session.setAttribute("Cart", cart);
	    session.setAttribute("TotalQuanty", cartDao.TotalQuanty(cart));
	    session.setAttribute("TotalPrice",cartDao.TotalPrice(cart));
		return "user/cart";
	}
	
	@GetMapping("/deleteCart/{id}")
	public String deleteCart(Model model, @PathVariable(name = "id") int id, HttpSession session, HttpServletRequest request) {
		HashMap<Integer, cart> cart = (HashMap<Integer, laptrinh.modell.cart>) session.getAttribute("Cart");
		if(cart ==  null) {
			cart = new HashMap<Integer, cart>();
		}
		cart = cartDao.DeleteCart(id, cart);
		 session.setAttribute("Cart", cart);
	    session.setAttribute("TotalQuanty", cartDao.TotalQuanty(cart));
	    session.setAttribute("TotalPrice",cartDao.TotalPrice(cart));
	    return "redirect:" + request.getHeader("Referer");
	}
	@GetMapping("/wishlist")
	public  String wishlist() {
		return "user/wishlist";
	}
	@GetMapping("/addWishlist/{id}")
	public  String addwishlist(Model model,HttpServletRequest request, HttpSession session, @PathVariable(name = "id") int id) {
		HashMap<Integer, product> wishlist = (HashMap<Integer, product>) session.getAttribute("Wishlist");
		if(wishlist == null) {
			wishlist = new HashMap<Integer, product>();
		}
		product product = product_repository.getById(id);
		if(wishlist.containsKey(id)) {
			wishlist.remove(id);
		}
		else {
			wishlist.put(id, product);
			for( Map.Entry<Integer, product> itemsCart : wishlist.entrySet()) {
				System.out.println(itemsCart.getValue().getTen());
			}
		}
		session.setAttribute("Wishlist", wishlist);
		return "redirect:" + request.getHeader("Referer");
	}
	
}
