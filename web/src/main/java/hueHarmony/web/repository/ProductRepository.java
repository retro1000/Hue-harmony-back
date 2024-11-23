package hueHarmony.web.repository;

import hueHarmony.web.dto.PosProductDto;
import hueHarmony.web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
   @Query("SELECT new hueHarmony.web.dto.PosProductDto(p.productId, p.productName, p.productPrice,p.productDiscount) FROM Product p")
   List<PosProductDto> getProducts();
}
