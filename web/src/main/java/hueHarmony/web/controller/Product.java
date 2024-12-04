package hueHarmony.web.controller;

import hueHarmony.web.dto.AddProductDto;
import hueHarmony.web.dto.FilterProductDto;
import hueHarmony.web.dto.GetProductDto;
import hueHarmony.web.dto.UpdateProductDto;
import hueHarmony.web.dto.response.PosDisplayDto;
import hueHarmony.web.dto.response.PopularProductsDto;
import hueHarmony.web.dto.response.ProductDisplayDto;
import hueHarmony.web.dto.response.ProductUserDisplayDto;
import hueHarmony.web.service.FirebaseStorageService;
import hueHarmony.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class Product {
    private final ProductService productService;
    private final FirebaseStorageService firebaseStorageService;

    @GetMapping("/view/{productId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_USER', 'ROLE_SALESMANAGER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> viewProduct(@PathVariable("productId") int productId) {
        try{
            return ResponseEntity.status(200).body("Supplier status update successfully.");

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/create")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createProduct(@RequestBody AddProductDto addProductDto) {
        try{
            productService.createProduct(addProductDto);
            return ResponseEntity.status(200).body("Supplier status update successfully.");

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam Long productId) {
        try{
            productService.deleteProduct(productId);
            return ResponseEntity.status(200).body("Product Deleted successfully.");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }

    }


//    @GetMapping("/filter-products")
//    //    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<Object> filterProducts(@ModelAttribute FilterProductDto productFilterDto){
//        try{
//            Page<ProductUserDisplayDto> displayDtos = productService.filterProductsForList(productFilterDto);
//
//            if(displayDtos.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//
//            return ResponseEntity.status(HttpStatus.OK).body(displayDtos);
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().body("Internal server error!!! Please try again later...");
//        }
//    }

    @GetMapping("/filter")
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> filterProductsTable(@ModelAttribute FilterProductDto productFilterDto){
        try{
            Page<ProductDisplayDto> displayDtos = productService.filterProductsForDashboardTable(productFilterDto);

            if(displayDtos.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

            return ResponseEntity.status(HttpStatus.OK).body(displayDtos);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal server error!!! Please try again later...");
        }
    }

    @GetMapping("/filter-color/{color}")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> filterByColor(@PathVariable String color){
        try{
            List<PopularProductsDto> displayDtos = productService.filterProductsByColor(color);

            if(displayDtos.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

            return ResponseEntity.status(HttpStatus.OK).body(displayDtos);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal server error!!! Please try again later...");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(
            @RequestParam Long productId,
            @RequestBody UpdateProductDto updateProductDto) {

        try {
            productService.updateProduct(productId, updateProductDto);
            return ResponseEntity.ok("Product updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to update product: " + e.getMessage());
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<hueHarmony.web.model.Product> getProductDetails(@PathVariable("id") Long productId) {
        hueHarmony.web.model.Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/read/popular")
    public ResponseEntity<Page<PopularProductsDto>> getPopularProducts(Pageable pageable) {
        Page<PopularProductsDto> product = productService.filterProductsForPopularProducts(pageable);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/image-urls")
    public ResponseEntity<Map<String, String>> getImageUrls(@RequestBody List<String> imageIds) {
        if (imageIds == null || imageIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Image IDs cannot be null or empty"));
        }

        try {
            // Get URLs for the given image IDs
            List<String> urls = firebaseStorageService.getImageUrlsFromFirebase(imageIds);

            // Map image IDs to URLs
            Map<String, String> imageUrlMap = imageIds.stream()
                    .collect(Collectors.toMap(id -> id, id -> urls.get(imageIds.indexOf(id))));

            return ResponseEntity.ok(imageUrlMap);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }


    @GetMapping("/read/all")
    public ResponseEntity<List<GetProductDto>> getAllProducts() {
        List<GetProductDto> products = productService.fetchAllProducts();
        return ResponseEntity.ok(products);
    }

}
