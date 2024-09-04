package hueHarmony.web.service;

import com.fasterxml.jackson.annotation.JsonView;
import hueHarmony.web.dto.ProductDto;
import hueHarmony.web.model.Product;
import hueHarmony.web.repository.ProductRepository;
import hueHarmony.web.specification.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAllProductListDto();
    }

    public Product save(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> searchProducts(String category, String key) {
        Specification<Product> specification = Specification.where(ProductSpecifications.withKeyword(key))
                .and(ProductSpecifications.withCategory(category));

        return productRepository.findAll(specification);
    }
}
