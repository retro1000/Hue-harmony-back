package hueHarmony.web.controller;

import hueHarmony.web.dto.FilterCustomerDto;
import hueHarmony.web.dto.FilterOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class Customer {

    @GetMapping("/view/{customerId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER')")
    public ResponseEntity<Object> viewCustomer(@PathVariable("customerId") int customerId) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER')")
    public ResponseEntity<Object> filterCustomer(
            @Validated(FilterOrderDto.whenOrganization.class) @ModelAttribute FilterCustomerDto request,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "select", defaultValue = "all") String select,
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> createCustomer() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> updateCustomer() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> deleteCustomer() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/approve")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> approveCustomer() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
