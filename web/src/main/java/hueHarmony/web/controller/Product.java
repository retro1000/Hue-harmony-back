package hueHarmony.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class Product {

    @GetMapping("/view/{productId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_USER', 'ROLE_SALESMANAGER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> viewProduct(@PathVariable("productId") int productId) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> filter(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "select", defaultValue = "all") String select,
            @RequestParam(value = "brand", required = false) List<String> brands,
            @RequestParam(value = "color", required = false) List<String> color,
            @RequestParam(value = "size", required = false) List<String> size,
            @RequestParam(value = "price", required = false) List<String> price,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "sort", defaultValue = "id,asc") String sort
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/filter/product")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> filterProduct(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "brand", required = false) String brands,
            @RequestParam(value = "room_type", required = false) String roomType,
            @RequestParam(value = "finish", required = false) String finish,
            @RequestParam(value = "product_type", required = false) String productType,
            @RequestParam(value = "color", required = false) List<String> color,
            @RequestParam(value = "size", required = false) List<String> size,
            @RequestParam(value = "surface", required = false) List<String> surface,
            @RequestParam(value = "position", required = false) List<String> position,
            @RequestParam(value = "price", required = false) List<String> price,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "sort", defaultValue = "id,asc") String sort
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createProduct() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateProduct() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteProduct() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
