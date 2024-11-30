package hueHarmony.web.repository;

import hueHarmony.web.dto.ProductDto;
import hueHarmony.web.dto.PosProductDto;
import hueHarmony.web.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import hueHarmony.web.dto.PosProductDto;
import hueHarmony.web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ExtendedRepository<Product, Long>{
    List<Product> findByProductName(@Param("productName") String productName);

    @Query("SELECT new hueHarmony.web.dto.PosProductDto(p.productId, p.productName, p.productPrice,p.productDiscount) FROM Product p")
    List<PosProductDto> getProducts();

//    @Query("SELECT new hueHarmony.web.dto.ProductDto(p.productId, p.productName, p.productImageUrl, p.startingPrice, p.productStatus, p.productDiscount) FROM Product p")
//    List<ProductDto> findAllProductListDto();

//    @Query("SELECT new hueHarmony.web.dto.PosProductDto(p.productId, p.productName, p.productPrice,p.productDiscount) FROM Product p")
//    List<PosProductDto> getProducts();

}
