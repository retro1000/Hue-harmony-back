package hueHarmony.web.controller;

import hueHarmony.web.dto.AddProductDto;
import hueHarmony.web.dto.FilterProductDto;
import hueHarmony.web.dto.FilterUserDto;
import hueHarmony.web.dto.response.ProductDisplayDto;
import hueHarmony.web.dto.response.ProductUserDisplayDto;
import hueHarmony.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class Product {
    private final ProductService productService;

    @GetMapping("/view/{productId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_USER', 'ROLE_SALESMANAGER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> viewProduct(@PathVariable("productId") int productId) {
        try{
            return ResponseEntity.status(200).body("Supplier status update successfully.");

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> filter(@Validated(FilterProductDto.whenOrganization.class) @ModelAttribute FilterProductDto request) {
        try{
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

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateProduct() {
        try{
            return ResponseEntity.status(200).body("Supplier status update successfully.");

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

//    @DeleteMapping("/delete")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<Object> deleteProduct() {
//        try{
//            return ResponseEntity.status(200).body("Supplier status update successfully.");
//        }catch(Exception e){
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }

    @GetMapping("/filter-products")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> filterProducts(@ModelAttribute FilterProductDto productFilterDto){
        try{
            Page<ProductUserDisplayDto> displayDtos = productService.filterProductsForList(productFilterDto);
//
            if(displayDtos.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

            return ResponseEntity.status(HttpStatus.OK).body(displayDtos);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal server error!!! Please try again later...");
        }
    }

    @GetMapping("/filter")
    @PreAuthorize("hasRole('ROLE_USER')")
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
}
