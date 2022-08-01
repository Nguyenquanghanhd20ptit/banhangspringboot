package laptrinh.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import laptrinh.repository.category_repository;
import laptrinh.repository.product_repository;
import laptrinh.entity.category;
import laptrinh.entity.product;

@Controller
public class StoreController {
	@Autowired
	product_repository product_repository;
	@Autowired
	category_repository category_repository;
	@GetMapping("/store/{page}")
	String product(Model model,HttpSession session ,@PathVariable(name = "page") int page) {
		List<product> products = new ArrayList<product>();
		List<product> products2 = new  ArrayList<product>();
		List<product> TopProducts = product_repository.findTop();
		Pageable pageable = PageRequest .of(page-1, 9);
		if(session.getAttribute("Price_min") != null) {
			double price_min = (double) session.getAttribute("Price_min");
			double price_max = (double) session.getAttribute("Price_max");
	            products = product_repository.findPricePage(price_min,price_max,pageable);
			    products2 = product_repository.findPriceAll(price_min,price_max);
		}
		else {
			 products = product_repository.findPage(pageable);
			 products2 = product_repository.findAll();
		}
		int totalPage = 1;
		 if(products2.size() % 9 == 0) {
			 totalPage = products2.size()/9;
		 }
		 else {
			 totalPage = (products2.size()/9)+1;
		}
		session.setAttribute("list_category", null);
		int ktra = 0;
	    List<category> categories = category_repository.findAll();
	    model.addAttribute("categories", categories);
	    model.addAttribute("products", products);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("TopProducts", TopProducts);
	    model.addAttribute("ktra",ktra);
		return "user/store";
	}
	@PostMapping("/store/{page}")
	String product(Model model,HttpServletRequest request,HttpSession session ,@PathVariable(name = "page") int page) {
		 double price_min = Double.parseDouble( request.getParameter("Price_min") );
		 double price_max = Double.parseDouble( request.getParameter("Price_max") );
		 
		session.setAttribute("Price_min", price_min);
		session.setAttribute("Price_max", price_max);
		return "redirect:/store/{page}";
	}
	@GetMapping("/store/id/{id}/{page}")
	String categoryProduct(Model model, @PathVariable(name =  "id") int id,@PathVariable(name = "page") int page, HttpSession session) {
		List<product> products = new ArrayList<product>();
		List<product> products2 = new  ArrayList<product>();
		List<product> TopProducts = product_repository.findTop();
		session.setAttribute("list_category", id);
		Pageable pageable = PageRequest .of(page-1, 9);
		if(session.getAttribute("Price_min2") != null) {
			double price_min = (double) session.getAttribute("Price_min2");
			double price_max = (double) session.getAttribute("Price_max2");
	            products = product_repository.findCategoryPricePage(id,price_min,price_max,pageable);
			    products2 = product_repository.findCategoryPriceAll(id,price_min,price_max);
		}
		else {
			 products = product_repository.findCategoryPage(id, pageable);
			 products2 =  product_repository.findAllCategory(id);
		}
		 int totalPage = 1;
		 if(products2.size() % 9 == 0) {
			 totalPage = products2.size()/9;
		 }
		 else {
			 totalPage = (products2.size()/9)+1;
		}
	    List<category> categories = category_repository.findAll();
	    model.addAttribute("categories", categories);
	    model.addAttribute("products", products);
	    model.addAttribute("TopProducts", TopProducts);
	    model.addAttribute("totalPage", totalPage);
		return "user/store";
	}
	
	@PostMapping("/store/id/{id}/{page}")
	String CategoryProduct(Model model,HttpServletRequest request, @PathVariable(name =  "id") int id,@PathVariable(name = "page") int page, HttpSession session) {
		 double price_min = Double.parseDouble( request.getParameter("Price_min") );
		 double price_max = Double.parseDouble( request.getParameter("Price_max") );
		 
		session.setAttribute("Price_min2", price_min);
		session.setAttribute("Price_max2", price_max);
		session.setAttribute("ktra2", id);
		return "redirect:/store/id/{id}/{page}";
	}
}
