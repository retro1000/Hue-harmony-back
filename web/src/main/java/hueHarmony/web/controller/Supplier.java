package hueHarmony.web.controller;

import hueHarmony.web.dto.FilterSupplierDto;
import hueHarmony.web.dto.FilterUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class Supplier {

    @GetMapping("/view/{supplierId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INVENTORYMANAGER', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> viewSupplier(@PathVariable("supplierId") int supplierId) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INVENTORYMANAGER', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> filterSupplier(@Validated(FilterSupplierDto.whenOrganization.class) @ModelAttribute FilterSupplierDto request) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/filter/variation/{variationId}")
    @PreAuthorize("hasAnyRole('ROLE_INVENTORYMANAGER')")
    public ResponseEntity<Object> filterSupplierByVariation(@PathVariable("variationId") String variationId) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> createSupplier() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> updateSupplier() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> deleteSupplier() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/approve")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> approveSupplier() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
