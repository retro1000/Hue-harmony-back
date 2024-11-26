package hueHarmony.web.repository;

import hueHarmony.web.model.Product;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ExtendedRepository<Product, Long>{
    List<Product> findByProductName(@Param("productName") String productName);

}
