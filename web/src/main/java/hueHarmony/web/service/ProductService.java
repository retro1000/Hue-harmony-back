package hueHarmony.web.service;

import hueHarmony.web.dto.AddProductDto;
import hueHarmony.web.dto.FilterProductDto;
import hueHarmony.web.dto.response.ProductDisplayDto;
import hueHarmony.web.dto.response.ProductUserDisplayDto;
import hueHarmony.web.model.Brand;
import hueHarmony.web.model.Product;
import hueHarmony.web.model.ProductFeature;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
                List.of("productId", "productTitle", "productStatus", "productImage","productPrice"),
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
                .where(ProductSpecification.hasProductStatus(Collections.singleton(ProductStatus.APPROVED)))
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
                List.of("productId", "productTitle", "productStatus, productImage", "reviewCount", "productRate"),
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

        product.setProductStatus(ProductStatus.valueOf(addProductDto.getProductStatus().toUpperCase()));
        product.setBrand(addProductDto.getBrand());
        product.setRoomType(addProductDto.getRoomType());
        product.setFinish(addProductDto.getFinish());
        product.setProductType(addProductDto.getProductType());


        product.setSurfaces(Arrays.stream(addProductDto.getSurfaces())
                .map(Surface::valueOf)
                .collect(Collectors.toSet()));


        product.setPositions(Arrays.stream(addProductDto.getPositions())
                .map(Position::valueOf)
                .collect(Collectors.toSet()));

        product.setPositions(Arrays.stream(addProductDto.getPositions())
                .map(Position::valueOf)
                .collect(Collectors.toSet()));


        // Map productFeatures

        // Save the product to the database using the repository
        // productRepository.save(product);
    }

}
