package hueHarmony.web.repository;

import hueHarmony.web.dto.ProductDto;
import hueHarmony.web.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ExtendedRepository<Product, Long>{
    List<Product> findByProductName(@Param("productName") String productName);

    @Query("SELECT new hueHarmony.web.dto.ProductDto(p.productId, p.productName, p.productImageUrl, p.startingPrice, p.productStatus, p.productDiscount) FROM Product p")
    List<ProductDto> findAllProductListDto();
}
