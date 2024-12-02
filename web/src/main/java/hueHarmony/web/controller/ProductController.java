package hueHarmony.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import hueHarmony.web.dto.FilterProductDto;
import hueHarmony.web.dto.ProductDto;
import hueHarmony.web.dto.response.ProductDisplayDto;
import hueHarmony.web.model.Product;
import hueHarmony.web.service.FirebaseStorageService;
import hueHarmony.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private final FirebaseStorageService firebaseStorageService;


    @Autowired
    public ProductController(ProductService productService,FirebaseStorageService firebaseStorageService) {
        this.productService = productService;
        this.firebaseStorageService=firebaseStorageService;
    }

//    @GetMapping()
//    public ResponseEntity<?> getAllProducts(@ModelAttribute FilterProductDto filterProductDto) {
//        Page<ProductDto> products = productService.getAllProducts();
//
//        if (products.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No products available");
//        }
//
//        return ResponseEntity.ok(products);
//        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No products available");
//    }



}
