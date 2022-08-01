package laptrinh.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import laptrinh.entity.user;
import laptrinh.repository.user_repository;

@Service
public class user_validation implements Validator {
	@Autowired
	user_repository user_repository;
	public boolean supports(Class<?> clazz) {
		return user.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		user user = (user) target;
		if(user.getTaikhoan() == null || user.getTaikhoan().length()==0) {
			errors.rejectValue("taikhoan", "requireTaikhoan");
		}
		if( ObjectUtils.isEmpty(user_repository.getByTaikhoan(user.getTaikhoan())) == false) {
			errors.rejectValue("taikhoan", "InvaliTaikhoan");
		}
		if(user.getPassword() == null || user.getPassword().length()==0) {
			errors.rejectValue("password", "requirePassword");
		}
		if(user.getEmail() == null || user.getEmail().length() == 0 ) {
			errors.rejectValue("email", "requireEmail");
		}
		boolean check = false;
		for(int i=0;i<user.getEmail().length();i++) {
			if(user.getEmail().charAt(i)=='@') {
				check = true;
				break;
			}
		}
		if (check == false) {
			errors.rejectValue("email", "InvalidEmail");
		}
		if(user.getHoten() == null || user.getHoten().length()==0) {
			errors.rejectValue("hoten", "requireTen");
		}
		if(user.getTuoi() == 0) {
			errors.rejectValue("tuoi", "requireTuoi");
		}
		if(user.getDiachi() == null || user.getDiachi().length()==0) {
			errors.rejectValue("diachi", "requireDiachi");
		}
		if(user.getSdt() == null || user.getSdt().length()==0) {
			errors.rejectValue("sdt", "requireSdt");
		}
		if(user.getPassword().length() < 6) {
			errors.rejectValue("password", "InvalidPassword");
		}
		for(int i=0;i<user.getSdt().length();i++) {
			if(user.getSdt().charAt(i)<'0' || user.getSdt().charAt(i) >'9') {
				errors.rejectValue("diachi", "InvalidSdt");
			}
		}
	}

}
