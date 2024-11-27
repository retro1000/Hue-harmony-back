package hueHarmony.web.controller;

import hueHarmony.web.dto.FilterOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class Order {

    @PostMapping("/place-order/online")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> placeNewOrderOnline(){
        try{
            return ResponseEntity.status(200).body("");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/place-order/shop")
    @PreAuthorize("hasRole('ROLE_CACHIER')")
    public ResponseEntity<Object> placeNewOrderShop(){
        try{
            return ResponseEntity.status(200).body("");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/create-order")
    @PreAuthorize("hasRole('ROLE_BACKOFFICE')")
    public ResponseEntity<Object> createOrder(){
        try{
            return ResponseEntity.status(200).body("");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/view/{orderId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_SALESMANAGER', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> viewOrder(@PathVariable("orderId") int orderId){
        try{
            return ResponseEntity.status(200).body("");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SALESMANAGER', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> filterOrders(@Validated(FilterOrderDto.whenOrganization.class) @ModelAttribute FilterOrderDto request) {
        try{
            return ResponseEntity.status(200).body("");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/filter-my-orders")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> filterMyOrders(@Validated(FilterOrderDto.whenUser.class) @ModelAttribute FilterOrderDto request) {
        try{
            return ResponseEntity.status(200).body("");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
