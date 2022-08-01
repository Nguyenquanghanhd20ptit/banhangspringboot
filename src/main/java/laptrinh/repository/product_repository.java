package laptrinh.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import laptrinh.entity.product;

public interface product_repository extends JpaRepository<product, Integer>{
	
	@Query( value = "select * from product ORDER BY RAND() Limit 9",  nativeQuery=true)
	List<product> findAllLimit();
	
	@Query( value = "select * from product where new_product=1 ORDER BY RAND() Limit 6",  nativeQuery=true)
	List<product> findAllNew();
	
	@Query( value = "select * from product where top_selling=1 ORDER BY RAND() Limit 6",  nativeQuery=true)
	List<product> findAllTop();
	
	@Query( value = "select * from product where category_id=?1 and new_product=1 ORDER BY RAND() Limit 6",  nativeQuery=true)
	List<product> findNewProducts(int id);
	
	@Query( value = "select * from product where category_id=?1 and top_selling=1 ORDER BY RAND() Limit 6",  nativeQuery=true)
	List<product> findTopProducts(int id);
	
	@Query( value = "select * from product",  nativeQuery=true)
	List<product> findPage(Pageable pageable);
	
	@Query( value = "select * from product where category_id = ?1",  nativeQuery=true)
	List<product> findCategoryPage(int id,Pageable pageable);
	
	@Query( value = "select * from product where category_id = ?1",  nativeQuery=true)
	List<product> findAllCategory(int id);
	
	@Query( value = "select * from product where price between ?1 and ?2",  nativeQuery=true)
	List<product> findPricePage(double price_min ,double price_max,Pageable pageable);

	@Query( value = "select * from product where price between ?1 and ?2",  nativeQuery=true)
	List<product> findPriceAll(double price_min ,double price_max);
	
	@Query( value = "select * from product where  category_id = ?1 and price between ?2 and ?3",  nativeQuery=true)
	List<product> findCategoryPricePage(int id,double price_min ,double price_max,Pageable pageable);

	@Query( value = "select * from product where  category_id = ?1 and price between ?2 and ?3",  nativeQuery=true)
	List<product> findCategoryPriceAll(int id,double price_min ,double price_max);
	
	@Query( value = "select * from product where top_selling=1 ORDER BY RAND() Limit 3",  nativeQuery=true)
	List<product> findTop();
	
	@Query( value = "select * from product where category_id = ?1 ORDER BY RAND() Limit 4",  nativeQuery=true)
	List<product> findrelated(int id);
	
	@Query( value = "select * from product where ten like %?1% ",  nativeQuery=true)
	List<product> searchProducts(String search);
	
	@Query( value = "select * from product where ten like %?1% ",  nativeQuery=true)
	List<product> searchProductsPage(String search , Pageable pageable);
}
