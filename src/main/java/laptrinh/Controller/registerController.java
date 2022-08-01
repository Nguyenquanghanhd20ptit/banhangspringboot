package laptrinh.Controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import laptrinh.entity.user;
import laptrinh.repository.user_repository;
import laptrinh.validation.user_validation;

@Controller
public class registerController {
	@Autowired
	user_validation user_validation;
	@Autowired
	user_repository user_repository;
	@GetMapping("/register")
	public String register(Model model) {
		user user = new user();
		String status = "Vui Lòng Nhập Thông Tin";
		model.addAttribute("user", user);
	    model.addAttribute("status", status);
		return "user/register";
	}
	@PostMapping("/register")
	public String Register(Model model, @ModelAttribute(name = "user") user user ,
			BindingResult bindingResult) {
		  String status = "Vui Lòng Nhập Thông Tin";
		  user_validation.validate(user, bindingResult);
		  if(bindingResult.hasErrors()) {
			  model.addAttribute("status", status);
			  return "user/register";
		  }
		  String password = user.getPassword();
		  user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
		  user_repository.save(user);
		  status = "Đăng Kí Thành Công";
		  model.addAttribute("status", status);
		  user.setPassword(password);
		  return "user/register";
	}
}
