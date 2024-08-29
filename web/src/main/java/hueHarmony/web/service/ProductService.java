package hueHarmony.web.service;

import com.fasterxml.jackson.annotation.JsonView;
import hueHarmony.web.dto.ProductDto;
import hueHarmony.web.model.Product;
import hueHarmony.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @JsonView(ProductDto.onCreate.class)
    @Transactional
    public Product save(Product newProduct) {
        Product product = productRepository.save(newProduct);
        return product;
    }
}
