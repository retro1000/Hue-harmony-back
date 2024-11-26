package hueHarmony.web.controller;

import hueHarmony.web.dto.PosOrderDto;
import hueHarmony.web.dto.PosProductDto;
import hueHarmony.web.service.PosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos")
@RequiredArgsConstructor
public class Pos {

    private final PosService posService;

    @GetMapping("/get-products")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER')")
    public ResponseEntity<Object> getProducts() {
        try{
            List<PosProductDto> products = posService.getProducts();
//           return ResponseEntity.status(200).body("Supplier status update successfully.");
            return ResponseEntity.status(200).body(products);

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }


}
