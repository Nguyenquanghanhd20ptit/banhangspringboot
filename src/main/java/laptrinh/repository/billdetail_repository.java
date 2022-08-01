package laptrinh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import laptrinh.entity.billdetail;

public interface billdetail_repository extends JpaRepository<billdetail, Integer>{
	
	@Query(value = "select * from billdetail where bills_id = ?1", nativeQuery = true)
	List<billdetail> findbilldetails(int id);
}
