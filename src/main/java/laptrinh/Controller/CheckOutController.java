package laptrinh.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import laptrinh.entity.billdetail;
import laptrinh.entity.bills;
import laptrinh.entity.user;
import laptrinh.modell.cart;
import laptrinh.repository.bill_repository;
import laptrinh.repository.billdetail_repository;
import laptrinh.validation.billValidator;
@Controller
public class CheckOutController {
	@Autowired
	billValidator billValidator;
	@Autowired
	bill_repository bill_repository;
	@Autowired
	billdetail_repository  billdetail_repository;
	@GetMapping("/checkout")
	public String checkout( Model model,HttpSession session) {
		if(session.getAttribute("loginInfor") == null) {
			String status = "Vui Lòng Đăng Nhập Trước Khi Thanh Toán";
			model.addAttribute("status", status);
			return "user/login";
		}
		user user = (laptrinh.entity.user) session.getAttribute("loginInfor");
		model.addAttribute("user", user);
		return "user/checkOut";
	}
	@PostMapping("/checkout")
	public String Checkout(HttpServletRequest request,HttpSession session , Model model , @ModelAttribute(name = "user") user user,
			BindingResult bindingResult) {
		billValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "user/checkOut";
		}
		String status = "Đặt hàng thành công";
		String ghichu = request.getParameter("ghichu");
		bills bills = new bills();
		bills.setHoten(user.getHoten());
		bills.setEmail(user.getEmail());
		bills.setDiachi(user.getDiachi());
		bills.setSdt(user.getSdt());
		bills.setTotalprice((double)session.getAttribute("TotalPrice"));
		bills.setQuanty((int)session.getAttribute("TotalQuanty"));
		bills.setGhichu(ghichu);
		bill_repository.save(bills);
		
		int id_bills = bill_repository.getById();
	   HashMap<Integer, cart> cart	= (HashMap<Integer, laptrinh.modell.cart>) session.getAttribute("Cart");
		for( Map.Entry<Integer, cart> itemsCart : cart.entrySet()) {
			billdetail billdetail = new billdetail();
			billdetail.setBills_id(id_bills);
			billdetail.setProduct_id(itemsCart.getValue().getProduct().getId());
			billdetail.setQuanty(itemsCart.getValue().getQuanty());
			billdetail.setTotalprice(itemsCart.getValue().getTotalPrice());
			billdetail_repository.save(billdetail);
		}
		model.addAttribute("status", status);
		return "user/checkOut";
	}
	
}
