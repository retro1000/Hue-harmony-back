package hueHarmony.web.controller;

import hueHarmony.web.service.PosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Pos")
@RequiredArgsConstructor
public class Pos {

    private final PosService posService;

    @GetMapping("/get-products")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER')")
    public ResponseEntity<Object> getProducts(@PathVariable("customerId") int customerId) {
        try{
            return ResponseEntity.status(200).body("Supplier status update successfully.");

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }


}
