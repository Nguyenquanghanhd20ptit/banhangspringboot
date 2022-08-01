package laptrinh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import laptrinh.entity.category;
import laptrinh.entity.product;

public interface category_repository extends JpaRepository<category, Integer>{
	
}
