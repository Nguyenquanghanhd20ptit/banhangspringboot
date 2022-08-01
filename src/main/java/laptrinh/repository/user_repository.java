package laptrinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import laptrinh.entity.user;

public interface user_repository extends JpaRepository<user, Integer> {
	
	@Query( value = "select * from user where taikhoan = ?1",  nativeQuery=true)
	user getByTaikhoan(String tk);
}
