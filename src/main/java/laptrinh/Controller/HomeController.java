package laptrinh.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import laptrinh.entity.category;
import laptrinh.entity.menu;
import laptrinh.entity.product;
import laptrinh.entity.user;
import laptrinh.modell.cart;
import laptrinh.repository.category_repository;
import laptrinh.repository.menu_repository;
import laptrinh.repository.product_repository;

@Controller
public class HomeController {
	@Autowired
	menu_repository menu_repository;
	@Autowired
	product_repository product_repository;
	@Autowired
	category_repository category_repository;
	
	@GetMapping(value = {"/","product"})
	public String Index(Model model,HttpSession  session) {
		session.setAttribute("id_new", null);
		session.setAttribute("id_top", null);
		 HashMap<Integer, cart> cart = (HashMap<Integer, cart>) session.getAttribute("Cart");
		 session.setAttribute("Cart", cart);
		 HashMap<Integer, product> wishlist = (HashMap<Integer, product>) session.getAttribute("Wishlist");
		 session.setAttribute("Wishlist", wishlist);
		 user user = (laptrinh.entity.user) session.getAttribute("loginInfor");
		 session.setAttribute("loginInfor", user);
		 List<product> searchProducts = (List<product>) session.getAttribute("SearchProducts");
		 session.setAttribute("SearchProducts", searchProducts);
		List<category> categories = category_repository.findAll();
		List<product> products1 = product_repository.findAllLimit();
		List<product> products2 = product_repository.findAllLimit();
		List<product> products3 = product_repository.findAllLimit();
		List<product> NewProducts =product_repository.findAllNew();
		List<product> TopProducts = product_repository.findAllTop();
		model.addAttribute("caregories",categories);
		model.addAttribute("products1",products1);
		model.addAttribute("products2",products2);
		model.addAttribute("products3",products3);
		model.addAttribute("NewProducts", NewProducts);
		model.addAttribute("TopProducts", TopProducts);
		return "user/index";
	}
	@GetMapping("/product/new/{id}")
	public String new_product(Model model, @PathVariable(name = "id") int id,HttpSession session) {
		session.setAttribute("id_new", id);
		List<category> categories = category_repository.findAll();
		List<product> NewProducts = product_repository.findNewProducts(id);
		System.out.println("chieu dai la:  "+NewProducts.size());
		List<product> products1 = product_repository.findAllLimit();
		List<product> products2 = product_repository.findAllLimit();
		List<product> products3 = product_repository.findAllLimit();
		List<product> TopProducts = product_repository.findAllTop();
		model.addAttribute("caregories",categories);
		model.addAttribute("NewProducts", NewProducts);
		model.addAttribute("TopProducts",TopProducts);
		model.addAttribute("products1",products1);
		model.addAttribute("products2",products2);
		model.addAttribute("products3",products3);
		return "user/index";
	}
	@GetMapping("/product/top/{id}")
	public String Top_product(Model model, @PathVariable(name = "id") int id,HttpSession session) {
		session.setAttribute("id_top", id);
		List<category> categories = category_repository.findAll();
		List<product> NewProducts = product_repository.findAllNew();
		List<product> TopProducts = product_repository.findTopProducts(id);
		List<product> products1 = product_repository.findAllLimit();
		List<product> products2 = product_repository.findAllLimit();
		List<product> products3 = product_repository.findAllLimit();
		model.addAttribute("caregories",categories);
		model.addAttribute("NewProducts", NewProducts);
		model.addAttribute("TopProducts",TopProducts);
		model.addAttribute("products1",products1);
		model.addAttribute("products2",products2);
		model.addAttribute("products3",products3);
		return "user/index";
	}
	@GetMapping("/search/{page}")
	public String Search(Model model,HttpServletRequest request,HttpSession session, @PathVariable(name = "page") int page) {
		Pageable pageable =PageRequest.of(page-1, 9);
	    List<product> products = (List<product>) session.getAttribute("SearchProductsPage");
	    List<product> products2 = (List<product>) session.getAttribute("SearchProducts");
	    List<product> TopProducts = product_repository.findTop();
	    int totalPage;
	    if(products2.size() % 9 == 0) {
	    	totalPage = products2.size()/9; 
	    }
	    else totalPage = (products2.size()/9)+1;
	    List<category> categories = category_repository.findAll();
	    model.addAttribute("categories", categories);
	    model.addAttribute("products", products);
	    model.addAttribute("totalPage",totalPage);
	    model.addAttribute("TopProducts", TopProducts);
	    return "user/store";
	}
	@PostMapping("/search/{page}")
	public String search(Model model,HttpServletRequest request,HttpSession session, @PathVariable(name = "page") int page) {
		String search = request.getParameter("search");
		Pageable pageable =PageRequest.of(page-1, 9);
	    List<product> products = product_repository.searchProductsPage(search, pageable);
	    List<product> products2 = product_repository.searchProducts(search);
	    List<product> TopProducts = product_repository.findTop();
	    session.setAttribute("SearchProductsPage", products);
	    session.setAttribute("SearchProducts", products2);
	    int totalPage;
	    if(products2.size() % 9 == 0) {
	    	totalPage = products2.size()/9; 
	    }
	    else totalPage = (products2.size()/9)+1;
	    List<category> categories = category_repository.findAll();
	    model.addAttribute("categories", categories);
	    model.addAttribute("products", products);
	    model.addAttribute("totalPage",totalPage);
	    model.addAttribute("TopProducts", TopProducts);
	    return "user/store";
	}
}
