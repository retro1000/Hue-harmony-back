package hueHarmony.web.service;

import com.google.api.client.util.Value;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import hueHarmony.web.dto.AddProductDto;
import hueHarmony.web.dto.FilterProductDto;
import hueHarmony.web.dto.UpdateProductDto;
import hueHarmony.web.dto.response.ProductDisplayDto;
import hueHarmony.web.dto.response.ProductUserDisplayDto;
import hueHarmony.web.model.Product;
import hueHarmony.web.model.enums.data_set.*;
import hueHarmony.web.repository.ProductRepository;
import hueHarmony.web.specification.ProductSpecification;
import hueHarmony.web.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FirebaseStorageService firebaseStorageService;

    public Page<ProductDisplayDto> filterProductsForDashboardTable(FilterProductDto productFilterDto){
        Specification<Product> productSpecification = Specification
                .where(ProductSpecification.hasName(productFilterDto.getSearch()))
                .and(ProductSpecification.hasProductStatus(productFilterDto.getStatus()));
//                .and(ProductSpecification.betweenDates(productFilterDto.getStartDate(), productFilterDto.getEndDate()));

        return productRepository.filterAndSelectFieldsBySpecsAndPage(
                productSpecification,
                PageRequest.of(productFilterDto.getPage(), productFilterDto.getLimit()).withSort(productFilterDto.getSortOrder(), productFilterDto.getSortCol()),
                List.of("productId", "productName", "productStatus", "imageIds","productPrice"),
                ProductDisplayDto.class
        ).map(product -> {
                    ProductDisplayDto dto = (ProductDisplayDto) product;
                    dto.setProductImage(firebaseStorageService.getFileDownloadUrl(dto.getProductImage(), 60, TimeUnit.MINUTES));
                    return dto;
                }
        );
    }

    public Page<ProductUserDisplayDto> filterProductsForList(FilterProductDto productFilterDto){
        Float[] unitPriceRange = ConvertUtil.convertRangeToArray(
                productFilterDto.getSellingPrice(),
                Float::parseFloat,
                new Float[0]
        );

        Specification<Product> productSpecification = Specification
                .where(ProductSpecification.hasProductStatus(Collections.singleton(ProductStatus.AVAILABLE)))
                .and(ProductSpecification.betweenVariationUnitPrice(unitPriceRange[0], unitPriceRange[1]));

        Sort.Direction direction;
        String column;

        switch (productFilterDto.getSort()){
            case LOWEST -> {
                direction = Sort.Direction.ASC;
                column = "unitPrice";
            }
            case HIGHEST -> {
                direction = Sort.Direction.DESC;
                column = "unitPrice";
            }
            default -> {
                direction = Sort.Direction.DESC;
                column = "productPublishedTime";
            }
        }

        return productRepository.filterAndSelectFieldsBySpecsAndPage(
                productSpecification,
                PageRequest.of(productFilterDto.getPage(), productFilterDto.getLimit()).withSort(direction, column),
                List.of("productId", "productName", "productStatus", "imageIds"),
                ProductUserDisplayDto.class
        ).map(product -> {
            ProductUserDisplayDto dto = (ProductUserDisplayDto) product;
            return new ProductUserDisplayDto(
                    dto.getProductId(),
                    dto.getProductTitle(),
                    dto.getProductStatus(),
                    firebaseStorageService.getFileDownloadUrl(dto.getProductImage(), 60, TimeUnit.MINUTES),
                    dto.getPrice(),
                    dto.isSale(),
                    dto.isNew(),
                    dto.getReviewRate(),
                    dto.getTotalReviews(),
                    dto.getDiscount()

            );
        });
    }


    public void createProduct(AddProductDto addProductDto) {
        Product product = new Product();

        // Basic fields
        product.setProductName(addProductDto.getProductName());
        product.setProductDescription(addProductDto.getProductDescription());
        product.setProductPrice(addProductDto.getProductPrice());
        product.setProductDiscount(addProductDto.getProductDiscount());
        product.setCoat(addProductDto.getCoat());
        product.setDryingTime(addProductDto.getDryingTime() + " hours");
        product.setCoverage(addProductDto.getCoverage());
        product.setOnlineLimit(addProductDto.getOnlineLimit());
        product.setProductQuantity(addProductDto.getProductQuantity());

        product.setProductStatus(ProductStatus.valueOf(addProductDto.getProductStatus().toUpperCase()));
        product.setBrand(addProductDto.getBrand());
        product.setRoomType(addProductDto.getRoomType());
        product.setFinish(addProductDto.getFinish());

        List<Surface> validSurfaces = addProductDto.getSurfaces().stream()
                .filter(Surface::contains)
                .map(value -> Surface.valueOf(value.toUpperCase()))
                .toList();
        product.setSurfaces(validSurfaces);

        List<Position> validPositions = addProductDto.getPositions().stream()
                .filter(Position::contains)
                .map(value -> Position.valueOf(value.toUpperCase()))
                .toList();
        product.setPositions(validPositions);

        List<ProductType> validProductTypes = addProductDto.getProductTypes().stream()
                .filter(ProductType::contains)
                .map(value -> ProductType.valueOf(value.toUpperCase()))
                .toList();
        product.setProductType(validProductTypes);

        product.setProductFeatures(addProductDto.getProductFeatures());

        List<String> imageIds = firebaseStorageService.uploadImagesToFirebase(addProductDto.getProductImage());

        product.setImageIds(imageIds);

        System.out.println(product);

        productRepository.save(product);

    }

    public void updateProduct(Long productId, UpdateProductDto updateProductDTO) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product with ID " + productId + " not found.");
        }

        Product product = optionalProduct.get();

        // Update fields
        product.setProductName(updateProductDTO.getProductName());
        product.setProductDescription(updateProductDTO.getProductDescription());
        product.setProductPrice(updateProductDTO.getProductPrice());
        product.setProductDiscount(updateProductDTO.getProductDiscount());
        product.setCoat(updateProductDTO.getCoat());
        product.setDryingTime(updateProductDTO.getDryingTime());
        product.setCoverage(updateProductDTO.getCoverage());
        product.setOnlineLimit(updateProductDTO.getOnlineLimit());
        product.setProductQuantity(updateProductDTO.getProductQuantity());
        product.setProductStatus(updateProductDTO.getProductStatus());
        product.setBrand(updateProductDTO.getBrand());
        product.setRoomType(updateProductDTO.getRoomType());
        product.setFinish(updateProductDTO.getFinish());
        product.setProductType(updateProductDTO.getProductTypes());
        product.setSurfaces(updateProductDTO.getSurfaces());
        product.setPositions(updateProductDTO.getPositions());
        product.setProductFeatures(updateProductDTO.getProductFeatures());
        product.setImageIds(updateProductDTO.getImages());


        productRepository.save(product);
    }


    public void deleteProduct(Long productId) throws Exception {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new Exception("Product not found");
        }
        productRepository.delete(product.get());
    }

    public Product getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElseThrow(() -> new RuntimeException("Product not found for ID: " + productId));
    }

    public float[] getProductPriceAndDiscount(long productId) {
        return null;
    }
}
