package laptrinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import laptrinh.entity.bills;

public interface bill_repository extends JpaRepository<bills, Integer> {
	
	@Query(value = "select max(id) from bills" , nativeQuery = true)
	int getById();
}
