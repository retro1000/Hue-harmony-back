package hueHarmony.web.service;


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
                PageRequest.of(productFilterDto.getPage(), productFilterDto.getLimit()),
                List.of("productId", "productName", "productStatus", "brand", "roomType", "finish", "productPrice", "productQuantity"),
                ProductDisplayDto.class
        ).map(product -> (ProductDisplayDto) product);
    }

//    public Page<ProductUserDisplayDto> filterProductsForList(FilterProductDto productFilterDto){
//        Float[] unitPriceRange = ConvertUtil.convertRangeToArray(
//                productFilterDto.getSellingPrice(),
//                Float::parseFloat,
//                new Float[0]
//        );
//
//        Specification<Product> productSpecification = Specification
//                .where(ProductSpecification.hasProductStatus(Collections.singleton(ProductStatus.AVAILABLE)))
//                .and(ProductSpecification.betweenVariationUnitPrice(unitPriceRange[0], unitPriceRange[1]));
//
//        Sort.Direction direction;
//        String column;
//
//        switch (productFilterDto.getSort()){
//            case LOWEST -> {
//                direction = Sort.Direction.ASC;
//                column = "unitPrice";
//            }
//            case HIGHEST -> {
//                direction = Sort.Direction.DESC;
//                column = "unitPrice";
//            }
//            default -> {
//                direction = Sort.Direction.DESC;
//                column = "productPublishedTime";
//            }
//        }
//
//        return productRepository.filterAndSelectFieldsBySpecsAndPage(
//                productSpecification,
//                PageRequest.of(productFilterDto.getPage(), productFilterDto.getLimit()).withSort(direction, column),
//                List.of("productId", "productName", "productStatus", "imageIds"),
//                ProductUserDisplayDto.class
//        ).map(product -> {
//            ProductUserDisplayDto dto = (ProductUserDisplayDto) product;
//            return new ProductUserDisplayDto(
//                    dto.getProductId(),
//                    dto.getProductName(),
//                    dto.getProductStatus(),
//                    firebaseStorageService.getFileDownloadUrl(dto.getProductImage(), 60, TimeUnit.MINUTES),
//                    dto.getProductPrice(),
//                    dto.isSale(),
//                    dto.isNew(),
//                    dto.getReviewRate(),
//                    dto.getTotalReviews(),
//                    dto.getDiscount()
//
//            );
//        });
//    }


    public void createProduct(AddProductDto addProductDto) {
        Product product = new Product();

        // Basic fields
        product.setProductName(addProductDto.getProductName());
        product.setProductDescription(addProductDto.getProductDescription());
        product.setProductPrice(addProductDto.getProductPrice());
        product.setProductDiscount(addProductDto.getProductDiscount());
        product.setCoat(addProductDto.getCoat());
        product.setDryingTime(addProductDto.getDryingTime());
        product.setCoverage(addProductDto.getCoverage());
        product.setOnlineLimit(addProductDto.getOnlineLimit());
        product.setProductQuantity(addProductDto.getProductQuantity());

        product.setProductStatus(ProductStatus.valueOf(addProductDto.getProductStatus().toUpperCase()));
//        product.setProductStatus(addProductDto.getProductStatus());

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

    public void updateProduct(Long productId, UpdateProductDto updateProductDto) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product with ID " + productId + " not found.");
        }

        Product product = optionalProduct.get();

        // Update fields
        product.setProductId(updateProductDto.getProductId());
        product.setProductName(updateProductDto.getProductName());
        product.setProductDescription(updateProductDto.getProductDescription());
        product.setProductPrice(updateProductDto.getProductPrice());
        product.setProductDiscount(updateProductDto.getProductDiscount());
        product.setCoat(updateProductDto.getCoat());
    //    product.setDryingTime(updateProductDto.getDryingTime());
        product.setCoverage(updateProductDto.getCoverage());
        product.setOnlineLimit(updateProductDto.getOnlineLimit());
        product.setProductQuantity(updateProductDto.getProductQuantity());

        product.setProductStatus(ProductStatus.valueOf(updateProductDto.getProductStatus().toUpperCase()));
        product.setBrand(updateProductDto.getBrand());
        product.setRoomType(updateProductDto.getRoomType());
        product.setFinish(updateProductDto.getFinish());

        List<Surface> validSurfaces = updateProductDto.getSurfaces().stream()
                .filter(Surface::contains)
                .map(value -> Surface.valueOf(value.toUpperCase()))
                .toList();
        product.setSurfaces(validSurfaces);

        List<Position> validPositions = updateProductDto.getPositions().stream()
                .filter(Position::contains)
                .map(value -> Position.valueOf(value.toUpperCase()))
                .toList();
        product.setPositions(validPositions);

        List<ProductType> validProductTypes = updateProductDto.getProductTypes().stream()
                .filter(ProductType::contains)
                .map(value -> ProductType.valueOf(value.toUpperCase()))
                .toList();
        product.setProductType(validProductTypes);

        product.setProductFeatures(updateProductDto.getProductFeatures());

        List<String> imageIds = firebaseStorageService.uploadImagesToFirebase(updateProductDto.getProductImages());

        product.setImageIds(imageIds);

        System.out.println(product);

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
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        Product product = productOptional.get();


        List<String> imageIds = product.getImageIds();

        List<String> imageUrls = firebaseStorageService.getImageUrlsFromFirebase(imageIds);
        product.setImageIds(imageUrls);

        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public float[] getProductPriceAndDiscount(long productId) {
        List<Object[]> result = productRepository.findProductPriceAndDiscountByProductId(productId);

        if (result.isEmpty()) {
            return new float[]{0.0f, 0.0f};
        }

        return new float[]{
                (float) result.get(0)[0],
                ((float) result.get(0)[1]) * (100-(float) result.get(0)[0]) / 100
        };
    }

}
