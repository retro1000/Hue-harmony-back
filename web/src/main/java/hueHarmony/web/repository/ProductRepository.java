package hueHarmony.web.repository;

import hueHarmony.web.dto.ProductDto;
import hueHarmony.web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("SELECT new hueHarmony.web.dto.ProductDto(p.productId, p.productName, p.productImageUrl, p.startingPrice, p.productStatus, p.productDiscount) FROM Product p")
    List<ProductDto> findAllProductListDto();
}
