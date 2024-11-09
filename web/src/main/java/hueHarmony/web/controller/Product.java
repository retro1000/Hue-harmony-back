package hueHarmony.web.controller;

import hueHarmony.web.dto.FilterProductDto;
import hueHarmony.web.dto.FilterUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
        return null;
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> filter(@Validated(FilterProductDto.whenOrganization.class) @ModelAttribute FilterProductDto request) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
        return null;
    }

    @GetMapping("/filter/product")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> filterProduct(@Validated(FilterProductDto.whenUser.class) @ModelAttribute FilterProductDto request) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
        return null;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createProduct() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
        return null;
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateProduct() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
        return null;
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteProduct() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
        return null;
    }
}
