package laptrinh.Controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import laptrinh.entity.user;
import laptrinh.repository.user_repository;

@Controller
public class loginController {
	@Autowired
	user_repository user_repository;
	@GetMapping("/login")
	public String login(Model model) {
		String status = "Vui Lòng Đăng Nhập";
		model.addAttribute("status", status);
		return "user/login";
	}
	
	@PostMapping("/login")
	public String Login(Model model,HttpServletRequest request,HttpSession session) {
		String taikhoan = request.getParameter("taikhoan");
		String password = request.getParameter("password");
		user user = user_repository.getByTaikhoan(taikhoan);
		boolean check;
		if(ObjectUtils.isEmpty(user) == false) {
			if(BCrypt.checkpw(password,user.getPassword())) {
				check = true;
			}
			else check = false;
		}
		else check = false;
		
		if(check == false) {
			
			String status = "Vui Lòng Đăng Nhập";
			model.addAttribute("status", status);
			String tbao1 = " Sai mật khẩu hoặc tài khoản";
			model.addAttribute("tbao1", tbao1);
			return "user/login";
		}
		else {
			session.setAttribute("loginInfor", user);
			return "redirect:/product";
		}
	}
	@GetMapping("/dangxuat")
	public String dangxuat(HttpSession session,HttpServletRequest request) {
		session.setAttribute("loginInfor", null);
		return "redirect:" + request.getHeader("Referer");
	}
}
