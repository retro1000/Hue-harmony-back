package hueHarmony.web.repository;

import hueHarmony.web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ExtendedRepository<Product, Long> {

    @Query("SELECT p.productDiscount, p.productPrice FROM Product p WHERE p.productId = :productId")
    List<Object[]> findProductPriceAndDiscountByProductId(@Param("productId")long productId);

}
