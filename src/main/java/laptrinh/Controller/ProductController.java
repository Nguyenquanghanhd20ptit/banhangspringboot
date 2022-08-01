package laptrinh.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import laptrinh.entity.category;
import laptrinh.entity.product;
import laptrinh.repository.category_repository;
import laptrinh.repository.product_repository;

@Controller
public class ProductController {
	@Autowired
	category_repository category_repository;
	@Autowired
	product_repository product_repository;
	@GetMapping("/productDetail/{id}")
	public String Product(Model model,@PathVariable(name = "id") int id) {
		List<category> categories = category_repository.findAll();
		product  product = product_repository.getById(id);
		  List<product> relatedProducts = product_repository.findrelated(product.getCategory_id());
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		model.addAttribute("relatedProducts", relatedProducts);
		return"user/product";
	}
}
