package laptrinh.Controller;


import java.security.PublicKey;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import laptrinh.entity.bills;
import laptrinh.entity.billdetail;
import laptrinh.entity.product;
import laptrinh.repository.bill_repository;
import laptrinh.repository.billdetail_repository;
import laptrinh.repository.product_repository;


@Controller
public class AdminController {
	@Autowired
	product_repository product_repository;
	
	@Autowired
	bill_repository bill_repository;
	
	@Autowired
	billdetail_repository billdetail_repository;
	
	@GetMapping("/addproduct")
	public String addproduct(Model model) {
		return "admin/AddProduct";
	}
	
	@PostMapping("/addproduct")
	public String Addproduct(Model model,HttpServletRequest request) {
		String category_id = request.getParameter("category_id");
		String ten = request.getParameter("ten");
		String img = request.getParameter("img");
		String price = request.getParameter("price");
		String quanty = request.getParameter("quanty");
		String detail = request.getParameter("detail");
		String top_selling = request.getParameter("selling");
		String new_product = request.getParameter("new_product");
		boolean selling,New;
		if(Integer.parseInt(top_selling)==1) {
			selling=true;
		}
		else selling = false;
		if(Integer.parseInt(new_product)==1) {
			New = true;
		}
		else New = false;
		product product = new product( Integer.parseInt(category_id), ten, img,Double.parseDouble(price), Integer.parseInt(quanty), detail,selling,New);
		product_repository.save(product);
		String status = "Thêm Sản Phẩm Thành Công";
		model.addAttribute("status", status);
		return "admin/AddProduct";
	}
	@GetMapping("/listproduct")
	public String listproduct(Model model) {
		List<product> products = product_repository.findAll();
		model.addAttribute("products", products);
		return "admin/ListProduct";
	}
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(Model model,@PathVariable(name = "id") int id) {
		product_repository.deleteById(id);
	    return "redirect:/listproduct";
	}
	@GetMapping("/editProduct/{id}")
	public String editProduct( Model model, @PathVariable(name = "id") int id) {
		product  product = product_repository.getById(id);
		model.addAttribute("product",product);
		return "admin/EditProduct";
	}
	
	@PostMapping("/editProduct/{id}")
	public String updateUser(Model model, @ModelAttribute(name = "Product") product product,
			 @PathVariable(name = "id") int id, BindingResult  bindingResult) {
	    if(bindingResult.hasErrors()) {
	    	return "admin/EditProduct";
	    }
	    product.setId(id);
	    product_repository.save(product);
	    return "redirect:/listproduct";
	}
	
	@GetMapping("/allbill")
	public String allbill(Model model) {
		List<bills>  bills =  bill_repository.findAll();
		model.addAttribute("bills", bills);
		return "admin/AllBills";
	}
	
	@GetMapping("/billdetail/{id}")
	public String billdetail(Model model, @PathVariable(name = "id") int id) {
		List<billdetail> billdetails = billdetail_repository.findbilldetails(id);
		model.addAttribute("billdetails", billdetails);
		return "admin/BillDetail";
	}
}
