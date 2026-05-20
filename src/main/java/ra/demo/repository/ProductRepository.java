package ra.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.demo.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByProNameContaining(String proName, Pageable pageable);

    @Query("select p from  Product  p where p.proName like concat('%', :proName, '%') " +
            "and p.producer like concat('%', :producer, '%') " +
            "and p.price >= :min and p.price <= :max")
    Page<Product> getProductsByNameProducerPrice(@Param("proName") String proName,
                                                 @Param("producer") String producer,
                                                 @Param("min") Double min, @Param("max") Double max, Pageable pageable);
}
