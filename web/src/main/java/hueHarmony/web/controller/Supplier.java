package hueHarmony.web.controller;

import hueHarmony.web.dto.FilterSupplierDto;
import hueHarmony.web.dto.SupplierDto;
import hueHarmony.web.dto.SupplierVariationDto;
import hueHarmony.web.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class Supplier {

    private final SupplierService supplierService;


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
    public ResponseEntity<Object> filterSupplier(
            @Validated(FilterSupplierDto.whenOrganization.class) @ModelAttribute FilterSupplierDto request,
            BindingResult bindingResult
    ) {
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
    public ResponseEntity<Object> createSupplier(
            @Validated(SupplierDto.onCreation.class) @RequestBody SupplierDto supplierDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> updateSupplier(
            @Validated(SupplierDto.onUpdate.class) @RequestBody SupplierDto supplierDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> deleteSupplier(
            @Validated(SupplierDto.onDelete.class) @RequestBody SupplierDto supplierDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/approve")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> approveSupplier(
            @Validated(SupplierDto.onStatusUpdate.class) @RequestBody SupplierDto supplierDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/create/supplier-variation")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> createSupplierVariation(
            @Validated(SupplierVariationDto.onCreation.class) @RequestBody SupplierVariationDto supplierVariationDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/update/supplier-variation")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> updateSupplier(
            @Validated(SupplierVariationDto.onUpdate.class) @RequestBody SupplierVariationDto supplierVariationDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @DeleteMapping("/delete/supplier-variation")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> deleteSupplier(
            @Validated(SupplierVariationDto.onDelete.class) @RequestBody SupplierVariationDto supplierVariationDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
